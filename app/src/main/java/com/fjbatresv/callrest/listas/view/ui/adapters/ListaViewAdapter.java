package com.fjbatresv.callrest.listas.view.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fjbatresv.callrest.R;
import com.fjbatresv.callrest.entities.Contacto;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by javie on 29/09/2016.
 */
public class ListaViewAdapter extends RecyclerView.Adapter<ListaViewAdapter.ViewHolder> {
    private List<Contacto> contactos;
    private ListaViewOnItemClickListener listener;

    public ListaViewAdapter(List<Contacto> contactos, ListaViewOnItemClickListener listener) {
        this.contactos = contactos;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacto_card, parent, false);
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
        holder.id.setText(contacto.getId());
        holder.setOnItemClickListener(contacto, listener);
    }

    public void setList(List<Contacto> contactos){
        this.contactos = contactos;
        notifyDataSetChanged();
    }

    public void addContact(Contacto contacto){
        contactos.add(contacto);
        notifyDataSetChanged();
    }

    public void addContacts(List<Contacto> contactos) {
        this.contactos = contactos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.nombre)
        TextView nombre;
        @Bind(R.id.numero)
        TextView numero;
        @Bind(R.id.id)
        TextView id;
        @Bind(R.id.deleteContact)
        ImageView deleteContact;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final Contacto contacto, final ListaViewOnItemClickListener listener){
            deleteContact.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    listener.onDelete(contacto);
                }
            });
        }
    }
}
