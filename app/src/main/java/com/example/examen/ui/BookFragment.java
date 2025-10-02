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
import com.example.examen.entities.Autor;
import com.example.examen.entities.Libro;

import java.util.List;

public class BookFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btnAddBook;
    private BookAdapter adapter;
    private AppDatabase db;
    private List<Libro> libros;

    public BookFragment() {
        // Constructor vacío requerido
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        recyclerView = view.findViewById(R.id.recyclerBooks);
        btnAddBook = view.findViewById(R.id.btnAddBook);

        db = AppDatabase.getInstance(requireContext());
        libros = db.libroDao().getAll();

        adapter = new BookAdapter(requireContext(), libros);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        btnAddBook.setOnClickListener(v -> showAddDialog());

        return view;
    }

    private void showAddDialog() {
        final EditText input = new EditText(requireContext());
        input.setHint("Título del libro");

        new AlertDialog.Builder(requireContext())
                .setTitle("Nuevo libro")
                .setView(input)
                .setPositiveButton("Agregar", (d, w) -> {
                    String titulo = input.getText().toString().trim();
                    if (!titulo.isEmpty()) {
                        Autor autor;
                        List<Autor> autores = db.autorDao().getAll();
                        if (autores.isEmpty()) {
                            autor = new Autor("Autor Genérico");
                            db.autorDao().insert(autor);
                            autores = db.autorDao().getAll();
                        }
                        autor = autores.get(0);

                        Libro nuevo = new Libro(titulo, autor.id);
                        db.libroDao().insert(nuevo);

                        libros.add(nuevo);
                        adapter.notifyItemInserted(libros.size() - 1);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}
