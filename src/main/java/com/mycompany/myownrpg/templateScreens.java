/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myownrpg;

import java.util.ArrayList;

/**
 *
 * @author bala_
 */
public class templateScreens {
    private double duration;
    private String title, bgColor;
    private textProfile textprofile;
    ArrayList <motionClass> motions;
    public templateScreens(String _title, double _duration, textProfile _textprofile, String _bgColor){
        title=_title;
        duration=_duration;
          textprofile=_textprofile;      
              bgColor=_bgColor;
              motions=new ArrayList<motionClass>();
    }
    
    public String getTitle(){
        return title;
    }
    public double getDuration(){
        return duration;
    }
    public textProfile getTextProfile(){
        return textprofile;
    }
    
    public String getBgColor(){
        return bgColor;
    }
    
    public void addMotion(motionClass motionType){
       motions.add(motionType);
    }
   
}
