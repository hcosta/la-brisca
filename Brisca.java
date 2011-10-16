package labrisca;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * Brisca.java
 *
 * Created on 13-oct-2011, 19:52:36
 */
/**
 * LaBrisca 2011
 * @author Hector COsta Guzman
 */
public final class Brisca extends javax.swing.JFrame {

    /** Creates new form Brisca */
    public Brisca() {

        cargarImagenes();
        nuevoJuego();
        initComponents();
        setIconImage(icono);

        //con esto centramos la ventana al medio
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LaBrisca");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 937, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        // TODO add your handling code here:
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_R:
                jb = new JBrisca();
                nuevoJuego();
                nJugada = 1;
                repaint();
                break;

            case KeyEvent.VK_SPACE:

                URL soundURL = getClass().getClassLoader().getResource("Musica/fanfare.mid");
                if (jb.getCartasJ1().isEmpty() && jb.getCartasJ2().isEmpty()) {
                    jb.contarYLimpiar();
                    String msg = jb.resultado();
                    try {
                        if (jb.resultadoInt() == 0) {
                            if (this.reproducido == false) {
                                
                                MidiSound.startMidi(soundURL);
                                this.reproducido = true;
                            }

                        }
                    } catch (Exception e2) {
                    }
                    JOptionPane.showMessageDialog(this, msg + "\nCartas en tu poder: " + jb.getGanadasJ1().size() + "\nCartas en poder de la CPU: " + jb.getGanadasJ2().size());

                } else if (jb.getJugada().size() == 2) {

                    jb.contarYLimpiar();

                    nJugada++;

                } else if (jb.getJugadorGanador() == 0 && jb.getJugada().size() < 2 || jb.getJugadorGanador() == 1 && jb.getJugada().size() == 1) {

                    if (jb.getCartasJ1().size() > 0 && jb.getJugada().size() != 2) {

                        jb.jugarCarta(nFoco);

                        if (jb.getCartasJ2().size() > 0 && jb.getJugada().size() < 2) {
                            if (jb.getGanadasJ2().size() > 0 && jb.getPalo() != null) {
                                for (int i = 0; i < jb.getManoJ2().size(); i++) {
                                    if (jb.getManoJ2().get(i).getNum() == 7 && jb.getManoJ2().get(i).getPalo().equals(jb.comprobarPaloGanador()) && jb.getPalo().getNum() > 7) {
                                        jb.cambiarCartaJ2(i, 7);
                                    }
                                    if (jb.getManoJ2().get(i).getNum() == 2 && jb.getManoJ2().get(i).getPalo().equals(jb.comprobarPaloGanador()) && jb.getPalo().getNum() > 2 && jb.getPalo().getNum() <= 7) {
                                        jb.cambiarCartaJ2(i, 2);
                                    }
                                }
                            }
                            jb.jugarCartaComputadora();
                        }
                    }
                } else if (jb.getJugadorGanador() == 1 && jb.getJugada().size() < 2 || jb.getJugadorGanador() == 0 && jb.getJugada().size() == 1) {
                    if (jb.getCartasJ2().size() > 0 && jb.getJugada().size() < 2) {
                        if (jb.getGanadasJ2().size() > 0 && jb.getPalo() != null) {
                            for (int i = 0; i < jb.getManoJ2().size(); i++) {
                                if (jb.getManoJ2().get(i).getNum() == 7 && jb.getManoJ2().get(i).getPalo().equals(jb.comprobarPaloGanador()) && jb.getPalo().getNum() > 7) {
                                    jb.cambiarCartaJ2(i, 7);
                                }
                                if (jb.getManoJ2().get(i).getNum() == 2 && jb.getManoJ2().get(i).getPalo().equals(jb.comprobarPaloGanador()) && jb.getPalo().getNum() > 2 && jb.getPalo().getNum() <= 7) {
                                    jb.cambiarCartaJ2(i, 2);
                                }
                            }
                        }

                        jb.jugarCartaComputadora();
                    }
                }

                if (jb.getManoJ1().size() == 3) {
                    nFoco = 0;
                }
                if (jb.getManoJ1().size() == 2) {
                    nFoco = 0;
                }
                if (jb.getManoJ1().size() == 1) {
                    nFoco = 0;
                }

                repaint();
                break;

            case KeyEvent.VK_F5:
                JOptionPane.showMessageDialog(this, "\n\n"
                        + "- Pulsa la flecha izquierda y la flecha derecha para elegir la carta a jugar.\n"
                        + "- Pulsa la tecla Espacio para jugar la carta o continuar en el turno de la la CPU.\n"
                        + "- Puedes cambiar el 7 o el 2 con la tecla C si almenos has ganado una jugada.\n"
                        + "- Para reiniciar el juego sólo necesitas apretar la tecla R.\n"
                        + "- Para salir en cualquier momento del juego pulsa la tecla ESC.\n\n"
                        + "            LaBrisca - Programado por Héctor Costa Guzmán\n                                            hcosta.info - 2011",
                        "Controles de LaBrisca", 1);
                break;

            case KeyEvent.VK_RIGHT:
                nFoco++;
                if (nFoco > 2) {
                    nFoco = 0;
                }

                if (manoJ1.size() == 2) {
                    if (nFoco > 1) {
                        nFoco = 0;
                    }
                }
                if (manoJ1.size() == 1) {
                    nFoco = 0;
                }

                repaint();
                break;

            case KeyEvent.VK_LEFT:
                nFoco--;
                if (nFoco < 0) {
                    nFoco = 2;
                }

                if (manoJ1.size() == 2) {
                    if (nFoco < 0) {
                        nFoco = 1;
                    }
                }
                if (manoJ1.size() == 1) {
                    nFoco = 0;
                }

                repaint();
                break;

            case KeyEvent.VK_C:
                if (jb.getGanadasJ1().size() > 0 && jb.getPalo() != null) {
                    for (int i = 0; i < jb.getManoJ1().size(); i++) {
                        if (jb.getManoJ1().get(i).getNum() == 7 && jb.getManoJ1().get(i).getPalo().equals(jb.comprobarPaloGanador()) && jb.getPalo().getNum() > 7) {
                            jb.cambiarCartaJ1(i, 7);
                        }
                        if (jb.getManoJ1().get(i).getNum() == 2 && jb.getManoJ1().get(i).getPalo().equals(jb.comprobarPaloGanador()) && jb.getPalo().getNum() > 2 && jb.getPalo().getNum() <= 7) {
                            jb.cambiarCartaJ1(i, 2);
                        }
                    }
                }
                repaint();
                break;

            case KeyEvent.VK_ESCAPE:
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(false);
                dispose();
                break;

        }
    }//GEN-LAST:event_formKeyPressed

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Brisca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Brisca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Brisca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Brisca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Brisca().setVisible(true);
            }
        });
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {

        // draw baseline, ascent, and descent
        gdb.drawImage(tablero, 0, 0, this);

        /* JUGADOR 1 */

        manoJ1 = jb.getManoJ1();

        int posicionCartasX = 350;

        for (int i = 0; i < manoJ1.size(); i++) {

            cartaJ1 = manoJ1.get(i).getImagen();
            gdb.drawImage(cartaJ1, posicionCartasX, 390, cartaJ1.getWidth(this) * 90 / 100, cartaJ1.getHeight(this) * 90 / 100, this);
            if (i == nFoco) {
                gdb.drawImage(foco, posicionCartasX, 390, foco.getWidth(this) * 90 / 100, foco.getHeight(this) * 90 / 100, this);
            }

            posicionCartasX += 150;
        }

        /* JUGADOR 2 */

        posicionCartasX = 250;

        manoJ2 = jb.getCartasJ2();

        for (int i = 0; i < manoJ2.size(); i++) {

            cartaJ2 = manoJ2.get(i).getImagen();

            gdb.drawImage(reversa, posicionCartasX, 40, reversa.getWidth(this) * 40 / 100, reversa.getHeight(this) * 40 / 100, this);
            //gdb.drawImage(cartaJ2, posicionCartasX, 40, cartaJ2.getWidth(this) * 40 / 100, cartaJ2.getHeight(this) * 40 / 100, this);

            posicionCartasX += 80;
        }

        /* JUGADA */

        posicionCartasX = 350;
        int posicionCartasY = 180;

        jugada = jb.getJugada();

        for (int i = 0; i < jugada.size(); i++) {

            cartaJ = jugada.get(i).getImagen();

            gdb.drawImage(cartaJ, posicionCartasX, posicionCartasY, cartaJ.getWidth(this) * 65 / 100, cartaJ.getHeight(this) * 65 / 100, this);

            posicionCartasX += 90;
            posicionCartasY -= 10;
        }

        /* PINTAMOS MARCADORES */

        gdb.setColor(Color.black);
        gdb.setFont(new Font("Tahoma", Font.BOLD, 28));
        if (jb.getJugada().isEmpty()) {
            if (jb.getJugadorGanador() == 0) {
                gdb.drawString("Turno de: J1", 730, 100);
            } else {
                gdb.drawString("Turno de: CPU", 730, 100);
            }

        }
        gdb.drawString("Jugada: " + nJugada, 730, 60);
        gdb.setFont(new Font("Arial", Font.BOLD, 20));
        if (jugada.size() == 1) {
            gdb.drawString("" + jugada.get(0).getNum() + " de " + jugada.get(0).getPalo(), 625, 250);
        }
        if (jugada.size() == 2) {
            gdb.drawString("" + jugada.get(0).getNum() + " de " + jugada.get(0).getPalo(), 625, 250);
            gdb.drawString("vs", 670, 280);
            gdb.drawString("" + jugada.get(1).getNum() + " de " + jugada.get(1).getPalo(), 625, 310);
        }



        /* PALO Y BARAJA */

        gdb.setColor(Color.black);
        gdb.setFont(new Font("Arial", Font.BOLD, 18));
        gdb.drawString("El palo de Triunfo es ", 27, 50);
        gdb.setFont(new Font("Tahoma", Font.BOLD, 28));
        gdb.drawString(jb.comprobarPaloGanador(), 27, 86);

        if (jb.getBaraja().size() > 0) {

            paloImg = jb.getPalo().getImagen();
            gdb.drawImage(paloImg, 50, 200, paloImg.getWidth(this) * 70 / 100, paloImg.getHeight(this) * 70 / 100, this);
            gdb.drawImage(reversaG, 10, 310, reversaG.getWidth(this) * 70 / 100, reversaG.getHeight(this) * 70 / 100, this);
        }

        g.drawImage(db, 0, 0, this);
    }

    public void cargarImagenes() {

        // get its metrics
        icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + "icono.gif"));
        tablero = Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + "tablero.gif"));
        reversaG = Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + "reversa.png"));
        reversa = Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + "0.png"));
        foco = Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + "foco.gif"));

    }
    private Image icono, tablero, paloImg, reversaG, reversa, cartaJ1, cartaJ2, cartaJ, foco;
    //  private String sRuta = ".\\resources\\";
    private String sRuta = "/Imagenes/";
    private BufferedImage db = new BufferedImage(948, 590, BufferedImage.TYPE_4BYTE_ABGR);
    private Graphics gdb = db.getGraphics();
    private JBrisca jb = new JBrisca();
    private int nFoco = 0; //1..3
    private ArrayList<Carta> manoJ1 = new <Carta>ArrayList();
    private ArrayList<Carta> manoJ2 = new <Carta>ArrayList();
    private ArrayList<Carta> jugada = new <Carta>ArrayList();
    private Carta palo = new Carta();
    private int nJugada = 1;
    private MidiSound mS = new MidiSound();
    ClassLoader classLoader = getClass().getClassLoader();
    private boolean reproducido;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void nuevoJuego() {
        jb.nuevaPartida();
        jb.mezclarBaraja();
        jb.repartirCartas();


        palo = jb.getPalo();
        try {
            if (MidiSound.midiPlayer.isRunning()) {
                MidiSound.midiPlayer.stop();
                MidiSound.midiPlayer.close();
            }
        } catch (Exception e) {
        }

        this.reproducido = false;
    }

    public void jpane(String cad) {
        JOptionPane.showMessageDialog(this, cad);
    }
}