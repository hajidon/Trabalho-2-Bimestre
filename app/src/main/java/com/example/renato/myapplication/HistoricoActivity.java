package com.example.renato.myapplication;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.renato.myapplication.banco.HistoricoDAO;

public class HistoricoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_historico );
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
                boolean sucesso = dao.salvar(solicitante, horario, numeroCaixa);
                if(sucesso) {
                    //limpa os campos
                    txtSolicitante.setText("");
                    txtHorario.setText( "" );
                    txtNumeroCaixa.setText( "" );
                    Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else{
                    Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}
