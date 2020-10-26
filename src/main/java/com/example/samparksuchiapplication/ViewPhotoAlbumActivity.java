package com.example.samparksuchiapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.samparksuchiapplication.Adapter.ViewPhotoAdapter;
import com.example.samparksuchiapplication.Model.AlbumModel;
import java.util.ArrayList;
import java.util.List;

public class ViewPhotoAlbumActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<AlbumModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_photo_album_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ViewPhotoAlbumActivity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        gridView = findViewById(R.id.simpleGridView);
        getPhotos();
    }

    private void getPhotos() {
        AlbumModel albumModel = new AlbumModel();
        albumModel.setPhoto("http://i.imgur.com/DvpvklR.png");
        list.add(albumModel);

        albumModel = new AlbumModel();
        albumModel.setPhoto("http://i.imgur.com/DvpvklR.png");
        list.add(albumModel);

        albumModel = new AlbumModel();
        albumModel.setPhoto("http://i.imgur.com/DvpvklR.png");
        list.add(albumModel);

        albumModel = new AlbumModel();
        albumModel.setPhoto("http://i.imgur.com/DvpvklR.png");
        list.add(albumModel);

        albumModel = new AlbumModel();
        albumModel.setPhoto("http://i.imgur.com/DvpvklR.png");
        list.add(albumModel);

        albumModel = new AlbumModel();
        albumModel.setPhoto("http://i.imgur.com/DvpvklR.png");
        list.add(albumModel);

        albumModel = new AlbumModel();
        albumModel.setPhoto("http://i.imgur.com/DvpvklR.png");
        list.add(albumModel);

        ViewPhotoAdapter adapter = new ViewPhotoAdapter(getApplicationContext(),R.layout.view_photo_adapter_list,list);
        gridView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
