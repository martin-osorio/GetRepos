package com.martinosorio.restpractice;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ResultAPIAdapter extends RecyclerView.Adapter<ResultAPIAdapter.MyViewHolder> {

    private List<ResultAPI> resultAPIList;
    private TextView tvdummy;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name, owner;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.idee);
            name = (TextView) view.findViewById(R.id.name);
            owner = (TextView) view.findViewById(R.id.owner);
        }
    }

    public ResultAPIAdapter(List<ResultAPI> resultAPIList) {
        this.resultAPIList = resultAPIList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_row_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ResultAPI resultAPI = resultAPIList.get(position);
        holder.id.setText(resultAPI.getId().toString());
        holder.name.setText(resultAPI.getName());
        holder.owner.setText(resultAPI.getOwner().toString());

        final String details = resultAPI.toString();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ResultAPIDetail.class);
                i.putExtra("details", details);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultAPIList.size();
    }


}
