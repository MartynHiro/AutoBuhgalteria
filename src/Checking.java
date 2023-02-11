public class Checking {

    protected static boolean compareMonthsYear(String[] yearLines, String[] monthLines, String yearName, String monthName) {
        int monthExpenses = 0;  //общий расход месяца
        int monthIncome = 0;    //общий доход месяца
        int yearExpenses = 0;   //общий годовой расход
        int yearIncome = 0;     //общий годовой доход
        boolean isMatch = false;//совпадает ли сверка отчётов

        String yearNumber = yearName.substring(2, 5); //вырезаем номер года этого отчёта
        String monthNumber = monthName.substring(2,5);

        if (yearNumber.equals(monthNumber)) {  //если год отчётов совпадает, то сравниваем их
            for (int i = 1; i < monthLines.length; i++) {       //в нулевой строке названия столбцов
                String[] data = monthLines[i].split(",");

                if (data[1].equals("true")) { //в 1-м элементе массива у нас обозначение расход или доход
                    monthExpenses += Integer.parseInt(data[2]) * Integer.parseInt(data[3]); //2-й элемент массива это кол-во товара
                } else {                                                                    //3-тий это стоимость за штуку
                    monthIncome += Integer.parseInt(data[2]) * Integer.parseInt(data[3]);
                }
            }

            for (int i = 1; i < yearLines.length; i++) {
                String[] data = yearLines[i].split(",");

                if (data[0].equals(monthName.substring(6,7))) { //в годовом в 0 ячейке указан номер месяца, если совпадает, то сравниваем
                    if (data[2].equals("true")) {   //если это расход, то достаем число
                        yearExpenses += Integer.parseInt(data[1]);
                    } else {                        //если доход
                        yearIncome += Integer.parseInt(data[1]);
                    }
                }
            }
            //сравниваем доходы/расходы года и месяца
            if (monthExpenses == yearExpenses && monthIncome == yearIncome) {  //если все доходы и расходы совпадают
                isMatch = true;
            }
        } else {
            System.out.println("Номер года в отчётах не совпадает, пожалуйста предоставьте другие данные");
        }
        return isMatch;
    }
}