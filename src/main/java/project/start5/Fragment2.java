package project.start5;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@AllArgsConstructor
@Setter
@Getter 
@Builder
public class Fragment2 {

    private String set1;
    private String set2;
    private String set3;
    private String set4;
    private String set5;

    private List<Fragment> children;

    public Fragment2(){

    } 
}