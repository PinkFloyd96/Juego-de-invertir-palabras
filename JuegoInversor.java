/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estruturadedatosfelipe;

import java.util.*;

/**
 * clase principal del juego para invertir frases
 * @author Felipe Leal
 * @version 2.0
 */
public class JuegoInversor {
    
    /** lector de entrada */
    static Scanner sc = new Scanner(System.in);
    /** lista de frases originales */
    static List<String> frasesOriginales = new ArrayList<>();
    /** pila que guarda el historial de frases invertidas */
    static Deque<String> historial = new ArrayDeque<>();
    /** limite de frases en el historial */
    static final int LIMITE = 5;

    /**
     * programa principal
     * muestra el menú y ejecuta las opciones
     * @param args 
     */
    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\nJuego de Invertir frases");
            System.out.println("1 - Invertir una nueva frase");
            System.out.println("2 - Ver frases ya insertadas");
            System.out.println("3 - Deshacer última inversión");
            System.out.println("4 - Invertir letras de cada palabra");
            System.out.println("5 - Salir");
            System.out.print("Elija una opción: ");
            opcion = leerNumero();

            switch (opcion) {
                case 1 -> invertirFrase();
                case 2 -> mostrarFrases();
                case 3 -> deshacerInversion();
                case 4 -> invertirLetras();
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida.");
            }
        }  while (opcion != 5);
    }

    /**
     * lee un número del usuario y controla errores
     * @return número ingresado o -1 si hay error
     */
    static int leerNumero() {
        try {
         return Integer.parseInt(sc.nextLine());
        }catch (Exception e) {
          return -1;
         }
    }

    /**
     * invierte el orden de las palabras en una frase
     */
    static void invertirFrase() {
        System.out.print("Escriba una frase: ");
        String frase = sc.nextLine();
        frasesOriginales.add(frase);
        Deque<String> pila = new ArrayDeque<>();
        for (String palabra : frase.split(" ")) {
            pila.push(palabra);
        }
        StringBuilder invertida = new StringBuilder();
        while (!pila.isEmpty()) {
          invertida.append(pila.pop()).append(" ");
        }

        String resultado = invertida.toString().trim();
        System.out.println("Frase original: " + frase);
        System.out.println("Frase invertida: " + resultado);

        agregarAlHistorial(resultado);
    }

    /**
     * muestra todas las frases guardadas en orden alfabético
     */
    static void mostrarFrases() {
        if (frasesOriginales.isEmpty()) {
            System.out.println("No hay frases guardadas.");
            return;
        }
        List<String> ordenadas = new ArrayList<>(frasesOriginales);
        Collections.sort(ordenadas, String.CASE_INSENSITIVE_ORDER);
        System.out.println("\nFrases ingresadas (orden alfabético):");
        for (String f : ordenadas) {
            System.out.println("- " + f);
        }
    }

    /**
     * deshace la última inversión del historial
     */
    static void deshacerInversion() {
        if (historial.isEmpty()) {
            System.out.println("No hay inversiones para deshacer.");
        } else {
            String ultima = historial.pop();
            System.out.println("Se deshizo la última inversión: " + ultima);
        }
    }

    /**
     * invierte las letras de cada palabra de la frase
     */
    static void invertirLetras() {
        System.out.print("Escriba una frase: ");
        String frase = sc.nextLine();
        frasesOriginales.add(frase);

        StringBuilder nueva = new StringBuilder();
        for (String palabra : frase.split(" ")) {
            nueva.append(new StringBuilder(palabra).reverse()).append(" ");
        }

        String resultado = nueva.toString().trim();
        System.out.println("Frase original: " + frase);
        System.out.println("Frase con letras invertidas: " + resultado);

        agregarAlHistorial(resultado);
    }

    /**
     * agrega una frase al historial, eliminando la más antigua si se supera el límite
     * @param frase frase a agregar al historial
     */
    static void agregarAlHistorial(String frase) {
        if (historial.size() >= LIMITE) {
            historial.removeLast();
        }
        historial.push(frase);
    }
}
