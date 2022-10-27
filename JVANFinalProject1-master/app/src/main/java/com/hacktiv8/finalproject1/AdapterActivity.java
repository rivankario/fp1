package com.hacktiv8.finalproject1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class AdapterActivity extends RecyclerView.Adapter<AdapterActivity.ViewHolder>{
    private Context context;
    private List<ModelActivity> List;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View theView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new ViewHolder(theView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelActivity club = List.get(position);
        holder.tvClubName.setText(club.getName());
        holder.imgClubLogo.setImageResource(R.drawable.hacktivrounded);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getRootView().getContext())
                        .setTitle("Delete to Do List?")
                        .setMessage("Do you really want to Delete?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                List.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                notifyItemRangeChanged(holder.getAdapterPosition(), List.size());
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final EditText taskEditText = new EditText(v.getRootView().getContext());
                taskEditText.setHint("Update toDo List");
                AlertDialog dialog = new AlertDialog.Builder(v.getRootView().getContext())
                        .setTitle("Update List")
                        .setView(taskEditText)
                        .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                if (task.equals("")){
                                    Toast.makeText(v.getRootView().getContext(),"Name Must Be Filled", Toast.LENGTH_SHORT).show();
                                } else{
                                    holder.tvClubName.setText(task);
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendData1 = new Intent(context, Profile.class);
                sendData1.putExtra("DataFilm", club.getName());
                context.startActivity(sendData1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvClubName;
        public ImageView imgClubLogo;
        public ImageButton btnDelete;
        public ImageButton btnUpdate;

        public ViewHolder(View itemView){
            super(itemView);
            tvClubName = itemView.findViewById(R.id.club_name);
            imgClubLogo = itemView.findViewById(R.id.club_logo);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
        }
    }

    public AdapterActivity(Context context, List<ModelActivity> clubList){
        this.context = context;
        this.List = clubList;
    }
}
