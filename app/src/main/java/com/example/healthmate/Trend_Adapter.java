
package com.example.healthmate;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

/**
 * Trend_Adapter is a custom RecyclerView.Adapter for displaying trends data in a RecyclerView.
 * The adapter uses the trend_class data model and creates a view for each data item.
 */
public class Trend_Adapter extends RecyclerView.Adapter<Trend_Adapter.MyViewHolder> {
    Context context;
    ArrayList<Trend_Class> data;

    /**
     * Constructor for the Trend_Adapter class.
     *
     * @param context The application context.
     * @param data An ArrayList containing the trends data.
     */
    public Trend_Adapter(Context context, ArrayList<Trend_Class> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Trend_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trend_layout, parent, false);
        return new Trend_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Trend_Adapter.MyViewHolder holder, int position) {
        holder.activityName.setText(data.get(position).getActivity_name());
        holder.activityNumber.setText(data.get(position).getActivity_number());
        holder.activityUnit.setText(data.get(position).getActivity_unit());
        holder.icon.setImageResource(data.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * ViewHolder class to store and manage the views for each data item.
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView activityName;
        TextView activityNumber;
        TextView activityUnit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.trend_icon);
            activityName = itemView.findViewById(R.id.trend_activity);
            activityNumber = itemView.findViewById(R.id.trend_number);
            activityUnit = itemView.findViewById(R.id.trend_unit);
        }
    }
}
