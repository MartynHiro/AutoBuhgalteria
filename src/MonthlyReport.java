import java.util.ArrayList;

public class MonthlyReport {
    //Метод определения названия месяца по его порядковому номеру
    public static String[] getMonthName(ArrayList<String> monthsNumbers) {
        String[] fullMonthName = new String[monthsNumbers.size()]; //массив с полными именами месяцев

        for (int i = 0; i < monthsNumbers.size(); i++) {
            switch (monthsNumbers.get(i)) {
                case "01":
                    fullMonthName[i] = "Январь";
                case "02":
                    fullMonthName[i] = "Февраль";
                case "03":
                    fullMonthName[i] = "Март";
                case "04":
                    fullMonthName[i] = "Апрель";
                case "05":
                    fullMonthName[i] = "Май";
                case "06":
                    fullMonthName[i] = "Июнь";
                case "07":
                    fullMonthName[i] = "Июль";
                case "08":
                    fullMonthName[i] = "Август";
                case "09":
                    fullMonthName[i] = "Сентябрь";
                case "10":
                    fullMonthName[i] = "Октябрь";
                case "11":
                    fullMonthName[i] = "Ноябрь";
                case "12":
                    fullMonthName[i] = "Декабрь";
            }
        }
        return fullMonthName;
    }

    //Метод определения самого прибыльного товара в месяце
    protected static String[] mostProfit(ArrayList<String> dataLines) {
        String[] bestLines = new String[dataLines.size()]; //лучшие строки с прибылями за каждый месяц

        for (int i = 0; i < dataLines.size(); i++) {
            String[] lines = dataLines.get(i).split("\\n");
            int maxIncome = 0; //число самой большой прибыли

            for (int j = 1; j < lines.length; j++) {
                String[] data = lines[j].split(",");

                if (data[1].equals("false")) {
                    int income = Integer.parseInt(data[2]) * Integer.parseInt(data[3]);

                    if (maxIncome < income) {
                        maxIncome = income;
                        bestLines[i] = lines[j];
                    }
                }
            }
        }
        return bestLines;
    }

    //Метод определения самой большой траты в месяце
    protected static String[] mostSpending(ArrayList<String> dataLines) {
        String[] worseLines = new String[dataLines.size()]; //строки с самыми большими тратами за каждый месяц

        for (int i = 0; i < dataLines.size(); i++) {
            String[] lines = dataLines.get(i).split("\\n");
            int maxSpending = 0; //число самой большой траты

            for (int j = 1; j < lines.length; j++) {
                String[] data = lines[j].split(",");

                if (data[1].equals("true")) {
                    int spend = Integer.parseInt(data[2]) * Integer.parseInt(data[3]);

                    if (maxSpending < spend) {
                        maxSpending = spend;
                        worseLines[i] = lines[j];
                    }
                }
            }
        }
        return worseLines;
    }
}
