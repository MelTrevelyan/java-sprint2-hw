public class ReportService {
    MonthlyReport monthlyReport = new MonthlyReport();
    YearlyReport yearlyReport = new YearlyReport();

    public void readMonthsReports() {
        for (int i = 1; i < 4; i++) {
            monthlyReport.addMonthsInformation("resources/m.20210" + i + ".csv", i);
        }
        System.out.println("Отчеты по месяцам успешно считаны!");
    }

    public void readYearReport() {
        yearlyReport.addYearInformation("resources/y.2021.csv");
        System.out.println("Годовой отчёт успешно считан!");
    }

    public void verifyTheReports() {
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
    }

    public void printMonthsInformation() {
        if (!monthlyReport.monthlyData.isEmpty()) {
            for (int i = 1; i < 4; i++) {
                monthlyReport.printMonthName(i - 1);
                monthlyReport.getMostProfitableItemInMonth(i);
                monthlyReport.getBiggestExpense(i);
            }
        } else {
            System.out.println("Информация по месяцам не была считана, невозможно вывести статистику.");
        }
    }

    public void printYearInformation() {
        if (!yearlyReport.yearlySpending.isEmpty() && !yearlyReport.yearlyProfit.isEmpty()) {
            yearlyReport.printYear(0);
            yearlyReport.getProfits();
            yearlyReport.getAverageSpending();
            yearlyReport.getAverageProfit();
        } else {
            System.out.println("Информация по году не была считана, невозможно вывести статистику.");
        }
    }
}
