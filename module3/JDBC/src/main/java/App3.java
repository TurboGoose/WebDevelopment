import dbservice.DAOException;
import dbservice.DBService;
import dbservice.datasets.User;

public class App3 {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        dbService.printConnectionInfo();
        try {
            long userId = dbService.addUser("John");
            System.out.println("Added user id: " + userId);
            User user = dbService.getUserById(userId);
            System.out.println("Retrieved user by id: " + user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
