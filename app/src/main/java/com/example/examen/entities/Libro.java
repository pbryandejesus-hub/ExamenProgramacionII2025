package com.example.examen.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = Autor.class,
        parentColumns = "id",
        childColumns = "autorId",
        onDelete = ForeignKey.CASCADE
))
public class Libro {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titulo;
    public int autorId;

    public Libro() {}
    public Libro(String titulo, int autorId) {
        this.titulo = titulo;
        this.autorId = autorId;
    }
}
