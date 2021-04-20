package com.example.studnetinfo;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.RequestQueue;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;




/**
 * A simple {@link Fragment} subclass.
 */
public class privateInfo extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    ViewPager viewPager;
    public List<Student_data_private_info> private_info_list = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        view = inflater.inflate(R.layout.fragment_private_info, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.privateRecyclerId);
        ItemsAdapter_PrivateInfo itemsAdapter = new ItemsAdapter_PrivateInfo(getContext(), private_info_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(itemsAdapter);


        return view;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        JsonPrivateInfo jsonPrivateInfo = new JsonPrivateInfo();
        jsonPrivateInfo.execute();

    }




    public class JsonPrivateInfo extends AsyncTask<String, String, List<Student_data_private_info>>{

        HttpURLConnection httpURLConnection =null;
        BufferedReader bufferedReader = null;
        String jsonFile;


        @Override
        protected List<Student_data_private_info> doInBackground(String... strings) {


            try {
                URL url = new URL("https://mehedi1511009.000webhostapp.com/fetch_data_personal_info.php");

                httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();

                String line;

                while ((line=bufferedReader.readLine())!=null){
                    stringBuffer.append(line);
                }

                jsonFile=stringBuffer.toString();



                JSONArray privateInfoArray = new JSONArray(jsonFile);

                for(int i=0;i<privateInfoArray.length();i++){
                    JSONObject arrayObject = privateInfoArray.getJSONObject(i);
                    Student_data_private_info info = new Student_data_private_info();
                    info.setStudentId(arrayObject.getString("StudentId"));
                    info.setName(arrayObject.getString("Name"));
                    info.setFather_Name(arrayObject.getString("Father_Name"));
                    info.setMother_Name(arrayObject.getString("Mother_Name"));
                    info.setAddress(arrayObject.getString("Address"));

                    private_info_list.add(info);
                }

                return private_info_list;


            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            finally {

                try {
                    httpURLConnection.disconnect();
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Student_data_private_info> s) {
            super.onPostExecute(s);


            ItemsAdapter_PrivateInfo itemsAdapter = new ItemsAdapter_PrivateInfo(getContext(), s);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(itemsAdapter);

        }
    }
}
