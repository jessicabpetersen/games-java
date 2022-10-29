/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Word;

/**
 *
 * @author Jéssica Petersen
 */
public class ControllerWord {
    
    List<Word> list = new ArrayList();
    String[] words = {"CACHORRO", "JASMIN", "PROFESSOR", "FLORIANÓPOLIS",
                     "CAVALO", "ORQUÍDEA", "ARQUITETO", "PORTO ALEGRE",
                    "PATO", "JABUTICABEIRA", "POLICIAL", "ESTADIO BEIRA RIO",
                    "CORUJA", "GIRASSOL", "PROGRAMADOR", "RIO GRANDE DO SUL",
                    "LONTRA", "HIBISCO", "PERSONAL TRAINER", "SANTA CATARINA",
                    "GAVIÃO", "IPÊ", "CONTADOR", "LAGOA DA CONCEIÇÃO",
                    "ELEFANTE", "TREVO DE QUATRO FOLHAS", "ENGENHEIRO FLORESTAL", "BRASIL"};
    String[] category = {"Animal", "Planta", "Profisssão", "Lugar"};
    
    private static ControllerWord instance;
    
    public synchronized static ControllerWord getInstance() {
        if (instance == null)
                instance = new ControllerWord();

        return instance;
    }
    
    private int getCategoryIndex(int i){
        int indice = i+1;
        int categoryIndex = 0;
        if(indice > 4){
            if(indice%2==0){
               if(indice%4 == 0){
                   categoryIndex = 3;
               }else{
                   categoryIndex = 1;
               }
            }else{
                if(i%4==0){
                    categoryIndex = 0;
                }else{
                    categoryIndex = 2;
                }
            }
        }else{
            categoryIndex = i;
        }
        
        return categoryIndex;
    }
    
    public String replaceAllWords(String word){
        String replace = "";
        
        char[] chars = word.toCharArray();
        
        for(char iChar: chars){
            if(iChar != ' '){
                replace += " _";
            }else{
                replace += "&nbsp;";
            }
        }
        return replace;
    }
    
    public ControllerWord(){
        Word word;
        for(int i =0; i < words.length; i++){
            word = new Word();
            word.setWord(words[i]);
            word.setCategory(category[getCategoryIndex(i)]);
            word.setWordView(replaceAllWords(words[i]));
            list.add(word);
        }
    }
    
    public Word getWord(){
        int random = (int) ((Math.random() * (list.size() - 0)) + 0);
        return list.get(random);
    }
    
}
