package com.rms2307.minhastarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rms2307.minhastarefas.R;
import com.rms2307.minhastarefas.helper.TarefaDAO;
import com.rms2307.minhastarefas.model.Tarefa;

import java.util.List;

public class TarefaConcluidaAdapter extends RecyclerView.Adapter<TarefaConcluidaAdapter.MyViewHolder> {

    private List<Tarefa> tarefas;
    private RecyclerView recyclerView;

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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Tarefa tarefa = tarefas.get(position);

        holder.tarefaConcluida.setText(tarefa.getTarefa());
        holder.dataConcluida.setText(tarefa.getData());

        holder.checkBoxConcluida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TarefaDAO tarefaDAO = new TarefaDAO(view.getContext());
                if(!holder.checkBoxConcluida.isChecked()){
                    tarefa.setStatus("P");
                    tarefas.remove(position);
                    notifyItemRemoved(position);
                    tarefaDAO.atualizar(tarefa);
                }
            }
        });
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
