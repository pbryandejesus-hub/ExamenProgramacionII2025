package com.example.examen.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Autor {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nombre;

    // 🔹 Constructor vacío obligatorio para Room
    public Autor() {
    }

    // 🔹 Constructor para inicializar Autor
    public Autor(String nombre) {
        this.nombre = nombre;
    }
}
