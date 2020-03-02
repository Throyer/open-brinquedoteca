package com.github.throyer.brinquedoteca.services;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

    @Value("${upload.directory}")
    private String uploadDirectory;

    private static final int LARGURA = 580;
    private static final int ALTURA = 385;

    /**
     * Salva um arquivo de imagem na pasta de upload do sistema. Caso não exista uma
     * pasta referente ao id do objeto, ela e criada. E então o sistema armazena uma
     * imagem de capa desse objeto ludico.
     *
     * @param file arquivo de imagem.
     * @param id   objeto dono da imagem.
     * @return diretorio final da imagem.
     */
    public String armazenarCapaObjeto(MultipartFile file, Long id) {
        File pasta = new File(uploadDirectory + "objetos/" + id);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
        try {

            if (file.isEmpty()) {
                return null;
            }
            if (file.getOriginalFilename().contains("..")) {
                return null;
            }

            BufferedImage imagem = ImageIO.read(file.getInputStream());
            imagem = redimensionarImagem(imagem);
            ImageIO.write(imagem, "jpg", new File(pasta.getAbsolutePath() + "/capa.jpg"));
        } catch (IOException e) {
            return null;
        }
        return pasta.getAbsolutePath() + "/capa.jpg";
    }

    /**
     *
     * @param imagemOriginal
     * @return
     */
    private static BufferedImage redimensionarImagem(BufferedImage imagemOriginal) {
        int type = imagemOriginal.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : imagemOriginal.getType();
        BufferedImage imagemRedimensionada = new BufferedImage(LARGURA, ALTURA, type);
        Graphics2D g = imagemRedimensionada.createGraphics();
        g.drawImage(imagemOriginal, 0, 0, LARGURA, ALTURA, null);
        g.dispose();
        return imagemRedimensionada;
    }

    /**
     *
     * @param id
     * @return
     */
    public byte[] carregarCapaObjeto(String id) {
        try {

            BufferedImage imagem = null;
            try {
                imagem = ImageIO.read(new File(uploadDirectory + "objetos/" + id + "/capa.jpg"));
            } catch (NullPointerException exception) {
                return null;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imagem, "jpg", baos);
            baos.flush();

            byte[] imageInByte = baos.toByteArray();

            baos.close();

            return imageInByte;

        } catch (IOException exception) {
            return null;
        }
    }

}
