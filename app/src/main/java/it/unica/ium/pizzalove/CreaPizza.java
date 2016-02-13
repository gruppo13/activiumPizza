package it.unica.ium.pizzalove;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by perlo on 13/02/16.
 */
public class CreaPizza extends AppCompatActivity{
int countGatti;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creapizza);
        this.countGatti = 0;
        // immagini da trascinare
        findViewById(R.id.image).setOnLongClickListener(longListener);
        findViewById(R.id.image1).setOnLongClickListener(longListener);
        findViewById(R.id.image2).setOnLongClickListener(longListener);
        findViewById(R.id.image3).setOnLongClickListener(longListener);
        findViewById(R.id.image4).setOnLongClickListener(longListener);




        //immagini da modificare
        findViewById(R.id.imageMain).setOnDragListener(dropListener);

    }





    View.OnLongClickListener longListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            View.DragShadowBuilder dragShadow = new View.DragShadowBuilder(v);
            ClipData data = ClipData.newPlainText("", "");
            v.startDrag(data, dragShadow, v, 0) ;
            // v.startDrag()
            return false;

        }


    };



    View.OnDragListener dropListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            ImageView dropImage = (ImageView) v;
            //TextView dropText2 = (TextView) v;

            switch (dragEvent) {
                case DragEvent.ACTION_DRAG_ENTERED:
                    //dropText.setTextColor(Color.GREEN);
                    Log.i("Drag Event", "Entered");
                                       break;

                case DragEvent.ACTION_DRAG_EXITED:
                    //dropText.setTextColor(Color.RED);
                    break;

                case DragEvent.ACTION_DROP:

                    //ImageView draggedText = (ImageView) event.getLocalState();
                    //dropImage.setImageDrawable(draggedText.getDrawable());
                    int valoreGatti= dropImage.getImageAlpha();
                    switch (countGatti){
                        case 0 :
                            valoreGatti = R.drawable.gatti2;
                            countGatti++;
                            break;
                        case 1 :
                            valoreGatti = R.drawable.gatti3;
                            countGatti++;
                            break;

                        case 2 :
                            valoreGatti = R.drawable.gatti4;
                            countGatti++;
                            break;
                        case 3 :
                            valoreGatti = R.drawable.gatti5;
                            countGatti++;
                            break;
                        default:
                            valoreGatti = R.drawable.big;
                            countGatti=0;
                            break;

                    }
                    dropImage.setImageResource(valoreGatti);

                  /*  dropText.settext(draggedText.getText());
                    draggedText.setText(dropText2.getText());
*/
                    break;
            }

            return true;
        }

    };




}
