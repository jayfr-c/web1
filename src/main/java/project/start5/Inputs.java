package project.start5;

import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter 
@Builder
public class Inputs{ 
  
    @Digits(integer=2, fraction=0, message="min age 10")
    private String age; 

    private String gen; 

    public Inputs(){

    } 
}