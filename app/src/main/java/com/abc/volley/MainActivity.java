package com.abc.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Animation animation;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    private ArrayList arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        animation= AnimationUtils.loadAnimation(this,R.anim.fromrecycleview);
        recyclerView.setAnimation(animation);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://glosbe.com/gapi/tm?from=pol&dest=eng&format=json&phrase=borsuk&page=1&pretty=true", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.e("Responce", response);
               try {
                    JSONObject jsonObject=new JSONObject(response);
                    if (jsonObject.getString("result").equalsIgnoreCase("ok"))
                    {
                        if (jsonObject.getString("found").equalsIgnoreCase("326"))
                        {
                            JSONArray jsonArray=jsonObject.getJSONArray("examples");
                            //Toast.makeText(MainActivity.this, "response"+jsonArray, Toast.LENGTH_SHORT).show();

                            arrayList=new ArrayList<>();
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject obj=jsonArray.getJSONObject(i);
                                ModelClass modelClass=new ModelClass();

                                modelClass.setAuthore(obj.getString("author"));
                                modelClass.setFirst(obj.getString("first"));
                                modelClass.setSecound(obj.getString("second"));

                                arrayList.add(modelClass);
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "error data not found", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Error data is invalid", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter=new AdepaterClass(getApplicationContext(),arrayList);
               recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "error" + error);
                Toast.makeText(MainActivity.this, "Hello" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }
}
