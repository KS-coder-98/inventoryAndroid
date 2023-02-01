package pl.krzysiek.inventory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.krzysiek.inventory.adapter.ItemArrayAdapter;
import pl.krzysiek.inventory.model.FixedAssetClassification;
import pl.krzysiek.inventory.model.ItemDto;

public class ScannedBarcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned_barcode);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        scanCode();

    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() == null) {
                    Toast.makeText(ScannedBarcodeActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ScannedBarcodeActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    fetchListItem(result.getContents());
                }
            });

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
        options.setPrompt("Scan a barcode");
        options.setCameraId(0);  // Use a specific camera of the device
        options.setBeepEnabled(false);
        options.setBarcodeImageEnabled(true);
        barcodeLauncher.launch(options);
    }

    private void fetchListItem(String codeNumber) {
        SharedPreferences preferences = getSharedPreferences("jwt_token", MODE_PRIVATE);
        String jwt = preferences.getString("token", null);
        codeNumber = codeNumber.substring(0, codeNumber.length() - 1);
        String url = "http://10.8.2.215:8080/api/v1/item/bar_code?barCode=" + codeNumber;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        Gson gson = new Gson();
                        ItemDto myObject = gson.fromJson(response.toString(), ItemDto.class);
                        Toast.makeText(this, myObject.getName(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ScannedBarcodeActivity.this, Item.class);
                        i.putExtra("id", myObject.getId().toString());
                        i.putExtra("name", myObject.getName());
                        i.putExtra("purchaseDate", myObject.getPurchaseDate());
                        i.putExtra("purchasePrice", myObject.getPurchasePrice());
                        i.putExtra("currencyValue", myObject.getCurrencyValue());
                        i.putExtra("description", myObject.getDescription());
                        i.putExtra("location", myObject.getLocation());
                        String s = myObject.getClassification().toString();
                        FixedAssetClassification fixedAssetClassification = FixedAssetClassification.valueOf(s);
                        i.putExtra("classification", fixedAssetClassification.getDescription());
                        i.putExtra("barCodeNumber", myObject.getBarCodeNumber());
                        startActivity(i);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                },
                error -> {
                    Toast.makeText(this, "Error - pobieranie danych", Toast.LENGTH_SHORT).show();
                    Log.e("fail with fetch data", error.toString());
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + jwt);
                return headers;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }


}