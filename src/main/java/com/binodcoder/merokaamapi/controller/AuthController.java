package com.binodcoder.merokaamapi.controller;
import com.binodcoder.merokaamapi.dto.ApiError;
import com.binodcoder.merokaamapi.entity.Users;
import com.binodcoder.merokaamapi.entity.UsersType;
import com.binodcoder.merokaamapi.exceptions.EmailAlreadyExistsException;
import com.binodcoder.merokaamapi.repository.UserRepository;
import com.binodcoder.merokaamapi.repository.UserTypeRepository;
import com.binodcoder.merokaamapi.security.jwt.JwtUtils;
import com.binodcoder.merokaamapi.security.request.LoginRequest;
import com.binodcoder.merokaamapi.security.request.SignupRequest;
import com.binodcoder.merokaamapi.security.response.MessageResponse;
import com.binodcoder.merokaamapi.security.response.UserInfoResponse;
import com.binodcoder.merokaamapi.security.services.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "Bad credentials");
            map.put("status", false);
            return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        String userTypeNames = userDetails.getUserTypeName();
        UserInfoResponse response = new UserInfoResponse(userDetails.getId(),
                userDetails.getUsername(), userTypeNames, jwtCookie.toString());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
                        jwtCookie.toString())
                .body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already in use!");
        }
        // Create new user's account
        Users user = new Users(  signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                true,
                new Date(),
                new UsersType(0, "Job Seeker", null)
        );
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/username")
    public String currentUserName(Authentication authentication) {
        if (authentication != null)
            return authentication.getName();
        else
            return "";
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserDetails(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String userTypeName=userDetails.getUserTypeName();
        UserInfoResponse response = new UserInfoResponse(userDetails.getId(),
                userDetails.getUsername(), userTypeName);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,
                        cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
