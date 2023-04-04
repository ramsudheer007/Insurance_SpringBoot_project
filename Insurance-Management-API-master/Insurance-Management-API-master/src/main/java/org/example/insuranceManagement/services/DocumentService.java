package org.example.insuranceManagement.services;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.insuranceManagement.entity.Document;
import org.example.insuranceManagement.entity.Policy;
import org.example.insuranceManagement.repository.DocumentRepository;
import org.example.insuranceManagement.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final PolicyRepository policyRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository, PolicyRepository policyRepository){
        this.documentRepository=documentRepository;
        this.policyRepository=policyRepository;
    }

    public List<Document> getDocuments(){
        return documentRepository.findAll();
    }

    public Document getDocument(Long id){
        Optional <Document> documentOptional = documentRepository.findById(id);
        
        if(!documentOptional.isPresent()){
            throw new IllegalStateException("Document with id " + id + " does not exist");
        }

        return documentOptional.get();
    }

    public Document uploadDocument(Long policyId,MultipartFile file){
        if(policyId == null){
            throw new IllegalStateException("Policy is required");
        }

        Policy policyFromDb=policyRepository.findById(policyId).orElse(null);

        if(policyFromDb==null){
                throw new IllegalStateException("Policy with id "+policyId+ " does not exist");
        }

        // // create folder for the documents using the client name
        final Path root= Paths.get("uploads");
        if(!Files.exists(root)){
            try{
                Files.createDirectory(root);
            }catch(IOException e){
                throw new RuntimeException("Could not create directory for upload");
            }
        }

        // create a new file name with timestamp
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + fileExtension;
        Path filePath = root.resolve(newFileName);

        // check if file with the same name exists and rename it if necessary
        int i = 1;
        while (Files.exists(filePath)) {
            newFileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + "_" + i + fileExtension;
            filePath = root.resolve(newFileName);
            i++;
        }

        // save the file to the folder with the new name
        try {
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

        // save the document to the database
        Document document = new Document();

        document.setDocumentPath(
            filePath.toString()
        );
        document.setPolicy(policyFromDb);
        Document addedDoc = documentRepository.save(document);

        return addedDoc;
    }

    public void deleteDocument(Long id){
        boolean exists=documentRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Document with id " + id + " does not exist");
        }

        documentRepository.deleteById(id);
    }


    



}
