package com.cb;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        // Création de la fenêtre graphique

        JFrame frame = new JFrame(); // Création de la fenêtre graphique
        frame.setTitle("Jeu casse birque"); // Définition du titre de la fenêtre
        frame.setSize(800, 600); // Définition de la taille de la fenêtre
        frame.setLocationRelativeTo(null); // Centrage de la fenêtre
        frame.setResizable(false); // Interdiction de redimensionner la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermeture de la fenêtre lorsqu'on clique sur la croix
        frame.setVisible(true); // Affichage de la fenêtre
    }
}