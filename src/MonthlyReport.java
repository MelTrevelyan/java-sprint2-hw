
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    FileReader fileReader = new FileReader();
    public HashMap<Integer, ArrayList<MonthlyItem>> monthlyData;
    MonthlyReport() {
        monthlyData = new HashMap<>();
    }

/* Проверяем значение из файлов на null;
 * Делим файл по строкам, затем по запятым;
 * Сохраняем полученные значения строки в объекте класса MonthlyItem;
 * Объекты добавляем в список месяца;
 * Список помещаем в хэш-таблицу, где ключ - номер месяца;
 */
    public void addMonthsInformation (String path, Integer month) {
        ArrayList<MonthlyItem> oneMonthInformation = new ArrayList<>();
        String monthContent = fileReader.readFileContentsOrNull(path);
        if (monthContent != null) {
            String[] lines = monthContent.split("\r?\n");
            for (int i = 1; i < lines.length; i++) {
                String[] parts = lines[i].split(",");
                String itemName = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                int quantity = Integer.parseInt(parts[2]);
                int sumOfOne = Integer.parseInt(parts[3]);
                MonthlyItem monthlyItem = new MonthlyItem(itemName, isExpense, quantity, sumOfOne);
                oneMonthInformation.add(monthlyItem);
            }
            monthlyData.put(month, oneMonthInformation);
        }
    }

/* Считаем общую сумму трат за месяц;
 * @return полученное значение;
 */
    public Integer countMonthSpending(int month) {
        int sumOfSpending = 0;
        for (MonthlyItem item : monthlyData.get(month)) {
            if (item.isExpense) {
                sumOfSpending += item.quantity * item.sumOfOne;
            }
        }
        return sumOfSpending;
    }
/* Считаем общий доход за месяц;
 * @return полученное значение;
 */
    public Integer countMonthProfit(int month) {
        int sumOfProfit = 0;
        for (MonthlyItem item : monthlyData.get(month)) {
            if (!item.isExpense) {
                sumOfProfit += item.quantity * item.sumOfOne;
            }
        }
        return sumOfProfit;
    }
// Создаём список с названиями месяцев, чтобы потом использовать их в статистике;
    public void printMonthName(int month) {
        ArrayList<String> monthName = new ArrayList<>();
        monthName.add("Январь");
        monthName.add("Февраль");
        monthName.add("Март");
        System.out.println(monthName.get(month));
    }

    /* В цикле ищем, какой из объектов имеет в себе прибыль;
     * У них считаем произведение цены и количества предметов;
     * Сравниваем произведения, сохраняем максимальное и его название;
     */
    public void getMostProfitableItemInMonth(int month) {
        int multiply;
        int maxMultiply = 0;
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

    //Определяем и печатаем самый затратный товар (с учетом количества), и саму сумму;
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

