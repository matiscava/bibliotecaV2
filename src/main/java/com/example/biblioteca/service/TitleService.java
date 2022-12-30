package com.example.biblioteca.service;

import com.example.biblioteca.dto.req.TitleReqDto;
import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.dto.res.TitleResDto;
import com.example.biblioteca.entity.Author;
import com.example.biblioteca.entity.Title;
import com.example.biblioteca.exception.EntityNotFoundException;
import com.example.biblioteca.exception.IdNotFoundException;
import com.example.biblioteca.mapper.TitleMapper;
import com.example.biblioteca.repository.IAuthorRepository;
import com.example.biblioteca.repository.IBookRepository;
import com.example.biblioteca.repository.ITitleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TitleService implements ITitleService{
    private ITitleRepository titleRepository;
    private IAuthorRepository authorRepository;
    public TitleService (ITitleRepository titleRepository, IBookRepository bookRepository, IAuthorRepository authorRepository, ObjectMapper objectMapper) {
        this.titleRepository = titleRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<TitleResDto> getAll() {
        List<Title> titleList = titleRepository.findAll();
        List<TitleResDto> titleDtoList = new ArrayList<>();
        titleList.stream().forEach(
                title -> {
                    TitleResDto titleResDto = TitleMapper.convertEntityToResDto(title);
                    titleDtoList.add(titleResDto);
                }
        );

        return titleDtoList;
    }

    @Override
    public SuccessfullyMessageDto create(TitleReqDto titleDto) {
        Optional<Author> a = authorRepository.findById(titleDto.getAuthor_id());
        if (a.isEmpty()) throw new IdNotFoundException("El Usuario con ID "+titleDto.getAuthor_id()+" no existe, verifique sus datos.");
        Title title = TitleMapper.convertResDtoToEntity(titleDto, a.get());
        Title titleSaved = titleRepository.save(title);
        SuccessfullyMessageDto messageDto = new SuccessfullyMessageDto("El Titulo "+titleSaved.getTitle()+" se creó correctamente, con el ID: "+titleSaved.getId());
        return messageDto;
    }

    @Override
    public TitleResDto getById(Long id) {
        Optional<Title> title = titleRepository.findById(id);
        if (title.isEmpty()) throw new IdNotFoundException("No existe el Titulo con id "+id+", modifique los datos.");
        TitleResDto titleDto = TitleMapper.convertEntityToResDto(title.get());
        return titleDto;
    }

    @Override
    public SuccessfullyMessageDto update(Long id, TitleReqDto titleDto) {
        Optional<Title> title = titleRepository.findById(id);
        if (title.isEmpty()) throw new IdNotFoundException("No existe el Titulo con id "+id+", modifique los datos.");
        Author a = new Author();
        if (titleDto.getAuthor_id() !=null){
            a = authorRepository.findById(titleDto.getAuthor_id()).get();
            if (a.getName()!=null) throw new IdNotFoundException("El Usuario con ID "+titleDto.getAuthor_id()+" no existe, verifique sus datos.");
        }

        Title titleEdit = TitleMapper.convertEditDtoToEntity(title.get(),titleDto,a);
        titleRepository.save(titleEdit);

        return new SuccessfullyMessageDto("El Título id: "+id+" se editó correctamente");
    }

    @Override
    public SuccessfullyMessageDto deleteById(Long id) {
        Optional<Title> title = titleRepository.findById(id);
        if (title.isEmpty()) throw new IdNotFoundException("No existe el Titulo con id "+id+", modifique los datos.");
        titleRepository.deleteById(id);
        return new SuccessfullyMessageDto("El Título id: "+id+" se eliminó correctamente");
    }

    @Override
    public List<TitleResDto> getByTitle(String title) {
        List<Title> titleList = titleRepository.findByTitle(title);
        if (titleList.size() == 0 ) throw new EntityNotFoundException("No existe ningún libro con ese título");
        List<TitleResDto> titleDtoList = new ArrayList<>();
        titleList.stream().forEach(
                t->{
                    TitleResDto titleDto = TitleMapper.convertEntityToResDto(t);
                    titleDtoList.add(titleDto);
                }
        );
        return titleDtoList;
    }
}
