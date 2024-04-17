package com.example.avaliacao;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Campeonatos implements Parcelable {
    int ano_campeonato;

    protected Campeonatos(Parcel in) {
        ano_campeonato = in.readInt();
    }

    public Campeonatos(int ano_campeonato) {
        this.ano_campeonato = ano_campeonato;
    }

    public Campeonatos(int equipeId, int anoCampeonato) {
    }

    public int getAno_campeonato() {
        return ano_campeonato;
    }

    public void setAno_campeonato(int ano_campeonato) {
        this.ano_campeonato = ano_campeonato;
    }

    public static final Creator<Campeonatos> CREATOR = new Creator<Campeonatos>() {
        @Override
        public Campeonatos createFromParcel(Parcel in) {
            return new Campeonatos(in);
        }

        @Override
        public Campeonatos[] newArray(int size) {
            return new Campeonatos[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(ano_campeonato);
    }
}
