package com.gaogao.cucaishop.adm.security;

import com.gaogao.cucaishop.adm.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public static final String REQ_USR = "jwtUser";
    public static final String REQ_HEADER_AUTH = "Authorization";

    @Autowired
    private JwtTokenProvider tokenProvider;


    @Autowired
    private MyUserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (tokenProvider == null) {
                ServletContext servletContext = request.getServletContext();
                WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
                tokenProvider = webApplicationContext.getBean(JwtTokenProvider.class);
            }

            String jwt = request.getHeader(REQ_HEADER_AUTH);
            System.out.println(jwt);

            if (StringUtils.hasText(jwt) && jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7, jwt.length());
                if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                    String jwtUser = tokenProvider.getJwtUser(jwt);
                    request.setAttribute(REQ_USR, jwtUser);

                    UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUser);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);

        }
        filterChain.doFilter(request, response);
    }
}
