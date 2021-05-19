package project.start5;

import java.util.ArrayList;
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
public class SubMap{
	
	private String strA;
	private String strB;
	private List<String> strs; 

	public SubMap(){
		this.strA = "";
	}
}