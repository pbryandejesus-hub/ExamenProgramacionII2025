package com.example.examen.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Libro.class, parentColumns = "id", childColumns = "libroId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Miembro.class, parentColumns = "id", childColumns = "miembroId", onDelete = ForeignKey.CASCADE)
})
public class Prestamo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int libroId;
    public int miembroId;
    public String fecha;
    public Prestamo() {}
    public Prestamo(int libroId, int miembroId, String fecha){
        this.libroId = libroId; this.miembroId = miembroId; this.fecha = fecha;
    }
}
