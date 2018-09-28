package patronesdisenio;

import java.util.HashMap;

public class FlyweightFactory {

  private HashMap<Integer, FlyweightUsuario> usuarios = new HashMap<>();

  public HashMap<Integer, FlyweightUsuario> getUsuarios() {
    return this.usuarios;
  }

  public FlyweightUsuario getUsuario(int index) {
    return (FlyweightUsuario) usuarios.get(index);
  }

  public void addUsuario(int index, FlyweightUsuario flyweightUsuario) {
    this.usuarios.put(index, flyweightUsuario);
  }
}
