package com.example.examen.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.examen.dao.AutorDao;
import com.example.examen.dao.LibroDao;
import com.example.examen.dao.MiembroDao;
import com.example.examen.entities.Autor;
import com.example.examen.entities.Libro;
import com.example.examen.entities.Miembro;

@Database(entities = {Autor.class, Libro.class, Miembro.class}, version = 2) // 🔹 subí la versión
public abstract class AppDatabase extends RoomDatabase {

    public abstract AutorDao autorDao();
    public abstract LibroDao libroDao();
    public abstract MiembroDao miembroDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "examen_db")
                            .allowMainThreadQueries() // ⚠️ solo para pruebas
                            .fallbackToDestructiveMigration() // 🔹 resetea si cambia el esquema
                            .build();

                    // Datos de ejemplo
                    if (INSTANCE.autorDao().getAll().isEmpty()) {
                        Autor a1 = new Autor("Gabriel García Márquez");
                        Autor a2 = new Autor("Isabel Allende");
                        INSTANCE.autorDao().insert(a1);
                        INSTANCE.autorDao().insert(a2);

                        INSTANCE.libroDao().insert(new Libro("Cien Años de Soledad", 1));
                        INSTANCE.libroDao().insert(new Libro("La Casa de los Espíritus", 2));

                        INSTANCE.miembroDao().insert(new Miembro("Juan Pérez", "juan@correo.com"));
                    }
                }
            }
        }
        return INSTANCE;
    }
}
