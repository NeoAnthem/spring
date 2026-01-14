package com.sprk.employee_management.controller;


import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/v1")
public class FileController {
    @Value("${}file.upload-dir")
    private final String uploadDirectory = "uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam ("file")MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("file is empty");
        }
        File dir = new File(uploadDirectory);
        if (!dir.exists()){
            dir.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        File destination = new File(dir, fileName);
        file.transferTo(destination);

        file.transferTo(new File(uploadDirectory+file.getOriginalFilename()));
        return ResponseEntity.ok("File Uploaded Successfully");
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadInfo(@PathVariable String fileName) throws IOException{
        Path path = Paths.get(uploadDirectory, fileName);
        Resource resource= new UrlResource(path.toUri());
        if (!resource.exists()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attchment; filename = \"" + fileName + "\"").body(resource);
    }

}
