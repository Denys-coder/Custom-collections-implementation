public class CircularLinkedList extends AbstractLinkedList
{
    // node class
    public class CircularLinkedListNode extends AbstractNode
    {
        public CircularLinkedListNode(Object item)
        {
            super(item);
        }
    }
    
    @Override
    public void clear()
    {
        // DoublyLinkedListNode objects will be deleted by GC
        setHead(null);
        setSize(0);
    }
    
    @Override
    public void add(Object value)
    {
        if (getHead() == null)
        {
            setHead(new CircularLinkedList.CircularLinkedListNode(value));
            setSize(1);
        }
        else
        {
            AbstractNode currentNode = getHead();
            while (currentNode.getNext() != null)
            {
                currentNode = currentNode.getNext();
            }
            AbstractNode newNode = new CircularLinkedList.CircularLinkedListNode(value);
            currentNode.setNext(newNode);
            newNode.setPrevious(currentNode);
        }
        setSize(getSize() + 1);
        updateCircle();
    }
    
    @Override
    public Object get(int index)
    {
        if (index < 0 || index > getSize() - 1)
        {
            throw new IndexOutOfBoundsException();
        }
        
        AbstractNode currentNode = getHead();
        
        if (index == 0)
        {
            return currentNode.getItem();
        }
        
        for (int i = 1; ; i++)
        {
            currentNode = currentNode.getNext();
            if (i == index)
            {
                return currentNode.getItem();
            }
        }
    }
    
    @Override
    public void insert(int indexToInsert, Object value)
    {
        if (indexToInsert < 0 || indexToInsert > getSize())
        {
            throw new IndexOutOfBoundsException();
        }
        
        AbstractNode newNode = new CircularLinkedListNode(value);
        
        if (indexToInsert == 0)
        {
            getHead().setPrevious(newNode);
            newNode.setNext(getHead());
            setHead(newNode);
            setSize(getSize() + 1);
            updateCircle();
            return;
        }
        
        AbstractNode currentNode = getHead();
        for (int currentIndex = 0; ; currentIndex++)
        {
            currentNode = currentNode.getNext();
            if (currentIndex == indexToInsert - 1)
            {
                newNode.setNext(currentNode.getNext());
                currentNode.getNext().setPrevious(newNode);
                currentNode.setNext(newNode);
                newNode.setPrevious(currentNode);
                setSize(getSize() + 1);
                updateCircle();
                return;
            }
        }
    }
    
    @Override
    public void set(int index, Object value)
    {
        if (index < 0 || index > getSize() - 1)
        {
            throw new IndexOutOfBoundsException();
        }
        
        AbstractNode currentNode = getHead();
        
        if (index == 0)
        {
            currentNode.setItem(value);
        }
        
        for (int i = 1; ; i++)
        {
            currentNode = currentNode.getNext();
            if (i == index)
            {
                currentNode.setItem(value);
                updateCircle();
                updateCircle();
                return;
            }
        }
    }
    
    @Override
    public void remove(int indexToRemove)
    {
        if (indexToRemove < 0 || indexToRemove > getSize() - 1)
        {
            throw new IndexOutOfBoundsException();
        }
        
        if (indexToRemove == 0)
        {
            setHead(getHead().getNext());
            getHead().setPrevious(null);
            setSize(getSize() - 1);
            updateCircle();
            return;
        }
        
        AbstractNode currentNode = getHead();
        for (int currentIndex = 0; ; currentIndex++)
        {
            currentNode = currentNode.getNext();
            if (currentIndex == indexToRemove)
            {
                setSize(getSize() - 1);
                currentNode.getPrevious().setNext(currentNode.getNext());
                currentNode.getNext().setPrevious(currentNode.getPrevious());
                updateCircle();
                return;
            }
        }
    }
    
    @Override
    public void swapElements(int firstIndex, int secondIndex)
    {
        if (secondIndex - firstIndex != 1 || firstIndex < 0 || secondIndex > getSize() - 1)
        {
            throw new IndexOutOfBoundsException();
        }
        
        if (firstIndex == 0)
        {
            AbstractNode temp = getHead().getNext();
            
            getHead().setNext(temp.getNext());
            getHead().setPrevious(null);
            temp.getNext().setPrevious(getHead());
            
            temp.setPrevious(null);
            temp.setNext(getHead());
            setHead(temp);
            
            updateCircle();
            return;
        }
    }
    
    @Override
    public void addLinkedList(AbstractLinkedList secondList)
    {
        AbstractNode currentNode = getHead();
        while (currentNode.getNext() != null)
        {
            currentNode = currentNode.getNext();
        }
        
        AbstractNode secondListCurrentNode = secondList.getHead();
        while (secondListCurrentNode != null)
        {
            AbstractNode newNode = new CircularLinkedList.CircularLinkedListNode(secondListCurrentNode.getItem());
            currentNode.setNext(newNode);
            newNode.setPrevious(currentNode);
            currentNode = currentNode.getNext();
            secondListCurrentNode = secondListCurrentNode.getNext();
            setSize(getSize() + 1);
        }
        
        updateCircle();
    }
    
    private void updateCircle()
    {
        AbstractNode lastNode = getHead();
        while (lastNode.getNext() != null)
        {
            lastNode = lastNode.getNext();
        }
        getHead().setPrevious(lastNode);
        lastNode.setNext(getHead());
    }
    
    @Override
    public void print()
    {
        if (getHead() == null)
        {
            System.out.println("Список пуст");
            return;
        }
        
        System.out.print("Список: ");
        AbstractNode currentNode = getHead();
        while (true)
        {
            System.out.print(currentNode.getItem() + " ");
            currentNode = currentNode.getNext();
            if (currentNode == null)
            {
                System.out.println();
                break;
            }
        }
    }
}