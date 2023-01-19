package pl.krzysiek.inventory;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.annotations.SerializedName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import pl.krzysiek.inventory.model.FixedAssetClassification;
import pl.krzysiek.inventory.model.ItemDto;

public class Item extends AppCompatActivity {

    private TextView textId;
    private TextView textName;
    private TextView textPurchaseDate;
    private TextView textFaxNumber;
    private TextView textPurchasePrice;
    private TextView textAmountOfAnnualDepreciation;
    private TextView textCurrencyValue;
    private TextView textDescription;
    private TextView textLocation;
    private TextView textClassification;
    private TextView textBarCodeNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textId = findViewById(R.id.textViewProductId);
        textName = findViewById(R.id.textViewProductName);
        textPurchaseDate = findViewById(R.id.textViewPurchaseDate);
        textPurchasePrice = findViewById(R.id.textViewPurchasePrice);
        textAmountOfAnnualDepreciation = findViewById(R.id.textViewAmountOfAnnualDepreciation);
        textCurrencyValue = findViewById(R.id.textViewCurrencyValue);
        textDescription = findViewById(R.id.textViewDescription);
        textLocation = findViewById(R.id.textViewLocation);
        textClassification = findViewById(R.id.textViewClassification);
        textBarCodeNumber = findViewById(R.id.textViewBarCodeNumber);

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");

        textId.setText(id);
        textName.setText(name);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}