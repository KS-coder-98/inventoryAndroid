package pl.krzysiek.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {

    // creating a variable
    // for our text view..
    private TextView userNameTV;

    // button for logout
    private Button logoutBtn;
    private Button getProductByBarCodeBtn;
    private Button listProductBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logoutBtn = findViewById(R.id.idBtnLogout);
        getProductByBarCodeBtn = findViewById(R.id.idBtnfindByBarCode);
        listProductBtn = findViewById(R.id.idBtnAllItem);


        // initializing our variables
        userNameTV = findViewById(R.id.idTVUserName);

        // getting data from intent.
        String name = getIntent().getStringExtra("username");

        // setting data to our text view.
        userNameTV.setText(name);

        // initializing click listener for logout button
        logoutBtn.setOnClickListener(v -> {
            Toast.makeText(HomeActivity.this, "User Logged Out", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(i);
        });

        getProductByBarCodeBtn.setOnClickListener(v -> {
            Toast.makeText(HomeActivity.this, "get product by bar code", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, ScannedBarcodeActivity.class);
            startActivity(i);
        });

        listProductBtn.setOnClickListener(v -> {
            Toast.makeText(HomeActivity.this, "get all product", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HomeActivity.this, ListItemActivity.class);
            startActivity(i);
        });
    }
}