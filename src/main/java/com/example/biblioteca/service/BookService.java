package com.example.biblioteca.service;

import com.example.biblioteca.dto.req.BookReqDto;
import com.example.biblioteca.dto.res.BookResDto;
import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.entity.Title;
import com.example.biblioteca.exception.IdNotFoundException;
import com.example.biblioteca.mapper.BookMapper;
import com.example.biblioteca.repository.IBookRepository;
import com.example.biblioteca.repository.ITitleRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class BookService implements IBookService{

    private IBookRepository bookRepository;
    private ITitleRepository titleRepository;
    public BookService(IBookRepository bookRepository, ITitleRepository titleRepository) {
        this.bookRepository = bookRepository;
        this.titleRepository = titleRepository;
    }

    @Override
    public List<BookResDto> getAll() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResDto> bookResDtoList = new ArrayList<>();
        bookList.stream().forEach(
                b -> {
                    BookResDto bookDto = BookMapper.convertEntityToResDto(b);
                    bookResDtoList.add(bookDto);
                }
        );
        return bookResDtoList;
    }


    @Override
    public SuccessfullyMessageDto create(BookReqDto bookDto) {
        Optional<Title> t = titleRepository.findById(bookDto.getTitle_id());
        if (t.isEmpty()) throw new IdNotFoundException("No existe el Titulo con id "+bookDto.getTitle_id()+", modifique los datos.");
        Book book = BookMapper.convertReqDtoToEntity(bookDto,t.get());
        Book bookSaved = bookRepository.save(book);
        SuccessfullyMessageDto messageDto = new SuccessfullyMessageDto("El Libro "+bookSaved.getTitle().getTitle()+" se creó correctamente, con el ID: "+bookSaved.getId());
        return messageDto;
    }

    @Override
    public BookResDto getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) throw new IdNotFoundException("No existe el Libro con id "+id+", modifique los datos.");
        BookResDto bookDto = BookMapper.convertEntityToResDto(book.get());
        return bookDto;
    }

    @Override
    public SuccessfullyMessageDto update(Long id, BookReqDto bookDto) {
        Optional<Book> bookExist = bookRepository.findById(id);
        if (bookExist.isEmpty()) throw new IdNotFoundException("No existe el Libro con id "+id+", modifique los datos.");
        Title t = new Title();
        if (bookDto.getTitle_id() != null) {
             t = titleRepository.findById(bookDto.getTitle_id()).get();
            if (t.getTitle() == null) throw new IdNotFoundException("No existe el Titulo con id "+bookDto.getTitle_id()+", modifique los datos.");
        }
        Book bookEdit = BookMapper.convertEditDtoToEntity(bookExist.get(),bookDto,t);
        bookRepository.save(bookEdit);
        return new SuccessfullyMessageDto("El libro id: "+id+" se editó correctamente");
    }

    @Override
    public SuccessfullyMessageDto deleteById(Long id) {
        Optional<Book> bookExist = bookRepository.findById(id);
        if (bookExist.isEmpty()) throw new IdNotFoundException("No existe el Libro con id "+id+", modifique los datos.");
        bookRepository.deleteById(id);
        return new SuccessfullyMessageDto("El libro id: "+id+" se eliminó correctamente");
    }

    @Override
    public List<BookResDto> getAvailable() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResDto> bookResDtoList = new ArrayList<>();
        bookList.stream().forEach(
                b -> {
                    if (b.isEnabled()){
                        BookResDto bookDto = BookMapper.convertEntityToResDto(b);
                        bookResDtoList.add(bookDto);
                    }
                }
        );
        return bookResDtoList;
    }

    @Override
    public List<BookResDto> getDisabled() {
        List<Book> bookList = bookRepository.findAll();
        List<BookResDto> bookResDtoList = new ArrayList<>();
        bookList.stream().forEach(
                b -> {
                    if (!b.isEnabled()){
                        BookResDto bookDto = BookMapper.convertEntityToResDto(b);
                        bookResDtoList.add(bookDto);
                    }
                }
        );
        return bookResDtoList;
    }
}
