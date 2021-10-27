package accounts;

import dbservice.DBService;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static AccountService accountService;
    private final DBService dbService;
    private final Map<String, UserDataSet> sessionToProfile;

    private AccountService() {
        this.dbService = new DBService();
        this.sessionToProfile = new HashMap<>();
    }

    public static AccountService getInstance() {
        if (accountService == null) {
            accountService = new AccountService();
        }
        return accountService;
    }

    public boolean addUser(String login, String password) {
        return dbService.addUser(login, password);
    }

    public UserDataSet getUserByLogin(String login) {
        return dbService.getUserByLogin(login);
    }

    public void deleteUserByLogin(String login) {
        dbService.deleteUserByLogin(login);
    }

    public void addSession(String sessionId, UserDataSet user) {
        sessionToProfile.put(sessionId, user);
    }

    public UserDataSet getUserBySessionId(String sessionId) {
        return sessionToProfile.get(sessionId);
    }

    public UserDataSet deleteSessionById(String sessionId) {
        return sessionToProfile.remove(sessionId);
    }
}
