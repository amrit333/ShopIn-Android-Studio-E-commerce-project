package com.example.shopin_seller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class InsertOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insert_order);
        AutoCompleteTextView edtCategory;
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
    }
}