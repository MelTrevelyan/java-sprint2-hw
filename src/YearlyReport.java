import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    public HashMap<Integer, YearlyItem> yearlyProfit;
    public HashMap<Integer, YearlyItem> yearlySpending;

    YearlyReport() {
        yearlyProfit = new HashMap<>();
        yearlySpending = new HashMap<>();
    }

    public void addYearInformation(String path) {
        String yearContent = readFileContentsOrNull(path);
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
    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public void printYear(int index) {
        ArrayList<String> yearName = new ArrayList<>();
        yearName.add("2021");
        System.out.println(yearName.get(index) + " год");
    }

    public void getProfits() {
        int sumProfit;
        for(int i = 1; i < 4; i++) {
            sumProfit = yearlyProfit.get(i).amount - yearlySpending.get(i).amount;
            System.out.println("Прибыль за " + i + " месяц: " + sumProfit);
        }
    }
    public void getAverageSpending() {
        int averageSpending;
        int expensesSum = 0;
        for (int i = 1; i < 4; i++) {
            expensesSum += yearlySpending.get(i).amount;
        }
        averageSpending = expensesSum / yearlySpending.size();
        System.out.println("Средняя трата за месяц - " + averageSpending);
    }

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
