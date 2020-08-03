package com.example.myapplication;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.dao.PositionDao;
import com.example.myapplication.model.Maps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AutoCompleteTextView actData;
    private ImageView imgSearch;
    private PositionDao positionDao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        actData = (AutoCompleteTextView) findViewById(R.id.actData);
        imgSearch = (ImageView) findViewById(R.id.imgSearch);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        positionDao = new PositionDao(this);
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

        adrMap();
        List<Maps> list = positionDao.getAllPosition();
        if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    999);

        }
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setMyLocationEnabled(true);

        try {
            String auto[] = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                auto[i] = list.get(i).diaChi;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MapsActivity.this
                    , android.R.layout.select_dialog_item, auto);

            actData.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }



        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = actData.getText().toString().trim();
                Maps maps = positionDao.getAllPositionByName(data);
                String v1 = maps.kinhDo;
                String v2 = maps.viDo;
                String title = maps.diaChi;


                // Add a marker in Sydney and move the camera
                LatLng cs1 = new LatLng(Double.parseDouble(v1), Double.parseDouble(v2));
                mMap.addMarker(new MarkerOptions().position(cs1).title(title));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(cs1));
            }

        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
    }

    private void adrMap() {
        Maps hotay = new Maps("Hồ Tây", "105.8062693", "21.0580711");
        Maps hohoankiem = new Maps("Hồ Hoàn Kiếm", "105.8501706", "21.0287797");
        Maps cs1_CDFPT = new Maps("Trường Cao đẳng thực hành FPT Polytechnic, CS1", "105.7630956", "21.0355605");
        Maps cs2_CDFPT = new Maps("Trường Cao đẳng thực hành FPT Polytechnic, CS2", "105.8017078", "21.0395869");
        Maps langbac = new Maps("Lăng chủ tịch Hồ Chí Minh", "105.8324506", "21.0367839");
        Maps timecity = new Maps("Khu đô thị Times City, Vĩnh Tuy, Hai Bà Trưng, Hà Nội", "105.8635068", "20.9943877");
        Maps rouyalcity = new Maps("Khu đô thị Royal City, Thượng Đình, Thanh Xuân, Hà Nội", "105.8130437", "21.002476");
        Maps svdMyDinh = new Maps("Sân vận động Quốc gia Mỹ Đình, Đường Lê Đức Thọ, Mỹ Đình 1, Nam Từ Liêm, Hà Nội", "105.7617706", "21.0204522");
        Maps sbNoiBai = new Maps("Sân bay Nội Bài, Sóc Sơn, Hà Nội", "105.8019768", "21.2187199");
        Maps bxMyDinh = new Maps("Bến xe Mỹ Đình, Mỹ Đình 2, Nam Từ Liêm, Hà Nội", "105.7760746", "21.0284347");
        Maps bxGiapBat = new Maps("Bến xe Giáp Bát, Giải Phóng, Hoàng Mai, Hà Nội", "105.8392603", "20.9802198");


        boolean result1 = positionDao.insertPosition(hotay);
        boolean result2 = positionDao.insertPosition(hohoankiem);
        boolean result3 = positionDao.insertPosition(cs1_CDFPT);
        boolean result4 = positionDao.insertPosition(cs2_CDFPT);
        boolean result5 = positionDao.insertPosition(langbac);
        boolean result6 = positionDao.insertPosition(timecity);
        boolean result7 = positionDao.insertPosition(rouyalcity);
        boolean result8 = positionDao.insertPosition(svdMyDinh);
        boolean result9 = positionDao.insertPosition(sbNoiBai);
        boolean result10 = positionDao.insertPosition(bxMyDinh);
        boolean result11 = positionDao.insertPosition(bxGiapBat);
    }
}
