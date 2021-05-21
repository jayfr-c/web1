package project.start5;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
@Controller 
public class WebMap2 { 

    private Inputs2 data; 

    @Autowired
    public WebMap2() { 
        this.data = new Inputs2();
    }  

    @ModelAttribute(name = "data")
    public Inputs2 t2dmData() {
        return this.data;
    }    

    @GetMapping("/t2dm") 
    public String t2dmMap(Model m) {
        //m.addAttribute("data", new Inputs2());
        return "t2dm";
    }
 

    @PostMapping("/results2")
    public String results2Map(Model m, @Valid Inputs2 data, Errors errors) throws IOException, URISyntaxException { 
        
        if (errors.hasErrors()) { 
            return "t2dm";
        }    
            //System.out.println(this.data.getAge()+"--------\n-----\n------\n "+data.getAge());
        MLP2 mlp = new MLP2(); 
        String[] res = mlp.engine(data);
            m.addAttribute("data", data);
        m.addAttribute("val", res[0]);
        m.addAttribute("strVal", res[1]); 
        m.addAttribute("percentage", res[2]);
        //m.addAttribute("tree", constructor.buildTree());

        return "results2";
    }  
} 