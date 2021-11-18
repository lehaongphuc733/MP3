package com.example.mp3;

import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

public class KiemTraEmail {

    public boolean validateEmailAddress(EditText email){
        String mailInput = email.getText().toString();

        if(!mailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mailInput).matches()) {
            return true;
        }else
        {
            return false;
        }
    }
}
