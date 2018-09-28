package patronesdisenio;

public class PagoPSECuentaBancaria extends PagoPSE {

  private String numCuenta;

  public PagoPSECuentaBancaria(ComponenteDecorator pago) {
    super(pago);
  }

  @Override
  public String getInformacion() {
    return super.getInformacion() + this.getNuevaInformacion();
  }

  @Override
  public void setInformacion(String informacion) {
    super.setInformacion(informacion);
    String[] informacionArray = informacion.split(",");
    for (int i = 0; i < informacionArray.length; i++) {
      switch (informacionArray[i]) {
        case "numCuenta":
          this.numCuenta = informacionArray[i + 1];
          break;
      }
    }
  }

  public String getNuevaInformacion() {
    return "\nNúmero de cuenta: " + this.numCuenta;
  }
}
