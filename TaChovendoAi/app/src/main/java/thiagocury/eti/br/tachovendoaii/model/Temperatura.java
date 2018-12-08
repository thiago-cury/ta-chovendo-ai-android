package thiagocury.eti.br.tachovendoaii.model;

import android.util.Log;

import java.io.Serializable;

import thiagocury.eti.br.tachovendoaii.R;

/**
 * Created by thiagocury on 03/01/2018.
 */

public class Temperatura implements Serializable {

    private String bairro;
    private String latitude;
    private String longitude;
    private String chuvaDiaria;
    private String temperaturaExterna;
    private String sensacaoTermica;
    private String iconePrevisao;
    private String temperaturaInterna;
    private String umidadeInterna;
    private String pressao;
    private String velocidadeVento;
    private String umidadeExterna;

    public Temperatura() {
    }

    public Temperatura(String bairro, String latitude, String longitude, String chuvaDiaria, String temperaturaExterna, String sensacaoTermica, String iconePrevisao, String temperaturaInterna, String umidadeInterna, String pressao, String velocidadeVento, String umidadeExterna) {
        this.bairro = bairro;
        this.latitude = latitude;
        this.longitude = longitude;
        this.chuvaDiaria = chuvaDiaria;
        this.temperaturaExterna = temperaturaExterna;
        this.sensacaoTermica = sensacaoTermica;
        this.iconePrevisao = iconePrevisao;
        this.temperaturaInterna = temperaturaInterna;
        this.umidadeInterna = umidadeInterna;
        this.pressao = pressao;
        this.velocidadeVento = velocidadeVento;
        this.umidadeExterna = umidadeExterna;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getChuvaDiaria() {
        return chuvaDiaria;
    }

    public void setChuvaDiaria(String chuvaDiaria) {
        this.chuvaDiaria = chuvaDiaria;
    }

    public String getTemperaturaExterna() {
        return temperaturaExterna;
    }

    public String getTemperaturaInterna() {
        return temperaturaInterna;
    }

    public void setTemperaturaInterna(String temperaturaInterna) {
        this.temperaturaInterna = temperaturaInterna;
    }

    public String getUmidadeInterna() {
        return umidadeInterna;
    }

    public void setUmidadeInterna(String umidadeInterna) {
        this.umidadeInterna = umidadeInterna;
    }

    public String getPressao() {
        return pressao;
    }

    public void setPressao(String pressao) {
        this.pressao = pressao;
    }

    public String getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(String velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public String getUmidadeExterna() {
        return umidadeExterna;
    }

    public void setUmidadeExterna(String umidadeExterna) {
        this.umidadeExterna = umidadeExterna;
    }

    public void setTemperaturaExterna(String temperaturaExterna) {
        this.temperaturaExterna = temperaturaExterna;
    }

    public String getSensacaoTermica() {
        return sensacaoTermica;
    }

    public void setSensacaoTermica(String sensacaoTermica) {
        this.sensacaoTermica = sensacaoTermica;
    }

    public String getIconePrevisao() {
        return iconePrevisao;
    }

    public void setIconePrevisao(String iconePrevisao) {
        this.iconePrevisao = iconePrevisao;
    }

    public int verificarIcone() {
        if(iconePrevisao != null) {
            if (iconePrevisao.equals("claro")) {
                return R.mipmap.ic_claro;
            } else if (iconePrevisao.equals("chuva")) {
                return R.mipmap.ic_chuva;
            } else if (iconePrevisao.equals("parcial")) {
                return R.mipmap.ic_parcial;
            } else if (iconePrevisao.equals("solechuva")) {
                return R.mipmap.ic_solechuva;
            }
        }
        return R.mipmap.ic_crash;
    }

    @Override
    public String toString() {
        return "Temperatura{" +
                "bairro='" + bairro + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", chuvaDiaria='" + chuvaDiaria + '\'' +
                ", temperaturaExterna='" + temperaturaExterna + '\'' +
                ", sensacaoTermica='" + sensacaoTermica + '\'' +
                ", iconePrevisao='" + iconePrevisao + '\'' +
                ", temperaturaInterna='" + temperaturaInterna + '\'' +
                ", umidadeInterna='" + umidadeInterna + '\'' +
                ", pressao='" + pressao + '\'' +
                ", velocidadeVento='" + velocidadeVento + '\'' +
                ", umidadeExterna='" + umidadeExterna + '\'' +
                '}';
    }
}
