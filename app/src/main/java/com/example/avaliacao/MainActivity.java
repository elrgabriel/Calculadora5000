package com.example.avaliacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar_toolbar_main);
        setSupportActionBar(toolbar);
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        Fragment one = One.newInstance("","");
        t.add(R.id.fragmentContainerView,one);
        t.addToBackStack(null);
        t.commit(); //Aqui é executado.

    }

    @SuppressLint("RestrictedApi")

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Listener
        ((MenuBuilder)menu).setOptionalIconsVisible(true);
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Handler
        int id=item.getItemId();
        if (id==R.id.mnu_calculadora_mainmenu){
            Intent it = new Intent(MainActivity.this,Act_Calculadora.class);
            startActivity(it);
            Toast.makeText(this, "Calculadora", Toast.LENGTH_LONG).show();
        }
        if (id==R.id.mnu_selos_selos){
            FragmentTransaction t = getSupportFragmentManager().beginTransaction();
            Three frgtthree = Three.newInstance("","");
            t.replace(R.id.fragmentContainerView,frgtthree);
            t.addToBackStack(null);
            t.commit(); //Aqui é executado.
            Toast.makeText(this, "Selos", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}