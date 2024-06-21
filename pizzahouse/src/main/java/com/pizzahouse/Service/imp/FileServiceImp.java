package com.pizzahouse.Service.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileServiceImp {
    void init();
    boolean saveFile(MultipartFile file);
    Resource loadFile(String filename);
    boolean deleteAllFile();
//    Stream<Path> loadAllFiles();

}
