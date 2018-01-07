package hmo.clonalg.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hmo.clonalg.Antibody;

public class ResultFileWriter {
	private String problemInstance;
	private final String rootDir = "results/";
	
	public ResultFileWriter(String problemInstanceFile) {
		Pattern p = Pattern.compile("[a-zA-Z0-9]+(?=\\.txt)");
		Matcher m = p.matcher(problemInstanceFile);
		if (!m.find()) {
			System.err.println("Problem instance has to be discribed in .txt file.");
			System.exit(1);
		}
		
		this.problemInstance = m.group(0);
	}
	
	public void write(Antibody result, String time) throws IOException {
		String path = rootDir + "res-" + time + "-" + this.problemInstance + ".txt";
		File file = new File(path);
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}
		
		FileWriter writer = new FileWriter(file);
		writer.write(result.toString());
		writer.close();
	}

}
