package study.board.user.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.user.dto.CreateUserDTO;
import study.board.user.dto.LoginUserDTO;
import study.board.user.dto.UserDTO;
import study.board.user.service.UserService;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        UserDTO userDTO = userService.createUser(createUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        //TODO header에 token 처리
        String token = userService.loginUser(loginUserDTO);
        return ResponseEntity.ok().body(token);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO userDTO = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updateUserDTO) {
        UserDTO userDTO = userService.updateUser(id, updateUserDTO);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
