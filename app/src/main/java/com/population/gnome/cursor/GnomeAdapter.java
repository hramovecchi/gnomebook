package com.population.gnome.cursor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.population.gnome.R;
import com.population.gnome.dto.GnomeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hramovecchi on 29/1/2018.
 */

public class GnomeAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<GnomeDTO> gnomeList;
    private List<GnomeDTO> gnomeFilterList;
    private GnomeFilter filter;
    private LayoutInflater inflater;

    public GnomeAdapter(Context context, List<GnomeDTO> gnomeList){
        this.context = context;
        this.gnomeList = gnomeList;
        gnomeFilterList = gnomeList;

    }
    @Override
    public int getCount() {
        return gnomeList.size();
    }

    @Override
    public Object getItem(int i) {
        return gnomeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        ImageView img;
        TextView name;
        TextView profession;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_list_row, parent,false);

            holder = new ViewHolder();
            holder.img = (ImageView)convertView.findViewById(R.id.image);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.profession = (TextView)convertView.findViewById(R.id.profession);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GnomeDTO gnome = (GnomeDTO) getItem(position);
        Glide.with(context).load(gnome.getThumbnail()).apply(RequestOptions.circleCropTransform()).into(holder.img);
        holder.name.setText(gnome.getName());
        holder.profession.setText(gnome.getProfessions().toString().substring(1, gnome.getProfessions().toString().length()-1));

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new GnomeFilter();
        }
        return filter;
    }

    private class GnomeFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();

            if (charSequence != null && charSequence.length() > 0){
                ArrayList<GnomeDTO> filterList = new ArrayList<GnomeDTO>();
                for (int i = 0; i < gnomeFilterList.size(); i++) {
                    GnomeDTO gnome = gnomeFilterList.get(i);
                    if (nameCriteria(charSequence, gnome) || professionCriteria(charSequence, gnome)) {
                        filterList.add(gnome);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = gnomeFilterList.size();
                results.values = gnomeFilterList;
            }
            return results;
        }

        private boolean professionCriteria(CharSequence charSequence, GnomeDTO gnome) {
            String[] requestedProfessions = charSequence.toString().split(",");
            int criteriaMatches = 0;
            for (String reqProfession: requestedProfessions){
                reqProfession = reqProfession.replaceAll("\\s","");
                String gnomeProfessions = gnome.getProfessions().toString().replaceAll("\\s", "");
                if (gnomeProfessions.toUpperCase().contains(reqProfession.toUpperCase())){
                    criteriaMatches++;
                }
            }
            return criteriaMatches == requestedProfessions.length;
        }

        private boolean nameCriteria(CharSequence charSequence, GnomeDTO gnome) {
            return gnome.getName().toUpperCase().contains(charSequence.toString().toUpperCase());
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            gnomeList = (ArrayList<GnomeDTO>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
