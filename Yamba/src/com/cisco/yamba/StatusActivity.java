package com.cisco.yamba;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

public class StatusActivity extends Activity implements OnClickListener,
		TextWatcher {
	private static final int MAX_TEXT = 140;
	private EditText editStatus;
	private Button buttonUpdate;
	private TextView textCount;
	private int defaultColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);

		editStatus = (EditText) findViewById(R.id.text_status);
		buttonUpdate = (Button) findViewById(R.id.button_update);
		textCount = (TextView) findViewById(R.id.text_count);
		
		textCount.setText(String.valueOf(MAX_TEXT));
		defaultColor = textCount.getTextColors().getDefaultColor();

		buttonUpdate.setOnClickListener(this);
		editStatus.addTextChangedListener(this);
	}

	// --- OnClickListener callback ---
	@Override
	public void onClick(View v) {

		// Post to cloud
		String status = editStatus.getText().toString();
		new PostStatusTask().execute(status);

	}

	private class PostStatusTask extends AsyncTask<String, Void, String> {
		ProgressDialog dialog;

		/** Happens before the background task, on UI thread */
		@Override
		protected void onPreExecute() {
			dialog = ProgressDialog.show(StatusActivity.this, "Posting", "Please wait..");
		}

		/** Executes on a worker thread */
		@Override
		protected String doInBackground(String... param) {
			try {
				YambaClient yamba = new YambaClient("student", "password");
				yamba.postStatus(param[0]);
				return "Successfully posted";
			} catch (YambaClientException e) {
				Log.e("Yamba", "Failed to post", e);
				e.printStackTrace();
				return "Failed to post";
			}
		}

		/** Executes on the UI thread */
		@Override
		protected void onPostExecute(String result) {
			dialog.dismiss();
			Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG)
					.show();
		}

	}

	// --- TextWateched callbacks ---
	@Override
	public void afterTextChanged(Editable s) {
		int count = 140 - editStatus.length();

		textCount.setText(String.valueOf(count));

		if (count < 50) {
			textCount.setTextColor(Color.RED);
		} else {
			textCount.setTextColor(defaultColor);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}
}