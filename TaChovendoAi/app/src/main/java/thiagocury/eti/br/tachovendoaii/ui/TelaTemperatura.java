package thiagocury.eti.br.tachovendoaii.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import thiagocury.eti.br.tachovendoaii.R;
import thiagocury.eti.br.tachovendoaii.model.Temperatura;
import thiagocury.eti.br.tachovendoaii.adapters.TemperaturaAdapter;

public class TelaTemperatura extends AppCompatActivity {

    private Spinner spBairro;
    private RecyclerView rvTemperatura;
    private ArrayList<Temperatura> temperaturas;
    private TemperaturaAdapter adapter;
    private ArrayAdapter<String> adapterBairro;

    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_temperatura);

        setTitle(getResources().getText(R.string.title_activity_tela_temperatura));

        initialize();

        temperaturas = HomeActivity.temperaturas;

        adapter = new TemperaturaAdapter(TelaTemperatura.this, temperaturas);
        rvTemperatura.setAdapter(adapter);
        rvTemperatura.setHasFixedSize(true);
        rvTemperatura.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<String> arrayBairro = new ArrayList<>();
        arrayBairro.add("Ver todos");

        for(int i=0 ; i<temperaturas.size() ; i++){
            arrayBairro.add(temperaturas.get(i).getBairro());
        }

        adapterBairro = new ArrayAdapter<String>(
                TelaTemperatura.this,
                android.R.layout.simple_dropdown_item_1line,
                arrayBairro);

        spBairro.setAdapter(adapterBairro);

        spBairro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    ArrayList<Temperatura> tempsSpinner = new ArrayList<>();
                    tempsSpinner.add(temperaturas.get(position-1));
                    adapter.setTemperaturas(tempsSpinner);
                    adapter.notifyDataSetChanged();
                    flag = true;
                }else if(flag){
                    adapter.setTemperaturas(temperaturas);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter.setOnItemClickListener(new TemperaturaAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Temperatura t = temperaturas.get(position);
                Intent it = new Intent(TelaTemperatura.this, TemperaturaDetalhe.class);
                it.putExtra("t", t);
                startActivity(it);
            }

            @Override
            public void onItemLongClick(int position, View v) {
            }
        });
    }

    private void initialize(){
        rvTemperatura = findViewById(R.id.tt_rv_temperatura);
        spBairro = findViewById(R.id.tt_sp_bairro);
    }
}