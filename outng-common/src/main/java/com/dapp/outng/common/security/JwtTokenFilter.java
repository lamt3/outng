//package com.dapp.outng.common.security;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.dapp.outng.common.exception.OutngRuntimeException;
//import com.dapp.outng.common.http.SecurityConstants;
//
//@Component
//public class JwtTokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String jwtToken = jwtTokenProvider.resolveToken(request);
//
//        if (StringUtils.isNotEmpty(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
//            String userId = jwtTokenProvider.getUserIdFromJwt(jwtToken);
//            List<SimpleGrantedAuthority> authorities = jwtTokenProvider.getUserAuthoritiesFromJwt(jwtToken);
//
//            if (StringUtils.isNotEmpty(userId)) {
//                Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, authorities);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else {
//                throw new OutngRuntimeException(HttpStatus.UNAUTHORIZED, SecurityConstants.USER_UNAUTHORIZED_MESSAGE);
//            }
//        } else {
//            throw new OutngRuntimeException(HttpStatus.UNAUTHORIZED, SecurityConstants.USER_UNAUTHORIZED_MESSAGE);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}
