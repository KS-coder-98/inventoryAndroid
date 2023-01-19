package pl.krzysiek.inventory;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pl.krzysiek.inventory.model.ItemDto;

public class ListItemActivity extends AppCompatActivity {

    ListView itemList;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        itemList = findViewById(R.id.list);
        backBtn = findViewById(R.id.btnBack);
        fetchListItem();

        backBtn.setOnClickListener(v -> {
            finish();
        });

    }

    private void fetchListItem() {
        SharedPreferences preferences = getSharedPreferences("jwt_token", MODE_PRIVATE);
        String jwt = preferences.getString("token", null);
        String url = "http://10.8.2.215:8080/api/v1/item/all?page=0&size=10&sort=name";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    System.out.println(response);
//                    response.get("")
                    try {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<ItemDto>>() {
                        }.getType();
                        JSONArray jsonArray = response.getJSONArray("itemList");
                        List<ItemDto> itemDtoList = gson.fromJson(jsonArray.toString(), listType);
                        List<String> collect = itemDtoList.stream()
                                .filter(itemDto -> itemDto.getName() != null)
                                .map(ItemDto::getName)
                                .collect(Collectors.toList());
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, collect);
                        itemList.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> {
                    Toast.makeText(this, "1234", Toast.LENGTH_SHORT).show();
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