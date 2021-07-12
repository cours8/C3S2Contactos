package example.tarea.c3s2contactos;

public class Contacto {
    private String nombreCompleto;
    private String numeroTelefono;
    private String fechaNacimiento;
    private String correoElectronico;
    private String detallesContacto;


    public Contacto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Contacto(String nombreCompleto, String numeroTelefono, String fechaNacimiento, String correoElectronico, String detallesContacto) {
        this.nombreCompleto = nombreCompleto;
        this.numeroTelefono = numeroTelefono;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.detallesContacto = detallesContacto;
    }

    /**
     * @param serializado Generado con `serializar()`.
     */
    public Contacto(String[] serializado) {
        deserializar(serializado,true);
    }

    public Contacto() {}

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDetallesContacto() {
        return detallesContacto;
    }

    public void setDetallesContacto(String detallesContacto) {
        this.detallesContacto = detallesContacto;
    }

    /**
     * Serializa el objeto actual.
     * @return Crea una representación de los datos actuales a ser usada con
     *         `deserializar` o con el constructor `Contacto(String[])`.
     */
    public String[] serializar() {
        return new String[]{
                nombreCompleto,
                numeroTelefono,
                fechaNacimiento,
                correoElectronico,
                detallesContacto
        };
    }

    /**
     * Dado un array, obtiene una posición si es válida, y si no el valor nulo.
     * @param array Array donde se busca el valor
     * @param pos posición del elemento en `array`.
     * @param valorSiNulo valor si no existe o si es nulo.
     * @return Si existe y no es nulo, devuelve `array[pos]`; si no `valorSiNulo`.
     */
    public static String elementoDeArrayOValor(String[] array, int pos, String valorSiNulo) {
        if(null == array) return valorSiNulo;
        if(pos <0 ) return valorSiNulo;
        if(pos >= array.length) return valorSiNulo;

        String valor = array[pos];
        return (null == valor)? valorSiNulo : valor;
    }

    /**
     * Deserealiza el contacto a partir de un array generado con serializar().
     * @param serializado array generado con serializar().
     * @param sobreescribirNulos Si se desea sobreescribir con null los valores que sean null
     *                           o no existan en `serializado` o dejar el valor anterior.
     */
    public void deserializar(String[] serializado, boolean sobreescribirNulos) {
        if(null==serializado) return;
        nombreCompleto = elementoDeArrayOValor(serializado,0,
                sobreescribirNulos ? null : nombreCompleto);
        numeroTelefono = elementoDeArrayOValor(serializado, 1,
                sobreescribirNulos ? null : numeroTelefono);
        fechaNacimiento = elementoDeArrayOValor(serializado, 2,
                sobreescribirNulos ? null : fechaNacimiento);
        correoElectronico = elementoDeArrayOValor(serializado, 3,
                sobreescribirNulos ? null : correoElectronico);
        detallesContacto = elementoDeArrayOValor(serializado, 4,
                sobreescribirNulos ? null : detallesContacto);
    }

}
