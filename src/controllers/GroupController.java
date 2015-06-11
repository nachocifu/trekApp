package controllers;

import java.util.Date;
import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.Group;
import domain.Session;
import domain.SessionNotActiveException;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;
import src.domain.Message;
import src.domain.Profile;
import src.domain.Trip;


public class GroupController extends AbstractController<Group> {

    public GroupController(UserRepository profileRepo, TripRepository tripRepo,
            GroupRepository groupRepo) {
        super(profileRepo, tripRepo, groupRepo);
    }

    public String getGroupName() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getGroupName();
    }

    public HashSet<ProfileController> getMembers() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.generateListOfProfileControllers(obj.getMembers());
    }

    public ProfileController getAdmin() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        ProfileController response = null;
        if(Session.getInstance().getUserName().equals(obj.getAdminUser().getUsrName()))
            response = Application.getInstance().getCurrentProfileController();
        else
            response = Application.getInstance().getProfileController();

        response.load(Session.getInstance().getUserName());
        return response;
    }

    /**
     * Sets the Group for the Controller
     * @param groupId
     * @return
     */
    protected Boolean setGroup(Integer groupId){
        Group response = this.groupRepo.getById(groupId);

        if(response != null){
            obj = response;
            return true;
        }
        else
            return false;
    }

    //A continuaci�n solo getters

    public Double getCosts() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getCosts();
    }

    public Double getCostsPerMember() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return costs/members.size();
    }

    public void addPost(ProfileController profileController, Message msg) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.obj.addPost(profileController.getObject(), msg);
    }

    public void addGroupTrip(TripController tripController) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.obj.addGroupTrip(tripController.getObject);
    }

    public Integer groupSize(){
        return this.obj.groupSize();
    }
}
