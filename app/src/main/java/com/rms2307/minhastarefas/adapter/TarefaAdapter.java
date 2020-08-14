package com.rms2307.minhastarefas.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rms2307.minhastarefas.R;
import com.rms2307.minhastarefas.activity.AdicionarTarefaActivity;
import com.rms2307.minhastarefas.helper.TarefaDAO;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Tarefa tarefa = listTarefas.get(position);

        holder.tarefa.setText(tarefa.getTarefa());
        holder.data.setText(tarefa.getData());

        // Editar tarefa
        holder.tarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AdicionarTarefaActivity.class);
                intent.putExtra("tarefaSelecionada", tarefa);
                view.getContext().startActivity(intent);
            }
        });

        // Remover tarefa
        holder.tarefa.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());

                dialog.setTitle("Confirmar exclusão");
                dialog.setMessage("Deseja excluir a tarefa: " + tarefa.getTarefa() + "?");

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TarefaDAO tarefaDAO = new TarefaDAO(view.getContext());
                        if (tarefaDAO.deletar(tarefa)) {
                            listTarefas.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(view.getContext(), "Tarefa excluida!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(view.getContext(), "Erro ao excluir!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.setNegativeButton("Não", null);

                dialog.create();
                dialog.show();

                return true;
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Tarefa", tarefa.getTarefa());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tarefa;
        private TextView data;
        private CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa = itemView.findViewById(R.id.textViewTarefa);
            data = itemView.findViewById(R.id.textViewData);
            checkBox = itemView.findViewById(R.id.checkTarefaConcluida);
        }
    }
}
