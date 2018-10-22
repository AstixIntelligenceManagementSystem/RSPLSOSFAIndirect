package project.astix.com.rsplsosfaindirect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.astix.Common.CommonInfo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by User on 20-Sep-18.
 */

public class SurveyActivityTwo extends AppCompatActivity implements InterfaceClass{
    TextView tv_quest1,tv_quest2,tv_quest3,tv_quest4,tv_quest5,tv_quest6;
    RadioButton rb_quest1_yes,rb_quest1_no,rb_quest2_yes,rb_quest2_no,rb_quest3_yes,rb_quest3_no,rb_quest4_yes,
            rb_quest4_no,rb_quest5_yes,rb_quest5_no;
    EditText et_Comment;
    String StoreID;
    ImageView backIcon;
    Button btn_submit;
    public String userDate;
    public String pickerDate;
    public String imei;
    public LocationManager locationManager;
    DBAdapterKenya dbengine = new DBAdapterKenya(SurveyActivityTwo.this);
    DatabaseAssistanceSurvey DA=new DatabaseAssistanceSurvey(this);
    public String newfullFileName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_activity_two);
        locationManager=(LocationManager) this.getSystemService(LOCATION_SERVICE);
        getIntentData();
        intializeAllView();

    }
    public void intializeAllView(){
        tv_quest1=(TextView) findViewById(R.id.tv_quest1);
        tv_quest2=(TextView) findViewById(R.id.tv_quest2);
        tv_quest3=(TextView) findViewById(R.id.tv_quest3);
        tv_quest4=(TextView) findViewById(R.id.tv_quest4);
        tv_quest5=(TextView) findViewById(R.id.tv_quest5);
        tv_quest6=(TextView) findViewById(R.id.tv_quest6);

        rb_quest1_yes=(RadioButton) findViewById(R.id.rb_quest1_yes);
        rb_quest1_no=(RadioButton) findViewById(R.id.rb_quest1_no);
        rb_quest2_yes=(RadioButton) findViewById(R.id.rb_quest2_yes);
        rb_quest2_no=(RadioButton) findViewById(R.id.rb_quest2_no);
        rb_quest3_yes=(RadioButton) findViewById(R.id.rb_quest3_yes);
        rb_quest3_no=(RadioButton) findViewById(R.id.rb_quest3_no);
        rb_quest4_yes=(RadioButton) findViewById(R.id.rb_quest4_yes);
        rb_quest4_no=(RadioButton) findViewById(R.id.rb_quest4_no);
        rb_quest5_yes=(RadioButton) findViewById(R.id.rb_quest5_yes);
        rb_quest5_no=(RadioButton) findViewById(R.id.rb_quest5_no);
// we want to show first question ony by default
        tv_quest2.setVisibility(View.GONE);
        rb_quest2_yes.setVisibility(View.GONE);
        rb_quest2_no.setVisibility(View.GONE);
        tv_quest3.setVisibility(View.GONE);
        rb_quest3_yes.setVisibility(View.GONE);
        rb_quest3_no.setVisibility(View.GONE);
        tv_quest4.setVisibility(View.GONE);
        rb_quest4_yes.setVisibility(View.GONE);
        rb_quest4_no.setVisibility(View.GONE);
        tv_quest5.setVisibility(View.GONE);
        rb_quest5_yes.setVisibility(View.GONE);
        rb_quest5_no.setVisibility(View.GONE);

        tv_quest1.setTag("5");
        tv_quest2.setTag("6");
        tv_quest3.setTag("7");
        tv_quest4.setTag("8");
        tv_quest5.setTag("9");
        tv_quest6.setTag("10");

        rb_quest1_yes.setTag("12");
        rb_quest1_no.setTag("13");
        rb_quest2_yes.setTag("14");
        rb_quest2_no.setTag("15");
        rb_quest3_yes.setTag("16");
        rb_quest3_no.setTag("17");
        rb_quest4_yes.setTag("18");
        rb_quest4_no.setTag("19");
        rb_quest5_yes.setTag("21");
        rb_quest5_no.setTag("21");




        et_Comment=(EditText) findViewById(R.id.et_Comment);


        rb_quest1_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_quest1_yes.isChecked()){
                    rb_quest1_no.setChecked(false);
                }
                visibilityVisible();
            }
        });
        rb_quest1_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_quest1_no.isChecked()){
                    rb_quest1_yes.setChecked(false);
                }
                visibilityGone();
                LastQuestVisible();
            }
        });
        rb_quest2_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_quest2_yes.isChecked()){
                    rb_quest2_no.setChecked(false);
                }
            }
        });
        rb_quest2_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_quest2_no.isChecked()){
                    rb_quest2_yes.setChecked(false);
                }
            }
        });
        rb_quest3_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_quest2_yes.isChecked()){
                    rb_quest3_no.setChecked(false);
                }
            }
        });
        rb_quest3_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_quest3_no.isChecked()){
                    rb_quest3_yes.setChecked(false);
                }
            }
        });
        rb_quest4_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_quest4_yes.isChecked()){
                    rb_quest4_no.setChecked(false);
                }
            }
        });
        rb_quest4_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_quest4_no.isChecked()){
                    rb_quest4_yes.setChecked(false);
                }
            }
        });
        rb_quest5_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_quest5_yes.isChecked()){
                    rb_quest5_no.setChecked(false);
                }
            }
        });
        rb_quest5_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb_quest5_no.isChecked()){
                    rb_quest5_yes.setChecked(false);
                }
            }
        });
        btn_submit=(Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    if(isOnline()){
                        boolean isGPSok = false;
                        boolean isNWok=false;

                        isGPSok = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                        isNWok = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                        if(!isGPSok)
                        {
                            isGPSok = false;
                        }
                        if(!isNWok)
                        {
                            isNWok = false;
                        }
                        if(!isGPSok && !isNWok)
                        {
                            try
                            {
                                showSettingsAlert();
                            }
                            catch(Exception e)
                            {

                            }

                            isGPSok = false;
                            isNWok=false;
                        }
                        else {
                            LocationRetreivingGlobal llaaa=new LocationRetreivingGlobal();
                            llaaa.locationRetrievingAndDistanceCalculating(SurveyActivityTwo.this,false,50);

                        }
                    }
                    else{
                        showNoConnAlert();
                    }

                }

            }
        });
        backIcon=(ImageView) findViewById(R.id.backIcon);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent     intent=new Intent(SurveyActivityTwo.this,SurveyStoreList.class);
                intent.putExtra("imei", imei);
                intent.putExtra("userDate", userDate);
                intent.putExtra("pickerDate", pickerDate);
                startActivity(intent);
                finish();
            }
        });

    }
    public void visibilityGone(){
        tv_quest2.setVisibility(View.GONE);
        rb_quest2_yes.setVisibility(View.GONE);
        rb_quest2_no.setVisibility(View.GONE);
        tv_quest3.setVisibility(View.GONE);
        rb_quest3_yes.setVisibility(View.GONE);
        rb_quest3_no.setVisibility(View.GONE);
        tv_quest4.setVisibility(View.GONE);
        rb_quest4_yes.setVisibility(View.GONE);
        rb_quest4_no.setVisibility(View.GONE);
        /*tv_quest5.setVisibility(View.VISIBLE);
        rb_quest5_yes.setVisibility(View.VISIBLE);
        rb_quest5_no.setVisibility(View.VISIBLE);*/
    }
    public void visibilityVisible(){
        tv_quest2.setVisibility(View.VISIBLE);
        rb_quest2_yes.setVisibility(View.VISIBLE);
        rb_quest2_no.setVisibility(View.VISIBLE);
        tv_quest3.setVisibility(View.VISIBLE);
        rb_quest3_yes.setVisibility(View.VISIBLE);
        rb_quest3_no.setVisibility(View.VISIBLE);
        tv_quest4.setVisibility(View.VISIBLE);
        rb_quest4_yes.setVisibility(View.VISIBLE);
        rb_quest4_no.setVisibility(View.VISIBLE);
        tv_quest5.setVisibility(View.VISIBLE);
        rb_quest5_yes.setVisibility(View.VISIBLE);
        rb_quest5_no.setVisibility(View.VISIBLE);
    }
    public void LastQuestVisible(){

        tv_quest5.setVisibility(View.VISIBLE);
        rb_quest5_yes.setVisibility(View.VISIBLE);
        rb_quest5_no.setVisibility(View.VISIBLE);
    }
    public boolean validate(){
        if((!rb_quest1_yes.isChecked()) && (!rb_quest1_no.isChecked())){
            showAlertSingleButtonError("Please select option for question: "+ tv_quest1.getText());
            return false;
        }
      else  if((!rb_quest2_yes.isChecked()) && (!rb_quest2_no.isChecked()) && (tv_quest2.getVisibility()==View.VISIBLE)){
            showAlertSingleButtonError("Please select option for question: "+ tv_quest2.getText());
            return false;
        }
        else  if((!rb_quest3_yes.isChecked()) && (!rb_quest3_no.isChecked()) && (tv_quest3.getVisibility()==View.VISIBLE)){
            showAlertSingleButtonError("Please select option for question: "+ tv_quest3.getText());
            return false;
        }
        else  if((!rb_quest4_yes.isChecked()) && (!rb_quest4_no.isChecked()) && (tv_quest4.getVisibility()==View.VISIBLE)){
            showAlertSingleButtonError("Please select option for question: "+ tv_quest4.getText());
            return false;
        }
        else  if((!rb_quest5_yes.isChecked()) && (!rb_quest5_no.isChecked()) && (tv_quest5.getVisibility()==View.VISIBLE)){
            showAlertSingleButtonError("Please select option for question: "+ tv_quest5.getText());
            return false;
        }

        else{
            return true;
        }
    }
    public void showAlertSingleButtonError(String msg)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.AlertDialogHeaderErrorMsg))
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(R.drawable.error_ico)
                .setPositiveButton(getResources().getString(R.string.AlertDialogOkButton), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }
    public void getIntentData(){
        Intent intent=getIntent();
        StoreID= intent.getStringExtra("StoreID");
        String StoreName=   intent.getStringExtra("StoreName");
        imei = intent.getStringExtra("imei").trim();
        userDate = intent.getStringExtra("userDate");
        pickerDate = intent.getStringExtra("pickerDate").trim();

    }

    @Override
    public void testFunctionOne(String fnLati, String fnLongi, String finalAccuracy, String fnAccurateProvider, String GpsLat, String GpsLong, String GpsAccuracy, String NetwLat, String NetwLong, String NetwAccuracy, String FusedLat, String FusedLong, String FusedAccuracy, String AllProvidersLocation, String GpsAddress, String NetwAddress, String FusedAddress, String FusedLocationLatitudeWithFirstAttempt, String FusedLocationLongitudeWithFirstAttempt, String FusedLocationAccuracyWithFirstAttempt, int flgLocationServicesOnOff, int flgGPSOnOff, int flgNetworkOnOff, int flgFusedOnOff, int flgInternetOnOffWhileLocationTracking, String address, String pincode, String city, String state) {
        submitAllFunctionality(fnLati,fnLongi,finalAccuracy);
    }
    public void showSettingsAlert()
    {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle(R.string.AlertDialogHeaderMsg);
        alertDialog.setIcon(R.drawable.error_info_ico);
        alertDialog.setCancelable(false);
        // Setting Dialog Message
        alertDialog.setMessage(getText(R.string.genTermGPSDisablePleaseEnable));

        // On pressing Settings button
        alertDialog.setPositiveButton(R.string.AlertDialogOkButton, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
    public void showNoConnAlert()
    {
        AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(SurveyActivityTwo.this);
        alertDialogNoConn.setTitle(R.string.AlertDialogHeaderMsg);
        alertDialogNoConn.setMessage(R.string.NoDataConnectionFullMsg);
        alertDialogNoConn.setNeutralButton(R.string.AlertDialogOkButton,
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        // finish();
                    }
                });
        alertDialogNoConn.setIcon(R.drawable.error_ico);
        AlertDialog alert = alertDialogNoConn.create();
        alert.show();

    }
    public boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
        {
            return true;
        }
        return false;
    }
    public void submitAllFunctionality(String fnLati, String fnLongi, String finalAccuracy){


        long  syncTIMESTAMP = System.currentTimeMillis();
        Date dateobj = new Date(syncTIMESTAMP);
        // SimpleDateFormat df = new SimpleDateFormat("dd.MMM.yyyy.HH.mm.ss",Locale.ENGLISH);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        String currentDateTime = df.format(dateobj);
        dbengine.open();
        dbengine.deletetblSurveyData(StoreID);
        String QstnID= tv_quest1.getTag().toString();
        String OptionID="0";
        String OptionText="0";
        if(rb_quest1_yes.isChecked()){
            OptionID=rb_quest1_yes.getTag().toString();
            OptionText=rb_quest1_yes.getText().toString();
        }
        else {
            OptionID=rb_quest1_no.getTag().toString();
            OptionText=rb_quest1_no.getText().toString();
        }
        dbengine.fnsavetblSurveyData(StoreID,QstnID,OptionID,OptionText,currentDateTime,3,fnLati,fnLongi,finalAccuracy);
        if(tv_quest2.getVisibility()==View.VISIBLE){
            QstnID= tv_quest2.getTag().toString();
            if(rb_quest2_yes.isChecked()){
                OptionID=rb_quest2_yes.getTag().toString();
                OptionText=rb_quest2_yes.getText().toString();
            }
            else {
                OptionID=rb_quest2_no.getTag().toString();
                OptionText=rb_quest2_no.getText().toString();
            }
            dbengine.fnsavetblSurveyData(StoreID,QstnID,OptionID,OptionText,currentDateTime,3,fnLati,fnLongi,finalAccuracy);
        }


        if(tv_quest3.getVisibility()==View.VISIBLE){
            QstnID= tv_quest3.getTag().toString();
            if(rb_quest3_yes.isChecked()){
                OptionID=rb_quest3_yes.getTag().toString();
                OptionText=rb_quest3_yes.getText().toString();
            }
            else {
                OptionID=rb_quest3_no.getTag().toString();
                OptionText=rb_quest3_no.getText().toString();
            }
            dbengine.fnsavetblSurveyData(StoreID,QstnID,OptionID,OptionText,currentDateTime,3,fnLati,fnLongi,finalAccuracy);
        }
        if(tv_quest4.getVisibility()==View.VISIBLE){
            QstnID= tv_quest4.getTag().toString();
            if(rb_quest4_yes.isChecked()){
                OptionID=rb_quest4_yes.getTag().toString();
                OptionText=rb_quest4_yes.getText().toString();
            }
            else {
                OptionID=rb_quest4_no.getTag().toString();
                OptionText=rb_quest4_no.getText().toString();
            }
            dbengine.fnsavetblSurveyData(StoreID,QstnID,OptionID,OptionText,currentDateTime,3,fnLati,fnLongi,finalAccuracy);
        }
        if(tv_quest5.getVisibility()==View.VISIBLE){
            QstnID= tv_quest5.getTag().toString();
            if(rb_quest5_yes.isChecked()){
                OptionID=rb_quest5_yes.getTag().toString();
                OptionText=rb_quest5_yes.getText().toString();
            }
            else {
                OptionID=rb_quest5_no.getTag().toString();
                OptionText=rb_quest5_no.getText().toString();
            }
            dbengine.fnsavetblSurveyData(StoreID,QstnID,OptionID,OptionText,currentDateTime,3,fnLati,fnLongi,finalAccuracy);
        }

        QstnID= tv_quest6.getTag().toString();
        OptionID="0";
        OptionText="";
        if(!et_Comment.getText().toString().trim().equals("")){
            OptionText= et_Comment.getText().toString().trim();
        }
        dbengine.fnsavetblSurveyData(StoreID,QstnID,OptionID,OptionText,currentDateTime,3,fnLati,fnLongi,finalAccuracy);
        dbengine.close();


        df = new SimpleDateFormat("dd.MMM.yyyy.HH.mm.ss",Locale.ENGLISH);
        String currentDateTimeFile = df.format(dateobj);
        newfullFileName= CommonInfo.imei+"."+currentDateTimeFile;

        try {
            File OrderXMLFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.OrderXMLFolder);
            if (!OrderXMLFolder.exists())
            {
                OrderXMLFolder.mkdirs();

            }

            DA.open();
            DA.export(CommonInfo.DATABASE_NAME, newfullFileName,"0");
            DA.close();
            dbengine.savetbl_XMLfiles(newfullFileName, "3","1");
            dbengine. UpdateSstatSurvey(StoreID);




        }
        catch (Exception e){

        }
        if(isOnline()){
            Intent syncIntent = new Intent(SurveyActivityTwo.this, SyncMaster.class);
            syncIntent.putExtra("xmlPathForSync", Environment.getExternalStorageDirectory() + "/" + CommonInfo.OrderXMLFolder + "/" + newfullFileName + ".xml");
            syncIntent.putExtra("OrigZipFileName", newfullFileName);
            syncIntent.putExtra("whereTo", "SurveyActivity");
            syncIntent.putExtra("imei", imei);
            syncIntent.putExtra("userDate", userDate);
            syncIntent.putExtra("pickerDate", pickerDate);
            startActivity(syncIntent);
            finish();
        }
        else{
            showNoConnAlert();
        }

    }
}
