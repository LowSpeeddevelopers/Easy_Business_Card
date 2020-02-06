package com.nexttech.easybusinesscard.BusinessCard.Utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Toast;

import com.nexttech.easybusinesscard.BusinessCard.Activity.Create_card;

public class LongPresslistener implements View.OnLongClickListener,View.OnDragListener {
    int x;
    int y;
    Context context;
    public LongPresslistener(Context context){
        this.context=context;
    }
    @Override
    public boolean onLongClick(View v) {
        // Create a new ClipData.Item from the ImageView object's tag
        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
        // Create a new ClipData using the tag as a label, the plain text MIME type, and
        // the already-created item. This will create a new ClipDescription object within the
        // ClipData, and set its MIME type entry to "text/plain"
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
        // Instantiates the drag shadow builder.
        View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);
        // Starts the drag
        v.startDrag(data        // data to be dragged
                , dragshadow   // drag shadow builder
                , v           // local data about the drag and drop operation
                , 0          // flags (not currently used, set to 0)
        );
        return true;
    }
    // This is the method that the system calls when it dispatches a drag event to the listener.
    @Override
    public boolean onDrag(View v, DragEvent event) {



        int restrectedpointx;
        int restrectedpointy;
        int restrectedpointxend;
        int restrectedpointyend;
        if(Create_card.isLayoutVisible()){
            //Create_card.deltebuttonfront.setg
            Create_card.deltebuttonfront.setVisibility(View.VISIBLE);
            Create_card.deltebuttonfront.setX(Create_card.xvalue);
            Create_card.deltebuttonfront.setY(Create_card.yvalue);

            restrectedpointx=Math.round(Create_card.deltebuttonfront.getX());
            restrectedpointy = Math.round(Create_card.deltebuttonfront.getY());
            restrectedpointxend=restrectedpointx+Create_card.deltebuttonfront.getWidth();
            restrectedpointyend=restrectedpointy+Create_card.deltebuttonfront.getHeight();

        }else {
            Create_card.deltebuttonback.setVisibility(View.VISIBLE);
            restrectedpointx=Math.round(Create_card.deltebuttonback.getX());
            restrectedpointy = Math.round(Create_card.deltebuttonback.getY());
            restrectedpointxend=restrectedpointx+Create_card.deltebuttonfront.getWidth();
            restrectedpointyend=restrectedpointy+Create_card.deltebuttonfront.getHeight();
        }


        float minX = Math.round(Create_card.mainTempFront.getX());
        float minY = Math.round(Create_card.mainTempFront.getY());
        float maxX= Math.round(minX+Create_card.mainTempFront.getWidth());
        float maxY = Math.round(minY+Create_card.mainTempFront.getHeight());

        Log.e("minx",String.valueOf(minX));
        Log.e("miny",String.valueOf(minY));
        Log.e("maxx",String.valueOf(maxX));
        Log.e("maxy",String.valueOf(maxY));

        Log.e("absoluteheight",String.valueOf(Create_card.absoluteLayoutFront.getHeight()));
        Log.e("absoluteheight",String.valueOf(Create_card.absoluteLayoutFront.getWidth()));
        Log.e("imageHeight",String.valueOf(Create_card.mainTempFront.getHeight()));
        Log.e("imagewidth",String.valueOf(Create_card.mainTempFront.getWidth()));

        // Defines a variable to store the action type for the incoming event
        int action = event.getAction();
        // Handles each of the expected events
        switch (action) {

            case DragEvent.ACTION_DRAG_STARTED:
                // Determines if this View can accept the dragged data
                if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // if you want to apply color when drag started to your view you can uncomment below lines
                    // to give any color tint to the View to indicate that it can accept data.
                    //v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    //Invalidate the view to force a redraw in the new tint
                    //  v.invalidate();
                    // returns true to indicate that the View can accept the dragged data.
                    return true;
                }
                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                // Applies a GRAY or any color tint to the View. Return true; the return value is ignored.
                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                // Invalidate the view to force a redraw in the new tint
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                // Ignore the event

                Point touchPosition = getTouchPositionFromDragEvent(v, event);

                x = touchPosition.x;
                y = touchPosition.y;


                Log.e("x",Integer.toString(x));
                Log.e("y",Integer.toString(y));

                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                // Re-sets the color tint to blue. Returns true; the return value is ignored.
                // view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                //It will clear a color filter .
                v.getBackground().clearColorFilter();
                // Invalidate the view to force a redraw in the new tint
                v.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                // Gets the item containing the dragged data
                ClipData.Item item = event.getClipData().getItemAt(0);
                // Gets the text data from the item.
                String dragData = item.getText().toString();
                // Displays a message containing the dragged data.
                Toast.makeText(context, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();
                // Turns off any color tints
                v.getBackground().clearColorFilter();
                // Invalidates the view to force a redraw
                v.invalidate();

                View vw = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) vw.getParent();
                owner.removeView(vw); //remove the dragged view
                //caste the view into LinearLayout as our drag acceptable layout is LinearLayout


                if( x< maxX-((vw.getWidth())/2) && y<maxY-((vw.getHeight()/2)) && x>((vw.getWidth())/2) && y>((vw.getHeight()/2))){
                    Toast.makeText(context, "Cant Place There!", Toast.LENGTH_SHORT).show();
                    vw.setX(x-vw.getWidth()/2);
                    vw.setY(y-vw.getHeight()/2);
                }






                Log.e("set x",Float.toString(vw.getX()));
                Log.e("set y",Float.toString(vw.getY()));


                AbsoluteLayout container = (AbsoluteLayout) v;
                container.addView(vw);//Add the dragged view
                vw.setVisibility(View.VISIBLE);//finally set Visibility to VISIBLE
                // Returns true. DragEvent.getResult() will return true.


                if(x>restrectedpointx && x<restrectedpointxend && y>restrectedpointy && y<restrectedpointyend){
                    container.removeView(vw);
                    vw.setVisibility(View.GONE);
                    Create_card.viewPager.setCurrentItem(0);
                    Create_card.mAdapter.notifyDataSetChanged();
                    if(Create_card.isLayoutVisible()){
                        //ToolbarFragment.textArrayFront.remove(vw.getTag());
                    }else {
                        //ToolbarFragment.textArrayBack.remove(vw.getTag());
                    }
                }

                return true;


            case DragEvent.ACTION_DRAG_ENDED:

                if(Create_card.isLayoutVisible()){
                    Create_card.deltebuttonfront.setVisibility(View.INVISIBLE);
                }else {
                    Create_card.deltebuttonback.setVisibility(View.INVISIBLE);
                }
                // Turns off any color tinting
                v.getBackground().clearColorFilter();
                // Invalidates the view to force a redraw
                v.invalidate();
                // Does a getResult(), and displays what happened.
                if (event.getResult())
                    Toast.makeText(context, "The drop was handled.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(context, "The drop didn't work.", Toast.LENGTH_SHORT).show();
                // returns true; the value is ignored.
                return true;
            // An unknown action type was received.
            default:
                Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }

    public Point getTouchPositionFromDragEvent(View item, DragEvent event) {
        // Rect rItem = new Rect();
        // item.getGlobalVisibleRect(rItem);
        return new Point( Math.round(event.getX()),  Math.round(event.getY()));
    }
}
