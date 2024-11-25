package common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;

import javafx.scene.image.Image;

/**
 * Classe utilitária que fornece métodos auxiliares para operações comuns.
 */
public class Utils {
    private static String imagePrefix = "./";

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
        try {
            return new Image(new File(imagePrefix + path).toURI().toURL().toString());
        } catch (MalformedURLException e) {
            return new Image(imagePrefix + "assets/placeholder.png");
        }
    }

    public static String saveImage(String path) {
        try {
            String imageName = generateRandomString(20);
    
            Path source = Path.of(path);
            
            String fileExtension = "";
            int lastDotIndex = path.lastIndexOf('.');

            if (lastDotIndex != -1 && lastDotIndex < path.length() - 1) {
                fileExtension = path.substring(lastDotIndex + 1);
            } else {
                throw new IllegalArgumentException("Invalid file path: no extension found.");
            }   

            Path destination = Path.of(imagePrefix + "products/" + imageName + "." + fileExtension);
    
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    
            return "products/" + imageName + "." + fileExtension;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); 
            return "";
        }
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Gera uma string aleatoria
     *
     * @param length Tamanho
     * @return String
     */
    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(index));
        }
        return stringBuilder.toString();
    }
}
