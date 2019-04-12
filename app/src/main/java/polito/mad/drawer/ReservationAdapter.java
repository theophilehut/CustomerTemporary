package polito.mad.drawer;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {
    private List<ReservationData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, time, dishes, phone, notes;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tvResName);
            time = (TextView) view.findViewById(R.id.tvResTime);
            dishes = (TextView) view.findViewById(R.id.tvResDishes);
            phone = (TextView) view.findViewById(R.id.tvResPhone);
            notes = (TextView) view.findViewById(R.id.tvResNotes);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReservationAdapter(List<ReservationData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReservationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_reservation, parent, false);

        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ReservationData rd = mDataset.get(position);
        holder.name.setText(rd.getCustomerName());
        holder.time.setText(rd.getTime());
        holder.dishes.setText(rd.getDishes());
        holder.phone.setText(rd.getCustomerPhoneNumber());
        holder.notes.setText(rd.getNotes());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
