
public class MinHeap {
     private int[] heap;
     private int size = 0;

/* Konstruktor tworzy tabelę z jednym dodatkowym miejscem, aby ułatwić obsługę sytuacji, gdy poszukiwane dziecko nie istnieje,
   w wyniku czego program wysypałby błąd podczas próby sięgnięcia po dane z nieistniejącego indeksu w tabeli*/
    public MinHeap(int quant){
        this.heap = new int[quant + 1];
        heap[quant] = -1;
    }

    public int getSize(){
        return size;
    }

    public int getLength(){
        return heap.length - 1;
    }

    public void add(int number){
        int position = size;
        size++;
        heap[position] = number;
        swapWithParent(position);
    }

    private int swapWithParent(int position){
        int parent;
        int temp;
        if (position !=0) {
            parent = (int) Math.ceil((double) position / 2) - 1;
        } else parent = 0;
        if (heap[position] >= heap[parent]) return 0;
        else {
            temp = heap[position];
            heap[position] = heap[parent];
            heap[parent] = temp;
            return swapWithParent(parent);
        }
    }

    public int extract(){
        int position = size - 1;
        int top = heap[0];
        heap[0] = heap[position];
        size--;
        swapWithChild(0);
        return top;
    }

    private int swapWithChild(int position){
        int lChild = position * 2 + 1;
        if (lChild > size) lChild = position;
        int rChild = position * 2 + 2;
        if (rChild > size) rChild = position;
        int child = lChild;
        if (heap[rChild] < heap[child]) child = rChild;
        if (heap[position] <= heap[child]) return 0;
        else{
            int temp = heap[position];
            heap[position] = heap[child];
            heap[child] = temp;
            return swapWithChild(child);
        }
    }

/* Metoda check służy do sprawdzania, czy liczba podana jako parametr już istnieje w danym MinHeapie. Dzięki temu zarówno
   podanie, jak i wylosowanie liczby już istniejącej w danym MinHeapie jest niemożliwe. Aby ograniczyć ilość zbędnych sprawdzeń
   zastosowałem strukturę stosu.*/
    public boolean check(int data){
        if (size == 0) return false;
        CustomStack toCheck = new CustomStack();
        toCheck.add(0);
        int position;
        while (toCheck.size > 0){
            position = toCheck.pop();
            if (heap[position] == data){
                return true;
            }
            int lChild = position * 2 + 1;
            if (lChild > size) lChild = getLength() - 1;
            int rChild = position * 2 + 2;
            if (rChild > size) rChild = getLength() - 1;
            if (heap[rChild] > 0 && heap[rChild] <= data) {
                toCheck.add(rChild);
            }
            if (heap[lChild] > 0 && heap[lChild] <= data) {
                toCheck.add(lChild);
            }
        }
        return false;
    }

/*Metoda do wyświetlania MinHeapa we w miarę czytelny sposób. Pozostawiłem go do ewentualnego sprawdzenia kodu. */
    public void print(){
        System.out.println("Heap:");
        int counter = 0;
        for(int level = 0; counter < size; level++){
            for (int i = 0; i < Math.pow(2, level); i++){
                if (counter < size) {
                    System.out.print(heap[counter] + " ");
                    counter++;
                }
            }
            System.out.println();
        }
    }
}
