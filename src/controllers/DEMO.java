package controllers;

import java.rmi.ServerException;
import java.util.Date;

import domain.ControllerNotLoadedException;
import domain.ObjectAlreadyExistsException;
import domain.SessionNotActiveException;
import domain.UserNameAlreadyExistsException;

public class DEMO {

    @SuppressWarnings("deprecation")
	public static void main(String[] args) throws SessionNotActiveException, ControllerNotLoadedException, ObjectAlreadyExistsException {
        Application app = Application.getInstance();
        Session session = Session.getInstance();

        try {

            app.registerUser("naty", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
            app.registerUser("nacho", "ignacio", "cifuentes", new Date(1994, 3, 13), false, "agua", "lkj", "lkj@gmail.com");

            System.out.println("SE CREO A NATY");
            session.logIn("naty", "agua");
            CollectionAndSearchController searchContr = app.getCollectionController();

            ProfileController nacho = searchContr.getProfileControllerByUsername("nacho");
            System.out.println(session.getUserName());
            System.out.println(nacho.getUsername());


            CurrentProfileController naty = app.getCurrentProfileController();



            System.out.println("se levanta user: " + naty.getUsername());
            @SuppressWarnings("unused")
			MyGroupController group1 = app.registerGroup("groupName", naty, 3, 3, "ciudad");




        } catch (ServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UserNameAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
