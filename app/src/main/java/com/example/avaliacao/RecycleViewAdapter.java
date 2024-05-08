package com.example.avaliacao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList id_equipa, nome_equipa, ano_campeonato;
    int position;

    RecycleViewAdapter(Act_Campeonatos activity,
                       Context context,
                       ArrayList id_equipa,
                       ArrayList nome_equipa,
                       ArrayList ano_campeonato) { //Construtor do RecycleViewAdapter
        this.activity = activity;
        this.context = context;
        this.id_equipa = id_equipa;
        this.nome_equipa = nome_equipa;
        this.ano_campeonato = ano_campeonato;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //Inflate da Recycle view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_equipa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        this.position = position;
        holder.id_equipa_txt.setText(id_equipa.get(position).toString());
        holder.nome_equipa_txt.setText(nome_equipa.get(position).toString());
        holder.ano_campeonato_txt.setText(ano_campeonato.get(position).toString());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Mostra ao usuario os dados do item clicado
                Intent intent = new Intent(context, Update.class);
                intent.putExtra("id_equipa", String.valueOf(id_equipa.get(position)));
                intent.putExtra("nome_equipa", String.valueOf(nome_equipa.get(position)));
                intent.putExtra("ano_campeonato", String.valueOf(ano_campeonato.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id_equipa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_equipa_txt, nome_equipa_txt, ano_campeonato_txt;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_equipa_txt = itemView.findViewById(R.id.edit_idequipa_itemquipa);
            nome_equipa_txt = itemView.findViewById(R.id.edit_nome_equipa);
            ano_campeonato_txt = itemView.findViewById(R.id.edit_ano_campeonato);
            mainLayout = itemView.findViewById(R.id.itemquipa);
        }
    }
}
