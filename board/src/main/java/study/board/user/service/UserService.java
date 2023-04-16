package study.board.member.service;

import study.board.member.dto.UserDTO;


public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
