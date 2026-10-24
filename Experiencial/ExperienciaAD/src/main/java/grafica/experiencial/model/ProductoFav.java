package grafica.experiencial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoFav {
    private int id;
    private int idProducto;      // FK hacia Producto
}

