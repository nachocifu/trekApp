package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import repository.AbstractRepository;
import repository.TripRepository;
import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;
import domain.Trip;
import domain.TripStatus;

/**
 * An Trip object accessible to everyone
 * 
 *
 */
public class TripController extends AbstractController<Trip> {

    public TripController(AbstractRepository<Trip> repo) {
        super(repo);
    }

    public Date getStartDate() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getStartDate();
    }

    public Date getEndDate() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getEndDate();
    }

    public Double getEstimateCost() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getEstimateCost();
    }

    public String getTripDescription() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getTripDescription();
    }

    public String getOriginCity() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getOriginCity();
    }

    public String getEndCity() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getEndCity();
    }

    public TripStatus getTripStatus() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getTripStatus();
    }

    /**
     * Generate list of controllers from list of T objects
     * @param list
     * @return response List of controllers
     * @throws SessionNotActiveException
     */  
    protected static HashSet<TripController> generateListOfControllers(Collection<Trip> list) throws SessionNotActiveException {
        HashSet<TripController> response = new  HashSet<TripController>();
        Application app = Application.getInstance();
        for(Trip each: list){
            response.add(app.getATripController(each, null));
        }
        return response;
    }

    @Override
    protected TripRepository getRepository() {
        return (TripRepository) this.repository;
    }


}
