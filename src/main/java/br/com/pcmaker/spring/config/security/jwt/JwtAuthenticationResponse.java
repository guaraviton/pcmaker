package br.com.pcmaker.spring.config.security.jwt;

import java.io.Serializable;

/**
 * 
 * @author y2jm
 *
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
