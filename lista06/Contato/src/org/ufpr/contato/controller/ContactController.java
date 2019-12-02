/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ufpr.contato.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import org.ufpr.contato.model.Contact;
import org.ufpr.contato.model.ContactTableModel;
import org.ufpr.contato.model.dao.ContactDao;
import org.ufpr.contato.view.ContactView;

/**
 *
 * @author rafae
 */
public class ContactController {
    private final ContactTableModel model;
    private final ContactView view;
    private int linhaClicada=-1;
    private final ContactDao dao;
    
    public ContactController(ContactTableModel model, ContactView view, ContactDao dao){
        this.model = model;
        this.view = view;
        this.dao = dao;
    }
    
    public void initController(){
        //Registra o modelo de tabela na tabela
        view.getViewTable().setModel(model);
        //Registra os eventos
        view.getListButton().addActionListener((ActionEvent evt) -> listAll());
        view.getViewTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                marcaContatosSelecionados(evt);
            }
        });
        view.getCleanButton().addActionListener((ActionEvent evt) -> limparViewTabela());
        view.getDeleteButton().addActionListener((ActionEvent evt) -> excluirContatos());
        view.getNewButton().addActionListener((ActionEvent evt) -> incluirContato());
        view.getUpdateButton().addActionListener((ActionEvent evt) -> atualizarContato());
        initView();
    }
    
    public void initView(){
        java.awt.EventQueue.invokeLater(() -> {
            view.setVisible(true);
        });
    }

    private void listAll() {
        try {
            List<Contact> list = dao.getList();
            model.setListaContatos(list);
        } catch (Exception ex) {
            view.showError("Erro ao listar contato. Ex.:"+ex);
        }
    }

    private void marcaContatosSelecionados(MouseEvent evt) {
       //Pega a linha clicada
        linhaClicada = view.getSelectedLine(evt);
        //Pega o contato da linha clicada
        Contact contato = model.getContato(linhaClicada);
        //Seta os dados nos componentes
        view.setContact(contato);
    }

    private void limparViewTabela() {
        model.limpaTabela();
    }

    private void excluirContatos() {
        try {
            int[] linhasSelecionadas = view.getViewTable().getSelectedRows();
            List<Contact> listaExcluir = new ArrayList();
            for (int i = 0; i < linhasSelecionadas.length; i++) {
                Contact contato = model.getContato(linhasSelecionadas[i]);
                dao.delete(contato);
                listaExcluir.add(contato);

            }
            listaExcluir.forEach((contato) -> {
                model.removeContato(contato);
            });

        } catch (Exception ex) {
            view.showError("Erro ao excluir contato. "+ex);
        }
    }

    private void incluirContato() {       
         try {
             Contact contato = view.getContact();   
             dao.insert(contato);
             model.adicionaContato(contato);
         } catch (Exception ex) {
             view.showError("Erro ao incluir contato. "+ex);
         }
    }

    private void atualizarContato() {
        try {
            if (linhaClicada != -1) {
                Contact contato = model.getContato(linhaClicada);
                dao.update(contato);
                Contact contatoView = view.getContact();
                contato.clone(contatoView);
                //Atualiza tabela
                model.fireTableRowsUpdated(linhaClicada, linhaClicada);
            }
        } catch (Exception ex) {
            view.showError("Erro ao atualizar contato. " + ex);
        }

    }

}
