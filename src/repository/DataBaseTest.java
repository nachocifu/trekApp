package repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import domain.Profile;

/**
 * Esta clase se encarga de arrancar, testear y probar el servidor
 * Se usara para la parte de desarrollo y se borra para la version final.
 *
 * NO EJECUTAR YA QUE PISA LA BASE DE DATOS
 *
 * @author nacho
 */
public class DataBaseTest {

    public static void main(String[] args) {
        System.err.println("### Local Database TEST ###");
        System.out.println("starting database admin");
        DataBaseTest repoAdmin = new DataBaseTest();
        System.out.println("Starting tables");
        repoAdmin.start(Profile.class);
        repoAdmin.populateUsers();
    }


    private void populateUsers() {
        System.out.println("### Populating users table ###");

        Class<Profile> type = Profile.class;
        List<Profile> pool = new ArrayList<Profile>();

        try{

            Class.forName("org.sqlite.JDBC");

            /** create a connection source to our database */
            ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

            /**create DAO*/
            Dao<Profile, String> dao = DaoManager.createDao(connectionSource, type);

            /**Populate*/
            pool.add(new Profile("nacho", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "mail"));

            for(Profile each : pool)
                dao.createOrUpdate(each);

            /** close the connection source */
            connectionSource.close();
        }catch(Exception e){
            System.err.println("[ERROR] || " + e.getMessage());
            System.err.println("ERROR");
        }
        System.err.println("OK");;
    }



    private String databaseUrl = "jdbc:sqlite:DataBase";

    public DataBaseTest(){
    }


    /**
     * NO USAR.
     * Activa las tablas. Los cambios que hace son en memoria de la base de datos!!!!
     * @param type
     */
    private <T> void start(Class<T> type){

        System.out.println("### Starting table " + type.getSimpleName() + " ###");
        try{

            Class.forName("org.sqlite.JDBC");

            /** create a connection source to our database */
            ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

            /**drop and create the table */
            TableUtils.dropTable(connectionSource, type, false);
            TableUtils.createTableIfNotExists(connectionSource, type);

            /** close the connection source */
            connectionSource.close();
        }catch(Exception e){
            System.err.println("[ERROR] || " + e.getMessage());
            System.err.println("ERROR");
        }
        System.err.println("OK");;
    }
}

