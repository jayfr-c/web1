package project.start5;

import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter 
@Builder
public class Fragment {

    private Boolean value;
    private String label;  
    private String website;

    private List<Fragment> children;

    public Fragment(){
    	this.value = false;
    } 
}