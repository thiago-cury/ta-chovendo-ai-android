package thiagocury.eti.br.tachovendoaii;

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

        rvTemperatura = findViewById(R.id.tt_rv_temperatura);
        spBairro = findViewById(R.id.tt_sp_bairro);

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
                Temperatura t = new Temperatura();
                Toast.makeText(getBaseContext(),"A previsão do tempo é: "+t.getIconePrevisao(),Toast.LENGTH_LONG);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Temperatura t = new Temperatura();
                Toast.makeText(getBaseContext(),"Latitude: "+t.getLatitude()+", Longitude: "+t.getLongitude(),Toast.LENGTH_LONG);
            }
        });

    }
}


/*
        adapter = new ClimaAdapter(TelaClima.this,new ArrayList<Clima>(0));
        rvClima.setAdapter(adapter);
        rvClima.setHasFixedSize(true);
        rvClima.setLayoutManager(new LinearLayoutManager(this));

        //GSON
        Gson g = new GsonBuilder().registerTypeAdapter(Clima.class, new ClimaDeserialize()).create();

        //RETROFIT2
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIRetrofitService.BASE_URL).addConverterFactory(GsonConverterFactory.create(g)).build();

        //APIRETROFITSERVICE
        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        Call<List<Clima>> climaCall = service.getClimaCall();

        climaCall.enqueue(new Callback<List<Clima>>() {
            @Override
            public void onResponse(Call<List<Clima>> call, Response<List<Clima>> response) {

                List<Clima> climaAux = response.body();

                climas = new ArrayList<>();

                for (Clima clima : climaAux){
                    climas.add(clima);
                    arrayBairro.add(clima.getBairro());
                }

                 Setando a lista de produtos proveniente da internet
                adapter.setClimas(climas);
                 Notificando o adapter sobre o novo array
                        adapter.notifyDataSetChanged();
                        }

public void onFailure(Call<List<Clima>> call, Throwable t) {
        Toast.makeText(getBaseContext(), "Erro na conexão!"+t.getMessage(), Toast.LENGTH_LONG).show();
        }//fecha onFailure
        });

        adapter.setOnItemClickListener(new ClimaAdapter.ClickListener() {
@Override
public void onItemClick(int position, View v) {

        }//fechaOnItemClick
@Override
public void onItemLongClick(int position, View v) {
        }//fecha onItemLongClick
        });

        //header
        AccountHeader headerResult = new AccountHeaderBuilder().withActivity(this).withHeaderBackground(R.drawable.menu).addProfiles().withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener(){
@Override
public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
        return false;
        }
        })
        .build();

        //Menu
        result = new DrawerBuilder().withActivity(this).withTranslucentStatusBar(false).withToolbar(toolbar).withAccountHeader(headerResult).withSavedInstance(savedInstanceState).addDrawerItems(
        new PrimaryDrawerItem().withName("Home").withIdentifier(0).withIcon(R.drawable.ic_home_black_18dp),
        new DividerDrawerItem(),
        new SecondaryDrawerItem().withName("Clima").withIdentifier(1).withIcon(R.drawable.ic_cloud_queue_black_18dp),
        new DividerDrawerItem(),
        new SecondaryDrawerItem().withName("Sobre").withIdentifier(2).withIcon(R.drawable.ic_info_outline_black_18dp)
        )
        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
@Override
public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

        switch ((int)drawerItem.getIdentifier()){
        case 0:
        Intent it = new Intent(TelaClima.this,MainActivity.class);
        startActivity(it);
        break;

        case 1:
        break;

        case 2:
        Intent it2 = new Intent(TelaClima.this,TelaSobre.class);
        startActivity(it2);
        break;
        }
        return false;
        }
        }).build();

        }//fecha onCreate
        }//fecha Class
        */
