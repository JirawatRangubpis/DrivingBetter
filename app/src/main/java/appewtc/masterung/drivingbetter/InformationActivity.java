package appewtc.masterung.drivingbetter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Calendar;

public class InformationActivity extends AppCompatActivity {

    //Explicit
    private TextView idCarTextView, currentMileTextView,
    ACTextView, ACTnextTextView,
    taxTextView, taxnextTextView,
    insureTextView, insureNextTextView,
    battTextView, battNextTextView,
    tireTextView, tireNextTextView,
    engineOilTextView, engineOilnextTextView;

    private String idCarString, currentMileString,
            ACTString, ACTnextString,
            taxString, taxnextString,
            insureString, insureNextString,
            battString, battNextString,
            tireString, tireNextString,
            engineOilString, engineOilnextString;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //Bind Widget
        bindWidget();

        // Show View

        showView();

        createNextTimeAlert();

    }    // Main Method

    private void createNextTimeAlert() {

        Calendar currentCalendar = Calendar.getInstance();



        Calendar[] alertCalendar = new Calendar[4];
        String[] alertTime = new String[4];
        alertTime[0] = ACTnextTextView.getText().toString();
        alertTime[1] = taxnextTextView.getText().toString();
        alertTime[2] = insureNextTextView.getText().toString();
        alertTime[3] = battNextTextView.getText().toString();



        for (int i = 0; i < 4; i++) {

            alertCalendar[i] = (Calendar) currentCalendar.clone();
            alertCalendar[i].set(Calendar.DAY_OF_MONTH, timeOfMonth(alertTime[i], 0));
            alertCalendar[i].set(Calendar.MONTH, timeOfMonth(alertTime[i], 1) - 1);
            alertCalendar[i].set(Calendar.YEAR, timeOfMonth(alertTime[i], 2));
            alertCalendar[i].set(Calendar.HOUR_OF_DAY, 8); /// Alert 8โมงเช้า
            alertCalendar[i].set(Calendar.MINUTE, 0); /// Alert minute
            alertCalendar[i].set(Calendar.SECOND, 0); /// Alert second
            alertCalendar[i].set(Calendar.MILLISECOND, 0);

            Intent objIntent = new Intent(getBaseContext(), AlarmReceiver.class);
            PendingIntent objPendingIntent = PendingIntent.getBroadcast(getBaseContext(), 1, objIntent, 0);
            AlarmManager objAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            objAlarmManager.set(AlarmManager.RTC_WAKEUP, alertCalendar[i].getTimeInMillis(), objPendingIntent);


        } // for


    } // createNextTimeAlert

    private int timeOfMonth(String strDate, int intIndex) {

        int intResult = 0;

        String[] resultStrings = strDate.split("/");

        intResult = Integer.parseInt(resultStrings[intIndex]);

        return intResult;
    }

    private void showView() {

        //Receive id from Intent
        String strID = getIntent().getStringExtra("id");
        int intID = Integer.parseInt(strID);

        // Get Value from Database
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor objCursor = objSqLiteDatabase.rawQuery("SELECT * FROM carTABLE", null);
        objCursor.moveToFirst();
        objCursor.moveToPosition(intID - 1);
        Log.d("car", "idCar = " + objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Id_Car)));

        idCarString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Id_Car));
        currentMileString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_MileCar));
        ACTString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_ACT));
        taxString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_TAX));
        insureString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Insure));
        battString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Batt));
        tireString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Tire));
        engineOilString = objCursor.getString(objCursor.getColumnIndex(ManageTABLE.COLUMN_Engine_oil));

        //Show at TextView
        idCarTextView.setText(idCarString);
        currentMileTextView.setText(currentMileString);
        ACTextView.setText(ACTString);
        taxTextView.setText(taxString);
        insureTextView.setText(insureString);
        battTextView.setText(battString);
        tireTextView.setText(tireString);
        engineOilTextView.setText(engineOilString);

        // Split String
        String[] ACTStrings = ACTString.split("/");
        for (int i = 0; i < ACTStrings.length; i++) {
            Log.d("car", "ACTStrings[" + Integer.toString(i) + "] = " + ACTStrings[i]);
        }   // for

        // Increase Year
        int intYear = Integer.parseInt(ACTStrings[2]);
        intYear += 1;



        ACTnextString = ACTStrings[0] + "/" + ACTStrings[1] + "/" + Integer.toString(intYear);
        ACTnextTextView.setText(ACTnextString);

        String[] taxStrings = taxString.split("/");
        int intYearTax = Integer.parseInt(taxStrings[2]) + 1;
        taxnextTextView.setText(taxStrings[0] + "/" + taxStrings[1] + "/" + Integer.toString(intYearTax));

        String[] insureStrings = insureString.split("/");
        int intYearInsure = Integer.parseInt(insureStrings[2]) + 1;
        insureNextTextView.setText(insureStrings[0] + "/" + insureStrings[1] + "/" + Integer.toString(intYearInsure));

        String[] battStrings = battString.split("/");
        int intYearBatt = Integer.parseInt(battStrings[2]) + 2;
        battNextTextView.setText(battStrings[0] + "/" + battStrings[1] + "/" + Integer.toString(intYearBatt));

        String[] tireStrings = tireString.split("/");
        int intYearTire = Integer.parseInt(tireStrings[2]) + 2;
        tireNextTextView.setText(tireStrings[0] + "/" + tireStrings[1] + "/" + Integer.toString(intYearTire));

        int intEngineOil = Integer.parseInt(engineOilString) + 10000;
        engineOilnextTextView.setText(Integer.toString(intEngineOil));

    } // show view

    private void bindWidget() {

        idCarTextView = (TextView) findViewById(R.id.textView18);
        currentMileTextView = (TextView) findViewById(R.id.textView20);
        ACTextView = (TextView) findViewById(R.id.textView22);
        ACTnextTextView = (TextView) findViewById(R.id.textView33);
        taxTextView = (TextView) findViewById(R.id.textView24);
        taxnextTextView = (TextView) findViewById(R.id.textView34);
        insureTextView = (TextView) findViewById(R.id.textView26);
        insureNextTextView = (TextView) findViewById(R.id.textView35);
        battTextView = (TextView) findViewById(R.id.textView28);
        battNextTextView = (TextView) findViewById(R.id.textView36);
        tireTextView = (TextView) findViewById(R.id.textView30);
        tireNextTextView = (TextView) findViewById(R.id.textView37);
        engineOilTextView = (TextView) findViewById(R.id.textView32);
        engineOilnextTextView = (TextView) findViewById(R.id.textView38);

    } // bind Widget

}   // Main Class
