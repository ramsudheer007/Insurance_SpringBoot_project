package org.example.insuranceManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.insuranceManagement.entity.Beneficiary;
import org.example.insuranceManagement.services.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/beneficiaries")
public class BeneficiaryController {
    private final BeneficiaryService beneficiaryService;

    @Autowired
    public BeneficiaryController(BeneficiaryService beneficiaryService){
        this.beneficiaryService=beneficiaryService;
    }

    @GetMapping
    public List<Beneficiary> getBeneficiaries(){
        return beneficiaryService.getBeneficiaries();
    }

    @GetMapping(path="/{beneficiaryId}")
    public Beneficiary getBeneficiary(@PathVariable("beneficiaryId") Long id){
        return beneficiaryService.getBeneficiary(id);
    }

    @PostMapping("/")
    public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody Beneficiary beneficiary){
        Beneficiary newBeneficiary= beneficiaryService.addBeneficiary(beneficiary);

        return ResponseEntity.ok(newBeneficiary);
    }

    @DeleteMapping(path="/{beneficiaryId}")
    public ResponseEntity<Object> deleteBeneficiary(@PathVariable("beneficiaryId") Long id){
        beneficiaryService.deleteBeneficiary(id);
        Map<String, String> message = new HashMap<>();
        message.put("message","Beneficiary was successfully deleted");
        
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @PutMapping("/{beneficiaryId}/")
    public ResponseEntity<Beneficiary> updateBeneficiary(
        @PathVariable("beneficiaryId") Long id,
        @RequestBody Beneficiary beneficiary
    ){
        Beneficiary updatedBeneficiary=beneficiaryService.updateBeneficiary(id, beneficiary);

        return ResponseEntity.ok(updatedBeneficiary);
    }
    
}
