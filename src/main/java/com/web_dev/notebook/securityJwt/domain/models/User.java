package com.web_dev.notebook.securityJwt.domain.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Data
@Document(collection = "users")
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  private LocalDateTime created = LocalDateTime.now();

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  private String userUUID;

  @NotBlank
  @Size(max = 120)
  private String password;

  @DocumentReference
  private Set<Role> roles = new HashSet<>();

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.userUUID = UUID.randomUUID().toString();

  }
  public User(){}
  public User(String username, String email, String password, Set<Role> roles) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.userUUID = UUID.randomUUID().toString();
  }





}
