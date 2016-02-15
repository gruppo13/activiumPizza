package it.unica.ium.pizzalove;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* Created by perlo on 12/02/16.
*/
public class ExpandableList extends BaseExpandableListAdapter {

   private Context _context;
   //private List<> _listDataHeader; // header titles

    /*private HashMap<Pizza.Classica,Integer> repeatPizza;
   // child data in format of header title, child title
   private HashMap<Pizza.Classica, List<Pizza.Ingrediente>> _listDataChild;



   public ExpandableList(Context context, HashMap<Pizza.Classica, List<Pizza.Ingrediente>> listChildData) {
       this._context = context;
       this._listDataHeader = new ArrayList<>();
       this._listDataHeader.addAll(listChildData.keySet());
       this.repeatPizza = new HashMap<>();

       this._listDataChild = listChildData;

   }
*/
    List _listDataChild;

    public ExpandableList(Context context, List<ListaPizza> listChildData) {
        this._context = context;
        this._listDataChild = listChildData;

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
   public View getChildView(int groupPosition, final int childPosition,
                            boolean isLastChild, View convertView, ViewGroup parent) {

       final ListaIngrediente childText = (ListaIngrediente) getChild(groupPosition, childPosition);


       if (convertView == null) {
           LayoutInflater infalInflater = (LayoutInflater) this._context
                   .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = infalInflater.inflate(R.layout.list_item, null);
       }

       TextView txtListChild = (TextView) convertView
               .findViewById(R.id.lblListItem);

       txtListChild.setText(childText.getStringNome());

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
     /*  for (int i=0 i<getGroupCount();i++){
           if (groupPosition != 0) && parent.
       }*/


       if (convertView == null) {
           //System.out.println("sei dentro");
           LayoutInflater infalInflater = (LayoutInflater) this._context
                   .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = infalInflater.inflate(R.layout.listgroup, null);
       }

       TextView lblListHeader = (TextView) convertView
               .findViewById(R.id.lblListHeader);
       TextView lblListHeaderPrezzo = (TextView) convertView.findViewById(R.id.lblListHeaderPrezzo);

       lblListHeader.setTypeface(null, Typeface.BOLD);
       lblListHeaderPrezzo.setTypeface(null, Typeface.BOLD);



       String prezzoString;
       if (headerTitle.getNome().equals(ListaPizza.Classica.Creata)) {
           lblListHeader.setText("La tua creazione");
       }else {
            lblListHeader.setText(headerTitle.getStringNome());
       }

       lblListHeaderPrezzo.setText(Pizza.formatoPrezzo(headerTitle.getPrezzo()));

       return convertView;
   }

   @Override
   public boolean hasStableIds() {
       return false;
   }

   @Override
   public boolean isChildSelectable(int groupPosition, int childPosition) {
       return true;
   }




}
