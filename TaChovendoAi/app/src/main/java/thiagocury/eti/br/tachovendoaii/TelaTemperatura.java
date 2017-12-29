package thiagocury.eti.br.tachovendoaii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaTemperatura extends AppCompatActivity {

    private RecyclerView rvTemperatura;
    private ArrayList<Temperatura> temperaturas;
    private TemperaturaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_temperatura);

        setTitle(getResources().getText(R.string.title_activity_tela_temperatura));

        rvTemperatura = findViewById(R.id.tt_rv_temperatura);

        temperaturas = HomeActivity.temperaturas;

        adapter = new TemperaturaAdapter(TelaTemperatura.this, temperaturas);
        rvTemperatura.setAdapter(adapter);
        rvTemperatura.setHasFixedSize(true);
        rvTemperatura.setLayoutManager(new LinearLayoutManager(this));

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

//CODIGO COM O SPINNER ABAIXO

/*package thiagocury.eti.br.poatime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Callback;

public class TelaClima extends AppCompatActivity {

    //WIDGETS
    private Toolbar toolbar;
    private ImageView imgClima;
    private Spinner spBairro;
    private RecyclerView rvClima;
    private ArrayList<Clima>climas;
    private ClimaAdapter adapter;

    //Drawer
    private Drawer result = null;

    private boolean flag = false;

    private ArrayAdapter<String> adapterBairro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_clima);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //REFS
        imgClima = findViewById(R.id.fc_img_clima);
        spBairro = findViewById(R.id.spinner);
        rvClima = findViewById(R.id.fc_rv_clima);

        final ArrayList<String> arrayBairro = new ArrayList<>();
        arrayBairro.add("Ver todos");

        adapterBairro = new ArrayAdapter<String>(TelaClima.this, android.R.layout.simple_dropdown_item_1line, arrayBairro);

        spBairro.setAdapter(adapterBairro);

        spBairro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0){
                    ArrayList<Clima> climasSpinner = new ArrayList<>();
                    climasSpinner.add(climas.get(position-1));
                    adapter.setClimas(climasSpinner);
                    adapter.notifyDataSetChanged();
                    flag = true;
                }else if(flag){
                    adapter.setClimas(climas);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
