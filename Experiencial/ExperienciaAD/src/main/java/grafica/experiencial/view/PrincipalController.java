package grafica.experiencial.view;

import grafica.experiencial.dao.ProductoDAO;
import grafica.experiencial.model.Producto;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class PrincipalController {

    @FXML
    private TextField txtBusqueda;

    @FXML
    private TableView<Producto> tabla;

    private final ProductoDAO dao = new ProductoDAO();

    @FXML
    private void initialize() {
        configurarTabla();
        mostrarTodos();

        // Buscar automáticamente mientras se escribe
        txtBusqueda.textProperty().addListener(
                (observable, textoAnterior, textoNuevo) -> {
                    String texto = textoNuevo.trim();

                    if (texto.isEmpty()) {
                        mostrarTodos();
                    } else {
                        cargarTabla(dao.filtrarPorNombre(texto));
                    }
                }
        );
    }

    // Crear las columnas de la tabla
    private void configurarTabla() {
        TableColumn<Producto, Integer> colId =
                new TableColumn<>("ID");
        colId.setCellValueFactory(fila ->
                new ReadOnlyObjectWrapper<>(fila.getValue().getId())
        );

        TableColumn<Producto, String> colNombre =
                new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(fila ->
                new ReadOnlyObjectWrapper<>(fila.getValue().getNombre())
        );

        TableColumn<Producto, String> colDescripcion =
                new TableColumn<>("Descripción");
        colDescripcion.setCellValueFactory(fila ->
                new ReadOnlyObjectWrapper<>(fila.getValue().getDescripcion())
        );

        TableColumn<Producto, Integer> colCantidad =
                new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(fila ->
                new ReadOnlyObjectWrapper<>(fila.getValue().getCantidad())
        );

        TableColumn<Producto, Double> colPrecio =
                new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(fila ->
                new ReadOnlyObjectWrapper<>(fila.getValue().getPrecio())
        );

        tabla.getColumns().setAll(
                colId, colNombre, colDescripcion, colCantidad, colPrecio
        );

        colId.setPrefWidth(60);
        colNombre.setPrefWidth(180);
        colDescripcion.setPrefWidth(300);
        colCantidad.setPrefWidth(100);
        colPrecio.setPrefWidth(100);
    }

    // Mostrar todos los productos
    @FXML
    private void mostrarTodos() {
        cargarTabla(dao.listarTodos());
    }

    // Filtrar productos con precio inferior a 600
    @FXML
    private void filtrar600() {
        cargarTabla(dao.filtrarPorPrecio(600));
    }

    // Actualizar las filas
    private void cargarTabla(List<Producto> productos) {
        tabla.setItems(
                FXCollections.observableArrayList(productos)
        );
    }

    // Abrir la pantalla de gestión
    @FXML
    private void irAGestion() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/grafica/experiencial/gestion-view.fxml")
            );

            Stage nuevaVentana = new Stage();
            nuevaVentana.setScene(new Scene(loader.load()));
            nuevaVentana.setTitle("Gestión Northwind");
            nuevaVentana.show();

            Stage ventanaActual =
                    (Stage) tabla.getScene().getWindow();
            ventanaActual.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}