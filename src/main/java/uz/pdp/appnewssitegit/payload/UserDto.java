package uz.pdp.appnewssitegit.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class UserDto {
    @NotNull(message = "fullName bosh bolmasin")
    private String fullName;

    @NotNull(message ="username bowbolmasin")
    private String username;

    @NotNull(message ="password bow bolmasin")
    private String password;

    @NotNull(message ="lavozim bow bolmasin")
    private Long lavozimId;

}
