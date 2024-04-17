package com.example.avaliacao;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Equipa implements Parcelable {

    private String nome_equipa;

    protected Equipa(Parcel in) {
        nome_equipa = in.readString();

    }

    public Equipa(String nome) {
        this.nome_equipa = nome;
    }

    public static final Creator<Equipa> CREATOR = new Creator<Equipa>() {
        @Override
        public Equipa createFromParcel(Parcel in) {
            return new Equipa(in);
        }

        @Override
        public Equipa[] newArray(int size) {
            return new Equipa[size];
        }
    };

    public Equipa(int id, String nomeEquipas) {

    }

    //<editor-fold desc="Getter and Setter">
    public String getNome_equipa() {
        return nome_equipa;
    }

    public void setNome_equipa(String nome_equipa) {
        this.nome_equipa = nome_equipa;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nome_equipa);

    }
    //</editor-fold>


}
