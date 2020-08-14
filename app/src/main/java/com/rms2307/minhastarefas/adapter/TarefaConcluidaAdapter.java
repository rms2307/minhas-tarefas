package com.rms2307.minhastarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rms2307.minhastarefas.R;
import com.rms2307.minhastarefas.model.Tarefa;

import java.util.List;

public class TarefaConcluidaAdapter extends RecyclerView.Adapter<TarefaConcluidaAdapter.MyViewHolder> {

    private List<Tarefa> tarefas;

    public TarefaConcluidaAdapter(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_tarefa_concluida, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefa tarefa = tarefas.get(position);

        holder.tarefaConcluida.setText(tarefa.getTarefa());
        holder.dataConcluida.setText(tarefa.getData());
    }

    @Override
    public int getItemCount() {

        return tarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tarefaConcluida;
        private TextView dataConcluida;
        private CheckBox checkBoxConcluida;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefaConcluida = itemView.findViewById(R.id.textViewTarefaConcluida);
            dataConcluida = itemView.findViewById(R.id.textViewDataConcluida);
            checkBoxConcluida = itemView.findViewById(R.id.checkTarefaConcluida);
        }
    }
}
