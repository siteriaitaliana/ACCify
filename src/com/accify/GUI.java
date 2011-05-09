package com.accify;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

import com.accify.ProvaDB;

public class GUI {
	
	/*public GUI()
	{
		
	}*/
	
	public void AddLabel(ActionEvent e)
	{
		String projectname = "";
		try 
		{
			projectname = ProvaDB.readProjectName();
		} 
		catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		TextField field = null;
    	for(int i=0;i<pbott.getComponentCount();i++)
    	{
    		if(pbott.getComponent(i) instanceof TextField)
    		{
    			field = (TextField)pbott.getComponent(i);
    		}
    	}
    	try
    	{
        ProvaDB.addLabel(field.getText(), projectname);
    	} catch (Exception exc){System.err.println(exc);}
	}
	
	public void run(String [] args)
	{
		Frame f = new Frame("Accify");
		ptop = new Panel();
		pbott = new Panel();
		try 
		{
			ptop.add(new Label("Project name: "+ProvaDB.readProjectName()));
		} 
		catch (HeadlessException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<String> projectlabel = new ArrayList<String>();
		try 
		{
			projectlabel = ProvaDB.readProjectLabels();
		} 
		catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ptop.add(new Label("Labels: "+projectlabel));
		
		final TextField tf = new TextField(30);
		final Button addlabel = new Button("Add Label");
		pbott.add(tf);
		pbott.add(addlabel);
		f.add(ptop);
		f.add(pbott);
		f.setSize(600, 600);
		f.setVisible(true);
		f.setLayout(new FlowLayout());
		
		addlabel.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	AddLabel(e);
            }});
		
		f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
	}
	
	protected Panel pbott;
	protected Panel ptop;

	public static void main(String[] args) throws Exception 
	{
		GUI gui = new GUI();
		gui.run(args);
	}
}
