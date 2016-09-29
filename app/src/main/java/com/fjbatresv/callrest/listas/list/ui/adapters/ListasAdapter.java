package com.fjbatresv.callrest.listas.list.ui.adapters;

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

    public ListasAdapter(List<Lista> listas) {
        this.listas = listas;
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
    }

    public void setListas(List<Lista> listas){
        this.listas = listas;
        if (listas.size() == 0){
            this.listas.add(new Lista("Test", "Testing the test", null));
            this.listas.add(new Lista("Test2", "Testing the test", null));
            this.listas.add(new Lista("Test3", "Testing the test", null));
            this.listas.add(new Lista("Test4", "Testing the test", null));
        }
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
