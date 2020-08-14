package com.rms2307.minhastarefas.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rms2307.minhastarefas.R;
import com.rms2307.minhastarefas.adapter.TarefaAdapter;
import com.rms2307.minhastarefas.helper.TarefaDAO;
import com.rms2307.minhastarefas.model.Tarefa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerTarefas, recyclerTarefasConcluidas;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listTarefas = new ArrayList<>();
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar recyclers
        recyclerTarefas = findViewById(R.id.recyclerListaTarefas);
        recyclerTarefasConcluidas = findViewById(R.id.recyclerViewTarefasConcluidas);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdicionarTarefaActivity.class);
                startActivity(intent);
            }
        });

    }

    public void carregarListaTarefas() {

       recuperarListaTarefasBancoDados();

        // Configurar um adapter
        tarefaAdapter = new TarefaAdapter(listTarefas);

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerTarefas.setLayoutManager(layoutManager);
        recyclerTarefas.setHasFixedSize(true);
        recyclerTarefas.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerTarefas.setAdapter(tarefaAdapter);
    }

    public void recuperarListaTarefasBancoDados(){
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listTarefas = tarefaDAO.listar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarListaTarefas();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}