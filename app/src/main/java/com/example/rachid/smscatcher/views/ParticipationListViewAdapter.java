package com.example.rachid.smscatcher.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rachid.smscatcher.R;
import com.example.rachid.smscatcher.models.ParticipationDto;

import java.util.List;

/**
 * Created by rachid on 28/03/17.
 */

public class ParticipationListViewAdapter extends ArrayAdapter<ParticipationDto> {

    public ParticipationListViewAdapter(Context context, List<ParticipationDto> participationDtos) {
        super(context, 0, participationDtos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_participation,parent, false);
        }
        ParticipationViewHolder viewHolder = (ParticipationViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ParticipationViewHolder();
            viewHolder.numero = (TextView) convertView.findViewById(R.id.numero);
            viewHolder.nom = (TextView) convertView.findViewById(R.id.nom);
            viewHolder.prenom = (TextView) convertView.findViewById(R.id.prenom);
            viewHolder.boisson = (TextView) convertView.findViewById(R.id.boisson);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            convertView.setTag(viewHolder);
        }
        ParticipationDto participationDto = getItem(position);
        viewHolder.numero.setText(""+ participationDto.getNumero());
        viewHolder.nom.setText(participationDto.getNom());
        viewHolder.prenom.setText(participationDto.getPrenom());
        viewHolder.boisson.setText(participationDto.getBoisson());
        viewHolder.date.setText(participationDto.getDate());
        return convertView;
    }

    private class ParticipationViewHolder{
        public TextView numero;
        public TextView nom;
        public TextView prenom;
        public TextView boisson;
        public TextView date;
    }
}
