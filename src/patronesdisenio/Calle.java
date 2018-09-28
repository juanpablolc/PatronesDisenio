package patronesdisenio;

public class Calle implements Componente {

  private String nombre;
  private double coordenadaXOrigen;
  private double coordenadaYOrigen;
  private double coordenadaXDestino;
  private double coordenadaYDestino;
  private double distancia; // metros
  private int tiempoRecorrido; // segundos

  public Calle() {
    this.setNombre("");
    this.setCoordenadaXOrigen(0);
    this.setCoordenadaYOrigen(0);
    this.setCoordenadaXDestino(0);
    this.setCoordenadaYDestino(0);
    this.setDistancia(0);
    this.setTiempoRecorrido(0);
  }

  public Calle(
          String nombre,
          double coordenadaXOrigen,
          double coordenadaYOrigen,
          double coordenadaXDestino,
          double coordenadaYDestino,
          double distancia,
          int tiempoRecorrido
  ) {
    this.setNombre(nombre);
    this.setCoordenadaXOrigen(coordenadaXOrigen);
    this.setCoordenadaYOrigen(coordenadaYOrigen);
    this.setCoordenadaXDestino(coordenadaXDestino);
    this.setCoordenadaYDestino(coordenadaYDestino);
    this.setDistancia(distancia);
    this.setTiempoRecorrido(tiempoRecorrido);
  }

  @Override
  public String obtenerInformacion() {
    return this.nombre.toUpperCase() + "\n"
            + "Coordenadas origen: " + this.coordenadaXOrigen + ", " + this.coordenadaYOrigen + "\n"
            + "Coordenadas destino: " + this.coordenadaXDestino + ", " + this.coordenadaYDestino + "\n"
            + "Distancia: " + this.distancia + "\n"
            + "Tiempo del recorrido: " + this.tiempoRecorrido + "\n";
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getCoordenadaXOrigen() {
    return coordenadaXOrigen;
  }

  public void setCoordenadaXOrigen(double coordenadaXOrigen) {
    this.coordenadaXOrigen = coordenadaXOrigen;
  }

  public double getCoordenadaYOrigen() {
    return coordenadaYOrigen;
  }

  public void setCoordenadaYOrigen(double coordenadaYOrigen) {
    this.coordenadaYOrigen = coordenadaYOrigen;
  }

  public double getCoordenadaXDestino() {
    return coordenadaXDestino;
  }

  public void setCoordenadaXDestino(double coordenadaXDestino) {
    this.coordenadaXDestino = coordenadaXDestino;
  }

  public double getCoordenadaYDestino() {
    return coordenadaYDestino;
  }

  public void setCoordenadaYDestino(double coordenadaYDestino) {
    this.coordenadaYDestino = coordenadaYDestino;
  }

  public double getDistancia() {
    return distancia;
  }

  public void setDistancia(double distancia) {
    this.distancia = distancia;
  }

  public int getTiempoRecorrido() {
    return tiempoRecorrido;
  }

  public void setTiempoRecorrido(int tiempoRecorrido) {
    this.tiempoRecorrido = tiempoRecorrido;
  }

}
