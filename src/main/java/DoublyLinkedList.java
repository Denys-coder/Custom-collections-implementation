public class DoublyLinkedList extends AbstractLinkedList
{
    // node class
    public class DoublyLinkedListNode extends AbstractNode
    {
        // constructor
        public DoublyLinkedListNode(Object item)
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
            setHead(new DoublyLinkedList.DoublyLinkedListNode(value));
            setSize(1);
        }
        else
        {
            AbstractNode currentNode = getHead();
            while (currentNode.getNext() != null)
            {
                currentNode = currentNode.getNext();
            }
            AbstractNode newNode = new DoublyLinkedListNode(value);
            currentNode.setNext(newNode);
            newNode.setPrevious(currentNode);
        }
        setSize(getSize() + 1);
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
        
        AbstractNode newNode = new DoublyLinkedListNode(value);
        
        if (indexToInsert == 0)
        {
            getHead().setPrevious(newNode);
            newNode.setNext(getHead());
            setHead(newNode);
            setSize(getSize() + 1);
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
            temp.getNext().setPrevious(getHead());
            
            temp.setPrevious(null);
            temp.setNext(getHead());
            setHead(temp);
            
            return;
        }
        
        AbstractNode currentNode = getHead().getNext();
        for (int currentIndex = 1; ; currentIndex++)
        {
            if (currentIndex == firstIndex)
            {
                AbstractNode first = currentNode.getPrevious();
                AbstractNode second = currentNode;
                AbstractNode third = currentNode.getNext();
                AbstractNode fourth = currentNode.getNext().getNext();
                
                first.setNext(third);
                third.setPrevious(first);
                third.setNext(second);
                second.setPrevious(third);
                second.setNext(fourth);
                fourth.setPrevious(second);
                
                return;
            }
            currentNode = currentNode.getNext();
        }
    }
    
    @Override
    public void addLinkedList(AbstractLinkedList secondList)
    {
        AbstractNode currentListLastNode = getHead();
        AbstractNode secondListLastNode = secondList.getHead();
        
        if (getHead() == null)
        {
            setHead(secondListLastNode);
            currentListLastNode = getHead();
            secondListLastNode = secondListLastNode.getNext();
        }
        
        while (currentListLastNode.getNext() != null)
        {
            currentListLastNode = currentListLastNode.getNext();
        }
        
        while (secondListLastNode != null)
        {
            AbstractNode newNode = new DoublyLinkedList.DoublyLinkedListNode(secondListLastNode.getItem());
            currentListLastNode.setNext(newNode);
            newNode.setPrevious(currentListLastNode);
            secondListLastNode = secondListLastNode.getNext();
            secondListLastNode = secondListLastNode.getNext();
            setSize(getSize() + 1);
        }
    }
    
    public AbstractLinkedList getCircularLinkedList()
    {
        AbstractLinkedList circularLinkedList = new CircularLinkedList();
        circularLinkedList.addLinkedList(this);
        return circularLinkedList;
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
        for (int i = 1; true; i++)
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