package com.accify;
import com.accify.ProvaDB;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class GUI {
	protected Frame f;
	protected Panel ptop1;
	protected Panel ptop2;
	protected Panel ptop3;
	protected Panel ptop4;
	protected Panel pbott1;
	protected Panel pfooter;
	protected TextArea tconsole;
	protected TextArea tseconda;
	protected TextField tf;
	protected Button addlabel;
	protected Label addlabel_proj;
	protected Label addlabel_attr;
	protected Label addlabel_comp;
	protected Label addlabel_capab;
	protected ArrayList<Label> lista;
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
	
	public void init()
	{
		buildMainGUI();
		buildMenu();
	}
	
	private void buildMainGUI() 
	{
		f = new Frame("Accify");
		ptop1 = new Panel();
		ptop2 = new Panel();
	    ptop3 = new Panel();
	    ptop4 = new Panel();
		pbott1 = new Panel();
		pfooter = new Panel();
		lista = new ArrayList<Label>();
		tconsole = new TextArea(3,30);
		addlabel_proj = new Label("Project Details");
	    addlabel_attr = new Label("Attributes");
	    addlabel_comp = new Label("Components");
	    addlabel_capab = new Label("Capabilities");
	    pbott1.add(addlabel_proj);
		ptop2.add(addlabel_attr);
		ptop3.add(addlabel_comp);
		ptop4.add(addlabel_capab);
		tf = new TextField(30);
		addlabel = new Button("Add Label");
		pbott1.add(tf);
		pbott1.add(addlabel);
		f.add(ptop1);
		f.add(pbott1);
		pfooter.add(tconsole);
		f.add(pfooter);
	}
	
	private void buildMenu() 
	{
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
	}
	
	public void run (String [] args)
	{
		init();
		
		try {pbott1.add(new Label("Project name: "+ProvaDB.readProjectName()));} catch (HeadlessException e1) {e1.printStackTrace();} catch (Exception e1) {e1.printStackTrace();}
		
		//loadLista();
		
		addlabel.addActionListener(
				new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	AddLabel(e);
            }});
		
		f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
		loadLista();
		
	}

	protected void loadLista()
	{
		ArrayList<String> projectlabel = new ArrayList<String>();
		try 
		{
			projectlabel = ProvaDB.readProjectLabels();
		} 
		catch (Exception e1) {e1.printStackTrace();}
		
		for(String str:projectlabel)
		{	
			Label label_app = new Label(str);
			if(!lista.contains(label_app))
			{
				lista.add(label_app);
			}
		}
		
		for(Label label_add:lista)
		{
			ptop1.add(label_add);
		}
		
		f.setSize(1000,1000);
		f.setVisible(true);
		f.setLayout(new FlowLayout());
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
	  
	     Label label_app = new Label(field.getText());
	     ptop1.add(label_app);	  
	     f.setVisible(true);
	 }
	
	protected class MenuListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e){
			String item = e.getActionCommand(); 
		    if (item.equals("Attributes"))  {
		    	buildm2GUI();
			} 	else if (item.equals("Components")) {
					buildm3GUI();				
					} 	else if (item.equals("Capabilities")) {
							buildm4GUI();				
						} 	else if (item.equals("Project Details")) {
								buildm1GUI();
							}

			f.setSize(1000, 1000);
			f.setVisible(true);
			f.setLayout(new FlowLayout());
		}
		
		private void buildm3GUI() 
		{
			f.removeAll();
			f.add(ptop3);
			f.add(pfooter);
		    tconsole.append("\nComponents clicked");
		}

		private void buildm2GUI() 
		{
			f.removeAll();
			f.add(ptop2);
		    f.add(pfooter);
		    tconsole.append("\nAttributes clicked");
		}

		private void buildm4GUI() 
		{
			f.removeAll();
			f.add(ptop4);
			f.add(pfooter);
		    tconsole.append("\nCapabilities clicked");			
		}

		private void buildm1GUI()
		{
			f.removeAll();
			f.add(ptop1);
			f.add(pbott1);
			f.add(pfooter);
		    tconsole.append("\nProject Details clicked");
		}
	}

	
}
