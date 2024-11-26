package daos;

import java.util.List;

import models.CartItemModel;
import models.ProductModel;

public interface CartDAO {
    List<CartItemModel> getProductsCar() throws Exception;
    void finalizarCompra() throws Exception;
    void alterarQuantidade(int item, int idProduto) throws Exception;
    void adicionarAoCarrinho(ProductModel p);
    void realizarCompra(ProductModel p) throws Exception;
}
