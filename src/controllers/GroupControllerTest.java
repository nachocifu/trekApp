package controllers;

import static org.junit.Assert.*;

import java.rmi.ServerException;
import java.util.Date;

import org.junit.Test;

import domain.ControllerNotLoadedException;
import domain.GroupNameAlreadyExistsException;
import domain.InvalidPermissionException;
import domain.Profile;
import domain.SessionNotActiveException;
import domain.TripNotClosedException;
import domain.TripStatus;
import domain.UserNameAlreadyExistsException;

public class GroupControllerTest {

	
	
	@SuppressWarnings("deprecation")
	@Test(expected=TripNotClosedException.class)
	public void groupControllerTest() throws ServerException, GroupNameAlreadyExistsException, SessionNotActiveException, ControllerNotLoadedException, UserNameAlreadyExistsException, InvalidPermissionException, TripNotClosedException{
		
		Application app= Application.getInstance();
		Session session = Session.getInstance();
        
		/*register the members that will be a part of the group*/
		app.registerUser("naty", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		app.registerUser("username", "nombre", "apellido", new Date(2000,12,12), true, "12345","buenos aires", "nati@email.com");
		app.registerUser("username2", "nombre2", "apellido2", new Date(1998, 11,11), true, "123123", "hola", "hola@email.com");
		
        session.logIn("naty", "agua");
        CollectionAndSearchController searchRepo = app.getCollectionController();

        /*Get all the profile controllers of the members that will be added to the group*/
		CurrentProfileController profile1= app.getCurrentProfileController();
		ProfileController profile2=searchRepo.getProfileControllerByUsername("username");
		ProfileController profile3=searchRepo.getProfileControllerByUsername("username2");
		
		/*Add members to the group*/
		MyGroupController groupController= app.registerGroup("grupo", profile1, 3, 33, "Buenos Aires");
		groupController.addMember(profile2);
		groupController.addMember(profile3);
		
		/*various tests*/
		assertTrue(groupController.getGroupName().equals("grupo"));
		MyTripController trip= app.registerTrip(new Date(2011,11,11), new Date(2012,1,1), 213.11, "hola", "buenos aires", "cordoba");
		groupController.addGroupTrip(trip);
		groupController.sendReviewToAMember(profile2, "hola", 4);
		assertTrue(groupController.getMaxGroupSize().equals(3));
		groupController.changeGroupCapacity(6);
		assertTrue(groupController.getMaxGroupSize().equals(6));
		assertTrue(groupController.groupSize().equals(3));
		assertTrue(groupController.getMembers().contains(profile1));
		assertTrue(groupController.getMembers().contains(profile2));
		assertTrue(groupController.getMembers().contains(profile3));
		assertTrue(groupController.getAdmin().getUserName().equals("natalia"));
		
		/*try deleting members*/
		groupController.deleteMember(profile3);
		assertFalse(groupController.getMembers().contains(profile3));
		
	}
	
	
	
	@SuppressWarnings("deprecation")
	@Test(expected=TripNotClosedException.class)
	public void tripNotClosedTest() throws ServerException, UserNameAlreadyExistsException, SessionNotActiveException, GroupNameAlreadyExistsException, ControllerNotLoadedException, InvalidPermissionException, TripNotClosedException{
		Application app= Application.getInstance();
		Session session = Session.getInstance();
		
		app.registerUser("naty1", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		app.registerUser("username1", "nombre", "apellido", new Date(2000,12,12), true, "12345","buenos aires", "nati@email.com");
		session.logIn("naty1", "agua");
		
		CurrentProfileController profile1= app.getCurrentProfileController();

        CollectionAndSearchController searchRepo = app.getCollectionController();
		ProfileController profile2=searchRepo.getProfileControllerByUsername("username1");
		MyGroupController groupController= app.registerGroup("grupo", profile1, 3, 33, "Buenos Aires");
		
		MyTripController trip= app.registerTrip(new Date(2011,11,11), new Date(2012,1,1), 213.11, "hola", "buenos aires", "cordoba");
		groupController.addGroupTrip(trip);

		groupController.sendReviewToAMember(profile2, "hola", 4);
	}
	
	/*@Test(expected=InvalidPermissionException.class)
	public void tripTest() throws ServerException, UserNameAlreadyExistsException, SessionNotActiveException, GroupNameAlreadyExistsException, ControllerNotLoadedException, InvalidPermissionException, TripNotClosedException{
		Application app= Application.getInstance();
		Session session = Session.getInstance();
		
		app.registerUser("naty2", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		app.registerUser("username2", "nombre", "apellido", new Date(2000,12,12), true, "12345","buenos aires", "nati@email.com");
		session.logIn("naty2", "agua");
		
		CurrentProfileController profile1= app.getCurrentProfileController();

        CollectionAndSearchController searchRepo = app.getCollectionController();
		ProfileController profile2=searchRepo.getProfileControllerByUsername("username2");
		MyGroupController groupController= app.registerGroup("grupo", profile1, 3, 33, "Buenos Aires");
		
		MyTripController trip= app.registerTrip(new Date(2011,11,11), new Date(2012,1,1), 213.11, "hola", "buenos aires", "cordoba");
		groupController.addGroupTrip(trip);
		
		session.logOut();
		session.logIn("username2", "12345");
		
		CurrentProfileController profile4=app.getCurrentProfileController();
		//groupController.addGroupTrip(tripController);
		
		
		
	}*/
	
	/*
	 this.validateEnvironment();
     this.validateController(tripController);
     Profile loggedUser = Application.getInstance().getCurrentProfileController().getObject();
     if(!this.obj.getMembers().contains(loggedUser)){
         throw new InvalidPermissionException("Cannot add a trip because user is not a member of this group");
     }
	@Test
	public void sendMemberRequestTest(){
		Application app= Application.getInstance();
		Session session = Session.getInstance();
		
		app.registerUser("naty1", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		app.registerUser("username1", "nombre", "apellido", new Date(2000,12,12), true, "12345","buenos aires", "nati@email.com");
		session.logIn("naty1", "agua");

		
		CurrentProfileController profile1= app.getCurrentProfileController();

        CollectionAndSearchController searchRepo = app.getCollectionController();
		ProfileController profile2=searchRepo.getProfileControllerByUsername("username1");
		MyGroupController groupController= app.registerGroup("grupo", profile1, 3, 33, "Buenos Aires");
		groupController.sendMemberRequest();
		
		
	}*/
}
