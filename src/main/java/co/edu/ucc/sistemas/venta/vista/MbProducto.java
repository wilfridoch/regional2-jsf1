/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ucc.sistemas.venta.vista;

import co.edu.ucc.sistemas.venta.logica.LogicaCategoria;
import co.edu.ucc.sistemas.venta.logica.LogicaProducto;
import co.edu.ucc.sistemas.venta.modelo.Categoria;
import co.edu.ucc.sistemas.venta.modelo.Producto;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author juvinao
 */
@ManagedBean
@ViewScoped
public class MbProducto {
    private Producto producto;
    private List<Producto> productos;
    private List<SelectItem> comboCategorias;
    private Long categoriaSeleccionada;
    @EJB
    private LogicaProducto lp;
    @EJB
    private LogicaCategoria lc;
    
    @PostConstruct
    public void init(){
        producto = new Producto();
        productos = lp.consultar();
        List<Categoria> cs = lc.consultar();
        comboCategorias = new LinkedList<>();
        for(Categoria c:cs){
            comboCategorias.add(
                    new SelectItem(c.getId(), c.getNombre())
            );
        }
    }
    
    public String accionGuardar(){
        Categoria c = lc.consultaxId(categoriaSeleccionada);
        producto.setCategoria(c);
        lp.guardar(producto);
        init();
        return null;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<SelectItem> getComboCategorias() {
        return comboCategorias;
    }

    public void setComboCategorias(List<SelectItem> comboCategorias) {
        this.comboCategorias = comboCategorias;
    }

    public Long getCategoriaSeleccionada() {
        return categoriaSeleccionada;
    }

    public void setCategoriaSeleccionada(Long categoriaSeleccionada) {
        this.categoriaSeleccionada = categoriaSeleccionada;
    }
    
    
}
