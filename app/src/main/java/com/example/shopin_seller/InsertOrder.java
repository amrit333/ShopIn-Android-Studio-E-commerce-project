package com.example.shopin_seller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shopin_seller.Adapters.ProductImageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class InsertOrder extends AppCompatActivity {
    private ViewPager2 viewPagerImages;
    private TabLayout tabDots;

    private final ArrayList<Uri> imageList = new ArrayList<>();
    private ProductImageAdapter adapter;

    private ActivityResultLauncher<Intent> imagePickerLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_order);
        AutoCompleteTextView edtCategory;
        viewPagerImages = findViewById(R.id.viewPagerImages);
        tabDots = findViewById(R.id.tabDots);

        adapter = new ProductImageAdapter(imageList);
        viewPagerImages.setAdapter(adapter);
        if(FirebaseAuth.getInstance().getUid()==null){
            startActivity(new Intent(InsertOrder.this,Signup.class));
        }
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

        edtCategory.setOnClickListener(v -> {
            edtCategory.showDropDown();
        });
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        imageList.clear();

                        if (data.getClipData() != null) {
                            int count = data.getClipData().getItemCount();

                            if (count > 4) {
                                Toast.makeText(this, "Please select up to 4 images only", Toast.LENGTH_SHORT).show();
                                count = 4;
                            }

                            for (int i = 0; i < count; i++) {
                                Uri imageUri = data.getClipData().getItemAt(i).getUri();
                                imageList.add(imageUri);
                            }
                        } else if (data.getData() != null) {
                            imageList.add(data.getData());
                        }

                        adapter.notifyDataSetChanged();
                        viewPagerImages.setCurrentItem(0, false);
                    }
                }
        );

        findViewById(R.id.viewPagerImages).setOnClickListener(v -> openImagePicker());
    }
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        imagePickerLauncher.launch(Intent.createChooser(intent, "Select Product Images"));
    }
}