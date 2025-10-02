package com.example.examen.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.examen.entities.Miembro;

import java.util.List;

@Dao
public interface MiembroDao {
    @Insert
    void insert(Miembro miembro);

    @Update
    void update(Miembro miembro);

    @Delete
    void delete(Miembro miembro);

    @Query("SELECT * FROM Miembro")
    List<Miembro> getAll();

    @Query("SELECT * FROM Miembro WHERE id = :id LIMIT 1")
    Miembro getById(int id);
}
