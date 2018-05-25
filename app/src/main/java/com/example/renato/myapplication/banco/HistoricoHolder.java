package com.example.renato.myapplication.banco;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.renato.myapplication.R;

public class HistoricoHolder extends RecyclerView.ViewHolder {

    public TextView nomeSolicitante;
    public TextView horario;
    public ImageButton btnExcluir;

    public HistoricoHolder(View itemView) {
        super(itemView);
        nomeSolicitante = (TextView) itemView.findViewById( R.id.card_solicitante);
        horario = (TextView) itemView.findViewById(R.id.card_horario);
    }
}