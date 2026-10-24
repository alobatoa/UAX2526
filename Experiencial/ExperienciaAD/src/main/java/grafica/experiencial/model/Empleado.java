package grafica.experiencial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {
    private int id;
    private String nombre;
    private String apellidos;
    private String correo;
}

