package thiagocury.eti.br.tachovendoaii.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thiagocury.eti.br.tachovendoaii.interfaces.APIRetrofitService;
import thiagocury.eti.br.tachovendoaii.R;
import thiagocury.eti.br.tachovendoaii.model.Temperatura;
import thiagocury.eti.br.tachovendoaii.helper.TemperaturaDeserializer;

public class HomeActivity extends AppCompatActivity {

    public static ArrayList<Temperatura> temperaturas;

    //TOOLBAR
    private Toolbar toolbar;

    //DRAWE
    private Drawer result = null;

    //WIDGETS
    private Button btnImagem;
    private Button btnTemperatura;
    private Button btnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Gson g = new GsonBuilder().registerTypeAdapter(
                Temperatura.class,
                new TemperaturaDeserializer()).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIRetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);

        final Call<List<Temperatura>> temperaturaCall = service.getTemperaturaCall();

        temperaturaCall.enqueue(new Callback<List<Temperatura>>() {
            @Override
            public void onResponse(Call<List<Temperatura>> call, Response<List<Temperatura>> response) {

                List<Temperatura> temperaturaAux = response.body();

                temperaturas = new ArrayList<>();

                for (Temperatura temperatura : temperaturaAux){
                    temperaturas.add(temperatura);
                }
            }

            @Override
            public void onFailure(Call<List<Temperatura>> call, Throwable t) {

                Toast.makeText(
                        getBaseContext(),
                        "Erro na conexão: "+t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header_menu)
                .addProfiles(
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener(){
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //Inicio Menu
        result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Home").withIdentifier(0).withIcon(R.drawable.home),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName("Temperaturas").withIdentifier(10).withIcon(R.drawable.temperatura),
                        new SecondaryDrawerItem().withName("Mapa").withIdentifier(20).withIcon(R.drawable.mapa),
                        new SecondaryDrawerItem().withName("Sobre").withIdentifier(30).withIcon(R.drawable.sobre)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        switch ((int)drawerItem.getIdentifier()){
                            case 0:
                                Toast.makeText(getBaseContext(),"Bem Vindo a página inicial!",Toast.LENGTH_LONG).show();
                                break;

                            case 10:
                                if(temperaturas.isEmpty()){
                                  Toast.makeText(
                                          getBaseContext(),
                                          "Fazendo download de temperaturas!",
                                          Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent it = new Intent(HomeActivity.this, TelaTemperatura.class);
                                    startActivity(it);
                                }
                                break;

                            case 20:
                                if(temperaturas.isEmpty()){
                                    Toast.makeText(
                                            getBaseContext(),
                                            "Fazendo download de temperaturas!",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent it2 = new Intent(HomeActivity.this, TelaMapa.class);
                                    startActivity(it2);
                                }
                                break;

                            case 30:
                                Intent it3 = new Intent(HomeActivity.this, TelaSobre.class);
                                startActivity(it3);
                                break;
                        }
                        return false;
                    }
                }).build();

        btnTemperatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HomeActivity.this, TelaTemperatura.class);
                startActivity(it);
            }
        });

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(HomeActivity.this, TelaMapa.class);
                startActivity(it);
            }
        });

        btnImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(HomeActivity.this, TelaSobre.class);
                startActivity(it);
            }
        });
    }

    private void initialize(){
        //referencias
        btnImagem = findViewById(R.id.ma_btn_imagem);
        btnTemperatura = findViewById(R.id.ma_btn_temperaturas);
        btnMapa = findViewById(R.id.ma_btn_mapa);
    }
}