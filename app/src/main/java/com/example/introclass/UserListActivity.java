package com.example.introclass;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    ListView userListView;
    DatabaseHelper db = new DatabaseHelper(UserListActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        userListView = (ListView) findViewById(R.id.userList);

        getUsers();

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserModel clickedUser = (UserModel) parent.getItemAtPosition(position);
                int ID = clickedUser.getID();
                String email = clickedUser.getEmail();
                boolean success =  db.deleteUser(ID);
                if(success){
                    Snackbar.make(view,"User "+ email + " deleted successfully", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    getUsers();
                }else{
                    Snackbar.make(view,"User deletion successfully failed", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });

    }

    private void getUsers() {
//        List<UserModel> userList =  db.getUsers();
//
//        ArrayAdapter userListAdapter = new ArrayAdapter<UserModel>(UserListActivity.this, android.R.layout.simple_list_item_1, userList);
//        userListView.setAdapter(userListAdapter);
        String URL = "https://api.github.com/users";
        RequestQueue queue = Volley.newRequestQueue(this);


        Gson jsonConverter = new Gson();

        JsonObjectRequest userRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Type userALType = new TypeToken<ArrayList<UserModel>>(){}.getType();

                        ArrayList<UserModel> userList = jsonConverter.fromJson(String.valueOf(response), userALType);
                        userList.get(0);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );

        queue.add(userRequest);
    }


}