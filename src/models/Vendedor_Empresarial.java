package models;

public class Vendedor_Empresarial {
    private int CNPJ;
    private int VendedorCadastro_Id;
    private String Razao_Social;
    private String Informacao_cobranca;

    // Getters e Setters
    public int getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(int cnpj) {
        this.CNPJ = cnpj;
    }

    public int getVendedorCadastro_Id() {
        return VendedorCadastro_Id;
    }

    public void setVendedorCadastro_Id(int vendedorCadastro_Id) {
        this.VendedorCadastro_Id = vendedorCadastro_Id;
    }

    public String getRazao_Social() {
        return Razao_Social;
    }

    public void setRazao_Social(String razao_Social) {
        this.Razao_Social = razao_Social;
    }

    public String getInformacao_cobranca() {
        return Informacao_cobranca;
    }

    public void setInformacao_cobranca(String informacao_cobranca) {
        this.Informacao_cobranca = informacao_cobranca;
    }
}
