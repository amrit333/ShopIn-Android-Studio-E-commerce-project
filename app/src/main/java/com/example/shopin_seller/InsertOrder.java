package com.example.shopin_seller;

import android.Manifest;
import android.app.ComponentCaller;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopin_seller.Adapters.ProductImageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class InsertOrder extends AppCompatActivity {
    private ViewPager2 viewPagerImages;
    private TabLayout tabDots;
        ImageView imageView;
    private final ArrayList<Uri> imageList = new ArrayList<>();
    private ProductImageAdapter adapter;
    Uri image;

    private ActivityResultLauncher<Intent> imagePickerLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_order);
        AutoCompleteTextView edtCategory;
        if(FirebaseAuth.getInstance().getUid()==null){
            startActivity(new Intent(InsertOrder.this,Signup.class));
        }
        Dexter.withContext(InsertOrder.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                // i wrote Action get content becaue i want this intent to do action of getting a content from my device

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(InsertOrder.this, "please accept permission first", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();

            }
        }).check();
            imageView = findViewById(R.id.addProductImage);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    // set type is used to define that all images should cam
                    intent.setType("image/*");
                    startActivityForResult(intent,34);
                }
            });


        String[] categories = {
                "Electronics",
                "Fashion",
                "Shoes",
                "Mobiles",
                "Furniture",
                "Beauty",
                "Grocery",
                "Books",
                "Sports",
                "Accessories"
        };

        edtCategory = findViewById(R.id.edtCategory);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categories
        );

        edtCategory.setAdapter(adapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        image = data.getData();
        imageView.setImageURI(image);
        Toast.makeText(this, "imaage is set" +
                "", Toast.LENGTH_SHORT).show();
    }
}