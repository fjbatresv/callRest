package com.fjbatresv.callrest.eventos.list.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.fjbatresv.callrest.entities.Evento;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by javie on 5/10/2016.
 */
public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.ViewHolder> {
    List<Evento> eventos;
    EventosClickListener listener;

    public EventosAdapter(List<Evento> eventos, EventosClickListener listener) {
        this.eventos = eventos;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
