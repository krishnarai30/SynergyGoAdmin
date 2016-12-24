package sd_dtu.synergygoadmin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohitkumar on 25/12/16.
 */

public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.RecycViewHolder>{

    Context context;
    ArrayList<CardData> arrayList = new ArrayList<CardData>();

    public RecyclerCardAdapter(Context context,ArrayList<CardData> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecycViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.agent_card,parent,false);
        RecycViewHolder recycViewHolder = new RecycViewHolder(view,context,arrayList);
        return recycViewHolder;
    }

    @Override
    public void onBindViewHolder(RecycViewHolder holder, int position) {
        CardData cardData = arrayList.get(position);
        holder.textView1.setText(cardData.getName());
        holder.textView2.setText(cardData.getFile());
        holder.textView3.setText(cardData.getAddtype());
        holder.textView4.setText(cardData.getAddress());
        holder.textView5.setText(cardData.getLandmark());
        holder.textView6.setText(cardData.getPcontact());
        holder.textView7.setText(cardData.getScontact());

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecycViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
        Context context;
        ArrayList<CardData> data = new ArrayList<CardData>();

        public RecycViewHolder(View itemView, Context context, ArrayList<CardData> data) {
            super(itemView);
            this.data = data;
            this.context = context;
            textView1 = (TextView) itemView.findViewById(R.id.name);
            textView2 = (TextView) itemView.findViewById(R.id.file_no);
            textView3 = (TextView) itemView.findViewById(R.id.add_type);
            textView4 = (TextView) itemView.findViewById(R.id.address);
            textView5 = (TextView) itemView.findViewById(R.id.land_mark);
            textView6 = (TextView) itemView.findViewById(R.id.p_contact);
            textView7 = (TextView) itemView.findViewById(R.id.s_contact);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
