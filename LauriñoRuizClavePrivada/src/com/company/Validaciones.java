package com.company;

import java.io.File;

public class Validaciones {

    public static boolean existeFile(String nombreFichero){
        boolean existe = false;
        File file = new File(Constantes.rutaFicheros(nombreFichero));
        if(file.exists()){
            existe = true;
        } else System.out.println(String.format("El fichero '%s' no existe", nombreFichero));
        return existe;
    }
}
