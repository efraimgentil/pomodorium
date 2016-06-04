package me.efraimgentil.pomodorium.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.efraimgentil.pomodorium.R;
import me.efraimgentil.pomodorium.activity.ListTarefaFragment.OnListFragmentInteractionListener;
import me.efraimgentil.pomodorium.model.Intention;
import me.efraimgentil.pomodorium.model.Tarefa;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyListTarefaRecyclerViewAdapter extends RecyclerView.Adapter<MyListTarefaRecyclerViewAdapter.ViewHolder> {

    private List<Tarefa> mValues;
    private OnListFragmentInteractionListener mListener;

    public MyListTarefaRecyclerViewAdapter(List<Tarefa> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public void setmValues(List<Tarefa> mValues) {
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_listtarefa , parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nome.setText(mValues.get(position).getNome() );
        holder.descricao.setText(mValues.get(position).getDescricao());
        holder.ciclos.setText("Ciclos: " + mValues.get(position).getCiclos());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction( v , holder.mItem , Intention.CLICK );
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public final View mView;
        public final TextView nome;
        public final TextView descricao;
        public final TextView ciclos;
        public Tarefa mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nome = (TextView) view.findViewById(R.id.txt_task_name);
            descricao = (TextView) view.findViewById(R.id.txt_description);
            ciclos = (TextView) view.findViewById(R.id.txt_ciclos);
            view.setOnCreateContextMenuListener(this);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + nome.getText() + "'";
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Ações");
            final MenuItem editar = menu.add(0, v.getId(), 0, "Editar");//groupId, itemId, order, title
            editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    mListener.onListFragmentInteraction( mView , mItem , Intention.EDTIAR );
                    return true;
                }
            });
            final MenuItem remover = menu.add(0, v.getId(), 0, "Remover");
            remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    mListener.onListFragmentInteraction( mView , mItem , Intention.REMOVER );
                    return true;
                }
            });
        }

    }
}
