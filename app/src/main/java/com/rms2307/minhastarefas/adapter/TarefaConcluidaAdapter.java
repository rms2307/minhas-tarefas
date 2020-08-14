package com.rms2307.minhastarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rms2307.minhastarefas.R;

public class TarefaConcluidaAdapter extends RecyclerView.Adapter<TarefaConcluidaAdapter.MyViewHolder> {


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_tarefa_concluida, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tarefaConcluida.setText("Tarefa 01");
        holder.dataConcluida.setText("20/10/2020");
    }

    @Override
    public int getItemCount() {
        return 10;
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
