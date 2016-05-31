package it.unica.ium.pizzalove;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manuf_000 on 27/05/2016.
 */
public class GeoAutoCompleteAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 10;
    private Context mContext;
    private List<GeoSearchResult> resultsList = new ArrayList();

    public GeoAutoCompleteAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount(){
        return resultsList.size();
    }

    @Override
    public GeoSearchResult getItem(int index){
        return resultsList.get(index);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.geo_search_result, parent, false);
        }
        ((TextView)convertView.findViewById(R.id.geo_search_result_text)).setText(getItem(position).getAddress());
        return convertView;
    }

    @Override
    public Filter getFilter(){
        Filter filter = new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence costraint){
                FilterResults filterResults = new FilterResults();
                if(costraint != null){
                    List location = findLocations(mContext, costraint.toString());
                    filterResults.values = location;
                    filterResults.count = location.size();
                }
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence costraint, FilterResults results){
                if(results != null && results.count > 0){
                    resultsList = (List)results.values;
                    notifyDataSetChanged();
                }
                else{
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }

    private List<GeoSearchResult> findLocations (Context context, String query_text){
        List<GeoSearchResult> geo_search_result = new ArrayList<>();
        Geocoder geocoder = new Geocoder(context, context.getResources().getConfiguration().locale);
        List<Address> addresses;

        try{
            addresses = geocoder.getFromLocationName(query_text, MAX_RESULTS);
            for(int i = 0; i<addresses.size(); i++){
                Address address = addresses.get(i);
                if(address.getMaxAddressLineIndex() != -1)
                    geo_search_result.add(new GeoSearchResult(address));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return geo_search_result;
    }
}
