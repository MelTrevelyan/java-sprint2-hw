import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportService reportService = new ReportService();
        printMenu();
        int userInput = scanner.nextInt();
        while (userInput != 0) {
            switch (userInput) {
                case 1:
                    reportService.readMonthsReports();
                    printMenu();
                    userInput = scanner.nextInt();
                    break;
                case 2:
                    reportService.readYearReport();
                    printMenu();
                    userInput = scanner.nextInt();
                    break;
                case 3:
                    reportService.verifyTheReports();
                    printMenu();
                    userInput = scanner.nextInt();
                    break;
                case 4:
                    reportService.printMonthsInformation();
                    printMenu();
                    userInput = scanner.nextInt();
                    break;
                case 5:
                    reportService.printYearInformation();
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

