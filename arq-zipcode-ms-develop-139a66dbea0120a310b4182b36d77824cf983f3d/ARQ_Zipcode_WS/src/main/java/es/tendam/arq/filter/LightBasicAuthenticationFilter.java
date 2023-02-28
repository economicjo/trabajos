package es.tendam.arq.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LightBasicAuthenticationFilter extends OncePerRequestFilter {

	private static final String AUTH_BASIC_SCHEME = "Basic";
	
	@Value("${service.auth.basic.excludePathList:''}")

	private List<String> noAuthList;
	
	@Value("${service.auth.basic.username}")
	private String user;
		
	@Value("${service.auth.basic.password}")
	private String password;
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
    	
    	if (noAuthList.contains(request.getServletPath())) {
    		 filterChain.doFilter(request, response);
    	} else {
    	
	        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	        if (authHeader != null) {
	            StringTokenizer st = new StringTokenizer(authHeader);
	            if (st.hasMoreTokens()) {
	                String basic = st.nextToken();
	
	                if (basic.equalsIgnoreCase(AUTH_BASIC_SCHEME)) {
	                    try {
	                        String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
	                        int p = credentials.indexOf(":");
	                        if (p != -1) {
	                            String _username = credentials.substring(0, p).trim();
	                            String _password = credentials.substring(p + 1).trim();
	
	                            if (!user.equals(_username) || !password.equals(_password)) {
	                                unauthorized(response, "Bad credentials");
	                            }
	
	                            filterChain.doFilter(request, response);
	                        } else {
	                            unauthorized(response, "Invalid authentication token");
	                        }
	                    } catch (UnsupportedEncodingException e) {
	                        throw new Error("Couldn't retrieve authentication", e);
	                    }
	                }
	            }
	        } else {
	            unauthorized(response);
	        }
    	}
    	
    }

    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"tendam\"");
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
    }
}