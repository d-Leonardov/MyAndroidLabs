package alqonquin.cst2335.vill0413.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        model.buttonSelected.observe(this,selected -> {
            variableBinding.mycheckbox.setChecked(selected);
            variableBinding.myradiobutton.setChecked(selected);
            variableBinding.myswitch.setChecked(selected);
            Context context = getApplicationContext();
            CharSequence text = "The value is now: " + selected;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        });

        variableBinding.mycheckbox.setOnCheckedChangeListener((checkbox, isChecked) ->
        {
            model.buttonSelected.postValue(isChecked);
        });

        variableBinding.myradiobutton.setOnCheckedChangeListener((radiobutton, isChecked) ->
        {
            model.buttonSelected.postValue(isChecked);
        });

       variableBinding.myswitch.setOnCheckedChangeListener((switch1, isChecked) ->
        {
            model.buttonSelected.postValue(isChecked);
        });

        variableBinding.myimagebutton.setOnClickListener(click ->
        {
            int width = variableBinding.myimagebutton.getWidth();
            int height = variableBinding.myimagebutton.getHeight();
            Context context = getApplicationContext();
            CharSequence text = "The width = " + width + " and height = " + height;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
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