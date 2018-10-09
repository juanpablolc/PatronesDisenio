package patronesdisenio;

public interface ProxyInterface {

  public String login(String correo, String contrasenia);
  public String register(String tipoUsuario, String correo, String nombre, String contrasenia);
}
