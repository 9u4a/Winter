package study.board.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.user.domain.User;
import study.board.user.dto.CreateUserDTO;
import study.board.user.dto.UserDTO;
import study.board.user.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public UserDTO createUser(CreateUserDTO userDTO) {
        //validation 구현 필요
        userDTO.encodePassword(encoder);
        User savedUser = userRepository.save(userDTO.toEntity());
        return new UserDTO(savedUser);
    }

    @Override
    @Transactional
    public UserDTO getUser(Long id) {
        return new UserDTO(userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원")));
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userDTO.toEntity();
        user.setId(id);
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
