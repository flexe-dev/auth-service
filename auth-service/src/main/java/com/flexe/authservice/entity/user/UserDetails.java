package com.flexe.authservice.entity.user;

public class UserDetails {

    private String userId;
    private String name;
    private String image;
    private String username;
    private String job;

    public UserDetails(){

    }

    public UserDetails(UserNode user){
        this.userId = user.getUserId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.image = user.getImage();
        this.job = user.getJob();
    }

    public UserDetails(UserDisplay user){
        this.userId = user.getUser().getId();
        this.name = user.getUser().getName();
        this.username = user.getUser().getUsername();
        this.image = user.getUser().getImage();
        this.job = user.getProfile().getJob();
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
