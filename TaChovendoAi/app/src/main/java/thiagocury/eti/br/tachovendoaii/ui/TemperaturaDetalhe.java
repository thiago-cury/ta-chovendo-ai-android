package thiagocury.eti.br.tachovendoaii.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import thiagocury.eti.br.tachovendoaii.R;
import thiagocury.eti.br.tachovendoaii.helper.Padronizacao;
import thiagocury.eti.br.tachovendoaii.model.Temperatura;

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

        initialize();

        Temperatura t = null;
        t = (Temperatura) getIntent().getSerializableExtra("t");

        if(t != null){
            setTitle(getResources().getText(R.string.title_activity_tela_temperatura_detalhe)+" "+t.getBairro());
            tvBairro.setText(t.getBairro());

            if (t.getTemperaturaInterna() == null || t.getTemperaturaInterna().isEmpty()) {
                tvTemperaturaInterna.setText("");
                tvTemperaturaInterna.setVisibility(View.GONE);
            } else {
                tvTemperaturaInterna.setText("Temperatura Interna: " + Padronizacao.converterTemperatura(Double.parseDouble(t.getTemperaturaInterna())) + "˚C");
            }

            if (t.getUmidadeInterna() == null || t.getUmidadeInterna().isEmpty()) {
                tvUmidadeInterna.setText("");
                tvUmidadeInterna.setVisibility(View.GONE);
            } else {
                tvUmidadeInterna.setText("Umidade Interna: " + t.getUmidadeInterna() + "%");
            }

            if (t.getTemperaturaExterna() == null || t.getTemperaturaExterna().isEmpty()) {
                tvTemperaturaExterna.setText("");
                tvTemperaturaExterna.setVisibility(View.GONE);
            } else {
                tvTemperaturaExterna.setText("Temperatura Externa: " + Padronizacao.converterTemperatura(Double.parseDouble(t.getTemperaturaExterna())) + "˚C");
            }

            if (t.getUmidadeExterna() == null || t.getUmidadeExterna().isEmpty()) {
                tvUmidadeExterna.setText("");
                tvUmidadeExterna.setVisibility(View.GONE);
            } else {
                tvUmidadeExterna.setText("Umidade Externa: " + Padronizacao.converterTemperatura(Double.parseDouble(t.getUmidadeExterna())) + "%");
            }

            if (t.getChuvaDiaria() == null || t.getChuvaDiaria().isEmpty()) {
                tvChuvaDiaria.setText("");
                tvChuvaDiaria.setVisibility(View.GONE);
            } else {
                tvChuvaDiaria.setText("Chuva Diária: " + t.getChuvaDiaria() + "ml");
            }

            if (t.getVelocidadeVento() == null || t.getVelocidadeVento().isEmpty()) {
                tvVelocidadeVento.setText("");
                tvVelocidadeVento.setVisibility(View.GONE);
            } else {
                tvVelocidadeVento.setText("Velocidade do Vento: " + t.getVelocidadeVento() + "Km");
            }

            if (t.getSensacaoTermica() == null || t.getSensacaoTermica().isEmpty()) {
                tvSensacaoTermica.setText("");
                tvSensacaoTermica.setVisibility(View.GONE);
            } else {
                tvSensacaoTermica.setText("Sensação Térmica: " + Padronizacao.converterTemperatura(Double.parseDouble(t.getSensacaoTermica())) +"˚C");
            }

            if (t.getPressao() == null || t.getPressao().isEmpty()) {
                tvPressao.setText("");
                tvPressao.setVisibility(View.GONE);
            } else {
                tvPressao.setText("Pressão: " + t.getPressao());
            }

            ivIcone.setImageResource(t.verificarIcone());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void initialize(){
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
    }
}
