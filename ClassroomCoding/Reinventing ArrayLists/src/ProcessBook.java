/*
A class to:
  1) establish a scanner connection to a web-based text of a book;
  2) read every word from that book;
  3) add the words into a dynamic array object
 */

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class ProcessBook {

    /**
     * Establish a scanner connection to a book hosted on project Gutenberg. The method
     * requires a String with the link to the website with a text-version of the book.
     * It returns a scanner object to parse the book.
     *
     * @param link String with the link to the text version of a Project Gutenberg book
     * @return Scanner object that that allows us to parse the book or null if link is not valid.
     */
    public static Scanner connectToBook(String link) {
        /*
        The following two objects are required to establish a conduit, over the internet, between
        the program and the web page with the desired book, identified by the provided link. These
        objects are declared but are not assigned until we know if the link is correct and if a
        connection can be established. We use a try/catch block to test if a connection can be
        established. If it can, the scanner and inputStream objects are assigned values. If not, the
        catch part of the block will assign the scanner to null. This will signal the failure to
        connect using the provided link, but without throwing an exception and crashing the program.
         */
        Scanner scanner;
        InputStream inputStream;
        try {
            URL url = new URL(link);
            // Pull the conduit to the web site using URL's openstream
            inputStream = url.openStream();
            scanner = new Scanner(inputStream);
        } catch (Exception e) {
            // Something went wrong, set the scanner to null.
            scanner = null;
        }
        return scanner;
    }  // method connectToBook


    /**
     * Scans the contents of a provided scanner object to parse the words found therein and adds them
     * to a dynamic array that it then returns. If the scanner is null, because a connection could not
     * be established with the contents, the method returns a null Dynamic Array.
     *
     * @param bookToScan Scanner with contents to parse
     * @return DynamicArray with the words parsed, or null if the scanner was also null.
     */
    public static DynamicArray bookToArray(Scanner bookToScan) {
        // Declare the object as null for now, in case scanner is also null.
        DynamicArray words = null;
        if (bookToScan != null) {
            // Scanner is legit, initialize object words to a new dynamic array.
            words = new DynamicArray();
            // Scan the book.
            while (bookToScan.hasNext()) {
                // Obtain the next token from the scanner.
                String word = bookToScan.next();
                // Strip everything but letters, using POSIX regex and Pattern class.
                word = word.replaceAll("\\P{L}+", "");
                // if there is anything left in the token, and not already in the dynamic array, let's add it.
                if (word.length() > 0 && !words.contains(word))
                    words.add(word);
            }
        }
        return words;
    }  // method bookToArray


    /**
     * Driver code
     */
    public static void main(String[] args) {
        // Link to a book in the Project Gutenberg website. https://www.gutenberg.org/
        // You may choose a different one; here I am pointing to Verne's 20,000 Leagues under the Sea
        String linkToBook = "https://www.gutenberg.org/cache/epub/164/pg164.txt";
        // Get a scanner connected to the book we wish to examine.
        Scanner s = connectToBook(linkToBook);
        // Use the scanner to pull the book's words into a dynamic array.
        DynamicArray wordsFromTheBook = bookToArray(s);
        // Print some basic information about the dynamic array object
        System.out.println(wordsFromTheBook.describe());
    }
}