package alqonquin.cst2335.vill0413;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w(TAG, "In onCreate() - Loading Widgets" );
        Button loginButton = (Button) findViewById(R.id.button1);
        EditText emailEditText = (EditText) findViewById(R.id.editTextTextPersonName);
        loginButton.setOnClickListener( clk -> {
            Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);
            nextPage.putExtra( "EmailAddress", emailEditText.getText().toString() );
            startActivity( nextPage);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG, "In onStart() - Application visible on screen" );

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "In onResume() - Responding to user input" );

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "In onPause() - Not responding to user input" );

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG, "In onStop() - Application not visible" );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "In onDestroy() - Memory is freed" );

    }

}