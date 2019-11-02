package com.mingyin;

public class Post {
    private String author;
    private String content;
    private String score;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Post{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
