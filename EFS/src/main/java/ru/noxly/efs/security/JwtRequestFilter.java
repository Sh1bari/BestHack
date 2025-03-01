package ru.noxly.efs.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.noxly.efs.webClient.auth.AuthClient;
import ru.noxly.efs.webClient.auth.models.UserDto;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final AuthClient authClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val jwt = authHeader.substring(7);
            try {
                val user = authClient.validateUser(jwt);
                if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    List<SimpleGrantedAuthority> authorities = mapAuthorities(user);
                    UserDetails userDetails = new CustomUserDetails(user, authorities);
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            authorities
                    );
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            } catch (ExpiredJwtException e) {
                log.warn("Token timeout");
            } catch (Exception e) {
                log.warn("Wrong token");
            }
        }else {
            setAnonymousUser();
        }

        filterChain.doFilter(request, response);
    }

    private List<SimpleGrantedAuthority> mapAuthorities(UserDto user) {
        return user.getRoles().stream().map(o -> new SimpleGrantedAuthority(o.toString())).toList();
    }

    private void setAnonymousUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
            UserDetails anonymousUser = new CustomUserDetails(null, authorities);
            UsernamePasswordAuthenticationToken anonymousAuth =
                    new UsernamePasswordAuthenticationToken(anonymousUser, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(anonymousAuth);
        }
    }
}
