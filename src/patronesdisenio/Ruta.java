package patronesdisenio;

import java.util.ArrayList;

public class Ruta implements ComponenteComposite {

  private String nombre;
  private FlyweightUsuario conductor;
  private ArrayList<ComponenteComposite> componentes;

  public Ruta() {
    this.setNombre("");
    this.setConductor(null);
    this.setComponentes(new ArrayList());
  }

  public Ruta(String nombre, FlyweightUsuario conductor) {
    this.setNombre(nombre);
    this.setConductor(conductor);
    this.setComponentes(new ArrayList());
  }

  @Override
  public String obtenerInformacion() {
    String informacion = "RUTA: " + this.nombre + "\nCALLES: \n";

    for (ComponenteComposite componente : componentes) {
      informacion += componente.obtenerInformacion();
    }

    return informacion;
  }

  public void add(ComponenteComposite componente) {
    this.componentes.add(componente);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public FlyweightUsuario getConductor() {
    return this.conductor;
  }

  public void setConductor(FlyweightUsuario conductor) {
    this.conductor = conductor;
  }

  public ArrayList<ComponenteComposite> getComponentes() {
    return componentes;
  }

  public void setComponentes(ArrayList<ComponenteComposite> componentes) {
    this.componentes = componentes;
  }
}
