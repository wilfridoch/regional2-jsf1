/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.sistemas.venta.logica;

import co.edu.ucc.sistemas.venta.modelo.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juvinao
 */
@Stateless
public class LogicaProducto {
    @PersistenceContext(unitName = "ventaPU")
    private EntityManager em;
    
    public void guardar(Producto p){
        if (p.getId()==null) {
            em.persist(p);
        }else{
            Producto pTemp = em.find(Producto.class, p.getId());
            pTemp.setNombre(p.getNombre());
            pTemp.setpCosto(p.getpCosto());
            pTemp.setpVenta(p.getpVenta());
            pTemp.setCategoria(p.getCategoria());
            em.merge(pTemp);
        }
    }
    
    public List<Producto> consultar(){
        return em.createQuery("Select p from Producto p")
                .getResultList();   
    }
}
