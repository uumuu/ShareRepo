/*
Array-based data strcuture used for text edit buffer
ALlowsuser to move cursor, insert and deleter characters.
WIll be stack-based.
*/

public class TextEditBuffer<Item> implements Iterable<Item>{
	
	private char[] stack;
	private int n;
	private int cursorPos;

	public TextEditBuffer(){
		stack = new char[2];
		n = 0;
		cursorPos = 0;
	}

	public boolean isEmpty(){
		return n == 0;
	}

	public void resize(int capacity){
		assert capacity >= n;
		char[] temp = new char[capacity];
		for(int i = 0; i < n; i++){
			temp[i] = stack[i];
		}
		stack = temp;
	}

	public void insert(Item item){
		if (n == stack.length){resize(stack.length * 2);}
		stack[n++] = item;
		cursorPos++;
	}

	public char delete(){
		if (isEmpty()){throw new NoSuchElementException("Underflow");}
		char c = stack[n-1];
		stack[n-1] = null;
		cursorPos--;
		n--;
		return c;
	}

	public void left(int k){
		try{
			cursorPos -= 1;	

		}catch(IndexOutOfBoundsException e){
			System.out.println("No such position, cannot move cursor.");
		}
	}

	public void right(int k){
		try{
			cursorPos += 1;

		}catch(IndexOutOfBoundsException e){
			System.out.println("No such position, cannot move cursor.");
		}
	}

	public int size(){return n;}

	public Iterator<Item> iterator(){
		return new reverseArrayIterator();
	}

	private class reverseArrayIterator<Item> implements Iterator<Item>{
		private int i;

		public reverseArrayIterator(){
			i = n-1;
		}
		public boolean hasNext(){return i < n;}
		public void remove(){throw new UnsupportedOperationException();}
		public Item next(){
			if(!hasNext()){throw new NoSuchElementException();}
			return (Item) stack[i--];
	}
}