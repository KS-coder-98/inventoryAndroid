package pl.krzysiek.inventory;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class MakeInventoryActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewBarCodeNumber;
    private TextView editLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_inventory);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewName = findViewById(R.id.textViewName);
        textViewBarCodeNumber = findViewById(R.id.textViewBarCodeNumber);
        editLocation = findViewById(R.id.locationEdit);
        String name = "Nazwa przedmiotu: " + getIntent().getStringExtra("name");
        String barCodeNumber = "BarCode:          " + getIntent().getStringExtra("barCodeNumber");
        String location = getIntent().getStringExtra("location");

        editLocation.setText(location);
        textViewName.setText(name);
        textViewBarCodeNumber.setText(barCodeNumber);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Dodanie do spisu inwentaryzacji zakończono sukcesem", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}