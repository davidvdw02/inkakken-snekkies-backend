package nl.inkakken.snekkies.inkakkensnekkiesbackend.services;

import java.io.FileOutputStream;
import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Service;

import nl.inkakken.snekkies.inkakkensnekkiesbackend.models.RecipePicture;

@Service
public class RecipePictureService {
    

    public RecipePicture postNewPicture(String picture){
        System.out.println(picture);
        this.savePicture(picture);
        return null;
    }

    private boolean savePicture(String picture) {
        String fileName = UUID.randomUUID().toString() + ".png";
                try {
            String base64Data = picture.replaceAll("^data:[^;]+;base64,", "");
            byte[] imageBytes = Base64.getDecoder().decode(base64Data);
            System.out.println();;
            String filePath = System.getProperty("user.dir")+"/app/images/" + fileName;
            FileOutputStream outputStream = new FileOutputStream(filePath);
            outputStream.write(imageBytes);
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
