
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    FileReader fileReader = new FileReader();
    public HashMap<Integer, YearlyItem> yearlyProfit;
    public HashMap<Integer, YearlyItem> yearlySpending;

    YearlyReport() {
        yearlyProfit = new HashMap<>();
        yearlySpending = new HashMap<>();
    }

    /* Проверяем значение из файлов на null;
     * Делим файл по строкам, затем по запятым;
     * Сохраняем полученные значения строки в объекте класса YearlyItem;
     * Проверяем, является ли тратой isExpense;
     * Помещаем объекты в хэш-таблицу (доходов или расходов), где ключ - номер месяца;
     */
    public void addYearInformation(String path) {
        String yearContent = fileReader.readFileContentsOrNull(path);
        if (yearContent != null) {
            String[] lines = yearContent.split("\r?\n");
            for (int i = 1; i < lines.length; i++) {
                String[] parts = lines[i].split(",");
                Integer month = Integer.parseInt(parts[0]);
                Integer amount = Integer.parseInt(parts[1]);
                Boolean isExpense = Boolean.parseBoolean(parts[2]);
                YearlyItem yearlyItem = new YearlyItem(month, amount, isExpense);
                if (isExpense) {
                    yearlySpending.put(month, yearlyItem);
                } else {
                    yearlyProfit.put(month, yearlyItem);
                }
            }
        }
    }

    // Создаём список с номером года, чтобы потом использовать его в статистике
    public void printYear(int index) {
        ArrayList<String> yearName = new ArrayList<>();
        yearName.add("2021");
        System.out.println(yearName.get(index) + " год");
    }

    // Считаем прибыль за месяц как разницу между доходом и расходом;
    public void getProfits() {
        int sumProfit;
        for(int i = 1; i < 4; i++) {
            sumProfit = yearlyProfit.get(i).amount - yearlySpending.get(i).amount;
            System.out.println("Прибыль за " + i + " месяц: " + sumProfit);
        }
    }

    // Получаем сумму трат за все месяцы, делим на количество месяцев (получаем среднее значение);
    public void getAverageSpending() {
        int averageSpending;
        int expensesSum = 0;
        for (int i = 1; i < 4; i++) {
            expensesSum += yearlySpending.get(i).amount;
        }
        averageSpending = expensesSum / yearlySpending.size();
        System.out.println("Средняя трата за месяц - " + averageSpending);
    }

    // Получаем сумму доходов за все месяцы, делим на количество месяцев (получаем среднее значение);
    public void getAverageProfit() {
        int averageProfit;
        int profitsSum = 0;
        for (int i = 1; i < 4; i++) {
            profitsSum += yearlyProfit.get(i).amount;
        }
        averageProfit = profitsSum / yearlyProfit.size();
        System.out.println("Средний доход за месяц - " + averageProfit);
    }
}
