package com.rms2307.minhastarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rms2307.minhastarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        escreve = dbHelper.getWritableDatabase();
        le = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getTarefa());
        cv.put("status", tarefa.getStatus());
        try {
            escreve.insert(DBHelper.TABELA_TAREFAS, null, cv);
            Log.i("INFO", "Tarefa salva com sucesso");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao salvar tarefa: " + e.getMessage());
            return false;
        }
        return true;

    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getTarefa());
        cv.put("status", tarefa.getStatus());
        String[] args = {tarefa.getId().toString()};

        try {
            escreve.update(DBHelper.TABELA_TAREFAS, cv, "id=?", args);
            Log.i("INFO", "Tarefa atualizada com sucesso");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao atualizar tarefa: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        String[] args = {tarefa.getId().toString()};

        try {
            escreve.delete(DBHelper.TABELA_TAREFAS, "id=?", args);
            Log.i("INFO", "Tarefa excluida com sucesso");
        } catch (Exception e) {
            Log.e("INFO", "Erro ao excluir tarefa: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TABELA_TAREFAS + ";";
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()) {
            Tarefa tarefa = new Tarefa();

            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));
            String status = c.getString(c.getColumnIndex("status"));

            tarefa.setId(id);
            tarefa.setTarefa(nomeTarefa);
            tarefa.setStatus(status);

            tarefas.add(tarefa);
        }
        return tarefas;
    }

    public List<Tarefa> listarTarefasPendentes() {
        List<Tarefa> tarefas = listar();
        List<Tarefa> tarefasPendentes = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            if(tarefa.getStatus().equals("P")){
                tarefasPendentes.add(tarefa);
            }
        }
        return tarefasPendentes;
    }

    public List<Tarefa> listarTarefasConcluidas() {
        List<Tarefa> tarefas = listar();
        List<Tarefa> tarefasConcluidas = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            if(tarefa.getStatus().equals("C")){
                tarefasConcluidas.add(tarefa);
            }
        }
        return tarefasConcluidas;
    }
}
