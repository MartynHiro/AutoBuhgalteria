public class YearlyReport {
    //Метод получения номера года
    protected static String getYearNumber(String fileName) {
        return fileName.substring(2, 5);
    }

    //Метод получения общегодовой прибыли
    protected static int[] getYearReport(String yearData) {
        String[] months = FileManager.fileSplit(yearData);
        int totalIncome = 0;
        int totalSpending = 0;
        int[] yearReport = new int[3]; //в 0 ячейке прибыль, в 1- средний расход, в 2- средний доход

        for (int i = 1; i < months.length; i++) {
            String[] data = months[i].split(",");

            if (data[2].equals("true")) {
                totalSpending += Integer.parseInt(data[1]);
            } else {
                totalIncome += Integer.parseInt(data[1]);
            }
        }

        if (totalSpending > totalIncome) { //если расходы окажутся больше прибыли возвращаем 0 доход
            yearReport[0] = 0;
        } else {
            yearReport[0] = totalIncome - totalSpending;
        }

        yearReport[1] = totalSpending / 12; //все расходы делим на 12 месяцев

        yearReport[2] = totalIncome / 12; //так же и с доходами

        return yearReport;
    }
}
