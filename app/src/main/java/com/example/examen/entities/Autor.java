package com.example.examen.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Autor {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;

    public Autor() {}
    public Autor(String nombre) { this.nombre = nombre; }
}
