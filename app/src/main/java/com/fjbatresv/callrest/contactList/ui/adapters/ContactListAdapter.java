package com.fjbatresv.callrest.contactList.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Contacto;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by javie on 30/09/2016.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    private List<Contacto> contactos;
    private List<Contacto> res = new ArrayList<Contacto>();
    private boolean busqueda = false;
    private ContactListOnItemClickListener listener;

    public ContactListAdapter(List<Contacto> contactos, List<Contacto> selected, ContactListOnItemClickListener listener) {
        this.contactos = contactos;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contacto contacto = contactos.get(position);
        holder.nombre.setText(contacto.getNombre());
        holder.numero.setText(contacto.getNumero());
        holder.setOnItemClickListener(contacto, listener);
        if (busqueda && !res.contains(contacto)){
            holder.card_view.setVisibility(View.GONE);
            ViewGroup.LayoutParams params = holder.card_view.getLayoutParams();
            params.height = 0;
            holder.card_view.setLayoutParams(params);
        }else{
            holder.card_view.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = holder.card_view.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            holder.card_view.setLayoutParams(params);
        }
    }

    public void loadList(List<Contacto> contactos){
        this.contactos = contactos;
        notifyDataSetChanged();
    }

    public void search(String string) {
        Log.e("adapter", "buscando...");
        res.clear();
        for (Contacto contacto :  this.contactos){
            if (contacto.getNombre().toUpperCase().contains(string.toUpperCase())
                    || contacto.getNumero().toUpperCase().contains(string.toUpperCase())){
                res.add(contacto);
            }
        }
        busqueda = true;
        notifyDataSetChanged();
    }

    public void restart() {
        res.clear();
        busqueda = false;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.card_view)
        CardView card_view;
        @Bind(R.id.selected)
        CheckBox selected;

        @Bind(R.id.nombre)
        TextView nombre;
        @Bind(R.id.numero)
        TextView numero;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Contacto contacto, final ContactListOnItemClickListener listener){
            selected.setOnClickListener(new  View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if (selected.isChecked()){
                        listener.addSelected(contacto);
                    }else{
                        listener.removeSelected(contacto);
                    }
                }
            });
        }
    }
}
