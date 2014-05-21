import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	private int taille=10;
	private JButton[] tabGris=new JButton[taille];
	private JButton[] tabCouleurs=new JButton[taille];
	private JButton actu, toutAlea, partAlea, quitter, clear;
	private JPanel panel1, panel2;
	private JTextField[] tabR=new JTextField[taille];
	private JTextField[] tabV=new JTextField[taille];
	private JTextField[] tabB=new JTextField[taille];
	private List<Color>[] niveauGrisChoisi;
	private JLabel r, v, b, gris, couleur;
	private GridBagConstraints c1=new GridBagConstraints();
	
	public Fenetre(){
		super("Choose_Color");
		initComposants();
		placeComposants();
		setActionListeners();
		setLocation(200, 200);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	@SuppressWarnings("unchecked")
	private void initComposants(){
		Font f=new Font("Serif", Font.PLAIN, 20);

		setLayout(new BorderLayout());
		c1.fill=GridBagConstraints.HORIZONTAL;
		
		for(int i=0; i<taille; i++){
			tabGris[i]=new JButton();
			tabGris[i].setEnabled(false);
			tabGris[i].setBackground(new Color(255, 255, 255));
			tabCouleurs[i]=new JButton();
			tabR[i]=new JTextField();
			tabV[i]=new JTextField();
			tabB[i]=new JTextField();
			panel1=new JPanel();
			panel1.setLayout(new GridBagLayout());
			panel1.setBackground(new Color(255, 220, 145));
			panel2=new JPanel();
			panel2.setLayout(new FlowLayout());
			panel2.setBackground(new Color(255, 220, 145));
			getContentPane().setBackground(new Color(255, 220, 145));
		}
		actu=new JButton("Actualiser couleurs");
		toutAlea=new JButton("Couleurs aleatoires");
		quitter=new JButton("Quitter");
		partAlea=new JButton("Couleurs aleatoires restantes");
		clear=new JButton("Tout effacer");
		niveauGrisChoisi=new LinkedList[10];
		initListeGris();
		r=new JLabel("R");
		r.setForeground(new Color(255, 0, 0));
		r.setFont(f);
		v=new JLabel("V");
		v.setForeground(new Color(0, 255, 0));
		v.setFont(f);
		b=new JLabel("B");
		b.setForeground(new Color(0, 0, 255));
		b.setFont(f);
		couleur=new JLabel("Couleur");
		couleur.setFont(f);
		gris=new JLabel("Niveau de gris");
		gris.setFont(f);
	}
	private void initListeGris() {
		for(int i=0; i<niveauGrisChoisi.length; i++)
			niveauGrisChoisi[i]=new LinkedList<Color>();
		initCasesNiveauGrisChoisi();
	}
	private void clearValues() {
		for(int i=0; i<taille; i++){
			tabR[i].setText("");
			tabV[i].setText("");
			tabB[i].setText("");
			tabGris[i].setBackground(Color.white);
			tabCouleurs[i].setBackground(Color.white);
		}				
	}
	private void couleursAlea() {
		Random r=new Random();
		int gris;
		Color c;
		
		for(int i=0; i<taille; i++){
			c=niveauGrisChoisi[i].get(r.nextInt(niveauGrisChoisi[i].size()));
			gris=(int)(0.3*c.getRed()+0.59*c.getGreen()+0.11*c.getBlue());
			tabCouleurs[i].setBackground(c);
			tabGris[i].setBackground(new Color(gris, gris, gris));
			tabR[i].setText(tabCouleurs[i].getBackground().getRed()+"");
			tabV[i].setText(tabCouleurs[i].getBackground().getGreen()+"");
			tabB[i].setText(tabCouleurs[i].getBackground().getBlue()+"");
		}				
	}
	private void couleursAleaPartielles() {
		Random r=new Random();
		int gris;
		Color c;
		
		for(int i=0; i<taille; i++){
			if(tabR[i].getText().equals("") && tabV[i].getText().equals("") && tabB[i].getText().equals("")){
				c=niveauGrisChoisi[i].get(r.nextInt(niveauGrisChoisi[i].size()));
				gris=(int)(0.3*c.getRed()+0.59*c.getGreen()+0.11*c.getBlue());
				tabCouleurs[i].setBackground(c);
				tabGris[i].setBackground(new Color(gris, gris, gris));
				tabR[i].setText(tabCouleurs[i].getBackground().getRed()+"");
				tabV[i].setText(tabCouleurs[i].getBackground().getGreen()+"");
				tabB[i].setText(tabCouleurs[i].getBackground().getBlue()+"");
			}
		}
	}
	private void setActionListeners(){
		// bouton clear
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearValues();
			}
		});
		// bouton couleurs aleatoires
		toutAlea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				couleursAlea();
			}
		});
		// bouton couleurs aleatoires partielles
		partAlea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				couleursAleaPartielles();
			}			
		});
		// bouton quitter
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		// bouton actualiser
		actu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int gris;
				
				for(int i=0; i<taille; i++){
					int a=0, b=0, c=0;
					String ri=tabR[i].getText();
					String vi=tabV[i].getText();
					String bi=tabB[i].getText();
					
					if(!ri.equals("")){
						try{
							a=Integer.parseInt(ri);
						} catch (Exception e){
							a=255;
							b=255;
							c=255;
						}
					}
					if(!vi.equals("")){
						try{
							b=Integer.parseInt(vi);
						} catch (Exception e){
							a=255;
							b=255;
							c=255;
						}
					}
					if(!bi.equals("")){
						try{
							c=Integer.parseInt(bi);
						} catch (Exception e){
							a=255;
							b=255;
							c=255;
						}
					}
					if(ri.equals("") && vi.equals("") && bi.equals("")){
						a=255;
						b=255;
						c=255;
					}
					tabCouleurs[i].setBackground(new Color(a, b, c));
					gris=(int)(0.3*a+0.59*b+0.11*c);
					tabGris[i].setBackground(new Color(gris, gris, gris));
				}
			}
		});
		// couleurs
		for(int i=0; i<taille; i++){
			tabCouleurs[i].addActionListener(new ActionListener() {
				int i;
				
				public void actionPerformed(ActionEvent arg0) {
					boolean couleurVide=false;
					Color c=JColorChooser.showDialog(null, "Palette de couleur", null);
					if(c==null){
						couleurVide=true;
						c=new Color(255, 255, 255);
					}
					int gris=(int)(0.3*c.getRed()+0.59*c.getGreen()+0.11*c.getBlue());
	
					tabCouleurs[i].setBackground(c);
					tabGris[i].setBackground(new Color(gris, gris, gris));
					if(!couleurVide){
						tabR[i].setText(c.getRed()+"");
						tabV[i].setText(c.getGreen()+"");
						tabB[i].setText(c.getBlue()+"");
					}
				}
				public ActionListener init(int i){
					this.i = i;
					return this;
				}
			}.init(i));
		}
	}
	private void placeComposants(){
		c1.ipadx=40;
		c1.ipady=20;
		c1.insets=new Insets(6, 6, 6, 6);
		
		// niveau de gris
		c1.gridx=0;
		c1.gridy=0;
		panel1.add(gris, c1);
		// couleur
		c1.gridx=0;
		c1.gridy=1;
		panel1.add(couleur, c1);
		// "R"	
		c1.gridx=0;
		c1.gridy=2;
		panel1.add(r, c1);
		// "V"
		c1.gridx=0;
		c1.gridy=3;
		panel1.add(v, c1);
		// "B"
		c1.gridx=0;
		c1.gridy=4;
		panel1.add(b, c1);
		// boutons gris
		c1.gridy=0;
		c1.gridx=1;
		for(int i=0; i<taille; i++){
			panel1.add(tabGris[i], c1);
			c1.gridx++;
		}
		// boutons couleurs
		c1.gridx=1;
		c1.gridy=1;
		for(int i=0; i<taille; i++){
			panel1.add(tabCouleurs[i], c1);
			c1.gridx++;
		}
		// boutons RVB
		c1.gridx=1;
		c1.gridy=2;
		for(int i=0; i<taille; i++){
			panel1.add(tabR[i], c1);
			c1.gridx++;
		}
		c1.gridx=1;
		c1.gridy=3;
		for(int i=0; i<taille; i++){
			panel1.add(tabV[i], c1);
			c1.gridx++;
		}
		c1.gridx=1;
		c1.gridy=4;
		for(int i=0; i<taille; i++){
			panel1.add(tabB[i], c1);
			c1.gridx++;
		}
		// bouton quitter
		panel2.add(clear);
		// bouton actualiser
		panel2.add(actu);
		// bouton alea partiel
		panel2.add(partAlea);
		// bouton aléatoire
		panel2.add(toutAlea);
		// bouton quitter
		panel2.add(quitter);
		
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.SOUTH);
		add(panel2, BorderLayout.EAST);
	}
	private void initCasesNiveauGrisChoisi() {
		int gris;
		for(int i=0; i<256; i++){
			for(int j=0; j<256; j++){
				for(int k=0; k<256; k++){
					gris=(int)(0.3*i+0.59*j+0.11*k);
					Color c=new Color(i, j, k);
					if(gris==5 || gris==6)
						niveauGrisChoisi[9].add(c);
					else if(gris==40 || gris==41)
						niveauGrisChoisi[8].add(c);
					else if(gris==65 || gris==66)
						niveauGrisChoisi[7].add(c);
					else if(gris==90 || gris==91)
						niveauGrisChoisi[6].add(c);
					else if(gris==112 || gris==113)
						niveauGrisChoisi[5].add(c);
					else if(gris==137 || gris==138)
						niveauGrisChoisi[4].add(c);
					else if(gris==162 || gris==163)
						niveauGrisChoisi[3].add(c);
					else if(gris==190 || gris==191)
						niveauGrisChoisi[2].add(c);
					else if(gris==220 || gris==221)
						niveauGrisChoisi[1].add(c);
					else if(gris==237 || gris==238 || gris==239)
						niveauGrisChoisi[0].add(c);
				}
			}
		}
	}
	public static void main(String[] args) {
		new Fenetre();
	}
}