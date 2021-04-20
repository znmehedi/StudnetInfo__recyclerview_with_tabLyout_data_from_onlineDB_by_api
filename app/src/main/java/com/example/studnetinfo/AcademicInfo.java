package com.example.studnetinfo;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
public class AcademicInfo extends Fragment {

    View view;
    private RecyclerView recyclerView;
    public List<Student_data_academic_info> academic_info_list= new ArrayList<>();


    public AcademicInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_academic_info, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.academicRecycleId);


        ItemsAdapter_AcademicInfo itemsAdapter = new ItemsAdapter_AcademicInfo(getContext(), academic_info_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(itemsAdapter);
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JsonAcademicInfo jsonAcademicInfo = new JsonAcademicInfo();
        jsonAcademicInfo.execute();
    }

    public class JsonAcademicInfo extends AsyncTask<String, String, List<Student_data_academic_info>>{

        HttpURLConnection httpURLConnection=null;
        BufferedReader bufferedReader=null;
        String jsonFile;


        @Override
        protected List<Student_data_academic_info> doInBackground(String... strings) {

            try {
                URL url = new URL("https://mehedi1511009.000webhostapp.com/fetch_data_academic_data.php");

                httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer= new StringBuffer();

                String line;

                while((line=bufferedReader.readLine())!=null){
                    stringBuffer.append(line);
                }


                jsonFile = stringBuffer.toString();

                JSONArray academicInfoArray = new JSONArray(jsonFile);

                for(int i=0;i<academicInfoArray.length();i++){
                    JSONObject arrayObject = academicInfoArray.getJSONObject(i);
                    Student_data_academic_info info = new Student_data_academic_info();
                    info.setStudentId(arrayObject.getString("StudentId"));
                    info.setName(arrayObject.getString("Name"));
                    info.setCgpa(arrayObject.getString("cgpa"));
                    academic_info_list.add(info);
                }

                return academic_info_list;


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
        protected void onPostExecute(List<Student_data_academic_info> s) {
            super.onPostExecute(s);

            ItemsAdapter_AcademicInfo itemsAdapter = new ItemsAdapter_AcademicInfo(getContext(), s);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(itemsAdapter);
        }
    }

}
