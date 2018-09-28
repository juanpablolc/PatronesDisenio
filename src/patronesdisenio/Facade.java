package patronesdisenio;

import java.util.ArrayList;

public class Facade {

  FlyweightFactory usuarios = new FlyweightFactory();
  ArrayList<Ruta> rutas = new ArrayList<>();
  ArrayList<Reserva> reservas = new ArrayList<>();
  int maxUsuario;

  public Facade() {
    this.maxUsuario = 0;
  }

  // Usuarios
  public String adicionarConductor(String correo, String nombre, String contrasenia) {
    if (!this.existeUsuario(correo, "conductor")) {
      FlyweightUsuario conductor = new Conductor();
      conductor.adicionar(correo, nombre, contrasenia);
      this.usuarios.addUsuario(maxUsuario, conductor);
      this.maxUsuario++;
      return nombre + " ha sido registrado exitósamente como conductor.";
    } else {
      return "El correo " + correo + " ya está registrado como conductor.";
    }
  }

  public String adicionarPasajero(String correo, String nombre, String contrasenia) {
    if (!this.existeUsuario(correo, "pasajero")) {
      FlyweightUsuario pasajero = new Pasajero();
      pasajero.adicionar(correo, nombre, contrasenia);
      this.usuarios.addUsuario(maxUsuario, pasajero);
      this.maxUsuario++;
      return nombre + " ha sido registrado exitósamente como pasajero.";
    } else {
      return "El correo " + correo + " ya está registrado como pasajero.";
    }
  }

  public String adicionarAdministrador(String correo, String nombre, String contrasenia) {
    if (!this.existeUsuario(correo, "administrador")) {
      FlyweightUsuario administrador = new AdministradorAdapter();
      administrador.adicionar(correo, nombre, contrasenia);
      this.usuarios.addUsuario(maxUsuario, administrador);
      this.maxUsuario++;
      return nombre + " ha sido registrado exitósamente como administrador.";
    } else {
      return "El correo " + correo + " ya está registrado como administrador.";
    }
  }

  public String getNombreUsuario(int id) {
    return this.usuarios.getUsuario(id).getNombre();
  }

  public String getTipoUsuario(int id) {
    if (this.usuarios.getUsuario(id) instanceof Conductor) {
      return "conductor";
    } else if (this.usuarios.getUsuario(id) instanceof Pasajero) {
      return "pasajero";
    } else if (this.usuarios.getUsuario(id) instanceof AdministradorAdapter) {
      return "administrador";
    }
    return null;
  }

  private boolean existeUsuario(String correo, String tipoUsuario) {
    for (int key : this.usuarios.getUsuarios().keySet()) {
      if (this.usuarios.getUsuario(key).getCorreo().equals(correo)) {
        if ((tipoUsuario.equals("conductor") && this.usuarios.getUsuario(key) instanceof Conductor)
                || (tipoUsuario.equals("pasajero") && this.usuarios.getUsuario(key) instanceof Pasajero)
                || (tipoUsuario.equals("administrador") && this.usuarios.getUsuario(key) instanceof AdministradorAdapter)) {
          return true;
        }
      }
    }
    return false;
  }

  // Rutas
  public String registarRuta(String nombre, int idUsuario) {
    Ruta ruta = new Ruta(nombre, this.usuarios.getUsuario(idUsuario));
    this.rutas.add(ruta);
    return "La ruta ha sido registrada exitósamente.";
  }

  public int getUltimaRutaRegistrada() {
    return this.rutas.size() - 1;
  }

  public String aniadirCalle(
          int idRuta,
          String nombre,
          double coordenadaXOrigen,
          double coordenadaYOrigen,
          double coordenadaXDestino,
          double coordenadaYDestino,
          double distancia,
          int tiempoRecorrido
  ) {
    this.rutas.get(idRuta).add(new Calle(
            nombre,
            coordenadaXOrigen,
            coordenadaYOrigen,
            coordenadaXDestino,
            coordenadaYDestino,
            distancia,
            tiempoRecorrido
    ));
    return "Calle " + nombre + " añadida exitósamente.";
  }

  public boolean existenRutasConductor(int idConductor) {
    for (Ruta ruta : this.rutas) {
      if (ruta.getConductor().getCorreo().equals(this.usuarios.getUsuario(idConductor).getCorreo())) {
        return true;
      }
    }
    return false;
  }

  public String listarRutasConductor(int idConductor) {
    String rutas = "Rutas conductor " + this.usuarios.getUsuario(idConductor).getNombre() + "\n\n";
    int contadorRutas = 1;
    for (Ruta ruta : this.rutas) {
      if (ruta.getConductor().getCorreo().equals(this.usuarios.getUsuario(idConductor).getCorreo())) {
        rutas += "[" + contadorRutas + "] " + ruta.obtenerInformacion() + "\n";
        contadorRutas++;
      }
    }
    return rutas;
  }

  public String actualizarRuta(int idConductor, int numRuta, String nombre) {
    int contadorRutasConductor = 1;
    for (int i = 0; i < this.rutas.size(); i++) {
      if (this.rutas.get(i).getConductor().getCorreo().equals(this.usuarios.getUsuario(idConductor).getCorreo())) {
        if (contadorRutasConductor == numRuta) {
          this.rutas.get(i).setNombre(nombre);
          return "La ruta ha sido modificada exitósamente.";
        }
        contadorRutasConductor++;
      }
    }
    return "Número de ruta no válido.";
  }

  public String eliminarRuta(int idConductor, int numRuta) {
    int contadorRutasConductor = 1;
    for (int i = 0; i < this.rutas.size(); i++) {
      if (this.rutas.get(i).getConductor().getCorreo().equals(this.usuarios.getUsuario(idConductor).getCorreo())) {
        if (contadorRutasConductor == numRuta) {
          this.rutas.remove(i);
          return "La ruta ha sido eliminada exitósamente.";
        }
        contadorRutasConductor++;
      }
    }
    return "Número de ruta no válido.";
  }

  public boolean existenRutas() {
    return rutas.size() > 0;
  }

  // Reservas
  public String registrarReserva(int idRuta, int idPasajero, int puestosReservados) {
    idRuta--;
    if (rutas.get(idRuta) != null) {
      Reserva reserva = new Reserva(idRuta, idPasajero, puestosReservados);
      this.reservas.add(reserva);
      return "La reserva ha sido registrada exitósamente.";
    } else {
      return "Número de ruta no válido.";
    }
  }

  public String listarReservas() {
    if (this.existenReservas()) {
      String reservas = "Reservas: \n\n";

      for (int i = 0; i < this.reservas.size(); i++) {
        reservas += "[" + (i + 1) + "] Ruta: " + this.rutas.get(this.reservas.get(i).getIdRuta()).getNombre() + " - ";
        reservas += "Pasajero: " + this.usuarios.getUsuario(this.reservas.get(i).getIdPasajero()).getNombre();
      }

      return reservas;
    } else {
      return "No hay reservas registradas.";
    }
  }

  public String modificarReserva(int idReserva, int puestosReservados) {
    idReserva--;
    if (this.reservas.get(idReserva) != null) {
      this.reservas.get(idReserva).setPuestosReservados(puestosReservados);
      return "La reserva ha sido modificada exitósamente.";
    } else {
      return "Número de reserva no válido";
    }
  }

  public String eliminarReserva(int idReserva) {
    idReserva--;
    if (this.reservas.get(idReserva) != null) {
      this.reservas.remove(idReserva);
      return "La reserva ha sido eliminada exitósamente.";
    } else {
      return "Número de reserva no válido";
    }
  }

  public boolean existenReservas() {
    return this.reservas.size() > 0;
  }

  // Administrador
  public boolean existenUsuarios() {
    return this.maxUsuario > 0;
  }

  public String listarUsuarios() {
    String usuarios = "USUARIOS REGISTRADOS: \n\n";

    for (int i = 0; i < this.maxUsuario; i++) {
      usuarios += "(" + this.getTipoUsuario(i) + ")"
              + " Correo: " + this.usuarios.getUsuario(i).getCorreo()
              + " - Nombre: " + this.usuarios.getUsuario(i).getNombre()
              + " - Contraseña: " + this.usuarios.getUsuario(i).getContrasenia() + "\n";
    }

    return usuarios;
  }

  public String listarRutas() {
    String rutas = "RUTAS REGISTRADAS: \n\n";

    for (int i = 0; i < this.rutas.size(); i++) {
      rutas += "[" + (i + 1) + "]" + this.rutas.get(i).obtenerInformacion() + "\n";
    }

    return rutas;
  }

  // Getters
  public FlyweightFactory getUsuarios() {
    return this.usuarios;
  }

  public ArrayList<Ruta> getRutas() {
    return this.rutas;
  }

  public ArrayList<Reserva> getReservas() {
    return this.reservas;
  }
}
