/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import util.ConnectionJdbc;

/**
 *
 * @author Gustavo
 */

@ManagedBean
@Named
@RequestScoped
public class RelatorioBean {
    
    public void relatorioProdutos() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        
        String relPath = sc.getRealPath("/resources/reports/relatorio_produtos.jasper");
        
        try {
            Connection connection = ConnectionJdbc.getInstance().getConnection();
            JasperPrint print = JasperFillManager.fillReport(relPath, null, connection);
            
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=\"relatorio_produtos.pdf\"");
            
            JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
            
            ServletOutputStream responseStream = response.getOutputStream();
            responseStream.flush();
            responseStream.close();
            
            FacesContext.getCurrentInstance().renderResponse();
            FacesContext.getCurrentInstance().responseComplete();
        
        } catch (IOException | SQLException | JRException e){
            System.out.println(e.getMessage());
            throw new Exception("Erro ao gerar o relatório");
        }
    }
    
    public void relatorioFornecedores() throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        ServletContext sc = (ServletContext) context.getExternalContext().getContext();
        
        String relPath = sc.getRealPath("/resources/reports/relatorio_fornecedores.jasper");
        
        try {
            Connection connection = ConnectionJdbc.getInstance().getConnection();
            JasperPrint print = JasperFillManager.fillReport(relPath, null, connection);
            
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=\"relatorio_fornecedores.pdf\"");
            
            JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
            
            ServletOutputStream responseStream = response.getOutputStream();
            responseStream.flush();
            responseStream.close();
            
            FacesContext.getCurrentInstance().renderResponse();
            FacesContext.getCurrentInstance().responseComplete();
        
        } catch (IOException | SQLException | JRException e){
            System.out.println(e.getMessage());
            throw new Exception("Erro ao gerar o relatório");
        }
    }
    
}