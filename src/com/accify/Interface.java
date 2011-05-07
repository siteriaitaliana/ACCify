package com.accify;
import java.awt.*;
import java.util.ArrayList;

import com.accify.ProvaDB;

public class Interface {

	public static void main(String[] args) throws Exception {
		
		Frame f = new Frame("Accify");
		Panel p = new Panel();
		
		String projectname = ProvaDB.readProjectName();
		p.add(new Label("Project name: "+projectname));
		
		ArrayList<String> projectlabel = ProvaDB.readProjectLabels();
		
		p.add(new Label("Labels: "+projectlabel));

		f.add(p);
		f.setSize(600, 600);
		
		f.setVisible(true);

	}

}
