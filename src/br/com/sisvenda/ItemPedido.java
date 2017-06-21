/*
 * Fornecedores.java
 *
 * Created on 17 de Fevereiro de 2008, 05:22
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.sisvenda;

import br.com.sisvenda.fornecedores.FornecedorBean;
import br.com.sisvenda.fornecedores.FornecedorControl;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Cláudio
 */
public class Fornecedores extends Clientes{
    
    List<FornecedorBean> fornecedores;
    
    /** Creates a new instance of Fornecedores */
    public Fornecedores() {
        this.setVisible(true);
        this.setTitle("Fornecedores");
    }
    
    protected void cadastraCliente(){
        if (super.verificarCampos() && super.verificarUF()){
            FornecedorBean fornecedor = new FornecedorBean();
            fornecedor.setNome(tfNome.getText().trim());
            fornecedor.setEndereco(tfEndereco.getText().trim());
            fornecedor.setBairro(tfBairro.getText().trim());
            fornecedor.setCidade(tfCidade.getText().trim());
            fornecedor.setUf(tfUf.getText().trim());
            fornecedor.setCep(tfCep.getText().trim());
            fornecedor.setTelefone(tfTelefone.getText().trim());
            FornecedorControl c = new FornecedorControl();
            c.cadastrarFornecedor(fornecedor);
            super.desabilitarCampos();
        }
    }
    
    protected void listarClientes(){
        FornecedorControl fornec = new FornecedorControl();
        fornecedores = fornec.listarFornecedores("%" + tfPesquisaCliente.getText().trim() + "%");
        mostrarFornecedores(fornecedores);
    }
    
    protected void mostrarFornecedores(List<FornecedorBean> fornecedores){
        while (tmClientes.getRowCount() > 0){
            tmClientes.removeRow(0);
        }
        
        if (fornecedores.size() == 0){
            JOptionPane.showMessageDialog(this, "Nenhum fornecedor encontrado!");
        }else{
            String [] campos = new String[] {null, null, null, null};
            for (int i = 0; i < fornecedores.size(); i++){
                tmClientes.addRow(campos);
                tmClientes.setValueAt(fornecedores.get(i).getCodigo(), i, 0);
                tmClientes.setValueAt(fornecedores.get(i).getNome(), i, 1);
                tmClientes.setValueAt(fornecedores.get(i).getEndereco(), i, 2);
                tmClientes.setValueAt(fornecedores.get(i).getTelefone(), i, 3);
            }
        }
    }
    
    protected void alterarCliente(){
        if (verificarCampos() && verificarUF()){
            FornecedorBean fornecedor = new FornecedorBean();
            fornecedor.setCodigo(fornecedores.get(tbClientes.getSelectedRow()).getCodigo());
            fornecedor.setNome(tfNome.getText().trim());
            fornecedor.setEndereco(tfEndereco.getText().trim());
            fornecedor.setBairro(tfBairro.getText().trim());
            fornecedor.setCidade(tfCidade.getText().trim());
            fornecedor.setUf(tfUf.getText().trim());
            fornecedor.setCep(tfCep.getText().trim());
            fornecedor.setTelefone(tfTelefone.getText().trim());
            FornecedorControl c = new FornecedorControl();
            c.alterarFornecedor(fornecedor);
            desabilitarCampos();
        } else{
            JOptionPane.showMessageDialog(this, "Informe o nome do cliente!");
            tfNome.requestFocus();
        }
    }
    
    protected void tbClientesLinhaSelecionada(JTable tb){
        if (tb.getSelectedRow() != -1){
            tfNome.setText(fornecedores.get(tb.getSelectedRow()).getNome());
            tfEndereco.setText(fornecedores.get(tb.getSelectedRow()).getEndereco());
            tfBairro.setText(fornecedores.get(tb.getSelectedRow()).getBairro());
            tfCidade.setText(fornecedores.get(tb.getSelectedRow()).getCidade());
            tfUf.setText(fornecedores.get(tb.getSelectedRow()).getUf());
            tfCep.setText(fornecedores.get(tb.getSelectedRow()).getCep());
            tfTelefone.setText(fornecedores.get(tb.getSelectedRow()).getTelefone());
        } else {
            tfNome.setText("");
            tfEndereco.setText("");
            tfCidade.setText("");
            tfBairro.setText("");
            tfUf.setText("");
            tfCep.setText("");
            tfTelefone.setText("");
        }
    }

    
}
