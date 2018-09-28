package patronesdisenio;

abstract class PagoPSE implements ComponenteDecorator {

  protected ComponenteDecorator pago;

  public PagoPSE(ComponenteDecorator pago) {
    this.pago = pago;
  }

  public String getInformacion() {
    return this.pago.getInformacion();
  }

  public void setInformacion(String informacion) {
    this.pago.setInformacion(informacion);
  }
}
