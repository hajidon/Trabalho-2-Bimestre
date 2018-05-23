package com.example.renato.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Renato on 20/05/2018.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.HistoricoViewHolder>{

    private final List<Historico> historico;

    RVAdapter(List<Historico> historico){
        this.historico = historico;
    }
    @NonNull
    @Override
    public HistoricoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        HistoricoViewHolder pvh = new HistoricoViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricoViewHolder holder, int position) {

        holder.solicitante.setText( historico.get(position).solicitante);
        holder.horario.setText( historico.get(position).horario);
        holder.cv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        } );
       // holder.personPhoto.setImageResource( people.get(position).photoId);
    }

    @Override
    public int getItemCount() {
        return historico.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class HistoricoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView solicitante;
        TextView horario;
        HistoricoViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            solicitante = (TextView)itemView.findViewById(R.id.solicitante);
            horario = (TextView)itemView.findViewById(R.id.horario);
        }
    }


}
