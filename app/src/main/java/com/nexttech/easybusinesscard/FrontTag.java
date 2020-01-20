package com.nexttech.easybusinesscard;

import java.util.HashMap;

public class FrontTag {
    HashMap<String,String> FrontMap;
    private static final String FrontName1 = "normal.ttf";
    private static final String FrontName2 = "cursive.ttf";
    private static final String FrontName3 = "aclonia.ttf";
    private static final String FrontName4 = "cutive.ttf";
    private static final String FrontTag1 = "tag1";
    private static final String FrontTag2 = "tag2";
    private static final String FrontTag3 = "tag3";
    private static final String FrontTag4 = "tag4";
    public FrontTag() {
        FrontMap = new HashMap<>();
        FrontMap.put(FrontTag1,FrontName1);
        FrontMap.put(FrontTag2,FrontName2);
        FrontMap.put(FrontTag3,FrontName3);
        FrontMap.put(FrontTag4,FrontName4);
    }
    public String getFrontName(String Tag){
        return FrontMap.get(Tag);
    }






}
