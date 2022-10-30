/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.Icon;
import model.Jogador;
import model.JogadorDois;
import model.JogadorUm;
import model.Jogo;
import model.Vazio;

/**
 *
 * @author Jéssica Petersen
 */
public class ControllerGame {
    
    
    public Jogo jogo;
    private int jogadorAtual;
    private String feedback;
    private String[][] tabuleiroView =  {{"A_A", "A_B", "A_C"}, {"B_A", "B_B", "B_C"}, {"C_A", "C_B", "C_C"}};
    private boolean ganhou;
    
    public ControllerGame(){
        jogo = Jogo.getInstance();
        jogadorAtual = 1;
        setFeedback("");
        ganhou = false;
    }
    
    public void reset(){
        this.jogo.reset();
    }
    
    public void newGame(){
        this.jogo.newGame();
    }
    
   public void marcarVitoria(){
       if(this.jogadorAtual == 1){
           this.jogo.addVitoriaJogadorUm();
       }else{
           this.jogo.addVitoriaJogadorDois();
       }
       ganhou = true;
   }
     
     public Jogo getJogo(){
         return this.jogo;
     }
     /**
     * @return the jogadorAtual
     */
    public int getJogadorAtual() {
        return jogadorAtual;
    }
    
    public void mudarJogador(){
        if(this.jogadorAtual == 1){
            this.jogadorAtual = 2;
        }else{
            this.jogadorAtual = 1;
        }
    }
    
    public String indexOf(String peca){
         for(int i = 0; i <3; i++){
            for(int j = 0; j  <3; j++){
                if(this.tabuleiroView[i][j].equals(peca)){
                    return i+","+j;
                }
            }
        }
         return "0";
    }
     
    
    
     public boolean pecaClicada(String peca){
         boolean mudar  = false;
         String indexs = indexOf(peca);
         if(!indexs.equals("0")){
             String[] index = indexs.split(",");
             int linha = Integer.parseInt(index[0]);
             int coluna = Integer.parseInt(index[1]);
             Jogador jogador = this.jogo.getByIndex(linha, coluna);
             if(jogador.getClass() == Vazio.class){
                 mudar = true;
                 this.jogo.setarJogadorTabuleiro(jogadorAtual, linha, coluna);
                 if(verificarGanhador(linha, coluna)){
                     setFeedback("Parabéns, você ganhou!");
                     marcarVitoria();
                 }else{
                     mudarJogador();
                 }
                 
             }
         }
         return mudar;
     }
     
     public Icon getIconPeca(int linha, int coluna){
         return this.jogo.getByIndex(linha, coluna).getImagem();
     }

    /**
     * @return the feedback
     */
    public String getFeedback() {
        return feedback;
    }

    /**
     * @param feedback the feedback to set
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    private boolean verificarGanhador(int linha, int coluna) {
       if(!verificaLinha(linha)){
           if(!verificaColuna(coluna)){
               if(linha == coluna){
                    return verificaDiagonal(linha, coluna, true);
                }
               if((linha == 0 && coluna == 2) || (linha == 2 && coluna == 0)){
                   return verificaDiagonal(linha, coluna, false);
               }else{
                   return false;
               }
           }
       }
       return true;
    }
    
    public Class<?> verificaClasseJogadorAtual(){
        if(jogadorAtual == 1){
            return JogadorUm.class;
        }
        return JogadorDois.class;
    }

    private boolean verificaLinha(int linha) {
        boolean vitoria = false;
        int iguais = 0;
         Class classAtual = verificaClasseJogadorAtual();
        for(int i = 0; i < 3; i++){
            Jogador jogador = this.jogo.getByIndex(linha, i);
            if(jogador.getClass() == Vazio.class){
                break;
            }
            if(jogador.getClass() == classAtual){
                iguais++;
            }
        }
        if(iguais == 3){
            vitoria = true;
        }
        return vitoria;
    }

    private boolean verificaColuna(int coluna) {
         boolean vitoria = false;
        int iguais = 0;
        Class classAtual = verificaClasseJogadorAtual();
        for(int i = 0; i < 3; i++){
            Jogador jogador = this.jogo.getByIndex(i, coluna);
            if(jogador.getClass() == Vazio.class){
                break;
            }
            if(jogador.getClass() == classAtual){
                iguais++;
            }
        }
        if(iguais == 3){
            vitoria = true;
        }
        return vitoria;
    }

    private boolean verificaDiagonal(int linha, int coluna, boolean iguais) {
         boolean vitoria = false;
        int iguaisQtd = 0;
        Class classAtual = verificaClasseJogadorAtual();
        if(iguais){
            for(int i = 0; i < 3; i++){
                Jogador jogador = this.jogo.getByIndex(i, i);
                if(jogador.getClass() == Vazio.class){
                    break;
                }
                if(jogador.getClass() == classAtual){
                    iguaisQtd++;
                }
            }
            if(iguaisQtd == 3){
                return true;
            }else{
                iguaisQtd =0;
            }
        }
        
        if(!iguais || (linha == 1 && coluna == 1)){
            coluna = 2;
            for(int i = 0; i < 3; i++){
                Jogador jogador = this.jogo.getByIndex(i, coluna);
                if(jogador.getClass() == Vazio.class){
                    break;
                }
                if(jogador.getClass() == classAtual){
                    iguaisQtd++;
                }
                coluna--;
            }
        }
        
        if(iguaisQtd == 3){
            vitoria = true;
        }
        return vitoria;
    }

    /**
     * @return the ganhou
     */
    public boolean isGanhou() {
        return ganhou;
    }
    
    
}
