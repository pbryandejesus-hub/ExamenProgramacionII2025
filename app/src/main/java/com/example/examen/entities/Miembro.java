package com.example.examen.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Miembro {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nombre;
    public String email;

    // 🔹 Constructor vacío obligatorio para Room
    public Miembro() {
    }

    // 🔹 Constructor para inicializar miembros
    public Miembro(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
}
