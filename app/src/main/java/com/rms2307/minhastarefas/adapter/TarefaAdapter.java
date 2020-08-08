package com.rms2307.minhastarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rms2307.minhastarefas.R;
import com.rms2307.minhastarefas.model.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {

    private List<Tarefa> listTarefas;

    public TarefaAdapter(List<Tarefa> listTarefas) {
        this.listTarefas = listTarefas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_tarefa, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefa tarefa = listTarefas.get(position);

        holder.tarefa.setText(tarefa.getTarefa());
        holder.data.setText(tarefa.getData());
    }

    @Override
    public int getItemCount() {
        return this.listTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tarefa;
        private TextView data;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tarefa = itemView.findViewById(R.id.textViewTarefa);
            data = itemView.findViewById(R.id.textViewData);
        }
    }
}
