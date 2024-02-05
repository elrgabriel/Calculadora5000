package com.example.avaliacao;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Two#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Two extends Fragment {

  Button btnprev,btpares;
  TextView txtpares;
  ProgressBar progressBar;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Two() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Two.
     */
    // TODO: Rename and change types and number of parameters
    public static Two newInstance(String param1, String param2) {
        Two fragment = new Two();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnprev = view.findViewById(R.id.bt_prev_dois);
        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction t = getParentFragmentManager().beginTransaction();
                One one = One.newInstance("","");
                t.replace(R.id.fragmentContainerView,one);
                t.addToBackStack(null);
                t.commit();
            }
        });
        txtpares = view.findViewById(R.id.txt_pares_two);
        progressBar = view.findViewById(R.id.pb_progressbar_two);
        btpares = view.findViewById(R.id.bt_btpares_two);
        btpares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask myAsyncTask = new MyAsyncTask(Two.this);
                myAsyncTask.execute(100);
            }
        });
    }

    class MyAsyncTask extends AsyncTask<Integer,Integer,String>{

        Two two;

        public MyAsyncTask(Two t) {
            this.two=t;
        }

        @Override
        protected String doInBackground(Integer... integers) {
            int i,total,percentagem;
            for (i=0,total=0;total < integers[0];i++){
                if (Biblioteca.EPar(i)) {
                    total++;
                    percentagem = (total*100) / integers[0];
                    publishProgress(i,percentagem);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return "Fim";

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            two.progressBar.setVisibility(View.VISIBLE);
            two.progressBar.setProgress(0);
            two.txtpares.setText("Inicio");
            two.btpares.setEnabled(false);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            two.txtpares.setText(s);
            two.progressBar.setVisibility(View.INVISIBLE);
            two.btpares.setEnabled(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            two.progressBar.setProgress(values[1]);
            two.txtpares.setText(String.valueOf(values[0]));
        }
    }

}