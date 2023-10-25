package trabalho.conclusao.curso.tcc.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadImage {

    public static boolean makeUploadImagem(MultipartFile image){
        boolean uploadSuccess = false;

        //criar diretorio para armazenar arquivo
        if(!image.isEmpty()){
            String nameArquive = image.getOriginalFilename();
            try {
                String imagesPaste = "C:\\Users\\rafae\\eclipse-workspace\\tcc- not\\tcc\\src\\main\\resources\\static\\images\\image-moradias";
                File dir = new File(imagesPaste);
                if (!dir.exists()){
                    dir.mkdirs();
                }
                //criar arquivo no diretorio
                File serverFile = new File(dir.getAbsoluteFile() + File.separator+ nameArquive);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(image.getBytes());
                stream.close();




            }catch (Exception e){
                System.out.println("Deu erro no arquivo"+ e.getMessage());

            }

        }
        return uploadSuccess;
    }
}
