package pl.krzysiek.inventory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText userNameEdt, passwordEdt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our edit text  and buttons.
        userNameEdt = findViewById(R.id.idEdtUserName);
        passwordEdt = findViewById(R.id.idEdtPassword);
        loginBtn = findViewById(R.id.idBtnLogin);

        // adding on click listener for our button.
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are getting data from our edit text.
                String userName = userNameEdt.getText().toString();
                String password = passwordEdt.getText().toString();

                // checking if the entered text is empty or not.
                if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Please enter user name and password", Toast.LENGTH_SHORT).show();
                }

                // calling a method to login our user.
                loginUser(userName, password);
            }
        });
    }

    private void loginUser(String userName, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("username", userName);
        params.put("password", password);

        String URL = "http://10.8.2.215:8080/login";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest objJsonArrayRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                new JSONObject(params),
                response -> {
                    try {
                        if (response.get("Authorization") != null) {
                            Toast.makeText(this, "Login Successful ", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            i.putExtra("username", userName);
                            String authorization = response.get("Authorization").toString();
                            i.putExtra("Authorization", authorization);
                            SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("jwt_token", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("token", authorization);
                            editor.apply();
                            startActivity(i);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(this, "Login fail", Toast.LENGTH_SHORT).show()
        );
        requestQueue.add(objJsonArrayRequest);

    }
}