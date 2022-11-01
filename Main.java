import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        if(!db.open()){
            System.out.println("Can't open");
            return;
        }
        System.out.println();
        //List<members> members = db.members(58143026);
        List<amounts> amounts =db.amounts("EarlGray Tea","None");
        for(amounts amount:amounts){
            System.out.println("Second_choice: "+amount.getSecond_choice()+", Total_amount: "+amount.getAmount());

        }
       // List<menu> menus=db.menu();
        //List<option_choice> option_choices=db.option_choice();
        System.out.println();
        db.close();
    }
}