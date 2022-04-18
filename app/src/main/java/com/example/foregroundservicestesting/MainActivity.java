package com.example.foregroundservicestesting;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Log Tags
    public static final String PERM = "PERMISSION";

    //Hooks
    private EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooks
        editTextInput = findViewById(R.id.edit_text_input);

        requestPermissions();
    }

    public void startService(View v)
    {
        String input = editTextInput.getText().toString();

        Intent serviceIntent = new Intent(this, ForegroundService.class);
        serviceIntent.putExtra("inputExtra", input);

        startForegroundService(serviceIntent);
    }

    public void stopService(View v)
    {
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        stopService(serviceIntent);
    }

    //Asking for permission with Dexter
    public void requestPermissions()
    {
        Dexter.withContext(this).withPermissions(Manifest.permission.FOREGROUND_SERVICE)
                .withListener(new MultiplePermissionsListener()
                {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport)
                    {
                        Log.d(PERM, "PermissionsChecked");
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken)
                    {
                        Log.d(PERM, "PermissionRationaleShouldBeSHown");
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }
}