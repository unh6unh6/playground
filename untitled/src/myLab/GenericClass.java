package myLab;

class Printer {
    void print(Integer a) {
        System.out.print("A" + a);
    }
    void print(Object a) {
        System.out.print("B" + a);
    }
    void print(Number a) {
        System.out.print("C" + a);
    }
}
public class GenericClass {
    public static void main(String[] args) {
        new Container<>(0).print();
    }
    public static class Container<T> {
        T value;
        public Container(T t) {
            value = t;
            System.out.println(value.getClass().getName());
        }
        public void print() {
            new Printer().print(value);
        }
    }
}