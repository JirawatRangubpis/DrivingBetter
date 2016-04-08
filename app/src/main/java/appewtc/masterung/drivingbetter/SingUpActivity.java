package appewtc.masterung.drivingbetter;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SingUpActivity extends AppCompatActivity {

    //Explicit
    private EditText idCarEditText, passwordEditText, MileCarEditText,
            actEditText, taxEditText, insureEditText, battEditText,
            tireEditText, engineOilEditText;

    private String idCarString, passwordString, MileCarString, MileString,
            actString, taxString, insureString, battString,
            tireString, engineOilString, radiatorString, fullserviceString,
            dateString, provinceString;

    private TextView dateTextView;
    private Spinner provinceSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        //Bind Widget
        bindWidget();

        //Get & Show Time
        getAndShowTime();

        //Create Spinner
        createSpinner();

    }   // Main Method

    private void createSpinner() {

        final String[] strProvinceArray = getResources().getStringArray(R.array.province);
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strProvinceArray);
        provinceSpinner.setAdapter(objAdapter);

        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                provinceString = strProvinceArray[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                provinceString = strProvinceArray[0];
            }
        });

    }   // createSpinner

    private void getAndShowTime() {

        DateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        dateString = myDateFormat.format(currentDate);
        dateTextView.setText(dateString);

    }   // getAndShowTime

    public void clickSaveData(View view) {

        idCarString = idCarEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();
        MileCarString = MileCarEditText.getText().toString().trim();
        actString = actEditText.getText().toString().trim();
        taxString = taxEditText.getText().toString().trim();
        insureString = insureEditText.getText().toString().trim();
        battString = battEditText.getText().toString().trim();
        tireString = tireEditText.getText().toString().trim();
        engineOilString = engineOilEditText.getText().toString().trim();

        //Check Space
        if (idCarString.equals("") ||
                passwordString.equals("") ||
                MileCarString.equals("") ||
                actString.equals("") ||
                taxString.equals("") ||
                insureString.equals("") ||
                battString.equals("") ||
                tireString.equals("") ||
                engineOilString.equals("")) {

            //Have Space
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(SingUpActivity.this, "มีช่องว่าง", "กรุณากรอกทุกช่อง ครับ");

        } else {

            //No Space
            checkIDcar();

        }


    }   // clickSaveData

    private void checkIDcar() {

        try {

            //Alert Have This IDcar on my Database
            ManageTABLE objManageTABLE = new ManageTABLE(this);
            String[] strResult = objManageTABLE.searchIDcard(idCarString);

            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.errorDialog(SingUpActivity.this, "Error IDcar", "มี หมายเลยทะเบียน " + strResult[1] + " ใน ฐานข้อมูลของเราแล้ว ให้ใช้ หมายเลข ทะเบียนอื่น");

        } catch (Exception e) {
            //Confirm to mySQL
            confirmToMySQL();

        }

    }   // checkIDcar

    private void confirmToMySQL() {

        //Setup New Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        try {

//            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
//            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
//            objNameValuePairs.add(new BasicNameValuePair("Id_Car", idCarString));
//            objNameValuePairs.add(new BasicNameValuePair("Password", passwordString));
//            objNameValuePairs.add(new BasicNameValuePair("MileCar", MileCarString));
//            objNameValuePairs.add(new BasicNameValuePair("Date", dateString));
//            objNameValuePairs.add(new BasicNameValuePair("Mile", MileString));
//            objNameValuePairs.add(new BasicNameValuePair("ACT", actString));
//            objNameValuePairs.add(new BasicNameValuePair("TAX", taxString));
//            objNameValuePairs.add(new BasicNameValuePair("Insure", insureString));
//            objNameValuePairs.add(new BasicNameValuePair("Batt", battString));
//            objNameValuePairs.add(new BasicNameValuePair("Tire", tireString));
//            objNameValuePairs.add(new BasicNameValuePair("Engine_oil", engineOilString));
//            objNameValuePairs.add(new BasicNameValuePair("Radiator", radiatorString));
//            objNameValuePairs.add(new BasicNameValuePair("Fullservice", fullserviceString));
//
//            HttpClient objHttpClient = new DefaultHttpClient();
//            //HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/car/php_add_data_master.php");
//            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/car/php_add_data_gps.php");
//            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
//            objHttpClient.execute(objHttpPost);

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            objNameValuePairs.add(new BasicNameValuePair("Officer", "masterTest123"));
            objNameValuePairs.add(new BasicNameValuePair("Desk", "masterTest"));
            objNameValuePairs.add(new BasicNameValuePair("Food", "masterTest"));
            objNameValuePairs.add(new BasicNameValuePair("Item", "masterTest"));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/12dec/php_add_data.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);




            Toast.makeText(SingUpActivity.this, "Update New Value Successful", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(SingUpActivity.this, "ไม่สามารถ เชื่อมต่อ Server ได้", Toast.LENGTH_SHORT).show();
        }

    }   // confirmToMySQL


    private void bindWidget() {

        idCarEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        MileCarEditText = (EditText) findViewById(R.id.editText5);
        actEditText = (EditText) findViewById(R.id.editText6);
        taxEditText = (EditText) findViewById(R.id.editText7);
        insureEditText = (EditText) findViewById(R.id.editText8);
        battEditText = (EditText) findViewById(R.id.editText9);
        tireEditText = (EditText) findViewById(R.id.editText10);
        engineOilEditText = (EditText) findViewById(R.id.editText11);
        dateTextView = (TextView) findViewById(R.id.textView16);
        provinceSpinner = (Spinner) findViewById(R.id.spinner);


    }   // bindWidget


}   // Main Class
