package alqonquin.cst2335.vill0413;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This Activity prompts user for password input and checks whether it's valid or not
 * according to password criteria. Password requirements are evaluated after "Login"
 * button is hit.
 * @author Leonardo_Villalobos
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /** This variable holds the text at the center of the screen */
    private TextView loginMsg = null;

    /** This variable holds the user's input value */
    private EditText password = null;

    /** This variable holds the button for login at the button of the screen */
    private Button loginButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView loginMsg = findViewById(R.id.textView);
        EditText password = findViewById(R.id.editText);
        Button loginButton = findViewById(R.id.button);

        loginButton.setOnClickListener(click -> {
            boolean passwordCheckResult = false;
            String passwordCheck = password.getText().toString();
            passwordCheckResult = checkPasswordComplexity(passwordCheck);

            if(passwordCheckResult == true){
                loginMsg.setText("Your password meets the requirements");
            } else
                loginMsg.setText("You shall not pass!");
        });
    }

    /**
     * This function takes the password entered by the user and evaluates whether if it meets all four
     * complexity requirements (at least one uppercase , one digit , one lowercase and one special character)
     * @param password This value is the password entered by the user to be checked
     * @return boolean value, true if password meets all requirements , false otherwise
     */
    private boolean checkPasswordComplexity(String password){
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for(int i=0; i < password.length(); i++){
            char c = password.charAt(i);
            if(Character.isUpperCase(c)){
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)){
                foundSpecial = true;
            } else
                return false;
        }

        if(!foundUpperCase){
            Toast.makeText(this,"No uppercase found", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundNumber) {
            Toast.makeText(this,"No numbers found", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(this, "No lowercase found", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundSpecial) {
            Toast.makeText(this, "No special character found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * This function checks if any of the allowed special are used in the password
     * @param specialCharacter char value to be evaluated
     * @return boolean value, true if specialCharacter is one of: #$%^&*!@, false otherwise
     */
    private boolean isSpecialCharacter(char specialCharacter)
    {
        switch (specialCharacter)
        {
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '!':
            case '@':
            case '?':
                return true;
            default:
                return false;
        }
    }
}