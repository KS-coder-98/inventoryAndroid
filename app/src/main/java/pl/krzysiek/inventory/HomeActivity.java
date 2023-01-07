package pl.krzysiek.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        logoutBtn = findViewById(R.id.idBtnLogout);

        // initializing our variables
        userNameTV = findViewById(R.id.idTVUserName);

        // getting data from intent.
        String name = getIntent().getStringExtra("username");
        String token = getIntent().getStringExtra("Authorization");

        // setting data to our text view.
        userNameTV.setText(name);

        // initializing click listener for logout button
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "User Logged Out", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}