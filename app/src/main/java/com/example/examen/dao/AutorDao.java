package com.example.examen.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examen.entities.Autor;

import java.util.List;

@Dao
public interface AutorDao {
    @Insert
    void insert(Autor autor);

    @Update
    void update(Autor autor);

    @Delete
    void delete(Autor autor);

    @Query("SELECT * FROM Autor")
    List<Autor> getAll();

    @Query("SELECT * FROM Autor WHERE id = :id LIMIT 1")
    Autor findById(int id);
}
