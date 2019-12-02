/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufpr.contato.controller;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.ufpr.contato.model.ContactTableModel;
import org.ufpr.contato.model.dao.ConnectionFactory;
import org.ufpr.contato.model.dao.ContactDao;
import org.ufpr.contato.view.ContactView;

/**
 *
 * @author rafae
 */
public class Main {
    public static void main(String[] args){
        try{
            ContactDao dao = new ContactDao(new ConnectionFactory());
            ContactTableModel model = new ContactTableModel();
            ContactView view = new ContactView();
            ContactController controller = new ContactController(model,view,dao);
            controller.initController();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,"Erro ao iniciar a aplicação. \n"+ex.getLocalizedMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
