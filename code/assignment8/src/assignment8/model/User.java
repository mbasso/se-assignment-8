package assignment8.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="users")
public class User {
    
	@Id
    @Column(name="username")    
	private String username;
    
    @Column(name="password")    
	private String password;
    
    @Column(name="name")
	private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private User bestFriend;

    @OneToOne(cascade = {CascadeType.ALL})
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Address address;
	
	public User() {}
	
	public User(String username, String password, String name, User bestFriend, Address address) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.bestFriend = bestFriend;
		this.address = address;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getBestFriend() {
		return bestFriend;
	}

	public void setBestFriend(User bestFriend) {
		this.bestFriend = bestFriend;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
