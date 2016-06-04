package me.efraimgentil.pomodorium.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import me.efraimgentil.pomodorium.R;
import me.efraimgentil.pomodorium.model.Tarefa;
import me.efraimgentil.pomodorium.activity.TarefasFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display aand makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTarefasRecyclerViewAdapter extends RecyclerView.Adapter<MyTarefasRecyclerViewAdapter.ViewHolder> {

    private List<Tarefa> mValues;
    private final TarefasFragment.OnListFragmentInteractionListener mListener;

    public MyTarefasRecyclerViewAdapter(List<Tarefa> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public void setmValues(List<Tarefa> mValues) {
        this.mValues = mValues;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tarefas , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.nome.setText(mValues.get(position).getNome() );
        holder.descricao.setText(mValues.get(position).getDescricao());
        holder.ciclos.setText("Ciclos: " + mValues.get(position).getCiclos());
        holder.ciclos.setText("Ciclos: " + mValues.get(position).getCiclos());
        holder.buttonStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nome;
        public final TextView descricao;
        public final TextView ciclos;
        public final Button buttonStart;
        public Tarefa mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nome = (TextView) view.findViewById(R.id.tarefas_txt_task_name);
            descricao = (TextView) view.findViewById(R.id.tarefas_txt_description);
            ciclos = (TextView) view.findViewById(R.id.tarefas_txt_ciclos);
            buttonStart = (Button) view.findViewById(R.id.tarefas_btn_start);
        }



    }
}
