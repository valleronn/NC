package com.nikitin.valeriy.Hobbies;

import com.nikitin.valeriy.Hobby;

/**
 * Represents Football hobbies which extends Hobby
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

    public Football() {
        super();
    }

    public Football(String name, int startMonth, long startYear, String footballType, boolean outDoor) {
        super(name, startMonth, startYear);
        this.footballType = footballType;
        this.outDoor = outDoor;
    }

    public Football(String name, int startMonth, long startYear, float popularity,
                    double expenses, char riskEvaluation, String footballType, boolean outDoor) {
        super(name, startMonth, startYear, popularity, expenses, riskEvaluation);
        this.footballType = footballType;
        this.outDoor = outDoor;
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
     * Prints all the information about Football hobby
     */
    @Override
    public void tellAboutHobby() {
        System.out.printf(toString());
    }

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
