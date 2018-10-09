package patronesdisenio;

import java.util.HashMap;

public class FlyweightFactory {

  private HashMap<String, FlyweightUsuario> usuarios = new HashMap<>();

  public HashMap<String, FlyweightUsuario> getUsuarios() {
    return this.usuarios;
  }

  public FlyweightUsuario getUsuario(String correo) {
    return (FlyweightUsuario) usuarios.get(correo);
  }

  public void addUsuario(String correo, FlyweightUsuario flyweightUsuario) {
    this.usuarios.put(correo, flyweightUsuario);
  }
}
