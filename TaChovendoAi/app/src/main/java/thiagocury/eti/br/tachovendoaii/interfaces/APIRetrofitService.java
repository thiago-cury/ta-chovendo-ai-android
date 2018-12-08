package thiagocury.eti.br.tachovendoaii.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import thiagocury.eti.br.tachovendoaii.model.Temperatura;

/**
 * Created by thiagocury on 03/01/2018.
 */

public interface APIRetrofitService {
    String BASE_URL = "https://metroclimaestacoes.procempa.com.br/metroclima/seam/resource/rest/externalRest/";

    @GET("ultimaLeitura")
    Call<List<Temperatura>> getTemperaturaCall();
}
