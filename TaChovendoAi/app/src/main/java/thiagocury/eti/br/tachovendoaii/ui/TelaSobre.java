package thiagocury.eti.br.tachovendoaii.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import thiagocury.eti.br.tachovendoaii.R;

public class TelaSobre extends AppCompatActivity {

    private ImageView ivMobfeel;
    private TextView tvVersao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sobre);

        setTitle(getResources().getText(R.string.title_activity_home));

        initialize();

        String versionName="";

        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            tvVersao.setText(getResources().getText(R.string.as_tv_versao)+" "+versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        ivMobfeel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                        "http://www.mobfeel.com.br")));

            }
        });

    }

    private void initialize(){
        ivMobfeel = findViewById(R.id.as_iv_mobfeel);
        tvVersao = findViewById(R.id.as_tv_versao);
    }
}
