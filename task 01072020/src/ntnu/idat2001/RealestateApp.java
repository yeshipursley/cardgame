package ntnu.idat2001;
import java.util.Scanner;
import java.util.Iterator;


/**
 * Represents the main class of the Realestate application. An object of this
 * class is responsible for starting the application and being the user
 * interface (UI) for the application.
 *
 * The class has been structured in terms of the subtasks to be performed. Hence
 * the following methods have been implemented:
 * <ul>
 * <li><code>init()</code> - Is responsible for initialising the app-object, by
 * creating the internal PropertyRegister-instance, and filling the phone book
 * with dummies (for debug)
 * </li>
 * <li><code>start()</code> - Is the starting point of the application object.
 * After an instance of the RealestateApp-class have been created, this method
 * should be called. The start-method will then be in charge of running the app
 * until the user decides to quit.
 * </li>
 * </ul>
 *
 * @author Arne Styve
 * @version 2019-09-27
 */
public class RealestateApp {
    
    private static final String VERSION = "1.1-WITH-BUGS";

    // The Property register holding the properties
    private final PropertyRegister properties;
    
    // Constants representing the diferent menu choices
    private final int ADD_PROPERTY = 1;   
    private final int LIST_ALL_PROPERTIES = 2;
    private final int FIND_PROPERTY = 3;
    private final int CALCULATE_AVERAGE_AREA = 4;
    private final int EXIT = 9;

    /**
     * Creates an instance of the RealestateApp.
     */
    public RealestateApp() {
        this.properties = new PropertyRegister();
    }

    /**
     * Called to initialise the instance after having been created. Must be
     * called prior to calling <code>start()</code>
     */
    private void init() {
        this.fillRegisterWithProperties();
    }

    /**
     * Presents the menu for the user, and awaits input from the user. The menu
     * choice selected by the user is being returned.
     *
     * @return the menu choice by the user as a positive number starting from 1.
     * If 0 is returned, the user has entered a wrong value
     */
    private int showMenu() {
        int menuChoice = 0;

        System.out.println("\n***** Property Register Application v" + VERSION + " *****\n");
        System.out.println("1. Add property");
        System.out.println("2. List all properties");
        System.out.println("3. Search property");
        System.out.println("4. Calculate average area");
        //TODO: Add more menus
        System.out.println("9. Quit");
        System.out.println("\nPlease enter a number between 1 and 9.\n");
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            menuChoice = sc.nextInt();
        } else {
            System.out.println("You must enter a number, not text");
        }
        return menuChoice;
    }

    /**
     * Starts the application. This is the main loop of the application,
     * presenting the menu, retrieving the selected menu choice from the user,
     * and executing the selected functionality.
     */
    public void start() {
        boolean finished = false;

        // The while-loop will run as long as the user has not selected
        // to quit the application
        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice)
            {
                case ADD_PROPERTY:
                    this.addRealestatToRegister();
                    break;
                    
                case LIST_ALL_PROPERTIES:
                    this.listAllRealestates();
                    break;
                    
                case FIND_PROPERTY:
                    this.findRealestate();
                    break;
                    
                case CALCULATE_AVERAGE_AREA:
                    this.calculateAverageRealestateArea();
                    break;
                    
                case EXIT:
                    System.out.println("Thank you for using the Properties app!\n");
                    finished = true;
                    break;
                    
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }
    }

  

    /**
     * Requests the user to supply all necessary information to be able to
     * create a new Property to be added to the register. If all data is
     * provided in correct format, a new property is being created and added to
     * the register. If any of the data provided are faulty, the user is
     * informed, and no property is created and stored in the register.
     * I could have decided to let the user re-try to enter the information
     * that was entered faulty, but for simplicity, I decided to terminate the
     * creation of a property if data is invalid.
     */
    public void addRealestatToRegister() {
        int municipalityNumber = 0;
        String municipalityName = "";
        int lotNumber = 0;
        int sectionNumber = 0;
        double area = 0.0;
        String name = "";
        String owner = "";

        Scanner reader = new Scanner(System.in);
        boolean userInputValidSoFar = true; // Flag used to indicate that all 
                                            // input from the user is OK so far.

        System.out.println("Please supply details about the property to add:");

        // Municipality number
        System.out.print("Municipality number (0101 - 5444): ");
        if (reader.hasNextInt()) {
            municipalityNumber = reader.nextInt();
            reader.nextLine();
        } else {
            System.out.println("The Municipality number must be a number");
            userInputValidSoFar = false;
        }

        // Municipality name
        if (userInputValidSoFar) {
            System.out.print("Municipality name : ");
            municipalityName = reader.nextLine();

            System.out.print("Lot number (gardsnummer): ");
            if (reader.hasNextInt()) {
                lotNumber = reader.nextInt();
                reader.nextLine();
            } else {
                System.out.println("The Lot-number number must be a number");
                userInputValidSoFar = false;
            }
        }

        // Section number
        if (userInputValidSoFar) {
            System.out.print("Section number (bruksnummer): ");
            if (reader.hasNextInt()) {
                sectionNumber = reader.nextInt();
                reader.nextLine();
            } else {
                System.out.println("The Section-number number must be a number");
                userInputValidSoFar = false;
            }
        }

        // Name of property
        if (userInputValidSoFar) {
            System.out.print("Name of the property: ");
            name = reader.nextLine();

            System.out.print("Total area of the property, in square meters: ");
            if (reader.hasNextDouble()) {
                area = reader.nextDouble();
                reader.nextLine();
            } else {
                System.out.println("The Area must be a number");
                userInputValidSoFar = false;
            }
        }

        // Name of the owner
        if (userInputValidSoFar) {
            System.out.print("Name of the owner: ");
            owner = reader.nextLine();

            Property property = new Property(municipalityNumber, municipalityName,
                    lotNumber, sectionNumber, name, area);

            property.setNameOfOwner(owner);

            this.properties.addProperty(property);

            System.out.println("Property " + property.getMunicipalityNumber()
                    + "/" + property.getLotNumber() + "/" + property.getSectionNumber()
                    + " added to the register.");
        } else {
            System.out.println("The registration of property was terminated due to a"
                    + " false entry. Please start over.");
        }
    }

    /**
     * Displays a list of all the properties in the register.
     */
    public void listAllRealestates() {
        if (this.properties.getNumberOfProperties() == 0 ) {
            System.out.println("The property register is empty..");
        } else {
            System.out.println("List of all properties in the register");
            System.out.println("======================================\n");
            Iterator<Property> it = this.properties.getIterator();
            while (it.hasNext()) {
                Property property = it.next();
                displayProperty(property);
                System.out.println();
            }
        }
    }

    /**
     * Search for a property with a given municipalitynumber, lot- and section
     * number. The user is asked to provide this information in order to perform
     * the search. If a property was found matching the criterias, the property
     * is displayed to the user. If no property is found, a message is displayed
     * to the user informing the user about the failed search.
     */
    public void findRealestate() {
        Scanner reader = new Scanner(System.in);

        System.out.println("Search for realestate by municipality number, lot number and section number.");
        System.out.println("");

        System.out.print("Municipality number (0101 - 5444): ");
        int municipalityNumber = reader.nextInt();
        reader.nextLine();

        System.out.print("Lot number (gardsnummer): ");
        int lotNumber = reader.nextInt();
        reader.nextLine();

        System.out.print("Section number (bruksnummer): ");
        int sectionNumber = reader.nextInt();
        reader.nextLine();

        Property property = this.properties.findProperty(municipalityNumber, lotNumber, sectionNumber);

        if (null == property) {
            System.out.println("\nNo property was found matching the search criterias..");
        } else {
            System.out.println("\nThe following property was found: \n");
            displayProperty(property);
        }
    }

    /**
     * Calculates and displays the average area of all the properties in the
     * register.
     */
    public void calculateAverageRealestateArea() {
        // Check if there are any properties to perform the calculation on
        if (this.properties.getNumberOfProperties() == 0)
        {
            System.out.println("There are no properties in the register.");
        }
        else
        {
            System.out.println("The average area of all the properties in the register is "
                    + this.properties.getAverageAreaOfProperties() + " m2");
        }
    }

    /**
     * Displays the details of a given property.
     *
     * @param property the property to display
     */
    private void displayProperty(Property property) {
        System.out.println("Municipality number: " + property.getMunicipalityNumber());
        System.out.println("Municipality name  : " + property.getMunicipalityName());
        System.out.println("Lot number         : " + property.getLotNumber());
        System.out.println("Section number     : " + property.getSectionNumber());
        System.out.println("Property name      : " + property.getName());
        System.out.println("Area               : " + property.getArea() + " m2");
        if (null != property.getNameOfOwner()) {
            System.out.println("Name of owner      : " + property.getNameOfOwner());
        }
    }
    
    /**
     * Fills the property register with some default data to be used during
     * development and testing.
     */
    private void fillRegisterWithProperties() {
        this.properties.addProperty(new Property(1445, "Gloppen", 77, 631, "", 1017.6, "Jens Olsen"));
        this.properties.addProperty(new Property(1445, "Gloppen", 77, 131, "Syningom", 661.3, "Nicolay Madsen"));
        this.properties.addProperty(new Property(1445, "Gloppen", 75, 19, "Fugletun", 650.6, "Evilyn Jensen"));
        this.properties.addProperty(new Property(1445, "Gloppen", 74, 188, "", 1457.2, "Karl Ove Bråten"));
        this.properties.addProperty(new Property(1445, "Gloppen", 69, 47, "Høiberg", 1339.4, "Elsa Indregård"));
    }    

    /**
     * The main startingpoint for the application. When running the application
     * from the operating system (Windows/MacOSX/Linux), the operating system is
     * looking for this main-method to be able to know where to start the
     * application from.
     *
     * @param args command line arguments as an fixed size array of strings
     */
    public static void main(String[] args) {
        RealestateApp app = new RealestateApp();
        app.init();
        app.start();
    }
}
