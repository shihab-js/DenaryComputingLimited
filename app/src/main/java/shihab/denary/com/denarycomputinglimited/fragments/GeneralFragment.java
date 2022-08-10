package shihab.denary.com.denarycomputinglimited.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import shihab.denary.com.denarycomputinglimited.R;
import shihab.denary.com.denarycomputinglimited.URL.UrlList;
import shihab.denary.com.denarycomputinglimited.adapter.GeneralDataAdapter;

/**
 * Created by shihab on 12/28/2017.
 */

public class GeneralFragment extends Fragment{
    View view;
    RecyclerView recyclerView;
    ArrayList<String> titleList;
    ArrayList<String> imgUrl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_general,container,false);

        initViews();

        return view;
    }

    private void initViews() {

        recyclerView = view.findViewById(R.id.card_recycler_view);
        titleList = new ArrayList<>();
        imgUrl = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        final RecyclerView.Adapter adapter = new GeneralDataAdapter(getContext(),titleList,imgUrl);
        recyclerView.setAdapter(adapter);

        RequestQueue requestQueue;

        requestQueue = Volley.newRequestQueue(getContext());

        // Creating the JsonObjectRequest class called obreq, passing required parameters:
        //GET is used to fetch data from the server, JsonURL is the URL to be fetched from.
        StringRequest strreq = new StringRequest(Request.Method.GET, UrlList.generalList,
                // The third parameter Listener overrides the method onResponse() and passes
                //JSONObject as a parameter
                new Response.Listener<String>() {

                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(String response) {
                        titleList.clear();
                        imgUrl.clear();
                        adapter.notifyDataSetChanged();
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            int length = jsonArray.length();
                            for(int i=0; i<length; i++){

                                String name = jsonArray.getJSONObject(i).getString("name");
                                String icon_url = jsonArray.getJSONObject(i).getString("icon");

                                titleList.add(name);
                                imgUrl.add(icon_url);
                                adapter.notifyDataSetChanged();

                            }

                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                //as a parameter
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        requestQueue.add(strreq);

    }

}