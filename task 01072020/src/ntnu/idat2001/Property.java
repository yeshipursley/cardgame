package ntnu.idat2001;

/**
 * Represents a property or a real estate. Contains all information
 * related to a real estate such  as the lot number and section number etc.
 * A real state is uniquely identified by the combination of municipality number,
 * the lot number (gardsnummer in Norwegian) and the 
 * section number (bruksnummer in Norwegian).
 * 
 * @author asty
 */
public class Property
{
    private final int municipalityNumber; //A number between 101 (Halden) and 5054 (Indre Fosen)
    private final String municipalityName;
    private final int lotNumber;
    private final int sectionNumber;
    private final String name;
    private final double area;
    private String nameOfOwner;

    /**
     * Creates an instance of Property.
     * 
     * @param municipalityNumber the number (ID) of the municipality
     * @param municipalityName the name of the municipality
     * @param lotNumber the lot number of the property
     * @param sectionNumber the section number of the property
     * @param name the name of the property
     * @param area the area in square meters
     */
    public Property(int municipalityNumber, String municipalityName, 
                    int lotNumber, int sectionNumber, 
                    String name, double area) 
    {
        // Ideally all parameters should be tested against valid range and
        // content, but due to short on time in the exam, I have only
        // added a check for the nameOfOwner parameter in the method
        // setNameOfOwner().
        this.municipalityNumber = municipalityNumber;
        this.municipalityName = municipalityName;
        this.lotNumber = lotNumber;
        this.sectionNumber = sectionNumber;
        this.name = name;
        this.area = area;
        this.nameOfOwner = null;
    }
    

    /**
     * Creates an instance of Property.
     * 
     * @param municipalityNumber the number (ID) of the municipality
     * @param municipalityName the name of the municipality
     * @param lotNumber the lot number of the property
     * @param sectionNumber the section number of the property
     * @param name the name of the property
     * @param area the area in square meters
     * @param nameOfOwner the name of the owner of the property
     */
    public Property(int municipalityNumber, String municipalityName, 
                    int lotNumber, int sectionNumber, 
                    String name, double area, String nameOfOwner) 
    {
        this(municipalityNumber, municipalityName, 
                lotNumber, sectionNumber,name,area);
        this.setNameOfOwner(nameOfOwner);
    }    
    
    /**
     * Returns the municipality number. This is a number between 101 (Halden)
     * and 5054 (Indre Fosen) in Norway, and is a unique ID for the
     * Municipality.
     * 
     * @return the municipality number.
     */
    public int getMunicipalityNumber()
    {
        return municipalityNumber;
    }

    /**
     * Returns the name of the municipality.
     * @return the name of the municipality.
     */
    public String getMunicipalityName()
    {
        return municipalityName;
    }

    /**
     * Returns the lot number. Equivalent to the Norwegian "gardsnummer".
     * @return the lot number
     */
    public int getLotNumber()
    {
        return lotNumber;
    }

    /**
     * Returns the section number within the lot. Equivalent
     * to the Norwegian "bruksnummer".
     * @return  the section number within the lot
     */
    public int getSectionNumber()
    {
        return sectionNumber;
    }

    /**
     * Returns the name of the property.
     * @return the name of the property.
     */
    public String getName()
    {
        String name = "Test";
        return name;
    }

    /**
     * Returns the areas of the property in square meters.
     * @return the areas of the property in square meters
     */
    public double getArea()
    {
        return area;
    }
    
    /**
     * Returns the uique ID of the property, on the form
     * "municipalitynumber-lotNumber/sectionNumber",
     * for example: "1445-77/130"
     * @return the uique ID of the property as a String
     */
    public String getPropertyIDAsString()
    {
        String uniqueId = "" + this.getMunicipalityNumber()
                + "-" + this.getLotNumber()
                + "/" + this.getSectionNumber();
        return uniqueId;
    }

    /**
     * Returns the name of the owner of the property.
     * @return the name of the owner of the property.
     */
    public String getNameOfOwner()
    {
        return nameOfOwner;
    }
    
 
    /**
     * Sets the name of the owner. No checks are made to see if the property
     * is already owned by someone else.
     * If the name provided is <code>null</code> or an ampty string, the
     * name of the owner is set to "INVALID OWNER".
     * 
     * @param nameOfOwner the name of the owner.
     */
    public final void setNameOfOwner(String nameOfOwner)
    {
        // Validate the parameter
        if (null == nameOfOwner)
        {
            this.nameOfOwner = "INVALID OWNER";
        }
        else
        {
            if (nameOfOwner.isEmpty())
            {
                this.nameOfOwner = "INVALID OWNER";
            }
            else
            {
                this.nameOfOwner = nameOfOwner;                
            }
        }
    }
    
    /**
     * Compares this property to another property to check if it this property
     * is equal/the same property as the one given by the parameter 
     * <code>property</code>.
     * The properties are the same if the unique ID is the same (i.e.
     * if the method getPropertyIDAsString() returns the same string for both
     * properties).
     * 
     * If the two properties are equal, <code>true</code> is returned. Otherwise
     * <code>false</code> is returned.
     * 
     * @param property the property to compare this property with
     * @return <code>true</code> if the properties are equal, <code>false</code>
     *         otherwise.
     */
    public boolean isEqualTo(Property property)
    {
        boolean propertiesAreEqual = false;
        
        // Check if the property of the parameter has the same ID-string
        // as this obect. If so, they are equal.
        if (this.getPropertyIDAsString().equals(property.getPropertyIDAsString()))
        {
            propertiesAreEqual = true;
        }
        
        return propertiesAreEqual;
    }
}
