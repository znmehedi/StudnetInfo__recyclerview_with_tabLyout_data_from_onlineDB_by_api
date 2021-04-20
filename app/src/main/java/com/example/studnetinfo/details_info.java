package com.example.studnetinfo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class details_info extends AppCompatActivity {

    TextView info_type, studentid, name, father_name, mother_name, address, cgpa;
    TextView var_father_name, var_mother_name, var_address, var_cgpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info);
        info_type=(TextView)findViewById(R.id.info_type);
        studentid=(TextView) findViewById(R.id.student_id_input);
        name=(TextView) findViewById(R.id.student_name_input);
        father_name=(TextView) findViewById(R.id.father_name_input);
        mother_name=(TextView) findViewById(R.id.mother_name_input);
        address=(TextView) findViewById(R.id.address_input);
        cgpa=(TextView) findViewById(R.id.cgpa_input);
        var_father_name=(TextView)findViewById(R.id.father_name);
        var_mother_name=(TextView)findViewById(R.id.mother_name);
        var_address=(TextView)findViewById(R.id.address);
        var_cgpa=(TextView)findViewById(R.id.cgpa);


        Bundle bundle = getIntent().getExtras();
        String StudentId = null, Name = null, FatherName = null, MotherName = null, Address = null, Cgpa = null;
        if(bundle!=null){
            StudentId=bundle.getString("id");
            Name=bundle.getString("name");
            FatherName=bundle.getString("fatherName");
            MotherName=bundle.getString("motherName");
            Address=bundle.getString("address");
            Cgpa=bundle.getString("cgpa");
        }

        studentid.setText(StudentId);
        name.setText(Name);

        if(FatherName==null || MotherName==null || Address == null){
            info_type.setText("Academic Info");

            cgpa.setText(Cgpa);

            father_name.setTextSize(0);
            var_father_name.setTextSize(0);
            mother_name.setTextSize(0);
            var_mother_name.setTextSize(0);
            address.setTextSize(0);
            var_address.setTextSize(0);
        }
        else{
            info_type.setText("Private Info");
            father_name.setText(FatherName);
            mother_name.setText(MotherName);
            address.setText(Address);
            cgpa.setTextSize(0);
            var_cgpa.setTextSize(0);
        }


    }
}
