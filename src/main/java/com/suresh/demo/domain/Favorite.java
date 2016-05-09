package com.suresh.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "favorite")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Favorite {
	
    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long dogId;
    

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getuserId() {
		return userId;
	}

	public void setuserId(long userId) {
		this.userId = userId;
	}

	public Long getDogId() {
		return dogId;
	}

	public void setDogId(Long dogId) {
		this.dogId = dogId;
	}
	
	

	public Favorite() {
	    }

	    public Favorite(Long userId, Long dogId) {
	        this.userId = userId;
	        this.dogId = dogId;
	    }
	   
	
	@Override
    public String toString() {
        return "Favorite {" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", dogId=" + dogId+
                '}';
    }

}
