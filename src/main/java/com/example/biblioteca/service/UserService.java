package com.example.biblioteca.service;

import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.dto.res.TicketResDto;
import com.example.biblioteca.dto.res.UserResDto;
import com.example.biblioteca.entity.User;
import com.example.biblioteca.exception.AlreadyExistEntityException;
import com.example.biblioteca.exception.EntityNotFoundException;
import com.example.biblioteca.exception.IdNotFoundException;
import com.example.biblioteca.mapper.UserMapper;
import com.example.biblioteca.repository.ITicketRepository;
import com.example.biblioteca.repository.IUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService implements IUserService{
    private IUserRepository userRepository;
    private ITicketRepository ticketRepository;
    public UserService(IUserRepository userRepository, ITicketRepository ticketRepository, ObjectMapper objectMapper){
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<UserResDto> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserResDto> userResDtoList = new ArrayList<>();
        userList.stream()
                .forEach(
                        u-> {
                            UserResDto userDto = UserMapper.converEntityToResDto(u);
                            userResDtoList.add(userDto);
                        }
                );
        Collections.sort(userResDtoList, new Comparator<UserResDto>() {
            @Override
            public int compare(UserResDto u1, UserResDto u2) {
                return (u1.getName()).compareTo(u2.getName());
            }
        });
        return userResDtoList;
    }

    @Override
    public SuccessfullyMessageDto create(UserResDto userDto) {
        List<User> userList = userRepository.findAll();
        AtomicBoolean userDni = new AtomicBoolean(false);
        userList.stream().forEach( u -> { if (u.getDni() == userDto.getDni()) userDni.set(true); });
        if ( userDni.get() ) throw new AlreadyExistEntityException("Ya existe un usuario con el DNI: "+userDto.getDni()+", modifique sus datos.");
        AtomicBoolean userEmail = new AtomicBoolean(false);
        userList.stream().forEach( u -> { if (u.getEmail().equals(userDto.getEmail())) userEmail.set(true); });
        if ( userEmail.get() ) throw new AlreadyExistEntityException("Ya existe un administrador con el email: "+userDto.getEmail()+", modifique sus datos.");
        User user = UserMapper.convertResDtoToEntity(userDto);
        User userSaved = userRepository.save(user);
        SuccessfullyMessageDto messageDto = new SuccessfullyMessageDto("El Usuario "+userSaved.getName()+" se creó correctamente, con el Id: "+userSaved.getId());
        return messageDto;
    }

    @Override
    public UserResDto getById(Long id) {
        Optional<User> user= userRepository.findById(id);
        if (user.isEmpty()) throw new IdNotFoundException("No existe el Usuario con ID "+id+", modifique los datos.");
        UserResDto userDto = UserMapper.converEntityToResDto(user.get());

        return userDto;
    }

    @Override
    public SuccessfullyMessageDto update(Long id, UserResDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new IdNotFoundException("No existe el Usuario con ID "+id+", modifique los datos.");
        List<User> userList = userRepository.findAll();
        AtomicBoolean userDni = new AtomicBoolean(false);
        userList.stream().forEach( u -> { if (u.getDni() == userDto.getDni()) userDni.set(true); });
        if ( userDni.get() ) throw new AlreadyExistEntityException("Ya existe un usuario con el DNI: "+userDto.getDni()+", modifique sus datos.");
        AtomicBoolean userEmail = new AtomicBoolean(false);
        userList.stream().forEach( u -> { if (u.getEmail().equals(userDto.getEmail())) userEmail.set(true); });
        if ( userEmail.get() ) throw new AlreadyExistEntityException("Ya existe un administrador con el email: "+userDto.getEmail()+", modifique sus datos.");

        User userEdit = UserMapper.convertEditDtoToEntity(user.get(),userDto);
        userRepository.save(userEdit);
        return new SuccessfullyMessageDto("El usuario id: "+id+" se editó correctamente");

    }

    @Override
    public SuccessfullyMessageDto deleteById(Long id) {
        Optional<User> user= userRepository.findById(id);
        if (user.isEmpty()) throw new IdNotFoundException("No existe el Usuario con ID "+id+", modifique los datos.");
        userRepository.deleteById(id);
        return new SuccessfullyMessageDto("El usuario id: "+id+" se eliminó correctamente");
    }

    @Override
    public List<TicketResDto> getTickets(Long id) {
        Optional<User> user= userRepository.findById(id);
        if (user.isEmpty()) throw new IdNotFoundException("No existe el Usuario con ID "+id+", modifique los datos.");
        UserResDto userDto = UserMapper.converEntityToResDto(user.get());
        List<TicketResDto> ticketList = new ArrayList<>();
        userDto.getTicket_list().stream().forEach(
                t -> {
                    ticketList.add(t);
                }
        );
        Collections.sort(ticketList, new Comparator<TicketResDto>() {
            @Override
            public int compare(TicketResDto t1, TicketResDto t2) {
                return (t1.getBook()).compareTo(t2.getBook());
            }
        });
        ticketList.stream().sorted();
        return ticketList;
    }

    @Override
    public List<UserResDto> getByName(String name) {
        List<User> userList = userRepository.findByName(name);
        if (userList.size() == 0) throw new EntityNotFoundException("No existe el Usuario con el nombre "+name+", intente con otro nombre.");
        List<UserResDto> userDtoList = new ArrayList<>();
        userList.stream()
                .forEach(
                        u-> {
                            UserResDto userDto = UserMapper.converEntityToResDto(u);
                            userDtoList.add(userDto);
                        }
                );
        return userDtoList;
    }

    @Override
    public List<UserResDto> getByLastname(String lastname) {
        List<User> userList = userRepository.findByLastname(lastname);
        if (userList.size() == 0) throw new EntityNotFoundException("No existe el Usuario con el apellido "+lastname+", intente con otro apellido.");
        List<UserResDto> userDtoList = new ArrayList<>();
        userList.stream()
                .forEach(
                        u-> {
                            UserResDto userDto = UserMapper.converEntityToResDto(u);
                            userDtoList.add(userDto);
                        }
                );
        return userDtoList;
    }

    @Override
    public List<UserResDto> getByDni(int dni) {
        List<User> userList = userRepository.findByDni(dni);
        if (userList.size() == 0) throw new EntityNotFoundException("No existe el Usuario con el DNI: "+dni+", intente con otro DNI.");
        List<UserResDto> userResDtoList = new ArrayList<>();
        userList.stream()
                .forEach(
                        u-> {
                            UserResDto userDto = UserMapper.converEntityToResDto(u);
                            userResDtoList.add(userDto);
                        }
                );
        return userResDtoList;
    }
}
