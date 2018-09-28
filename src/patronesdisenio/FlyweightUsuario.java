package patronesdisenio;

public interface FlyweightUsuario {

  public void adicionar(String correo, String nombre, String contrasenia);

  public void modificar(String correo, String nombre, String contrasenia);

  public String consultar();

  public void eliminar();

  public String getCorreo();

  public void setCorreo(String correo);

  public String getNombre();

  public void setNombre(String nombre);

  public String getContrasenia();

  public void setContrasenia(String contrasenia);
}
