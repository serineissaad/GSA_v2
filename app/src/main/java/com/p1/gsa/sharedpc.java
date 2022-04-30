package com.p1.gsa;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class sharedpc extends Activity  implements RadioGroup.OnCheckedChangeListener {

    //private String email;

    private String gender;
    private String hobbies;
    TextView show;
    //private String zodiac;
    public static final String STORAGE_NAME = "MySharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpc);

       // email = "";
        gender = "";
        hobbies = "";

      // zodiac = "";

        // Add other code
        RadioGroup radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        radioGroupGender.setOnCheckedChangeListener(this);


    //    Spinner spinnerZodiac = (Spinner) findViewById(R.id.spinnerZodiac);
// Populate the spinner with data source
      //  ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.zodiac, android.R.layout.simple_spinner_item);
    //    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    //    spinnerZodiac.setAdapter(adapter);

        // Set a listener on spinner
      //  spinnerZodiac.setOnItemSelectedListener(this);
    }

    // Add other methods
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)radioGroup.findViewById(radioButtonId);
        gender = radioButton.getText().toString();
    }

    public void onCheckboxClicked(View view) {

        CheckBox chkJogging = (CheckBox) findViewById(R.id.chkJogging);
        CheckBox chkCoding = (CheckBox) findViewById(R.id.chkCoding);
        CheckBox chkWriting = (CheckBox) findViewById(R.id.chkWriting);


        StringBuilder sb = new StringBuilder();



        if (chkJogging.isChecked()) {
            sb.append(", " + chkJogging.getText());
        }

        if (chkCoding.isChecked()) {
            sb.append(", " + chkCoding.getText());
        }

        if (chkWriting.isChecked()) {
            sb.append(", " + chkWriting.getText());
        }

        if (sb.length() > 0) { // No toast if the string is empty
            // Remove the first comma
            hobbies = sb.deleteCharAt(sb.indexOf(",")).toString();
        } else {
            hobbies = "";
        }
    }

  //  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
   //     zodiac = parent.getItemAtPosition(position).toString();
 //   }

  //  public void onNothingSelected(AdapterView<?> parent) {
        // An interface callback
  //  }

    public void save(View view) {

     //   email = ((EditText)findViewById(R.id.txtEmail)).getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
      //  editor.putString("email", email);
        editor.putString("gender", gender);
        editor.putString("hobbies", hobbies);

     //   editor.putString("zodiac", zodiac);

        editor.apply();

        Toast.makeText(getApplicationContext(), "You have been saved!", Toast.LENGTH_SHORT).show();
    }


    public void retrieve(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        //email = sharedPreferences.getString("email", "");
        gender = sharedPreferences.getString("gender", "");
        hobbies = sharedPreferences.getString("hobbies", "");
      //  zodiac = sharedPreferences.getString("zodiac", "");

        setupUI();
    }


    public void show(View view) {

        //   email = ((EditText)findViewById(R.id.txtEmail)).getText().toString();
        TextView show =  (TextView) findViewById(R.id.show);
     //   SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
      //  String gender =sharedPreferences.getString(gender, null);
       if (hobbies != null ) {
          show.setText(hobbies);

        }


        }






    protected void setupUI(){
      //  ((EditText)findViewById(R.id.txtEmail)).setText(email);

        RadioButton radMale = (RadioButton)findViewById(R.id.radMale);
        RadioButton radFemale = (RadioButton)findViewById(R.id.radFemale);

        if (gender.equals("Male")){
            radMale.setChecked(true);
        } else if (gender.equals("Female")){
            radFemale.setChecked(true);
        } else {
            radMale.setChecked(false);
            radFemale.setChecked(false);
        }

        CheckBox chkCoding = (CheckBox)findViewById(R.id.chkCoding);
        CheckBox chkWriting = (CheckBox)findViewById(R.id.chkWriting);
        CheckBox chkJogging = (CheckBox)findViewById(R.id.chkJogging);

        chkCoding.setChecked(false);
        chkWriting.setChecked(false);
        chkJogging.setChecked(false);

        if (hobbies.contains("Coding")) {
            chkCoding.setChecked(true);
        }

        if (hobbies.contains("Writing")) {
            chkWriting.setChecked(true);
        }

        if (hobbies.contains("Jogging")) {
            chkJogging.setChecked(true);
        }

      //  Resources resource = getResources();
     //   String[] zodiacArray = resource.getStringArray(R.array.zodiac);
     //      for(int i = 0; i < zodiacArray.length; i++){
     //      if(zodiacArray[i].equals(zodiac)){
      //          ((Spinner)findViewById(R.id.spinnerZodiac)).setSelection(i);
      //      }
       // }


    }}




