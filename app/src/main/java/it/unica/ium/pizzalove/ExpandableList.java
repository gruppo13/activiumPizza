package it.unica.ium.pizzalove;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
* Created by perlo on 12/02/16.
*/
public class ExpandableList extends BaseExpandableListAdapter {

    private Context _context;
    private List<Pizza> _listDataChild;

    public ExpandableList(Context context, List<Pizza> listChildData) {
        this._context = context;
        this._listDataChild = listChildData;
        List<String> _listDataHeader = new ArrayList<>();
        int i=0;
        for(Pizza pizza: listChildData){
            if (pizza.getNomePizza().equals("creata")){
                _listDataHeader.add("La tua creazione n." + i);
                i++;
            }
            else {
                if(!_listDataHeader.contains(pizza.getNomePizza()))
                    _listDataHeader.add(pizza.getNomePizza());
            }
        }
    }


   @Override
   public Object getChild(int groupPosition, int childPosititon) {
       return this._listDataChild.get(groupPosition).getIngredienti();
   }

   @Override
   public long getChildId(int groupPosition, int childPosition) {
       return childPosition;
   }

   @Override
   public View getChildView(final int groupPosition, int childPosition,
                            boolean isLastChild, View convertView, ViewGroup parent) {

       LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       String childrenText = "";
       convertView = infalInflater.inflate(R.layout.list_item, null);
       TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
       String oldIngrediente = "";
       for (Ingredienti ingrediente : (_listDataChild.get(groupPosition).getIngredienti())) {
           if (oldIngrediente.equals(ingrediente.toString())) {
               childrenText += " x2";
           } else {
               if (!oldIngrediente.isEmpty())
                   childrenText += ",  ";
                   childrenText += ingrediente.toString();
           }
            oldIngrediente = ingrediente.toString();
       }
       txtListChild.setText(childrenText);
       return convertView;
   }

   @Override
   public int getChildrenCount(int groupPosition) {
       return 1;
   }

   @Override
   public Object getGroup(int groupPosition) {
       return this._listDataChild.get(groupPosition);
   }

   @Override
   public int getGroupCount() {
       return this._listDataChild.size();
   }

   @Override
   public long getGroupId(int groupPosition) {
       return groupPosition;
   }

   @Override
   public View getGroupView(final int groupPosition, boolean isExpanded,
                            View convertView, ViewGroup parent) {
       Pizza headerTitle = (Pizza) getGroup(groupPosition);



       if (convertView == null) {
           LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           if ((_context instanceof Carrello) ){
               convertView = infalInflater.inflate(R.layout.listgroup_carrello, null);
           }
           else if ((_context instanceof CreaPizza)){
               convertView = infalInflater.inflate(R.layout.list_item, null);
           }
           else{
               convertView = infalInflater.inflate(R.layout.listgroup, null);
           }
       }
        if (_context instanceof Carrello) {//scontrino
            TextView lblListNum = (TextView) convertView.findViewById(R.id.lblListHCarrelloNum);
            TextView lblListNome = (TextView) convertView.findViewById(R.id.lblListHCarrelloNome);
            TextView lblListPrezzo = (TextView) convertView.findViewById(R.id.lblListHCarrelloPrezzo);
            TextView lblListPrezzoTotale = (TextView) convertView.findViewById(R.id.lblListHCarrelloPrezzoTotale);
            Button btnListModifica = (Button) convertView.findViewById(R.id.lblListHCarrelloModifica);
            Button btnListRemove = (Button) convertView.findViewById(R.id.lblListHCarrelloRemove);

            if(_context.getResources().getBoolean(R.bool.is_landscape)) {
                Button btnListAdd = (Button) convertView.findViewById(R.id.lblListHCarrelloAdd);
                btnListAdd.setFocusable(false);
                btnListAdd.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        ((Carrello) _context).addPizzaCarrello(groupPosition);
                    }
                });
            }

            btnListModifica.setFocusable(false);
            btnListRemove.setFocusable(false);

            btnListModifica.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Carrello) _context).modificaPizzaCarrello(groupPosition);
                }
            });

            btnListRemove.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    ((Carrello) _context).removePizzaCarrello(groupPosition);
                }
            });

            lblListNome.setTypeface(null, Typeface.BOLD);
            lblListPrezzoTotale.setTypeface(null, Typeface.BOLD);


            lblListPrezzoTotale.setText(Pizza.formatoPrezzo(headerTitle.getPrezzo() * headerTitle.getCount()));
            lblListNum.setText(Integer.toString(headerTitle.getCount()));

            if (headerTitle.getNomePizza().equals("creata")) {
                lblListNome.setText("La tua creazione");

            }else {

                lblListNome.setText(headerTitle.getNomePizza());
            }

            lblListPrezzo.setText(Pizza.formatoPrezzo(headerTitle.getPrezzo()));

        }

        else {// si tratta delle pizze classiche
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            TextView lblListHeaderPrezzo = (TextView) convertView.findViewById(R.id.lblListHeaderPrezzo);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeaderPrezzo.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle.getNomePizza());
            lblListHeaderPrezzo.setText(Pizza.formatoPrezzo(headerTitle.getPrezzo()));
            }
       return convertView;
   }

   @Override
   public boolean hasStableIds() {
       return true;
   }

   @Override
   public boolean isChildSelectable(int groupPosition, int childPosition) {
       return false;
   }



}
