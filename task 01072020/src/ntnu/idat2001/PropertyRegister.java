package ntnu.idat2001;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


/**
 * A register holding all proerties/real estates in a municipality.
 * Properties can be added to the register, and searched for by the 
 * miunicipality number, lot number and section number.
 * @author arne
 */
public class PropertyRegister {
    // I choose to use a HashMap since all the properties
    // have a unique single key identifying a property.
    private final HashMap<String, Property> properties;

    /**
     * Creates a new instance of the PropertyRegister.
     */
    public PropertyRegister() {
        this.properties = new HashMap<>();
    }
    
    /**
     * Adds a property to the register. If the property is already in the register
     * the property is not added, and <code>false</code> is returned.
     * 
     * If the property was added successfully, <code>true</code> is returned.
     * 
     * @param property the property/real estate to add to the register.
     * @return <code>true</code> if property was added successfully, 
     *         <code>false</code> otherwise.
     */
    public boolean addProperty(Property property)
    {
        boolean success = false;
        if (!this.properties.containsKey(property.getPropertyIDAsString()))
        {
            this.properties.put(property.getPropertyIDAsString(), property);
            success = true;
        }
        return success;
    }
    
    /**
     * Searches the register for the property matching the municipality number,
     * lot number and section number provided by the parameters.
     * If no property found, <code>null</code> is returned.
     * 
     * @param municipalityNumber the municipality number to be matched
     * @param lotNumber the lot number to be matched
     * @param sectionNumber the section number to be matched
     * @return 
     */
    public Property findProperty(int municipalityNumber, int lotNumber, int sectionNumber)
    {
        Property foundProperty = null;

        String uniqueId = "" + municipalityNumber
                + "-" + lotNumber
                + "/" + sectionNumber;
        
        
        foundProperty = this.properties.get(uniqueId);
              
        return foundProperty;
    }
    
    /**
     * Findes all the properties that has a lot number matching the lotnumber
     * provided as parameter.
     * An iterator of the collection of found properties is returned.
     * 
     * @param lotNumber the lotNumber to search for
     * @return an iterator of a collection of found properties. If no
     *         properties was found matching the parameter lotNumber, an
     *         empty collection is returned.
     */
    public Iterator<Property> findAllPropertiesWithLotNumber(int lotNumber)
    {
        /*
        // Create a temperarely collection to store the found properties in
        HashSet<Property> foundProperties = new HashSet<>();
        for (Property property : this.properties.values())
        {
            if (property.getLotNumber() != lotNumber)
            {
                foundProperties.add(property);
            }
        }
        return foundProperties.iterator();

         */
        HashSet<Property> foundProperties = new HashSet<>();
        this.properties.values()
                .stream()
                .filter(s -> s.getLotNumber() != lotNumber)
                .forEach(s -> foundProperties.add(s));

        return foundProperties.iterator();
    }
    
    /**
     * Returns an iterator to be used to iterate over the propetries in the
     * property register.
     * 
     * @return an iterator to iterate over the properties in the register.
     */
    public Iterator<Property> getIterator()
    {
        return this.properties.values().iterator();
    }
    
    /**
     * Returns the average area of all the properties in the register.
     * The average area is given in square meters.
     * If the register does not contain any properties, 0 is returned.
     * 
     * @return the average area in square meters
     */
    public double getAverageAreaOfProperties()
    {
        double averageArea = 0;
        if (this.properties.size() > 0)
        {
            averageArea = getSumOfAreas()/getNumberOfProperties();
        }
        return averageArea;
    }
    
    /**
     * Returns the sum of the areas of all the properties, in square meters.
     * 
     * @return the sum of the areas of all the properties, in square meters.
     */
    private double getSumOfAreas()
    {
        /*
        double sumOfAreas = 0;
        for (Property property: this.properties.values())
        {
            sumOfAreas += property.getArea();
        }

        return sumOfAreas;

         */
        this.properties.values()
                .stream()
                .reduce(0, (sumOfAreas, area)-> return sumOfAreas + area);
    }
    
    /**
     * Returns the number of properties in the register.
     * 
     * @return the number of properties in the register.
     */
    public int getNumberOfProperties()
    {
        return this.properties.size();
    }
}
