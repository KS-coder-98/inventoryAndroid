package pl.krzysiek.inventory;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.annotations.SerializedName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    @SuppressLint("SetTextI18n")
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

        String id =              "Id przedmiotu:    " + getIntent().getStringExtra("id");
        String name =            "Nazwa przedmiotu: " + getIntent().getStringExtra("name");
        String purchaseDate =    "Data zakupu:      " + getIntent().getStringExtra("purchaseDate");
        String purchasePrice =   "Cena zakupu:      " + setPrecision(getIntent().getDoubleExtra("purchasePrice", 2));
        String currencyValue =   "Aktualna wartosc: " + setPrecision(getIntent().getDoubleExtra("currencyValue", 2));
        String description =     "Opis:             " + getIntent().getStringExtra("description");
        String location =        "Lokalizacja:      " + getIntent().getStringExtra("location");
        String classification =  "Rodzaj:           " + getIntent().getStringExtra("classification");
        String barCodeNumber =   "BarCode:          " + getIntent().getStringExtra("barCodeNumber");

        textId.setText(id);
        textName.setText(name);
        textPurchaseDate.setText(purchaseDate);
        textPurchasePrice.setText(purchasePrice);
        textCurrencyValue.setText(currencyValue);
        textDescription.setText(description);
        textLocation.setText(location);
        textClassification.setText(classification);
        textBarCodeNumber.setText(barCodeNumber);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private static String setPrecision(Double number) {
        return BigDecimal.valueOf(number)
                .setScale(2, RoundingMode.HALF_UP)
                .toString();
    }
}