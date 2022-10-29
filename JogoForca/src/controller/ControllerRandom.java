/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Word;

/**
 *
 * @author Jéssica Petersen
 */
public class ControllerRandom {
    
    private Word choosen;
    private String typedLetters;
    private int imgHangman;
    private boolean won;
    private boolean lost;
    
    
    public ControllerRandom(){
        ControllerWord controllerWord = new ControllerWord();
        
        choosen = controllerWord.getWord();
        typedLetters = " ";
        imgHangman = 1;
        won = false;
        lost = false;
    }
    
    public Word getChoosen(){
        return this.choosen;
    }

    /**
     * @return the typedLetters
     */
    public String getTypedLetters() {
        return typedLetters;
    }

    /**
     * @param typedLetters the typedLetters to set
     */
    public void setTypedLetters(String typedLetters) {
        this.typedLetters = typedLetters;
    }

    /**
     * @return the imgHangman
     */
    public int getImgHangman() {
        return imgHangman;
    }

    /**
     * @param imgHangman the imgHangman to set
     */
    public void setImgHangman(int imgHangman) {
        this.imgHangman = imgHangman;
    }
    
    public void verifyWord(String letter){
        if(letter.toUpperCase().trim().equals(getChoosen().getWord().trim())){
            setWon(true);
            setLost(false);
        }else{
            setWon(false);
            setLost(true);
        }
    }
    
    public void updateWord(int indexInicial, char iChar){
        int index = getChoosen().getWord().replace(" ", "").indexOf(iChar, indexInicial);
        if(index != -1){
             int underlines = 0;
             String newWordView = "";
            char[] wordsView = getChoosen().getWordView().toCharArray();
            for(char iChars: wordsView){
                if(iChars == '_'){
                    if(index == underlines){
                        newWordView += getChoosen().getWord().replace(" ", "").charAt(index);
                        underlines++;
                    }else{
                        newWordView += iChars;
                        underlines++;
                    }
                }else{
                    if(iChars != ' ' && iChars != ';' && iChars !='&' && iChars !='n'&& iChars != 'b' && iChars !='s' && iChars !='p'){
                        underlines++;
                    }
                    newWordView += iChars;
                }
                
            }
            this.getChoosen().setWordView(newWordView);
            if(getChoosen().getWord().length() > index){
                updateWord(index+1, iChar);
            }
        }
    }
    
    public void updateLetter(String letter){
        String[] chars = letter.toUpperCase().replace(" ", "").split(",");
        boolean hasEqual = false;
        
        for(String iString: chars){
            if(getChoosen().getWord().contains(iString)){
                hasEqual = true;
            }
            if(hasEqual){
                updateWord(0,iString.charAt(0));
            }else{
                if(getImgHangman() < 6){
                    String typed = getTypedLetters() + " "+iString;
                    setTypedLetters(typed);
                    setImgHangman(getImgHangman()+1);
                }else{
                    setImgHangman(getImgHangman()+1);
                    setWon(false);
                    setLost(true);
                    break;
                }
                
            }
            hasEqual = false;
        }
        
    }
    
    public void verifyLetter(String letter){
        if(letter.length() > 1 && !letter.contains(",")){
            verifyWord(letter);
        }else{
            updateLetter(letter);
        }
    }

    /**
     * @return the won
     */
    public boolean isWon() {
        return won;
    }

    /**
     * @param won the won to set
     */
    public void setWon(boolean won) {
        this.won = won;
    }

    /**
     * @return the lost
     */
    public boolean isLost() {
        return lost;
    }

    /**
     * @param lost the lost to set
     */
    public void setLost(boolean lost) {
        this.lost = lost;
    }
    
    
    
}
