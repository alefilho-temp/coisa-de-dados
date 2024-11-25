package models;

public class Vendedor {
	private int cadastroId;
    private String nomeLoja;
    private String informacaoLoja;

    // Getters e Setters
    public int getCadastroId() {
        return cadastroId;
    }

    public void setCadastroId(int cadastroId) {
        this.cadastroId = cadastroId;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public String getInformacaoLoja() {
        return informacaoLoja;
    }

    public void setInformacaoLoja(String informacaoLoja) {
        this.informacaoLoja = informacaoLoja;
    }
}	
