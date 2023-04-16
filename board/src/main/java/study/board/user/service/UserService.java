package study.board.user.service;

import study.board.user.dto.UserDTO;


public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
