package com.example.examen.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.examen.R;

public class BookFragment extends Fragment {
    public BookFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el layout de este fragmento
        return inflater.inflate(R.layout.fragment_book, container, false);
    }
}
