package controllers;

import java.util.HashMap;

/**
 * To be implemented by all services.
 * Operates between the controller and the repositorys.
 * Does the validations and logic
 * @author nacho
 */
public interface ServiceInterface<T> {

    public HashMap<String, Object> getById(Integer id);

    public void delete(Integer id);

}