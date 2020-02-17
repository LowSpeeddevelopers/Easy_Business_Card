package com.nexttech.easybusinesscard.Job.Adapter;

import android.content.Context;
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

        holder.jobFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.jobFavourite.getTag().equals("Like")){
                    FirebaseDatabase.getInstance().getReference().child("Favourites").child(String.valueOf(model.getPostId()))
                            .child(firebaseUser.getUid()).setValue(true);
                } else {
                    FirebaseDatabase.getInstance().getReference().child("Favourites").child(String.valueOf(model.getPostId()))
                            .child(firebaseUser.getUid()).removeValue();
                }
            }
        });
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



    private void isFavourite(String postId, final ImageView imageView){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final String userKey = firebaseUser.getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Favourites").child(postId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(userKey).exists()){
                    imageView.setImageResource(R.drawable.ic_favorite_selected);
                    imageView.setTag("Liked");
                } else {
                    imageView.setImageResource(R.drawable.ic_favorite_nonselected);
                    imageView.setTag("Like");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
