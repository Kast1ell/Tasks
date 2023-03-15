package POJOS;

public class Postings {
    private String MatDoc;
    private String Item;
    private String DocDate;
    private String PstngDate;
    private String Quantity;
    private String BUn;
    private String AmountLC;
    private String Crcy;
    public String UserName;
    private String MaterialDescription;

    private Boolean Autorized = false;

    public Postings(
            String MatDoc,
            String Item,
            String DocDate,
            String PstngDate,
            String MaterialDescription,
            String Quantity,
            String BUn,
            String AmountLC,
            String Crcy,
            String UserName
    ) {

        this.MatDoc = MatDoc;
        this.Item = Item;
        this.DocDate = DocDate;
        this.PstngDate = PstngDate;
        this.MaterialDescription = MaterialDescription;
        this.Quantity = Quantity;
        this.BUn = BUn;
        this.AmountLC = AmountLC;
        this.Crcy = Crcy;
        this.UserName = UserName;
    }
    public String getMatDoc() {
        return MatDoc;
    }
    public String getItem() {
        return Item;
    }
    public String getDocDate() {
        return DocDate;
    }
    public String getPstngDate() {
        return PstngDate;
    }
    public String getQuantity() {
        return Quantity;
    }
    public String getBUn() {
        return BUn;
    }
    public String getAmountLC() {
        return AmountLC;
    }
    public String getCrcy() {
        return Crcy;
    }
    public String getUserName() {
        return UserName;
    }
    public String getMaterialDescription() {
        return MaterialDescription;
    }
    public Boolean getAutorized() {
        return Autorized;
    }
    public void setAutorized(Boolean Autorized) {
        this.Autorized = Autorized;
    }
    public String getString() {
        String buf = "";
        buf += this.MatDoc + " " + this.Item + " " + this.DocDate + " " + this.PstngDate + " " + this.MaterialDescription + " " + this.Quantity + " " + this.BUn + " " + this.AmountLC + " " + this.Crcy + " " + this.UserName + " " + this.Autorized;
        return buf;
    }
}
