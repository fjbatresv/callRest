package com.fjbatresv.callrest.listas.list.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Lista;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by javie on 27/09/2016.
 */
public class ListasAdapter extends RecyclerView.Adapter<ListasAdapter.ViewHolder> {
    private List<Lista> listas;
    private OnItemClickListener listener;

    public ListasAdapter(List<Lista> listas, OnItemClickListener listener) {
        this.listas = listas;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lista lista = listas.get(position);
        holder.listaNombre.setText(lista.getNombre());
        holder.listaDesc.setText(lista.getDescripcion());
        holder.setOnItemClickListener(lista, listener);
    }

    public void setListas(List<Lista> listas){
        this.listas = listas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.listaNombre)
        TextView listaNombre;
        @Bind(R.id.listaDesc)
        TextView listaDesc;
        @Bind(R.id.listaCard)
        CardView listaCard;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Lista lista, final OnItemClickListener listener){
            listaCard.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    listener.onListaCardClick(lista);
                }
            });
        }
    }
}
