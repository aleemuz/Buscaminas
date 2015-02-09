package buscaminas2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Alejandra
 */
public class Buscaminas2 extends JPanel implements ActionListener{
    
    private int casillasFaltantes = 0, minas1 = 10;
    int x =5, y=5;
	int valores[][] = new int[x][y];
	public JLabel labelP;	
	private JButton botones[][] = new JButton[minas1][minas1];
	public JButton botonP;
        public String[] archi = {"/buscaminas2/gano.png", "/buscaminas2/perdio.png", "/buscaminas2/nueva.png"};
	private String archivos[] = {"/buscaminas2/0.PNG", "/buscaminas2/1.PNG", "/buscaminas2/2.PNG", "/buscaminas2/3.PNG", "/buscaminas2/4.PNG", "/buscaminas2/5.PNG", "/buscaminas2/6.PNG", "/buscaminas2/7.PNG", "/buscaminas2/8.PNG", "/buscaminas2/9.PNG"};
	
	private ImageIcon[] imagenes = new ImageIcon[10];    
	public ImageIcon[] ima = new ImageIcon[3];   
    
        private boolean visible[][] = new boolean[x][y];
        
        public Buscaminas2 (){
            
            this.setLayout(null);
            nuevaPartida();        
		this.setSize(200, 240);
		botonP = new JButton();
		botonP.setBounds(86, 5, 30, 30);
      //  botonP.setIcon(ima[2]);                
        this.add(botonP);
        this.botonP.addActionListener(this);
		labelP = new JLabel();
		labelP.setBounds(15, 15, 40, 15);
		this.add(labelP);
            
        }
        
        public void nuevaPartida(){
            
        ponerBotones();	
        casillasFaltantes = 0;
        ver(false);
        ponerMinas();
        contorno();
        visualizarMinas();
        eventos();
        }
        
        public void ponerBotones(){
        for(int i=0;i<10;i++){
            imagenes[i] = new ImageIcon(getClass().getResource(archivos[i]));
        }        
        for(int f = 0; f<x; f++){
        for(int c = 0; c<y; c++){
            botones[f][c] = new JButton();
            botones[f][c].setBounds(20*f, 20*c+40,20, 20);
            botones[f][c].setBackground(Color.gray);
            this.add(botones[f][c]);
        }
        }        
    }
        
        public void ponerMinas(){
        for(int f=0;f<x;f++){
        for(int c=0;c<y;c++){
            valores[f][c]=0;
        }
        }
           int f1, c1;
        for ( int i=0;i<minas1;i++){
            do{
                f1=(int)(Math.random()*minas1);
                c1=(int)(Math.random()*minas1);
            }while(valores[f1][c1]!=0);
            valores[f1][c1]=9;
        }
    }
        
        public void contorno(){
        for(int f=0;f<x;f++){
            for(int c=0;c<y;c++){
                if(valores[f][c]==9){
                    for(int f2=f-1;f2<=f+1;f2++){
                        for(int c2=c-1;c2<=c+1;c2++){
                            if(f2>=0 && f2<x && c2>=0 && c2<y && valores[f2][c2]!=9)
                                valores[f2][c2]++;
                        }
                      }
                   }
                }
            }
        }
        
        public void ver(boolean valor){
        for(int f=0;f<minas1;f++){
        for(int c=0;c<minas1;c++){
            visible[f][c]=valor;
        }
        }
    }
        
        public void pulsarBotonVas(int f, int c){
        if(f>=0 && f<minas1 && c>=0 && c<minas1 && visible[f][c]==false){
            if(visible[f][c]==false){
            	visible[f][c]=true;
                if(valores[f][c]==9){
                    ver(true);
                    JOptionPane.showMessageDialog(null, "              PERDISTE");
                    botonP.setIcon(ima[1]);}
            else if(visible[f][c]==true){
                casillasFaltantes++;
                if (casillasFaltantes == 90){
                    ver(true);
                    JOptionPane.showMessageDialog(null, "              GANASTE");
                    botonP.setIcon(ima[0]);
                    labelP.setText("");
                }
                labelP.setText(casillasFaltantes+"/90");
            }
            }
            if(valores[f][c]==0){
                pulsarBotonVas(f, c-1);
                pulsarBotonVas(f, c+1);
                pulsarBotonVas(f-1, c);
                pulsarBotonVas(f+1, c);
            }
        }
    }
	
	public void pulsarBoton(int f, int c) {                       
        pulsarBotonVas(f,c);
        visualizarMinas();
    }
        
        public void eventos(){
        for(int f=0;f<x;f++){
        for(int c=0;c<y;c++){
            this.botones[f][c].addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    for(int f=0;f<x;f++){
                    for(int c=0;c<y;c++){
                        if(e.getSource()==botones[f][c])
                            pulsarBoton(f,c);
                    }
                    }
                }
                }
            );
        }
        }
    }
	
	public void visualizarMinas(){
        for(int f=0;f<x;f++){
        for(int c=0;c<y;c++){
         if(visible[f][c]==false){
           botones[f][c].setText("");
         }else if(visible[f][c]==true){
           if(valores[f][c]==0){
           botones[f][c].setIcon(imagenes[0]);
           }else if(valores[f][c]==1){
           botones[f][c].setIcon(imagenes[1]);
           }else if(valores[f][c]==2){
           botones[f][c].setIcon(imagenes[2]);
           }else if(valores[f][c]==3){
           botones[f][c].setIcon(imagenes[3]);
           }else if(valores[f][c]==4){
           botones[f][c].setIcon(imagenes[4]);
           }else if(valores[f][c]==5){
           botones[f][c].setIcon(imagenes[5]);
           }else if(valores[f][c]==6){
           botones[f][c].setIcon(imagenes[6]);
           }else if(valores[f][c]==7){
           botones[f][c].setIcon(imagenes[7]);
           }else if(valores[f][c]==8){
           botones[f][c].setIcon(imagenes[8]);
           }else if(valores[f][c]==9)
           botones[f][c].setIcon(imagenes[9]);
          }

        }
       }
   }
	
	public void quitarBotones(){
        for(int f1 = 0; f1<minas1; f1++){
               for(int c1 = 0; c1<minas1; c1++){
                   this.remove(botones[f1][c1]);
               }
   }
 }

	@Override
	public void actionPerformed(ActionEvent eP) {
		if(eP.getSource()==botonP){
			botonP.setIcon(ima[2]);
            quitarBotones();
            this.setVisible(false);            
            labelP.setText("");
			nuevaPartida();
			this.setVisible(true);
		}		
	}	

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Buscaminas2 juego = new Buscaminas2();
    }
}
