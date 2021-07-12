package example.tarea.c3s2contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class DetallesContacto extends AppCompatActivity {

    public static final String INTENT_KEY_CONFIRMAR = "CONFIRMAR";
    protected Contacto contacto = new Contacto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalles);

        Button btnEditarDatos = findViewById(R.id.btnEditarDatos);
        btnEditarDatos.setOnClickListener(this::onEditarDatosClick);
        procesarIntents();
        mostrarContacto(contacto);
    }

    private void mostrarContacto(Contacto contacto) {
        setTextView(R.id.tvNombre, contacto.getNombreCompleto());
        setTextView(R.id.tvTelefono, R.string.telefono_hint,
                    contacto.getNumeroTelefono());
        setTextView(R.id.tvFechaNacimiento, R.string.fecha_de_nacimiento_hint,
                    contacto.getFechaNacimiento());
        setTextView(R.id.tvMail, R.string.email_hint,
                    contacto.getCorreoElectronico());
        setTextView(R.id.tvDescripcion, R.string.descripcion_contacto_hint,
                    contacto.getDetallesContacto());
    }

    private void setTextView(int textViewId, CharSequence nuevoTexto) {
        TextView tv = findViewById(textViewId);
        if(null==tv) return;
        tv.setText(nuevoTexto);
    }

    private void setTextView(int textViewId, int campoStringId, CharSequence valor) {
        String campo = getResources().getString(campoStringId);
        campo = (null==campo) ? "" : (campo+":");
        if(null!=valor)
            campo = campo+" "+valor;
        setTextView(textViewId,campo);
    }

    private void procesarIntents() {
        Bundle extras = getIntent().getExtras();
        if(null==extras) return;
        String[] confirmarSerializado = extras.getStringArray(INTENT_KEY_CONFIRMAR);
        if(null!=confirmarSerializado) onIntentConfirmar(confirmarSerializado);
    }

    private void onIntentConfirmar(String[] confirmarSerializado) {
        contacto.deserializar(confirmarSerializado,true);
    }

    private void errorSnack(View v, int id) {
        Snackbar snack = Snackbar.make(v,getResources().getString(id),Snackbar.LENGTH_SHORT);
        snack.show();
    }

    public void onEditarDatosClick(View v) {
        try {
            EditarContacto.startEditar(this,contacto);
            finish();
        } catch(Exception ex) {
            Log.i("onEditarDatosClick",ex.getMessage());
            errorSnack(v,R.string.errorAlEditar);
        }
    }

    public static void startConfirmar(android.content.Context context, String[] serializado) {
        Intent i = new Intent(context, DetallesContacto.class);
        i.putExtra(DetallesContacto.INTENT_KEY_CONFIRMAR, serializado);
        context.startActivity(i);
    }

    public static void startConfirmar(android.content.Context context, Contacto contacto) {
        String[] serializado = (null==contacto)?null:contacto.serializar();
        startConfirmar(context, serializado);
    }
}