package com.example.shopin_seller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shopin_seller.Entity.SellerModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class Signup extends AppCompatActivity {
    TextInputLayout shopNameLayout, ownerNameLayout, industryLayout, mobileLayout, emailLayout, addressLayout, gstLayout, passwordLayout, confirmPasswordLayout;

    TextInputEditText edtShopName, edtOwnerName, edtMobile, edtEmail, edtAddress, edtGst, edtPassword, edtConfirmPassword;

    AutoCompleteTextView edtIndustryType;

    View btnCreateAccount;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;


    String[] industries = {
            "Grocery",
            "Mobile Shop",
            "Garments",
            "Electronics",
            "Medical Store",
            "Salon",
            "Hardware",
            "Bakery",
            "Stationery",
            "Footwear",
            "Furniture",
            "General Store"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
       id();
        setupIndustryDropdown();

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopName = getText(edtShopName);
                String ownerName = getText(edtOwnerName);
                String industry = getText(edtIndustryType);
                String mobile = getText(edtMobile);
                String email = getText(edtEmail);
                String address = getText(edtAddress);
                String gst = getText(edtGst);
                String password = getText(edtPassword);
                String confirmPassword = getText(edtConfirmPassword);

                if(validateAndCreateAccount()){
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    SellerModel model = new SellerModel(auth.getCurrentUser().getUid(),shopName,ownerName,industry,mobile,email,address,gst,LocalDate.now().toString());

                                        reference.child(model.getSellerId()).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if ((task.isSuccessful())){
                                                    AlertDialog.Builder dialog = new AlertDialog.Builder(Signup.this);

                                                    dialog.setTitle("sign up complete");
                                                    dialog.setMessage("account is created successfuly you can now handle you products etc");

                                                    dialog.setPositiveButton("see dashboared", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            startActivity(new Intent(Signup.this, Dashboard.class));
                                                        finish();
                                                        }
                                                    });

                                                    dialog.setNegativeButton("insert product", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            startActivity(new Intent(Signup.this,InsertOrder.class));
                                                        finish();
                                                        }
                                                    });

                                                    dialog.setNeutralButton("close app", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            finish();
                                                        }
                                                    }).show();
                                                }
                                            }
                                        });
                                }
                            }
                        }
                    });
                }

            }
        });



    }







    public void id(){
        shopNameLayout = findViewById(R.id.shopNameLayout);
        ownerNameLayout = findViewById(R.id.ownerNameLayout);
        industryLayout = findViewById(R.id.industryLayout);
        mobileLayout = findViewById(R.id.mobileLayout);
        auth = FirebaseAuth.getInstance();
        emailLayout = findViewById(R.id.emailLayout);
        database = FirebaseDatabase.getInstance();
        reference= database.getReference("Sellers");
        addressLayout = findViewById(R.id.addressLayout);
        gstLayout = findViewById(R.id.gstLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        confirmPasswordLayout = findViewById(R.id.confirmPasswordLayout);
        edtShopName = findViewById(R.id.edtShopName);
        edtOwnerName = findViewById(R.id.edtOwnerName);
        edtIndustryType = findViewById(R.id.edtIndustryType);
        edtMobile = findViewById(R.id.edtMobile);
        edtEmail = findViewById(R.id.edtEmail);
        edtAddress = findViewById(R.id.edtAddress);
        edtGst = findViewById(R.id.edtGst);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);

    }
    private void setupIndustryDropdown() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                industries
        );
        edtIndustryType.setAdapter(adapter);
        edtIndustryType.setOnClickListener(v -> edtIndustryType.showDropDown());
        edtIndustryType.setKeyListener(null);
    }

    private boolean validateAndCreateAccount() {
        clearErrors();

        String shopName = getText(edtShopName);
        String ownerName = getText(edtOwnerName);
        String industry = getText(edtIndustryType);
        String mobile = getText(edtMobile);
        String email = getText(edtEmail);
        String address = getText(edtAddress);
        String gst = getText(edtGst);
        String password = getText(edtPassword);
        String confirmPassword = getText(edtConfirmPassword);

        boolean isValid = true;

        if (TextUtils.isEmpty(shopName)) {
            shopNameLayout.setError("Shop name is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(ownerName)) {
            ownerNameLayout.setError("Owner name is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(industry)) {
            industryLayout.setError("Industry type is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(mobile)) {
            mobileLayout.setError("Mobile number is required");
            isValid = false;
        } else if (mobile.length() != 10) {
            mobileLayout.setError("Enter valid 10 digit mobile number");
            isValid = false;
        }

        if (TextUtils.isEmpty(email)) {
            emailLayout.setError("Email is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLayout.setError("Enter valid email address");
            isValid = false;
        }

        if (TextUtils.isEmpty(address)) {
            addressLayout.setError("Address is required");
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordLayout.setError("Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            passwordLayout.setError("Password must be at least 6 characters");
            isValid = false;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordLayout.setError("Confirm password is required");
            isValid = false;
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordLayout.setError("Passwords do not match");
            isValid = false;
        }

      return isValid;
    }

    private void clearErrors() {
        shopNameLayout.setError(null);
        ownerNameLayout.setError(null);
        industryLayout.setError(null);
        mobileLayout.setError(null);
        emailLayout.setError(null);
        addressLayout.setError(null);
        gstLayout.setError(null);
        passwordLayout.setError(null);
        confirmPasswordLayout.setError(null);
    }

    private String getText(TextInputEditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString().trim();
    }

    private String getText(AutoCompleteTextView editText) {
        return editText.getText() == null ? "" : editText.getText().toString().trim();
    }
}