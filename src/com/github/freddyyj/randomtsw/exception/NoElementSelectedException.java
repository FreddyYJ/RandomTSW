package com.github.freddyyj.randomtsw.exception;

public class NoElementSelectedException extends RuntimeException{
    String message;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    Throwable cause;
    public NoElementSelectedException(String message, Throwable cause){
        this.message=message;
        this.cause=cause;
    }
    public NoElementSelectedException(String message){
        this.message=message;
    }
    public NoElementSelectedException(Throwable cause){
        this("no element selected",cause);
    }
    public NoElementSelectedException(){
        this("no element selected");
    }
}
