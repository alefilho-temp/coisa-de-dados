package common;

import models.Cliente;
import models.Vendedor;

public class DataHolder {
    static Cliente client;
    public static Cliente getClient() {
        return client;
    }
    public static void setClient(Cliente client) {
        DataHolder.client = client;
    }

    static Vendedor seller;
    public static Vendedor getSeller() {
        return seller;
    }
    public static void setSeller(Vendedor seller) {
        DataHolder.seller = seller;
    }
}
