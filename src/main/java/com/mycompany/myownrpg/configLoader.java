
package com.mycompany.myownrpg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
 
public class configLoader {
    public static int numTemplates;
    public static ArrayList <templateScreens> templates;
    public configLoader(File file){
        templates=new ArrayList <templateScreens>();
        processFile(file);        
    }

    private void processFile(File file) {
        try{
        FileReader fileReader=new FileReader(file.getPath());
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        
        String line=null;
        int counter=0;
        while((line = bufferedReader.readLine()) != null) {
            if(counter==0){
                numTemplates=Integer.parseInt(line);    
            }  
            else{
                String[]allWords=line.split("@");               
                templates.add(new templateScreens(allWords[0],Double.parseDouble(allWords[1]),new textProfile(allWords[2],Integer.parseInt(allWords[3]),allWords[4]), allWords[5]));
               
                for (int i=0; i<allWords[6].length()/5;i++){
                    templates.get(counter-1).addMotion(new motionClass(Character.getNumericValue(allWords[6].charAt(i*5)),Double.parseDouble(allWords[6].substring(i*5+1,i*5+5))));
                }
            }
            counter++;
        }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
       
    }
   
}
