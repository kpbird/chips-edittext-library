package com.kpbird.chipsedittextdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import com.kpbird.chipsedittextlibrary.ChipsAdapter;
import com.kpbird.chipsedittextlibrary.ChipsItem;
import com.kpbird.chipsedittextlibrary.ChipsMultiAutoCompleteTextview;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ChipsMultiAutoCompleteTextview ch= (ChipsMultiAutoCompleteTextview) findViewById(R.id.chipsMultiAutoCompleteTextview1);
		
		String[] countries = getResources().getStringArray(R.array.country);
		TypedArray imgs = getResources().obtainTypedArray(R.array.flags);
		
		
		ArrayList<ChipsItem> arrCountry = new ArrayList<ChipsItem>();
		
		
		for(int i=0;i<countries.length;i++){
			arrCountry.add(new ChipsItem(countries[i], imgs.getResourceId(i, -1) ));
			Log.i("Main Activity", arrCountry.get(i).getTitle() +  " = " + arrCountry.get(i).getImageid());
		}
		
		Log.i("MainActivity", "Array :" + arrCountry.size());
		
		ChipsAdapter chipsAdapter = new ChipsAdapter(this, arrCountry);
		ch.setAdapter(chipsAdapter);
		
	}



}
