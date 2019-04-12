package polito.mad.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TodayMenuAdapter extends RecyclerView.Adapter<TodayMenuAdapter.MyViewHolder> {

    private List<TodayMenuData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, description, notes;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tvTMName);
            price = (TextView) view.findViewById(R.id.tvTMPrice);
            description = (TextView) view.findViewById(R.id.tvTMDescription);
            notes = (TextView) view.findViewById(R.id.tvTMNotes);
            ;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TodayMenuAdapter(List<TodayMenuData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)


    @Override
    public TodayMenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_today_menu, parent, false);

        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)

    @Override
    public void onBindViewHolder(TodayMenuAdapter.MyViewHolder holder, int position) {
        TodayMenuData tmd = mDataset.get(position);
        holder.name.setText(tmd.getDishesName());
        holder.description.setText(tmd.getDescription());
        holder.price.setText(tmd.getDishesPrice());
        holder.notes.setText(tmd.getNotes());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
