package com.nikitin.valeriy.hobby.hobbies;

/**
 * Represents hobbies.
 * This class includes:
 * <ul>
 *     <li>8 fields of primitive data types</li>
 *     <li>Default constructor and with parameters</li>
 *     <li>Getters and setters of all fields</li>
 *     <li>Abstract Method tellAboutHobby()</li>
 * </ul>
 * @author Valeriy Nikitin
 */
public abstract class Hobby {
    private static byte counter;
    private short id;
    private int startMonth;
    private long startYear;
    private float popularity; // hobby popularity among people
    private double expenses; //amount of money needed for particular hobby
    private char riskEvaluation; // A - the most risky hobby, C - less risky
    private boolean active;
    private String name;

    {
        counter++;
    }

    /**
     * Default constructor that defines only id.
     */
    public Hobby() {
        id += counter;
    }

    /**
     * Second constructor that calls default constructor.
     * @param name Hobby name
     * @param startMonth mHobby start month
     * @param startYear Hobby start year
     */
    public Hobby(String name, int startMonth, long startYear) {
        this();
        this.name = name;
        this.startMonth = startMonth;
        this.startYear = startYear;
    }

    /**
     * Constructor that calls second constructor.
     * @param name Hobby name
     * @param startMonth Hobby start month
     * @param startYear Hobby start year
     * @param popularity Hobby popularity
     * @param expenses amount of money needed for a hobby
     * @param riskEvaluation evaluates Hobby risk
     */
    public Hobby(String name, int startMonth, long startYear, float popularity,
                 double expenses, char riskEvaluation) {
        this(name, startMonth, startYear);
        this.popularity = popularity;
        this.expenses = expenses;
        this.riskEvaluation = riskEvaluation;
    }

    /**
     * Method to print all information about a Hobby.
     * such as
     * id, name, startMonth, startYear, popularity
     * expenses, riskEvaluation
     * @throws HobbyException
     */
    public abstract void tellAboutHobby() throws HobbyException;

    public short getId() {
        return id;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public long getStartYear() {
        return startYear;
    }

    public void setStartYear(long startYear) {
        this.startYear = startYear;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public char getRiskEvaluation() {
        return riskEvaluation;
    }

    public void setRiskEvaluation(char riskEvaluation) {
        this.riskEvaluation = riskEvaluation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Overrides toString method to display Hobby object.
     * @return returns hobby
     */
    @Override
    public String toString() {
        String hobby = "ID: " + id
                + "; Name: " + name
                + "; Start Month: " + startMonth
                + "; Start Year: " + startYear
                + "; Popularity: " + popularity
                + "; Expenses: " + expenses
                + "; Risk Evaluation" + riskEvaluation
                + "; Is Active: " + active + "\n";
        return hobby;
    }

}
