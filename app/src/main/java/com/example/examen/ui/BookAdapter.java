package com.example.examen.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examen.databinding.ItemBookBinding;
import com.example.examen.db.AppDatabase;
import com.example.examen.entities.Autor;
import com.example.examen.entities.Libro;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.VH> {
    private final List<Libro> data;
    private final Context ctx;
    private final AppDatabase db;

    public BookAdapter(Context ctx, List<Libro> data) {
        this.ctx = ctx;
        this.data = data;
        this.db = AppDatabase.getInstance(ctx);
    }

    static class VH extends RecyclerView.ViewHolder {
        ItemBookBinding b;
        VH(ItemBookBinding binding) {
            super(binding.getRoot());
            b = binding;
        }
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookBinding binding = ItemBookBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Libro libro = data.get(position);
        holder.b.tvTitle.setText(libro.titulo);

        // Obtener autor (puede ser null si borraron)
        Autor autor = null;
        if (db.autorDao().getAll().size() > 0) {
            // buscamos autor por ID (ineficiente pero simple)
            for (Autor a : db.autorDao().getAll()) {
                if (a.id == libro.autorId) { autor = a; break; }
            }
        }
        holder.b.tvAuthor.setText(autor != null ? autor.nombre : "Autor desconocido");

        // Click para editar
        holder.itemView.setOnClickListener(v -> {
            // delega a la actividad/fragment para editar usando un diálogo simple
            showEditDialog(libro, position);
        });

        // Long click para eliminar
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(ctx)
                    .setTitle("Eliminar")
                    .setMessage("¿Eliminar \"" + libro.titulo + "\"?")
                    .setPositiveButton("Sí", (d, w) -> {
                        db.libroDao().delete(libro);
                        data.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        });
    }

    private void showEditDialog(Libro libro, int pos) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        final android.widget.EditText input = new android.widget.EditText(ctx);
        input.setText(libro.titulo);
        new AlertDialog.Builder(ctx)
                .setTitle("Editar libro")
                .setView(input)
                .setPositiveButton("Guardar", (d, w) -> {
                    String nuevo = input.getText().toString().trim();
                    if (!nuevo.isEmpty()) {
                        libro.titulo = nuevo;
                        db.libroDao().update(libro);
                        data.set(pos, libro);
                        notifyItemChanged(pos);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override public int getItemCount() { return data.size(); }
}
