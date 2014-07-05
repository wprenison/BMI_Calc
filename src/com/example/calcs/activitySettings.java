package com.example.calcs;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.ArrayAdapter;


public class activitySettings extends Activity {
	
	protected void onCreate(Bundle savedInstaceState)
	{
		super.onCreate(savedInstaceState);
		setContentView(R.layout.activity_settings);
		populateSpinners();
		
	}
	
	public void populateSpinners()
	{
		Spinner unitsSpinner = (Spinner)findViewById(R.id.spnUnits);	//retrieve spinner obj
		String[] unitsSpinnerItems = {getString(R.string.spnrItemMetric), getString(R.string.spnrItemImperial)};	//spinner items text array
		ArrayAdapter<String> unitsSpinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, unitsSpinnerItems); //Set up array adapter for adding spinner items
		
		unitsSpinner.setAdapter(unitsSpinnerAdapter);	//Assign adapter which fills the spinner with items
		
		//Get stored unit preff to set which option should be set as selected in spinner
		SharedPreferences settings = getSharedPreferences("units", MODE_PRIVATE);
		String unitsType = settings.getString("units", getString(R.string.spnrItemMetric));
		
		if(unitsType.equalsIgnoreCase(getString(R.string.spnrItemMetric)))
			unitsSpinner.setSelection(0);
		else
			unitsSpinner.setSelection(1);
		
	}
	
	public void onClickSave(View view)
	{
		//Finds selected settings and stores it to sharedprefs
		Spinner unitsSpinner = (Spinner)findViewById(R.id.spnUnits);
		String selectedUnits = unitsSpinner.getSelectedItem().toString();	
		
		SharedPreferences settings = getSharedPreferences("units", MODE_PRIVATE);	//gets prefs
		SharedPreferences.Editor settingsEditor = settings.edit();	//opens prefs for editing
				
		settingsEditor.putString("units", selectedUnits);
		settingsEditor.commit();
		
		setResult(RESULT_OK);
		
		finish();
	}
	
	

}
