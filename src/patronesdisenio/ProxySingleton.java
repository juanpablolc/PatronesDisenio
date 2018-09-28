package patronesdisenio;

public class ProxySingleton implements ProxyInterface {

  private static ProxySingleton unicaInstancia;
  private Facade facade;

  private ProxySingleton() {
    ProxySingleton.unicaInstancia = null;
    this.facade = new Facade();
  }

  public static ProxySingleton reemplazarConstructor() {
    if (ProxySingleton.unicaInstancia == null) {
      ProxySingleton.unicaInstancia = new ProxySingleton();
    }
    return ProxySingleton.unicaInstancia;
  }

  public int login(String correo, String contrasenia) {
    for (int key : this.facade.getUsuarios().getUsuarios().keySet()) {
      if (this.facade.getUsuarios().getUsuario(key).getCorreo().equals(correo)
              && this.facade.getUsuarios().getUsuario(key).getContrasenia().equals(contrasenia)) {
        return key;
      }
    }
    return -1;
  }

  public Facade getFacade() {
    return this.facade;
  }
}
