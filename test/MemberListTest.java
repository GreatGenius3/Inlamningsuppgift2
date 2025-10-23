
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MemberListTest
{
    @Test
    public void kollaAllaMedlemmar()
    {
        // Öppna medlemslistan
        MemberList memberList = new MemberList();
        memberList.loadMembersFromFile("res/memberlist/gym_medlemmar.txt");

        // Kolla antalet medlemmar i listan och börja skriv ut info på alla medlemmar
        int antalMedlemmar = memberList.getNumberOfMembers();
        for (int i = 0; i < antalMedlemmar; i++)
        {
            Member member = memberList.getMember(i);
            String strFormat = "Namn: %s Adress: %s Mailadress: %s Personnummer: %s Datum köpt gymmedlemskap: %s Datum senast uppdaterad: %s Medlemsniva: %s";
            IO.println(String.format(strFormat, member.getNamn(), member.getAddress(), member.getMailAdress(), member.getPersonNummer(), member.getMedlemsDatum(), member.getSenastUppdaterad(), member.getMedlemsNiva()));
        }
    }

    @Test
    public void kollaBetaldaMedlemmar()
    {
        // Öppna medlemslistan
        MemberList memberList = new MemberList();
        memberList.loadMembersFromFile("res/memberlist/gym_medlemmar.txt");

        // Nu kollar vi hur många betalda kunder vi har på listan
        // Vi tar fram dagens datum
        LocalDate today = LocalDate.now();
        int antalMedlemmar = memberList.getNumberOfMembers();
        for (int i = 0; i < antalMedlemmar; i++)
        {
            Member member = memberList.getMember(i);
            if (member.hasMemberPayed(today))
            {
                IO.println("Betalda medlem: " + member.getNamn() + "som betalade senast " + member.getSenastUppdaterad());
            }
        }
    }

    @Test
    public void testaInmatningavNamn()
    {
        // Öppna medlemslistan
        MemberList memberList = new MemberList();
        memberList.loadMembersFromFile("res/memberlist/gym_medlemmar.txt");

        assertEquals(20, memberList.getNumberOfMembers());
        assertEquals("Fredrik Berggren", memberList.getMember(0).getNamn());
        assertEquals("Astrid Larsson", memberList.getMember(1).getNamn());
        assertEquals("Pia Johansson", memberList.getMember(2).getNamn());
        assertEquals("Maria Lindström", memberList.getMember(3).getNamn());
        assertEquals("Maria Holmberg", memberList.getMember(4).getNamn());
        assertEquals("Eva Johansson", memberList.getMember(5).getNamn());
        assertEquals("Herbert Jansson", memberList.getMember(6).getNamn());
        assertEquals("Erik Gustafsson", memberList.getMember(7).getNamn());
        assertEquals("Linda Nyberg", memberList.getMember(8).getNamn());
        assertEquals("Gustaf Sjöberg", memberList.getMember(9).getNamn());
        assertEquals("Johan Lundberg", memberList.getMember(10).getNamn());
        assertEquals("Gustaf Karlsson", memberList.getMember(11).getNamn());
        assertEquals("Lennart Johansson", memberList.getMember(12).getNamn());
        assertEquals("Oskar Bengtsson", memberList.getMember(13).getNamn());
        assertEquals("Egon Jakobsson", memberList.getMember(14).getNamn());
        assertEquals("Viktoria Jakobsson", memberList.getMember(15).getNamn());
        assertEquals("Gustav Johansson", memberList.getMember(16).getNamn());
        assertEquals("Lilian Andersson", memberList.getMember(17).getNamn());
        assertEquals("Jan Persson", memberList.getMember(18).getNamn());
        assertEquals("Jakob Lundin", memberList.getMember(19).getNamn());

    }
}