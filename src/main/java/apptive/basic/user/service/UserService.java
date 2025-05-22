package apptive.basic.user.service;

import apptive.basic.user.dto.UserSignupRequestDto;
import apptive.basic.user.repository.UserRepository;
import apptive.basic.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void signup(UserSignupRequestDto requestDto)
    {
        Optional<User> existingUser = userRepository.findByEmail(requestDto.getEmail());
        if(existingUser.isPresent())
        {
            throw  new IllegalArgumentException("이미 가입된 이메일 입니다.");
        }

        if(!requestDto.getPassword().equals(requestDto.getConfirmPassword()))
        {
            throw new IllegalArgumentException("비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        //여기 부터 if 두개 맞는건가
        if(!requestDto.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
        {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }

        if(userRepository.findByEmail(requestDto.getEmail()).isPresent())
        {
            throw new IllegalArgumentException("이미 사용중인 이메일 입니다.");
        }

        User user = new User(
                requestDto.getEmail(),
                requestDto.getName(),
                requestDto.getPassword()
        );

        userRepository.save(user);
    }
}
