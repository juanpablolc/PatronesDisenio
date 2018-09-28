package patronesdisenio;

public class Reserva {

  private int idRuta;
  private int idPasajero;
  private int puestosReservados;

  public Reserva(int idRuta, int idPasajero, int puestosReservados) {
    this.idRuta = idRuta;
    this.idPasajero = idPasajero;
    this.puestosReservados = puestosReservados;
  }

  public int getIdRuta() {
    return idRuta;
  }

  public void setIdRuta(int idRuta) {
    this.idRuta = idRuta;
  }

  public int getIdPasajero() {
    return idPasajero;
  }

  public void setIdPasajero(int idPasajero) {
    this.idPasajero = idPasajero;
  }

  public int getPuestosReservados() {
    return puestosReservados;
  }

  public void setPuestosReservados(int puestosReservados) {
    this.puestosReservados = puestosReservados;
  }
}
