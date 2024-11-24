package common;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.image.Image;

/**
 * Classe utilitária que fornece métodos auxiliares para operações comuns.
 */
public class Utils {

    /**
     * Recupera uma imagem a partir do caminho especificado.
     *
     * Este método tenta carregar uma imagem do caminho de arquivo fornecido. Se o caminho do arquivo
     * for malformado, ele retorna uma imagem de placeholder.
     *
     * @param path o caminho relativo para o arquivo de imagem
     * @return um objeto Image valido
     */
    public static Image getImage(String path) {
        String prefix = "./";

        try {
            return new Image(new File(prefix + path).toURI().toURL().toString());
        } catch (MalformedURLException e) {
            return new Image(prefix + "assets/placeholder.png");
        }
    }
}
