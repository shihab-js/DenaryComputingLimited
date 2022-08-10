package shihab.denary.com.denarycomputinglimited.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import shihab.denary.com.denarycomputinglimited.adapter.ProjectAndDevelopmentListAdapter;

/**
 * Created by shihab on 12/28/2017.
 */

public class DevelopmentFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    ArrayList<String> titleList;
    ArrayList<String> shortDescription;
    ArrayList<String> longDescription;
    ArrayList<String> iconUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_development,container,false);

        initViews();
        return view;

    }

    private void initViews() {
        recyclerView = view.findViewById(R.id.card_recycler_view);
        titleList = new ArrayList<>();
        shortDescription = new ArrayList<>();
        longDescription = new ArrayList<>();
        iconUrl = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerView.Adapter adapter = new ProjectAndDevelopmentListAdapter(getContext(),titleList,shortDescription,longDescription,iconUrl);
        recyclerView.setAdapter(adapter);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getContext());


        StringRequest stringRequest = new StringRequest(Request.Method.GET, UrlList.developmentList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                titleList.clear();
                shortDescription.clear();
                longDescription.clear();
                iconUrl.clear();

                adapter.notifyDataSetChanged();

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    int length = jsonArray.length();

                    for(int i=0; i<length; i++){
                        String dev_title = jsonArray.getJSONObject(i).getString("development_title");
                        String short_des = jsonArray.getJSONObject(i).getString("short_description");
                        String long_des = jsonArray.getJSONObject(i).getString("long_description");
                        String Url_icon = jsonArray.getJSONObject(i).getString("development_icon");

                        titleList.add(dev_title);
                        shortDescription.add(short_des);
                        longDescription.add("Show more");
                        iconUrl.add(Url_icon);

                        adapter.notifyDataSetChanged();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        requestQueue.add(stringRequest);

    }
}
