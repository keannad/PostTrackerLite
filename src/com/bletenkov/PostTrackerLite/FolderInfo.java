package com.bletenkov.PostTrackerLite;

/**
 * Created with IntelliJ IDEA.
 * User: Keannad
 * Date: 16.01.13
 * Time: 22:38
 * To change this template use File | Settings | File Templates.
 */
public class FolderInfo{

    private String id;
    private String name;
    private boolean check;

    public FolderInfo(String id, String name, boolean check){
        this.id = id;
        this.name = name;
        this.check = check;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public boolean isCheck(){
        return check;
    }
}
