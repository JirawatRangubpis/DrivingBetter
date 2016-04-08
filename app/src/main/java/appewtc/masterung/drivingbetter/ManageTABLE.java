package appewtc.masterung.drivingbetter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 11/24/15 AD.
 */
public class ManageTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String TABLE_CAR = "carTABLE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_Id_Car = "Id_Car";
    public static final String COLUMN_Password = "Password";
    public static final String COLUMN_MileCar = "MileCar";
    public static final String COLUMN_Date = "Date";
    public static final String COLUMN_Mile = "Mile";
    public static final String COLUMN_ACT = "ACT";
    public static final String COLUMN_TAX = "TAX";
    public static final String COLUMN_Insure = "Insure";
    public static final String COLUMN_Batt = "Batt";
    public static final String COLUMN_Tire = "Tire";
    public static final String COLUMN_Engine_oil = "Engine_oil";
    public static final String COLUMN_Radiator = "Radiator";
    public static final String COLUMN_Fullservice = "Fullservice";

    public static final String TABLE_EMER = "emerTABLE";
    public static final String COLUMN_ImgService = "ImgService";
    public static final String COLUMN_TelService = "TelService";
    public static final String COLUMN_ImgInsure = "ImgInsure";
    public static final String COLUMN_TelInsure = "TelInsure";

    public static final String TABLE_FIX = "fixTABLE";
    public static final String COLUMN_Topig = "Topig";
    public static final String COLUMN_ImageFix = "ImageFix";
    public static final String COLUMN_DescripFix = "DescripFix";

    public static final String TABLE_LOGIN = "loginTABLE";
    public static final String COLUMN_ID_car_login = "ID_car_login";
    public static final String COLUMN_TimeDate = "TimeDate";
    public static final String COLUMN_Lat = "Lat";
    public static final String COLUMN_Lng = "Lng";


    public ManageTABLE(Context context) {

        //Connected Database
        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }   // Constructor

    public String[] searchIDcard(String strIDcard) {

        try {

            String[] strResult = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE_CAR,
                    new String[]{COLUMN_ID,
                            COLUMN_Id_Car,
                            COLUMN_Password,
                            COLUMN_MileCar,
                            COLUMN_Date,
                            COLUMN_Mile,
                            COLUMN_ACT,
                            COLUMN_TAX,
                            COLUMN_Insure,
                            COLUMN_Batt,
                            COLUMN_Tire,
                            COLUMN_Engine_oil,
                            COLUMN_Radiator,
                            COLUMN_Fullservice},
                    COLUMN_Id_Car + "=?",
                    new String[]{String.valueOf(strIDcard)},
                    null, null, null, null);

            if (objCursor != null) {
                if (objCursor.moveToFirst()) {

                    strResult = new String[objCursor.getColumnCount()];
                    for (int i=0;i<objCursor.getColumnCount();i++) {

                        strResult[i] = objCursor.getString(i);

                    }   //for

                }   // if2
            }   // if1

            objCursor.close();
            return strResult;

        } catch (Exception e) {
            return null;
        }

    }   // searchIDcard


    public long addValueLoginTABLE(String strID,
                                   String strTimeDate,
                                   String strLat,
                                   String strLng) {

        ContentValues objContentValues = new ContentValues();

        objContentValues.put(COLUMN_ID_car_login, strID);
        objContentValues.put(COLUMN_TimeDate, strTimeDate);
        objContentValues.put(COLUMN_Lat, strLat);
        objContentValues.put(COLUMN_Lng, strLng);

        return writeSqLiteDatabase.insert(TABLE_LOGIN, null, objContentValues);
    }

    public long addValueFixTABLE(String strTopic,
                                 String strImageFix,
                                 String strDescripFix) {

        ContentValues objContentValues = new ContentValues();

        objContentValues.put(COLUMN_Topig, strTopic);
        objContentValues.put(COLUMN_ImageFix, strImageFix);
        objContentValues.put(COLUMN_DescripFix, strDescripFix);

        return writeSqLiteDatabase.insert(TABLE_FIX, null, objContentValues);
    }


    public long addValueEmerTABLE(String strImageSer,
                                  String strTelSer,
                                  String strImageInsure,
                                  String strTelInsure) {

        ContentValues objContentValues = new ContentValues();

        objContentValues.put(COLUMN_ImgService, strImageSer);
        objContentValues.put(COLUMN_TelService, strTelSer);
        objContentValues.put(COLUMN_ImgInsure, strImageInsure);
        objContentValues.put(COLUMN_TelInsure, strTelInsure);

        return writeSqLiteDatabase.insert(TABLE_EMER, null, objContentValues);
    }

    public long addValueCarTABLE(String strIDcar,
                                 String strPass,
                                 String strMileCar,
                                 String strDate,
                                 String strMile,
                                 String strACT,
                                 String strTAX,
                                 String strInsure,
                                 String strBatt,
                                 String strTire,
                                 String strEngin,
                                 String strRadiator,
                                 String strFullService) {

        ContentValues objContentValues = new ContentValues();

        objContentValues.put(COLUMN_Id_Car, strIDcar);
        objContentValues.put(COLUMN_Password, strPass);
        objContentValues.put(COLUMN_MileCar, strMileCar);
        objContentValues.put(COLUMN_Date, strDate);
        objContentValues.put(COLUMN_Mile, strMile);
        objContentValues.put(COLUMN_ACT, strACT);
        objContentValues.put(COLUMN_TAX, strTAX);
        objContentValues.put(COLUMN_Insure, strInsure);
        objContentValues.put(COLUMN_Batt, strBatt);
        objContentValues.put(COLUMN_Tire, strTire);
        objContentValues.put(COLUMN_Engine_oil, strEngin);
        objContentValues.put(COLUMN_Radiator, strRadiator);
        objContentValues.put(COLUMN_Fullservice, strFullService);

        return writeSqLiteDatabase.insert(TABLE_CAR, null, objContentValues);
    }


}   // Main Class
