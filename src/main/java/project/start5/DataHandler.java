package project.start5;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;  

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import weka.core.Debug;
import weka.core.converters.ConverterUtils.DataSource; 

public class DataHandler {

	public List<String> getSymptoms() throws IOException, URISyntaxException {
		ArrayList l = new ArrayList();
		l.add("Polyuria");
		l.add("Polydipsia");
		l.add("Sudden Weight Loss");
		l.add("Fatigue");
		l.add("Polyphagia");
		l.add("Genital Thrush");
		l.add("Visual Blurring");
		l.add("Itching");
		l.add("Irritability");
		l.add("Delayed Healing");
		l.add("Partial Paresis");
		l.add("Muscle Stiffness");
		l.add("Alopecia");
		l.add("Obesity");
		return l; 
	}

	public File getFile(String fileName) throws URISyntaxException {
		ClassLoader cl = getClass().getClassLoader();
		URL resource = cl.getResource(fileName);
		if (resource == null) {
			System.out.println("File not found");
			return null; 
		} else {
			return new File(resource.toURI());
		}
	}
  
}