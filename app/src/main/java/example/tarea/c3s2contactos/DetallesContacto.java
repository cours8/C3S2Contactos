package example.tarea.c3s2contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetallesContacto extends AppCompatActivity {

    public static final String INTENT_KEY_CONFIRMAR = "CONFIRMAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalles);
    }
}