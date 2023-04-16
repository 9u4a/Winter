package study.security.security.common.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.security.security.common.auth.domain.User;
import study.security.security.common.auth.dto.UserDTO;
import study.security.security.common.auth.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        userRepository.findByEmail(userDTO.getEmail())
                .ifPresent(user -> {
                    throw new RuntimeException("존재");
                });

        User user = userDTO.toEntity();
        return new UserDTO(userRepository.save(user));
    }
}
