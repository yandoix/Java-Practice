import Interfaces.Identifiable;

import Exceptions.Unchecked.DuplicateEntityException;
import Exceptions.Unchecked.EntityNotFoundException;

//import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Repository<T extends Identifiable> {

    private final Map<String, T> entities = new HashMap<>();

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

}
