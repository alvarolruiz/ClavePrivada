package com.company.Modelo;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Decrypter {
    public static final String TERMINACION_DESENCRIPTADO = ".decript";

    private String nombreFicheroClave;
    private String ficheroEntrada;
    private String ficheroSalida;
    private String algoritmo;


    public Decrypter(String nombreFicheroClave, String ficheroEntrada, String ficheroSalida, String algoritmo) {
        this.nombreFicheroClave = nombreFicheroClave;
        this.ficheroEntrada = ficheroEntrada;
        this.ficheroSalida = ficheroSalida;
        this.algoritmo = algoritmo;

    }

    public boolean descifrarArchivo(){
        Cipher cifrado = getCifrado(leerValorClaveDeFicheroClave());
        return escribirFicheroDescifrado(cifrado);
    }

    private Cipher getCifrado(byte[] valorClave) {
        SecretKeySpec keySpec = new SecretKeySpec(valorClave, algoritmo);
        SecretKey clave;
        Cipher cifrado = null;
        try {
            clave = new SecretKeySpec(keySpec.getEncoded(), algoritmo);
            cifrado = Cipher.getInstance(algoritmo);
            cifrado.init(Cipher.DECRYPT_MODE, clave);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return cifrado;
    }

    private byte[] leerValorClaveDeFicheroClave(){
        byte[] valorClave = null;
        try (FileInputStream fisClave = new FileInputStream(nombreFicheroClave)) {
            valorClave = fisClave.readAllBytes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valorClave;
    }
    private boolean escribirFicheroDescifrado(Cipher cifrado){
        boolean correcto = false;
        File inputFile = new File(ficheroEntrada);
        File outputFile = new File(ficheroSalida + TERMINACION_DESENCRIPTADO);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile);
             BufferedInputStream is = new BufferedInputStream(fis);
             BufferedOutputStream os = new BufferedOutputStream(fos)) {
            byte[] buff = new byte[cifrado.getBlockSize()];
            while(is.read(buff) != -1) {
                os.write(cifrado.update(buff));
            }
            os.write(cifrado.doFinal());
            if(outputFile.length()!=0) correcto=true;
        } catch (IOException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return correcto;
    }
}
