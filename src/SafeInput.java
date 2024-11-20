import java.util.Scanner;

public class SafeInput {
    /**

     *

     * @param pipe a Scanner opened to read from System.in

     * @param prompt prompt for the user

     * @return a String response that is not zero length

     */

    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do
        {
            System.out.print("\n" +prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);

        return retString;
    }

    public static int getInt(Scanner pipe, String prompt)
    {
        int retValue = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine(); // clear key buffer
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer not: " + trash);
            }
        }while(!done);

        return retValue;
    }

    public static double getDouble(Scanner pipe, String prompt)
    {
        double retValue = 0;
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + ": ");
            if(pipe.hasNextDouble())
            {
                retValue = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double not: " + trash);
            }
        }while(!done);

        return retValue;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        int retValue = 0;
        String trash = "";
        boolean done = false;

        do
        {
            retValue = 0;
            System.out.print("\n" + prompt + ": ");
            if(pipe.hasNextInt())
            {
                retValue = pipe.nextInt();
                if (retValue >= low && retValue <= high) {
                    pipe.nextLine(); // clear key buffer
                    done = true;
                } else {
                    System.out.println("You must enter a valid integer within the range [" + low + "-" + high + "] not: " + retValue);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid integer within the range [" + low + "-" + high + "] not: " + trash);
            }
        }while(!done);

        return retValue;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        double retValue = 0;
        String trash = "";
        boolean done = false;

        do
        {
            retValue = 0;
            System.out.print("\n" + prompt + ": ");
            if(pipe.hasNextDouble())
            {
                retValue = pipe.nextDouble();
                if (retValue >= low && retValue <= high) {
                    pipe.nextLine(); // clear key buffer
                    done = true;
                } else {
                    trash = pipe.nextLine();
                    System.out.println("You must enter a valid double within the range [" + low + "-" + high + "] not: " + trash);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a valid double within the range [" + low + "-" + high + "] not: " + trash);
            }
        }while(!done);

        return retValue;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt)
    {
        boolean retValue = false;
        String input = "";
        String trash = "";
        boolean done = false;

        do
        {
            System.out.print("\n" + prompt + ": ");
            input = "";
            if(pipe.hasNextLine())
            {
                input = pipe.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    retValue = true;
                    done = true;
                } else if (input.equalsIgnoreCase("N")) {
                    retValue = false;
                    done = true;
                } else {
                    System.out.println("You must enter a valid [Y/N] input not: " + input);
                }
            }
        }while(!done);

        return retValue;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regExPattern)
    {
        String retString = "";
        boolean done = false;

        do
        {
            System.out.print(prompt + ": ");
            retString = pipe.nextLine();

            if(retString.matches(regExPattern))
            {
                done = true;
            } else {
                System.out.println("Invalid input: " + retString + "\n");
            }

        }while(!done);

        return retString;
    }

    public static void prettyHeader(String msg)
    {
        int HEADER_WIDTH = 60;
        System.out.println("");
        for (int count = 1; count <= HEADER_WIDTH; count++) {
            System.out.print("*");
        }
        System.out.print("\n***");
        for (int count = 1; count <= (HEADER_WIDTH - msg.length())/2 - 3; count++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int count = 1; count <= (HEADER_WIDTH - msg.length())/2 - 3; count++) {
            System.out.print(" ");
        }
        System.out.print("***\n");
        for (int count = 1; count <= HEADER_WIDTH; count++) {
            System.out.print("*");
        }
        System.out.println("");
    }
}
