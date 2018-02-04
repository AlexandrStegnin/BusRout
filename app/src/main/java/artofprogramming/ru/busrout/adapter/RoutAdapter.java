package artofprogramming.ru.busrout.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import artofprogramming.ru.busrout.MapsActivity;
import artofprogramming.ru.busrout.R;
import artofprogramming.ru.busrout.model.Checkpoint;
import artofprogramming.ru.busrout.model.Routes;
import artofprogramming.ru.busrout.service.OnItemClickListener;

/**
 * BusRout. Created by aleksandrstegnin on 29.01.2018.
 */

public class RoutAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private OnItemClickListener _listener;
    private Routes _routes;
    public Context _context;

    public RoutAdapter(Context context, Routes routes, OnItemClickListener listener) {
        this._routes = routes;
        this._context = context;
        this._listener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rout_card, parent, false);

        return new ItemViewHolder(view, _listener);
    }


    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int listPosition) {

        List<Checkpoint> checkpointDataSet = _routes.getCheckpoints();

        final TextView textViewName = holder._textName;
        final TextView textViewDescription = holder._textDescription;
        final TextView textViewNumber = holder._textNumber;
        textViewName.setText(checkpointDataSet.get(listPosition).getName());
        textViewDescription.setText(checkpointDataSet.get(listPosition).getDescription());
        textViewName.setTag(checkpointDataSet.get(listPosition).getId());
        if (_routes != null) {
            textViewNumber.setText(String.format("%s", _routes.getNumber()));
        } else {
            textViewNumber.setText("");
        }
        holder._cardView.setOnClickListener(v -> {

            if(_listener !=null){
                _listener.onClick(v, getItemCount());
            }
            /*
            HashMap hashMap = new HashMap<>();
            for (Checkpoint cp : checkpointDataSet) {
                hashMap.put(cp.getOrder(), cp);
            }
            */
            Intent intent = new Intent(v.getContext(), MapsActivity.class);
            //intent.putExtra("checkpoints", hashMap);
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return  _routes != null ? _routes.getCheckpoints().size() : 0;
    }

}
