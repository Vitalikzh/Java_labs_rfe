package bsu.rfe.java.group7.lab1.Zhelobkov.varC3;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MainClass {
    public static void main(String[] args) throws  IllegalAccessException, InvocationTargetException{

        Food[] breakfast = new Food[20];

        int cheeseN = 0, dessertN1 = 0, dessertN2 = 0, dessertN3 = 0, dessertN4 = 0,appleN1 = 0, appleN2 = 0, appleN3 = 0;
        Apple apple1=new Apple("big");
        Apple apple2=new Apple("middle");
        Apple apple3=new Apple("small");
        Dessert dessert1=new Dessert( "bagel", "cookie");
        Dessert dessert2=new Dessert( "bagel", "donut");
        Dessert dessert3=new Dessert( "cake", "cookie");
        Dessert dessert4=new Dessert( "cake", "donut");
        Cheese cheese=new Cheese();

        boolean logicSort = false;
        boolean logicCalories = false;
        ArrayList<Integer> cheeseArray = new ArrayList<>();
        ArrayList<Integer> appleArray = new ArrayList<>();
        ArrayList<Integer> dessertArray = new ArrayList<>();

        int iter = 0;
        int itemsSoFar = 0;
        for (String arg: args) {
            String[] partsOfArguments = arg.split("/");
            if(partsOfArguments[0].equals("-sort"))
            {
                logicSort = true;
                continue;
            }
            if(partsOfArguments[0].equals("-calories"))
            {
                logicCalories = true;
                continue;
            }

            String[] parts = arg.split("/");
            if (parts[0].equals("Cheese")) {
                breakfast[itemsSoFar] = new Cheese();
                cheeseArray.add(itemsSoFar);
            }
            if (parts[0].equals("Apple")) {
                breakfast[itemsSoFar] = new Apple(parts[1]);
                appleArray.add(itemsSoFar);
            }
            if (parts[0].equals("Dessert")) {
                breakfast[itemsSoFar] = new Dessert(parts[1],parts[2]);
                dessertArray.add(itemsSoFar);
            }
            itemsSoFar++;
            try {
                Class myClass = Class.forName("bsu.rfe.java.group7.lab1." + partsOfArguments[0]);

                if (partsOfArguments.length==1) {

                    Constructor constructor = myClass.getConstructor();
                    breakfast[iter] = (Food)constructor.newInstance();
                } else
                if (partsOfArguments.length==2) {

                    Constructor constructor = myClass.getConstructor(String.class);
                    breakfast[iter] = (Food)constructor.newInstance(partsOfArguments[1]);
                }
                if (partsOfArguments.length==3) {

                    Constructor constructor = myClass.getConstructor(String.class, String.class);
                    breakfast[iter] = (Food)constructor.newInstance(partsOfArguments[1],partsOfArguments[2] );
                }
            }catch (ClassNotFoundException e)
            {
                System.out.println("("+iter+")This product can't be eaten");
                continue;
            }
            catch (NoSuchMethodException e)
            {
                System.out.println("There are no some method");
                continue;
            }
            catch (InstantiationException e)
                {
                    continue;
            }

            breakfast[iter].consume();
            ++iter;
        }
        System.out.println("---------------------------------");
        if(logicCalories)
        {
            System.out.println("AMOUNT OF CALORIES: " + calculateCaloriesOfBreakfast(breakfast));
        }
        if(logicSort)
        {
            System.out.println("SORT BREAKFAST:");
            Arrays.sort(breakfast, new Comparator() {
                public int compare(Object f1, Object f2) {
                    if (f1==null) return 1;
                    if (f2==null) return -1;
                    return ((Food)f2).amountOfParams > (((Food)f1).amountOfParams) ? 1: -1;
                }
            });
            printMyBreakfast(breakfast);

        }
        for (int i = 0; i < 20; i++) {
            if (breakfast[i] != null) {
                if (breakfast[i].equals(apple1)) {
                    appleN1++; }
                else if (breakfast[i].equals(apple2)) {
                    appleN2++; }
                else if (breakfast[i].equals(apple3)) {
                    appleN3++; }
                else if (breakfast[i].equals(cheese)) {
                    cheeseN++; }
                else if (breakfast[i].equals(dessert1)) {
                    dessertN1++; }
                else if (breakfast[i].equals(dessert2)) {
                    dessertN2++; }
                else if (breakfast[i].equals(dessert3)) {
                    dessertN3++; }
                else if (breakfast[i].equals(dessert4)) {
                    dessertN4++; }
            } else break;
        }
        System.out.println("eaten for breakfast:");
        System.out.println("small Apple: " + appleN3 );
        System.out.println("middle Apple: " + appleN2);
        System.out.println("big Apple: " + appleN1);
        System.out.println("Cheeses:" + cheeseN);
        System.out.println("Dessert: bagel, cookie: " + dessertN1);
        System.out.println("Dessert: bagel, donut: " + dessertN2);
        System.out.println("Dessert: cake, cookie: " + dessertN3);
        System.out.println("Dessert: cake, donut: " + dessertN4);

    }

    public static void printMyBreakfast(Food[] breakfast)
    {
        for (Food obj: breakfast)
        {
            if(obj == null)
            {
                break;
            }
            obj.consume();

        }
    }

    public static int calculateCaloriesOfBreakfast(Food[] breakfast)
    {
        int totalCalories = 0;
        for(Food obj: breakfast)
        {
            if(obj == null)
            {
                break;
            }
            totalCalories+= obj.calculateCalories();
        }
        return totalCalories;
    }

}



