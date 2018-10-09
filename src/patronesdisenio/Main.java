package patronesdisenio;

import javax.swing.JOptionPane;

public class Main {

  public static void main(String[] args) {
    ProxySingleton proxySingleton = ProxySingleton.reemplazarConstructor();
    FacadeSingleton facadeSingleton = FacadeSingleton.reemplazarConstructor();

    // Datos de prueba
    facadeSingleton.adicionarConductor("conductor@usab", "Administrador", "123");
    facadeSingleton.adicionarPasajero("pasajero@usab", "Pasajero", "123");
    facadeSingleton.adicionarAdministrador("admin@usab", "Administrador", "123");
    facadeSingleton.registrarRuta("Unisabana - Portal", "conductor@usab");
    facadeSingleton.aniadirCalle(0, "Autopista norte", 250, 0, 170, 0, 80, 100);

    // Variables para el menú 1
    int opcion;
    String correo, nombre, contrasenia, mensaje;
    String tipoUsuario = "";

    while (true) {
      opcion = Integer.parseInt(JOptionPane.showInputDialog(
              "Bienvenido al segundo parcial de Software II.\n\n"
              + "Seleccione una opción: \n\n"
              + "1. Registrar usuario\n"
              + "2. Iniciar sesión\n\n"
              + "0. Salir"
      ));

      switch (opcion) {
        case 1:
          // Variables para el menú 2
          int opcion2;

          while (true) {
            opcion2 = Integer.parseInt(JOptionPane.showInputDialog(
                    "¿Qué tipo de usuario desea registrar?\n\n"
                    + "1. Conductor\n"
                    + "2. Pasajero\n"
                    + "3. Administrador\n\n"
                    + "0. Atrás"
            ));

            if (opcion2 == 0) {
              break;
            }

            correo = JOptionPane.showInputDialog("Ingrese el correo institucional: ");
            nombre = JOptionPane.showInputDialog("Ingrese el nombre: ");
            contrasenia = JOptionPane.showInputDialog("Ingrese la contraseña: ");

            switch (opcion2) {
              case 1:
                tipoUsuario = "conductor";
                break;
              case 2:
                tipoUsuario = "pasajero";
                break;
              case 3:
                tipoUsuario = "administrador";
                break;
            }

            mensaje = proxySingleton.register(tipoUsuario, correo, nombre, contrasenia);
            JOptionPane.showMessageDialog(null, mensaje);
          }
          break;
        case 2:
          correo = JOptionPane.showInputDialog("Ingrese el correo institucional: ");
          contrasenia = JOptionPane.showInputDialog("Ingrese la contraseña: ");

          // Variables para el menú 3
          int opcion3 = -1;
          int idRuta;
          int numRuta;
          int puestosReservados;
          String nombreRuta;
          String valor,
           idConductor,
           idPasajero,
           numTarjeta,
           CCV,
           fechaCaducidad,
           numCuenta;

          tipoUsuario = proxySingleton.login(correo, contrasenia);

          if (tipoUsuario == null) {
            JOptionPane.showMessageDialog(null, "Correo o contraseña no válidos.");
            continue;
          }

          switch (tipoUsuario) {
            case "conductor":
              while (opcion3 != 0) {
                opcion3 = Integer.parseInt(JOptionPane.showInputDialog(
                        "Bienvenido " + facadeSingleton.getNombreUsuario(correo) + " (Conductor)\n\n"
                        + "Seleccione una opción: \n\n"
                        + "1. Registrar ruta\n"
                        + "2. Listar rutas\n"
                        + "3. Actualizar ruta\n"
                        + "4. Eliminar ruta\n\n"
                        + "0. Cerrar sesión"
                ));

                switch (opcion3) {
                  case 1:
                    nombreRuta = JOptionPane.showInputDialog("Ingrese el nombre de la ruta: ");
                    mensaje = facadeSingleton.registrarRuta(nombreRuta, correo);
                    JOptionPane.showMessageDialog(null, mensaje);
                    idRuta = facadeSingleton.getUltimaRutaRegistrada();

                    // Variables para el menú 4
                    int opcion4 = -1;
                    String nombreCalle;
                    double coordenadaXOrigen;
                    double coordenadaYOrigen;
                    double coordenadaXDestino;
                    double coordenadaYDestino;
                    double distancia;
                    int tiempoRecorrido;

                    while (opcion4 != 1) {
                      if (opcion4 == -1) {
                        JOptionPane.showMessageDialog(null, "Debe añadir por lo menos una calle.");
                      }

                      nombreCalle = JOptionPane.showInputDialog("Ingrese el nombre de la calle: ");
                      coordenadaXOrigen = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la coordenada 'x' de origen: "));
                      coordenadaYOrigen = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la coordenada 'y' de origen: "));
                      coordenadaXDestino = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la coordenada 'x' de destino: "));
                      coordenadaYDestino = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la coordenada 'y' de destino: "));
                      distancia = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la distancia: "));
                      tiempoRecorrido = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el tiempo de recorrido a 60km/h"));
                      mensaje = facadeSingleton.aniadirCalle(idRuta, nombreCalle, coordenadaXOrigen, coordenadaYOrigen, coordenadaXDestino, coordenadaYDestino, distancia, tiempoRecorrido);
                      JOptionPane.showMessageDialog(null, mensaje);
                      opcion4 = JOptionPane.showConfirmDialog(null, "¿Desea añadir otra calle?", "TallerFacade", JOptionPane.YES_NO_OPTION);
                    }
                    break;
                  case 2:
                    if (facadeSingleton.existenRutasConductor(correo)) {
                      JOptionPane.showMessageDialog(null, facadeSingleton.listarRutasConductor(correo));
                    } else {
                      JOptionPane.showMessageDialog(null, facadeSingleton.getNombreUsuario(correo) + " no tiene ninguna ruta registrada.");
                    }
                    break;
                  case 3:
                    if (facadeSingleton.existenRutasConductor(correo)) {
                      numRuta = Integer.parseInt(JOptionPane.showInputDialog(facadeSingleton.listarRutasConductor(correo) + "Ingrese el número de la ruta que desea actualizar: "));
                      nombreRuta = JOptionPane.showInputDialog("Ingrese el nuevo nombre para la ruta: ");
                      mensaje = facadeSingleton.actualizarRuta(correo, numRuta, nombreRuta);
                      JOptionPane.showMessageDialog(null, mensaje);
                    } else {
                      JOptionPane.showMessageDialog(null, facadeSingleton.getNombreUsuario(correo) + " no tiene ninguna ruta registrada.");
                    }
                    break;
                  case 4:
                    if (facadeSingleton.existenRutasConductor(correo)) {
                      numRuta = Integer.parseInt(JOptionPane.showInputDialog(facadeSingleton.listarRutasConductor(correo) + "Ingrese el número de la ruta que desea eliminar: "));
                      mensaje = facadeSingleton.eliminarRuta(correo, numRuta);
                      JOptionPane.showMessageDialog(null, mensaje);
                    } else {
                      JOptionPane.showMessageDialog(null, facadeSingleton.getNombreUsuario(correo) + " no tiene ninguna ruta registrada.");
                    }
                    break;
                }
              }
              break;
            case "pasajero":
              while (opcion3 != 0) {
                opcion3 = Integer.parseInt(JOptionPane.showInputDialog(
                        "Bienvenido " + facadeSingleton.getNombreUsuario(correo) + " (Pasajero)\n\n"
                        + "Seleccione una opción: \n\n"
                        + "1. Registrar reserva\n"
                        + "2. Listar reservas\n"
                        + "3. Modificar reserva\n"
                        + "4. Eliminar reserva\n"
                        + "5. Realizar un pago PSE con tarjeta\n"
                        + "6. Realizar un pago PSE con cuenta bancaria\n\n"
                        + "0. Cerrar sesión"
                ));
                switch (opcion3) {
                  case 1:
                    idRuta = Integer.parseInt(JOptionPane.showInputDialog(facadeSingleton.listarRutas() + "Ingrese el número de la ruta que desea reservar: "));
                    puestosReservados = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de puestos que desea reservar: "));
                    mensaje = facadeSingleton.registrarReserva(idRuta, correo, puestosReservados);
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                  case 2:
                    mensaje = facadeSingleton.listarReservas();
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                  case 3:
                    idRuta = Integer.parseInt(JOptionPane.showInputDialog(facadeSingleton.listarRutas() + "Ingrese el número de la ruta que desea modificar: "));
                    puestosReservados = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad de puestos a reservar: "));
                    mensaje = facadeSingleton.modificarReserva(idRuta, puestosReservados);
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                  case 4:
                    idRuta = Integer.parseInt(JOptionPane.showInputDialog(facadeSingleton.listarRutas() + "Ingrese el número de la ruta que desea eliminar: "));
                    mensaje = facadeSingleton.eliminarReserva(idRuta);
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                  case 5:
                    valor = JOptionPane.showInputDialog("Ingrese el valor del pago: ");
                    idConductor = JOptionPane.showInputDialog("Ingrese el id del conductor: ") + ",";
                    idPasajero = JOptionPane.showInputDialog("Ingrese el id del pasajero: ") + ",";
                    numTarjeta = JOptionPane.showInputDialog("Ingrese el número de la tarjeta: ") + ",";
                    CCV = JOptionPane.showInputDialog("Ingrese el CCV: ") + ",";
                    fechaCaducidad = JOptionPane.showInputDialog("Ingrese la fecha de caducidad: ");
                    mensaje = facadeSingleton.registrarPagoTarjeta(valor, idConductor, idPasajero, numTarjeta, CCV, fechaCaducidad);
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                  case 6:
                    valor = JOptionPane.showInputDialog("Ingrese el valor del pago: ");
                    idConductor = JOptionPane.showInputDialog("Ingrese el id del conductor: ") + ",";
                    idPasajero = JOptionPane.showInputDialog("Ingrese el id del pasajero: ") + ",";
                    numCuenta = JOptionPane.showInputDialog("Ingrese el número de la cuenta: ");
                    mensaje = facadeSingleton.registrarPagoCuentaBancaria(valor, idConductor, idPasajero, numCuenta);
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }
              }
              break;
            case "administrador":
              while (opcion3 != 0) {
                opcion3 = Integer.parseInt(JOptionPane.showInputDialog(
                        "Bienvenido " + facadeSingleton.getNombreUsuario(correo) + " (Administrador)\n\n"
                        + "Seleccione una opción: \n\n"
                        + "1. Listar usuarios\n"
                        + "2. Listar rutas\n"
                        + "3. Listar reservas\n"
                        + "4. Listar pagos\n\n"
                        + "0. Cerrar sesión"
                ));

                switch (opcion3) {
                  case 1:
                    if (facadeSingleton.existenUsuarios()) {
                      JOptionPane.showMessageDialog(null, facadeSingleton.listarUsuarios());
                    } else {
                      JOptionPane.showMessageDialog(null, "No hay ningún usuario registrado.");
                    }
                    break;
                  case 2:
                    if (facadeSingleton.existenRutas()) {
                      JOptionPane.showMessageDialog(null, facadeSingleton.listarRutas());
                    } else {
                      JOptionPane.showMessageDialog(null, "No hay ninguna ruta registrada.");
                    }
                    break;
                  case 3:
                    if (facadeSingleton.existenReservas()) {
                      JOptionPane.showMessageDialog(null, facadeSingleton.listarReservas());
                    } else {
                      JOptionPane.showMessageDialog(null, "No hay ninguna reserva registrada.");
                    }
                    break;
                  case 4:
                    JOptionPane.showMessageDialog(null, facadeSingleton.listarPagos());
                }
              }
              break;
          }
          break;
        case 0:
          System.exit(0);
      }
    }
  }
}
