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
	protected Panel ptop3;
	protected Panel ptop4;
	protected Panel pfooter;
	protected TextArea tconsole;
	protected TextArea tseconda;
	protected TextField tf;
	protected Button addlabel;
	protected Label addlabel_proj;
	protected Label addlabel_attr;
	protected Label addlabel_comp;
	protected Label addlabel_capab;
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
	
	public void run (String [] args)
	{
		f = new Frame("Accify");
		ptop1 = new Panel();
		pbott1 = new Panel();
		pfooter = new Panel();
		lista = new List();
		tconsole = new TextArea(1,70);
		addlabel_proj = new Label("Project Details");
	    addlabel_attr = new Label("Attributes");
	    addlabel_comp = new Label("Components");
	    addlabel_capab = new Label("Capabilities");
	    ptop2 = new Panel();
	    ptop3 = new Panel();
	    ptop4 = new Panel();
	    ptop1.add(addlabel_proj);
		ptop2.add(addlabel_attr);
		ptop3.add(addlabel_comp);
		ptop4.add(addlabel_capab);
		
	    
	    tf = new TextField(30);
		addlabel = new Button("Add Label");
		tconsole.append("Console ready.");
		pbott1.add(tf);
		pbott1.add(addlabel);
		pfooter.add(tconsole);
		
		Menu menu = new Menu("File");
		MenuItem m1 = new MenuItem("Project Details");
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
	    m1.addActionListener(new MenuListener());
	    m2.addActionListener(new MenuListener());
	    m3.addActionListener(new MenuListener());
	    m4.addActionListener(new MenuListener());
	  	    		
		try {ptop1.add(new Label("Project name: "+ProvaDB.readProjectName()));} 
		catch (HeadlessException e1) {e1.printStackTrace();} 
		catch (Exception e1) {e1.printStackTrace();}
		
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
		
		f.setSize(800, 600);
		f.setVisible(true);
		f.setLayout(new FlowLayout());
	}

	protected void refreshLista(){
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
	
	protected void AddLabel(ActionEvent e)
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
	
	class MenuListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			String item = e.getActionCommand(); 
		    if (item.equals("Attributes"))  {
			f.removeAll();
			f.add(ptop2);
			} else if (item.equals("Components")) {
				f.removeAll();
				f.add(ptop3);
			} else if (item.equals("Capabilities")) {
				f.removeAll();
				f.add(ptop4);
			} else if (item.equals("Project Info")) {
				f.removeAll();
				f.add(ptop1);
				f.add(pbott1);
			}
		    
		    f.add(pfooter);
			f.setSize(800, 600);
			f.setVisible(true);
			f.setLayout(new FlowLayout());
			tconsole.append("\nAttributes clicked");

		}

	}

	
}
