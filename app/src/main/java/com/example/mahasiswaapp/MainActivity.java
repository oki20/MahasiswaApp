package com.example.mahasiswaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNim;
    private EditText editTextNama;
    private EditText editTextAlamat;
    private EditText editTextProg;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNim = findViewById(R.id.editTextNim);
        editTextNama = findViewById(R.id.editTextName);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextProg = findViewById(R.id.editTextProg);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonView = findViewById(R.id.buttonView);

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAdd){
            addMahasiswa();
        }
        if (v == buttonView){
            startActivity(new Intent(this, TampilSemuaMhs.class));
        }
    }

    private void addMahasiswa(){
        final String nim = editTextNim.getText().toString().trim();
        final String nama = editTextNama.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        final String prog = editTextProg.getText().toString().trim();

        class AddMahasiswa extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Menambahkan .... ",
                        "Tunggu...", false,
                        false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NIM, nim);
                params.put(konfigurasi.KEY_EMP_NAMA, nama);
                params.put(konfigurasi.KEY_EMP_ALAMAT, alamat);
                params.put(konfigurasi.KEY_EMP_PROG_STUDI, prog);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return null;
            }
        }

        AddMahasiswa am = new AddMahasiswa();
        am.execute();
    }
}
