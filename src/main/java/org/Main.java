import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0),
                new Order("Smartphone", 17000.0),
                new Order("PC", 900.0),
                 new Order("PC", 1900.0),
                new Order("Laptop", 1956.0)
        );

        Stream<Order> orderStream = orders.stream();

        List<Map.Entry<String, Double>> groupedByItem = orderStream
                .collect(Collectors.groupingBy(Order::getProduct,
                        Collectors.summingDouble(Order::getCost)))
                        .entrySet().stream()
                         .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                                 .limit(3)
                                         .collect(Collectors.toList());



         System.out.println(groupedByItem);



    }
}
