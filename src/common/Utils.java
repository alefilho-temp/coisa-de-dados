package common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;

import daos.CartDAOImpl;
import javafx.scene.image.Image;
import models.Cliente;
import models.Vendedor;
import service.ClienteDAOImpl;
import service.ClienteException;
import service.VendedorDAOImpl;

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
            if (!(new File(imagePrefix + path).exists())) {
                System.out.println("nao tem a image");
                return new Image(new File(imagePrefix + "assets/placeholder.png").toURI().toURL().toString());
            }

            return new Image(new File(imagePrefix + path).toURI().toURL().toString());
        } catch (Exception e) {
            // Retorna uma imagem de placeholder em caso de erro
            try {
                System.out.println("nao tem a image");
                return new Image(new File(imagePrefix + "assets/placeholder.png").toURI().toURL().toString());
            } catch (Exception e1) {
                e1.printStackTrace();
                return new Image("");
            }
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

    public static Cliente getClientByName(String name) throws ClienteException {
        Cliente c = ClienteDAOImpl.pesquisarporNome(name);
        if(c != null)return c;
        // o codigo de pegar o cliente pelo nome, retornar null se nao tiver
        return null;
    }

    public static Vendedor getSellerByName(String name) {
        Vendedor v =  VendedorDAOImpl.pesquisarNome(name);
        if(v != null) return v;
        return null;
    }

    public static int getCartId() {
    	int n = 0; 
    	try {
			 n = CartDAOImpl.pegarId();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 if(n != 0) {
    		 return n;
    	 }
    	 return CartDAOImpl.criarId();
    }
}