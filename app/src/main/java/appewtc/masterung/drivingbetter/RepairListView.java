package appewtc.masterung.drivingbetter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class RepairListView extends AppCompatActivity {

    // Explicit
    private ListView repairListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair_list_view);

        // Bind Widget
        repairListView = (ListView) findViewById(R.id.listView);

        // Create ListView
        createListView();

    }   // Main Method

    public void clickBackRepairListView(View view) {
        finish();
    }

    private void createListView() {

        // Get Value From SQLite
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM fixTABLE", null);
        objCursor.moveToFirst();
        int intLoop = objCursor.getCount();
        final String[] topicStrings = new String[intLoop];
        final String[] imageFixStrings = new String[intLoop];
        final String[] descripFixStrings = new String[intLoop];

        for (int i = 0; i < intLoop; i++) {

            topicStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Topig));
            imageFixStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_ImageFix));
            descripFixStrings[i] = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_DescripFix));

            objCursor.moveToNext();

        }   // For
        objCursor.close();

        // Create ListView
        RepairAdapter objRepairAdapter = new RepairAdapter(RepairListView.this, topicStrings, imageFixStrings);
        repairListView.setAdapter(objRepairAdapter);

        // Active Click ListView
        repairListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent objIntent = new Intent(RepairListView.this, DetailRepairActivity.class);
                objIntent.putExtra("Title", topicStrings[i]);
                objIntent.putExtra("Image", imageFixStrings[i]);
                objIntent.putExtra("Detail", descripFixStrings[i]);
                startActivity(objIntent);

            }   // Event
        });


    }   // Create ListView

}   // Main Class
