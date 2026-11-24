package com.clinica;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


@Converter
public class Cripto implements AttributeConverter<String, String> {


    private static final String CHAVE = "ChaveSecreta1234";

    @Override
    public String convertToDatabaseColumn(String texto) {
        if (texto == null) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec chave = new SecretKeySpec(CHAVE.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            byte[] criptografado = cipher.doFinal(texto.getBytes());
            return Base64.getEncoder().encodeToString(criptografado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar");
        }
    }
}
