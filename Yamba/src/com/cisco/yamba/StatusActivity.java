package com.cisco.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StatusActivity extends Activity implements OnClickListener {
	EditText editStatus;
	Button buttonUpdate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);

		editStatus = (EditText) findViewById(R.id.text_status);
		buttonUpdate = (Button) findViewById(R.id.button_update);

		buttonUpdate.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String status = editStatus.getText().toString();
		Log.d("Yamba", "onClick'd with text: " + status);
	}

}
