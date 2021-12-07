package bsu.rfe.java.group7.lab3.Zhelobkov.varB5;

import javax.swing.table.AbstractTableModel;
@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
// В данной модели два столбца
        return 3;
    }
    public Double[] getcoefficients(){
        return coefficients;
    }

    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return  Double.valueOf(Math.ceil((to-from)/step)).intValue()+1;
    }
    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step*row;


        double result = 0;
        int i = coefficients.length - 1;
        result = coefficients[i--];
        while (i >= 0) {
            result = result * x + coefficients[i--];
        }
        // проверка на простое
        boolean f = true;
        int temp;
        int prost = (int) result;

        if (col==0) {
// Если запрашивается значение 1-го столбца, то это X
            return x;
        } else if (col==1){
// Если запрашивается значение 2-го столбца, то это значение
// многочлена
// Вычисление значения в точке по схеме Горнера.
// Вспомнить 1-ый курс и реализовать
// ...
            return result;
        }else if(col == 2){
            for (int m=2; m<=prost/2; m++) {
                temp = prost % m;
                if (temp == 0) {
                   f = false;
                    break;
                } else f = true;

            }
            if(f == false){
                return false;
            }else return true;
        }else return 0;

    }
    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
// Название 2-го столбца
                return "Простое число?";
        }
    }
    public Class<?> getColumnClass(int col) {
// И в 1-ом и во 2-ом столбце находятся значения типа Double
        if(col == 1 || col == 0){
            return Double.class;
        }else return Boolean.class;

    }
}

