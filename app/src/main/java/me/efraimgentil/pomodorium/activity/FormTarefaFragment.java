package me.efraimgentil.pomodorium.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.efraimgentil.pomodorium.R;
import me.efraimgentil.pomodorium.model.Tarefa;


public class FormTarefaFragment extends Fragment {

    private Tarefa tarefa;

    public FormTarefaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View inflate = inflater.inflate(R.layout.fragment_form_tarefa, container, false);
        if(tarefa != null) {
            ((TextView) inflate.findViewById(R.id.ipt_nome)).setText(tarefa.getNome());
            ((TextView) inflate.findViewById(R.id.ipt_descricao)).setText(tarefa.getDescricao());
            ((TextView) inflate.findViewById(R.id.ipt_ciclos)).setText( String.valueOf( tarefa.getCiclos() ));
        }
        return inflate;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
}
