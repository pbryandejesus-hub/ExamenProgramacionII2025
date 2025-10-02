package com.example.examen.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examen.dao.AutorDao;
import com.example.examen.dao.LibroDao;
import com.example.examen.dao.MiembroDao;
import com.example.examen.dao.PrestamoDao;
import com.example.examen.entities.Autor;
import com.example.examen.entities.Libro;
import com.example.examen.entities.Miembro;
import com.example.examen.entities.Prestamo;

@Database(entities = {Autor.class, Libro.class, Miembro.class, Prestamo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AutorDao autorDao();
    public abstract LibroDao libroDao();
    public abstract MiembroDao miembroDao();
    public abstract PrestamoDao prestamoDao();

    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "examen_db")
                    .allowMainThreadQueries() // simplifica para el examen
                    .fallbackToDestructiveMigration()
                    .build();

            // Seed mínimo opcional
            new Thread(() -> {
                if (INSTANCE.autorDao().getAll().isEmpty()) {
                    long aid = INSTANCE.autorDao().insert(new Autor("Autor Ejemplo"));
                    INSTANCE.libroDao().insert(new Libro("Libro Ejemplo", (int) aid));
                    INSTANCE.miembroDao().insert(new Miembro("Miembro Ejemplo"));
                }
            }).start();
        }
        return INSTANCE;
    }
}
