package com.suresh.demo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

/*
 * a simple domain entity doubling as a DTO
 */
@Entity
@Table(name = "dog")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Dog {

    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @Column(name="breed")
    String breed;

   
    @Column()
    private String imageUrl;

    public Dog() {
    }

    public Dog(String name, String description) {
        this.name = name;
        this.description = description;
      
    }

    public Long getId() {
        return this.id;
    }

    // for tests ONLY
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
    public String toString() {
        return "Dog {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", breed='" + breed + '\'' +
                ", imageUrl=" + imageUrl +
                '}';
    }
}
