package com.example.examen.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.examen.entities.Miembro;
import java.util.List;

@Dao
public interface MiembroDao {
    @Insert
    long insert(Miembro miembro);

    @Query("SELECT * FROM Miembro")
    List<Miembro> getAll();
}
