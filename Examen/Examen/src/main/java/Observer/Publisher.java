/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Observer;

import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface Publisher {
    
    
    void suscribir(Suscriber s);
    void desuscribir(Suscriber s);
    
    void notificar();
}
