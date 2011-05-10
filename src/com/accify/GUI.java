package com.accify;

import com.accify.ProvaDB;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class GUI {
	
	protected Frame f;
	protected Panel ptop1;
	protected Panel pbott1;
	protected Panel ptop2;
	protected Panel pbott2;
	protected Panel pfooter;
	protected TextArea tconsole;
	protected TextArea tseconda;
	protected List lista;
	protected MenuBar mb;
	protected MenuItem m1;
	protected MenuItem m2;
	protected MenuItem m3;
	protected MenuItem m4;
	
	public static void main(String[] args) throws Exception 
	{
		GUI gui = new GUI();
		gui.run(args);
	}
	
	public void run(String [] args)
	{
		f = new Frame("Accify");
		ptop1 = new Panel();
		pbott1 = new Panel();
		pfooter = new Panel();
		lista = new List();
		tconsole = new TextArea(1,70);
		Menu menu = new Menu("File");
		MenuItem m1 = new MenuItem("Project Info");
		MenuItem m2 = new MenuItem("Attributes");
		MenuItem m3 = new MenuItem("Components");
		MenuItem m4 = new MenuItem("Capabilities");
		menu.add(m1);
		menu.add(m2);
		menu.add(m3);
		menu.add(m4);
	    MenuBar mb = new MenuBar();
	    mb.add(menu);
	    f.setMenuBar(mb);
		
		m2.addActionListener(
				new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	f.remove(ptop1);
    			f.remove(pbott1);

    			tconsole.append("\nAttributes clicked");
            }});

		try {ptop1.add(new Label("Project name: "+ProvaDB.readProjectName()));} 
		catch (HeadlessException e1) {e1.printStackTrace();} 
		catch (Exception e1) {e1.printStackTrace();}
		
		final TextField tf = new TextField(30);
		final Button addlabel = new Button("Add Label");
		tconsole.append("Console ready.");
		pbott1.add(tf);
		pbott1.add(addlabel);
		pfooter.add(tconsole);

		addlabel.addActionListener(
				new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	AddLabel(e);
            }});
		refreshLista();
		
		f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
		f.add(ptop1);
		f.add(pbott1);
		f.add(pfooter);
		f.setSize(600, 600);
		f.setVisible(true);
		f.setLayout(new FlowLayout());
	}

	public void refreshLista(){
		ptop1.remove(lista);
		ArrayList<String> projectlabel = new ArrayList<String>();
		try 
		{
			projectlabel = ProvaDB.readProjectLabels();
		} 
		catch (Exception e1) {e1.printStackTrace();}
		lista.removeAll();
		for(String str:projectlabel)
		{	
			lista.add(str);
		}
		ptop1.add(lista);
	}
	
	public void AddLabel(ActionEvent e)
	{
		String projectname = "";
		TextField field = null;
		
		try {projectname = ProvaDB.readProjectName();} 
		catch (Exception e1) {e1.printStackTrace();}
		
    	for(int i=0;i<pbott1.getComponentCount();i++)
    	{	
    		if(pbott1.getComponent(i) instanceof TextField)
    		{
    			field = (TextField)pbott1.getComponent(i);
    		}
    	}
    	try  {ProvaDB.addLabel(field.getText(), projectname); } catch (Exception exc){System.err.println(exc);}
    	tconsole.append("\nLabel Added.");
		refreshLista();
	}

	/*public void ProjInt(ActionEvent e) {
		if(e.getSource()== m1){
			f.remove(ptop1);
			f.remove(pbott1);
			tconsole.append("m1 clicked");
		}
	}*/

	
}
