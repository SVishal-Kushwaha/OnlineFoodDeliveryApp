package com.authorize.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="restraunt_owner")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestrauntOwner {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // This is also used in Restaurant.ownerId

    private String name;

    private String email;

    private String phone;

    private String password; 

    private String role;

}
