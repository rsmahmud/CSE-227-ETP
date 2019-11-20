package com.example.cse_227etp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonVolleyActivity extends AppCompatActivity {

    ListView listView;
    RequestQueue requestQueue;

    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_volley);

        listView = findViewById(R.id.json_list_view);

        arrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        findViewById(R.id.json_btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://api.myjson.com/bins/109x54";

                JsonObjectRequest jreq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("student");

                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject student = jsonArray.getJSONObject(i);
                                String name = student.getString("name");
                                long mob = student.getLong("mobno");

                                arrayList.add(name+" "+mob);

                                arrayAdapter = new ArrayAdapter<String>(JsonVolleyActivity.this,android.R.layout.simple_list_item_1,arrayList);
                                listView.setAdapter(arrayAdapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                requestQueue.add(jreq);
            }
        });

    }
}
