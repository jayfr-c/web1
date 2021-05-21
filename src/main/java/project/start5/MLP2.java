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

public class MLP2 {  

	private Classifier cls;
	private Instances instance;
	DataHandler dh = new DataHandler();

	public void initialize() throws IOException, URISyntaxException  {
		ArrayList attributes = new ArrayList();
 
		Attribute pregnancies = new Attribute("pregnancies");
		Attribute glucose = new Attribute("glucose");
		Attribute bloodPressure = new Attribute("bloodPressure");
		Attribute skinThickness = new Attribute("skinThickness");
		Attribute insulin = new Attribute("insulin");
		Attribute bmi = new Attribute("bmi");
		Attribute diabetesPedigreeFunction = new Attribute("diabetesPedigreeFunction");
		Attribute age = new Attribute("age");
		Attribute cls = new Attribute("class"); 
		
		attributes.add(pregnancies);
		attributes.add(glucose);
		attributes.add(bloodPressure);
		attributes.add(skinThickness);
		attributes.add(insulin);
		attributes.add(bmi);
		attributes.add(diabetesPedigreeFunction);
		attributes.add(age);  
		attributes.add(cls);  

	 	instance = new Instances("testInstances", attributes, 1);
	 	instance.setClassIndex(instance.numAttributes() - 1); 
	 			//System.out.println(attributes.size());
	} 

	private HashMap<String, Double> mapInputs(Inputs2 data) {
        HashMap<String, Double> inputs = new HashMap<>();   
        
        //inputs.put("Age", Double.parseDouble(age.getText()));  
        inputs.put("pregnancies", Double.parseDouble(data.getPregnancies()));
        inputs.put("glucose", Double.parseDouble(data.getGlucose()));
        inputs.put("bloodPressure", Double.parseDouble(data.getBloodPressure()));
        inputs.put("skinThickness", Double.parseDouble(data.getSkinThickness()));
        inputs.put("insulin", Double.parseDouble(data.getInsulin()));
        inputs.put("bmi", Double.parseDouble(data.getBmi()));
        inputs.put("diabetesPedigreeFunction", Double.parseDouble(data.getDiabetesPedigreeFunction()));
        inputs.put("age", Double.parseDouble(data.getAge()));
       

        inputs.put("class", 0.0);  
        return inputs;
    }

	private double[] convertHashMap(HashMap<String, Double> inputs) throws IOException, URISyntaxException {
		double[] values = new double[9];
		List<String> vars = dh.getVariables();
		for (int i = 0; i < 8; i++) {
			values[i] = inputs.get(vars.get(i));
		}  
		values[8] = 0.0;
		return values; 
	}

	public void createInstance(HashMap<String, Double> inputs) throws IOException, URISyntaxException  {  
	 	int count = 0;
	 	instance.clear();
	 	double[] instanceValues = convertHashMap(inputs);
	 	Instance inst = new DenseInstance(1.0, instanceValues);
	 	instance.add(inst);  
	}

	public String[] engine(Inputs2 data) throws IOException, URISyntaxException  {
 		HashMap<String, Double> inputs =  mapInputs(data);
 		double prediction = 0.0;
		double[] values = new double[3];

		Resource resource = new ClassPathResource("static/MLP-T2DM.model");
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

		MLP mlp = new MLP();
		values = mlp.classifyWithPercentage(instance, cls);
		prediction = values[2];
		 
 		String[] res = new String[3];
 		res[0] = prediction + " "; //(prediction > 0.5) ? "1.0" : "0.0";
 		res[1] = (prediction > 0.0) ? "\tPositive" : "\tNegative";
 		res[2] = "\t"+ String.format("%.2f", (values[1] * 100))+" %";
        return res;
	}

}
