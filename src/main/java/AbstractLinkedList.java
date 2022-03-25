public abstract class AbstractLinkedList
{
    // node class
    public abstract class AbstractNode
    {
        // fields
        private Object item;
        private AbstractNode next;
        private AbstractNode previous;
        
        // constructors
        public AbstractNode(Object item)
        {
            this.item = item;
        }
        
        // methods
        public Object getItem()
        {
            return item;
        }
        
        public void setItem(Object item)
        {
            this.item = item;
        }
        
        public AbstractNode getNext()
        {
            return next;
        }
        
        public void setNext(AbstractNode next)
        {
            this.next = next;
        }
        
        public AbstractNode getPrevious()
        {
            return previous;
        }
        
        public void setPrevious(AbstractNode previous)
        {
            this.previous = previous;
        }
    }
    
    // fields
    private AbstractNode head;
    private int size;
    
    // constructors
    public AbstractLinkedList()
    {
        size = 0;
    }
    
    // defined methods
    public AbstractNode getHead()
    {
        return head;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public void setSize(int size)
    {
        this.size = size;
    }
    
    public void setHead(AbstractNode head)
    {
        this.head = head;
    }
    
    // abstract methods
    public abstract void clear();
    
    public abstract void add(Object value);
    
    public abstract Object get(int index);
    
    public abstract void insert(int index, Object value);
    
    public abstract void set(int index, Object value);
    
    public abstract void remove(int index);
    
    public abstract void swapElements(int firstValue, int secondValue);
    
    public abstract void addLinkedList(AbstractLinkedList secondList);
    
    public abstract void print();
}