package alqonquin.cst2335.vill0413.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import alqonquin.cst2335.vill0413.R;
import alqonquin.cst2335.vill0413.data.MainViewModel;
import alqonquin.cst2335.vill0413.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        //setContentView(R.layout.activity_main);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        //TextView mytext = findViewById(R.id.textview);
        //variableBinding.textview.setText(model.editString);
        //Button btn = findViewById(R.id.button);
        //EditText myedit = findViewById(R.id.myedittext);
        variableBinding.button.setOnClickListener(click ->
        {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
            //variableBinding.textview.setText("Your edit text has: " + model.editString);

        });

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has: " + s);
        });
    /*
       btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                String editString = myedit.getText().toString();
                mytext.setText( "Your edit text has: " + editString);
            }
        });

        btn.setOnClickListener(    (View v) -> {
            String editString = myedit.getText().toString();
            mytext.setText("Your edit text has: " + editString);
        });

     */
    }
}