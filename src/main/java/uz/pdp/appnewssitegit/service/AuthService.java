package uz.pdp.appnewssitegit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssitegit.entity.User;
import uz.pdp.appnewssitegit.excetions.ResourceNotFoundException;
import uz.pdp.appnewssitegit.payload.ApiResponse;
import uz.pdp.appnewssitegit.payload.RegisterDto;
import uz.pdp.appnewssitegit.repository.LavozimRepository;
import uz.pdp.appnewssitegit.repository.UserRepository;
import uz.pdp.appnewssitegit.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public ApiResponse registerUser(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getPrePassword())){
            return new ApiResponse("Parollar mos emas",false);
        }

        if (userRepository.existsByUsername(registerDto.getUsername())){
            return new ApiResponse("Bunday username mavjut",false);
        }

        User user=new User(
                registerDto.getFullName(),
                registerDto.getUsername(),
                passwordEncoder.encode(registerDto.getPassword()),
                lavozimRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("Lavozim","name",AppConstants.USER)),
                true

        );
        userRepository.save(user);
        return new ApiResponse("Muofaqiyatlik royhatdan otdingiz",true);
    }

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));

    }
}
