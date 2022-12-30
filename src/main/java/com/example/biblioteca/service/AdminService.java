package com.example.biblioteca.service;

import com.example.biblioteca.dto.res.AdminResDto;
import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.entity.Admin;
import com.example.biblioteca.exception.AlreadyExistEntityException;
import com.example.biblioteca.exception.IdNotFoundException;
import com.example.biblioteca.mapper.AdminMapper;
import com.example.biblioteca.repository.IAdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AdminService implements IAdminService{
    private IAdminRepository adminRepository;

    public AdminService(IAdminRepository adminRepository){ this.adminRepository = adminRepository; }

    @Override
    public List<AdminResDto> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<Admin> adminList = adminRepository.findAll();
        List<AdminResDto> adminResDtoList = new ArrayList<>();
        adminList.stream().forEach(
                admin -> adminResDtoList.add( mapper.map( admin, AdminResDto.class ) )
        );
        return adminResDtoList;
    }

    @Override
    public SuccessfullyMessageDto create(AdminResDto adminDto) {
        ModelMapper mapper = new ModelMapper();
        List<Admin> adminList = adminRepository.findAll();
        AtomicBoolean adminDni = new AtomicBoolean(false);
        adminList.stream().forEach( a -> { if (a.getDni() == adminDto.getDni()) adminDni.set(true); });
        if ( adminDni.get() ) throw new AlreadyExistEntityException("Ya existe un administrador con el DNI: "+adminDto.getDni()+", modifique sus datos.");
        AtomicBoolean adminEmail = new AtomicBoolean(false);
        adminList.stream().forEach( a -> { if (a.getEmail().equals(adminDto.getEmail())) adminEmail.set(true); });
        if ( adminEmail.get() ) throw new AlreadyExistEntityException("Ya existe un administrador con el email: "+adminDto.getEmail()+", modifique sus datos.");
        Admin admin = mapper.map(adminDto, Admin.class );
        Admin adminSaved = adminRepository.save(admin);
        SuccessfullyMessageDto messageDto = new SuccessfullyMessageDto("El admin "+adminSaved.getName()+" "+adminSaved.getLastname()+"se creó correctamente, con ID: "+adminSaved.getId());
        return messageDto;
    }

    @Override
    public AdminResDto getById(Long id) {
        ModelMapper mapper = new ModelMapper();
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty())    throw new IdNotFoundException("No existe el Admin con id "+id+", modifique los datos.");

        AdminResDto adminResDto = mapper.map(admin, AdminResDto.class);
        return adminResDto;
    }

    @Override
    public SuccessfullyMessageDto update(Long id, AdminResDto adminDto) {
        Optional<Admin> adminExist = adminRepository.findById(id);
        if (adminExist.isEmpty()) throw new IdNotFoundException("No existe el Admin con id "+id+", modifique los datos.");
        Admin adminEdit = AdminMapper.convertEditDtoToEntity(adminExist.get(),adminDto);

        adminRepository.save(adminEdit);

        return new SuccessfullyMessageDto("El admin ID: "+id+" se editó correctamente");
    }

    @Override
    public SuccessfullyMessageDto deleteById(Long id) {
        Optional<Admin> adminExist = adminRepository.findById(id);
        if (adminExist.isEmpty())    throw new IdNotFoundException("No existe el Admin con id "+id+", modifique los datos.");
        adminRepository.deleteById(id);
        return new SuccessfullyMessageDto("El admin ID: "+id+" se eliminó correctamente");
    }
}
