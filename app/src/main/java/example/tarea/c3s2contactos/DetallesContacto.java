package example.tarea.c3s2contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class DetallesContacto extends AppCompatActivity {

    public static final String INTENT_KEY_CONFIRMAR = "CONFIRMAR";
    protected Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalles);

        Button btnEditarDatos = findViewById(R.id.btnEditarDatos);
        btnEditarDatos.setOnClickListener(this::onEditarDatosClick);

    }

    private void errorSnack(View v, int id) {
        Snackbar snack = Snackbar.make(v,getResources().getString(id),Snackbar.LENGTH_SHORT);
        snack.show();
    }

    public void onEditarDatosClick(View v) {
        Snackbar.make(v,"Intentando",Snackbar.LENGTH_SHORT).show();
        try {
            Intent i = new Intent(this, EditarContacto.class);
            i.putExtra(EditarContacto.INTENT_KEY_EDITAR, null == contacto ? null : contacto.serializar());
            startActivity(i);
            finish();
        } catch(Exception ex) {
            Log.i("onEditarDatosClick",ex.getMessage());
            errorSnack(v,R.string.errorAlEditar);
        }
    }
}