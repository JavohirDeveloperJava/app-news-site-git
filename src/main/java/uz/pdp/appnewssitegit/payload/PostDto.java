package uz.pdp.appnewssitegit.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String title;


    private String text;


    private String url;
}
