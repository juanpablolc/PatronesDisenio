package patronesdisenio;

public class Pasajero extends Usuario {

  public String consultar() {
    return this.getCorreo() + "," + this.getNombre() + "," + this.getContrasenia();
  }

  public void eliminar() {

  }
}
