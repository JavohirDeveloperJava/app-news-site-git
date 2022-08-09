package uz.pdp.appnewssitegit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssitegit.entity.Lavozim;
import uz.pdp.appnewssitegit.payload.ApiResponse;
import uz.pdp.appnewssitegit.payload.LavozimDto;
import uz.pdp.appnewssitegit.repository.LavozimRepository;

@Service
public class LavozimService {

    @Autowired
    LavozimRepository lavozimRepository;
    public ApiResponse addLavozim(LavozimDto lavozimDto){
        boolean exists = lavozimRepository.existsByName(lavozimDto.getName());
        if (exists){
            return new ApiResponse("Bumday lavozim bor",false);
        }

        Lavozim lavozim=new Lavozim(
                lavozimDto.getName(),
                lavozimDto.getHuquqList(),
                lavozimDto.getDescription());

        lavozimRepository.save(lavozim);

        return new ApiResponse("Saqlandi",true);

    }

    public ApiResponse editLavozim(Long id, LavozimDto lavozimDto) {

        return new ApiResponse("" ,true);
    }
}
