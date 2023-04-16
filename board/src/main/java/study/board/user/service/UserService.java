package study.board.user.service;

import study.board.user.dto.CreateUserDTO;
import study.board.user.dto.LoginUserDTO;
import study.board.user.dto.UserDTO;


public interface UserService {

    UserDTO createUser(CreateUserDTO userDTO);
    UserDTO getUser(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    String loginUser(LoginUserDTO loginUserDTO);
}
