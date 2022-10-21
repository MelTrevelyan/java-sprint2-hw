import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 0) {
            switch (userInput) {
                case 1:
                    for (int i = 1; i < 4; i++) {
                        monthlyReport.addMonthsInformation("resources/m.20210" + i + ".csv", i);
                    }
                    System.out.println("Отчеты по месяцам успешно считаны!");
                    printMenu();
                    userInput = scanner.nextInt();
                    break;
                case 2:
                    yearlyReport.addYearInformation("resources/y.2021.csv");
                    System.out.println("Годовой отчёт успешно считан!");
                    printMenu();
                    userInput = scanner.nextInt();
                    break;
                case 3:
                    if (!monthlyReport.monthlyData.isEmpty() && !yearlyReport.yearlySpending.isEmpty() && !yearlyReport.yearlyProfit.isEmpty()) {
                        for (int i = 1; i < 4; i++) {
                            if (!monthlyReport.countMonthSpending(i).equals(yearlyReport.yearlySpending.get(i).amount) ||
                                    !monthlyReport.countMonthProfit(i).equals(yearlyReport.yearlyProfit.get(i).amount)) {
                                System.out.println("В месяце № " + i + " произошла ошибка в отчетах");
                            }
                        }
                        System.out.println("Сверка данных совершена");
                    } else {
                        System.out.println("Команда не выполнена. Перед сверкой необходимо считать месячные и годовой отчеты");
                    }
                    printMenu();
                    userInput = scanner.nextInt();
                    break;
                case 4:
                    for(int i = 1; i < 4; i++) {
                        monthlyReport.printMonthName(i - 1);
                        monthlyReport.getMostProfitableItemInMonth(i);
                        monthlyReport.getBiggestExpense(i);
                    }
                    printMenu();
                    userInput = scanner.nextInt();
                    break;
                case 5:
                        yearlyReport.printYear(0);
                        yearlyReport.getProfits();
                        yearlyReport.getAverageSpending();
                        yearlyReport.getAverageProfit();
                    printMenu();
                    userInput = scanner.nextInt();
                    break;
                default:
                    System.out.println("Извините, такой команды пока нет.");
                    printMenu();
                    userInput = scanner.nextInt();
                    break;
            }
        }
        System.out.println("Программа завершена");
    }
    private static void printMenu() {
        System.out.println("Что вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выйти из программы");
    }
}

