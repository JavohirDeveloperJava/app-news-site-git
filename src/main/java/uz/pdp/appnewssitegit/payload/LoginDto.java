package uz.pdp.appnewssitegit.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotNull(message ="username bowbolmasin")
    private String username;

    @NotNull(message ="password bow bolmasin")
    private String password;
}
