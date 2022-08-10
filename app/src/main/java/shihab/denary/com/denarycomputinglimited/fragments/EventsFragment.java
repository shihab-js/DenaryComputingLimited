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
import shihab.denary.com.denarycomputinglimited.adapter.EventDataAdapter;

/**
 * Created by shihab on 12/28/2017.
 */

public class EventsFragment extends Fragment{

    View view;
    RecyclerView recyclerView;

    ArrayList<String> ev_name;
    ArrayList<String> sp_name;
    ArrayList<String> date;
    ArrayList<String> time;
    ArrayList<String> imgUrl;
    ArrayList<String> location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events,container,false);

        initViews();

        return view;
    }

    private void initViews() {

        recyclerView = view.findViewById(R.id.card_recycler_view);

        ev_name = new ArrayList<>();
        imgUrl = new ArrayList<>();
        sp_name = new ArrayList<>();
        date = new ArrayList<>();
        time = new ArrayList<>();
        location = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerView.Adapter adapter = new EventDataAdapter(getContext(),ev_name,sp_name,date,time,location,imgUrl);
        recyclerView.setAdapter(adapter);


        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getContext());


        StringRequest stringRequest = new StringRequest(Request.Method.GET, UrlList.eventList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ev_name.clear();
                sp_name.clear();
                date.clear();
                time.clear();
                imgUrl.clear();
                adapter.notifyDataSetChanged();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    int length = jsonArray.length();

                    for(int i=0; i<length; i++){
                        String e_name = jsonArray.getJSONObject(i).getString("event_name");
                        String s_name = jsonArray.getJSONObject(i).getString("speaker_name");
                        String e_date = jsonArray.getJSONObject(i).getString("date");
                        String e_time = jsonArray.getJSONObject(i).getString("time");
                        String e_imgUrl = jsonArray.getJSONObject(i).getString("icon");
                        String e_location = jsonArray.getJSONObject(i).getString("location");

                        ev_name.add(e_name);
                        sp_name.add("Speaker: "+s_name);
                        date.add("Date: "+e_date);
                        time.add("Time: "+e_time);
                        imgUrl.add(e_imgUrl);
                        location.add("Vanue: "+e_location);

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
        });

        requestQueue.add(stringRequest);
    }

}
