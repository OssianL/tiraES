package utils;

public class DynArray<E> {

	private static final int DEFAULT_SIZE = 100000;
	
	private Object[] array;
	private int size = DEFAULT_SIZE;
	private int lastElement = 0;
	private int elementCount = 0;
	private int removed = 0;
	
	public DynArray() {
		this(DEFAULT_SIZE);
	}
	
	public DynArray(int initialCapacity) {
		array = new Object[initialCapacity];
		size = initialCapacity;
	}
	
	public boolean add(E element) {
		if(lastElement >= size) {
			return false;
		}
		array[lastElement] = element;
		lastElement++;
		return true;
	}
	
	public boolean add(int index, E element) {
		if(size > index) {
			array[index] = element;
			if(lastElement < index) {
				lastElement = index + 1;
			}
			return true;
		}
		return false;
	}
	
	public E get(int i) {
		return (E) array[i];
	}
	
	public void remove(int i) {
		array[i] = null;
		removed++;
	}
	
	public void defragmentate() {
		int newSize = elementCount * 2;
		Object[] newArray = new Object[newSize];
		int counter = 0;
		for(int i = 0; i < size; i++) {
			if(array[i] != null) {
				newArray[counter] = array[i];
				counter++;
			}
		}
		lastElement = counter;
		size = newSize;
		removed = 0;
	}
	
	private void grow() {
		
	}
	
	private void grow(int i) {
		
	}
	
	public Object[] getArray() {
		return array;
	}
	
	public void setArray(Object[] newArray) {
		this.array = newArray;
		this.size = newArray.length;
	}
	
	public int getElementCount() {
		return elementCount;
	}
	
	public int size() {
		return size;
	}
}
