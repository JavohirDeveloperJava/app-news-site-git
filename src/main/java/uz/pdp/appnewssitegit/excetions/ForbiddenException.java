package uz.pdp.appnewssitegit.excetions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.FORBIDDEN)
@Data
public class ForbiddenException extends RuntimeException{
    private String type;
    private String massage;

    public ForbiddenException(String type, String massage) {
        this.type = type;
        this.massage = massage;
    }
}
