package appewtc.masterung.drivingbetter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Camera;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // Explicit
    private GoogleMap mMap;
    private double centerLatADouble = 13.66852671;
    private double centerLngADouble = 100.6053257;
    private LatLng centerLatLng;
    private double[] makerLatDoubles, makerLngDoubles;
    private LatLng[] makerLatLngs;
    private  String[] carIDStrings, dateCarStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Read All SQLite
        readAllSQLite();



    }   // Main Method

    private void readAllSQLite() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM " + ManageTABLE.TABLE_LOGIN, null);
        int intCount = objCursor.getCount();
        makerLatDoubles = new double[intCount];
        makerLngDoubles = new double[intCount];
        makerLatLngs = new LatLng[intCount];
        carIDStrings = new String[intCount];
        dateCarStrings = new String[intCount];

        objCursor.moveToFirst();
        for (int i = 0; i < intCount; i++) {

            makerLatDoubles[i] = Double.parseDouble(objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Lat)));
            makerLngDoubles[i] = Double.parseDouble(objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Lng)));
            makerLatLngs[i] = new LatLng(makerLatDoubles[i], makerLngDoubles[i]);
            carIDStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_ID_car_login));
            dateCarStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_TimeDate));

            objCursor.moveToNext();

        } // for
        objCursor.close();

    } // readAllSQLite


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Create Map On Center
        createMapOnCenter();

        // Marker Car
        markerCar();

    }   // onMapReady

    private void markerCar() {

        for (int i = 0; i < makerLatLngs.length; i++) {

            mMap.addMarker(new MarkerOptions().position(makerLatLngs[i]).title(carIDStrings[i])
                    .snippet(dateCarStrings[i]));

        } // for

    } //markerCar

    private void createMapOnCenter() {

        centerLatLng = new LatLng(centerLatADouble, centerLngADouble);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 16));

    }

}   // Main Class
