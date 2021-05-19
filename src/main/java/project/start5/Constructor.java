package project.start5; 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Service
public class Constructor {

    public Fragment buildTree() {
        Fragment root = new Fragment();

        List<Fragment> component = new ArrayList<>();

        component.add(Fragment.builder()
                                .label("Polyuria")
                                .value(false)
                                .website("https://www.diabetes.co.uk/symptoms/polyuria.html")
                                .build());
        component.add(Fragment.builder()
                                .label("Polydipsia")
                                .value(false)
                                .website("https://www.diabetes.co.uk/symptoms/polydipsia.html")
                                .build());
        component.add(Fragment.builder()
                                .label("Sudden Weight Loss")
                                .value(false)
                                .website("https://www.diabetes.co.uk/symptoms/unexplained-weight-loss.html")
                                .build());
        component.add(Fragment.builder()
                                .label("Fatigue")
                                .value(false)
                                .website("https://www.diabetes.co.uk/symptoms/extreme-tiredness.html")
                                .build());
        component.add(Fragment.builder()
                                .label("Polyphagia")
                                .value(false)
                                .website("https://www.diabetes.co.uk/symptoms/polyphagia.html")
                                .build());
        component.add(Fragment.builder()
                                .label("Genital Thrush")
                                .value(false)
                                .website("https://www.diabetes.co.uk/diabetes-complications/diabetes-and-yeast-infections.html")
                                .build());
        component.add(Fragment.builder()
                                .label("Visual Blurring")
                                .value(false)
                                .website("https://www.diabetes.co.uk/symptoms/blurred-vision.html")
                                .build());
        component.add(Fragment.builder()
                                .label("Itching")
                                .value(false)
                                .website("https://www.healthline.com/health/diabetes/diabetic-dermopathy")
                                .build());
        component.add(Fragment.builder()
                                .label("Irritability")
                                .value(false)
                                .website("ttps://www.medicalnewstoday.com/articles/317458#anxiety-depression-and-diabetes-distress")
                                .build());
        component.add(Fragment.builder()
                                .label("Delayed Healing")
                                .value(false)
                                .website("https://www.diabetes.co.uk/symptoms/slow-healing-of-wounds.html")
                                .build());
        component.add(Fragment.builder()
                                .label("Partial Paresis")
                                .value(false)
                                .website("https://www.healthline.com/health/paresis")
                                .build());
        component.add(Fragment.builder()
                                .label("Muscle Stiffness")
                                .value(false)
                                .website("https://www.healthline.com/health/diabetes/leg-pain-cramps-treatment#medication")
                                .build());
        component.add(Fragment.builder()
                                .label("Alopecia")
                                .value(false)
                                .website("https://www.webmd.com/skin-problems-and-treatments/guide/alopecia-areata")
                                .build());
        component.add(Fragment.builder()
                                .label("Obesity")
                                .value(false)
                                .website("https://www.diabetes.co.uk/diabetes-and-obesity.html")
                                .build());
 
 

        root.setChildren(component);

        return root;
    }
    public Fragment2 buildTree2() { 
        Fragment2 root = new Fragment2();
        return root;
    }

}