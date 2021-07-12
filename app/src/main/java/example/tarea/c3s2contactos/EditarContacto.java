package example.tarea.c3s2contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class EditarContacto extends AppCompatActivity {

    public static final String INTENT_KEY_EDITAR = "EDITAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_editar);
        findViewById(R.id.btnSiguiente).setOnClickListener(this::onSiguienteClick);
        procesarIntentExtras();
    }

    private void procesarIntentExtras() {
        try {
            Bundle extras = getIntent().getExtras();
            if (null == extras) return;
            String[] editarSerializado = extras.getStringArray(INTENT_KEY_EDITAR);
            if (null != editarSerializado) onIntentEditar(editarSerializado);
        } catch(Exception ex) {
            Log.i("procesarIntentExtras",ex.getMessage());
            errorSnack(this.getCurrentFocus(),R.string.errorAlIniciar);
        }
    }

    private void onIntentEditar(String[] editarSerializado) {
        Contacto contacto = new Contacto(editarSerializado);
        setEditText(R.id.nombre_completo,      contacto.getNombreCompleto());
        setEditText(R.id.telefono,             contacto.getNumeroTelefono());
        setEditText(R.id.fecha_de_nacimiento,  contacto.getFechaNacimiento());
        setEditText(R.id.email,                contacto.getCorreoElectronico());
        setEditText(R.id.descripcion_contacto, contacto.getDetallesContacto());
    }

    private Contacto getContactoDeControles() {
        Contacto contacto = new Contacto();
        contacto.setNombreCompleto(getEditText(R.id.nombre_completo));
        contacto.setNumeroTelefono(getEditText(R.id.telefono));
        contacto.setFechaNacimiento(getEditText(R.id.fecha_de_nacimiento));
        contacto.setCorreoElectronico(getEditText(R.id.email));
        contacto.setDetallesContacto(getEditText(R.id.descripcion_contacto));
        return contacto;
    }

    private String getEditText(int idCampo) {
        View v = findViewById(idCampo);
        if(!(v instanceof EditText)) return null;
        return ((EditText) v).getText().toString();
    }

    private void setEditText(int idCampo, String nuevoTexto) {
        View v = findViewById(idCampo);
        if(!(v instanceof EditText)) return;
        ((EditText) v).setText(nuevoTexto);
    }

    private void errorSnack(View v, int id) {
        Snackbar snack = Snackbar.make(v,getResources().getString(id),Snackbar.LENGTH_SHORT);
        snack.show();
    }

    public void onSiguienteClick(View v) {
        try {
            DetallesContacto.startConfirmar(this, getContactoDeControles());
            finish();
        } catch(Exception ex) {
            Log.i("onSiguienteClick",ex.getMessage());
            errorSnack(v,R.string.errorAlVerDetalles);
        }
    }

    public static void startEditar(android.content.Context contexto,String[] contacto) {
        Intent i = new Intent(contexto, EditarContacto.class);
        i.putExtra(EditarContacto.INTENT_KEY_EDITAR, contacto);
        contexto.startActivity(i);
    }

    public static void startEditar(android.content.Context contexto, Contacto contacto) {
        String[] serializado = (null == contacto) ? null : contacto.serializar();
        startEditar(contexto,serializado);
    }

}