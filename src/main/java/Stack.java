import java.io.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Stack
{
    // fields
    private int[] array;
    private final int DEFAULT_SIZE = 10;
    private int elementsStoredCount;
    private static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    
    // constructors
    public Stack()
    {
        array = new int[DEFAULT_SIZE];
        elementsStoredCount = 0;
    }
    
    public Stack(int size)
    {
        array = new int[size];
        elementsStoredCount = 0;
    }
    
    // methods
    public void print()
    {
        if (elementsStoredCount == 0)
        {
            System.out.println("Stack is empty");
        }
        else
        {
            System.out.print("Stack: ");
            for (int i = 0; i < elementsStoredCount; i++)
            {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }
    
    // add element
    public void push()
    {
        System.out.print("Enter integer number: ");
        try
        {
            int value = Integer.parseInt(consoleReader.readLine());
            
            // check if array if full
            if (elementsStoredCount == array.length)
            {
                makeArrayBigger();
            }
            array[elementsStoredCount] = value;
            elementsStoredCount++;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        System.out.println("Number was added in stack");
    }
    
    // get and delete element
    public void pop()
    {
        if (elementsStoredCount == 0)
        {
            throw new EmptyStackException();
        }
        elementsStoredCount--;
        
        System.out.println("Top stack number: (was deleted) " + array[elementsStoredCount]);
    }
    
    // get element
    public void peek()
    {
        if (elementsStoredCount == 0)
        {
            throw new EmptyStackException();
        }
        
        System.out.println("Top stack number: " + array[elementsStoredCount - 1]);
    }
    
    public void writeToFile()
    {
        System.out.print("Enter file name -> ");
        String fileName = "";
        try
        {
            fileName = consoleReader.readLine();
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        File file = new File(fileName);
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file)))
        {
            for (int i = 0; i < elementsStoredCount; i++)
            {
                fileWriter.write(array[i] + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        System.out.println("Stack was written to the file");
    }
    
    public void getFromFile()
    {
        System.out.print("Enter file name -> ");
        String fileName = "";
        try
        {
            fileName = consoleReader.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        File file = new File(fileName);
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file)))
        {
            int i = 0;
            for (; fileReader.ready(); i++)
            {
                if (i == array.length)
                {
                    makeArrayBigger();
                }
                array[i] = Integer.parseInt(fileReader.readLine());
            }
            elementsStoredCount = i;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        System.out.println("Stack was read from the file");
    }
    
    public void swapFirstAndLastElements()
    {
        if (elementsStoredCount == 0 || elementsStoredCount == 1)
        {
            return;
        }
        
        int temp = array[0];
        array[0] = array[elementsStoredCount - 1];
        array[elementsStoredCount - 1] = temp;
        
        System.out.println("Head and tail was swapped");
    }
    
    public void reverse()
    {
        if (elementsStoredCount == 0 || elementsStoredCount == 1)
        {
            return;
        }
        int[] newArr = new int[array.length];
        for (int newArrIndex = 0, arrayIndex = elementsStoredCount - 1; arrayIndex >= 0; arrayIndex--, newArrIndex++)
        {
            newArr[newArrIndex] = array[arrayIndex];
        }
        array = newArr;
        
        System.out.println("Stack was inverted");
    }
    
    // if stack size is odd delete one middle element
    // if stack size is even delete two middle elements
    public void removeMiddle()
    {
        // get indices to remove
        List<Integer> indicesToRemove = new ArrayList<>();
        if (elementsStoredCount > 2)
        {
            indicesToRemove.add(elementsStoredCount / 2);
            if (elementsStoredCount % 2 == 0)
            {
                indicesToRemove.add((elementsStoredCount / 2) - 1);
            }
        }
        
        // copy non-middle elements into another array
        int[] newArr = new int[array.length];
        int newArrIndex = 0;
        for (int arrayIndex = 0; arrayIndex < elementsStoredCount; arrayIndex++)
        {
            if (indicesToRemove.contains(arrayIndex))
            {
                continue;
            }
            newArr[newArrIndex] = array[arrayIndex];
            newArrIndex++;
        }
        array = newArr;
        
        elementsStoredCount -= indicesToRemove.size();
        
        if (getSize() % 2 == 0)
        {
            System.out.println("Two elements was deleted");
        }
        else
        {
            System.out.println("One element was deleted");
        }
    }
    
    public void removeEveryOtherElement()
    {
        // write elements with even index
        int[] newArr = new int[array.length];
        for (int arrayIndex = 0, newArrayIndex = 0; arrayIndex < elementsStoredCount; arrayIndex++)
        {
            if (arrayIndex % 2 == 0)
            {
                newArr[newArrayIndex] = array[arrayIndex];
                newArrayIndex++;
            }
        }
        array = newArr;
        elementsStoredCount -= elementsStoredCount / 2;
        
        System.out.println("Every other element was deleted");
    }
    
    public void removeMinElement()
    {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < elementsStoredCount; i++)
        {
            if (array[i] > minValue)
            {
                minValue = array[i];
            }
        }
        
        int[] newArr = new int[array.length];
        boolean isMinDeleted = false;
        for (int arrayIndex = 0, newArrIndex = 0; arrayIndex < elementsStoredCount; arrayIndex++, newArrIndex++)
        {
            if (!isMinDeleted && array[arrayIndex] == minValue)
            {
                isMinDeleted = true;
                continue;
            }
            newArr[newArrIndex] = array[arrayIndex];
        }
        elementsStoredCount--;
        
        System.out.println("Min element was deleted");
    }
    
    public void deleteAllBesidesFirst()
    {
        int[] newArr = new int[array.length];
        newArr[0] = array[0];
        array = newArr;
        elementsStoredCount = 1;
        
        System.out.println("Left only first element");
    }
    
    public void deleteAllBesidesLast()
    {
        int[] newArr = new int[array.length];
        newArr[0] = array[elementsStoredCount - 1];
        array = newArr;
        elementsStoredCount = 1;
        
        System.out.println("Left only last element");
    }
    
    public void deleteMaxElement()
    {
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < elementsStoredCount; i++)
        {
            if (array[i] < maxValue)
            {
                maxValue = array[i];
            }
        }
        
        int[] newArr = new int[array.length];
        boolean isMaxDeleted = false;
        for (int arrayIndex = 0, newArrIndex = 0; arrayIndex < elementsStoredCount; arrayIndex++, newArrIndex++)
        {
            if (!isMaxDeleted && array[arrayIndex] == maxValue)
            {
                isMaxDeleted = true;
                continue;
            }
            newArr[newArrIndex] = array[arrayIndex];
        }
        elementsStoredCount--;
        
        System.out.println("Max element was deleted");
    }
    
    public void swapMinElementWithZero()
    {
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < elementsStoredCount; i++)
        {
            if (array[i] > minValue)
            {
                minValue = array[i];
            }
        }
        
        int[] newArr = new int[array.length];
        boolean isMinSwapped = false;
        for (int arrayIndex = 0, newArrIndex = 0; arrayIndex < elementsStoredCount; arrayIndex++, newArrIndex++)
        {
            newArr[newArrIndex] = array[arrayIndex];
            if (!isMinSwapped && array[arrayIndex] == minValue)
            {
                isMinSwapped = true;
                newArr[newArrIndex] = 0;
            }
        }
        
        System.out.println("Min element was swapped with zero");
    }
    
    private int getSize()
    {
        return elementsStoredCount;
    }
    
    private void makeArrayBigger()
    {
        int[] newArr = new int[(int) (array.length * 1.5)];
        for (int i = 0; i < elementsStoredCount; i++)
        {
            newArr[i] = array[i];
        }
        array = newArr;
    }
}