package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class DataAggregator {

    ProductInfo aggregateProductInfo(String productName) {


        CompletableFuture<String> descriptioncf = CompletableFuture.supplyAsync(() -> {
                        return fetchDescription(productName);
        }).exceptionally(ex -> {
            System.out.println("Incorrect Description");
            return ("No Data");
        });

        CompletableFuture<Double> pricecf = CompletableFuture.supplyAsync(() -> {
            return fetchPrice(productName);
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return 0.0;
        });

        CompletableFuture<Double> ratingcf = CompletableFuture.supplyAsync(() -> {
            return fetchRating(productName);
        }).exceptionally(ex -> {
            System.out.println("Incorrect Description");
            return 0.0;
        });

        CompletableFuture<ProductInfo> product = CompletableFuture.allOf(descriptioncf, pricecf, ratingcf)
                .thenApply(v -> {
                    ProductInfo pi = new ProductInfo();
                    pi.setName(productName);
                    pi.setDescription(descriptioncf.join());
                    pi.setPrice(pricecf.join());
                    pi.setRating(ratingcf.join());
                    return pi;
                });


        return product.join();
    }

    private double fetchPrice(String productName)  {
       try {

           if (ThreadLocalRandom.current().nextDouble() < 0.2) {
               throw new RuntimeException("Incorrect price");
           }
           Thread.sleep(1500);
           return Math.round(((Math.random() * (123494 - 13234 + 1)) + 13234) * 100.0) / 100.0;
       }
       catch (InterruptedException e){
           Thread.currentThread().interrupt();
           throw new RuntimeException("Thread interrupted", e);
       }
    }

    private String fetchDescription(String productName)  {
       try {

           if (ThreadLocalRandom.current().nextDouble() < 0.2) {
               throw new RuntimeException("Incorrect description");
           }
           Thread.sleep(1500);
           return "Some Description of notebook";
       }
       catch (InterruptedException e){
           Thread.currentThread().interrupt();
           throw new RuntimeException("Thread interrupted", e);
       }
    }

    private double fetchRating (String productName)  {
        try {
            if (ThreadLocalRandom.current().nextDouble() < 0.2) {
                throw new RuntimeException("Incorrect rating");
            }
            Thread.sleep(1500);
            return 4.0;
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted", e);
        }


    }

    public static void main(String[] args) {
        DataAggregator da = new DataAggregator();
        System.out.println(da.aggregateProductInfo("Notebook"));
    }
}
