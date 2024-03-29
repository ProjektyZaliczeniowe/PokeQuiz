package com.project.pokequiz;


import java.io.Serializable;

public class Question implements Serializable {
    private long id;
    private String imageName;
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

    public String getImageName(){
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Question(String imageName, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, String goodAnswer) {
        this.imageName = imageName;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.goodAnswer = goodAnswer;
    }
}
