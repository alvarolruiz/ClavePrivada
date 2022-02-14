package com.company.Modelo;

import com.company.Constantes;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class KeyGenerator {
    private String algoritmoClaveSimetrica;
    private String algoritmoGeneracionNumAleatorio;
    private String nombreFicheroClave;

    public KeyGenerator(String algoritmoClaveSimetrica, String algoritmoGeneracionNumAleatorio, String nombreFicheroClave) {
        this.algoritmoClaveSimetrica = Constantes.rutaFicheros(algoritmoClaveSimetrica);
        this.algoritmoGeneracionNumAleatorio = Constantes.rutaFicheros(algoritmoGeneracionNumAleatorio);
        this.nombreFicheroClave = Constantes.rutaFicheros(nombreFicheroClave);
    }

    public void crearYEscribirClaveEnFichero() {
        try {
            SecretKey clave = getSecretKey();
            byte [] valorClave = getClaveSimetrica(clave);
            escribirClaveEnArchivo(valorClave);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public SecretKey getSecretKey() throws NoSuchAlgorithmException {
        SecretKey clave = null;
        javax.crypto.KeyGenerator genClaves = javax.crypto.KeyGenerator.getInstance(algoritmoClaveSimetrica);
        SecureRandom srand = SecureRandom.getInstance(algoritmoGeneracionNumAleatorio);
        genClaves.init(srand);
        clave = genClaves.generateKey();
        System.out.println(String.format("Formato de clave: %s\n", clave.getFormat()));
        return clave;
    }

    private void escribirClaveEnArchivo(byte[] valorClave) {
        try (FileOutputStream fos = new FileOutputStream(nombreFicheroClave)) {
            fos.write(valorClave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getClaveSimetrica(SecretKey clave) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] valorClave = null;
        SecretKeyFactory keySpecFactory = null;
        if (algoritmoClaveSimetrica != "AES") {
            keySpecFactory = SecretKeyFactory.getInstance(algoritmoClaveSimetrica);
        }
        switch (algoritmoClaveSimetrica) {
            case "DESede" -> {
                DESedeKeySpec keySpec = (DESedeKeySpec) keySpecFactory.getKeySpec(clave, DESedeKeySpec.class);
                valorClave = keySpec.getKey();
            }
            case "DES" -> {
                DESKeySpec keySpec = (DESKeySpec) keySpecFactory.getKeySpec(clave, DESKeySpec.class);
                valorClave = keySpec.getKey();
            }
            case "AES" -> {
                SecretKeySpec keySpec = new SecretKeySpec(clave.getEncoded(), algoritmoClaveSimetrica);
                valorClave = keySpec.getEncoded();
            }
        }
        System.out.printf("Longitud de clave: %d bits\n", valorClave.length * 8);
        System.out.printf("Valor de la clave: [%s] en fichero %s\n",
                valorHexadecimal(valorClave), nombreFicheroClave);
        return valorClave;
    }

    private String valorHexadecimal(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%x", b));
        }
        return result.toString();
    }
}
