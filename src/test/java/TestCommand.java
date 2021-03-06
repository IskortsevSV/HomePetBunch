import command.*;
import dao.BunchDAO;
import dao.DAOFactory;
import dao.FlowerDAO;
import dao.UserDAO;
import entity.Bunch;
import entity.Flower;
import entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;


public class TestCommand {


    ReqestWrapper wrapper;


    @Before
    public void init() {
        wrapper = new ReqestWrapper();
    }


    @Test
    public void testAddNewFlowerToExists() {
        User user = new User();
        user.setAdmin(true);
        wrapper.getSession().setAttribute("user", user);

        wrapper.addParam("name", "Podsnezhnik");
        wrapper.addParam("price", String.valueOf(25));
        wrapper.addParam("lengthSteack", String.valueOf(6));
        wrapper.addParam("iceLevel", String.valueOf(7));

        Command command = new AddNewFlowerToExistsCommand();
        DAOFactory factory = DAOFactory.getInstance();
        FlowerDAO flowerDAO = factory.getFlowerDAO();
        List<Flower> all = flowerDAO.getAll();
        int oldSize = all.size();

        command.execute(wrapper);

        all = flowerDAO.getAll();

        int newSize = all.size();

        System.out.println("Old size: " + oldSize);
        System.out.println("New size: " + newSize);

        Assert.assertEquals(oldSize + 1, newSize);
    }

    @Test
    public void testRegisterCommand() {
        wrapper.addParam("name", "Vasya");
        wrapper.addParam("password", "parol");

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        Set<User> users = userDAO.getAllUsers();
        int currentSize = users.size();

        Command command = new RegisterCommand();
        String errorPage = "register.jsp";
        String page = command.execute(wrapper);

        users = userDAO.getAllUsers();
        int newSize = users.size();

        System.out.println(page);

        Assert.assertEquals(currentSize + 1, newSize);
        Assert.assertNotEquals(errorPage, page);
    }

    @Test
    public void testLogin() {
        wrapper.addParam("name", "user");
        wrapper.addParam("password", "user");

        Command command = new LoginCommand();
        String errorPage = "login.jsp";
        String page = command.execute(wrapper);

        HttpSession session = wrapper.getSession();
        User user = (User) session.getAttribute("user");

        Assert.assertNotNull(user);
        Assert.assertNotEquals(page, errorPage);
    }

    @Test
    public void testLogout() {
        HttpSession session = wrapper.getSession();
        session.setAttribute("user", new User());

        Command command = new LogoutCommand();
        command.execute(wrapper);

        User user = (User) session.getAttribute("user");
        Assert.assertNull(user);
    }

    @Test
    public void testCreateBunchCommand() {
        User user = new User();
        user.setId(6);
        wrapper.getSession().setAttribute("user", user);

        Command command = new CreateBunchCommand();

        DAOFactory factory = DAOFactory.getInstance();
        BunchDAO bunchDAO = factory.getBunchDAO();
        List<Bunch> bunches = bunchDAO.getAllBunches(user);
        int oldSize = bunches.size();

        command.execute(wrapper);

        bunches = bunchDAO.getAllBunches(user);
        int newSize = bunches.size();

        System.out.println("Old size: " + oldSize);
        System.out.println("New size: " + newSize);

        Assert.assertEquals(oldSize + 1, newSize);
    }

    @Test
    public void testRemoveBunch() {
        Bunch bunch = new Bunch();
        bunch.setId(66);
        User user = new User();
        user.setId(6);

        wrapper.addParam("bunch_id", String.valueOf(bunch.getId()));
        Command command = new RemoveBunchCommand();

        DAOFactory daoFactory = DAOFactory.getInstance();
        BunchDAO bunchDAO = daoFactory.getBunchDAO();
        FlowerDAO flower = daoFactory.getFlowerDAO();

        List<Bunch> allBunches = bunchDAO.getAllBunches(user); // ???????????????? ??????-???? ??????????????
        List<Flower> flowerAll = flower.getAll();

        int oldSizeBunch = allBunches.size();
        int oldSizeFlower = flowerAll.size();

        command.execute(wrapper);

        flowerAll = flower.getAll();
        allBunches = bunchDAO.getAllBunches(user);

        int newSizeBunch = allBunches.size();
        int newSizeFlower = flowerAll.size();

        System.out.println("Result Bunch: " + oldSizeBunch + " " + newSizeBunch);
        System.out.println("Result Flower: " + oldSizeFlower + " " + newSizeFlower);

        Assert.assertNotEquals(oldSizeFlower,newSizeFlower);
        Assert.assertNotEquals(oldSizeBunch,newSizeBunch);







    }

}
