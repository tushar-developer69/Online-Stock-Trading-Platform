import java.util.List;

public class AdminService {
    private UserService userService;

    public AdminService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public boolean freezeAccount(String userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            // Add account freeze logic here
            return true;
        }
        return false;
    }
}
