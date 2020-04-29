package com.conpany.project;

import com.company.project.model.Roll;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RollTest {
    public static void main(String[] args) {
        Roll roll = new Roll("[1,2,3,6,5]","[1,2]",new Date());
        Integer redNum = 0, blueNum = 0;
        String reds = "5,6,7,8,9", blues = "1,2";
        String regex = ",",regex1 = "\\d";
        Pattern pattern = Pattern.compile(regex);
        String[] redArr =  pattern.split(reds),blueArr =  pattern.split(blues);
        pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(roll.getRedBall());
        while (matcher.find()){
            for (String str : redArr){
                if(matcher.group().equals(str)){
                    redNum ++;
                }
            }
        }
        matcher = pattern.matcher(roll.getBlueBall());
        while (matcher.find()){
            for (String str : blueArr){
                if(matcher.group().equals(str)){
                    blueNum ++;
                }
            }
        }
        System.out.println("red : " + redNum + "," + "blue : " + blueNum);
    }
}
