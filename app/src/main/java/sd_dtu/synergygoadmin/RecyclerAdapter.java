package sd_dtu.synergygoadmin;

import android.content.Context;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        }
    }
}
