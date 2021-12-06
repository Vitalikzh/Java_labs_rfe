package bsu.rfe.java.group7.lab1.Zhelobkov.varC3;

public abstract class Food implements INutritious, IConsumable{
    int calories = 0;
    int amountOfParams = 0;
    private String name;

    public String getName() {
        return name;
    }

    public Food(String name) {
        this.name = name;
    }

    public boolean equals(Object arg0)         // перегружен метод сравнения
    {
        if (!(arg0 instanceof Food)) return false;
        if (name == null || ((Food) arg0).name == null) return false;
        return name.equals(((Food) arg0).name);
    }

}