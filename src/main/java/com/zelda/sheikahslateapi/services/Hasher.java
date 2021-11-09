package com.zelda.sheikahslateapi.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Hasher {

    public static String hash(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static boolean isMatch(String password, String hash) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return (passwordEncoder.matches(password, hash));
    }

    public static void main(String[] args) {
        String hashedPassword = Hasher.hash("some password");
        System.out.println(Hasher.isMatch("some password", hashedPassword));
    }
}
