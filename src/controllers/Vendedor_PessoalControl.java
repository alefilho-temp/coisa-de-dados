package controllers;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Vendedor_Pessoal;
import service.Vendedor_PessoalDAO;
import service.Vendedor_PessoalDAOImpl;

public class Vendedor_PessoalControl {

    private IntegerProperty vendedorCadastroId = new SimpleIntegerProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> dataNascimento = new SimpleObjectProperty<>(LocalDate.now());
    private StringProperty inscricaoEstadual = new SimpleStringProperty("");
    private IntegerProperty cpf = new SimpleIntegerProperty(0);

    private ObservableList<Vendedor_Pessoal> lista = FXCollections.observableArrayList();
    private Vendedor_PessoalDAO vendedorPessoalDao;
    private int contador = 0;

    public Vendedor_PessoalControl() {
        vendedorPessoalDao = new Vendedor_PessoalDAOImpl();
    }

    public void entidadeParaTela(Vendedor_Pessoal v) {
        if (v != null) {
            this.vendedorCadastroId.set(v.getVendedorCadastro_Id());
            this.nome.set(v.getNome());
            this.dataNascimento.set(v.getData_Nascimento());
            this.inscricaoEstadual.set(v.getInscricao_Estadual());
            this.cpf.set(v.getCPF());
        }
    }

    public Vendedor_Pessoal telaParaEntidade() {
        Vendedor_Pessoal v = new Vendedor_Pessoal();
        v.setVendedorCadastro_Id(this.vendedorCadastroId.get());
        v.setNome(this.nome.get());
        v.setData_Nascimento(this.dataNascimento.get());
        v.setInscricao_Estadual(this.inscricaoEstadual.get());
        v.setCPF(this.cpf.get());
        return v;
    }

    public void gravar() throws Exception {
        Vendedor_Pessoal v = telaParaEntidade();
        vendedorPessoalDao.inserir(v);
        
        pesquisarTodos();
		limparTudo();
    }

    public void pesquisarPorCPF() throws Exception {
        lista.clear();
        lista.addAll(vendedorPessoalDao.pesquisarPorCPF(cpf.get()));
    }

    public void pesquisarTodos() throws Exception {
        lista.clear();
        lista.addAll(vendedorPessoalDao.pesquisarTodos());
    }

    public void remover(Vendedor_Pessoal v) throws Exception {
        vendedorPessoalDao.remover(v);
        pesquisarTodos();
    }

    public void limparTudo() {
        vendedorCadastroId.set(0);
        nome.set("");
        dataNascimento.set(LocalDate.now());
        inscricaoEstadual.set("");
        cpf.set(0);
    }

    public IntegerProperty vendedorCadastroIdProperty() {
        return this.vendedorCadastroId;
    }

    public StringProperty nomeProperty() {
        return this.nome;
    }

    public ObjectProperty<LocalDate> dataNascimentoProperty() {
        return this.dataNascimento;
    }

    public StringProperty inscricaoEstadualProperty() {
        return this.inscricaoEstadual;
    }

    public IntegerProperty cpfProperty() {
        return this.cpf;
    }

    public ObservableList<Vendedor_Pessoal> getLista() {
        return this.lista;
    }
}
