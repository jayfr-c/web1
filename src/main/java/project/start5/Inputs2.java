package project.start5;

import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor; 
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter  
public class Inputs2{ 
  
    @Digits(integer=2, fraction=0, message="*")
    private String pregnancies; 

    @Digits(integer=3, fraction=2, message="*")
    private String glucose;

    @Digits(integer=3, fraction=2, message="*")
    private String bloodPressure; 

    @Digits(integer=2, fraction=2, message="*")
    private String skinThickness; 

    @Digits(integer=3, fraction=2, message="*")
    private String insulin; 
    
    @Digits(integer=2, fraction=2, message="*")
    private String bmi; 
    
    @Digits(integer=2, fraction=3, message="*")
    private String diabetesPedigreeFunction; 
    
    @Digits(integer=3, fraction=0, message="*")
    private String age;  

    public Inputs2(){

    } 
}