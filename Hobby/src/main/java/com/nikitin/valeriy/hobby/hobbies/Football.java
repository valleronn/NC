package com.nikitin.valeriy.hobby.hobbies;

/**
 * Represents Football hobbies which extends Hobby.
 * This class includes:
 * <ul>
 *     <li>2 fields footballType and outDoor</li>
 *     <li>Default constructor and with parameters</li>
 *     <li>Getters and setters of the fields</li>
 *     <li>Overrides method tellAboutHobby() of Hobby</li>
 * </ul>
 * @author Valeriy Nikitin
 */
public class Football extends Hobby {
    private String footballType;
    private boolean outDoor;

    /**
     * Default constructor that calls Hobby default constructor.
     */
    public Football() {
        super();
    }

    /**
     * Second constructor that calls Hobby default constructor.
     * @param name Hobby name
     * @param startMonth Hobby start month
     * @param startYear Hobby start year
     * @param footballType Hobby type
     */
    public Football(String name, int startMonth, long startYear,
                    String footballType) {
        super(name, startMonth, startYear);
        this.footballType = footballType;
    }

    public Football(String name, int startMonth, long startYear,
                    float popularity, double expenses,
                    char riskEvaluation, String footballType) {
        super(name, startMonth, startYear, popularity, expenses,
                riskEvaluation);
        this.footballType = footballType;
    }

    public String getFootballType() {
        return footballType;
    }

    public void setFootballType(String footballType) {
        this.footballType = footballType;
    }

    public boolean isOutDoor() {
        return outDoor;
    }

    public void setOutDoor(boolean outDoor) {
        this.outDoor = outDoor;
    }

    /**
     * Prints all the information about Football hobby.
     */
    @Override
    public void tellAboutHobby() throws HobbyException {
        if (getName() == null) {
            throw new HobbyException("Hobby name can't be null");
        }
        System.out.printf(toString());
    }

    /**
     * Overrides toString method to display Football hobby object.
     * @return returns hobby
     */
    @Override
    public String toString() {
        String hobby = "ID: " + getId()
                + "; Name: " + getName()
                + "; Start Month: " + getStartMonth()
                + "; Start Year: " + getStartYear()
                + "; Popularity: " + getPopularity()
                + "; Expenses: " + getExpenses()
                + "; Risk Evaluation: " + getRiskEvaluation()
                + "; Football Type: " + footballType
                + "; OutDoor: " + outDoor
                + "; Is Active: " + isActive() + "\n";
        return hobby;
    }
}
