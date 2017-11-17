/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myownrpg;

/**
 *
 * @author bala_
 */
public class motionClass {
    private int type;
    private double duration;
    public motionClass(int _type, double _duration){
        type=_type;
        duration=_duration;
    }
    
    public int getType(){
        return type;
    }
    public double getDuration(){
        return duration;
    }
}
