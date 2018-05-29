package com.example.renato.myapplication.banco;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.renato.myapplication.DetalhesHistorico;
import com.example.renato.myapplication.R;

import java.util.List;


public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoHolder> {

    private final List<Historico> historicos;

    public HistoricoAdapter(List<Historico> historicos) {
        this.historicos = historicos;
        Log.d("MSG","ENtrou no adapter");
    }


    public void atualizarHistoricoEnviado(Historico historico) {
        //historicos.set(historicos.indexOf(historico), historico);
        //notifyItemChanged(historicos.indexOf(historico));

    }

    public void adicionarHistoricoEnviado(Historico historico) {
        historicos.add(historico);
        notifyItemInserted(getItemCount());
    }

    public void removerHistoricoEnviado(Historico historico) {
        int posicao = historicos.indexOf(historico);
        historicos.remove(posicao);
        notifyItemRemoved(posicao);
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

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull HistoricoHolder holder, int position) {
        final Historico historico = historicos.get( position );
        holder.nomeSolicitante.setText(historicos.get(position).getSolicitante());
        //holder.horario.setText( historicos.get( position ).getHorario() );
        Log.d("MSG","Entrou no onBlindView");
        holder.btnEditar.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

              editar( v,historico );
            }
        });
    }

    @Override
    public int getItemCount() {
        return historicos != null ? historicos.size() : 0;
    }

    private void editar(final View v, final Historico historico ) {

        Activity activity = getActivity(v);
        Intent intent = new Intent(activity.getBaseContext(), DetalhesHistorico.class);
        intent.putExtra("historico", historico );
        activity.startActivity(intent);
    }
    public void removerHistorico(Historico historico){
        int position = historicos.indexOf(historico);
        historicos.remove(position);
        notifyItemRemoved(position);
    }
}