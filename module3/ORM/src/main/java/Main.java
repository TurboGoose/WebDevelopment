import dbservicehibernate.DBService;
import dbservicehibernate.datasets.UserDataSet;

public class Main {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        long userId = dbService.addUser("John");
        System.out.println("Added user id: " + userId);
        UserDataSet user = dbService.getUserById(userId);
        System.out.println("Retrieved user by id: " + user);
    }
}
