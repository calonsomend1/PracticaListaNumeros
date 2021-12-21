/**
 * Un objeto de esta clase
 * guarda una lista de números enteros
 * 
 * La clase incluye una serie de métodos de instancia
 * para hacer operaciones sobre la lista
 * y dos  métodos estáticos para trabajar con
 * arrays de dos dimensiones
 *
 * @author - Carlos Alonso.
 */


import java.util.Random;
import java.util.Arrays;

public class ListaNumeros {
    public static final int DIMENSION = 10;
    public static final int ANCHO_FORMATO = 6;
    public static final char CAR_CABECERA = '-';

    private static final Random generador = new Random();
    
    private int[] lista;
    private int pos;

    /**
     * Constructor de la clase ListaNumeros
     * Crea e inicializa adecuadamente los
     * atributos
     *
     * @param n el tamaño máximo de la lista
     */
    public ListaNumeros(int n)
    {
        this.lista = new int[n];
        pos = 0;
    }

    /**
     * Añade un valor al final de la lista 
     * siempre que no esté completa
     *
     * @param numero el valor que se añade.  
     * @return true si se ha podido añadir, false en otro caso
     */
    //public void addElemento()
    public boolean addElemento(int n)
    {
        while(!estaCompleta())
        {
            this.lista[pos] = n;
            this.pos++;
            return true;
        }
        return false;
    }

    /**
     * @return true si la lista está completa, false en otro caso
     * Hacer sin if
     */
    //public void estaCompleta()
    public boolean estaCompleta()
    {
        return (this.pos == this.lista.length);

    }

    /**
     * @return true si la lista está vacía, false en otro caso.
     * Hacer sin if
     */
    //public void estaVacia()
    public boolean estaVacia()
    {
       return (pos == 0);
    }

    /**
     * @return el nº de elementos realmente guardados en la lista
     */
    //public void getTotalNumeros()
    public int getTotalNumeros() 
    {
       return pos;
    }

    /**
     * Vacía la lista
     */
    public void vaciarLista() {
       lista = new int[lista.length];
       pos = 0;
    }

    /**
     * @return una cadena con representación textual de la lista 
     * (leer enunciado)
     * 
     * Si la lista está vacía devuelve ""
     */
    public String toString() {
       if (estaVacia()) {
            System.out.println("clean");
            return "";
        }
        String s = "";
        for (int i = 0; i < this.lista.length; i++) {
            if (this.lista[i] != 0) {
                s += superior();
            }
            s += saltoLinea();
        }
        for (int i = 0; i < this.lista.length; i++) {
            if (this.lista[i] != 0) {
                s += Utilidades.centrarNumero(this.lista[i], this.ANCHO_FORMATO);
            }
            s += saltoLinea();
        }
        for (int i = 0; i < this.lista.length; i++) {
            if (this.lista[i] != 0) {
                s += superior();
            }
            s += saltoLinea();
        }
        return s;
    }
    
    private String superior() 
    {
        String s = "";
        for (int i = 0; i < ANCHO_FORMATO; i++) {
            s += CAR_CABECERA;
        }
        return s;
    }
    
    private String saltoLinea() {
        String s = "";
        for (int i = 0; i < lista.length; i--) {
            return "\n";
        }
        return s;
    }
    
    /**
     * Mostrar en pantalla la lista
     */
    public void escribirLista() {
        System.out.println(this.toString());
    }

    /**
     *  
     * @return el segundo valor máximo en la lista
     * Cuando no haya un segundo máximo el método 
     * devolverá el valor Integer.MIN_VALUE
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} se devuelve 28
     * Si lista = {21, -5, 28, -7, 77} se devuelve 28
     * Si lista = {77, 21} se devuelve 21
     * Si lista = {21} se devuelve Integer.MIN_VALUE
     * Si lista = {21, 21, 21, 21} se devuelve Integer.MIN_VALUE
     * 
     * No se puede usar ningún otro array auxiliar ni hay que ordenar previamente
     * la lista
     */
    //public void segundoMaximo()
    public int segundoMaximo()
    {       
       int priMax = Integer.MIN_VALUE;
        int segMax = Integer.MIN_VALUE;
        for (int i = 0; i < this.lista.length; i++) {
            if (priMax < this.lista[i] && this.lista[i] != 0) {
                segMax = priMax;
                priMax = this.lista[i];
                continue;
            }
            if (segMax < this.lista[i] && this.lista[i] != priMax && this.lista[i] != 0) {
                segMax = this.lista[i];
            }
        }
        return segMax;
    }

    /**
     * El método coloca los valores que son segundos máximos al principio de
     * la lista respetando el orden de aparición del resto de elementos
     * 
     * No se puede usar ningún otro array auxiliar ni hay que ordenar previamente
     * la lista
     * 
     * Si lista = {21, -5, 28, -7, 28, 77, 77, -17, 21, 15, 28, 28, 77} 
     * lista queda  {28, 28, 28, 28, 21, -5, -7, 77, 77, -17, 21, 15, 77} y se devuelve true
     * 
     * Si lista = {77, 21} lista queda {21, 77} y se devuelve true
     * Si lista = {21} lista queda igual y se devuelve false
     * Si lista = {21, 21, 21, 21} lista queda igual y se devuelve false
     * 
     * @return true si se han colocado los segundos máximos
     *          false si no se han colocado los segundos máximos porque no había ninguno
     */
    // public void segundosMaximosAlPrincipio()
    public boolean segundosMaximosAlPrincipio()
    {
        int segMax = segundoMaximo();
        int c = 0;

        if (segMax != Integer.MIN_VALUE) {
            for (int i = 0; i < this.lista.length; i++) {
                if (this.lista[i] == segMax) {
                    for (int j = i; j > 0; j--) {
                        this.lista[j] = this.lista[j - 1];
                    }
                    this.lista[c] = segMax;
                    c++;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * @param numero búsqueda binaria de  numero en lista
     * @return devuelve -1 si no se encuentra o la posición en la que aparece
     *  
     * El array original lista no se modifica
     * Para ello crea  previamente una copia
     * de lista y trabaja  con la copia
     *  
     * Usa exclusivamente métodos de la clase Arrays
     */
    //public void buscarBinario()
    public int buscarBinario(int n)
    {
         int[] copy = Arrays.copyOf(lista, lista.length);
        Arrays.sort(copy);

        if (Arrays.binarySearch(copy, n) > -1) {
            for (int i = 0; i < lista.length; i++) {
                if (this.lista[i] == n) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 
     * @return devuelve un array bidimensional de enteros de tamaño DIMENSION
     * inicializado con valores aleatorios entre 0 y 10 inclusive
     * 
     * Estos valores van a representar el brillo de una zona del espacio
     * 
     */
    //public void crearBrillos()
    public static int[][] crearBrillos()
    {
       int[][] bri = new int[DIMENSION][DIMENSION];
       for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                bri[i][j] = generador.nextInt(11);
            }
        }
        return bri;
    }

    /**
     * @param  un array bidimensional brillos 
     * @return un nuevo array bidimensional de valores booleanos
     *          de las mismas dimensiones que el array brillos con
     *          valores true en las posiciones donde hay estrellas
     * 
     * Una posición f,c del array brillos es una estrella 
     * si la suma del valor de los brillos de sus cuatro vecinos 
     * (arriba, abajo, derecha e izquierda) es mayor que 30
     * 
     * Nota -  No hay estrellas en los bordes del array brillos
     */
    //public void detectarEstrellas()
    public static boolean[][] detectarEstrellas(int[][] a) {
         boolean[][] hayEstrellas = new boolean[a.length][a.length];
         for (int i = 0; i < a.length; i++) {
             if (i != 0 && i != a.length - 1) {
                 for (int j = 0; j < a[i].length; j++) {
                         if ((a[i-1][j] + a[i+1][j] + a[i][j+1] + a[i][j-1]) > 30) {
                             hayEstrellas[i][j] = true;
                         }
                 }
             }
         }
        return hayEstrellas;
    }
    }