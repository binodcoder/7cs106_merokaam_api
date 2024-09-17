package com.binodcoder.merokaamapi.security.request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {

    @NotBlank(message = "Email is required.")
    @Size(max = 50)
    @Email(message = "Please provide a valid email address.")
    private String email;

    private Set<String> role;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 40)
    private String password;

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}