package com.example.renato.myapplication.banco;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.renato.myapplication.R;

import java.util.List;


public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoHolder> {

    private final List<Historico> historicos;

    public HistoricoAdapter(List<Historico> historicos) {
        this.historicos = historicos;
        Log.d("MSG","ENtrou no adapter");
    }


    @NonNull
    @Override
    public HistoricoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MSG","Entrou no HistoricoHolder");
        try {
            return new HistoricoHolder( LayoutInflater.from(parent.getContext())
                    .inflate( R.layout.card_layout, parent, false));
        } catch (Exception e) {
            Log.d( "erro","Deu erro no inflarCard" );
        }

        return new HistoricoHolder( LayoutInflater.from(parent.getContext())
                .inflate( R.layout.card_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricoHolder holder, int position) {

        holder.nomeSolicitante.setText(historicos.get(position).getSolicitante());
        holder.horario.setText( historicos.get( position ).getHorario() );
        Log.d("MSG","ENtrou no onBlindView");
    }

    @Override
    public int getItemCount() {
        return historicos != null ? historicos.size() : 0;
    }
}