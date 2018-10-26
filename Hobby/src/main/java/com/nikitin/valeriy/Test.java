package com.nikitin.valeriy;

import com.nikitin.valeriy.hobbies.Fishing;
import com.nikitin.valeriy.hobbies.Football;
import com.nikitin.valeriy.hobbies.Hobby;
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

        Hobby[] hobbies = new Hobby[2];

        // Assigning values for the first hobby
        hobbyName = "Football";
        startMonth = 4;
        startYear = 2008L;
        popularity = 6.00f;
        expenses = 300.00d;
        riskEvaluation = 'A';
        String type = "Soccer";
        boolean outDoor = true;
        hobbies[0] = new Football(hobbyName, startMonth, startYear, type, outDoor);
        logger.info("Creating " + hobbyName + " hobby");
        test.checkMonth(startMonth, hobbies[0]);
        hobbies[0].setPopularity(popularity);
        hobbies[0].setExpenses(expenses);
        hobbies[0].setRiskEvaluation(riskEvaluation);
        hobbies[0].setActive(ACTIVE);
        logger.debug("Is " + hobbyName + " active: " + hobbies[0].isActive());

        // Assigning values for the second hobby
        hobbyName = "Fishing";
        startMonth = 0;
        startYear = 2015L;
        popularity = 2.00f;
        expenses = 545.00d;
        riskEvaluation = 'B';
        type = "Sea fishing";
        hobbies[1] = new Fishing(hobbyName, startMonth, startYear, popularity, expenses,
                                    riskEvaluation, type);
        logger.info("Creating " + hobbyName + " hobby");
        test.checkMonth(startMonth, hobbies[1]);
        hobbies[1].setActive(ACTIVE);
        logger.debug("Is " + hobbyName + " active: " + hobbies[1].isActive());
        //hobby[1].tellAboutHobby();

        for (Hobby hobby: hobbies) {
            hobby.tellAboutHobby();
        }

    }

    public void checkMonth(int startMonth, Hobby hobby) {
        if (startMonth <= 0 || startMonth > 12) {
            logger.error("Wrong month for " + hobby.getName() + " hobby");
        }
    }
}