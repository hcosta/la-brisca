import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * LaBrisca 2011
 * @author Hector COsta Guzman
 */
public class JBrisca {

    /* Constante de importancia = 2*/
    private static int CONST = 2;
    private ArrayList<Carta> baraja = new <Carta>ArrayList();
    private ArrayList<Carta> manoJ1 = new <Carta>ArrayList();
    private ArrayList<Carta> manoJ2 = new <Carta>ArrayList();
    private ArrayList<Carta> jugada = new <Carta>ArrayList();
    private int turno = 0;
    private boolean finDeTurno = false;
    public String paloG;
    private int carta = 0;

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public ArrayList<Carta> getGanadasJ1() {
        return ganadasJ1;
    }

    public void setGanadasJ1(ArrayList<Carta> ganadasJ1) {
        this.ganadasJ1 = ganadasJ1;
    }

    public ArrayList<Carta> getGanadasJ2() {
        return ganadasJ2;
    }

    public void setGanadasJ2(ArrayList<Carta> ganadasJ2) {
        this.ganadasJ2 = ganadasJ2;
    }

    public ArrayList<Carta> getJugada() {
        return jugada;
    }

    public void setJugada(ArrayList<Carta> jugada) {
        this.jugada = jugada;
    }

    public int getJugadorGanador() {
        return jugadorGanador;
    }

    public void setJugadorGanador(int jugadorGanador) {
        this.jugadorGanador = jugadorGanador;
    }

    public String getsRuta() {
        return sRuta;
    }

    public void setsRuta(String sRuta) {
        this.sRuta = sRuta;
    }
    private ArrayList<Carta> ganadasJ1 = new <Carta>ArrayList();
    private ArrayList<Carta> ganadasJ2 = new <Carta>ArrayList();
    private int jugadorGanador = 0;
    private Carta palo = new Carta();

    public ArrayList<Carta> getBaraja() {
        return baraja;
    }

    public void setBaraja(ArrayList<Carta> baraja) {
        this.baraja = baraja;
    }

    public ArrayList<Carta> getManoJ1() {
        return manoJ1;
    }

    public void setManoJ1(ArrayList<Carta> manoJ1) {
        this.manoJ1 = manoJ1;
    }

    public ArrayList<Carta> getManoJ2() {
        return manoJ2;
    }

    public void setManoJ2(ArrayList<Carta> manoJ2) {
        this.manoJ2 = manoJ2;
    }

    public Carta getPalo() {
        return palo;
    }

    public void setPalo(Carta palo) {
        this.palo = palo;
    }

    public JBrisca() {
    }

    void jugarCarta(int nFoco) {

        jugada.add(manoJ1.get(nFoco));
        manoJ1.remove(nFoco);

    }

    void jugarCartaComputadora() {

        /* Modo pensante */

        int valoracion = 0;
        this.carta = 0;
        Brisca b = new Brisca();
        /* Es hora de valorar las cartas 
         * Empezaremos por hacer una observacion de cada una de las cartas de nuestra mano
         */

        for (int i = 0; i < manoJ2.size(); i++) {
            if (valoracion < EvaluarJugada(i)) {
                valoracion = EvaluarJugada(i);

                //b.jpane("valoracion: "+valoracion+" i: "+i);
                this.carta = i;
            }
        }



        jugada.add(manoJ2.get(this.carta));
        manoJ2.remove(this.carta);
        b = null;
        /* Modo facil*/
        /*jugada.add(manoJ2.get(1));
        manoJ2.remove(1);*/
    }

    /* AQUI EMPIEZA LO BUENO */
    public int EvaluarJugada(int i) {

        int suma = 1;
        int valorEnemigo, valorJ2;
        String paloEnemigo, paloJ2;

        /*Si le toca tirar segundo */

        if (jugadorGanador == 0) {

            paloJ2 = manoJ2.get(i).getPalo();
            valorJ2 = manoJ2.get(i).getPuntos();
            paloEnemigo = jugada.get(0).getPalo();
            valorEnemigo = jugada.get(0).getPuntos();

            /* Si enemigo no es triunfo y tu carta es del pano enemigo y mayor 10 puntos */
            if (!paloEnemigo.equals(comprobarPaloGanador()) && paloJ2.equals(paloEnemigo) && valorJ2 > valorEnemigo) {
                suma += valorJ2 + 10;
            }
            /*Si el palo enemigo es palo ganador priorizamos las cartas bajas con 21 - valorCarta + 10*/
            if (paloEnemigo.equals(comprobarPaloGanador())) {
                suma += 21 - valorJ2 + 10;
            }
            /*Si el palo enemigo no es triunfo y su carta es 3 o 1 y tenemos carta triunfo la tiramos priorizando las bajas*/
            if (!paloEnemigo.equals(comprobarPaloGanador()) && valorEnemigo > 15 && paloJ2.equals(comprobarPaloGanador())) {
                suma += 21 - valorJ2 + 10;
            }
            /*si nuestra carta es menor que la suya y no es palo triunfo la tiramos*/
            if (suma == 1) {
                if (!paloJ2.equals(comprobarPaloGanador())) {
                    suma += 21 - valorJ2 + CONST;
                } //si es palo triunfo restamos 5
                else {
                    suma += 21 - valorJ2 - CONST;
                }
            }
        }

        /*Si le toca tirar primero */

        if (jugadorGanador == 1) {

            valorJ2 = manoJ2.get(i).getPuntos();
            paloJ2 = manoJ2.get(i).getPalo();

            if (paloJ2.equals(comprobarPaloGanador())) {
                suma -= CONST;
            }

            if (!paloJ2.equals(comprobarPaloGanador())) {
                suma += CONST;
            }
            suma += 21 - valorJ2;
            return suma;

        }

        return suma;
    }

    public ArrayList<Carta> getCartasJ1() {
        return manoJ1;
    }

    public ArrayList<Carta> getCartasJ2() {
        return manoJ2;
    }

    public void nuevaPartida() {

        int counter = 1;

        for (int i = 1; i < 49; i++) {

            if (counter > 12) {
                counter = 1;
            }

            if (i > 0 && i <= 12) {

                if (counter == 1) {
                    baraja.add(new Carta("Bastos", counter, 10 + 11, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 3) {
                    baraja.add(new Carta("Bastos", counter, 10 + 10, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 10) {
                    baraja.add(new Carta("Bastos", counter, 10 + 2, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 11) {
                    baraja.add(new Carta("Bastos", counter, 10 + 3, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 12) {
                    baraja.add(new Carta("Bastos", counter, 10 + 4, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else {
                    baraja.add(new Carta("Bastos", counter, counter, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                }

            }
            if (i > 12 && i <= 24) {

                if (counter == 1) {
                    baraja.add(new Carta("Copas", counter, 10 + 11, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 3) {
                    baraja.add(new Carta("Copas", counter, 10 + 10, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 10) {
                    baraja.add(new Carta("Copas", counter, 10 + 2, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 11) {
                    baraja.add(new Carta("Copas", counter, 10 + 3, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 12) {
                    baraja.add(new Carta("Copas", counter, 10 + 4, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else {
                    baraja.add(new Carta("Copas", counter, counter, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                }
            }
            if (i > 24 && i <= 36) {

                if (counter == 1) {
                    baraja.add(new Carta("Espadas", counter, 10 + 11, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 3) {
                    baraja.add(new Carta("Espadas", counter, 10 + 10, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 10) {
                    baraja.add(new Carta("Espadas", counter, 10 + 2, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 11) {
                    baraja.add(new Carta("Espadas", counter, 10 + 3, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 12) {
                    baraja.add(new Carta("Espadas", counter, 10 + 4, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else {
                    baraja.add(new Carta("Espadas", counter, counter, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                }

            }
            if (i > 36) {

                if (counter == 1) {
                    baraja.add(new Carta("Oros", counter, 10 + 11, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 3) {
                    baraja.add(new Carta("Oros", counter, 10 + 10, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 10) {
                    baraja.add(new Carta("Oros", counter, 10 + 2, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 11) {
                    baraja.add(new Carta("Oros", counter, 10 + 3, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else if (counter == 12) {
                    baraja.add(new Carta("Oros", counter, 10 + 4, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                } else {
                    baraja.add(new Carta("Oros", counter, counter, Toolkit.getDefaultToolkit().getImage(getClass().getResource(sRuta + i + ".gif"))));
                }

            }

            counter++;

        }
    }

    public void contarYLimpiar() {

        /* la primera vez el turno era de jugador 1 - turno = 0*/

        if (getCartasJ1().size() >= 0 && getCartasJ2().size() >= 0 && baraja.size() >= 0 && jugada.size() == 2) {
            if (jugadorGanador == 0) {

                if (jugada.get(0).getPalo().equals(comprobarPaloGanador()) && !jugada.get(1).getPalo().equals(comprobarPaloGanador())
                        || jugada.get(0).getPalo().equals(jugada.get(1).getPalo()) && jugada.get(0).getPuntos() > jugada.get(1).getPuntos()
                        || !jugada.get(0).getPalo().equals(comprobarPaloGanador()) && !jugada.get(1).getPalo().equals(comprobarPaloGanador()) && jugada.get(0).getPalo().equals(jugada.get(1).getPalo()) && jugada.get(0).getPuntos() > jugada.get(1).getPuntos()
                        || !jugada.get(0).getPalo().equals(comprobarPaloGanador()) && !jugada.get(1).getPalo().equals(comprobarPaloGanador()) && !jugada.get(0).getPalo().equals(jugada.get(1).getPalo())) {

                    ganadasJ1.add(jugada.get(0));
                    ganadasJ1.add(jugada.get(1));

                    jugada.remove(0);
                    jugada.remove(0);

                    jugadorGanador = 0;


                } else {
                    ganadasJ2.add(jugada.get(0));
                    ganadasJ2.add(jugada.get(1));

                    jugada.remove(0);
                    jugada.remove(0);

                    jugadorGanador = 1;
                }

            } else if (jugadorGanador == 1) {

                if (jugada.get(0).getPalo().equals(comprobarPaloGanador()) && !jugada.get(1).getPalo().equals(comprobarPaloGanador())
                        || jugada.get(0).getPalo().equals(jugada.get(1).getPalo()) && jugada.get(0).getPuntos() > jugada.get(1).getPuntos()
                        || !jugada.get(0).getPalo().equals(comprobarPaloGanador()) && !jugada.get(1).getPalo().equals(comprobarPaloGanador()) && jugada.get(0).getPalo().equals(jugada.get(1).getPalo()) && jugada.get(0).getPuntos() > jugada.get(1).getPuntos()
                        || !jugada.get(0).getPalo().equals(comprobarPaloGanador()) && !jugada.get(1).getPalo().equals(comprobarPaloGanador()) && !jugada.get(0).getPalo().equals(jugada.get(1).getPalo())) {

                    ganadasJ2.add(jugada.get(0));
                    ganadasJ2.add(jugada.get(1));

                    jugada.remove(0);
                    jugada.remove(0);

                    jugadorGanador = 1;


                } else {
                    ganadasJ1.add(jugada.get(0));
                    ganadasJ1.add(jugada.get(1));

                    jugada.remove(0);
                    jugada.remove(0);

                    jugadorGanador = 0;
                }

            }
        }

        /* Repartimos una carta a cada uno*/

        if (turno == 0) {
            if (baraja.size() > 0) {
                manoJ1.add(baraja.get(0));
                baraja.remove(0);
                if (baraja.isEmpty()) {
                    manoJ2.add(palo);
                    palo = null;
                } else {
                    manoJ2.add(baraja.get(0));
                    baraja.remove(0);
                }
            }

        } else {

            if (baraja.size() > 0) {
                manoJ2.add(baraja.get(0));
                baraja.remove(0);

                if (baraja.isEmpty()) {
                    manoJ1.add(palo);
                    palo = null;
                } else {
                    manoJ1.add(baraja.get(0));
                    baraja.remove(0);
                }
            }
        }

    }

    public void mezclarBaraja() {
        // mezclamos la baraja
        Collections.shuffle(baraja);
        Collections.shuffle(baraja);
        Collections.shuffle(baraja);
    }

    public void repartirCartas() {

        /// Primero el palo = 1 carta

        palo = baraja.get(0);
        baraja.remove(0);
        this.paloG = palo.getPalo();

        /// Luego el Jugador 1 = 3 cartas

        manoJ1.add(baraja.get(0));
        baraja.remove(0);
        manoJ1.add(baraja.get(0));
        baraja.remove(0);
        manoJ1.add(baraja.get(0));
        baraja.remove(0);

        /// Luego el Jugador 2 = 3 cartas  

        manoJ2.add(baraja.get(0));
        baraja.remove(0);
        manoJ2.add(baraja.get(0));
        baraja.remove(0);
        manoJ2.add(baraja.get(0));
        baraja.remove(0);

    }
    //private String sRuta = ".\\resources\\";
    private String sRuta = "/Imagenes/";

    public String comprobarPaloGanador() {
        return this.paloG;
    }

    public String resultado() {
        int suma1 = 0, suma2 = 0;

        Iterator<Carta> itr = ganadasJ1.iterator();
        while (itr.hasNext()) {
            Carta c = (Carta) itr.next();
            if (c.getNum() == 1) {
                suma1 += 11;
            } else if (c.getNum() == 3) {
                suma1 += 10;
            } else if (c.getNum() == 10) {
                suma1 += 2;
            } else if (c.getNum() == 11) {
                suma1 += 3;
            } else if (c.getNum() == 12) {
                suma1 += 4;
            }
        }

        Iterator<Carta> itr2 = ganadasJ2.iterator();
        while (itr2.hasNext()) {
            Carta c2 = (Carta) itr2.next();
            if (c2.getNum() == 1) {
                suma2 += 11;
            } else if (c2.getNum() == 3) {
                suma2 += 10;
            } else if (c2.getNum() == 10) {
                suma2 += 2;
            } else if (c2.getNum() == 11) {
                suma2 += 3;
            } else if (c2.getNum() == 12) {
                suma2 += 4;
            }
        }

        if (suma1 > suma2) {
            return "¡Felicidades has GANADO con " + suma1 + " puntos ante los " + suma2 + " de la CPU! ";
        } else if (suma1 < suma2) {
            return "¡Lástima, has logrado " + suma1 + " puntos y la CPU " + suma2 + ", esta vez PIERDES! ";
        } else {
            return "¡Increíble has logrado un EMPATE con la CPU con " + suma1 + " puntos! ";
        }
    }

    public int resultadoInt() {
        int suma1 = 0, suma2 = 0;

        Iterator<Carta> itr = ganadasJ1.iterator();
        while (itr.hasNext()) {
            Carta c = (Carta) itr.next();
            if (c.getNum() == 1) {
                suma1 += 11;
            } else if (c.getNum() == 3) {
                suma1 += 10;
            } else if (c.getNum() == 10) {
                suma1 += 2;
            } else if (c.getNum() == 11) {
                suma1 += 3;
            } else if (c.getNum() == 12) {
                suma1 += 4;
            }
        }

        Iterator<Carta> itr2 = ganadasJ2.iterator();
        while (itr2.hasNext()) {
            Carta c2 = (Carta) itr2.next();
            if (c2.getNum() == 1) {
                suma2 += 11;
            } else if (c2.getNum() == 3) {
                suma2 += 10;
            } else if (c2.getNum() == 10) {
                suma2 += 2;
            } else if (c2.getNum() == 11) {
                suma2 += 3;
            } else if (c2.getNum() == 12) {
                suma2 += 4;
            }
        }

        if (suma1 > suma2) {
            return 0;
        }
        return 1;
    }

    public void cambiarCartaJ1(int i, int numero) {
        if (numero == 2) {
            Carta c = manoJ1.get(i);
            manoJ1.remove(i);
            manoJ1.add(palo);
            palo = c;
        }
        if (numero == 7) {
            Carta c = manoJ1.get(i);
            manoJ1.remove(i);
            manoJ1.add(palo);
            palo = c;
        }

    }

    public void cambiarCartaJ2(int i, int numero) {
        if (numero == 2) {
            Carta c = manoJ2.get(i);
            manoJ2.remove(i);
            manoJ2.add(palo);
            palo = c;
        }
        if (numero == 7) {
            Carta c = manoJ2.get(i);
            manoJ2.remove(i);
            manoJ2.add(palo);
            palo = c;
        }

    }


}