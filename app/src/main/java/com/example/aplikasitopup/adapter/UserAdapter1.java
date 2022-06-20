package com.example.aplikasitopup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasitopup.R;
import com.example.aplikasitopup.model.User1;

import java.util.List;

public class UserAdapter1 extends RecyclerView.Adapter<UserAdapter1.MyViewHolder> {

    private Context context;
    private List<User1> list1;
    private Dialog dialog1;


    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog1(Dialog dialog1) {
        this.dialog1 = dialog1;
    }

    public  UserAdapter1(Context context, List<User1> list1){
        this.context = context;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public UserAdapter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pesanan, parent, false);
        return new MyViewHolder(itemview1);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter1.MyViewHolder holder, int position) {
        holder.userid.setText(list1.get(position).getUserid());
        holder.diamond.setText(list1.get(position).getDiamond());
        holder.qty.setText(list1.get(position).getQty());
        holder.hargapesan.setText(list1.get(position).getHargapesan());

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userid, diamond, qty, hargapesan;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            userid = itemView.findViewById(R.id.txUserID);
            diamond = itemView.findViewById(R.id.txDiamond);
            qty = itemView.findViewById(R.id.txQty);
            hargapesan = itemView.findViewById(R.id.txHargaPesan);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(dialog1 != null){
                        dialog1.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }




}
