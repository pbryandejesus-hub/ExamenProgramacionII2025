package com.example.examen.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Libro {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String titulo;
    public int autorId; // Relación con Autor

    // 🔹 Constructor vacío obligatorio para Room
    public Libro() {
    }

    // 🔹 Constructor para inicializar Libro
    public Libro(String titulo, int autorId) {
        this.titulo = titulo;
        this.autorId = autorId;
    }
}
