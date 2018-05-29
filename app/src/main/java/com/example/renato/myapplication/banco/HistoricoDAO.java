package com.example.renato.myapplication.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class HistoricoDAO {

    private final String TABLE_HISTORICO = "Historico";
    private DbGateway gw;

    public HistoricoDAO(Context context){
        gw = DbGateway.getInstance(context);
    }


    public boolean salvar(String nome, String horario, int numeroCaixa) {

        return salvar( 0,nome,horario,numeroCaixa );

    }
    public boolean salvar(int id, String nome, String horario, int numeroCaixa){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nome", nome);
        contentValues.put("Horario", horario);
        contentValues.put("Caixa", numeroCaixa);
        if (id > 0) {
            return gw.getDatabase().update(TABLE_HISTORICO, contentValues, "ID=?", new String[]{ id + "" }) > 0;

        } else {
            return gw.getDatabase().insert(TABLE_HISTORICO, null, contentValues) > 0;
        }

    }
    public List<Historico> retornarTodos(){
        List<Historico> historicos = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM Historico", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("ID"));
            String solicitante = cursor.getString(cursor.getColumnIndex("Nome"));
            String horario = cursor.getString(cursor.getColumnIndex("Horario"));
            int numeroCaixa = Integer.parseInt( cursor.getString(cursor.getColumnIndex("Caixa")));
            historicos.add(new Historico(id, solicitante, horario, numeroCaixa));
        }
        cursor.close();
        return historicos;
    }
    public Historico retornarUltimo() {
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM " + TABLE_HISTORICO + " ORDER BY ID DESC", null);
        if (cursor.moveToFirst()) {
            Historico historico = this.getHistoricoFromCursor(cursor);
            cursor.close();
            return historico;
        }

        return null;
    }
    private Historico getHistoricoFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("ID"));
        String solicitante = cursor.getString(cursor.getColumnIndex("Nome"));
        String horario = cursor.getString(cursor.getColumnIndex("Horario"));
        int caixa = Integer.parseInt( cursor.getString(cursor.getColumnIndex("Caixa")));

        return new Historico(id, solicitante, horario, caixa);
    }
}