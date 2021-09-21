/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gustavo
 */

@Repository
@Transactional
public class ProdutoDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Produto> listar() {
        String sql = "select p from Produto p";
        
        return em.createQuery(sql, Produto.class).getResultList();
    }
    
    public Produto salvar(Produto produto) {
        return em.merge(produto);
    }
    
    public void excluir(Produto produto) {
        em.remove( em.contains(produto)? produto : em.merge(produto) );
    }
    
}
