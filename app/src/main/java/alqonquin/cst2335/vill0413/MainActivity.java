package alqonquin.cst2335.vill0413;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import alqonquin.cst2335.vill0413.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    protected String cityName;
    protected RequestQueue queue;
    Bitmap image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());

        queue = Volley.newRequestQueue(this);

        binding.forecastButton.setOnClickListener(click -> {
            cityName = binding.cityTextField.getText().toString();
            String stringURL;

            try {
                stringURL = "https://api.openweathermap.org/data/2.5/weather?q="
                                + URLEncoder.encode(cityName, "UTF-8")
                                + "&appid=7e943c97096a9784391a981c4d878b22&units=Metric";
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {
                        try {
                            JSONObject coord = response.getJSONObject("coord");

                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject position0 = weatherArray.getJSONObject(0);
                            String description = position0.getString("description");
                            String iconName = position0.getString("icon");

                            JSONObject mainObject = response.getJSONObject("main");
                            double current = mainObject.getDouble("temp");
                            double min = mainObject.getDouble("temp_min");
                            double max = mainObject.getDouble("temp_max");
                            int humidity = mainObject.getInt("humidity");

                            int vis = response.getInt("visibility");
                            String name = response.getString("name");

                            String pathName = getFilesDir() + "/" + iconName + ".png";
                            File file = new File(pathName);

                            if (file.exists())
                            {
                                image = BitmapFactory.decodeFile(pathName);
                                binding.icon.setImageBitmap(image);
                            }
                            else {
                                String imageURL = "https://openweathermap.org/img/w/" + iconName + ".png";
                                ImageRequest imgReq = new ImageRequest(imageURL, new Response.Listener<Bitmap>() {
                                    @Override
                                    public void onResponse(Bitmap bitmap) {
                                        try {
                                            // Do something with loaded bitmap...
                                            image = bitmap;
                                            image.compress(Bitmap.CompressFormat.PNG,100,
                                                    MainActivity.this.openFileOutput(iconName + ".png", Activity.MODE_PRIVATE));

                                        } catch (Exception e) {
                                            Toast.makeText(MainActivity.this, "Error while fetching image", Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    /* InputStream is = new URL(imageURL).openStream();
                                       Bitmap decodedBitmap = BitmapFactory.decodeStream(is);
                                       binding.icon.setImageBitmap(bitmap);

                                    FileOutputStream fOut = null;
                                    try {
                                        fOut = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                        fOut.flush();
                                        fOut.close();
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    String imagePath = getFilesDir() + "/" + iconName + ".png";
                                    Bitmap imageBitmap = BitmapFactory.decodeFile(imagePath);
                                    binding.icon.setImageBitmap(imageBitmap);*/
                                    }
                                }, 1024, 1024, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // Handle errors here...
                                        Toast.makeText(MainActivity.this, "Error while fetching image", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                queue.add(imgReq);
                                binding.icon.setImageBitmap(image);
                            }

                            runOnUiThread(() -> {
                                binding.temp.setText("The current temperature is: " + current + " ºC");
                                binding.temp.setVisibility(View.VISIBLE);

                                binding.minTemp.setText("The min temp is: " + min + " ºC");
                                binding.minTemp.setVisibility(View.VISIBLE);

                                binding.maxTemp.setText("The max temp is: " + max + " ºC");
                                binding.maxTemp.setVisibility(View.VISIBLE);

                                binding.humidity.setText("The humidity is: " + humidity + "%");
                                binding.humidity.setVisibility(View.VISIBLE);

                                binding.icon.setImageBitmap(image);
                                binding.icon.setVisibility(View.VISIBLE);

                                binding.description.setText(description);
                                binding.description.setVisibility(View.VISIBLE);
                            });

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    (error) -> {

                    });

            queue.add(request);
        });
    }
}