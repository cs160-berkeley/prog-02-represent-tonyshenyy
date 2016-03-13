package com.example.tony.represent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView repName;
        TextView repParty;
        ImageView repPhoto;
        ImageView email;
        ImageView website;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            repName = (TextView) itemView.findViewById(R.id.rep_name);
            repParty = (TextView) itemView.findViewById(R.id.rep_party);
            repPhoto = (ImageView) itemView.findViewById(R.id.rep_photo);
            email = (ImageView) itemView.findViewById(R.id.rep_email);
            website = (ImageView) itemView.findViewById(R.id.rep_website);
        }
    }

    List<Reps> representatives;
    Context context;

    RVAdapter(List<Reps> persons, Context context){
        this.representatives = persons;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return representatives.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.congressional_view, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        final String tempEmail, tempWebsite, repInfo;
        String bioId = representatives.get(i).bioId;
        String url = "https://theunitedstates.io/images/congress/225x275/" + bioId + ".jpg";

        personViewHolder.repName.setText(representatives.get(i).name);
        personViewHolder.repParty.setText(representatives.get(i).party);
        new GetRepPhoto((ImageView) personViewHolder.repPhoto).execute(url);
        tempEmail = representatives.get(i).email;
        tempWebsite = representatives.get(i).website;

        personViewHolder.email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(tempEmail));
                context.startActivity(intent);
            }
        });

        personViewHolder.website.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(tempWebsite));
                context.startActivity(intent);
            }
        });

        repInfo = representatives.get(i).jsonObj;
        personViewHolder.repPhoto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailedViewActivity.class);
                Bundle extras = new Bundle();
                extras.putString("REP_INFO", repInfo);
                intent.putExtras(extras);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
