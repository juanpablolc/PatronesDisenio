package patronesdisenio;

import javax.swing.JOptionPane;

public class Main {

  public static void main(String[] args) {
    ProxySingleton proxySingleton = ProxySingleton.reemplazarConstructor();

    // Datos de prueba
    proxySingleton.getFacade().adicionarConductor("conductor@usab", "Administrador", "123");
    proxySingleton.getFacade().adicionarPasajero("pasajero@usab", "Pasajero", "123");
    proxySingleton.getFacade().adicionarAdministrador("admin@usab", "Administrador", "123");
    proxySingleton.getFacade().registarRuta("Unisabana - Portal", 0);
    proxySingleton.getFacade().aniadirCalle(0, "Autopista norte", 250, 0, 170, 0, 80, 100);

    // Variables para el menú 1
    int opcion;
    String correo, nombre, contrasenia, mensaje;

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

          menu2:
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
                mensaje = proxySingleton.getFacade().adicionarConductor(correo, nombre, contrasenia);
                JOptionPane.showMessageDialog(null, mensaje);
                break menu2;
              case 2:
                mensaje = proxySingleton.getFacade().adicionarPasajero(correo, nombre, contrasenia);
                JOptionPane.showMessageDialog(null, mensaje);
                break menu2;
              case 3:
                mensaje = proxySingleton.getFacade().adicionarAdministrador(correo, nombre, contrasenia);
                JOptionPane.showMessageDialog(null, mensaje);
                break menu2;
            }
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

          int idUsuario = proxySingleton.login(correo, contrasenia);

          if (idUsuario == -1) {
            JOptionPane.showMessageDialog(null, "Correo o contraseña no válidos.");
            continue;
          }

          switch (proxySingleton.getFacade().getTipoUsuario(idUsuario)) {
            case "conductor":
              while (opcion3 != 0) {
                opcion3 = Integer.parseInt(JOptionPane.showInputDialog(
                        "Bienvenido " + proxySingleton.getFacade().getNombreUsuario(idUsuario) + " (Conductor)\n\n"
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
                    mensaje = proxySingleton.getFacade().registarRuta(nombreRuta, idUsuario);
                    JOptionPane.showMessageDialog(null, mensaje);
                    idRuta = proxySingleton.getFacade().getUltimaRutaRegistrada();

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
                      mensaje = proxySingleton.getFacade().aniadirCalle(idRuta, nombreCalle, coordenadaXOrigen, coordenadaYOrigen, coordenadaXDestino, coordenadaYDestino, distancia, tiempoRecorrido);
                      JOptionPane.showMessageDialog(null, mensaje);
                      opcion4 = JOptionPane.showConfirmDialog(null, "¿Desea añadir otra calle?", "TallerFacade", JOptionPane.YES_NO_OPTION);
                    }
                    break;
                  case 2:
                    if (proxySingleton.getFacade().existenRutasConductor(idUsuario)) {
                      JOptionPane.showMessageDialog(null, proxySingleton.getFacade().listarRutasConductor(idUsuario));
                    } else {
                      JOptionPane.showMessageDialog(null, proxySingleton.getFacade().getNombreUsuario(idUsuario) + " no tiene ninguna ruta registrada.");
                    }
                    break;
                  case 3:
                    if (proxySingleton.getFacade().existenRutasConductor(idUsuario)) {
                      numRuta = Integer.parseInt(JOptionPane.showInputDialog(proxySingleton.getFacade().listarRutasConductor(idUsuario) + "Ingrese el número de la ruta que desea actualizar: "));
                      nombreRuta = JOptionPane.showInputDialog("Ingrese el nuevo nombre para la ruta: ");
                      mensaje = proxySingleton.getFacade().actualizarRuta(idUsuario, numRuta, nombreRuta);
                      JOptionPane.showMessageDialog(null, mensaje);
                    } else {
                      JOptionPane.showMessageDialog(null, proxySingleton.getFacade().getNombreUsuario(idUsuario) + " no tiene ninguna ruta registrada.");
                    }
                    break;
                  case 4:
                    if (proxySingleton.getFacade().existenRutasConductor(idUsuario)) {
                      numRuta = Integer.parseInt(JOptionPane.showInputDialog(proxySingleton.getFacade().listarRutasConductor(idUsuario) + "Ingrese el número de la ruta que desea eliminar: "));
                      mensaje = proxySingleton.getFacade().eliminarRuta(idUsuario, numRuta);
                      JOptionPane.showMessageDialog(null, mensaje);
                    } else {
                      JOptionPane.showMessageDialog(null, proxySingleton.getFacade().getNombreUsuario(idUsuario) + " no tiene ninguna ruta registrada.");
                    }
                    break;
                }
              }
              break;
            case "pasajero":
              while (opcion3 != 0) {
                opcion3 = Integer.parseInt(JOptionPane.showInputDialog(
                        "Bienvenido " + proxySingleton.getFacade().getNombreUsuario(idUsuario) + " (Pasajero)\n\n"
                        + "Seleccione una opción: \n\n"
                        + "1. Registrar reserva\n"
                        + "2. Listar reservas\n"
                        + "3. Modificar reserva\n"
                        + "4. Eliminar reserva\n\n"
                        + "0. Cerrar sesión"
                ));
                switch (opcion3) {
                  case 1:
                    idRuta = Integer.parseInt(JOptionPane.showInputDialog(proxySingleton.getFacade().listarRutas() + "Ingrese el número de la ruta que desea reservar: "));
                    puestosReservados = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de puestos que desea reservar: "));
                    mensaje = proxySingleton.getFacade().registrarReserva(idRuta, idUsuario, puestosReservados);
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                  case 2:
                    mensaje = proxySingleton.getFacade().listarReservas();
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                  case 3:
                    idRuta = Integer.parseInt(JOptionPane.showInputDialog(proxySingleton.getFacade().listarRutas() + "Ingrese el número de la ruta que desea modificar: "));
                    puestosReservados = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad de puestos a reservar: "));
                    mensaje = proxySingleton.getFacade().modificarReserva(idRuta, puestosReservados);
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                  case 4:
                    idRuta = Integer.parseInt(JOptionPane.showInputDialog(proxySingleton.getFacade().listarRutas() + "Ingrese el número de la ruta que desea eliminar: "));
                    mensaje = proxySingleton.getFacade().eliminarReserva(idRuta);
                    JOptionPane.showMessageDialog(null, mensaje);
                    break;
                }
              }
              break;
            case "administrador":
              while (opcion3 != 0) {
                opcion3 = Integer.parseInt(JOptionPane.showInputDialog(
                        "Bienvenido " + proxySingleton.getFacade().getNombreUsuario(idUsuario) + " (Administrador)\n\n"
                        + "Seleccione una opción: \n\n"
                        + "1. Listar usuarios\n"
                        + "2. Listar rutas\n"
                        + "3. Listar reservas\n\n"
                        + "0. Cerrar sesión"
                ));

                switch (opcion3) {
                  case 1:
                    if (proxySingleton.getFacade().existenUsuarios()) {
                      JOptionPane.showMessageDialog(null, proxySingleton.getFacade().listarUsuarios());
                    } else {
                      JOptionPane.showMessageDialog(null, "No hay ningún usuario registrado.");
                    }
                    break;
                  case 2:
                    if (proxySingleton.getFacade().existenRutas()) {
                      JOptionPane.showMessageDialog(null, proxySingleton.getFacade().listarRutas());
                    } else {
                      JOptionPane.showMessageDialog(null, "No hay ninguna ruta registrada.");
                    }
                    break;
                  case 3:
                    if (proxySingleton.getFacade().existenReservas()) {
                      JOptionPane.showMessageDialog(null, proxySingleton.getFacade().listarReservas());
                    } else {
                      JOptionPane.showMessageDialog(null, "No hay ninguna reserva registrada.");
                    }
                    break;
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
