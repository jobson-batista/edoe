package com.edoe.api.services;

import com.edoe.api.exceptions.DescriptorAlreadyRegisteredException;
import com.edoe.api.exceptions.ForbiddenException;
import com.edoe.api.models.Descriptor;
import com.edoe.api.repositories.DescriptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.List;

@Service
public class DescriptorService {

    @Autowired
    private DescriptorRepository descriptorRepository;

    @Autowired
    private UserService userService;

    public Descriptor createDescriptor(Descriptor descriptor, String header) throws ServletException {
        for(Descriptor d: descriptorRepository.findAll()) {
            if(d.getDescriptor().equals(descriptor.getDescriptor())) {
                throw new DescriptorAlreadyRegisteredException();
            }
        }
        if(userService.isDonor(header) || userService.isAdmin(header)) {
            return descriptorRepository.save(descriptor);
        } else {
            throw new ForbiddenException();
        }
    }

    public List<Descriptor> getAllDescriptors() {
        return descriptorRepository.findAll();
    }
}
