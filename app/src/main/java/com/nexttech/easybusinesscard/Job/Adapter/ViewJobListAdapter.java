package com.nexttech.easybusinesscard.Job.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nexttech.easybusinesscard.Job.Model.PostModel;
import com.nexttech.easybusinesscard.R;

import java.util.Calendar;
import java.util.List;

public class ViewJobListAdapter extends RecyclerView.Adapter<ViewJobListAdapter.ViewHolder> {

    Context context;
    List<PostModel> jobList;

    FirebaseUser firebaseUser;

    public ViewJobListAdapter(Context context, List<PostModel> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.job_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final PostModel model = jobList.get(position);

        holder.jobPosition.setText(model.getJobPostion());
        holder.companyName.setText(model.getCompanyName());
        holder.companyAddress.setText(model.getCompanyAddress());
        holder.jobSalary.setText(model.getJobSalary());

        isFavourite(String.valueOf(model.getPostId()), holder.jobFavourite);

        isViewed(String.valueOf(model.getPostId()), holder.itemView);

        holder.jobFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.jobFavourite.getTag().equals("Save")){
                    FirebaseDatabase.getInstance().getReference().child("Saves").child(firebaseUser.getUid())
                            .child(String.valueOf(model.getPostId())).setValue(true);
                } else {
                    FirebaseDatabase.getInstance().getReference().child("Saves").child(firebaseUser.getUid())
                            .child(String.valueOf(model.getPostId())).removeValue();
                }

            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(holder.itemView.getTag().equals("View")){
//                    FirebaseDatabase.getInstance().getReference().child("Last Viewed").child(firebaseUser.getUid())
//                            .child(String.valueOf(model.getPostId())).setValue(true);
//                } else {
//                    FirebaseDatabase.getInstance().getReference().child("Last Viewed").child(firebaseUser.getUid())
//                            .child(String.valueOf(model.getPostId())).removeValue();
//
//                    FirebaseDatabase.getInstance().getReference().child("Last Viewed").child(firebaseUser.getUid())
//                            .child(String.valueOf(model.getPostId())).setValue(true);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView jobPosition, companyName, companyAddress, jobSalary;
        ImageView jobFavourite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            jobPosition = itemView.findViewById(R.id.tv_job_position);
            companyName = itemView.findViewById(R.id.tv_company_name);
            companyAddress = itemView.findViewById(R.id.tv_company_address);
            jobSalary = itemView.findViewById(R.id.tv_job_salary);

            jobFavourite = itemView.findViewById(R.id.job_favorite);
        }
    }



    private void isFavourite(final String postId, final ImageView imageView){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final String userKey = firebaseUser.getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Saves").child(userKey);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(postId).exists()){
                    imageView.setImageResource(R.drawable.ic_favorite_selected);
                    imageView.setTag("Saved");
                } else {
                    imageView.setImageResource(R.drawable.ic_favorite_nonselected);
                    imageView.setTag("Save");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void isViewed(final String postId, final View view){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final String userKey = firebaseUser.getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Last Viewed").child(userKey);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(postId).exists()){
                    view.setTag("Viewed");
                } else {
                    view.setTag("View");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
