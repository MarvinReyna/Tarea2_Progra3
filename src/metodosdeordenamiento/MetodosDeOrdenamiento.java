/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metodosdeordenamiento;

/**
 *
 * @author gio10
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;

public class MetodosDeOrdenamiento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de elementos a ordenar: ");
        int n = scanner.nextInt();
        Object[] array = new Object[n]; // Usamos Object para permitir tanto números como letras
        
        // Pedir al usuario que ingrese los elementos
        System.out.println("Ingrese los elementos (numeros o letras):");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.next();
        }
        
        int opcion;
        do {
            System.out.println("\n=== Menu de Ordenamiento ===");
            System.out.println("1. Selection sort (seleccion)");
            System.out.println("2. Bubble sort (burbuja)");
            System.out.println("3. Insertion sort (insercion)");
            System.out.println("4. Merge sort (combinacion)");
            System.out.println("5. Quick sort (rapida)");
            System.out.println("6. Heap sort (monton)");
            System.out.println("7. Counting sort (conteo)");
            System.out.println("8. Radix sort (raiz)");
            System.out.println("9. Bucket sort (cubo)");
            System.out.println("10. Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = scanner.nextInt();
            
            switch(opcion) {
                case 1:
                    selectionSort(array);
                    System.out.println("Array ordenado usando el metodo de Selection Sort:");
                    printArray(array);
                    break;
                case 2:
                    bubbleSort(array);
                    System.out.println("Array ordenado usando el metodo de Bubble Sort:");
                    printArray(array);
                    break;
                case 3:
                    insertionSort(array);
                    System.out.println("Array ordenado usando el metodo de Insertion Sort:");
                    printArray(array);
                    break;
                case 4:
                    mergeSort(array, 0, array.length - 1);
                    System.out.println("Array ordenado usando el metodo de Merge Sort:");
                    printArray(array);
                    break;
                case 5:
                    quickSort(array, 0, array.length - 1);
                    System.out.println("Array ordenado usando el metodo de Quick Sort:");
                    printArray(array);
                    break;
                case 6:
                    heapSort(array);
                    System.out.println("Array ordenado usando el metodo de Heap Sort:");
                    printArray(array);
                    break;
                 case 7:
                    countingSort(array);
                    System.out.println("Array ordenado usando el metodo de Counting Sort:");
                    printArray(array);
                    break;
                case 8:
                    radixSort(array);
                    System.out.println("Array ordenado usando el metodo de Radix Sort:");
                    printArray(array);
                    break;
                case 9:
                    bucketSort(array);
                    System.out.println("Array ordenado usando el metodo de Bucket Sort:");
                    printArray(array);
                    break;
                case 10:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor, seleccione una opcion valida.");
            }
            
        } while(opcion != 10);
        
        scanner.close();
    }
    
    // Método de ordenamiento de selección
    public static void selectionSort(Object[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (array[j].toString().compareTo(array[minIndex].toString()) < 0) {
                    minIndex = j;
                }
            }
            // Swap
            Object temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
    
    // Método de ordenamiento de burbuja
    public static void bubbleSort(Object[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (array[j].toString().compareTo(array[j+1].toString()) > 0) {
                    // Swap
                    Object temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
    
    // Método de ordenamiento de inserción
    public static void insertionSort(Object[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            Object key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].toString().compareTo(key.toString()) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }
    
    // Método de ordenamiento Merge Sort
    public static void mergeSort(Object[] array, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(array, l, m);
            mergeSort(array, m + 1, r);
            merge(array, l, m, r);
        }
    }

    public static void merge(Object[] array, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        Object[] L = new Object[n1];
        Object[] R = new Object[n2];
        System.arraycopy(array, l, L, 0, n1);
        System.arraycopy(array, m + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].toString().compareTo(R[j].toString()) <= 0) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }
    
    // Método de ordenamiento Quick Sort
    public static void quickSort(Object[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    public static int partition(Object[] array, int low, int high) {
        Object pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j].toString().compareTo(pivot.toString()) <= 0) {
                i++;
                Object temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        Object temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
    
    // Método de ordenamiento Heap Sort
    public static void heapSort(Object[] array) {
        int n = array.length;
        // Construir el heap (reorganizar el array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Extraer elementos del heap uno por uno
        for (int i = n - 1; i >= 0; i--) {
            // Mover la raíz actual al final del array
            Object temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Llamar al heapify en el heap reducido
            heapify(array, i, 0);
        }
    }

    // Para convertir el subárbol en un árbol binario
    public static void heapify(Object[] array, int n, int i) {
        int largest = i; // Inicializar el nodo más grande como raíz
        int left = 2 * i + 1; // izquierda = 2*i + 1
        int right = 2 * i + 2; // derecha = 2*i + 2

        // Si el hijo izquierdo es más grande que la raíz
        if (left < n && array[left].toString().compareTo(array[largest].toString()) > 0) {
            largest = left;
        }

        // Si el hijo derecho es más grande que el más grande hasta ahora
        if (right < n && array[right].toString().compareTo(array[largest].toString()) > 0) {
            largest = right;
        }

        // Si el nodo más grande no es la raíz
        if (largest != i) {
            Object swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Recursivamente heapify el subárbol afectado
            heapify(array, n, largest);
        }
    }
    
     // Método de ordenamiento Counting Sort
public static void countingSort(Object[] array) {
    int n = array.length;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    // Encontrar el rango del array de entrada
    for (Object element : array) {
        if (element instanceof String) {
            int value = Integer.parseInt((String) element);
            min = Math.min(min, value);
            max = Math.max(max, value);
        } else if (element instanceof Integer) {
            int value = (int) element;
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
    }

    int range = max - min + 1;

    // Inicializar un array de conteo con todos los elementos como 0
    int[] count = new int[range];
    for (Object element : array) {
        int value;
        if (element instanceof String) {
            value = Integer.parseInt((String) element);
        } else {
            value = (int) element;
        }
        count[value - min]++;
    }

    // Modificar el array de conteo de tal manera que cada elemento en el índice
    // refleje la posición actual de ese elemento en el array de salida
    for (int i = 1; i < range; i++) {
        count[i] += count[i - 1];
    }

    // Construir el array de salida
    Object[] output = new Object[n];
    for (int i = n - 1; i >= 0; i--) {
        int value;
        if (array[i] instanceof String) {
            value = Integer.parseInt((String) array[i]);
        } else {
            value = (int) array[i];
        }
        output[count[value - min] - 1] = array[i];
        count[value - min]--;
    }

    // Copiar el array de salida ordenado al array original
    System.arraycopy(output, 0, array, 0, n);
}

// Método de ordenamiento Radix Sort
public static void radixSort(Object[] array) {
    // Encuentra el valor máximo para saber la cantidad de dígitos
    int maxDigits = getMaxDigits(array);

    // Aplicar Counting Sort para cada dígito, comenzando por el dígito menos significativo
    for (int digit = 1; digit <= maxDigits; digit++) {
        countingSortRadix(array, digit);
    }
}

// Método para obtener la cantidad máxima de dígitos en el array
public static int getMaxDigits(Object[] array) {
    int maxDigits = 0;
    for (Object element : array) {
        int digits = element.toString().length();
        if (digits > maxDigits) {
            maxDigits = digits;
        }
    }
    return maxDigits;
}

// Método de Counting Sort para Radix Sort
public static void countingSortRadix(Object[] array, int digit) {
    int n = array.length;
    int[] count = new int[256]; // Rango de 256 caracteres ASCII

    // Contar la frecuencia de cada carácter en la posición del dígito actual
    for (Object element : array) {
        String str = element.toString();
        int index = str.length() - digit;
        int charValue = (index >= 0) ? (int) str.charAt(index) : 0;
        count[charValue]++;
    }

    // Modificar el array de conteo para que cada elemento en el índice
    // refleje la posición actual del carácter en el array de salida
    for (int i = 1; i < count.length; i++) {
        count[i] += count[i - 1];
    }

    // Construir el array de salida
    Object[] output = new Object[n];
    for (int i = n - 1; i >= 0; i--) {
        String str = array[i].toString();
        int index = str.length() - digit;
        int charValue = (index >= 0) ? (int) str.charAt(index) : 0;
        output[count[charValue] - 1] = array[i];
        count[charValue]--;
    }

    // Copiar el array de salida ordenado al array original
    System.arraycopy(output, 0, array, 0, n);
}

// Método de ordenamiento Bucket Sort
    public static void bucketSort(Object[] array) {
        // Crear un arreglo de listas enlazadas (buckets)
        LinkedList<Object>[] buckets = new LinkedList[10];
        for (int i = 0; i < 10; i++) {
            buckets[i] = new LinkedList<>();
        }

        // Distribuir los elementos del array en los buckets
        for (Object element : array) {
            int bucketIndex = hash((String) element);
            buckets[bucketIndex].add(element);
        }

        // Ordenar cada bucket individualmente
        for (LinkedList<Object> bucket : buckets) {
            insertionSortBucket(bucket);
        }

        // Combinar los buckets en el array original
        int index = 0;
        for (LinkedList<Object> bucket : buckets) {
            for (Object element : bucket) {
                array[index++] = element;
            }
        }
    }

    // Método de hash para determinar el índice del bucket
    public static int hash(String element) {
        return Integer.parseInt(element.substring(0, 1));
    }

    // Método de ordenamiento de inserción para el Bucket Sort
    public static void insertionSortBucket(LinkedList<Object> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            Object key = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && ((String) bucket.get(j)).compareTo((String) key) > 0) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, key);
        }
    }

    // Método para imprimir el array
    public static void printArray(Object[] array) {
        for (Object element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
