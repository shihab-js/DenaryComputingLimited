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
import shihab.denary.com.denarycomputinglimited.adapter.CourseDataAdapter;

/**
 * Created by shihab on 12/28/2017.
 */

public class CoursesFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    ArrayList<String> course_name;
    ArrayList<String> instructor_name;
    ArrayList<String> course_duration;
    ArrayList<String> imgUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_courses,container,false);
        initViews();
        return view;

    }

    private void initViews() {

        recyclerView = view.findViewById(R.id.card_recycler_view);
        course_name = new ArrayList<>();
        imgUrl = new ArrayList<>();
        instructor_name = new ArrayList<>();
        course_duration = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final RecyclerView.Adapter adapter = new CourseDataAdapter(getContext(),course_name,instructor_name,course_duration,imgUrl);
        recyclerView.setAdapter(adapter);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, UrlList.courseList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                course_name.clear();
                instructor_name.clear();
                course_duration.clear();
                imgUrl.clear();
                adapter.notifyDataSetChanged();

                try {
                    JSONArray jsonArray = new JSONArray(response);

                    int length = jsonArray.length();

                    for (int i=0; i<length; i++){
                        String c_name = jsonArray.getJSONObject(i).getString("course_name");
                        String ins_name = jsonArray.getJSONObject(i).getString("instructor_name");
                        String c_duration = jsonArray.getJSONObject(i).getString("duration");
                        String icon_url = jsonArray.getJSONObject(i).getString("icon");

                        course_name.add(c_name);
                        instructor_name.add("Instructor: "+ins_name);
                        course_duration.add("Duration: "+c_duration);
                        imgUrl.add(icon_url);

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
