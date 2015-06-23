package repositoryMem;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import domain.Group;
import domain.Trip;


/**
 * The connection to the file system regarding groups and their persistence.
 */
public class GroupRepository extends AbstractRepository<Group> {


    public GroupRepository(String pathToDataBase,Class reposClass) {     
    }

    public Collection<Group> getGroupsWithTripsBy(Date startDate, Date endDate, String startCity, String endCity, String description ){
    	Collection<Group> result = new HashSet<Group>();
    	for (Group group : this.repository) {
    		Trip trip = group.getGroupTrip();
			if(trip.getStartDate().before(startDate)
					&& trip.getEndDate().after(endDate)
					&& trip.getOriginCity().trim().contains(startCity.trim())
					&& trip.getEndCity().trim().contains(endCity.trim())
					&& trip.getTripDescription().trim().contains(description.trim())){
				result.add(group);
			}
		}
    	return result;
    	
    }

}
