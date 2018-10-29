/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.sistemas.venta.vista;

import co.edu.ucc.sistemas.venta.logica.LogicaCategoria;
import co.edu.ucc.sistemas.venta.modelo.Categoria;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juvinao
 */
@ManagedBean
@ViewScoped
public class MbCategoria {

    private Categoria categoria;
    private List<Categoria> categorias;
    @EJB
    private LogicaCategoria lc;

    @PostConstruct
    public void init() {
        categoria = new Categoria();
        categorias = lc.consultar();
    }

    public String accionGuardar() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            lc.guardar(categoria);
            context.addMessage(null, 
                    new FacesMessage("Confirmacion", "Se ha registrado la categoria"));
            init();
        } catch (Exception ex) {
            Logger.getLogger(MbCategoria.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, 
                    new FacesMessage("Error", "Ha ocurrido un error"));
        }
        return null;
    }
    
    public String accionEditar(Categoria c){
        categoria = c;
        return null;
    }
    
    public String accionActualizar(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            lc.actualizar(categoria);
            context.addMessage(null, 
                    new FacesMessage("Confirmacion", "Se ha actualizado la categoria"));
            init();
        } catch (Exception ex) {
            Logger.getLogger(MbCategoria.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, 
                    new FacesMessage("Error", "Ha ocurrido un error"));
        }
        return null;
    }
    
    public String accionEliminar(Categoria c){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            lc.eliminar(c.getId());
            context.addMessage(null, 
                    new FacesMessage("Confirmacion", "Se ha eliminado la categoria"));
            init();
        } catch (Exception ex) {
            Logger.getLogger(MbCategoria.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, 
                    new FacesMessage("Error", "Ha ocurrido un error"));
        }
        return null;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    
}
