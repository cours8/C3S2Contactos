package example.tarea.c3s2contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class EditarContacto extends AppCompatActivity {

    public static final String INTENT_KEY_EDITAR = "EDITAR";

    public static void startEditar(android.content.Context contexto,String[] contacto) {
        Intent i = new Intent(contexto, EditarContacto.class);
        i.putExtra(EditarContacto.INTENT_KEY_EDITAR, contacto);
        contexto.startActivity(i);
    }

    public static void startEditar(android.content.Context contexto, Contacto contacto) {
        String[] serializado = (null == contacto) ? null : contacto.serializar();
        startEditar(contexto,serializado);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_editar);
        findViewById(R.id.btnSiguiente).setOnClickListener(this::onSiguienteClick);
    }

    private void errorSnack(View v, int id) {
        Snackbar snack = Snackbar.make(v,getResources().getString(id),Snackbar.LENGTH_SHORT);
        snack.show();
    }

    public void onSiguienteClick(View v) {
        try {
            String[] serializado = null;
//            Intent i = new Intent(this, DetallesContacto.class);
//            i.putExtra(DetallesContacto.INTENT_KEY_CONFIRMAR, serializado);
//            startActivity(i);
            DetallesContacto.startConfirmar(this,serializado);
            finish();
        } catch(Exception ex) {
            Log.i("onSiguienteClick",ex.getMessage());
            errorSnack(v,R.string.errorAlVerDetalles);
        }
    }
}