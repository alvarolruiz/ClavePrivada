package com.company.Controlador;

import com.company.Constantes;
import com.company.Modelo.Decrypter;
import com.company.Modelo.Encrypter;
import com.company.Modelo.KeyGenerator;
import com.company.Vista.Menu;

public class Gestora {

    // El programa tiene fallos de menú que no he podido solucionar.
    public Gestora() {
    }

    public void imprimirMenu(){
        boolean fin = false;
        int opcion;
        do{
            opcion = Menu.imprimirMenuPrincipal();
            switch(opcion){
                case 1 : generarClave();
                break;
                case 2 : encriptar();
                break;
                case 3 : desencriptar();
                break;
                case 0 : fin = true;
            }
        }while(fin==false);
    }

    private void generarClave() {
        KeyGenerator kg = getKeyGenerator();
        kg.crearYEscribirClaveEnFichero();
    }

    private KeyGenerator getKeyGenerator(){
        String ficheroClave = Menu.solicitarNombreFicheroClave();
        String algoritmoClaveSimetrica = getAlgoritmoSeleccionado();
        String algoritmoGeneracionNumAleatorio = Constantes.ALGORITMO_GENERACION_ALEATORIOS;
        KeyGenerator generadorClaves = new KeyGenerator(algoritmoClaveSimetrica,algoritmoGeneracionNumAleatorio,ficheroClave);
        return generadorClaves;
    }

    private void encriptar() {
        Encrypter encripter = getEncrypter();
        if(encripter.cifrarArchivo()){
            System.out.println(Menu.MENSAJE_CIFRADO_CORRECTO);
        }else{
            System.out.println(Menu.MENSAJE_CIFRADO_INCORRECTO);
        }
    }

    private Encrypter getEncrypter(){
        String ficheroClave = Menu.solicitarNombreFicheroClave();
        String in = Menu.solicitarNombreFicheroAEcriptar();
        String out = Menu.solicitarNombreFicheroEncriptado();
        String algoritmoClaveSimetrica = getAlgoritmoSeleccionado();
        Encrypter encripter =new Encrypter(ficheroClave, in, out,algoritmoClaveSimetrica);
        return encripter;
    }


    private Decrypter getDecrypter(){
        String ficheroClave = Menu.solicitarNombreFicheroClave();
        String in = Menu.solicitarNombreFicheroADesencriptar();
        String out = Menu.solicitarNombreFicheroDesencriptado();
        String algoritmoClaveSimetrica = getAlgoritmoSeleccionado();
        Decrypter decrypter =new Decrypter(ficheroClave, in, out,algoritmoClaveSimetrica);
        return decrypter;
    }

    public String getAlgoritmoSeleccionado(){
        String algoritmoClaveSimetrica = "";
        int algoritmoSeleccionado = Menu.imprimirMenuSeleccionAlgoritmo();
        switch (algoritmoSeleccionado){
            case 1: algoritmoClaveSimetrica = "AES";
                break;
            case 2: algoritmoClaveSimetrica = "DES";
                break;
            case 3: algoritmoClaveSimetrica = "DESede";
                break;
            default:
                System.out.println("Opcion no valida");
        }
        return algoritmoClaveSimetrica;
    }

    private void desencriptar() {
        Decrypter decrypter = getDecrypter();
        decrypter.descifrarArchivo();
    }





}
