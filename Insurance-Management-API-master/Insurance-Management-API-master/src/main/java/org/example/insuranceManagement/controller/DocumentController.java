package org.example.insuranceManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.insuranceManagement.entity.Document;
import org.example.insuranceManagement.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path="api/v1/documents")
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService){
        this.documentService=documentService;
    }

    @GetMapping
    public List<Document> getDocuments(){
        return documentService.getDocuments();
    }

    @GetMapping(path="/{documentId}")
    public Document getDocument(@PathVariable("documentId") Long id){
        return documentService.getDocument(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<Document> uploadDocument(@RequestParam("policyId") Long policyId, @RequestParam("file") MultipartFile file){
        Document newDocument= documentService.uploadDocument(policyId, file);
        return ResponseEntity.ok(newDocument);
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<Object> deleteDocument(@PathVariable("documentId") Long id){
        documentService.deleteDocument(id);
        Map<String, String> message = new HashMap<>();
        message.put("message","Document was successfully deleted");
        
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
