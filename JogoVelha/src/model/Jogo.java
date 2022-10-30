/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import model.Vazio;

/**
 *
 * @author Jéssica Petersen
 */
public class Jogo {
    
    private static Jogo instance;
    private int vitoriasJogadorUm;
    private int vitoriasJogadorDois;
    private Jogador[][] tabuleiro = new Jogador[3][3];
    
     public synchronized static Jogo getInstance() {
        if (instance == null)
            instance = new Jogo();

        return instance;
    }
     
     public void reset(){
        for(int i = 0; i <3; i++){
            for(int j = 0; j  <3; j++){
                tabuleiro[i][j] = new Vazio();
            }
        }
     }
     
     public void newGame(){
         reset();
         this.vitoriasJogadorDois = 0;
         this.vitoriasJogadorUm = 0;
     }
     
     public void setarJogadorTabuleiro(int jogador, int linha, int coluna){
         if(jogador == 1){
             tabuleiro[linha][coluna] = new JogadorUm();
         }else{
             tabuleiro[linha][coluna] = new JogadorDois();
         }
     }
     
     public Jogador getByIndex(int linha, int coluna){
         return this.tabuleiro[linha][coluna];
     }
     
     public Jogo(){
         reset();
     }
    /**
     * @return the vitoriasJogadorUm
     */
    public int getVitoriasJogadorUm() {
        return vitoriasJogadorUm;
    }

    /**
     * @return the vitoriasJogadorDois
     */
    public int getVitoriasJogadorDois() {
        return vitoriasJogadorDois;
    }
     
    public void addVitoriaJogadorUm(){
        this.vitoriasJogadorUm++;
    }
     
    public void addVitoriaJogadorDois(){
       this.vitoriasJogadorDois++;
   }
    
    
    
}
