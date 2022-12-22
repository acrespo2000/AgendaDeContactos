package principal;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Contacto implements Serializable{
    
    
    private String nombre;
    private String apellido1;
    private String apellido2;
    private long telefono;
    private TipoEnum tipo;
    private Date nacimiento;
    private String dni;

    
     public Contacto(String registro) throws ParseException {
        String[] trozos = registro.split("_");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        nombre = trozos[0];
        apellido1=trozos[1];
        apellido2=trozos[2];
        telefono = Long.parseLong(trozos[3]);
        tipo = TipoEnum.valueOf(trozos[4]); //tipo =Enumerador.valueOf( trozos[4]);
        dni = trozos[5];
        nacimiento = sdf.parse(trozos[6]);
    }

    public Contacto(String nombre, String apellido1, String apellido2, long telefono, TipoEnum tipo, Date nacimiento, String dni) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.tipo = tipo;
        this.nacimiento = nacimiento;
        this.dni = dni;
    }

    public Contacto() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public long getTelefono() {
        return telefono;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public String getDni() {
        return dni;
    }
    
    @Override
    public String toString() {
       return nombre + ", " +apellido1+ ", " +apellido2+ ", " + telefono + ", " + tipo + ", " + nacimiento + ", " + dni; //To change body of generated methods, choose Tools | Templates.
    }
}