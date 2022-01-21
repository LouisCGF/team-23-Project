package com.example.team_23_project;

import org.junit.Test;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class Testing {

    @Test
    public void checkPasswordHashing() throws InvalidKeySpecException, NoSuchAlgorithmException {
        PBKDF2WithHmacSHA512Hash hasher = new PBKDF2WithHmacSHA512Hash();

        assertNotEquals("1000:e41860cbe54ba4fd296d0a33e66cbf81:f7cb333825f11989605ce03ee33" +
                "5d282543d88b1de401788eb3df45b1a1ff81deb8c4c8f07c52c5b9bc6d82ef9412c41c3ae5f2fbb4558" +
                "8ea3183efa37b525d6", hasher.hashPBKDF2WithHmacSHA512Password("Password4321"));
    }

    @Test
    public void checkPasswordValidationHashing() throws InvalidKeySpecException, NoSuchAlgorithmException {
        PBKDF2WithHmacSHA512Hash hasher = new PBKDF2WithHmacSHA512Hash();
        assertTrue(hasher.validatePBKDF2WithHmacSHA512Password("Password1234",
                "1000:e41860cbe54ba4fd296d0a33e66cbf81:f7cb333825f11989605ce03ee335d282" +
                        "543d88b1de401788eb3df45b1a1ff81deb8c4c8f07c52c5b9bc6d82ef9412c41c3ae5f2fbb4" +
                        "5588ea3183efa37b525d6" ));

        assertFalse(hasher.validatePBKDF2WithHmacSHA512Password("Password4321",
                "1000:e41860cbe54ba4fd296d0a33e66cbf81:f7cb333825f11989605ce03ee335d282" +
                        "543d88b1de401788eb3df45b1a1ff81deb8c4c8f07c52c5b9bc6d82ef9412c41c3ae5f2fbb4" +
                        "5588ea3183efa37b525d6" ));
    }

    @Test
    public void checkPasswordValidation(){
        UserRegisterActivityStudent uras = new UserRegisterActivityStudent();
        assertTrue(uras.validatePassword("fitkJQ78D"));
        assertFalse(uras.validatePassword("asdfg7"));

    }


}