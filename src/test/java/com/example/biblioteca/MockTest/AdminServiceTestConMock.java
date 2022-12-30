package com.example.biblioteca.MockTest;

import com.example.biblioteca.dto.res.AdminResDto;
import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.entity.Admin;
import com.example.biblioteca.repository.IAdminRepository;
import com.example.biblioteca.service.AdminService;
import com.example.biblioteca.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AdminServiceTestConMock {
    @Mock
    private IAdminRepository adminRepository;
    @InjectMocks
    private AdminService adminService;

    @Test
    void getAllAdminTestOk(){
        List<Admin> adminList = Utils.laodAdminList();
        List<AdminResDto> adminResDtoList = Utils.laodAdminDtoList();

        when(adminRepository.findAll()).thenReturn(adminList);
        List<AdminResDto> obtainList = adminService.getAll();

        Assertions.assertEquals(adminList.size(),obtainList.size());
        Assertions.assertEquals(adminList.get(0).getEmail(),obtainList.get(0).getEmail());

    }

    @Test
    void createAdminTestOk(){
        ModelMapper mapper = new ModelMapper();
        AdminResDto adminDto = new AdminResDto("Bart","Simposn",123789,7587968,"bsimposn@mail.com","qwerty");
        Admin admin = mapper.map(adminDto,Admin.class);
        Admin adminExpected = mapper.map(adminDto,Admin.class);
        adminExpected.setId(3L);
        SuccessfullyMessageDto messageDto = new SuccessfullyMessageDto("El admin "+admin.getName()+" "+admin.getLastname()+"se creó correctamente, con ID: 3");

        when(adminRepository.save(Mockito.any(Admin.class))).thenReturn(adminExpected);
        SuccessfullyMessageDto messageObtain = adminService.create(adminDto);

        Assertions.assertTrue(messageObtain.getMessage().equals(messageDto.getMessage()));
        Assertions.assertNotEquals(admin.getId(),adminExpected.getId());
        Assertions.assertEquals(admin.getName(),adminExpected.getName());
    }

}
