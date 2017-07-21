package chained_list;

public class Chain<T extends Comparable<T>> {
	
	private ChainItem<T> head = null;
	
	public void add(T item) {
		if (head==null){
			head= new ChainItem<T>(item);
			return;
		}
		Chain<T>.ChainItem<T> ptr = this.getHead();
		Chain<T>.ChainItem<T> previousPtr= this.getHead();
		while (ptr != null) {
			previousPtr = ptr;
			ptr = ptr.getNext();
		}
		previousPtr.setNext(new ChainItem<T>(item));
		
	}
	
	public void add(T item, int index) throws IndexOutOfBoundsException {
		if (this.head == null){
			return;
		}
		ChainItem<T> addedItem = new ChainItem<T>(item);
		if (index == 0){
			addedItem.setNext(this.head);
			this.head = addedItem;
			return;
		}
		
		Chain<T>.ChainItem<T> ptr = this.getHead();
		Chain<T>.ChainItem<T> previousPtr= this.getHead();
		int i = 0;
		while (i < index && ptr != null){
			
			previousPtr = ptr;
			ptr = ptr.getNext();
			i++;
		}
		if (i < index){
			throw new IndexOutOfBoundsException();
		}
		
		previousPtr.setNext(addedItem);
		addedItem.setNext(ptr);
		
		
	}
	
	public void remove(int index) throws IndexOutOfBoundsException {
		if (this.head == null){
			return;
		}
		if (index == 0){
			this.head = this.head.getNext();
			return;
		}
		
		Chain<T>.ChainItem<T> ptr = this.getHead();
		Chain<T>.ChainItem<T> previousPtr= this.getHead();
		int i = 0;
		while (i < index && ptr != null){
			
			previousPtr = ptr;
			ptr = ptr.getNext();
			i++;
		}
		if (i < index){
			throw new IndexOutOfBoundsException();
		}
		previousPtr.setNext(ptr.getNext());
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void remove(T element) {
		if (this.head == null || element == null){
			return;
		}
		
		Chain<T>.ChainItem<T> ptr = this.getHead();
		Chain<T>.ChainItem<T> previousPtr= this.getHead();
		while (ptr != null && !(ptr.getData().equals(element))){
			previousPtr = ptr;
			ptr = ptr.getNext();
		}
		if (ptr == null){
			return;
		}
		previousPtr.setNext(ptr.getNext());
		
	}
	
	public int getSize() {
		int size = 0;
		Chain<T>.ChainItem<T> ptr = this.getHead();
		
		while (ptr != null) {
			size++;
			ptr = ptr.getNext();
		}
		return size;
	}
	
	public ChainItem<T> getHead() { return this.head; }
	
	public void sortAscending(){
		if (this.head == null){
			return;
		}
		while(!this.isAscending()){
			Chain<T>.ChainItem<T> ptr = this.getHead();
			Chain<T>.ChainItem<T> previousPtr= this.getHead();
			while (ptr != null){
				
				if(previousPtr.getData().compareTo(ptr.getData()) > 0){
					ChainItem<T> temp = new ChainItem(previousPtr.getData());
					previousPtr.setData(ptr.getData());
					ptr.setData(temp.getData());
				}
				previousPtr = ptr;
				ptr = ptr.getNext();
			}
		}
	}
	
	public void sortDescending(){
		if (this.head == null){
			return;
		}
		while(!this.isDescending()){
			Chain<T>.ChainItem<T> ptr = this.getHead();
			Chain<T>.ChainItem<T> previousPtr= this.getHead();
			while (ptr != null){
				
				if(previousPtr.getData().compareTo(ptr.getData()) < 0){
					ChainItem<T> temp = new ChainItem(previousPtr.getData());
					previousPtr.setData(ptr.getData());
					ptr.setData(temp.getData());
				}
				previousPtr = ptr;
				ptr = ptr.getNext();
			}
		}
	}
	
	public boolean isAscending(){
		if (this.head == null){
			return false;
		}
		
		Chain<T>.ChainItem<T> ptr = this.getHead();
		Chain<T>.ChainItem<T> previousPtr= this.getHead();
		while (ptr != null){
			
			if(previousPtr.getData().compareTo(ptr.getData()) > 0){
				return false;
			}
			previousPtr = ptr;
			ptr = ptr.getNext();
		}
		return true;
	}
	
	public boolean isDescending(){
		if (this.head == null){
			return false;
		}
		
		Chain<T>.ChainItem<T> ptr = this.getHead();
		Chain<T>.ChainItem<T> previousPtr= this.getHead();
		while (ptr != null){
			
			if(previousPtr.getData().compareTo(ptr.getData()) < 0){
				return false;
			}
			previousPtr = ptr;
			ptr = ptr.getNext();
		}
		return true;
	}
	
	
	public class ChainItem<T extends Comparable<T>> {
		private T data;
		private ChainItem<T> next = null;
		
		public ChainItem(T arg) {
			this.data = arg;
		}
		
		public ChainItem(T arg, ChainItem<T> next) {
			this(arg);
			this.next = next;
		}
		
		public T getData() { return this.data; }
		
		public ChainItem<T> getNext() { return this.next; }
		
		public void setNext(ChainItem<T> next) { this.next = next; }
		
		public void setData(T arg){
			this.data = arg;
		}
	}
}
