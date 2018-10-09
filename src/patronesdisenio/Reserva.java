package patronesdisenio;

public class Reserva {

  private int idRuta;
  private String correoPasajero;
  private int puestosReservados;

  public Reserva(int idRuta, String idPasajero, int puestosReservados) {
    this.idRuta = idRuta;
    this.correoPasajero = idPasajero;
    this.puestosReservados = puestosReservados;
  }

  public int getIdRuta() {
    return idRuta;
  }

  public void setIdRuta(int idRuta) {
    this.idRuta = idRuta;
  }

  public String getCorreoPasajero() {
    return correoPasajero;
  }

  public void setCorreoPasajero(String correoPasajero) {
    this.correoPasajero = correoPasajero;
  }

  public int getPuestosReservados() {
    return puestosReservados;
  }

  public void setPuestosReservados(int puestosReservados) {
    this.puestosReservados = puestosReservados;
  }
}
