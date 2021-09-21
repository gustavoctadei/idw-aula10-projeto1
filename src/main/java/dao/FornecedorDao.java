/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Fornecedor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gustavo
 */

@Repository
@Transactional
public class FornecedorDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Fornecedor> listar() {
        String sql = "select f from Fornecedor f";
        
        return em.createQuery(sql, Fornecedor.class).getResultList();
    }
    
    public Fornecedor salvar(Fornecedor fornecedor) {
        return em.merge(fornecedor);
    }
    
    public void excluir(Fornecedor fornecedor) {
        em.remove( em.contains(fornecedor)? fornecedor : em.merge(fornecedor) );
    }
    
}
