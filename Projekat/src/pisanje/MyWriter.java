package pisanje;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MyWriter {
	
	String fileName;
	
	public MyWriter(String fileName) {
		this.fileName = fileName;
	}
	
	public <T extends ISerializable> void write(ArrayList<T> somethings) {
		
		try {
			BufferedWriter bw = new BufferedWriter(
									new OutputStreamWriter(
											new FileOutputStream(this.fileName), "utf-8"));
			
			for(T s : somethings) {
				bw.write(s.toLine() + "\n");
			}
			
			bw.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public <T extends ISerializable> void write(T something) {
		
		try {
			BufferedWriter bw = new BufferedWriter(
									new OutputStreamWriter(
											new FileOutputStream(this.fileName, true), "utf-8"));
			
			bw.write(something.toLine() + "\n");
			
			bw.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
