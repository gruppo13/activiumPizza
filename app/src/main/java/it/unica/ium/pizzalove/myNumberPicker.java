package it.unica.ium.pizzalove;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.NumberPicker;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)//For backward-compability
public class myNumberPicker extends NumberPicker {
    private float bottomFadingEdgeStrength = 1.0f;
    private float topFadingEdgeStrength = 1.0f;


    public myNumberPicker(Context context) {
        super(context);
    }

    public myNumberPicker(Context context, AttributeSet attrs) {

        super(context, attrs);
        processAttributeSet(attrs);
    }


    public myNumberPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        processAttributeSet(attrs);
    }


    private void processAttributeSet(AttributeSet attrs) {
        //This method reads the parameters given in the xml file and sets the properties according to it
        this.setMinValue(attrs.getAttributeIntValue(null, "min", 0));
        this.setMaxValue(attrs.getAttributeIntValue(null, "max", 0));
    }


    public void setTopFadingEdgeStrength(float strength){
        topFadingEdgeStrength = strength;
    }

    public void setBottomFadingEdgeStrength(float strength){
        bottomFadingEdgeStrength = strength;
    }


}