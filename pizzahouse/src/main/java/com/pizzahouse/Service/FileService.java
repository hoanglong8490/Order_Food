package com.pizzahouse.Service;

import com.pizzahouse.Service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FileService  implements FileServiceImp {
    @Value("${uploadFile.rootPath}")
    private String rootPath;

    private Path root;

    @Override
    public void init() {
        try{
            root = Paths.get(rootPath);
            if(Files.notExists(root)){
                Files.createDirectories(root);
            }
        }catch (Exception e){
        System.out.println("Error create folder root:"+e.getMessage());
        }
    }

    @Override
    public boolean saveFile(MultipartFile file) {
        init();
        try {
            Files.copy(file.getInputStream(),root.resolve(Objects.requireNonNull(file.getOriginalFilename())), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File saved successfully");
            return true;
        }catch (Exception e){
            System.out.println("Error saving file:"+e.getMessage());
            return false;
        }
    }

    @Override
    public Resource loadFile(String filename) {
        Path file = root.resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()||resource.isReadable()){
                    return resource;
            }else{
                throw new RuntimeException("Could not read the file!" );
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteAllFile() {
        try {
            FileSystemUtils.deleteRecursively(root.toFile());
            return true;
        }catch (Exception e){
            System.out.println("Error deleting all files: "+e.getMessage());
            return false;
        }
    }

//    @Override
//    public Stream<Path> loadAllFiles() {
//        try{
//            return Files.walk(root, 1).filter(path -> !path.equals(root)).map(root::relativize);
//        }catch (Exception e){
//            throw new RuntimeException("Error: " + e.getMessage());
//        }
//    }
}
