package work.database;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList equip_id, equip_title, equip_lab;

    CustomAdapter(Activity activity, Context context, ArrayList equip_id, ArrayList equip_lab, ArrayList equip_title){
        this.activity = activity;
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
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.equip_id_txt.setText(String.valueOf(equip_id.get(position)));
        holder.equip_lab_txt.setText(String.valueOf(equip_lab.get(position)));
        holder.equip_title_txt.setText(String.valueOf(equip_title.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateData.class);
                intent.putExtra("id", String.valueOf(equip_id.get(position)));
                intent.putExtra("lab", String.valueOf(equip_lab.get(position)));
                intent.putExtra("title", String.valueOf(equip_title.get(position)));

                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return equip_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView equip_id_txt, equip_lab_txt, equip_title_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            equip_id_txt = itemView.findViewById(R.id.equip_id_txt);
            equip_lab_txt = itemView.findViewById(R.id.equip_lab_txt);
            equip_title_txt = itemView.findViewById(R.id.equip_title_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
