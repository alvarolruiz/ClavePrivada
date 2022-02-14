package com.company.Modelo;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encrypter {
    public static final String TERMINACION_ENCRIPTADO = ".encript";

    private String nombreFicheroClave;
    private String ficheroEntrada;
    private String ficheroSalida;
    private String algoritmo;


    public Encrypter(String nombreFicheroClave, String ficheroEntrada, String ficheroSalida, String algoritmo) {
        this.nombreFicheroClave = nombreFicheroClave;
        this.ficheroEntrada = ficheroEntrada;
        this.ficheroSalida = ficheroSalida;
        this.algoritmo = algoritmo;

    }

    public boolean cifrarArchivo(){
        Cipher cifrado = getCifrado(leerValorClaveDeFicheroClave());
        return escribirFicheroCifrado(cifrado);
    }

    private Cipher getCifrado(byte[] valorClave) {
        SecretKeySpec keySpec = new SecretKeySpec(valorClave, algoritmo);
        SecretKey clave;
        Cipher cifrado = null;
        try {
            clave = new SecretKeySpec(keySpec.getEncoded(), algoritmo);
            cifrado = Cipher.getInstance(algoritmo);
            cifrado.init(Cipher.ENCRYPT_MODE, clave);
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
    private boolean escribirFicheroCifrado(Cipher cifrado){
        boolean correcto = false;
        File inputFile = new File(ficheroEntrada);
        File outputFile = new File(ficheroSalida + TERMINACION_ENCRIPTADO);
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)){
            byte[] buffer = new byte[64];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] output = cifrado.update(buffer, 0, bytesRead);
                if (output != null) {
                    fos.write(output);
                }
            }
            byte[] outputBytes = cifrado.doFinal();
            if (outputBytes != null) {
                fos.write(outputBytes);
                correcto = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return correcto;
    }

}
