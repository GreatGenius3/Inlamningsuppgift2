import java.time.LocalDate;

public class Member
{
    String namn;
    String address;
    String mailAdress;
    String personNummer;
    LocalDate memberDate;
    LocalDate memberLastDate;
    String medlemsNiva;

    // Konstruktor för Member
    // 7 parametrar (String)
    Member(String namn, String address,
           String mailAdress, String personNummer,
           String medlemsDatum, String senastUppdaterad, String medlemsNiva)
    {
        this.namn = namn;
        this.address = address;
        this.mailAdress = mailAdress;
        this.personNummer = personNummer;
        memberDate = LocalDate.parse(medlemsDatum);
        memberLastDate = LocalDate.parse(senastUppdaterad);
        this.medlemsNiva = medlemsNiva;
    }

    // Kopierings kontruktor för Member
    // 1 parameter (Member)
    Member(Member copy)
    {
        this.namn = copy.namn;
        this.address = copy.address;
        this.mailAdress = copy.mailAdress;
        this.personNummer = copy.personNummer;
        this.memberDate = copy.memberDate;
        this.memberLastDate = copy.memberLastDate;
        this.medlemsNiva = copy.medlemsNiva;
    }

    // Alla getters
    public String getNamn() { return namn; }
    public String getAddress() { return address; }
    public String getMailAdress() { return mailAdress; }
    public String getPersonNummer() { return personNummer; }
    public LocalDate getMedlemsDatum() { return memberDate; }
    public LocalDate getSenastUppdaterad() { return memberLastDate; }
    public String getMedlemsNiva() { return medlemsNiva; }

    // Alla setters
    // public void setNamn(String namn) { this.namn = namn; }
    public void setAddress(String address) { this.address = address; }
    public void setMailAdress(String mailAdress) { this.mailAdress = mailAdress; }
    public void setPersonNummer(String personNummer) { this.personNummer = personNummer; }
    public void setMedlemsDatum(LocalDate medlemsDatum) { this.memberDate = medlemsDatum; }
    public void setSenastUppdaterad(LocalDate senastUppdaterad) { this.memberLastDate = senastUppdaterad; }
    public void setMedlemsNiva(String medlemsNiva) { this.medlemsNiva = medlemsNiva; }

    // En funktion som kollar om medlemskapet är betalt minst 1 år tillbaka
    public boolean hasMemberPayed(LocalDate currentdate)
    {
        // Kolla nu om medlemskapet är betalt minst 1 år tillbaka
        LocalDate oneYearAgo = currentdate.minusYears(1);
        return memberLastDate.isAfter(oneYearAgo);
    }
}