package thiagocury.eti.br.tachovendoaii;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TemperaturaDetalhe extends AppCompatActivity {

    //WIDGETS
    private ImageView ivIcone;
    private TextView tvBairro;
    private TextView tvTemperaturaInterna;
    private TextView tvUmidadeInterna;
    private TextView tvTemperaturaExterna;
    private TextView tvUmidadeExterna;
    private TextView tvChuvaDiaria;
    private TextView tvPressao;
    private TextView tvVelocidadeVento;
    private TextView tvSensacaoTermica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura_detalhe);

        //Refs
        ivIcone = findViewById(R.id.td_iv_icone);
        tvBairro = findViewById(R.id.td_tv_bairro);
        tvTemperaturaInterna = findViewById(R.id.td_tv_temperaturainterna);
        tvUmidadeInterna = findViewById(R.id.td_tv_umidadeinterna);
        tvTemperaturaExterna = findViewById(R.id.td_tv_temperaturaexterna);
        tvUmidadeExterna = findViewById(R.id.td_tv_umidadeexterna);
        tvChuvaDiaria = findViewById(R.id.td_tv_chuvadiaria);
        tvPressao = findViewById(R.id.td_tv_pressao);
        tvVelocidadeVento = findViewById(R.id.td_tv_velocidadevento);
        tvSensacaoTermica = findViewById(R.id.td_tv_sensacaotermica);

        Temperatura t = null;
        t = (Temperatura) getIntent().getSerializableExtra("t");

        if(t != null){
            setTitle(getResources().getText(R.string.title_activity_tela_temperatura_detalhe)+" "+t.getBairro());
            tvBairro.setText(t.getBairro());
            tvTemperaturaExterna.setText("Temperatura Externa: "+t.getTemperaturaExterna());
            tvUmidadeExterna.setText("Umidade Externa: "+t.getTemperaturaExterna());
            tvTemperaturaInterna.setText("Temperatura Interna: "+t.getTemperaturaInterna());
            tvUmidadeInterna.setText("Umidade Interna: "+t.getUmidadeInterna());
            tvPressao.setText("Pressão: "+t.getPressao());
            tvVelocidadeVento.setText("Velocidade do Vento: "+t.getVelocidadeVento());
            tvSensacaoTermica.setText("Sensação Térmica: "+t.getSensacaoTermica());
            tvChuvaDiaria.setText("Chuva Diária: "+t.getChuvaDiaria());
            ivIcone.setImageResource(t.verificarIcone());
        }
    }
}
