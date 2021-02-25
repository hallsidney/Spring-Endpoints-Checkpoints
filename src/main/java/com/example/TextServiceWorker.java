package com.example;

import java.util.List;

public class TextServiceWorker {

    String original; //SNAKE-CASE
    boolean initialCap; //CAE
    List<String> badWord;
    String find, replacement, body;
    String message, key;

    public String sed(){
        return this.body.replaceAll(this.find, this.replacement);
    }

    public String encode() {
        String alphabet = "", translate = "";
        for(char i = 'a'; i <= 'z'; i++) alphabet += i;
        for(int i = 0; i < this.message.length(); i++){
            char current = this.message.charAt(i);
            if(current != ' ') { translate += String.valueOf(key.charAt(alphabet.indexOf(current)));}
            else{translate += ' ';}
        }return translate;
    }

    public String fixBadWord() {
        //String s = this.original;
        for(int i = 0; i < this.badWord.size(); i++){ this.original = this.original.replaceAll(this.badWord.get(i), "*".repeat(this.badWord.get(i).length())); }
        return this.original; //s
    }

    public String getCamelCase() {
        StringBuffer s = new StringBuffer(this.original);
        for(int i = 0; i < s.length(); i++)
        {
            if(this.initialCap == true){
                s.setCharAt(0,Character.toUpperCase(s.charAt(0)));
            }
            if(s.charAt(i) == '_'){
                s.setCharAt(i,Character.toUpperCase(s.charAt(i+1)));
                s.deleteCharAt(i+1);
            }
        }
        return s.toString();
    }

    public List<String> getBadWord() {
        return badWord;
    }
    public void setBadWord(List<String> badWord) {
        this.badWord = badWord;
    }
    public String getOriginal() {
        return original;
    }
    public void setOriginal(String original) {
        this.original = original;
    }
    public boolean isInitialCap() {
        return initialCap;
    }
    public void setInitialCap(String initialCap) {
        this.initialCap = Boolean.parseBoolean(initialCap);
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getFind() {
        return find;
    }
    public void setFind(String find) {
        this.find = find;
    }
    public String getReplacement() {
        return replacement;
    }
    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getBody() {
        return this.body;
    }
}
