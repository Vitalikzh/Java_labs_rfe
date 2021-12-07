package bsu.rfe.java.group7.lab3.Zhelobkov.varB5;

//Исходный код визуализатора ячеек

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class GornerTableCellRenderer implements TableCellRenderer {

    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    // Ищем ячейки, строковое представление которых совпадает с needle
// (иголкой). Применяется аналогия поиска иголки в стоге сена, в роли
// стога сена - таблица
    private String needle = null;
    private DecimalFormat formatter =
            (DecimalFormat)NumberFormat.getInstance();


    public GornerTableCellRenderer() {
// Показывать только 5 знаков после запятой
        formatter.setMaximumFractionDigits(5);
// Не использовать группировку (т.е. не отделять тысячи
// ни запятыми, ни пробелами), т.е. показывать число как "1000",
// а не "1 000" или "1,000"
        formatter.setGroupingUsed(false);
// Установить в качестве разделителя дробной части точку, а не
// запятую. По умолчанию, в региональных настройках
// Россия/Беларусь дробная часть отделяется запятой
        DecimalFormatSymbols dottedDouble =
                formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');

        formatter.setDecimalFormatSymbols(dottedDouble);
// Разместить надпись внутри панели
        panel.add(label);
// Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

    }


    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {
// Преобразовать double в строку с помощью форматировщика
        String formattedDouble = formatter.format(value);

// Установить текст надписи равным строковому представлению числа
        label.setText(formattedDouble);
        System.out.println(value);
        double num = Double.valueOf(formattedDouble);
        int f = (int) num;
        boolean t = false;
        num = num - f;

        for(int i = 0;i < 5; i++){
            num = num * 10;
            f = (int) num;
            num = num - f;
        if((f == 3) || (f == 1) || (f == 5)){
            t = true;
        }else {
            if(f == 0){
               double n = num * 10;
                int d = (int) n;
                if(d == 0){
                    break;
                }else {
                    t = false;
                    break;
                }
            }else {
                t = false;
                break;
            }

        }
        if(t == false)
            break;

    }



        if (((col==1) || (col ==0)) && needle!=null && needle.equals(formattedDouble)) {
// Номер столбца = 1 (т.е. второй столбец) + иголка не null
// (значит что-то ищем) +
// значение иголки совпадает со значением ячейки таблицы -
// окрасить задний фон панели в красный цвет
            panel.setBackground(Color.RED);
        } else if(t == true){
// Иначе - в обычный белый
            panel.setBackground(Color.GREEN);
            t = false;
        }else panel.setBackground(Color.white);
        return panel;

    }


    public void setNeedle(String needle) {
        this.needle = needle;
    }
}
