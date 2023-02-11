import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Добрый день!");
        while (true) {
            System.out.println("""
                    Введите номер команды:
                    \t 1. Считать месячный отчёт
                    \t 2. Считать годовой отчёт
                    \t 3. Сверить отчёты
                    \t 4. Вывести информацию о всех месячных отчётах
                    \t 5. Вывести информацию о годовом отчёте
                    \t 6. Закончить работу""");
            try {
                int userInput = scanner.nextInt();

                if (userInput == 6) {
                    break;
                }

                ArrayList<String> monthsData = new ArrayList<>();     //для хранения месячных отчётов
                ArrayList<String> monthsNames = new ArrayList<>(); //и их имён (можно соотнести с отчётами по номеру ячейки)
                String filePath;
                String yearData = null;
                String yearNumber = null;
                String[] yearLines;

                switch (userInput) {
                    case 1 -> {
                        System.out.println("Введите полный путь к файлу месячного отчёта");
                        filePath = scanner.next();
                        monthsData.add(FileManager.readFileText(filePath));
                        monthsNames.add(FileManager.readFileName(filePath));
                    }
                    case 2 -> {
                        System.out.println("Введите полный путь к файлу годового отчёта");
                        filePath = scanner.next();
                        yearData = FileManager.readFileText(filePath);
                        yearNumber = FileManager.readFileName(filePath);
                    }
                    case 3 -> {
                        if (yearData == null) {
                            System.out.println("Вы не предоставили годовой отчёт");
                            break;
                        } else {
                            yearLines = FileManager.fileSplit(yearData);
                        }
                        for (int i = 0; i < monthsData.size(); i++) { //проходимся по всем сохранённым месячным отчётам в списке
                            String[] monthLines = FileManager.fileSplit(monthsData.get(i));
                            if (Checking.compareMonthsYear(yearLines, monthLines, yearNumber, monthsNames.get(i))) {
                                System.out.println((i + 1) + ". Месяц прошёл сверку");
                            } else {
                                System.out.println((i + 1) + ". Месяц имеет расхождения по сверке!");
                            }
                        }
                    }
                    case 4 -> {
                        for (int i = 0; i < monthsData.size(); i++) {
                            String[] names = MonthlyReport.getMonthName(monthsData);

                            String[] allProfits = MonthlyReport.mostProfit(monthsData);
                            String[] allSpending = MonthlyReport.mostSpending(monthsData);

                            String[] profitLine = allProfits[i].split(",");
                            String[] spendingLine = allSpending[i].split(",");

                            System.out.print("В месяце " + names[i] + ":" +
                                    "\n \t Самый прибыльный товар был - " + profitLine[0] +
                                    " он принёс " + (Integer.parseInt(profitLine[2]) * Integer.parseInt(profitLine[3])) + "руб." +
                                    "\n \t Самая большая трата была - " + spendingLine[0] +
                                    " на него было потрачено " + (Integer.parseInt(spendingLine[2]) * Integer.parseInt(spendingLine[3])) + "руб." +
                                    "\n");
                        }
                    }
                    case 5 -> {
                        if (yearNumber == null) {
                            System.out.println("Вы не предоставили годовой отчёт");
                        } else {
                            int[] year = YearlyReport.getYearReport(yearData);

                            System.out.print("Отчёт по году " + YearlyReport.getYearNumber(yearNumber) + ": " +
                                    "\n \t Общая прибыль составила - " + year[0] +
                                    "\n \t Средний расход за год составил - " + year[1] +
                                    "\n \t Средний доход за год составил - " + year[2] +
                                    "\n");
                        }
                    }
                    default -> System.out.println("Вы вводите неверный номер операции!");
                }
            } catch (RuntimeException e) {
                System.out.println("Вы вводите не число!");
            }
        }
    }
}