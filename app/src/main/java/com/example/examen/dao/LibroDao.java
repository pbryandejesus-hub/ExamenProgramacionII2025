package com.example.examen.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examen.entities.Libro;

import java.util.List;

@Dao
public interface LibroDao {
    @Insert
    void insert(Libro libro);

    @Update
    void update(Libro libro);

    @Delete
    void delete(Libro libro);

    @Query("SELECT * FROM Libro")
    List<Libro> getAll();

    @Query("SELECT * FROM Libro WHERE id = :id LIMIT 1")
    Libro findById(int id);
}
