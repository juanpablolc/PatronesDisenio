package patronesdisenio;

public class Pago implements ComponenteDecorator {

  private double valor;
  private int idConductor, idPasajero;

  public String getInformacion() {
    return "Valor: " + this.valor + "\nId conductor: " + this.idConductor + "\nId pasajero: " + this.idPasajero;
  }

  public void setInformacion(String informacion) {
    String[] informacionArray = informacion.split(",");

    for (int i = 0; i < informacionArray.length; i++) {
      switch (informacionArray[i]) {
        case "valor":
          this.valor = Double.parseDouble(informacionArray[i + 1]);
          break;
        case "idConductor":
          this.idConductor = Integer.parseInt(informacionArray[i + 1]);
          break;
        case "idPasajero":
          this.idPasajero = Integer.parseInt(informacionArray[i + 1]);
          break;
      }
    }
  }
}
