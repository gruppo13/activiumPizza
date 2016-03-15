package it.unica.ium.pizzalove;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
* Created by perlo on 12/02/16.
*/
public class ExpandableList extends BaseExpandableListAdapter {

    private Context _context;
    private List<Pizza> _listDataChild;
    private List<String> _listDataHeader;
    Button btn1;
    Button btn2;

    public ExpandableList(Context context, List<Pizza> listChildData) {
        this._context = context;
        this._listDataChild = listChildData;
        this._listDataHeader = new ArrayList<>();
        int i=0;
        for(Pizza pizza: listChildData){
            if (pizza.getNomePizza().equals("creata")){
                this._listDataHeader.add("La tua creazione n." + i);
                i++;
            }
            else {
                this._listDataHeader.add(pizza.getNomePizza());
            }

        }
    }


   @Override
   public Object getChild(int groupPosition, int childPosititon) {
       return this._listDataChild.get(groupPosition).getIngredienti().get(childPosititon);
   }

   @Override
   public long getChildId(int groupPosition, int childPosition) {
       return childPosition;
   }

   @Override
   public View getChildView(int groupPosition, int childPosition,
                            boolean isLastChild, View convertView, ViewGroup parent) {

       final Ingredienti childText;
       while(((Ingredienti) getChild(groupPosition, childPosition)).getNumber()==0)
             childPosition++;

       childText = (Ingredienti) getChild(groupPosition, childPosition);


       if (convertView == null) {
           LayoutInflater infalInflater = (LayoutInflater) this._context
                   .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = infalInflater.inflate(R.layout.list_item, null);
       }

       TextView txtListChild = (TextView) convertView
               .findViewById(R.id.lblListItem);

       txtListChild.setText(childText.toString());
       return convertView;
   }

   @Override
   public int getChildrenCount(int groupPosition) {
       return this._listDataChild.get(groupPosition).countIngredienti();
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
   public View getGroupView(int groupPosition, boolean isExpanded,
                            View convertView, ViewGroup parent) {
       Pizza headerTitle = (Pizza) getGroup(groupPosition);



       if (convertView == null) {
           Log.d(_context.getClass().toString(), "1");
           LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           if (!(_context instanceof Carrello)){
               Log.d(_context.getClass().toString(), "2");
               convertView = infalInflater.inflate(R.layout.listgroup, null);}
           else
           {
               Log.d(_context.getClass().toString(), "3");
               convertView = infalInflater.inflate(R.layout.listgroup_carrello, null);}
       }
        if (_context instanceof Carrello) {//scontrino
            Log.d(_context.getClass().toString(), "4");
            TextView lblListNum = (TextView) convertView.findViewById(R.id.lblListHCarrelloNum);
            TextView lblListNome = (TextView) convertView.findViewById(R.id.lblListHCarrelloNome);
            TextView lblListPrezzo = (TextView) convertView.findViewById(R.id.lblListHCarrelloPrezzo);
            TextView lblListPrezzoTotale = (TextView) convertView.findViewById(R.id.lblListHCarrelloPrezzoTotale);

            lblListNome.setTypeface(null, Typeface.BOLD);
            lblListPrezzoTotale.setTypeface(null, Typeface.BOLD);


            lblListPrezzoTotale.setText(Ingredienti.formatoPrezzo(headerTitle.getPrezzo() * headerTitle.getCount()));
            lblListNum.setText(Integer.toString(headerTitle.getCount()));

            if (headerTitle.getNomePizza().equals("creata")) {
                lblListNome.setText("La tua creazione");

            }else {

                lblListNome.setText(headerTitle.getNomePizza());
            }

            lblListPrezzo.setText(Ingredienti.formatoPrezzo(headerTitle.getPrezzo()));

        }
       else {// si tratta delle pizze classiche
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            TextView lblListHeaderPrezzo = (TextView) convertView.findViewById(R.id.lblListHeaderPrezzo);

            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeaderPrezzo.setTypeface(null, Typeface.BOLD);

            lblListHeader.setText(headerTitle.getNomePizza());

            lblListHeaderPrezzo.setText(Ingredienti.formatoPrezzo(headerTitle.getPrezzo()));
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
