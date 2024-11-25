package controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Vendedor_Empresarial;
import service.Vendedor_EmpresarialDAO;
import service.Vendedor_EmpresarialDAOImpl;

public class Vendedor_EmpresarialControl {

    private IntegerProperty vendedorCadastroId = new SimpleIntegerProperty(0);
    private IntegerProperty cnpj = new SimpleIntegerProperty(0);
    private StringProperty razaoSocial = new SimpleStringProperty("");
    private StringProperty informacaoCobranca = new SimpleStringProperty("");

    private ObservableList<Vendedor_Empresarial> lista = FXCollections.observableArrayList();
    private Vendedor_EmpresarialDAO vendedorEmpresarialDao;

    public Vendedor_EmpresarialControl() {
        vendedorEmpresarialDao = new Vendedor_EmpresarialDAOImpl();
    }

    // Método para converter de entidade para tela
    public void entidadeParaTela(Vendedor_Empresarial v) {
        if (v != null) {
            this.vendedorCadastroId.set(v.getVendedorCadastro_Id());
            this.cnpj.set(v.getCNPJ());
            this.razaoSocial.set(v.getRazao_Social());
            this.informacaoCobranca.set(v.getInformacao_cobranca());
        }
    }

    // Método para converter de tela para entidade
    public Vendedor_Empresarial telaParaEntidade() {
        Vendedor_Empresarial v = new Vendedor_Empresarial();
        v.setVendedorCadastro_Id(this.vendedorCadastroId.get());
        v.setCNPJ(this.cnpj.get());
        v.setRazao_Social(this.razaoSocial.get());
        v.setInformacao_cobranca(this.informacaoCobranca.get());
        return v;
    }

    // Método para gravar um novo vendedor empresarial
    public void gravar() throws Exception {
        Vendedor_Empresarial v = telaParaEntidade();
        vendedorEmpresarialDao.inserir(v);
        
        pesquisarTodos();
        limparTudo();
    }

    // Método para pesquisar por CNPJ
    public void pesquisarPorCNPJ() throws Exception {
        lista.clear();
        lista.addAll(vendedorEmpresarialDao.pesquisarPorCNPJ(cnpj.get()));
    }

    // Método para pesquisar todos os vendedores empresariais
    public void pesquisarTodos() throws Exception {
        lista.clear();
        lista.addAll(vendedorEmpresarialDao.pesquisarTodos());
    }

    // Método para remover um vendedor empresarial
    public void remover(Vendedor_Empresarial v) throws Exception {
        vendedorEmpresarialDao.remover(v);
        pesquisarTodos();
    }

    // Limpa os campos da tela
    public void limparTudo() {
        vendedorCadastroId.set(0);
        cnpj.set(0);
        razaoSocial.set("");
        informacaoCobranca.set("");
    }

    // Getters para as propriedades de JavaFX
    public IntegerProperty vendedorCadastroIdProperty() {
        return this.vendedorCadastroId;
    }

    public IntegerProperty cnpjProperty() {
        return this.cnpj;
    }

    public StringProperty razaoSocialProperty() {
        return this.razaoSocial;
    }

    public StringProperty informacaoCobrancaProperty() {
        return this.informacaoCobranca;
    }

    public ObservableList<Vendedor_Empresarial> getLista() {
        return this.lista;
    }
}
