package com.cisco.yamba;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClientException;

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

		// Post to cloud
		String status = editStatus.getText().toString();
		new PostStatusTask().execute(status);
		
	}

	private class PostStatusTask extends AsyncTask<String, Void, String> {

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
			Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
		}

		
	}
}
