package grafica.experiencial.model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data                       // Genera getters, setters, toString, equals y hashCode
@AllArgsConstructor          // Genera constructor con todos los campos
@NoArgsConstructor 
public class Producto {
	private int id;
	private String nombre;
	private String descripcion;
	private int cantidad;
	private double precio;
}

