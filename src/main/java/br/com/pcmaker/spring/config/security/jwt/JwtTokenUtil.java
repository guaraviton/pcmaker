package br.com.pcmaker.spring.config.security.jwt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.pcmaker.common.util.ConfiguracaoUtils;
import br.com.pcmaker.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author y2jm
 *
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_NOME = "nome";
    static final String CLAIM_KEY_ID = "id";
    static final String CLAIM_KEY_CREATED = "created";
    static final String CLAIM_KEY_PERFIL = "perfil";
    
    public String getLoginFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    
    public Integer getIdFromToken(String token) {
        Integer id;
        try {
            final Claims claims = getClaimsFromToken(token);
            id = (Integer) claims.get(CLAIM_KEY_ID);
        } catch (Exception e) {
        	id = null;
        }
        return id;
    }
    
    public String getNomeFromToken(String token) {
        String nome;
        try {
            final Claims claims = getClaimsFromToken(token);
            nome = (String) claims.get(CLAIM_KEY_NOME);
        } catch (Exception e) {
        	nome = null;
        }
        return nome;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(ConfiguracaoUtils.get("jwt.secret"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + Integer.parseInt(ConfiguracaoUtils.get("jwt.expiration")) * 1000 * 60);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Usuario usuario, Collection<? extends GrantedAuthority> perfis) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(CLAIM_KEY_USERNAME, usuario.getLogin());
        claims.put(CLAIM_KEY_NOME, usuario.getNome());
        claims.put(CLAIM_KEY_ID, usuario.getId());
        claims.put(CLAIM_KEY_PERFIL, perfis);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
    
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, ConfiguracaoUtils.get("jwt.secret"))
                .compact();
    }

    public Boolean validateToken(String token, String chaveUsuario) {
        final String username = getLoginFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (
                username.equals(chaveUsuario)
                        && !isTokenExpired(token));
    }

	public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
		List<GrantedAuthority> perfis;
        try {
            final Claims claims = getClaimsFromToken(token);
            perfis = getPerfis(claims.get(CLAIM_KEY_PERFIL));
        } catch (Exception e) {
        	perfis = null;
        }
        return perfis;
	}

	@SuppressWarnings("unchecked")
	private List<GrantedAuthority> getPerfis(Object object) {
		if(object == null){
			return null;
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<Map<String,String>> perfisObj = (List<Map<String,String>>) object;
		for(Map<String,String> perfil : perfisObj){
			authorities.add(new SimpleGrantedAuthority(perfil.get("authority")));
		}
		return authorities;
	}
	
}