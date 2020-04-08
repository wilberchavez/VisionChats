package com.visionchatsvirtualcode7ec.visionchats.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.visionchatsvirtualcode7ec.visionchats.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class registroActivity extends AppCompatActivity
{
    private static final int SELECT_FILE = 1;
    ImageView imageView_photo_perfil_java;
    Uri selectedImageUri = null;
    Uri selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        imageView_photo_perfil_java=findViewById(R.id.img_photo_perfil_xml);
        imageView_photo_perfil_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photo_recoger();
            }
        });
    }

    private void photo_recoger()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        gallery.setType("image/*");
        startActivityForResult(Intent.createChooser(gallery, "Seleccione una imagen"),SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case SELECT_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImage = data.getData();
                    String selectedPath=selectedImage.getPath();
                    if (requestCode == SELECT_FILE) {
                        if (selectedPath != null) {
                            InputStream imageStream = null;
                            try {
                                imageStream = getContentResolver().openInputStream(selectedImage);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                            Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                            // Ponemos nuestro bitmap en un ImageView que tengamos en la vista
                            imageView_photo_perfil_java.setImageBitmap(bmp);
                        }
                    }
                }
                break;
        }
    }


}
