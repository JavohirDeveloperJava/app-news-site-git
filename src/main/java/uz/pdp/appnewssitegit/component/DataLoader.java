package uz.pdp.appnewssitegit.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssitegit.entity.Lavozim;
import uz.pdp.appnewssitegit.entity.User;
import uz.pdp.appnewssitegit.entity.enums.Huquq;
import uz.pdp.appnewssitegit.repository.LavozimRepository;
import uz.pdp.appnewssitegit.repository.UserRepository;
import uz.pdp.appnewssitegit.utils.AppConstants;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LavozimRepository lavozimRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Value("${spring.sql.init.mode}")
    private String initleMode;


    @Override
    public void run(String... args) throws Exception {
       if (initleMode.equals("always")){
           Huquq[] huquqs = Huquq.values();

           Lavozim admin = lavozimRepository.save(new Lavozim(
                   AppConstants.ADMIN,
                   Arrays.asList(huquqs),
                   "Sistema egasi"
           ));

           Lavozim user = lavozimRepository.save(new Lavozim(
                   AppConstants.USER,
                   Arrays.asList(Huquq.ADD_COMMENT, Huquq.EDIT_COMMENT, Huquq.DELETE_MY_COMMENT),
                   "Oddiy foydalanuvchi"
           ));

           userRepository.save(new User(
                   "Admin",
                   "admin",
                   passwordEncoder.encode("admin123"),
                   admin,
                   true
           ));

           userRepository.save(new User(
                   "User",
                   "user",
                   passwordEncoder.encode("user123"),
                   user,
                   true
           ));
       }
    }
}
