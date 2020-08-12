package com.rms2307.minhastarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.rms2307.minhastarefas.R;
import com.rms2307.minhastarefas.helper.TarefaDAO;
import com.rms2307.minhastarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.editTarefa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSalvar:
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                String nomeTarefa = editTarefa.getText().toString();
                if (!nomeTarefa.isEmpty()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setTarefa(nomeTarefa);
                    tarefaDAO.salvar(tarefa);
                    finish(); // fechar activity
                } else {
                    Toast.makeText(this, "Digite uma tarefa!", Toast.LENGTH_LONG).show();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}