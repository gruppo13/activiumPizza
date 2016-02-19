package it.unica.ium.pizzalove;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
* Created by perlo on 12/02/16.
*/
public class ExpandableList extends BaseExpandableListAdapter {

   private Context _context;
   List _listDataChild;
    List<String> _listDataHeader;
    Button btn1;
    Button btn2;

    public ExpandableList(Context context, List<ListaPizza> listChildData) {
        this._context = context;
        this._listDataChild = listChildData;
        this._listDataHeader = new ArrayList<>();
        int i=0;
        for(ListaPizza pizza: listChildData){
            if (pizza.getNome().equals(ListaPizza.Classica.Creata)){
                this._listDataHeader.add("La tua creazione n." + i);
            }
            else
                this._listDataHeader.add(pizza.getStringNome());

        }



    }




   @Override
   public Object getChild(int groupPosition, int childPosititon) {
       return ((ListaPizza)(this._listDataChild.get(groupPosition))).getIngredienti().get(childPosititon);
   }

   @Override
   public long getChildId(int groupPosition, int childPosition) {
       return childPosition;
   }

   @Override
   public View getChildView(int groupPosition, int childPosition,
                            boolean isLastChild, View convertView, ViewGroup parent) {

       final ListaIngrediente childText;
       while(((ListaIngrediente) getChild(groupPosition, childPosition)).getCount()==0)
             childPosition++;

       childText = (ListaIngrediente) getChild(groupPosition, childPosition);


       if (convertView == null) {
           LayoutInflater infalInflater = (LayoutInflater) this._context
                   .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = infalInflater.inflate(R.layout.list_item, null);
       }

       TextView txtListChild = (TextView) convertView
               .findViewById(R.id.lblListItem);


     //  Pizza.printAll(this._listDataChild);
       txtListChild.setText(childText.getStringNome());
/*
       Button btn = (Button)convertView.findViewById(R.id.btnAgggiungiClassica);
       Button btn2 = (Button)convertView.findViewById(R.id.btnModificaClassica);
       if (isLastChild){
           btn.setVisibility(View.VISIBLE);
           btn2.setVisibility(View.VISIBLE);
       }else
       {
           btn.setVisibility(View.GONE);
           btn2.setVisibility(View.GONE);
       }
*/
       return convertView;


   }

   @Override
   public int getChildrenCount(int groupPosition) {
       return ((ListaPizza)(this._listDataChild.get(groupPosition))).getSizeIngredienti();
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
       ListaPizza headerTitle = (ListaPizza) getGroup(groupPosition);



       if (convertView == null) {
           LayoutInflater infalInflater = (LayoutInflater) this._context
                   .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           if (headerTitle.getCount()==0)
               convertView = infalInflater.inflate(R.layout.listgroup, null);
           else
                convertView = infalInflater.inflate(R.layout.listgroup_carrello, null);
       }
        if (headerTitle.getCount()>0) {// si tratta dello scontrino

            TextView lblListNum = (TextView) convertView.findViewById(R.id.lblListHCarrelloNum);
            TextView lblListNome = (TextView) convertView.findViewById(R.id.lblListHCarrelloNome);
            TextView lblListPrezzo = (TextView) convertView.findViewById(R.id.lblListHCarrelloPrezzo);
            TextView lblListPrezzoTotale = (TextView) convertView.findViewById(R.id.lblListHCarrelloPrezzoTotale);

            lblListNome.setTypeface(null, Typeface.BOLD);
            lblListPrezzoTotale.setTypeface(null, Typeface.BOLD);


            lblListPrezzoTotale.setText(Pizza.formatoPrezzo(headerTitle.getPrezzo() * headerTitle.getCount()));
            lblListNum.setText(Integer.toString(headerTitle.getCount()));

            if (headerTitle.getNome().equals(ListaPizza.Classica.Creata)) {
                lblListNome.setText("La tua creazione");

            }else {

                lblListNome.setText(headerTitle.getStringNome());
            }

            lblListPrezzo.setText(Pizza.formatoPrezzo(headerTitle.getPrezzo()));

        }
       else {// si tratta delle pizze classiche
            TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
            TextView lblListHeaderPrezzo = (TextView) convertView.findViewById(R.id.lblListHeaderPrezzo);

            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeaderPrezzo.setTypeface(null, Typeface.BOLD);

            lblListHeader.setText(headerTitle.getStringNome());

            lblListHeaderPrezzo.setText(Pizza.formatoPrezzo(headerTitle.getPrezzo()));

            TextView quantita = (TextView)convertView.findViewById(R.id.lblListHeaderN);

            quantita.setText("");
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
