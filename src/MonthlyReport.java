import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    public HashMap<Integer, ArrayList<MonthlyItem>> monthlyData;
    MonthlyReport() {
        monthlyData = new HashMap<>();
    }

    public void addMonthsInformation (String path, Integer month) {
        ArrayList<MonthlyItem> oneMonthInformation = new ArrayList<>();
        String monthContent = readFileContentsOrNull(path);
        String[] lines = monthContent.split("\r?\n");                 //Делим файл по строкам, затем по запятым;
        for (int i = 1; i < lines.length; i++) {                            //Сохраняем полученные значения строки в объекте класса MonthlyItem;
            String[] parts = lines[i].split(",");                     //Объекты добавляем в список месяца;
            String itemName = parts[0];                                     //Список помещаем в хэш-таблицу, где ключ - номер месяца;
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sumOfOne = Integer.parseInt(parts[3]);
            MonthlyItem monthlyItem = new MonthlyItem(itemName, isExpense, quantity, sumOfOne);
            oneMonthInformation.add(monthlyItem);
        }
            monthlyData.put(month, oneMonthInformation);

    }

    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
    public Integer countMonthSpending(int month) {
        int sumOfSpending = 0;
        for (MonthlyItem item : monthlyData.get(month)) {
            if (item.isExpense) {
                sumOfSpending += item.quantity * item.sumOfOne;
            }
        }
        return sumOfSpending;
    }

    public Integer countMonthProfit(int month) {
        int sumOfProfit = 0;
        for (MonthlyItem item : monthlyData.get(month)) {
            if (!item.isExpense) {
                sumOfProfit += item.quantity * item.sumOfOne;
            }
        }
        return sumOfProfit;
    }

    public void printMonthName(int month) {
        ArrayList<String> monthName = new ArrayList<>();
        monthName.add("Январь");
        monthName.add("Февраль");
        monthName.add("Март");
        System.out.println(monthName.get(month));
    }
    public void getMostProfitableItemInMonth(int month) {                   //В цикле ищем, какой из объектов имеет в себе прибыль;
        int multiply;                                                       //У них считаем произведение цены и количества предметов;
        int maxMultiply = 0;                                                //Сравниваем произведения, сохраняем максимальное и его название;
        String name = null;
        for (MonthlyItem item : monthlyData.get(month)) {
            if (!item.isExpense) {
                multiply = item.quantity * item.sumOfOne;
                if (multiply > maxMultiply) {
                    maxMultiply = multiply;
                    name = item.itemName;
                }
            }
        }
        System.out.println("Товар месяца с наибольшей выручкой: " + name + " , сумма выручки: " + maxMultiply);
    }

    public void getBiggestExpense(int month) {
        String name = null;
        int maxExpense = 0;
        int expense;
        for (MonthlyItem item : monthlyData.get(month)) {
            if (item.isExpense) {
                expense = item.quantity * item.sumOfOne;
                if (expense > maxExpense) {
                    maxExpense = expense;
                    name = item.itemName;
                }
            }
        }
        System.out.println("Товар, на который потрачено больше всего в этом месяце: " + name + ", сумма траты: " + maxExpense);
    }

}

