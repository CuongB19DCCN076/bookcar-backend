package bookcarupdate.bookcar.controller;

import bookcarupdate.bookcar.dto.*;
import bookcarupdate.bookcar.exception.CloudNotFoundException;
import bookcarupdate.bookcar.models.*;
import bookcarupdate.bookcar.repositories.OrderRepository;
import bookcarupdate.bookcar.repositories.ProductRepository;
import bookcarupdate.bookcar.repositories.StoreRepository;
import bookcarupdate.bookcar.repositories.UserRepository;
import bookcarupdate.bookcar.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final StoreService storeService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final OrderService orderService;
    private final StoreRepository storeRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/sign-up-seller")
    public ResponseEntity<Store> signUpSeller(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUpSeller(signUpRequest));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refresh(refreshTokenRequest));
    }

    @GetMapping("/get-role/{email}")
    private ResponseEntity<Role> getRoleUser(@PathVariable("email") String email) {
        System.out.println("email:" + email);
        return ResponseEntity.ok(userService.getCurrentUser(email).getRole());
    }


    @GetMapping("/get-user/{email}")
    private ResponseEntity<User> getUser(@PathVariable("email") String email) {
        System.out.println("email:" + email);
        return ResponseEntity.ok(userService.getCurrentUser(email));
    }

    @PostMapping("/get-store")
    private ResponseEntity<User> getIdStore(@RequestBody GetStoreDTO email) {
        System.out.println(email.getEmailUser());
        return ResponseEntity.ok(userService.getCurrentUser(email.getEmailUser()));
    }

    @GetMapping("/get-store/{id}")
    private ResponseEntity<Optional<Store>> getStore(@PathVariable("id") Long id) {
        return ResponseEntity.ok(storeService.getStore(id));
    }

}
