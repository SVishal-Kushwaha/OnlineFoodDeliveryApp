package com.authorize.entity;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "delivery_agents")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 

    private String name;

    private String email; // unique, used for login
    
    private String password;

    private String phone;

    private boolean available; // whether agent is free to be assigned
    
    private String role;

    private Instant registeredAt;
}