package com.example.books.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  public static final String TOKEN_PREFIX = "Bearer";
  public static final String HEADER_STRING = "Authorization";

  @Autowired
  private TokenProvider tokenProvider;

  @Qualifier("customUserDetailsService")
  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain
  ) throws ServletException, IOException {
    String header = httpServletRequest.getHeader(HEADER_STRING);
    String userName = null;
    String authToken = null;
    if (header != null && header.startsWith(TOKEN_PREFIX)) {
      authToken = header.replace(TOKEN_PREFIX, "");
      try {
        userName = tokenProvider.getUsernameFromToken(authToken);
      } catch (IllegalArgumentException e) {
        logger.warn("An error occurred during getting username from token.", e);
      } catch (ExpiredJwtException e) {
        logger.warn("The token is expired and not valid anymore.", e);
      } catch (SignatureException e) {
        logger.warn("Authentication failed. Username and password not valid.", e);
      }
    } else {
      logger.warn("Couldn't find bearer string, will ignore the header.");
    }

    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

      if (tokenProvider.validateToken(authToken, userDetails)) {
        UsernamePasswordAuthenticationToken authentication =
            tokenProvider.getAuthentication(
                authToken,
                SecurityContextHolder.getContext().getAuthentication(),
                userDetails
            );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        logger.debug("Authenticated user " + userName + ", setting security context.");
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
