package Util;
import Interfaces.Identifiable;

import Exceptions.Unchecked.DuplicateEntityException;
import Exceptions.Unchecked.EntityNotFoundException;
import java.util.Collection;
//import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
//import java.util.List;

public class Repository<T extends Identifiable> {
    ///Here, Repository<String> doesn't work as it doesn't extend/implement the Identifiable interface, which each
    /// object created in this code logic must do.

    private final Map<String, T> entities = new TreeMap<>();

    public void add(T entity) throws DuplicateEntityException {

        if (entity==null) {
            throw new IllegalArgumentException("A null entity can not be added in the map.");
            }
        

        String id = entity.getID();

        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException(id+" : is not a valid existing ID in this entity.");
            }

        if (entities.containsKey(id)) {
            throw new DuplicateEntityException( "An entity with ID " + id + " already exists" );
            }
        
        entities.put(id, entity);
    }

    public T findById(String Id) throws EntityNotFoundException {

        if (!entities.containsKey(Id)) {
            throw new EntityNotFoundException(Id+" : ID doesn't exist in the map!");
        }
        return entities.get(Id);
    }

    public void remove(String Id) throws EntityNotFoundException{

        if (!entities.containsKey(Id)) {
            throw new EntityNotFoundException(Id+" : ID doesn't exist in the map!");
        }

        entities.remove(Id);
    }

    public boolean contains(String id) {
        return entities.containsKey(id);
    }

    public int size() {
        return entities.size();
    }

    public Collection<T> getAll() {

        return new java.util.ArrayList<>(entities.values());
    }
}
