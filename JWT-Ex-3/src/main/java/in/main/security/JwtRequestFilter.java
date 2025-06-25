package in.main.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import in.main.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader=request.getHeader("Authorization");
		String email=null;
		String jwt=null;
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")) {
			jwt=authorizationHeader.substring(7);
			email=jwtUtil.extractEmail(jwt);
		}
		if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			if(jwtUtil.validateToken(jwt, email)) {
				List<String>rolesList=jwtUtil.extractRoles(jwt);
				List<SimpleGrantedAuthority>authorities=new ArrayList<SimpleGrantedAuthority>();
				for(String role:rolesList) {
					authorities.add(new SimpleGrantedAuthority(role));
				}
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(email,null,authorities);
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}
	

}
