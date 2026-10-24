package grafica.experiencial.view;

import grafica.experiencial.dao.EmpleadoDAO;
import grafica.experiencial.dao.PedidoDAO;
import grafica.experiencial.dao.ProductoDAO;
import grafica.experiencial.dao.ProductoFavDAO;

import grafica.experiencial.model.Empleado;
import grafica.experiencial.model.Pedido;
import grafica.experiencial.model.Producto;
import grafica.experiencial.model.ProductoFav;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionController {

    @FXML
    private TableView<ObservableList<Object>> tabla;

    private final ProductoDAO productoDAO = new ProductoDAO();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();
    private final ProductoFavDAO productoFavDAO = new ProductoFavDAO();
    private final PedidoDAO pedidoDAO = new PedidoDAO();

    // Mostrar empleados
    @FXML
    private void mostrarEmpleados() {
        List<Object[]> filas = new ArrayList<>();

        for (Empleado e : empleadoDAO.listarTodos()) {
            filas.add(new Object[]{
                    e.getId(),
                    e.getNombre(),
                    e.getApellidos(),
                    e.getCorreo()
            });
        }

        cargarTabla(
                new String[]{"ID", "Nombre", "Apellidos", "Correo"},
                filas
        );
    }

    // Mostrar todos los productos
    @FXML
    private void mostrarProductos() {
        List<Object[]> filas = new ArrayList<>();

        for (Producto p : productoDAO.listarTodos()) {
            filas.add(new Object[]{
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getCantidad(),
                    p.getPrecio()
            });
        }

        cargarTabla(
                new String[]{"ID", "Nombre", "Descripción", "Cantidad", "Precio"},
                filas
        );
    }

    // Mostrar pedidos
    @FXML
    private void mostrarPedidos() {
        List<Object[]> filas = new ArrayList<>();

        for (Pedido p : pedidoDAO.listarTodos()) {
            filas.add(new Object[]{
                    p.getId(),
                    p.getIdProducto(),
                    p.getDescripcion(),
                    p.getPrecioTotal()
            });
        }

        cargarTabla(
                new String[]{"ID", "ID Producto", "Descripción", "Precio Total"},
                filas
        );
    }

    // Mostrar productos con precio inferior a 600
    @FXML
    private void mostrarProductosMenores600() {
        List<Object[]> filas = new ArrayList<>();

        for (Producto p : productoDAO.filtrarPorPrecio(600)) {
            filas.add(new Object[]{
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getCantidad(),
                    p.getPrecio()
            });
        }

        cargarTabla(
                new String[]{"ID", "Nombre", "Descripción", "Cantidad", "Precio"},
                filas
        );
    }

    // Insertar en favoritos los productos cuyo precio supera 1000
    @FXML
    private void insertarFavoritos() {
        List<Producto> productos =
                productoDAO.filtrarPorPrecioMayor(1000);

        for (Producto p : productos) {
            productoFavDAO.insertar(
                    new ProductoFav(0, p.getId())
            );
        }

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Productos favoritos");
        alerta.setHeaderText(null);
        alerta.setContentText(
                "Se han procesado " + productos.size()
                        + " productos para insertar en favoritos."
        );
        alerta.showAndWait();
    }

    // Construir las columnas y las filas de la tabla
    private void cargarTabla(String[] nombresColumnas,
                             List<Object[]> datos) {

        ObservableList<ObservableList<Object>> filas =
                FXCollections.observableArrayList();

        for (Object[] dato : datos) {
            filas.add(
                    FXCollections.observableArrayList(
                            java.util.Arrays.asList(dato)
                    )
            );
        }

        List<TableColumn<ObservableList<Object>, ?>> columnas =
                new ArrayList<>();

        for (int i = 0; i < nombresColumnas.length; i++) {
            final int indice = i;

            TableColumn<ObservableList<Object>, Object> columna =
                    new TableColumn<>(nombresColumnas[i]);

            columna.setCellValueFactory(celda ->
                    new ReadOnlyObjectWrapper<>(
                            celda.getValue().get(indice)
                    )
            );

            columna.setPrefWidth(150);
            columnas.add(columna);
        }

        tabla.getColumns().setAll(columnas);
        tabla.setItems(filas);
    }

    // Cerrar esta ventana
    @FXML
    private void volver() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/grafica/experiencial/principal-view.fxml")
            );

            Scene escena = new Scene(loader.load());

            Stage ventana = (Stage) tabla.getScene().getWindow();
            ventana.setScene(escena);
            ventana.setTitle("Northwind - Productos");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}