package appewtc.masterung.drivingbetter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class ServiceCenterActivity extends AppCompatActivity {

    // Explicit
    private TextView titleTextView;
    private ListView serviceListView;
    private int indexAnInt;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_center);

        // Bind Widget
        bindWidget();

        // Show Title
        showTitle();

        // Create ListView
        createListView();

    }   // Main Method

    public void clickBackServiceCenter(View view) {
        finish();
    }

    private void createListView() {

        // Get Value From EmerTABLE
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM EmerTABLE", null);
        objCursor.moveToFirst();
        int intLoop = objCursor.getCount();
        String[] serviceStrings = new String[intLoop];
        String[] telServiceStrings = new String[intLoop];
        String[] insureString = new String[intLoop];
        String[] telInsureStrings = new String[intLoop];

        for (int i = 0; i < intLoop; i++) {

            serviceStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_ImgService));
            telServiceStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_TelService));
            insureString[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_ImgInsure));
            telInsureStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_TelInsure));

            objCursor.moveToNext();

        }   // for

        objCursor.close();

        switch (indexAnInt) {
            case 1:
                RepairAdapter objRepairAdapter = new RepairAdapter(ServiceCenterActivity.this, telServiceStrings,
                        serviceStrings);
                serviceListView.setAdapter(objRepairAdapter);
                break;

            case 2:
                RepairAdapter obj2RepairAdapter = new RepairAdapter(ServiceCenterActivity.this, telInsureStrings, insureString);
                serviceListView.setAdapter(obj2RepairAdapter);
                break;

        }   // Switch



    }   // createListView

    private void showTitle() {

        indexAnInt = getIntent().getIntExtra("Index", 1);
        String strTitle = getResources().getString(R.string.title1);
        String strTitle2 = getResources().getString(R.string.title2);

        switch (indexAnInt) {

            case 1:
                titleTextView.setText(strTitle);
                break;
            case 2:
                titleTextView.setText(strTitle2);
                break;
        }   // Switch

    }   // Show Title

    private void bindWidget() {

        titleTextView = (TextView) findViewById(R.id.textView43);
        serviceListView = (ListView) findViewById(R.id.listView2);


    }
}   // Main Class
