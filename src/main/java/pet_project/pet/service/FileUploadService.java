package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pet_project.pet.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final UserService userService;

    public User uploadPicture(MultipartFile file, Long id) {

        try {
            if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
                byte[] bytes = file.getBytes();
                String fileName = DigestUtils.sha1Hex("avatar_"+ id);
                Path path = Paths.get("build/resources/main/static/profile/" + fileName +".jpg");
                Files.write(path, bytes);

                User currentUser = userService.getUser(id);
                currentUser.setPicture(fileName);
                currentUser.setRealPicture(bytes);
                return userService.updateUser(currentUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public byte[]  getPicture(String picture) throws IOException {

        String image = "static/profile/"+ picture + ".jpg";
        InputStream in;
        try {
            ClassPathResource resource = new ClassPathResource(image);
            in = resource.getInputStream();
        } catch (Exception e) {
            ClassPathResource resource = new ClassPathResource("static/profile/default.jpg");
            in = resource.getInputStream();
        }
        return IOUtils.toByteArray(in);
    }
}
