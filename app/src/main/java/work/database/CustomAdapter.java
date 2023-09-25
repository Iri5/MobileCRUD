package work.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList equip_id, equip_title, equip_lab;

    CustomAdapter(Context context, ArrayList equip_id, ArrayList equip_lab, ArrayList equip_title){
        this.context = context;
        this.equip_id = equip_id;
        this.equip_lab = equip_lab;
        this.equip_title = equip_title;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.equip_lab_txt.setText(String.valueOf(equip_lab.get(position)));
        holder.equip_title_txt.setText(String.valueOf(equip_title.get(position)));

    }

    @Override
    public int getItemCount() {
        return equip_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView equip_id_txt, equip_lab_txt, equip_title_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            equip_lab_txt = itemView.findViewById(R.id.lab);
            equip_title_txt = itemView.findViewById(R.id.title);
        }
    }
}
