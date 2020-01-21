package com.nexttech.easybusinesscard;

import android.content.Context;




public class ViewDialogue {
    Context context;
    showDialogue showDialogue;
    public ViewDialogue(showDialogue showDialogue,Context context){
        this.context = context;
        this.showDialogue=showDialogue;
    }

    void callback(){
        showDialogue.BuildDialogue(context);
    }

}
