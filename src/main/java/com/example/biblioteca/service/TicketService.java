package com.example.biblioteca.service;

import com.example.biblioteca.dto.res.SuccessfullyMessageDto;
import com.example.biblioteca.dto.req.TicketReqDto;
import com.example.biblioteca.dto.res.TicketResDto;
import com.example.biblioteca.entity.Book;
import com.example.biblioteca.entity.Ticket;
import com.example.biblioteca.entity.User;
import com.example.biblioteca.exception.IdNotFoundException;
import com.example.biblioteca.exception.MaxBookLimitException;
import com.example.biblioteca.exception.NotAvailableBookException;
import com.example.biblioteca.mapper.TicketMapper;
import com.example.biblioteca.repository.IBookRepository;
import com.example.biblioteca.repository.ITicketRepository;
import com.example.biblioteca.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TicketService implements ITicketService{

    private ITicketRepository ticketRepository;
    private IBookRepository bookRepository;
    private IUserRepository userRepository;

    public  TicketService(ITicketRepository ticketRepository, IBookRepository bookRepository, IUserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TicketResDto> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<Ticket> ticketList = ticketRepository.findAll();
        List<TicketResDto> ticketResDtoList = new ArrayList<>();
        ticketList.stream().forEach(
                t -> {
                    TicketResDto ticketDto = TicketMapper.convertEntityToResDto(t);
                    ticketResDtoList.add(ticketDto);
                }
        );
        return ticketResDtoList;
    }

    @Override
    public SuccessfullyMessageDto create(TicketReqDto ticketDto) {
        Optional<User> u = userRepository.findById(ticketDto.getUser_id());
        if (u.isEmpty()) throw new IdNotFoundException("No existe el Usuario con ID "+ticketDto.getUser_id()+", modifique los datos.");
        Optional<Book> b = bookRepository.findById(ticketDto.getBook_id());
        if (b.isEmpty()) throw new IdNotFoundException("No existe el Libro con ID "+ticketDto.getBook_id()+", modifique los datos.");
        if (!b.get().isEnabled()) throw new NotAvailableBookException("El libro "+b.get().getTitle().getTitle()+", ID: "+ticketDto.getBook_id()+" no está habilitado, seleccione otro libro." );
        AtomicInteger openOrder = new AtomicInteger();
        u.get().getTicket_list().stream().forEach(
                ticket -> {
                    if(!ticket.isReturned()) openOrder.getAndIncrement();
                }
        );
        if(Integer.parseInt(openOrder.toString() ) == u.get().getMaxBook())throw new MaxBookLimitException("El usuario ID "+ticketDto.getUser_id()+" ya alcanzó la cantidad máxima de libros a retirar");
        Ticket ticket = TicketMapper.convertReqDtoToEntity(ticketDto,u.get(),b.get());
        Ticket ticketSaved = ticketRepository.save(ticket);
        SuccessfullyMessageDto messageDto = new SuccessfullyMessageDto();
        if (ticket.isReturned() == false){
            b.get().setEnabled(false);
            Book bChanged = bookRepository.save(b.get());
            messageDto.setMessage("El ticket con el ID: "+ticketSaved.getId()+" se creó correctamente. El usuario "+ticketSaved.getUser().getName()+" "+ticketSaved.getUser().getLastname()+" retiró el libro "+ticketSaved.getBook().getTitle().getTitle());
        }else{
            messageDto.setMessage("El ticket con el ID: "+ticketSaved.getId()+" se creó correctamente, y se devolvió. El usuario "+ticketSaved.getUser().getName()+" "+ticketSaved.getUser().getLastname()+" retiró el libro "+ticketSaved.getBook().getTitle().getTitle());
        }

        return messageDto;

    }

    @Override
    public TicketResDto getById(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) throw new IdNotFoundException("No existe el Ticket con ID "+id+", modifique los datos.");
        TicketResDto ticketDto = TicketMapper.convertEntityToResDto(ticket.get());
        return ticketDto;
    }
    @Override
    public SuccessfullyMessageDto returnById(Long id){
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) throw new IdNotFoundException("No existe el Ticket con ID "+id+", modifique los datos.");
        Optional<Book> b = bookRepository.findById(ticket.get().getBook().getId());
        if (b.isEmpty()) throw new IdNotFoundException("No existe el Libro con ID "+ticket.get().getBook().getId()+", modifique los datos.");
        Optional<User> u = userRepository.findById(ticket.get().getUser().getId());
        if (u.isEmpty()) throw new IdNotFoundException("No existe el Usuario con ID "+ticket.get().getUser().getId()+", modifique los datos.");
        b.get().setEnabled(true);
        ticket.get().setReturned(true);
        Book bChanged = bookRepository.save(b.get());
        Ticket ticketSaved = ticketRepository.save(ticket.get());
        SuccessfullyMessageDto messageDto = new SuccessfullyMessageDto("El ticket con el ID: "+ticketSaved.getId()+" se cerró correctamente. El usuario "+ticketSaved.getUser().getName()+" "+ticketSaved.getUser().getLastname()+" devolvió el libro "+ticketSaved.getBook().getTitle().getTitle());
        return messageDto;
    }

    @Override
    public SuccessfullyMessageDto update(Long id, TicketReqDto ticketDto) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) throw new IdNotFoundException("No existe el Ticket con ID "+id+", modifique los datos.");
        Book b = new Book();
        User u = new User();
        if(ticketDto.getBook_id() != null){
            b = bookRepository.findById(ticketDto.getBook_id()).get();
            if (b.getTitle() == null) throw new IdNotFoundException("No existe el Libro con ID "+ticket.get().getBook().getId()+", modifique los datos.");
            if (ticketDto.isReturned()){
                b.setEnabled(true);
                bookRepository.save(b);
            }else {
                throw new NotAvailableBookException("El libro "+b.getTitle().getTitle()+", ID: "+ticketDto.getBook_id()+" no está habilitado, seleccione otro libro." );
            }
        }
        if(ticketDto.getUser_id() != null){
            u = userRepository.findById(ticketDto.getUser_id()).get();
            if (u.getName() == null) throw new IdNotFoundException("No existe el Usuario con ID "+ticket.get().getUser().getId()+", modifique los datos.");
            AtomicInteger openOrder = new AtomicInteger();
            u.getTicket_list().stream().forEach(
                    t -> {
                        if(!t.isReturned()) openOrder.getAndIncrement();
                    }
            );
            if(Integer.parseInt(openOrder.toString() ) == u.getMaxBook())throw new MaxBookLimitException("El usuario ID "+ticketDto.getUser_id()+" ya alcanzó la cantidad máxima de libros a retirar");
        }
        Ticket ticketEdit = TicketMapper.convertEditDtoToEntity(ticket.get(),ticketDto,b,u);

        ticketRepository.save(ticketEdit);
        return new SuccessfullyMessageDto("El Ticket id: "+id+" se editó correctamente");
    }

    @Override
    public SuccessfullyMessageDto deleteById(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) throw new IdNotFoundException("No existe el Ticket con ID "+id+", modifique los datos.");
        ticketRepository.deleteById(id);
        return new SuccessfullyMessageDto("El Ticket id: "+id+" se eliminó correctamente");
    }
}
