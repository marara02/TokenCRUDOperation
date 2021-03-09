package com.jwtaut.demo;

import com.jwtaut.demo.models.AuthontificationRequest;
import com.jwtaut.demo.models.AuthontificationResponse;
import com.jwtaut.demo.models.UserModel;
import com.jwtaut.demo.models.UserRepository;
import com.jwtaut.demo.services.UserService;
import com.jwtaut.demo.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtils jwtUtils;

    @GetMapping("/dashboard")


    private String testingToken() {
        return "Welcome to WorldWideNet ^_^ ";
    }

    @PostMapping("/subs")


    private ResponseEntity<?> subscribeClient(@RequestBody AuthontificationRequest authontificationRequest) {

        String username = authontificationRequest.getUsername();
        String password = authontificationRequest.getPassword();
        String email = authontificationRequest.getEmail();
        UserModel userModel = new UserModel();
        userModel.setUsername(username);
        userModel.setPassword(password);
        userModel.setEmail(email);
        try {
            userRepository.save(userModel);
        } catch (Exception error) {
            return ResponseEntity.ok(new AuthontificationResponse(("Error during client subscription" + username)));
        }
        return ResponseEntity.ok(new AuthontificationResponse("Successfully subscription of client" + username));
    }

    @PostMapping("/auth")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthontificationRequest authontificationRequest) {

        String username = authontificationRequest.getUsername();
        String password = authontificationRequest.getPassword();
        String email = authontificationRequest.getEmail();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception error) {
            return ResponseEntity.ok(new AuthontificationResponse("Error during authentication of client" + "" + username));
        }

        UserDetails loadedUser = userService.loadUserByUsername(username);
        String generatedToken = jwtUtils.generateToken(loadedUser);

        return ResponseEntity.ok(new AuthontificationResponse(generatedToken));
    }


}
