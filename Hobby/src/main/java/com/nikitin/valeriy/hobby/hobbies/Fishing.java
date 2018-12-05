
package com.nikitin.valeriy.hobby.hobbies;

/**
 * Represents Fishing hobbies which extends Hobby.
 * This class includes:
 * <ul>
 *     <li>fishingType field</li>
 *     <li>Default constructor and with parameters</li>
 *     <li>Getters and setters</li>
 *     <li>Overrides method tellAboutHobby() of Hobby</li>
 * </ul>
 * @author Valeriy Nikitin
 */
public class Fishing extends Hobby {
    private String fishingType;

    public Fishing() {
        super();
    }

    public Fishing(String name, int startMonth, long startYear,
                   String fishingType) {
        super(name, startMonth, startYear);
        this.fishingType = fishingType;
    }

    public Fishing(String name, int startMonth, long startYear,
                   float popularity, double expenses, char riskEvaluation,
                   String fishingTypeType) {
        super(name, startMonth, startYear, popularity, expenses,
                riskEvaluation);
        this.fishingType = fishingTypeType;
    }

    /**
     * Prints all the information about Fishing hobby.
     */
    @Override
    public void tellAboutHobby() throws HobbyException {
        if (getName() == null) {
            throw new HobbyException("Hobby name can't be null");
        }
        System.out.printf(toString());
    }

    /**
     * Overrides toString method to display Fishing hobby object.
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
                + "; Fishing Type: " + fishingType
                + "; Is Active: " + isActive() + "\n";
        return hobby;
    }
}
