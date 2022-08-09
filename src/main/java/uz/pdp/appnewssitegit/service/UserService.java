package uz.pdp.appnewssitegit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssitegit.entity.Lavozim;
import uz.pdp.appnewssitegit.entity.User;
import uz.pdp.appnewssitegit.payload.ApiResponse;
import uz.pdp.appnewssitegit.payload.UserDto;
import uz.pdp.appnewssitegit.repository.LavozimRepository;
import uz.pdp.appnewssitegit.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LavozimRepository lavozimRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse addUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()){
            return new ApiResponse("Bunday user mavjut",false);
        }
        Optional<Lavozim> optionalLavozim = lavozimRepository.findById(userDto.getLavozimId());
        if (!optionalLavozim.isPresent()){
            return new ApiResponse("Bunday lavozim mavjut emas",false);
        }
        User user=new User();
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setLavozim(optionalLavozim.get());
        user.setEnabled(true);
        userRepository.save(user);
        return new ApiResponse("User lavozimi bilan saqlandi",true);
    }
}
