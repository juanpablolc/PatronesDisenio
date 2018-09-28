package patronesdisenio;

public class AdministradorAdapter extends Usuario {

  private Administrador administrador;

  public AdministradorAdapter() {
    this.administrador = new Administrador();
  }

  public String consultar() {
    return this.getCorreo() + "," + this.getNombre() + "," + this.getContrasenia();
  }

  public void eliminar() {

  }
}
