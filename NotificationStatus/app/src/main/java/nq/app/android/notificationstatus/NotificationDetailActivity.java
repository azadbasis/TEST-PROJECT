package nq.app.android.notificationstatus;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class NotificationDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_detail);
		
		TextView tv = new TextView(this);
		setContentView(tv);
		
		Intent intent = getIntent();
		String data = intent.getExtras().getString(MainActivity.NOTIFICATION_DATA);
		tv.setText(data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification_detail, menu);
		return true;
	}

}
