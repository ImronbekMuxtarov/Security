package com.example.securitychains.service;

import com.example.securitychains.config.PrincipleUser;
import com.example.securitychains.entity.Attachment;
import com.example.securitychains.entity.Users;
import com.example.securitychains.repository.AttachmentRepository;
import com.example.securitychains.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final UserRepository userRepository;

    public String  getExtension(String fileName){
        if (fileName == null){
            return "";
        }
        int index = fileName.lastIndexOf('.');
        return fileName.substring(index);
    }

    public List<Attachment> upload(MultipartHttpServletRequest request, PrincipleUser principleUser){
        File folder = new File("uploads/" + LocalDate.now());
        Users byUsername = userRepository.findByUsername(principleUser.getUsername()).orElseThrow();
        if (!folder.exists()){
            folder.mkdirs();
        }
        Iterator<String> fileNames = request.getFileNames();
        List<Attachment> attachments = new ArrayList<>();
        while (fileNames.hasNext()){
            String fileName = fileNames.next();
            MultipartFile file = request.getFile(fileName);
            Attachment attachment = new Attachment();
            attachment.setName(fileName);
            File uploadFile = new File(folder.getAbsolutePath() +"/" +file.getOriginalFilename());
            try {
                file.transferTo(uploadFile);
                attachment.setExtension(getExtension(file.getOriginalFilename()));
                attachment.setSize(file.getSize());
                attachment.setPath(uploadFile.getAbsolutePath());
                attachment.setUsers(byUsername);
                attachment.setContentType("JSON");
                attachmentRepository.save(attachment);
                attachments.add(attachment);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return attachments;
    }
}
