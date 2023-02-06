package alqonquin.cst2335.vill0413;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        TextView email = (TextView) findViewById(R.id.textView);
        email.setText("Welcome Back " + emailAddress);

        EditText phoneNumber = (EditText) findViewById(R.id.editTextPhone);
        Button phoneCall = (Button) findViewById(R.id.callNumber);

        phoneCall.setOnClickListener(clk -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel: " + phoneNumber.getText()));
            startActivity(call);
        });

        ImageView profileImage = (ImageView) findViewById(R.id.imageView);
        Button changePicture = (Button) findViewById(R.id.change_picture);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

               ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                Bitmap thumbnail = data.getParcelableExtra("data");
                                profileImage.setImageBitmap(thumbnail);
                            }
                        }
                    });

        changePicture.setOnClickListener(clk -> {
            cameraResult.launch(cameraIntent);
        });
    }
}