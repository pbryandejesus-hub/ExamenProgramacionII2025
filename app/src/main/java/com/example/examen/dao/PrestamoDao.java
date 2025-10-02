package com.example.examen.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.examen.entities.Prestamo;
import java.util.List;

@Dao
public interface PrestamoDao {
    @Insert
    long insert(Prestamo prestamo);

    @Query("SELECT * FROM Prestamo")
    List<Prestamo> getAll();
}
