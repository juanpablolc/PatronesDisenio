package patronesdisenio;

public abstract class Usuario implements FlyweightUsuario {

  private String correo;
  private String nombre;
  private String contrasenia;

  public void adicionar(String correo, String nombre, String contrasenia) {
    this.correo = correo;
    this.nombre = nombre;
    this.contrasenia = contrasenia;
  }

  public void modificar(String correo, String nombre, String contrasenia) {
    this.correo = correo;
    this.nombre = nombre;
    this.contrasenia = contrasenia;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }
}
