package accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static AccountService accountService;
    private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionToProfile;

    private AccountService() {
        loginToProfile = new HashMap<>();
        sessionToProfile = new HashMap<>();
    }

    public static AccountService getInstance() {
        if (accountService == null) {
            accountService = new AccountService();
        }
        return accountService;
    }

    public void addUser(UserProfile profile) {
        loginToProfile.put(profile.getLogin(), profile);
    }

    public void addSession(String sessionId, UserProfile profile) {
        sessionToProfile.put(sessionId, profile);
    }

    public UserProfile deleteUser(String login) {
        return loginToProfile.remove(login);
    }

    public UserProfile deleteUser(UserProfile profile) {
        return loginToProfile.remove(profile.getLogin());
    }

    public UserProfile deleteSession(String sessionId) {
        return sessionToProfile.remove(sessionId);
    }

    public UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionToProfile.get(sessionId);
    }
}
