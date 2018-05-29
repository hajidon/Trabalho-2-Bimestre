package com.example.renato.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.renato.myapplication.banco.Historico;
import com.example.renato.myapplication.banco.HistoricoAdapter;
import com.example.renato.myapplication.banco.HistoricoDAO;

public class DetalhesHistorico extends AppCompatActivity {
    private String TAG = "Detalhes Hisstorico";
    private HistoricoAdapter adapter;
    Toolbar toolbar;
    Historico historicoEditado = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detalhes_historico );
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar );
        //toolbar.setTitle( "Detalhes" );
        Intent intent = getIntent();

        TextView vSolicitante = (TextView)findViewById(R.id.viewSolicitante);
        TextView vHorario = (TextView)findViewById( R.id.viewHorario);
        TextView vCaixa = (TextView)findViewById( R.id.viewCaixa);
        if(intent.hasExtra("historico")){
            Log.d("msg","entrou aqui");
            historicoEditado = (Historico) intent.getSerializableExtra("historico");
            vSolicitante.setText(historicoEditado.getSolicitante());
            vHorario.setText( historicoEditado.getHorario() );
            vCaixa.setText( String.valueOf(  historicoEditado.getNumeroCaixa()) );
        }
        FloatingActionButton fbVoltar = (FloatingActionButton)findViewById( R.id.voltar );
        fbVoltar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
        FloatingActionButton fbEditar = (FloatingActionButton)findViewById( R.id.editar );
        fbEditar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HistoricoActivity.class);
                intent.putExtra("historico", historicoEditado );
                finish();
                startActivity(intent);

            }
        } );
        FloatingActionButton fbExcluir = (FloatingActionButton)findViewById( R.id.deletar );
        fbExcluir.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirmação")
                        .setMessage("Tem certeza que deseja excluir este cliente?")
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Historico historico = his.get(index);
                                HistoricoDAO dao = new HistoricoDAO(view.getContext());
                                boolean sucesso = dao.excluir(historicoEditado.getId());
                                if(sucesso) {
                                    //adapter.removerHistorico(historicoEditado);
                                    Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                    finish();
                                }else{
                                    Snackbar.make(view, "Erro ao excluir o cliente!", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create()
                        .show();
            }
            } );


    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
