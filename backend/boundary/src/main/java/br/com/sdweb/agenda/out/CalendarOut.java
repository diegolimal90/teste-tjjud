package br.com.sdweb.agenda.out;

import br.com.sdweb.domains.Agenda;
import exceptions.MethodNotImplemented;

import java.time.LocalDateTime;
import java.util.List;

public interface CalendarOut {

    default Agenda saveEvent(Agenda agenda) throws RuntimeException{
        throw new MethodNotImplemented("Metódo não implementado.");
    }

    default List<Agenda> findByDate(LocalDateTime dateTime) throws RuntimeException{
        throw new MethodNotImplemented("Metódo não implementado.");
    }
}
