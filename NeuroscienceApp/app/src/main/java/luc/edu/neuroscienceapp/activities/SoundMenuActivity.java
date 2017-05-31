package luc.edu.neuroscienceapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import luc.edu.neuroscienceapp.R;
import luc.edu.neuroscienceapp.utils.FileManager;

/**
 * @author Diego Tavares
 */
public class SoundMenuActivity extends AppCompatActivity {

    ImageButton btExamples, btChoose, btGallery;
    FileManager fileManager = new FileManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
            }
        }
        ActionBar toolbar = getSupportActionBar();
        if(toolbar != null) {
            toolbar.setDisplayHomeAsUpEnabled(true);
        }

        btExamples = (ImageButton) findViewById(R.id.bt_record);
        btChoose = (ImageButton) findViewById(R.id.bt_choose);
        btGallery = (ImageButton) findViewById(R.id.bt_gallery);


        btExamples.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGallery = new Intent(SoundMenuActivity.this, SoundExamplesActivity.class);
                startActivity(intentGallery);
            }
        });

        btChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRecorder = new Intent(SoundMenuActivity.this, SoundRecordingActivity.class);
                startActivity(intentRecorder);
            }
        });

        btGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent audioIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //audioIntent.setType("audio/*");
                startActivityForResult(audioIntent, 1);
                Uri uri = audioIntent.getData();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        if (id == R.id.action_about) {
            Intent intentAbout = new Intent(SoundMenuActivity.this, AboutActivity.class);
            startActivity(intentAbout);
            return true;
        }
        if (id == R.id.action_settings) {
            Intent intentSettings = new Intent(SoundMenuActivity.this, SettingsActivity.class);
            startActivity(intentSettings);
            return true;
        }
        if (id == R.id.action_info) {
            Intent intent = new Intent(SoundMenuActivity.this, WelcomeActivity.class);
            intent.putExtra("menu","menu");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
