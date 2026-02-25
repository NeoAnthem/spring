package com.example.teacher.filter;

import com.example.teacher.service.impl.CustomeUserDetailService;
import com.example.teacher.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomeUserDetailService customeUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path=request.getRequestURI();

        if (path.equals("/api/v1/register/teacher")||path.equals("/api/v1/login/teacher")){
            System.out.println("no filtering");
            filterChain.doFilter(request,response);
            return;
        }
        String token=null;
        String username=null;
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
            String role = jwtService.extractRole(token);

        }
        if (username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=customeUserDetailService.loadUserByUsername(username);

            if (jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }


        filterChain.doFilter(request, response);
    }
}
