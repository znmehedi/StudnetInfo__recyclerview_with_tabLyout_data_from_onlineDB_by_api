package com.example.studnetinfo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter_AcademicInfo extends RecyclerView.Adapter<ItemsAdapter_AcademicInfo.MyViewHolder>{




    Context context;
    public List<Student_data_academic_info> ItemsData;

    public ItemsAdapter_AcademicInfo(Context context, List<Student_data_academic_info> ItemsData){
        this.context=context;
        this.ItemsData=ItemsData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.items,viewGroup, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.id.setText(ItemsData.get(position).getStudentId());;
        holder.name.setText(ItemsData.get(position).getName());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, details_info.class);
                intent.putExtra("id", ItemsData.get(position).getStudentId());
                intent.putExtra("name", ItemsData.get(position).getName());
                intent.putExtra("cgpa", ItemsData.get(position).getCgpa());
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return ItemsData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout linearLayout;
        TextView id, name;

    public MyViewHolder(@NonNull View itemView) {

        super(itemView);
        id=itemView.findViewById(R.id.itemsId);
        name=itemView.findViewById(R.id.itemsName);
        linearLayout=itemView.findViewById(R.id.individualitems);


    }


}
}
