package com.project.pokequiz;


public class Question {
    private long id;
    private byte[] base64Image;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;
    private String goodAnswer;

    public Question() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(byte[] base64Image) {
        this.base64Image = base64Image;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public String getGoodAnswer() {
        return goodAnswer;
    }

    public void setGoodAnswer(String goodAnswer) {
        this.goodAnswer = goodAnswer;
    }

    public Question(byte[] base64Image, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, String goodAnswer) {
        this.base64Image = base64Image;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.goodAnswer = goodAnswer;
    }
}
