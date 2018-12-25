package com.example.user.smartmuseum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
ImageView imageGallery,imageAbout,imageSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imageGallery = findViewById(R.id.galery);
        imageAbout = findViewById(R.id.aboutUs);
        imageSearch = findViewById(R.id.search);

        imageGallery.setOnClickListener(this);
        imageAbout.setOnClickListener(this);
        imageSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

            switch (view.getId()){
                case R.id.aboutUs:
                    Intent intent = new Intent(MenuActivity.this,AboutUsActivity.class);
                    startActivity(intent);
                    break;

                case R.id.galery:
                    Intent intent1 = new Intent(MenuActivity.this,GalleryActivity.class);
                    startActivity(intent1);
                    break;

                case R.id.search:
                    Intent intent3 = new Intent(MenuActivity.this,Search.class);
                    startActivity(intent3);
                    break;
            }
    }
}
