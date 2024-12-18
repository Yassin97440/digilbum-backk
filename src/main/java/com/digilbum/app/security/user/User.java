package com.digilbum.app.security.user;

import com.digilbum.app.security.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User implements UserDetails {

  @Setter
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Setter
  @Getter
  private String firstname;
  @Setter
  @Getter
  private String lastname;
  @Setter
  @Getter
  private String email;
  @Setter
  private String password;

  @Setter
  @Getter
  @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
  private List<Token> tokens = new ArrayList<>();

  @Setter
  @ManyToMany(fetch = FetchType.EAGER)
  private List<Role> roles = new ArrayList<>();

  @Override
  public Collection<Role> getAuthorities() {
    return roles;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
