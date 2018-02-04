package artofprogramming.ru.busrout.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import artofprogramming.ru.busrout.R;
import artofprogramming.ru.busrout.service.OnItemClickListener;

/**
 * BusRout. Created by aleksandrstegnin on 29.01.2018.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private OnItemClickListener _listener;
    CardView _cardView;
    TextView _textName;
    TextView _textDescription;
    TextView _textNumber;

    ItemViewHolder(View v, OnItemClickListener _listener) {

        super(v);

        this._cardView = v.findViewById(R.id.routeCard);
        this._textName = itemView.findViewById(R.id.routName);
        this._textDescription = itemView.findViewById(R.id.routDescription);
        this._textNumber = itemView.findViewById(R.id.routNumber);
    }

    @Override
    public void onClick(View view) {
        _listener.onClick(view, (int)view.getTag(getAdapterPosition()));
    }

}
