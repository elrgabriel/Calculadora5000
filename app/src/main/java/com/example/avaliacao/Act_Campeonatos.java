package com.example.avaliacao;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class Act_Campeonatos extends AppCompatActivity {

    private ListView listViewEquipas;
    private List<Equipa> equipasList;
    private MySQL dbHelper;
    private ArrayAdapter<Equipa> equipaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_campeonato);

        dbHelper = new MySQL(this);

        listViewEquipas = findViewById(R.id.list_view_equipas);
        equipasList = new ArrayList<>();
        equipaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, equipasList);
        listViewEquipas.setAdapter(equipaAdapter);
        listViewEquipas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click here if needed
            }
        });

        loadEquipas();

        Button btnAddEquipa = findViewById(R.id.btn_add_equipa);
        btnAddEquipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement add team operation here
            }
        });

        Button btnEditEquipa = findViewById(R.id.btn_edit_equipa);
        btnEditEquipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement edit team operation here
            }
        });

        Button btnDeleteEquipa = findViewById(R.id.btn_delete_equipa);
        btnDeleteEquipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement delete team operation here
            }
        });

        Button btnAddCampeonato = findViewById(R.id.btn_add_campeonato);
        btnAddCampeonato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement add championship operation here
            }
        });

        Button btnEditCampeonato = findViewById(R.id.btn_edit_campeonato);
        btnEditCampeonato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement edit championship operation here
            }
        });

        Button btnDeleteCampeonato = findViewById(R.id.btn_delete_campeonato);
        btnDeleteCampeonato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement delete championship operation here
            }
        });
    }

    private void loadEquipas() {
        equipasList.clear();
        equipasList.addAll(dbHelper.carregaListaEquipas());
        equipaAdapter.notifyDataSetChanged();
    }
}
