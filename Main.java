import java.io.BufferedReader;
import java.net.*;
import java.io.*;
import java.io.InputStreamReader;

class Main{

    static String emailID;

    public static void main(String[] args) throws IOException{
        //gets the email id
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter email ID: ");
        emailID = inputReader.readLine();

        //construct full web page
        String webPageAddress = "https://www.ecs.soton.ac.uk/people/" + emailID;

        //making URL object from web address
        URL website = new URL(webPageAddress);

        //using BufferedReader to read from URL object: website and turn it into a string
        BufferedReader websiteReader = new BufferedReader(new InputStreamReader(website.openStream()));
        String line;
        String name = "nothing";
        int nameEnd=0;
        while ((line = websiteReader.readLine()) != null)
            //checks to see if the line contains this title text which holds the name
            if (line.contains("<meta property=\"og:title\" content=")){
                //checks the char after the name so that the extra stuff can be removed
                nameEnd = line.indexOf("\" />");
                name = line.substring(35,nameEnd);
            }
        websiteReader.close();

        //outputs name for ID or says it doesn't exist
        if (name.equals("nothing")){
            System.out.println("Email ID doesn't exist...");
        } else {
            System.out.println("Person's name for given ID: "+name);
        }
    }
}