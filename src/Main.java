//***********************************************************************************
// YOUR NAME: Jayson M. Peralta
// COURSE TITLE: Advanced Object-Oriented Programming with Java (and C++/C)
// COURSE NUMBER: CIS 357
// PROF NAME: Il-Hyung Cho
// ASSIGNMENT NUMBER: Homework 2
// DUE DATE: 05/19/2020
// POSSIBLE POINTS: 100 Points
// PURPOSE: This java program that prompts user to enter Y/(y) or N/(n) for new sale.
// Then, it ask the user to enter for the product code, then print the item name.
// Furthermore, it prompt to enter quantity as integer. After entering valid quantity, it re-prompt product code for items.
// If the user enters -1, it displays the items list, subtotal, and total with tax.
// In addition, it prompt the user to enter tendered amount. Once the tender amount is valid, then calculate the change.
// Then prompt user to enter new sale and repeat the program if user enters Y/y to continue OR N/n to end the program*
//***********************************************************************************

/* Import java libraries */
import java.util.*;
import java.io.*;
import static java.lang.Integer.parseInt;

public class Main {
    /**
     * @throws IOException  If an input or output exception occurred
     */

    public static void main(String[] args) throws IOException {
        /* Scanner for user input */
        Scanner input = new Scanner(System.in);

        /*Create a variables and initialize with specified values*/
        String productCode = "";
        int quantity = 0;
        double tax = 0.06;
        double subtotal = 0.0;
        double totalAmountSaleWithTax = 0.0;
        double TotalAmountSaleWithTax = 0;
        double tenderAmount = 0;
        double change;
        double totalSaleForToday = 0.0;

        String listOfItems = "";
        String outputDataLine = "";

        /* assign item object to null */
        ProductSpecification productspec = null;

       // SalesLineItem sli = new SalesLineItem(productspec, quantity);

        /* Create a variable for input and output file*/
        String textInputFile = "";
        String textOutputFile = "";
        textInputFile = "hw1-in1.txt";

        /*Create a variable selection and assign it to zero*/
        int selection = 0;
        infile_outfile_selectionHeader(); // Method header that print selection choices
        // Use try catch to avoid crashing the program
        try {
            selection = input.nextInt();  // Enter selection

            // Check if it is equal to 1, then it uses hw1-out1.txt file for first sample run
            if (selection == 1) {
                textOutputFile = "hw1-out1.txt";
            }
            // Check if it is equal to 2, then it uses hw1-out2.txt file for second sample run
            else if (selection == 2) {
                textOutputFile = "hw1-out2.txt";
            }
            // Otherwise, the choice does not exists
            else {
                System.out.println("Your choice doesn't exist ");
                System.exit(0);
            }
        }
        // When the inputs is a String/Character, throw a mismatch input
        catch (InputMismatchException ex) {
            printMismatchInputHeader();
        }

        /* Create an instance of ProductCatalog class and pass the file name */
        ProductCatalog productcatalog = new ProductCatalog(textInputFile);

        // Prompt the user to enter Y/N
        System.out.println("Welcome to POST system!");
        System.out.print("Beginning a new sale(Y/N)");
        input.nextLine();
        String choice = input.nextLine();
        System.out.println("------------------------");

        /*If choice is equal to n/N, then exit the program*/
        if ((choice.equals("n") || choice.equals("N")))
        {
            exit();
        }
        /*If choice is not equal Y/y, then run again and it exit the program*/
        else if ((!choice.equals("y") && !choice.equals("Y")))
        {
            System.out.println("Invalid input!!! Please run again!");
            System.exit(0);
        }

        FileWriter outputDataFile = new FileWriter(textOutputFile);
        PrintWriter outputFile = new PrintWriter(outputDataFile);
        do {

            try{
                System.out.print("Enter product code:");
                productCode = input.nextLine();
            }
            catch (NumberFormatException ne)
            {
                input=new Scanner(System.in);
            }

            /* While product code is not equal to -1, continue the following instructions */
            while (!productCode.equals(-1)) {

                    System.out.print("Enter product code:");
                    try
                    {
                        productCode = input.nextLine();
                    }
                    catch (NumberFormatException ne)
                    {
                        input=new Scanner(System.in);
                    }

                //Get the item of the specified productCode of the user and assign it to item
              //  productspec = productcatalog.getSpecification(productCode);
                productspec = productcatalog.getSpecification(productCode);
              //  System.out.print("Product Spec: " + productspec);
                System.out.printf("%20s%s\n", "item name:", productspec.getDescription());

                try
                {
                    System.out.print("Enter quantity:");
                    quantity = parseInt(input.nextLine());
                }
                catch (NumberFormatException ne)
                {
                    printMismatchInputHeader();
                }
                SalesLineItem sli = new SalesLineItem(productspec, quantity);

                // Getting the subtotal of product specification
                new SalesLineItem(productspec, quantity);
               System.out.printf("%20s%.2f", "item total:$", sli.getSubtotal());

               // subtotal += quantity * productspec.getPrice();
                listOfItems += String.format("%10d %-15s $%.2f\n", quantity, productspec.getDescription(), sli.getSubtotal());
                System.out.println();

                try
                {
                    // Prompt the user to enter product code
                    System.out.print("Enter product code:");
                    productCode = input.nextLine();
                }
                // Catch error for product code if invalid inputs
                catch (NumberFormatException ne)
                {
                    System.out.println("!!! Invalid product code");
                    System.out.print("Enter product code:");
                    input=new Scanner(System.in);
                }
            }

            printListItemHeader();
            System.out.println(listOfItems);
         //   System.out.printf("Subtotal $%.2f\n", sli.getSubtotal());
          //  System.out.printf("Total with Tax (6%%) $ %.2f\n", calculateTotalAmountSaleWithTax(sli.getSubtotal(), tax));


            // Try catch the tender amount for errors, then, re-enters again if it is invalid
            try {
                System.out.printf("Enter tendered amount,$");
                tenderAmount = Double.parseDouble(input.nextLine());
            }catch (NumberFormatException ne)
            {
                System.out.println("Invalid tendered amount");
                input=new Scanner(System.in);
            }

            // Re-assign the total amount with tax in a new variable called TotalAmountSaleWithTax and it uses this to pass in calculateChange()
            TotalAmountSaleWithTax += calculateTotalAmountSaleWithTax(subtotal, tax);
            totalSaleForToday = totalSaleForToday + calculateTotalAmountSaleWithTax(subtotal, tax);

            // Prompt the user to enter tender amount
            System.out.printf("Tendered amount $ %.2f\n", tenderAmount);
            Payment tenderamt  = new Payment(tenderAmount);  /* Create a instance of Payment */

            // Check if the tender amount is greater that
            while (!(tenderAmount > calculateTotalAmountSaleWithTax(subtotal, tax)))
            {
                try{
                    System.out.println("Not enough tender amount. Please give appropriate tender amount");
                    System.out.printf("Enter tendered amount,$");
                    tenderAmount = Double.parseDouble(input.nextLine());
                }
                catch (NumberFormatException ne)
                {
                    input=new Scanner(System.in);
                    //printMismatchInputHeader();
                }
            }
            // Print the change when tender amount is valid
            System.out.printf("Change $ %.2f\n", calculateChange(tenderAmount, TotalAmountSaleWithTax));

            // Beginning a new sale Header
            printHeader();
            choice = input.nextLine();

            // Putting it in output file
            outputDataLine = "----------------------------" + "\nItems list:";
            outputFile.println(outputDataLine);
            outputFile.println(listOfItems);
            outputFile.printf("Subtotal $%.2f\n", subtotal);
            outputFile.printf("Total with Tax (6%%) $ %.2f\n", calculateTotalAmountSaleWithTax(subtotal, tax));
            outputFile.println("Tender Amount: " + tenderAmount);
            outputFile.println("Change $ " + calculateChange(tenderAmount, TotalAmountSaleWithTax));


            // Reset all the data field values for next sales
            if (choice.equals("Y") || choice.equals("y"))
            {
                subtotal = 0.0;
                tenderAmount = 0.0;
                change = 0.0;
                productCode = "";
                listOfItems = "";
                //item = null;
                TotalAmountSaleWithTax = 0;
            }

            // while choice is equal to y/Y, do loop instructions will continue executing
        } while (choice.equals("y") || choice.equals("Y"));

        /* Putting it in output txt file*/
        outputFile.println("");
        outputFile.printf("The total sale for the day is $ %.2f\n", totalSaleForToday);

        /* Close the output file */
        outputFile.close();

        /* Print the total sale for the day */
        System.out.printf("The total sale for the day is $%.2f\n", totalSaleForToday);
        exit();
    }

    /**
     * Returns the calculated change of the sale transaction
     * @param tenderAmount,totalAmountSaleWithTax use to get the change by subtracting tenderAmount by totalAmountSaleWithtax
     * @return calculated change
     */
    public static double calculateChange(double tenderAmount, double totalAmountSaleWithTax){
        // Math.round((double)41.3f * 100) / 100.0;
        return Math.round((double)(tenderAmount - totalAmountSaleWithTax) * 100) / 100.0;
    }

    /**
     * Returns the calculated total sale for today
     * @param totalSaleForToday,subtotal use to get the total sale for today
     * @return the total sale for today
     */
    public static double calculateTotalSaleForToday(double totalSaleForToday, double subtotal){
        return totalSaleForToday + subtotal;
    }

    /**
     * Returns the calculated total sale with tax
     * @param subtotal,tax use to get the total sale with tax
     * @return total sale with tax
     */
    public static double calculateTotalAmountSaleWithTax(double subtotal, double tax){
        return ((subtotal + subtotal * tax) * 100) / 100.0;
    }

    /**
     * Method that print the selection header for hw1-out1.txt and hw1-out2.txt
     */
    public static void infile_outfile_selectionHeader() {
        System.out.println("Please enter a choice ");
        System.out.println("Enter 1: First sample run,  hw1-out1.txt for storing the output in the file");
        System.out.println("Enter 2: Second sample run,  hw1-out2.txt for storing the output in the file");
    }


    /**
     * Method that print Good Bye and exit the program
     */
    public static void exit() {
        System.out.println("Thanks for using POST system. Goodbye.");
        System.exit(0);
    }

    /**
     * Method that print Item list header
     */
    public static void printListItemHeader() {
        System.out.println("-------------------------");
        System.out.println("Items list:");
    }

    /**
     * Method that print Beginning a new sale header
     */
    public static void printHeader() {
        System.out.println("");
        System.out.println("Beginning a new sale (Y/N): ");
        System.out.println("------------------");
    }

    /**
     * Method that print invalid data. It will exit the program, then re-run again.
     */
    public static void printMismatchInputHeader() {
        System.out.println("You entered wrong data." );
        System.out.println("Run the program again!!!" );
        System.exit(0);
    }

}
