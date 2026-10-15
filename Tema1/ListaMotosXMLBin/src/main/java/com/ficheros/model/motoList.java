package com.ficheros.model;


import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "motos") //Establece el elemento raíz para el documento XML
@XmlAccessorType(XmlAccessType.FIELD) //indica que cada elemento de la lista se representa mediante una etiqueta <moto>
public class motoList implements Serializable {

    @XmlElement(name = "moto")
    private List<moto> motos = new ArrayList<>();

    public List<moto> getMotos(){
        return motos;
    }
    public void addMoto(moto moto){
        motos.add(moto);
    }
    public void setMotos(List<moto> motos){
        this.motos = motos;
    }

}
/* Esta será la estructura que tendrá el archivo XML
* <motos>
    <moto>
        <marca>Honda</marca>
        <modelo>CB500F</modelo>
        <cilindrada>500</cilindrada>
    </moto>
    <moto>
        <marca>Yamaha</marca>
        <modelo>MT-07</modelo>
        <cilindrada>689</cilindrada>
    </moto>
</motos>*/