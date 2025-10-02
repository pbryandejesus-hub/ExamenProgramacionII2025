package com.example.examen.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.examen.databinding.ItemMemberBinding;
import com.example.examen.db.AppDatabase;
import com.example.examen.entities.Miembro;
import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.VH> {
    private final List<Miembro> data;
    private final Context ctx;
    private final AppDatabase db;

    public MemberAdapter(Context ctx, List<Miembro> data) {
        this.ctx = ctx;
        this.data = data;
        this.db = AppDatabase.getInstance(ctx);
    }

    static class VH extends RecyclerView.ViewHolder {
        ItemMemberBinding b;
        VH(ItemMemberBinding binding) {
            super(binding.getRoot());
            b = binding;
        }
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMemberBinding binding = ItemMemberBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Miembro m = data.get(position);
        holder.b.tvName.setText(m.nombre);
        holder.b.tvEmail.setText(m.email);

        // Editar
        holder.itemView.setOnClickListener(v -> showEditDialog(m, position));

        // Eliminar
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(ctx)
                    .setTitle("Eliminar miembro")
                    .setMessage("¿Eliminar a " + m.nombre + "?")
                    .setPositiveButton("Sí", (d, w) -> {
                        db.miembroDao().delete(m);
                        data.remove(position);
                        notifyItemRemoved(position);
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        });
    }

    private void showEditDialog(Miembro m, int pos) {
        EditText input = new EditText(ctx);
        input.setText(m.nombre);
        new AlertDialog.Builder(ctx)
                .setTitle("Editar miembro")
                .setView(input)
                .setPositiveButton("Guardar", (d, w) -> {
                    String nuevo = input.getText().toString().trim();
                    if (!nuevo.isEmpty()) {
                        m.nombre = nuevo;
                        db.miembroDao().update(m);
                        data.set(pos, m);
                        notifyItemChanged(pos);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override public int getItemCount() { return data.size(); }
}
