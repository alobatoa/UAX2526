package grafica.experiencial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private int id;
    private int idProducto;      // FK hacia Producto
    private String descripcion;
    private double precioTotal;
}


