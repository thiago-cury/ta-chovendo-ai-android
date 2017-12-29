package thiagocury.eti.br.tachovendoaii;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by QI on 29/12/2017.
 */

public interface APIRetrofitService {
    String BASE_URL = "https://metroclimaestacoes.procempa.com.br/metroclima/seam/resource/rest/externalRest/";

    @GET("ultimaLeitura")
    Call<List<Temperatura>> getTemperaturaCall();
}
