package com.rollingstone.services;

import com.rollingstone.entity.Ticket;
import com.rollingstone.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    public List<Ticket> getAllTickets() {
        return repository.findAll();
    }

    public Optional<Ticket> getTicketById(Integer id) {
        return repository.findById(id);
    }

    public Ticket createTicket(Ticket ticket) {
        return repository.save(ticket);
    }

    public Ticket updateTicket(Integer id, Ticket ticket) {
        if (repository.existsById(id)) {
            ticket.setTicketId(id);
            return repository.save(ticket);
        }
        throw new RuntimeException("Ticket not found with id: " + id);
    }

    public void deleteTicket(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Ticket not found with id: " + id);
        }
    }
}
