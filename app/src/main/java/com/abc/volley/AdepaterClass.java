package com.abc.volley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdepaterClass extends RecyclerView.Adapter<AdepaterClass.viewHolder> {
    ArrayList<ModelClass> arrayList;

    Context context;

    public AdepaterClass(Context applicationContext, ArrayList<ModelClass> arrayList) {

        this.context=applicationContext;
        this.arrayList=arrayList;

    }

    @Override
    public AdepaterClass.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.design_layout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdepaterClass.viewHolder holder, int position) {

        holder.t_autore.setText(arrayList.get(position).getAuthore());
        holder.t_first.setText(arrayList.get(position).getFirst());
        holder.t_secound.setText(arrayList.get(position).getSecound());
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder{

        TextView t_autore,t_first,t_secound;

         public viewHolder(View itemView) {
            super(itemView);


            t_autore=itemView.findViewById(R.id.author);
            t_first=itemView.findViewById(R.id.first);
            t_secound=itemView.findViewById(R.id.second);
        }
    }
}
