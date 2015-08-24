package com.example.gq.lifeapp.view;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gq.lifeapp.R;
import com.example.gq.lifeapp.app.BaseActivity;

import java.util.List;

/**
 * Created by gq on 2015/8/23.
 */
public class MapActivity extends BaseActivity {

    private LocationManager mLocationManager;
    private String provider;
    private WebView webView;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, MapActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);
        webView = (WebView) findViewById(R.id.wv_map);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("地图");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        List<String> providerList = mLocationManager.getProviders(criteria, true);
        if (providerList.contains(LocationManager.NETWORK_PROVIDER)){
            provider = LocationManager.NETWORK_PROVIDER;
        } else if (providerList.contains(LocationManager.GPS_PROVIDER)){
            provider = LocationManager.GPS_PROVIDER;
        } else {
            Toast.makeText(this, R.string.location, Toast.LENGTH_SHORT).show();
            return;
        }
        Location mLocation = mLocationManager.getLastKnownLocation(provider);
        if (mLocation != null){
            showLocation(mLocation);
        }
        mLocationManager.requestLocationUpdates(provider, 5 * 1000, 5, mLocationListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationManager!=null){
            mLocationManager.removeUpdates(mLocationListener);
        }
    }

    LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void showLocation(Location mLocation) {
        String mapURL, longitude, latitude;

        longitude = String.valueOf(mLocation.getLongitude());
        latitude = String.valueOf(mLocation.getLatitude());
        mapURL = "http://m.amap.com/navi/?dest="+longitude+","+latitude+"&destName=我在这儿&hideRouteIcon=1&key=5bfe6d9bd868ed669fdf7e7848939154";


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(mapURL);

    }
}
