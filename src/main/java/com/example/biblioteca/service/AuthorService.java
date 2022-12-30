package com.example.biblioteca.service;

import com.example.biblioteca.dto.res.AuthorResDto;
import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.entity.Author;
import com.example.biblioteca.exception.EntityNotFoundException;
import com.example.biblioteca.exception.IdNotFoundException;
import com.example.biblioteca.mapper.AuthorMapper;
import com.example.biblioteca.repository.IAuthorRepository;
import com.example.biblioteca.repository.ITitleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorService implements IAuthorService{
    private IAuthorRepository authorRepository;
    public AuthorService(IAuthorRepository authorRepository, ITitleRepository titleRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorResDto> getAll() {
        List<Author> authorList = authorRepository.findAll();
        List<AuthorResDto> authorDtoList = new ArrayList<>();
        authorList.stream().forEach(
                a ->    {
                    AuthorResDto authorDto = AuthorMapper.convertEntityToResDto(a);
                    authorDtoList.add(authorDto);
                }
        );

        Collections.sort(authorDtoList, new Comparator<AuthorResDto>() {
            @Override
            public int compare(AuthorResDto a1, AuthorResDto a2) {
                return (a1.getName()).compareTo(a2.getName());
            }
        });

        return authorDtoList;
    }

    @Override
    public SuccessfullyMessageDto create(AuthorResDto authorDto) {
        Author author = AuthorMapper.convertResDtoToEntity(authorDto);
        System.out.println(authorDto);
        Author authorSaved = authorRepository.save(author);
        SuccessfullyMessageDto messageDto = new SuccessfullyMessageDto("El autor "+authorSaved.getName()+" "+authorSaved.getLastname()+" se creó correctamente, con el Id: "+authorSaved.getId());
        return messageDto;
    }

    @Override
    public AuthorResDto getById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) throw new IdNotFoundException("El Usuario con ID "+id+" no existe, verifique sus datos.");
        AuthorResDto authorDto = AuthorMapper.convertEntityToResDto(author.get());
        return authorDto;
    }

    @Override
    public SuccessfullyMessageDto update(Long id, AuthorResDto authorDto) {
        Optional<Author> authorExist = authorRepository.findById(id);
        if (authorExist.isEmpty()) throw new IdNotFoundException("El Usuario con ID "+id+" no existe, verifique sus datos.");
        Author authorEdit = AuthorMapper.convertEditDtoToEntity(authorExist.get(),authorDto);
        authorRepository.save(authorEdit);
        return new SuccessfullyMessageDto("El autor ID: "+id+" se editó correctamente.");
    }

    @Override
    public SuccessfullyMessageDto deleteById(Long id) {
        Optional<Author> authorExist = authorRepository.findById(id);
        if (authorExist.isEmpty()) throw new IdNotFoundException("El Usuario con ID "+id+" no existe, verifique sus datos.");
        authorRepository.deleteById(id);
        return new SuccessfullyMessageDto("El autor ID: "+id+" se eliminó correctamente");
    }

    @Override
    public List<AuthorResDto> getByName(String name) {
        List<Author> authorList = authorRepository.findByName(name);
        if (authorList.size() == 0) throw new EntityNotFoundException("No existe el autor con el nombre: "+name+".");
        List<AuthorResDto> authorResDtoList = new ArrayList<>();
        authorList.stream().forEach(
                a-> {
                    AuthorResDto authorDto = AuthorMapper.convertEntityToResDto(a);
                    authorResDtoList.add(authorDto);
                }
        );
        return authorResDtoList;
    }

    @Override
    public List<AuthorResDto> getByLastname(String lastname) {
        List<Author> authorList = authorRepository.findByLastname(lastname);
        if (authorList.size() == 0) throw new EntityNotFoundException("No existe el autor con el apellido: "+lastname+".");
        List<AuthorResDto> authorResDtoList = new ArrayList<>();
        authorList.stream().forEach(
                a-> {
                    AuthorResDto authorDto = AuthorMapper.convertEntityToResDto(a);
                    authorResDtoList.add(authorDto);
                }
        );
        return authorResDtoList;
    }
}
