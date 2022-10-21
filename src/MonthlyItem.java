public class MonthlyItem {
    String itemName;
    Boolean isExpense;
    Integer quantity;
    Integer sumOfOne;

    public MonthlyItem(String itemName, Boolean isExpense, Integer quantity, Integer sumOfOne) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
        this.isExpense = isExpense;
    }

}
