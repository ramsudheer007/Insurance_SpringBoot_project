package org.example.insuranceManagement.controller;

import java.util.List;

import org.example.insuranceManagement.entity.Policy;
import org.example.insuranceManagement.services.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path="api/v1/policies")
public class PolicyController {
    private final PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService){
        this.policyService=policyService;
    }

    @GetMapping
    public List<Policy> getPolicies(){
        return policyService.getPolicies();
    }

    @GetMapping(path="/{policyId}")
    public Policy getPolicy(@PathVariable("policyId") Long id){
        return policyService.getPolicy(id);
    }

    @PostMapping("/")
    public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy){
        Policy newPolicy= policyService.addPolicy(policy);

        return ResponseEntity.ok(newPolicy);
    }

    @DeleteMapping(path="/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable("policyId") Long id){
        policyService.deletePolicy(id);
        return ResponseEntity.ok("Policy was successfully deleted");
    }

    @PutMapping("/{policyId}/")
    public ResponseEntity<Policy> updatePolicy(
        @PathVariable("policyId") Long id,
        @RequestBody Policy policy
    ){
        Policy updatedPolicy=policyService.updatePolicy(id, policy);

        return ResponseEntity.ok(updatedPolicy);
    }
}
