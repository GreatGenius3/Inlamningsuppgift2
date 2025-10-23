import javax.swing.*;
import java.io.*;
import java.time.LocalDate;

//  Gym klassen
public class TheGym
{
    private MemberList memberList;
    final private String gymName;

    // Konstruktor för TheGym
    // 1 parameter (String)
    TheGym(String gymName)
    {
        this.gymName = gymName;
        memberList = null;
    }

    // Hämtar namnet på gymmet
    public String getGymName() { return gymName; }
    // Hämtar medlemslistan
    public MemberList getMemberList() { return memberList; }

    // Funktionen checkCustomerPersonNumber kollar om personnummret som angavs
    // är giltigt
    public boolean checkCustomerPersonNumber(String personnumber)
    {
        // Kontrollera först längden på strängen
        // Den måste vara 10 siffor
        if (personnumber.length() != 10)
        {
            // Annars felmeddelande
            JOptionPane.showMessageDialog (null, "Personnummret måste vara 10 siffor",
                    "Felaktig format",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kontrollera om strängen innehåller endast siffor
        for (int i = 0; i < personnumber.length(); i++)
        {
            // ÄR tecknet en siffra?
            if (!Character.isDigit(personnumber.charAt(i)))
            {
                // Annars visar vi ett felmeddelande
                JOptionPane.showMessageDialog (null, "Innehåller otillåtna tecken",
                        "Felaktig format",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    // Funktionen checkIfCustomerIsPaid kollar om kunden är en betald medlem
    // Tar in en Member objekt och ett datum
    public void checkIfCustomerIsPaid(Member aMember, LocalDate datum)
    {
        // Finns inte kunden i listan
        if (aMember == null)
        {
            JOptionPane.showMessageDialog (null, "Personen du söker saknar behörighet",
                    "Obehörig",
                    JOptionPane.ERROR_MESSAGE);
        }
        // Om medlemen finns i listan
        else
        {
            // Kolla nu om detta är en betald kund
            if (aMember.hasMemberPayed(datum))
            {
                JOptionPane.showMessageDialog (null, "Kunden " +
                                aMember.getNamn() +
                                " är medlem i nivån\n" +
                                aMember.getMedlemsNiva(),
                        "Kund",
                        JOptionPane.INFORMATION_MESSAGE);
                // Logga nu in kunden i PT:s logg fil
                if (memberList.saveMemberToPTfile("res/traininglog/pt_log.txt", aMember, datum) == false)
                {
                    // Blir något fel med skrivningen så skicka ett felmeddelande
                    JOptionPane.showMessageDialog (null, "Fel vid skrivning till PT filen",
                            "Filläsnings fel",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            // Eller om denna kund var en föredetta medlem
            else
            {
                JOptionPane.showMessageDialog (null, "Kunden " + aMember.getNamn() +
                                "\nKunden måste betala ny avgift för att aktivera medlemskapet igen.\n" +
                                "\nSenast betald " + aMember.getSenastUppdaterad(),
                        "Föredetta kund",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // Funktionen runGym kör igång programmet
    public void runGym()
    {
        boolean isRunning = true;

        // Skapa en medlemslista som öppnar en fil där alla medlemmar finns
        memberList = new MemberList();
        if (memberList.loadMembersFromFile("res/memberlist/gym_medlemmar.txt") == false)
        {
            JOptionPane.showMessageDialog (null, "Fel vid öppning av loggfilen",
                    "Fel",
                    JOptionPane.ERROR_MESSAGE);
            // Avsluta programmet
            System.exit(0);
        }


        // Vi kör så länge isRunning är sant
        while (isRunning)
        {
            // Hämta dagens datum
            LocalDate today = LocalDate.now();

            // Visa en dialogruta där vi frågar efter kundens namn eller personnummer
            String customer = JOptionPane.showInputDialog(null,
                    "Välkommen till " + gymName + "\n" +
                            "Och idag är det "+ today.getDayOfWeek() + "\n" +
                            "Och dagens datum "+ today.getYear() + "-" + today.getMonthValue() + "-" + today.getDayOfMonth() + "\n\n" +
                            "Nu kommer det fram en kund till receptionen.\n" +
                            "Skriv in kundens för och efternamn eller han/hon:s personnummer\n");

            // Trycker vi på kryss knappen eller avbryt knappen så avslutas programmet
            if (customer == null)
                isRunning = false;
            // Är rutan tom?
            else if (customer.isEmpty())
                JOptionPane.showMessageDialog(null, "Rutan får inte vara tom");
            else
            {
                // Kontrollera om det är ett personnummer genom att
                // kolla om första tecknet i strängen är en siffra
                if (Character.isDigit(customer.charAt(0)))
                {
                    // Kontrollera nu att det är ett giltligt personnummer
                    if (checkCustomerPersonNumber(customer))
                    {
                        // Lägg till "-" mellan de fyra sista siffrorna
                        customer = customer.substring(0, 6) + "-" + customer.substring(6);

                        // Nu kollar vi om det personnummret finns i medlemslistan
                        Member member = memberList.getMemberPersonNumber(customer);
                        checkIfCustomerIsPaid(member, today);
                    }
                }
                // Om inte så är det ett namn vi söker
                else
                {
                    Member member = memberList.getMemberName(customer);
                    checkIfCustomerIsPaid(member, today);
                }
            }
        }
    }
}
