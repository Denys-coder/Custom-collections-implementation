public class SinglyLinkedList extends AbstractLinkedList
{
    // node class
    public class SinglyLinkedListNode extends AbstractNode
    {
        public SinglyLinkedListNode(Object item)
        {
            super(item);
        }
    }
    
    @Override
    public void clear()
    {
        // SinglyLinkedListNode objects will be deleted by GC
        setHead(null);
        
        setSize(0);
    }
    
    @Override
    public void add(Object value)
    {
        if (getHead() == null)
        {
            setHead(new SinglyLinkedListNode(value));
            setSize(1);
        }
        else
        {
            AbstractNode currentNode = getHead();
            while (currentNode.getNext() != null)
            {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(new SinglyLinkedListNode(value));
            setSize(getSize() + 1);
        }
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
        
        AbstractNode newNode = new SinglyLinkedListNode(value);
        if (indexToInsert == 0)
        {
            newNode.setNext(getHead());
            setHead(newNode);
            setSize(getSize() + 1);
            return;
        }
        
        SinglyLinkedListNode currentNode = (SinglyLinkedListNode) getHead();
        for (int currentIndex = 0; ; currentIndex++)
        {
            currentNode = (SinglyLinkedListNode) currentNode.getNext();
            if (currentIndex == indexToInsert - 1)
            {
                newNode.setNext(currentNode.getNext());
                currentNode.setNext(newNode);
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
            setSize(getSize() - 1);
            return;
        }
        
        AbstractNode currentNode = getHead();
        for (int currentIndex = 0; ; currentIndex++)
        {
            currentNode = currentNode.getNext();
            if (currentIndex == indexToRemove - 1)
            {
                currentNode.setNext(currentNode.getNext().getNext());
                setSize(getSize() - 1);
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
            AbstractNode temp = getHead();
            setHead(getHead().getNext());
            temp.setNext(getHead().getNext());
            getHead().setNext(temp);
            return;
        }
        
        AbstractNode currentNode = getHead();
        for (int currentIndex = 0; ; currentIndex++)
        {
            if (currentIndex == firstIndex - 1)
            {
                AbstractNode temp = currentNode.getNext();
                currentNode.setNext(temp.getNext());
                temp.setNext(currentNode.getNext().getNext());
                currentNode.getNext().setNext(temp);
                return;
            }
            currentNode = currentNode.getNext();
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
            AbstractNode newNode = new SinglyLinkedListNode(secondListCurrentNode.getItem());
            currentNode.setNext(newNode);
            currentNode = currentNode.getNext();
            secondListCurrentNode = secondListCurrentNode.getNext();
            setSize(getSize() + 1);
        }
    }
    
    public AbstractLinkedList getDoublyLinkedList()
    {
        AbstractLinkedList doublyLinkedList = new DoublyLinkedList();
        doublyLinkedList.addLinkedList(this);
        return doublyLinkedList;
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