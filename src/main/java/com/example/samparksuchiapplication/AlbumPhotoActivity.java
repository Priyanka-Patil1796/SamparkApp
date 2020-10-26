package com.example.samparksuchiapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.samparksuchiapplication.Model.AlbumModel;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.List;

public class AlbumPhotoActivity extends AppCompatActivity {
    List<AlbumModel> list;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_photo_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Album Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        linearLayout = findViewById(R.id.ll_photo);

        list = new ArrayList<>();
        getAlbums();
    }

    private void getAlbums() {
        AlbumModel albumModel = new AlbumModel();
        albumModel.setAlbum("ALBUM ONE");
        list.add(albumModel);

        albumModel = new AlbumModel();
        albumModel.setAlbum("ALBUM TWo");
        list.add(albumModel);

        albumModel = new AlbumModel();
        albumModel.setAlbum("ALBUM THREE");
        list.add(albumModel);

        albumModel = new AlbumModel();
        albumModel.setAlbum("ALBUM FOUR");
        list.add(albumModel);

        LayoutInflater linflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for ( int i=0;i<list.size();i++) {
            View myView = linflater.inflate(R.layout.album_list, null); //here item is the the layout you want to inflate
            TextView name = myView.findViewById(R.id.tv_album);
            MaterialCardView cardView = myView.findViewById(R.id.cv_album);

            name.setText(list.get(i).getAlbum());
            linearLayout.addView(myView);

            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AlbumPhotoActivity.this,ViewPhotoAlbumActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
