/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.FornecedorDao;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import model.Fornecedor;
import util.FacesUtils;

/**
 *
 * @author Gustavo
 */

@ManagedBean
@Named
@ViewScoped
public class FornecedorBean implements Serializable {
    
    private List<Fornecedor> listaFornecedor;
    private Fornecedor currentFornecedor;
    
    @ManagedProperty("#{fornecedorDao}")
    private FornecedorDao fornecedorDao;
    
    @PostConstruct
    public void postConstruct() {
        listaFornecedor = fornecedorDao.listar();
    }
    
    public void novo() {
        currentFornecedor = new Fornecedor();
        currentFornecedor.setDataCadastro(new Date());
        
        FacesUtils.executaJavaScript("PF('modalFornecedor').show();");
    }
    
    public void editar() {
        FacesUtils.executaJavaScript("PF('modalFornecedor').show();");
    }
    
    public void salvar() {
        Boolean novoProduto = currentFornecedor.getIdFornecedor() == null;
        
        currentFornecedor = fornecedorDao.salvar(currentFornecedor);
        
        if (novoProduto) listaFornecedor.add(currentFornecedor);
        
        FacesUtils.executaJavaScript("PF('modalFornecedor').hide();");
        FacesUtils.mensagem("Fornecedor salvo com sucesso.", FacesMessage.SEVERITY_INFO);
    }
    
    public void excluir() {
        fornecedorDao.excluir(currentFornecedor);
        listaFornecedor.remove(currentFornecedor);
        
        currentFornecedor = new Fornecedor();
        FacesUtils.mensagem("Fornecedor excluído com sucesso.", FacesMessage.SEVERITY_INFO);
    }
    
    ////////////////////////////////////////////////////////////////////////////

    public List<Fornecedor> getListaFornecedor() {
        return listaFornecedor;
    }

    public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
        this.listaFornecedor = listaFornecedor;
    }

    public Fornecedor getCurrentFornecedor() {
        return currentFornecedor;
    }

    public void setCurrentFornecedor(Fornecedor currentFornecedor) {
        this.currentFornecedor = currentFornecedor;
    }

    public FornecedorDao getFornecedorDao() {
        return fornecedorDao;
    }

    public void setFornecedorDao(FornecedorDao fornecedorDao) {
        this.fornecedorDao = fornecedorDao;
    }
    
}
