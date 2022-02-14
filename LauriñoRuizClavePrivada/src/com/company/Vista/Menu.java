package com.company.Vista;

import com.company.Constantes;
import com.company.Validaciones;

import java.util.Scanner;

public class Menu {
    private static final Scanner tecla = new Scanner(System.in);
    private static final String MENU_PRINCIPAL =
            "Menu Principal" + "\n" +
                    "------------------------" + "\n" +
                    "1.- Generar clave" + "\n" +
                    "2.- Encriptar" + "\n" +
                    "3.- Desencriptar" + "\n" +
                    "0.- Salir" + "\n" +
                    "------------------------";
    public static final String MENU_SELECCION_ALGORITMO =
            "Menu Seleccion Algoritmo" + "\n" +
                    "------------------------" + "\n" +
                    "1.- AES" + "\n" +
                    "2.- DES" + "\n" +
                    "3.- DESede" + "\n" +
                    "------------------------";
    public static final String MENSAJE_CIFRADO_CORRECTO = "El fichero se ha encriptado correctamente";
    public static final String MENSAJE_CIFRADO_INCORRECTO = "Ha ocurrido algun error en el cifrado";
    public static final String SOLICITUD_SELECCION_OPCION_MENU = "Por favor introduce una de las opciones mostradas:";
    public static final String SOLICITUD_NOMBRE_FICHERO_CLAVE = "Introduce el nombre del fichero que contendrá " +
            "la clave simétrica:";
    public static final String SOLICITUD_NOMBRE_FICHERO_A_ENCRIPTAR = "Introduce el nombre del fichero a encriptar:";
    public static final String SOLICITUD_NOMBRE_FICHERO_A_DESENCRIPTAR = "Introduce el nombre del fichero a desencriptar:";
    public static final String SOLICITUD_NOMBRE_FICHERO_ENCRIPTADO = "Introduce el nombre del fichero resultante (fichero encriptado):";
    public static final String SOLICITUD_NOMBRE_FICHERO_DESENCRIPTADO = "Introduce el nombre del fichero resultante (fichero desencriptado):";


    public static int imprimirMenuPrincipal() {
        int op = 0;
        do {
            System.out.println(MENU_PRINCIPAL);
            System.out.println(SOLICITUD_SELECCION_OPCION_MENU);
            try {
                op = Integer.parseInt(tecla.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número de los que aparecen en el menú");
            }
        } while (op < Constantes.OPCION_MINIMA_MENU || op > Constantes.OPCION_MAXIMA_MENU);
        return op;
    }

    public static int imprimirMenuSeleccionAlgoritmo() {
        int op = 0;
        do {
            System.out.println(MENU_SELECCION_ALGORITMO);
            System.out.println(SOLICITUD_SELECCION_OPCION_MENU);
            op = Integer.parseInt(tecla.nextLine());
        } while (op < Constantes.OPCION_MINIMA_SELECCION_ALGORITMO || op > Constantes.OPCION_MAXIMA_SELECCION_ALGORITMO);
        return op;
    }

    public static String solicitarNombreFicheroClave() {
        String valor = "";
        do {
            System.out.println(SOLICITUD_NOMBRE_FICHERO_CLAVE);
            valor = tecla.nextLine();
        } while (!Validaciones.existeFile(valor));
        return valor;
    }

    public static String solicitarNombreFicheroAEcriptar() {
        String valor = "";
        do {
            System.out.println(SOLICITUD_NOMBRE_FICHERO_A_ENCRIPTAR);
            valor = tecla.nextLine();
        } while (!Validaciones.existeFile(valor));
        return valor;
    }

    public static String solicitarNombreFicheroADesencriptar() {
        String valor = "";
        do {
            System.out.println(SOLICITUD_NOMBRE_FICHERO_A_DESENCRIPTAR);
            valor = tecla.nextLine();
        } while (!Validaciones.existeFile(valor));
        return valor;
    }

    public static String solicitarNombreFicheroDesencriptado() {

        String valor = "";
        do {
            System.out.println(SOLICITUD_NOMBRE_FICHERO_DESENCRIPTADO);
            valor = tecla.nextLine();
        } while (!Validaciones.existeFile(valor));
        return valor;
    }

    public static String solicitarNombreFicheroEncriptado() {
        String valor = "";
        do {
            System.out.println(SOLICITUD_NOMBRE_FICHERO_ENCRIPTADO);
            valor = tecla.nextLine();
        } while (!Validaciones.existeFile(valor));
        return valor;
    }
}
