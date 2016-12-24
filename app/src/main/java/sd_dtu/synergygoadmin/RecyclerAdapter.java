package sd_dtu.synergygoadmin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by mohitkumar on 24/12/16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    ArrayList<RecyclerData> arrayList = new ArrayList<RecyclerData>();
    Context context;


    public RecyclerAdapter(ArrayList<RecyclerData> arrayList,Context context)
    {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,context,arrayList);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        RecyclerData recyclerData = arrayList.get(position);
        holder.textView.setText(recyclerData.getAgent());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        Context context;
        ArrayList<RecyclerData> data = new ArrayList<RecyclerData>();

        public RecyclerViewHolder(View itemView, Context context,ArrayList<RecyclerData> data) {
            super(itemView);
            this.context = context;
            this.data = data;
            itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.agent_id);
        }

        @Override
        public void onClick(View view) {
            String pat = textView.getText().toString();
            final ArrayList<String> list = new ArrayList<String>();
            DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference();
            reff.child("file").child(pat).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot file : dataSnapshot.getChildren())
                    {
                        list.add(file.getKey());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            Bundle bundle = null;
            bundle.putCharSequenceArray("array",list.toArray(new CharSequence[list.size()]));
            bundle.putString("agent",pat);

            Intent intent = new Intent(this.context,AgentClass.class);
            intent.putExtra("chararray",bundle);
            context.startActivity(intent);
        }
    }
}
