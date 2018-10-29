/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.sistemas.venta.logica;

import co.edu.ucc.sistemas.venta.modelo.Categoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author juvinao
 */
@Stateless
public class LogicaCategoria {

    @PersistenceContext(unitName = "ventaPU")
    private EntityManager em;

    public void guardar(Categoria c) throws Exception {
        em.persist(c);
    }
    
    public void actualizar(Categoria c) throws Exception {
        Categoria cTemp = em.find(Categoria.class, c.getId());
        cTemp.setNombre(c.getNombre());
        em.merge(cTemp);
    }
    
    public List<Categoria> consultar(){
        return em.createQuery("Select c from Categoria c")
                .getResultList();
    }
    
    public void eliminar(Long id) throws Exception {
        Categoria cTemp = em.find(Categoria.class, id);
        em.remove(cTemp);
    }
    
    public Categoria consultaxId(Long id){
        return em.find(Categoria.class, id);
    }
}
