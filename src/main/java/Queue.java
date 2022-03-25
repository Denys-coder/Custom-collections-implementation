import java.io.*;

public class Queue
{
    // fields
    private int[] array;
    private final int DEFAULT_SIZE = 10;
    private int elementsStoredCount;
    private static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    
    // constructors
    public Queue()
    {
        array = new int[DEFAULT_SIZE];
        elementsStoredCount = 0;
    }
    
    public Queue(int size)
    {
        array = new int[size];
        elementsStoredCount = 0;
    }
    
    public void print()
    {
        if (elementsStoredCount == 0)
        {
            System.out.println("Queue is empty");
        }
        else
        {
            System.out.print("Queue: ");
            for (int i = 0; i < elementsStoredCount; i++)
            {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }
    
    public void printSize()
    {
        System.out.println("Queue size: " + elementsStoredCount + " elements");
    }
    
    public void printArithmeticalMean()
    {
        if (elementsStoredCount == 0)
        {
            System.out.println("Queue is empty");
        }
        else
        {
            int sum = 0;
            for (int i = 0; i < elementsStoredCount; i++)
            {
                sum += array[i];
            }
            System.out.println("Arithmetic mean: " + sum / elementsStoredCount);
        }
    }
    
    public void printMinElement()
    {
        int minElement = Integer.MAX_VALUE;
        for (int i = 0; i < elementsStoredCount; i++)
        {
            if (array[i] < minElement)
            {
                minElement = array[i];
            }
        }
        System.out.println("Min element: " + minElement);
    }
    
    public void printMaxElement()
    {
        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < elementsStoredCount; i++)
        {
            if (array[i] > maxElement)
            {
                maxElement = array[i];
            }
        }
        System.out.println("Max element: " + maxElement);
    }
    
    public void printElementBeforeMin()
    {
        int minElement = Integer.MAX_VALUE;
        int minElementIndex = 0;
        for (int i = 0; i < elementsStoredCount; i++)
        {
            if (array[i] < minElement)
            {
                minElement = array[i];
                minElementIndex = i;
            }
        }
        System.out.println("Element before min: " + array[minElementIndex - 1]);
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
        
        System.out.println("Queue was written to the file");
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
        
        System.out.println("Queue was written from the file");
    }
    
    private void makeArrayBigger()
    {
        int[] newArr = new int[(int) (array.length * 1.5)];
        if (elementsStoredCount >= 0)
            System.arraycopy(array, 0, newArr, 0, elementsStoredCount);
        array = newArr;
    }
}