/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ProdutoDao;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import model.Produto;
import util.FacesUtils;

/**
 *
 * @author Gustavo
 */

@ManagedBean
@Named
@ViewScoped
public class ProdutoBean {
    
    private Produto currentProduto = new Produto();
    
    private List<Produto> listaProduto;
    
    @ManagedProperty("#{produtoDao}")
    private ProdutoDao produtoDao;
    
    @PostConstruct
    public void postConstruct() {
        listaProduto = produtoDao.listar();
    }
    
    public void salvar() {
        try {
            Boolean novoProduto = currentProduto.getIdProduto() == null;
            
            currentProduto = produtoDao.salvar(currentProduto);
            
            if (novoProduto) listaProduto.add(currentProduto);
            currentProduto = new Produto();
            
            FacesUtils.mensagem("Produto salvo com sucesso.", FacesMessage.SEVERITY_INFO);
        
        } catch (Exception e) {
            FacesUtils.mensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void excluir() {
        try {
            produtoDao.excluir(currentProduto);
            
            listaProduto.remove(currentProduto);
            currentProduto = new Produto();
            
            FacesUtils.mensagem("Produto excluido com sucesso.", FacesMessage.SEVERITY_INFO);
        
        } catch (Exception e) {
            FacesUtils.mensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////

    public Produto getCurrentProduto() {
        return currentProduto;
    }

    public void setCurrentProduto(Produto currentProduto) {
        this.currentProduto = currentProduto;
    }

    public List<Produto> getListaProduto() {
        return listaProduto;
    }

    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }
    
}
