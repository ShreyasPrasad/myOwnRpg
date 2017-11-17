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
public class textProfile {
    
     private String content, color; 
     private int size;
     public textProfile(String _content, int _size, String _color){    
        content=_content;
        size=_size;
        color=_color;
    }
     
     public String getContent(){
         return content;
    }
     public String getColor(){
         return color;
     }
     public int getSize(){
         return size;
     }
}
