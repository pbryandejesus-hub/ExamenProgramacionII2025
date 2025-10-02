package com.example.examen.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen.R;
import com.example.examen.db.AppDatabase;
import com.example.examen.entities.Miembro;

import java.util.List;

public class MemberFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btnAddMember;
    private MemberAdapter adapter;
    private AppDatabase db;
    private List<Miembro> miembros;

    public MemberFragment() {
        // Constructor vacío requerido
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);

        recyclerView = view.findViewById(R.id.recyclerMembers);
        btnAddMember = view.findViewById(R.id.btnAddMember);

        db = AppDatabase.getInstance(requireContext());
        miembros = db.miembroDao().getAll();

        adapter = new MemberAdapter(requireContext(), miembros);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        btnAddMember.setOnClickListener(v -> showAddDialog());

        return view;
    }

    private void showAddDialog() {
        final EditText input = new EditText(requireContext());
        input.setHint("Nombre del miembro");

        new AlertDialog.Builder(requireContext())
                .setTitle("Nuevo miembro")
                .setView(input)
                .setPositiveButton("Agregar", (d, w) -> {
                    String nombre = input.getText().toString().trim();
                    if (!nombre.isEmpty()) {
                        Miembro nuevo = new Miembro(nombre, nombre.toLowerCase() + "@mail.com");
                        db.miembroDao().insert(nuevo);

                        miembros.add(nuevo);
                        adapter.notifyItemInserted(miembros.size() - 1);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
