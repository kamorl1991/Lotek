import java.util.Scanner;

/* Oto moja implementacja gry losowej. Do przechowywania i utrzymywania liczb zarówno wprowadzonych, jak i wylosowanych postanowiłem
   zastosować strukturę danych MinHeap - co prawda wymaga ona trochę pokodzenia, ale w porównaniu ze zwykłą tablicą posortowaną
   po zakończeniu wprowadzania powinna ona być wydajniejsza (ilość pięter do ewentualnego pokonania przez właśnie dodawany element
   do MinHeapu jest mniejsza niż ilość porównań, jaką trzeba wykonać przy jednym przeskanowaniu całej tabeli
   podczas sortowania). Poza tym samo ćwiczenie potraktowałem jako ćwiczenie świeżo przyswojonych struktur danych. */

public class Main {
    static final int CEIL = 99;

/* Metoda do sprawdzania liczby podanej przez użytkownika i wychwytywania ewentualnych błędów.
   Napisałem ją oddzielnie, ponieważ nie znam sposobu, by zapętlenie błędu typu danych aż do podania liczby dało się napisać inaczej. */

    static int numberToGuess(MinHeap heap){
        Scanner scanner = new Scanner(System.in);
        try {
            int typ = scanner.nextInt();
            while (typ < 1 || typ > CEIL) {
                System.out.print("Nieprawidłowy zakres podanej liczby! Spróbuj jeszcze raz: ");
                typ = scanner.nextInt();
            }
            while (heap.check(typ)){
                System.out.print("Podałeś już tę liczbę! Spróbuj jeszcze raz: ");
                typ = scanner.nextInt();
            }
            return typ;
        }
        catch (Exception e){
            System.out.print("Podałeś typ danych inny niż liczbę! Spróbuj jeszcze raz: ");
            return numberToGuess(heap);
        }
    }
/* Metoda utworzona, by obsługiwać cały proces pojedynczej "partii". Nie umieszczałem tego kodu w mainie, ponieważ dzięki
   metodzie można wygodnie zaimplementować pytanie o powtórzenie gry.*/
    static void gra(){
        MinHeap wybrane = new MinHeap(6);
        MinHeap wylosowane = new MinHeap(6);
        int typ;
        int los;

        System.out.println("Najpierw wytypuj swoje liczby. Możesz je wybrać z zakresu między 1 a " + CEIL + ".");
        for (int i = 0; i < wybrane.getLength(); i++){
            System.out.print("Liczba nr " + (i + 1) +": ");
            typ = numberToGuess(wybrane);
            wybrane.add(typ);
        }

        System.out.println("Wszystkie liczby wytypowane! Pora teraz na losowanie.");
        System.out.print("Oto wyniki: ");
        for (int i = 0; i < wylosowane.getLength(); i++){
            do {
                los = (int) (Math.floor(Math.random()*CEIL)+1);
            } while (wylosowane.check(los));
            wylosowane.add(los);
            System.out.print(los + " ");
        }
        System.out.println();

        System.out.println("Wszystkie liczby wylosowane! Pora na sprawdzenie wyników.");
        int wybrComp = wybrane.extract();
        int wyloComp = wylosowane.extract();
        int counter = 0;
        while (wybrane.getSize() > 0 && wylosowane.getSize() > 0){
            if (wybrComp == wyloComp){
                if (counter == 0){
                    System.out.print("Trafione liczby: ");
                }
                System.out.print(wybrComp + " ");
                counter++;
                wybrComp = wybrane.extract();
                wyloComp = wylosowane.extract();
            }
            else if (wybrComp > wyloComp){
                wyloComp = wylosowane.extract();
            }
            else wybrComp = wybrane.extract();
        }
        if (wybrComp == wyloComp){
            if (counter == 0){
                System.out.print("Trafione liczby: ");
            }
            System.out.print(wybrComp + " ");
            counter++;
        }
        System.out.println();

        if (counter == wybrane.getSize()){
            System.out.println("Gratulacje! Udało Ci się trafić wszystkie liczby.");
        }
        else if (counter > 0) System.out.println("Ilość trafionych liczb: " + counter + ". Nieźle!");
        else{
            System.out.println("Niestety tym razem zabrakło szczęścia...");
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String odp;
        System.out.println("Witaj w minigrze Lotek! Do wygrania jest mnóstwo pieniędzy!");
        gra();
        do {
            System.out.print("Czy chcesz zagrać jeszcze raz? (tak/nie) ");
            odp = scanner.nextLine();
            while (!odp.equals("tak") && !odp.equals("nie")) {
                System.out.print("Nie rozumiem... Spróbuj odpowiedzieć jeszcze raz: ");
                odp = scanner.nextLine();
            }
            if (odp.equals("tak")) gra();
        } while (odp.equals("tak"));
        System.out.println("Dzięki za grę, życzymy miłego dnia!");

    }
}
