package com.nikitin.valeriy;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

/**
 * Test class which contains main function
 * @author Valeriy Nikitin
 */
public class Test {
    private final static Logger logger = LogManager.getLogger(Test.class);
    private static String hobbyName;
    private static int startMonth;
    private static long startYear;
    private static float popularity;
    private static double expenses;
    private static char riskEvaluation;
    private final static boolean ACTIVE = true;

    public static void main(String[] args) {

        Test test = new Test();
        // Assigning values for the first hobby
        hobbyName = "Reading";
        startMonth = 2;
        startYear = 2005L;
        popularity = 5.00f;
        expenses = 200.00d;
        riskEvaluation = 'D';

        Hobby reading = new Hobby();
        logger.info("Creating " + hobbyName + " hobby");
        test.checkMonth(startMonth, reading);
        reading.setName("Reading");
        reading.setStartMonth(startMonth);
        reading.setStartYear(startYear);
        reading.setExpenses(expenses);
        reading.setPopularity(popularity);
        reading.setRiskEvaluation(riskEvaluation);
        reading.setActive(ACTIVE);
        logger.debug("Is " + hobbyName + " active: " + reading.isActive());
        reading.tellAboutHobby();

        // Assigning values for the second hobby
        hobbyName = "Cycling";
        startMonth = 4;
        startYear = 2008L;
        popularity = 6.00f;
        expenses = 300.00d;
        riskEvaluation = 'A';

        Hobby cycling = new Hobby(hobbyName, startMonth, startYear);
        logger.info("Creating " + hobbyName + " hobby");
        test.checkMonth(startMonth, cycling);
        cycling.setPopularity(popularity);
        cycling.setExpenses(expenses);
        cycling.setRiskEvaluation(riskEvaluation);
        cycling.setActive(ACTIVE);
        logger.debug("Is " + hobbyName + " active: " + cycling.isActive());
        cycling.tellAboutHobby();

        // Assigning values for the third hobby
        hobbyName = "Bird Watching";
        startMonth = 0;
        startYear = 2015L;
        popularity = 2.00f;
        expenses = 545.00d;
        riskEvaluation = 'C';

        Hobby birdWatching = new Hobby(hobbyName, startMonth, startYear, popularity, expenses, riskEvaluation);
        logger.info("Creating " + hobbyName + " hobby");
        test.checkMonth(startMonth, birdWatching);
        birdWatching.setActive(ACTIVE);
        logger.debug("Is " + hobbyName + " active: " + birdWatching.isActive());
        birdWatching.tellAboutHobby();
    }

    public void checkMonth(int startMonth, Hobby hobby) {
        if (startMonth <= 0 || startMonth > 12) {
            logger.error("Wrong month for " + hobby.getName() + " hobby");
        }
    }
}