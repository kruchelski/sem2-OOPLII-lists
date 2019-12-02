/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ufpr.contato.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rafael
 */
public class ContactTableModel extends AbstractTableModel{
    private String[] colunas=new String[]{"id","Nome", "email", "Nascimento","Endereco"};

    private List<Contact> lista=new ArrayList();

    
    public ContactTableModel(List<Contact> lista){
        this.lista=lista;
    }

    public ContactTableModel(){
    }


    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
        //if(column==0)
        //    return false;
        //return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact customer = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return customer.getId();//if column 0 (code)
            case 1: return customer.getName();//if column 1 (name)
            case 2: return customer.getEmail();//if column 2 (birthday)
            case 3: return customer.getDataNascimento();
            case 4: return customer.getAddress() ;
            default : return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Contact customer = lista.get(row);
        switch (col) {
            case 0:
                customer.setId((Long) value); //if column 0 (code)
                break;
            case 1:
                customer.setNome((String) value);
                break;
            case 2:
                customer.setEmail((String) value);
                break;
            case 3:
                LocalDate data = LocalDate.parse((String)value);
                customer.setDataNascimento(data);
                break;
            case 4:
                customer.setAddress((String) value);
                break;
            default:
        }
        this.fireTableCellUpdated(row, col);
    }

    public boolean removeContato(Contact customer) {
        int linha = this.lista.indexOf(customer);
        boolean result = this.lista.remove(customer);
        this.fireTableRowsDeleted(linha,linha);//update JTable
        return result;
    }

    public void adicionaContato(Contact customer) {
        this.lista.add(customer);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size()-1,lista.size()-1);//update JTable
    }

    public void setListaContatos(List<Contact> contatos) {
        this.lista = contatos;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
    }

    public void limpaTabela() {
        int indice = lista.size()-1;
        if(indice<0)
            indice=0;
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0,indice);//update JTable
    }

    public Contact getContato(int linha){
        return lista.get(linha);
    }
    
}
