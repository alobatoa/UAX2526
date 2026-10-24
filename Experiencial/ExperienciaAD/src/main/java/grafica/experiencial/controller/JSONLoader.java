package grafica.experiencial.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import grafica.experiencial.dao.ProductoDAO;
import grafica.experiencial.model.Producto;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JSONLoader {

    public static void cargarProductosDesdeJSON() {

        ProductoDAO dao = new ProductoDAO();

        try {
            URL url = new URL("https://dummyjson.com/products");

            try (InputStream is = url.openStream();
                 InputStreamReader reader =
                         new InputStreamReader(is, StandardCharsets.UTF_8)) {

                // Leer el JSON utilizando Gson
                JsonObject respuesta = JsonParser.parseReader(reader)
                        .getAsJsonObject();

                // Obtener el array de productos
                JsonArray productos = respuesta.getAsJsonArray("products");

                // Recorrer los productos recibidos
                for (int i = 0; i < productos.size(); i++) {

                    JsonObject jsonProducto =
                            productos.get(i).getAsJsonObject();

                    Producto producto = new Producto();

                    producto.setId(
                            jsonProducto.get("id").getAsInt()
                    );

                    producto.setNombre(
                            jsonProducto.get("title").getAsString()
                    );

                    producto.setDescripcion(
                            jsonProducto.get("description").getAsString()
                    );

                    producto.setCantidad(
                            jsonProducto.get("stock").getAsInt()
                    );

                    producto.setPrecio(
                            jsonProducto.get("price").getAsDouble()
                    );

                    // Guardar el producto en la base de datos
                    dao.insertar(producto);
                }

                System.out.println("Productos cargados correctamente.");
            }

        } catch (Exception e) {
            System.err.println("Error al cargar los productos desde JSON:");
            e.printStackTrace();
        }
    }
}