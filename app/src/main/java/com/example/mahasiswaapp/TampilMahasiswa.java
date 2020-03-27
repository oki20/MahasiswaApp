package com.example.mahasiswaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TampilMahasiswa extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNim;
    private EditText editTextNama;
    private EditText editTextAlamat;
    private EditText editTextProgram;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_mahasiswa);

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.EMP_ID);

        editTextNim = findViewById(R.id.editTextNim);
        editTextNama = findViewById(R.id.editTextNama);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextProgram = findViewById(R.id.editTextProg);

        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        getMahasiswa();
    }

    private void getMahasiswa() {
        class GetMahasiswa extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilMahasiswa.this, "Fetching.....",
                        "Wait....", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showDetailMahasiswa(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_EMP, id);
                return s;
            }
        }

        GetMahasiswa gm = new GetMahasiswa();
        gm.execute();
    }

    private void showDetailMahasiswa(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nim = c.getString(konfigurasi.TAG_NIM);
            String nama = c.getString(konfigurasi.TAG_NAMA);
            String alamat = c.getString(konfigurasi.TAG_ALAMAT);
            String program = c.getString(konfigurasi.TAG_PROG_STUDI);

            editTextNim.setText(nim);
            editTextNama.setText(nama);
            editTextProgram.setText(program);
            editTextAlamat.setText(alamat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateMahasiswa(){
        final String nim = editTextNim.getText().toString().trim();
        final String nama = editTextNama.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();
        final String program = editTextProgram.getText().toString().trim();

        class UpdateMahasiswa extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilMahasiswa.this, "Updating .....",
                        "Wait ....", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilMahasiswa.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_ID, id);
                hashMap.put(konfigurasi.KEY_EMP_NIM, nim);
                hashMap.put(konfigurasi.KEY_EMP_NAMA, nama);
                hashMap.put(konfigurasi.KEY_EMP_ALAMAT, alamat);
                hashMap.put(konfigurasi.KEY_EMP_PROG_STUDI, program);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP, hashMap);
                return s;
            }
        }

        UpdateMahasiswa um = new UpdateMahasiswa();
        um.execute();
    }

    public void deleteMahasiswa(){
        class DeleteMahasiswa extends AsyncTask<Void, Void, String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilMahasiswa.this, "Delete .... ",
                        "Wait . . ...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilMahasiswa.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, id);
                return s;
            }
        }

        DeleteMahasiswa dm = new DeleteMahasiswa();
        dm.execute();
    }

    public void confirmDeleteMahasiswa(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah kamu yakin ingin menghapus data Mahasiswa Ini?");

        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                deleteMahasiswa();
                startActivity(new Intent(TampilMahasiswa.this, TampilSemuaMhs.class));
            }
        });
        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonUpdate){
            updateMahasiswa();
        }
        if (v == buttonDelete){
            confirmDeleteMahasiswa();
        }
    }
}
