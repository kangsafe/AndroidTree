package com.cardflip;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.ks.androidtree.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flip_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
