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
@RequestMapping({ "/", "/index" }) 
public class WebMap {
 
    private final Constructor constructor; 
    private Fragment fragment;
    private Inputs details; 
    private SubMap subMap;

    @Autowired
    public WebMap(Constructor constructor) {
        this.constructor = constructor;
        this.details = new Inputs();
        this.subMap = new SubMap();
    } 

    @ModelAttribute(name = "details")
    public Inputs inputs() {
        return new Inputs();
    }  

    @ModelAttribute(name = "tree")
    public Fragment build() {
        return constructor.buildTree();
    }

    @GetMapping({"/","/index"})
    public String main(Model m) {  
        m.addAttribute("data", new Inputs2());
        return "index";
    }

    @GetMapping("/page1")
    public String page1Map(Model m) {  
        return "page1";
    }

    @GetMapping("/page2")
    public String page2GMap(Model m) {
        m.addAttribute("tree", constructor.buildTree()); 
        return "page2";
    }

    @GetMapping("/form")
    public String formMap() {
        return "form";
    }

    @GetMapping("/results") 
    public String resultsMap() {
        return "results";
    }  

    @PostMapping("/page2")
    public String page2Map(Model m, @Valid Inputs details, Errors errors) { 
        String str = " "; 
        if (errors.hasErrors() || Integer.parseInt(details.getAge()) < 10) {
            str = "*min age 10";
            m.addAttribute("str", str); 
            return "page1";
        } 
        System.out.println("\n\n  --==--==---\n\n="+details.getAge());
        this.details = details; 
        return "page2";
    }  


    @PostMapping("/results")
    public String process(Fragment root, Model m) throws IOException, URISyntaxException { 
         
        if (emptyForm(root)) {
            String str = "  *select more than one from the checkboxes";
            m.addAttribute("str", str);
            System.out.println("\n\nemptyForm\n\n");
            //return "redirect:/page2"; 
            return "page2";
        }

        m.addAttribute("tree", root);
        m.addAttribute("details", details);
            System.out.println(root.getChildren().size()+"\n\n  --==--==---\n\n="+details.getAge());

        MLP mlp = new MLP();
        if (screen(root)) {
                        System.out.println("@ (screen(root)) {");
            m.addAttribute("val", "0.0");
            m.addAttribute("strVal", "\tNegative"); 
            //m.addAttribute("percentage", res[2]); 
        }
        else { 
            String[] res = mlp.engine(root, details.getAge(), details.getGen()); 
                    System.out.println("@ mlp.engine { " + res[0] + " --" + res[1] + " -- "+res[2]);
            m.addAttribute("val", res[0]);
            m.addAttribute("strVal", res[1]); 
        }
        return "results";
    } 

    private Boolean emptyForm(Fragment root) { 
        for(Fragment f : root.getChildren()) {
            if(f.getValue() == null) continue;
            if (f.getValue() == true) 
                return false;  
        }
        return true; 
    }

    private Boolean screen(Fragment root) {
        List<Fragment> li = root.getChildren(); 
        
        if (li.get(3).getValue() == true) {
            for (int i = 0; i < li.size(); i++) {
                if (i == 3) continue;
                if (li.get(i).getValue() == true)
                    return false; 
            }   
            return true; 
        } else {
            return false; 
        } 
    }
} 