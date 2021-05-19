package project.start5;

import java.io.IOException; 
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List; 
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import weka.classifiers.Classifier;
import weka.core.Attribute;  
import weka.core.DenseInstance;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.SMO; 
import weka.classifiers.functions.SimpleLogistic;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class MLP {  

	private Classifier cls;
	private Instances data;
	private HashMap<String, Double> inputs; 
	DataHandler dh = new DataHandler(); 
	
	public double[] getResults(Classifier c, Instances instances) {
		return getResults(c, instances.firstInstance());
	}
	
	public double[] getResults(Classifier c, Instance instance) { 
		double[] results = new double[3];
		try {
			double[] distribution = c.distributionForInstance(instance);
			results[0] = distribution[0];
			results[1] = distribution[1];
			results[2] = c.classifyInstance(instance);
		} catch (Exception e) { 
			new Exception ("Unable to get results");
			results[2] = -1; 
		}
		return results;
	}

	public double[] classifyWithPercentage(Instances instance, Classifier cls) {
		try { 
			return getResults(cls, instance);
		} catch (Exception e) {
			new Exception ("Errors classifying with " + cls.toString()); 
		}
		return null;
	}

	private HashMap<String, Double> mapInputs(Fragment root, String age, String gen) {
        HashMap<String, Double> inputs = new HashMap<>();   
        
        //inputs.put("Age", Double.parseDouble(age.getText()));  
        inputs.put("Age",  Double.parseDouble(age));
       if (gen == "Female") {
            inputs.put("Gender", 0.0);
            System.out.println("Female: "+0.0);
        } else {
            inputs.put("Gender", 1.0);
            System.out.println("Male: "+1.0);
        }  
        List<Fragment> children = root.getChildren();
        for (Fragment f : children) {
            if(f.getValue() != null) 
            	inputs.put(f.getLabel(), 1.0);
            else 
            	inputs.put(f.getLabel(), 0.0);
        }

        inputs.put("class", 0.0);  
        return inputs;
    }
    
    private double[] convertHashMap(HashMap<String, Double> inputs) throws IOException, URISyntaxException {
		double[] values = new double[17];
		List<String> symptoms = dh.getSymptoms();
		values[0] = inputs.get("Age");
		values[1] = inputs.get("Gender");
		for (int i = 2, j = 0; i < 16; i++, j++) {
			values[i] = inputs.get(symptoms.get(j));
			//System.out.println(symptoms.get(j) +" : "+ inputs.get(symptoms.get(j)));
		} 
		/*for(Map.Entry<String, Double> entry : inputs.entrySet()) 
 			System.out.println( entry.getKey() + " => " + ": " + entry.getValue());
 		System.out.println("\n--==-=--===--=-=\n");*/
		values[16] = 0.0;
		return values; 
	}

    public void createInstance(HashMap<String, Double> inputs) throws IOException, URISyntaxException  {  
	 	int count = 0;
	 	data.clear();
	 	double[] instanceValues = convertHashMap(inputs);
	 	Instance inst = new DenseInstance(1.0, instanceValues);
	 	data.add(inst);  
	}

    public void initialize() throws IOException, URISyntaxException  {
		ArrayList attributes = new ArrayList();

		List genderLabels = new ArrayList();
		genderLabels.add("Female");
		genderLabels.add("Male");
		List valueLabels = new ArrayList();
		valueLabels.add("No");
		valueLabels.add("Yes");
		List classLabels = new ArrayList();
		classLabels.add("Negative");
		classLabels.add("Positive"); 

		Attribute age = new Attribute("Age"); 
		attributes.add(age); 
		attributes.add(new Attribute("Gender", genderLabels)); 

		/*Iterating through symptoms prior to attribute addition */
		List<String> symptomsList = dh.getSymptoms();
		for (int i = 0; i < symptomsList.size(); i++) {       
			attributes.add(new Attribute(symptomsList.get(i), valueLabels));
			//System.out.println("@adding attributes/expect symptoms/expect 14 values/"+symptomsList.get(i)+" is added");	         
		} 
		attributes.add(new Attribute("class", classLabels));   

	 	data = new Instances("testInstances", attributes, 1);
	 	data.setClassIndex(data.numAttributes() - 1);
	 			//System.out.println(attributes.size());
	} 
	public String[] engine(Fragment root, String age, String gen) throws IOException, URISyntaxException  {
 		HashMap<String, Double> inputs =  mapInputs(root, age, gen);
 		double prediction = 0.0;
		double[] values = new double[3];
		Resource resource = new ClassPathResource("static/MLP.model");
        InputStream inputStream = resource.getInputStream(); 
        System.out.println("\n***********\ninputStream: "+inputStream);
		try {
			cls = (Classifier) SerializationHelper.read(inputStream); 
		} catch (Exception e) { 
			new Exception("\n--\n---File not found\n--\n--\n---"); 
		}

		initialize();
		convertHashMap(inputs);
		createInstance(inputs); 
		values = classifyWithPercentage(data, cls);
		prediction = values[2];
		
		//System.out.println("\n--\n===\n"+prediction+"\n===\n--\n-- "+ String.format("%.2f", (values[1] * 100)));
 		/*for(Map.Entry<String, Double> entry : inputs.entrySet()) {
 			System.out.println( entry.getKey() + " => " + ": " + entry.getValue());
 		}*/
 		String[] res = new String[3];
 		res[0] = "\t"+ prediction;
 		res[1] = (prediction > 0.0) ? "\tPositive" : "\tNegative";
 		res[2] = "\t"+ String.format("%.2f", (values[1] * 100))+" %";
        return res;
	}
}