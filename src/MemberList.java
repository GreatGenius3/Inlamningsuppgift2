import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

// Klassen MemberList
public class MemberList
{
    // Listan med alla Member objekt
    List<Member> listOfMembers;

    // Kontruktor för Memberlist
    MemberList() { listOfMembers = null; }

    // Funktion för att ladda alla medlemmar från en fil
    public boolean loadMembersFromFile(String filename)
    {
        listOfMembers = new ArrayList<>();

        // Skapa en Path från String
        Path filePath = Path.of(filename);
        try
        {
            // Scanner för inläsning av filen
            Scanner fileScanner = new Scanner(filePath);

            // Första raden är rubriker, hoppa över den
            fileScanner.nextLine();

            // Nu kör vi så länge det finns något att läsa in
            while(true)
            {
                String firstline = "";

                // Har vi något kvar att läsa?
                // Om inte; avbryt
                if (!fileScanner.hasNextLine())
                    break;

                // Läs in första raden
                if (fileScanner.hasNextLine())
                    firstline = fileScanner.nextLine();

                // Nu splittar vi strängen vid barje kolontecken ";"
                String[] data = firstline.split(";");

                // Skapa sedan en ny medlem med alla data från String arrayen
                Member newMember = new Member(data[0], data[1],
                        data[2], data[3],
                        data[4], data[5], data[6]);

                // Lägg till den nya medlemmen i listan
                listOfMembers.add(newMember);

            }
        }
        // Om något skulle gå fel; släng iväg ett felmeddelande
        catch (IOException e)
        {
            e.printStackTrace();
            IO.println("Kunde inte läsa filen: " + filePath);
            return false;
        }
        catch (IllegalStateException e)
        {
            e.printStackTrace();
            IO.println("Felaktig värde inläst: " + filePath);
            return false;
        }

        return true;
    }

    // Funktion för att spara ner en medlems träningstid i en
    // fil som personliga tränaren behöver
    public boolean saveMemberToPTfile(String filename, Member aMember, LocalDate newDate)
    {
        try
        {
            // Nu öppnar vi file pt_log.txt där vi loggar in kundens namn, personnummer samt dagens datum
            FileWriter logFile = new FileWriter(filename, true);

            // Förbered textformatet som ska skrivas till filen
            String writeFormat = "Namn: %s\nPersonnummer: %s\nDatum: %s\n\n";

            // Skriv till filen
            logFile.write(String.format(writeFormat, aMember.getNamn(), aMember.getPersonNummer(), newDate));

            // Stäng ner filen
            logFile.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            IO.println("Kunde inte läsa filen: " + filename);
            return false;
        }
        return true;
    }

    // Funktion som tar fram medlemslistan
    public List<Member> getMemberList()
    {
        return listOfMembers;
    }

    // Funktion som tar fram antalet medlemmar
    public int getNumberOfMembers()
    {
        return listOfMembers.size();
    }

    // Funktion som tar fram en medlem via index
    public Member getMember(int index)
    {
        return listOfMembers.get(index);
    }

    // Funktion som tar fram en medlem via namn
    public Member getMemberName(String name)
    {
        for (Member member : listOfMembers)
        {
            // Hittar vi medlemmen returnera den
            if (member.getNamn().equals(name))
                return member;
        }
        // Annars returnera null
        return null;
    }

    // Funktion som tar fram en medlem via personnummer
    public Member getMemberPersonNumber(String personNumber)
    {
        for (Member member : listOfMembers)
        {
            if (member.getPersonNummer().equals(personNumber))
                return member;
        }
        // Annars returnera null
        return null;
    }
}