package autoride.azhar.me.mynotification;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText txtInfo;
    Button btnSend;
    public final static String NOTIFICATION_DATA = "NOTIFICATION_DATA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInfo = (EditText) this.findViewById(R.id.txtInfo);
        btnSend = (Button) this.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                createNotification(Calendar.getInstance().getTimeInMillis(), txtInfo.getText().toString());
            }
        });
    }

    private void createNotification(long time, String text){

        String notificationContent = "Detail : Press to show detail !";
        String notificationTitle = "Notification";

        // Create bitmap (icon notification)
        Bitmap largeicon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
        int smallicon = R.drawable.ic_launcher_background;

        // Set data and call intent NotificationDetail
        Intent intent = new Intent(getApplicationContext(), NotificationDetailActivity.class);
        intent.putExtra(NOTIFICATION_DATA, "Detail : " + text);

        // Set data in intent
        intent.setData(Uri.parse("content://"+time));

        @SuppressLint("WrongConstant") PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);

        // Create and request notification
        NotificationManager notificationManager
                = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder;
        builder = new NotificationCompat.Builder(getApplicationContext());

        builder.setWhen(time)
                .setContentText(notificationContent)
                .setContentTitle(notificationTitle)
                .setSmallIcon(smallicon)
                .setAutoCancel(true)
                .setTicker(notificationTitle)
                .setLargeIcon(largeicon)
                .setDefaults(Notification.DEFAULT_LIGHTS |
                        Notification.DEFAULT_SOUND|
                        Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notificationManager.notify((int)time, notification);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
