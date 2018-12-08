package thiagocury.eti.br.tachovendoaii.helper;

import java.text.DecimalFormat;

/**
 * Created by thiagocury on 03/01/2018.
 */

public class Padronizacao {

    public static String converterTemperatura(double temp){
        DecimalFormat format = new DecimalFormat("0.##");
        return format.format(temp);
    }
}
