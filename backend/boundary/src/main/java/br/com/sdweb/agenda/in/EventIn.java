package br.com.sdweb.agenda.in;

import br.com.sdweb.domains.Agenda;
import exceptions.MethodNotImplemented;

import java.util.List;

public interface EventIn {

    default Agenda saveEvent(Agenda agenda) throws RuntimeException{
        throw new MethodNotImplemented("Metódo não implementado.");
    }

    default List<Agenda> findEventAll() throws RuntimeException{
        throw new MethodNotImplemented("Metódo não implementado.");
    }

    default List<Agenda> findEventById(String id) throws RuntimeException{
        throw new MethodNotImplemented("Metódo não implementado.");
    }
}
