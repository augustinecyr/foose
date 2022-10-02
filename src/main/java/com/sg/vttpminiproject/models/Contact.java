package com.sg.vttpminiproject.models;


public class Contact {
    String name;
    String email;
    String message;
   
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Contact(String email, String name, String message) {
        this.email = email;
        this.name = name;
        this.message = name;
    }

    public Contact(String name, String message) {
    
        this.name = name;
        this.message = message;
    }

    

    
    
}
