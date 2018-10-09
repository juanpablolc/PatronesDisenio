package patronesdisenio;

import java.util.ArrayList;

public class FacadeSingleton {

  private static FacadeSingleton unicaInstancia;

  FlyweightFactory usuarioFactory = new FlyweightFactory();
  ArrayList<Ruta> rutas = new ArrayList<>();
  ArrayList<Reserva> reservas = new ArrayList<>();
  ArrayList<ComponenteDecorator> pagos = new ArrayList<>();

  public static FacadeSingleton reemplazarConstructor() {
    if (unicaInstancia == null) {
      unicaInstancia = new FacadeSingleton();
    }
    return unicaInstancia;
  }

  // Usuarios
  public String adicionarConductor(String correo, String nombre, String contrasenia) {
    if (this.usuarioFactory.getUsuario(correo) == null) {
      FlyweightUsuario conductor = new Conductor();
      conductor.adicionar(correo, nombre, contrasenia);
      this.usuarioFactory.addUsuario(correo, conductor);
      return nombre + " ha sido registrado exitósamente como conductor.";
    } else {
      return "El correo " + correo + " ya está registrado como conductor.";
    }
  }

  public String adicionarPasajero(String correo, String nombre, String contrasenia) {
    if (this.usuarioFactory.getUsuario(correo) == null) {
      FlyweightUsuario pasajero = new Pasajero();
      pasajero.adicionar(correo, nombre, contrasenia);
      this.usuarioFactory.addUsuario(correo, pasajero);
      return nombre + " ha sido registrado exitósamente como pasajero.";
    } else {
      return "El correo " + correo + " ya está registrado como pasajero.";
    }
  }

  public String adicionarAdministrador(String correo, String nombre, String contrasenia) {
    if (this.usuarioFactory.getUsuario(correo) == null) {
      FlyweightUsuario administrador = new AdministradorAdapter();
      administrador.adicionar(correo, nombre, contrasenia);
      this.usuarioFactory.addUsuario(correo, administrador);
      return nombre + " ha sido registrado exitósamente como administrador.";
    } else {
      return "El correo " + correo + " ya está registrado como administrador.";
    }
  }

  public String getNombreUsuario(String correo) {
    return this.usuarioFactory.getUsuario(correo).getNombre();
  }

  public String getTipoUsuario(String correo) {
    if (this.usuarioFactory.getUsuario(correo) instanceof Conductor) {
      return "conductor";
    } else if (this.usuarioFactory.getUsuario(correo) instanceof Pasajero) {
      return "pasajero";
    } else if (this.usuarioFactory.getUsuario(correo) instanceof AdministradorAdapter) {
      return "administrador";
    }
    return null;
  }

  // Rutas
  public String registrarRuta(String nombre, String correo) {
    Ruta ruta = new Ruta(nombre, this.usuarioFactory.getUsuario(correo));
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

  public boolean existenRutasConductor(String correo) {
    for (Ruta ruta : this.rutas) {
      if (ruta.getConductor().getCorreo().equals(this.usuarioFactory.getUsuario(correo).getCorreo())) {
        return true;
      }
    }
    return false;
  }

  public String listarRutasConductor(String correo) {
    String rutas = "Rutas conductor " + this.usuarioFactory.getUsuario(correo).getNombre() + "\n\n";
    int contadorRutas = 1;
    for (Ruta ruta : this.rutas) {
      if (ruta.getConductor().getCorreo().equals(this.usuarioFactory.getUsuario(correo).getCorreo())) {
        rutas += "[" + contadorRutas + "] " + ruta.obtenerInformacion() + "\n";
        contadorRutas++;
      }
    }
    return rutas;
  }

  public String actualizarRuta(String correo, int numRuta, String nombre) {
    int contadorRutasConductor = 1;
    for (int i = 0; i < this.rutas.size(); i++) {
      if (this.rutas.get(i).getConductor().getCorreo().equals(this.usuarioFactory.getUsuario(correo).getCorreo())) {
        if (contadorRutasConductor == numRuta) {
          this.rutas.get(i).setNombre(nombre);
          return "La ruta ha sido modificada exitósamente.";
        }
        contadorRutasConductor++;
      }
    }
    return "Número de ruta no válido.";
  }

  public String eliminarRuta(String correo, int numRuta) {
    int contadorRutasConductor = 1;
    for (int i = 0; i < this.rutas.size(); i++) {
      if (this.rutas.get(i).getConductor().getCorreo().equals(this.usuarioFactory.getUsuario(correo).getCorreo())) {
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
  public String registrarReserva(int idRuta, String correoPasajero, int puestosReservados) {
    idRuta--;
    if (rutas.get(idRuta) != null) {
      Reserva reserva = new Reserva(idRuta, correoPasajero, puestosReservados);
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
        reservas += "Pasajero: " + this.usuarioFactory.getUsuario(this.reservas.get(i).getCorreoPasajero()).getNombre();
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

  // Pagos
  public String registrarPagoTarjeta(
          String valor,
          String idConductor,
          String idPasajero,
          String numTarjeta,
          String CCV,
          String fechaCaducidad
  ) {
    String informacion = "";
    informacion += "valor," + valor + ",";
    informacion += "idConductor," + idConductor + ",";
    informacion += "idPasajero," + idPasajero + ",";
    informacion += "numTarjeta," + numTarjeta + ",";
    informacion += "CCV," + CCV + ",";
    informacion += "fechaCaducidad," + fechaCaducidad;
    ComponenteDecorator pago = new PagoPSETarjeta(new Pago());
    pago.setInformacion(informacion);
    this.pagos.add(pago);
    return "El pago ha sido registrado exitósamente.";
  }

  public String registrarPagoCuentaBancaria(
          String valor,
          String idConductor,
          String idPasajero,
          String numCuenta
  ) {
    String informacion = "";
    informacion += "valor," + valor + ",";
    informacion += "idConductor," + idConductor + ",";
    informacion += "idPasajero," + idPasajero + ",";
    informacion += "numCuenta," + numCuenta;
    ComponenteDecorator pago = new PagoPSECuentaBancaria(new Pago());
    pago.setInformacion(informacion);
    this.pagos.add(pago);
    return "El pago ha sido registrado exitósamente.";
  }

  // Administrador
  public boolean existenUsuarios() {
    return !this.usuarioFactory.getUsuarios().isEmpty();
  }

  public String listarUsuarios() {
    String usuarios = "USUARIOS REGISTRADOS: \n\n";

    for (FlyweightUsuario flyweightUsuario : this.usuarioFactory.getUsuarios().values()) {
      usuarios += "(" + this.getTipoUsuario(flyweightUsuario.getCorreo()) + ")"
              + " Correo: " + this.usuarioFactory.getUsuario(flyweightUsuario.getCorreo()).getCorreo()
              + " - Nombre: " + this.usuarioFactory.getUsuario(flyweightUsuario.getCorreo()).getNombre()
              + " - Contraseña: " + this.usuarioFactory.getUsuario(flyweightUsuario.getCorreo()).getContrasenia() + "\n";
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

  public boolean existenPagos() {
    return this.pagos.size() > 0;
  }

  public String listarPagos() {
    String pagos = "PAGOS REGISTRADOS:\n\n";
    for (ComponenteDecorator pago : this.pagos) {
      if (pago instanceof PagoPSETarjeta) {
        pagos += "Pago tarjeta: ";
      } else if (pago instanceof PagoPSECuentaBancaria) {
        pagos += "Pago cuenta bancaria: ";
      }
      pagos += pago.getInformacion() + "\n\n";
    }
    return pagos;
  }

  // Getters
  public FlyweightFactory getUsuarioFactory() {
    return this.usuarioFactory;
  }

  public ArrayList<Ruta> getRutas() {
    return this.rutas;
  }

  public ArrayList<Reserva> getReservas() {
    return this.reservas;
  }
}
