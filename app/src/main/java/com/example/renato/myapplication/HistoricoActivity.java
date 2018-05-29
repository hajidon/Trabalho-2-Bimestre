package com.example.renato.myapplication;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.renato.myapplication.banco.Historico;
import com.example.renato.myapplication.banco.HistoricoAdapter;
import com.example.renato.myapplication.banco.HistoricoDAO;

public class HistoricoActivity extends AppCompatActivity {
    private HistoricoAdapter adapter;
    Historico historicoEditado = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_historico );
        Intent intent = getIntent();
        EditText txtSolicitante = (EditText)findViewById(R.id.solicitante);
        EditText txtHorario = (EditText)findViewById( R.id.horario );
        EditText txtNumeroCaixa = (EditText)findViewById( R.id.caixa);
        if(intent.hasExtra("historico")){
            Log.d("msg","entrou aqui");
              historicoEditado = (Historico) intent.getSerializableExtra("historico");
               txtSolicitante.setText(historicoEditado.getSolicitante());
               txtHorario.setText( historicoEditado.getHorario() );
               txtNumeroCaixa.setText( String.valueOf(  historicoEditado.getNumeroCaixa()) );

        }
        Button btnSalvar = (Button)findViewById(R.id.salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //carregando os campos

                EditText txtSolicitante = (EditText)findViewById(R.id.solicitante);
                EditText txtHorario = (EditText)findViewById( R.id.horario );
                EditText txtNumeroCaixa = (EditText)findViewById( R.id.caixa);

                //pegando os valores
                String solicitante = txtSolicitante.getText().toString();
                String horario = txtHorario.getText().toString();
                int numeroCaixa = Integer.parseInt( txtNumeroCaixa.getText().toString() );



                //salvando os dados
                HistoricoDAO dao = new HistoricoDAO(getBaseContext());
                boolean sucesso;
                if( historicoEditado != null) {
                    sucesso = dao.salvar(historicoEditado.getId(),solicitante, horario, numeroCaixa);
                } else
                    sucesso = dao.salvar(solicitante, horario, numeroCaixa);
                if(sucesso) {
                    Historico historico = dao.retornarUltimo();

                    if (historicoEditado != null) {
                        adapter.atualizarHistoricoEnviado( historico );
                        historicoEditado = null;
                    } else {
                        adapter.adicionarHistoricoEnviado( historico );
                        Snackbar.make(view, "Salvo com sucesso!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    txtSolicitante.setText("");
                    txtHorario.setText( "" );
                    txtNumeroCaixa.setText( "" );
                }else{
                    Snackbar.make(view, "Erro ao salvar, verifique os logs!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
    }
}
