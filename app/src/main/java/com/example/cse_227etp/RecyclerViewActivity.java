package com.example.cse_227etp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    EditText etName, etReg;
    RecyclerView rv;
    MyAdapter md;
    ArrayList<ModelPerson> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        etName = findViewById(R.id.etname);
        etReg = findViewById(R.id.etreg);
        rv = findViewById(R.id.rv);

        al = new ArrayList<>();
        md = new MyAdapter(this,al);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(md);

        findViewById(R.id.btn_recycler_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String reg = etReg.getText().toString();
                ModelPerson p = new ModelPerson(name,reg);

                al.add(p);

                md.notifyDataSetChanged();
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        Context ctx;
        ArrayList<ModelPerson> al;

        MyAdapter(Context ctx, ArrayList<ModelPerson> al){
            this.ctx = ctx;
            this.al = al;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(ctx).inflate(R.layout.person_list_item,parent,false);

            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            ModelPerson person = al.get(position);
            holder.tvName.setText(""+person.getName());
            holder.tvReg.setText(""+person.getReg());
        }

        @Override
        public int getItemCount() {
            return al.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView tvName, tvReg;
            ImageView ivremove;
            LinearLayout linearLayout;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tvName = itemView.findViewById(R.id.tvname);
                tvReg = itemView.findViewById(R.id.tvreg);

                ivremove = itemView.findViewById(R.id.ivremove);

                ivremove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        delete(getAdapterPosition());
                        ivremove.setVisibility(View.INVISIBLE);
                    }
                });

            }
            public void delete(int position) {
                ModelPerson p1 = al.get(position);
                al.remove(p1);
                notifyDataSetChanged();
            }
        }
    }
}
