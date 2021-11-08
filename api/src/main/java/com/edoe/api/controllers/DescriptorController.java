package com.edoe.api.controllers;

import com.edoe.api.models.Descriptor;
import com.edoe.api.services.DescriptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;

@RestController
@RequestMapping("/descriptors")
public class DescriptorController {

    @Autowired
    private DescriptorService descriptorService;

    @PostMapping
    public ResponseEntity<Descriptor> createDescriptor(@RequestBody Descriptor descriptor, @RequestHeader("Authorization") String header) throws ServletException {
        return new ResponseEntity<Descriptor>(descriptorService.createDescriptor(descriptor, header), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Descriptor>> getAllDescriptor() {
        return new ResponseEntity<List<Descriptor>>(descriptorService.getAllDescriptors(), HttpStatus.OK);
    }

}
