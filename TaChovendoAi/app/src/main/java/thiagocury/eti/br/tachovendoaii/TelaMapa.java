package thiagocury.eti.br.tachovendoaii;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class TelaMapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<Temperatura> temperaturas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        temperaturas = HomeActivity.temperaturas;

        if(!temperaturas.isEmpty()) {
            for (int i = 0; i < temperaturas.size(); i++) {
                mMap.addMarker(
                        new MarkerOptions().position(
                                new LatLng(Float.parseFloat(temperaturas.get(i).getLatitude()), Float.parseFloat(temperaturas.get(i).getLongitude()))).
                                title(temperaturas.get(i).getBairro()).
                                snippet(temperaturas.get(i).getBairro()).
                                icon(BitmapDescriptorFactory.fromResource(temperaturas.get(i).verificarIcone())));
            }
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Temperatura t = null;
                for (int i = 0; i < temperaturas.size(); i++) {
                    if (marker.getTitle().equalsIgnoreCase(temperaturas.get(i).getBairro())){
                        t = temperaturas.get(i);
                    }
                }

                Intent it = new Intent(TelaMapa.this, TemperaturaDetalhe.class);
                it.putExtra("t",t);
                startActivity(it);

                return true;
            }
        });

        //Mostrando localização atual no mapa
        LatLng latLng = new LatLng(-30.0883982400, -51.1751859520);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //Movimentando o zoom da camera no map
        mMap.moveCamera(CameraUpdateFactory.zoomTo(11));
    }
}
