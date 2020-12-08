package br.ueg.webflux.document;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3117776853367111175L;

	@Id
    private String id;
    
	private String name;
    private String username;
    private String password;
    
    private Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
    
    @Transient
    private List<String> rolesArray = null;
    
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
      
    public List<String> getRolesArray() {
  		return rolesArray;
  	}


    public void deleteJson() {
    	this.rolesArray = null;
    }

  	public void setRolesArray(List<String> rolesArray) {
  		this.rolesArray = rolesArray;
  	}

  	public Set<GrantedAuthority> getRoles() {
  		return roles;
  	}

  	public void setRoles(Set<GrantedAuthority> roles) {
  		this.roles = roles;
  	}
  	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles;
    }
	
    @Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
