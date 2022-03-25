import java.io.*;

public class DeQueue
{
    // fields
    private int[] array;
    private final int DEFAULT_SIZE = 10;
    private int elementsStoredCount;
    private static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    
    // constructors
    public DeQueue()
    {
        array = new int[DEFAULT_SIZE];
        elementsStoredCount = 0;
    }
    
    public DeQueue(int size)
    {
        array = new int[size];
        elementsStoredCount = 0;
    }
    
    public void print()
    {
        if (elementsStoredCount == 0)
        {
            System.out.println("Dequeue is empty");
        }
        else
        
        {
            System.out.print("Dequeue: ");
            for (int i = 0; i < elementsStoredCount; i++)
            {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }
    
    public void clear()
    {
        elementsStoredCount = 0;
        array = new int[DEFAULT_SIZE];
    }
    
    public void printIfFull()
    {
        if (elementsStoredCount == 0)
        {
            System.out.println("Dequeue is empty");
        }
        else
        {
            System.out.println("Dequeue is not empty");
        }
    }
    
    public void pushIntoBeginning()
    {
        if (elementsStoredCount == array.length)
        {
            makeArrayBigger();
        }
        System.out.print("Enter number: ");
        try
        {
            int value = Integer.parseInt(consoleReader.readLine());
            int[] newArr = new int[array.length];
            for (int arrayIndex = 0, newArrIndex = 1; arrayIndex < elementsStoredCount; arrayIndex++, newArrIndex++)
            {
                newArr[newArrIndex] = array[arrayIndex];
            }
            newArr[0] = value;
            elementsStoredCount++;
            array = newArr;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }
    
    public void push()
    {
        if (elementsStoredCount == array.length)
        {
            makeArrayBigger();
        }
        System.out.print("Enter number: ");
        try
        {
            int value = Integer.parseInt(consoleReader.readLine());
            array[elementsStoredCount] = value;
            elementsStoredCount++;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
    }
    
    public void getFromBeginning()
    {
        if (elementsStoredCount == 0)
        {
            System.out.println("Dequeue is empty");
        }
        else
        {
            System.out.println("First Element: " + array[0]);
            int[] newArr = new int[array.length];
            for (int arrayIndex = 1, newArrIndex = 0; arrayIndex < elementsStoredCount; arrayIndex++, newArrIndex++)
            {
                newArr[newArrIndex] = array[arrayIndex];
            }
            array = newArr;
            elementsStoredCount--;
        }
    }
    
    public void get()
    {
        if (elementsStoredCount == 0)
        {
            System.out.println("Dequeue is empty");
        }
        else
        {
            System.out.println("Last element: " + array[elementsStoredCount - 1]);
            elementsStoredCount--;
        }
    }
    
    public void getLastElement()
    {
        if (elementsStoredCount == 0)
        {
            System.out.println("Dequeue is empty");
        }
        else
        {
            System.out.println("Last element: " + array[elementsStoredCount - 1]);
        }
    }
    
    public void writeToLastElement()
    {
        System.out.print("Enter number: (int) ");
        try
        {
            int value = Integer.parseInt(consoleReader.readLine());
            if (elementsStoredCount == 0)
            {
                array[0] = value;
            }
            else
            {
                array[elementsStoredCount - 1] = value;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }
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
            return;
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
            return;
        }
        
        System.out.println("Deque was written to the file");
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
            return;
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
            return;
        }
        
        System.out.println("Dequeue was read from the file");
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