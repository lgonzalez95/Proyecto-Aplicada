package com.proyecto.aplicada.conectados;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Luis on 24/05/2016.
 */
public class RecyclerAdapMsjs extends RecyclerView.Adapter<RecyclerAdapMsjs.ViewHolder> {
    private final Context contexto;
    private Cursor items;
    private ControlItemMsjs escucha;


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Campos respectivos de un item
        public TextView txtUsuario;
        public TextView txtAsunto;
        public TextView txtDescripcion;

        public ViewHolder(View vista) {
            super(vista);
            txtAsunto = (TextView) vista.findViewById(R.id.txtview_titulo_asunto);
            txtDescripcion = (TextView) vista.findViewById(R.id.txtView_descripcion);
            txtUsuario = (TextView) vista.findViewById(R.id.txtView_asunto);
            vista.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {escucha.onClick(this, obtenerIdNotaRapida(getAdapterPosition()));}
    }

    private int obtenerIdNotaRapida(int posicion) {
        if (items != null) {
            if (items.moveToPosition(posicion)) {
                return items.getInt(0);
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public RecyclerAdapMsjs(Context contexto, ControlItemMsjs escucha) {
        this.contexto = contexto;
        this.escucha = escucha;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_mensaje, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        items.moveToPosition(position);
        String info;

        // Asignación de las vistas
        info = items.getString(3);
        holder.txtAsunto.setText("Mensaje de: " +info);

        info = items.getString(2);
        holder.txtDescripcion.setText(info);

        info = items.getString(1);
        holder.txtUsuario.setText("Asunto: "+ info);
    }

    @Override
    public int getItemCount() {
        if(items != null)
            return items.getCount();
        return -1;
    }
    public void actualizarCursor(Cursor nuevoCursor) {
        if (nuevoCursor != null) {
            items = nuevoCursor;
            notifyDataSetChanged();
        }
    }


}
