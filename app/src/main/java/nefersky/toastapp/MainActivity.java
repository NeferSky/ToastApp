package nefersky.toastapp;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void MakeToast(){
        Toast toast = Toast.makeText(getApplicationContext(), R.string.feed_cat, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void MakeToast2(){
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(getApplicationContext(), R.string.feed_cat, duration);
        toast.setGravity(Gravity.TOP, 0, 0);

        LinearLayout toastLayout = (LinearLayout)toast.getView();
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.cat100);
        toastLayout.addView(imageView, 0);

        toast.show();
    }

    private void MakeToast3(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_layout, (ViewGroup)findViewById(R.id.toast_layout));

        TextView textViewToast = (TextView)layout.findViewById(R.id.textView3);
        textViewToast.setText(R.string.feed_cat2);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public void onClick(View view) {
        mainProcessing();
    }

    private Runnable doUpdateGUI = new Runnable() {
        @Override
        public void run() {
            Context context = getApplicationContext();
            String msg = "To open mobile document";
            int duration = Toast.LENGTH_LONG;
            Toast.makeText(context, msg, duration).show();
        }
    };

    private void backgroundThreadProcessing(){
        handler.post(doUpdateGUI);
    };

    private Runnable doBackgroundThreadProcessing = new Runnable() {
        @Override
        public void run() {
            backgroundThreadProcessing();
        }
    };

    private void mainProcessing() {
        Thread thread = new Thread(null, doBackgroundThreadProcessing, "Background");
        thread.start();
    }
}
