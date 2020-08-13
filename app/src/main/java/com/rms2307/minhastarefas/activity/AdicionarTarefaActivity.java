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
    private Tarefa tarefaAtual = new Tarefa();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.editTarefa);

        // Recuperar tarefa, caso seja edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        // Configurar tarefa na caixa de texto
        if (tarefaAtual != null) editTarefa.setText(tarefaAtual.getTarefa());

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
                if (tarefaAtual != null) {
                    atualizar();
                } else {
                    salvar();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvar() {
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        String nomeTarefa = editTarefa.getText().toString();
        if (!nomeTarefa.isEmpty()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setTarefa(nomeTarefa);
            if (tarefaDAO.salvar(tarefa)) {
                finish(); // fechar activity
                Toast.makeText(this, "Tarefa salva!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Digite uma tarefa!", Toast.LENGTH_SHORT).show();
        }
    }

    private void atualizar() {
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        String nomeTarefa = editTarefa.getText().toString();
        if (!nomeTarefa.isEmpty()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setTarefa(nomeTarefa);
            tarefa.setId(tarefaAtual.getId());

            if (tarefaDAO.atualizar(tarefa)) {
                finish();
                Toast.makeText(this, "Tarefa atualizada!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}