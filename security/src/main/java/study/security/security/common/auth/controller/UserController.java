package study.security.security.common.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.security.security.common.auth.dto.UserDTO;
import study.security.security.common.auth.security.service.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO createUserRequestDTO){
        return ResponseEntity.ok().body(createUserRequestDTO);
    }
}
