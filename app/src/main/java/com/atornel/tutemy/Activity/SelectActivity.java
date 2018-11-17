package com.atornel.tutemy.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.atornel.tutemy.R;

public class SelectActivity extends AppCompatActivity {

    private Button gdrive_btn, torrent_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        gdrive_btn = (Button) findViewById(R.id.gdrive_btn);
        torrent_btn = (Button) findViewById(R.id.torrent_btn);

        gdrive_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectActivity.this, com.atornel.tutemy.Activity.GDrive.MainActivity.class));
            }
        });

        torrent_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectActivity.this, com.atornel.tutemy.Activity.Torrent.MainActivity.class));
            }
        });
    }

}
