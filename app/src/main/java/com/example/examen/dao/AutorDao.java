package com.example.examen.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.examen.entities.Autor;
import java.util.List;

@Dao
public interface AutorDao {
    @Insert
    long insert(Autor autor); // devuelve id

    @Query("SELECT * FROM Autor")
    List<Autor> getAll();
}
