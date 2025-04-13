package com.exemplo.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "admin123"; // Senha que você deseja usar
        String hashedPassword = encoder.encode(password);
        System.out.println("Senha criptografada: " + hashedPassword);
    }
}