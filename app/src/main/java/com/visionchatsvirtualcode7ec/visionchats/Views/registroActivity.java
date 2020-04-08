package com.visionchatsvirtualcode7ec.visionchats.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.visionchatsvirtualcode7ec.visionchats.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class registroActivity extends AppCompatActivity
{
    Toolbar toolbar_java;
    private static final int SELECT_FILE = 1;
    ImageView imageView_photo_perfil_java;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        toolbar_java=findViewById(R.id.toolbar_xml_resgistro_);
        imageView_photo_perfil_java = findViewById(R.id.perfil_imagen_xml);
        setSupportActionBar(toolbar_java);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageView_photo_perfil_java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                abrir_galeria();
            }
        });
    }

    private void abrir_galeria()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Seleccione una imagen"),
                SELECT_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        switch (requestCode)
        {
            case SELECT_FILE:
                if (resultCode==RESULT_OK)
                {
                    Uri uri = data.getData();
                    croopfoto(uri);
                    imageView_photo_perfil_java.setImageURI(uri);
                }
                break;
            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    crearbitmapphoto(resultUri);
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void crearbitmapphoto(Uri resultUri)
    {
        String selectedPath=resultUri.getPath();
            if (selectedPath != null) {
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(
                            resultUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // Transformamos la URI de la imagen a inputStream y este a un Bitmap
                Bitmap bmp = BitmapFactory.decodeStream(imageStream);
                // Ponemos nuestro bitmap en un ImageView que tengamos en la vista
                imageView_photo_perfil_java.setImageBitmap(bmp);
            }
    }


    private void croopfoto(Uri uri)
    {
        CropImage.activity(uri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }
}
