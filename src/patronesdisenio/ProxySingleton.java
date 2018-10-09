package patronesdisenio;

import java.util.HashMap;

public class ProxySingleton implements ProxyInterface {

  private static ProxySingleton unicaInstancia;
  private HashMap<String, FlyweightUsuario> copiaUsuarios = new HashMap<>();
  private FacadeSingleton facadeSingleton = FacadeSingleton.reemplazarConstructor();

  public static ProxySingleton reemplazarConstructor() {
    if (ProxySingleton.unicaInstancia == null) {
      ProxySingleton.unicaInstancia = new ProxySingleton();
    }
    return ProxySingleton.unicaInstancia;
  }

  public String login(String correo, String contrasenia) {
    for (FlyweightUsuario fu : this.copiaUsuarios.values()) {
      if (fu.getCorreo().equals(correo) && fu.getContrasenia().equals(contrasenia)) {
        if (fu instanceof Conductor) {
          return "conductor";
        } else if (fu instanceof Pasajero) {
          return "pasajero";
        } else if (fu instanceof AdministradorAdapter) {
          return "administrador";
        }
      }
    }
    return null;
  }

  public String register(String tipoUsuario, String correo, String nombre, String contrasenia) {
    String mensaje = "";
    switch (tipoUsuario) {
      case "conductor":
        mensaje = this.facadeSingleton.adicionarConductor(correo, nombre, contrasenia);
        break;
      case "pasajero":
        mensaje = this.facadeSingleton.adicionarPasajero(correo, nombre, contrasenia);
        break;
      case "administrador":
        mensaje = this.facadeSingleton.adicionarAdministrador(correo, nombre, contrasenia);
        break;
    }
    this.copiaUsuarios = this.facadeSingleton.getUsuarioFactory().getUsuarios();
    return mensaje;
  }
}
