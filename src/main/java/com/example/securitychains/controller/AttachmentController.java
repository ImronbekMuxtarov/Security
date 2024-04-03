package com.example.securitychains.controller;

import com.example.securitychains.config.PrincipleUser;
import com.example.securitychains.entity.Attachment;
import com.example.securitychains.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;


    @PostMapping("/upload")
    public List<Attachment> upload(MultipartHttpServletRequest request, @AuthenticationPrincipal PrincipleUser principleUser) {
        return attachmentService.upload(request, principleUser);
    }
}
