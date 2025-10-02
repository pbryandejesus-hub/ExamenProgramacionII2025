package com.example.examen.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Miembro {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombre;
    public Miembro() {}
    public Miembro(String nombre){ this.nombre = nombre; }
}
