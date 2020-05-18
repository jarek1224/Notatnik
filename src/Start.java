import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Start extends JFrame implements ActionListener {

//	private static final long serialVersionUID = 1L;
	
	JMenuBar menuBar;
	JMenu mPlik, mPomoc;
	JMenuItem iNowy, iOtworz, iZapisz, iZapiszJako, iExit,iAutor;
	JTextArea notatnik;
	
	File plik;
	

	Start() {
		super("Notatnik");
		menuBar = new JMenuBar();
		
			mPlik = new JMenu("Plik");
			mPomoc = new JMenu("Pomoc");
			
				iNowy = new JMenuItem("Nowy"); iNowy.addActionListener(this);
				iOtworz = new JMenuItem("Otworz"); iOtworz.addActionListener(this);
				iZapisz = new JMenuItem("Zapisz"); iZapisz.addActionListener(this);
				iZapiszJako = new JMenuItem("Zapisz jako"); iZapiszJako.addActionListener(this);
				iExit = new JMenuItem("Wyjœcie"); iExit.addActionListener(this);
				
				iAutor = new JMenuItem("Autor aplikacji"); iAutor.addActionListener(this);
				
			
			menuBar.add(mPlik); menuBar.add(mPomoc);
			
			mPlik.add(iNowy);mPlik.add(iOtworz);mPlik.add(iZapisz);mPlik.add(iZapiszJako);
			mPlik.addSeparator();mPlik.add(iExit);
			mPomoc.add(iAutor);
			
		
		notatnik = new JTextArea();
		JScrollPane scrol = new JScrollPane(notatnik); 
		notatnik.setFont(new Font("System", Font.PLAIN, 15));
		add(scrol);
		
		
		setJMenuBar(menuBar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	public static void main(String[] args) { new Start();}

	private void otworz() {
		notatnik.setText("");
		JFileChooser fc = new JFileChooser();
		if(fc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
			plik = fc.getSelectedFile();
			try {
				Scanner scanner = new Scanner(plik);
				while (scanner.hasNext()) notatnik.append(scanner.nextLine()+"\n");
				scanner.close();
				this.setTitle(fc.getName(plik));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void zapisz() {
		try {
			PrintWriter pw = new PrintWriter(plik);
			Scanner scanner = new Scanner(notatnik.getText());
			while (scanner.hasNext()) pw.print(scanner.nextLine());
			scanner.close();pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void zapiszJako() {
		JFileChooser fc = new JFileChooser();
		fc.setSelectedFile(plik);
		if(fc.showSaveDialog(this)==JFileChooser.APPROVE_OPTION){
			plik = fc.getSelectedFile();
			zapisz();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object cel = e.getSource();
		
		if(cel==iExit) System.exit(0); else
		if(cel==iAutor) JOptionPane.showMessageDialog(this, "Jaros³aw Bubicz", "Autor", JOptionPane.INFORMATION_MESSAGE); else
		if(cel==iOtworz) otworz();else
		if(cel==iNowy) {notatnik.setText("");this.setTitle("Nowy");plik=null;}else
		if(cel==iZapiszJako) zapiszJako(); else
		if(cel==iZapisz) {if(plik==null) zapiszJako(); else zapisz();}
		
			
		
		
	}

}
