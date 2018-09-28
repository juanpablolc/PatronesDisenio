package patronesdisenio;

public class Conductor extends Usuario {

  public String consultar() {
    return this.getCorreo() + "," + this.getNombre() + "," + this.getContrasenia();
  }

  public void eliminar() {

  }
}
