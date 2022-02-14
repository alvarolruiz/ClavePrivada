package com.company;

public class Constantes {
    public static final int OPCION_MINIMA_SELECCION_ALGORITMO = 1;
    public static final int OPCION_MAXIMA_SELECCION_ALGORITMO = 3;
    public static int OPCION_MINIMA_MENU = 0;
    public static int OPCION_MAXIMA_MENU = 3;
    public static String ALGORITMO_GENERACION_ALEATORIOS = "SHA1PRNG";
    public static String RUTA_FILES = "LauriñoRuizClavePrivada/src/com/company/Files";

    public static String rutaFicheros(String nombreFichero) {
        return RUTA_FILES +nombreFichero;
    }
}
