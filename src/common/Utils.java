package common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;

import javafx.scene.image.Image;
import models.Cliente;

/**
 * Classe utilitária que fornece métodos auxiliares para operações comuns.
 * 
 * Esta classe contém métodos para carregar imagens, salvar imagens em um diretório específico
 * e gerar strings aleatórias.
 * 
 */
public class Utils {
    
    // Prefixo para o caminho das imagens
    private static String imagePrefix = "./";

    /**
     * Recupera uma imagem a partir do caminho especificado.
     *
     * Este método tenta carregar uma imagem do caminho de arquivo fornecido. Se o caminho do arquivo
     * for malformado, ele retorna uma imagem de placeholder.
     *
     * @param path o caminho relativo para o arquivo de imagem
     * @return um objeto Image válido
     */
    public static Image getImage(String path) {
        try {
            return new Image(new File(imagePrefix + path).toURI().toURL().toString());
        } catch (MalformedURLException e) {
            // Retorna uma imagem de placeholder em caso de erro
            return new Image(imagePrefix + "assets/placeholder.png");
        }
    }

    /**
     * Salva uma imagem a partir de um caminho especificado em um diretório de destino.
     *
     * Este método gera um nome de arquivo aleatório para a imagem e a copia para o diretório
     * especificado. Se o caminho do arquivo não contiver uma extensão válida, uma exceção será lançada.
     *
     * @param path o caminho do arquivo de imagem a ser salvo
     * @return o caminho relativo da imagem salva, ou uma string vazia em caso de erro
     */
    public static String saveImage(String path) {
        try {
            // Gera um nome de imagem aleatório
            String imageName = generateRandomString(20);
    
            Path source = Path.of(path);
            String fileExtension = extractFileExtension(path);

            // Define o caminho de destino para a imagem
            Path destination = Path.of(imagePrefix + "products/" + imageName + "." + fileExtension);
    
            // Copia a imagem para o destino
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

    /**
     * Extrai a extensão do arquivo a partir do caminho fornecido.
     *
     * @param path o caminho do arquivo
     * @return a extensão do arquivo
     * @throws IllegalArgumentException se não for encontrada uma extensão válida
     */
    private static String extractFileExtension(String path) {
        String fileExtension = "";
        int lastDotIndex = path.lastIndexOf('.');

        if (lastDotIndex != -1 && lastDotIndex < path.length() - 1) {
            fileExtension = path.substring(lastDotIndex + 1);
        } else {
            throw new IllegalArgumentException("Caminho de arquivo inválido: nenhuma extensão encontrada.");
        }
        return fileExtension;
    }

    // Caracteres permitidos para a geração de strings aleatórias
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Gera uma string aleatória de um determinado tamanho.
     *
     * @param length o tamanho da string a ser gerada
     * @return uma string aleatória
     */
    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(index));
        }
        return stringBuilder.toString();
    }

    Cliente getClientByName() {
        // TODO: Fazer o codigo de pegar o cliente pelo nome, retornar null se nao tiver
        return null;
    }

    int createCart(Cliente client) {
        // TODO: Fazer o codigo de criar o carrinho se nao tiver
        return 0;
    }
}
