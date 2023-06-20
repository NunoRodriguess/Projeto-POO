package app;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

/**
 * Represents a UserManager that manages a collection of users.
 */
public class UserManager implements Serializable {
    private Map<Integer, User> userMap;

    /**
     * Constructs a new UserManager object.
     */
    public UserManager() {
        this.userMap = new HashMap<Integer, User>();
    }

    /**
     * Retrieves the user with the specified ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID
     * @throws NullPointerException if the user with the given ID does not exist
     */
    public User getUser(int id) throws NullPointerException {
        if (!this.userMap.containsKey(id)) {
            throw new NullPointerException();
        }

        return this.userMap.get(id);
    }

    /**
     * Adds a user to the user map.
     *
     * @param oneUser the user to add
     * @throws NullPointerException if the user is null
     */
    public void addUser(User oneUser) throws NullPointerException {
        if (oneUser == null) {
            new NullPointerException();
        }
        this.userMap.put(oneUser.getId(), oneUser.clone());
    }

    /**
     * Removes a user from the user map.
     *
     * @param id the ID of the user to remove
     * @return the user that was removed
     */
    public User removeUser(int id) {
        return this.userMap.remove(id);
    }

    /**
     * Retrieves a list of all users in the user map.
     *
     * @return a list of all users
     */
    public List<User> getUsers() {
        List<User> users = new LinkedList<User>();
        for (Integer key : this.userMap.keySet()) {
            User value = this.userMap.get(key);
            users.add(value.clone());
        }
        return users;
    }

    /**
     * Retrieves the internal user map.
     *
     * @return the user map
     */
    private Map<Integer, User> getUserMap() {
        return this.userMap;
    }

    /**
     * Creates a copy of the user map.
     *
     * @return a copy of the user map
     */
    public Map<Integer, User> getUserMapCopy() {
        Map<Integer, User> userMapCopy = new HashMap<>();

        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {
            int id = entry.getKey();
            User user = entry.getValue();
            userMapCopy.put(id, user.clone());
        }

        return userMapCopy;
    }

    /**
     * Deletes bills associated with a specific order.
     *
     * @param order the order for which bills should be deleted
     */
    public void deleteBills(Order order) {
        Map<Integer, User> it = this.getUserMap();

        for (int key : it.keySet()) {
            User u = it.get(key);
            for (int keyBill : u.getBills().keySet()) {
                Bill b = u.getBills().get(keyBill);
                if (b.getOrder().getID() == order.getID())
                    u.getBills().remove(keyBill);
            }
        }
    }

    /**
     * Finds a user by their email.
     *
     * @param email the email to search for
     * @return the user with the specified email, or null if not found
     */
    public User findUserByEmail(String email) {
        for (Integer user_id : userMap.keySet()) {

            User temp = this.userMap.get(user_id);
            if (temp.getEmail().equals(email))
                return temp;
        }
        return null;
    }

}