package exercise;

import java.util.Scanner;

class NhnMartShell {
    public static void main(String[] args) {
        NhnMart mart = new NhnMart();
        mart.prepareMart();

        BuyList buyList = inputBuyListFromShell();

        Customer jordan = new Customer(buyList);
        // 장바구니를 챙긴다.
        jordan.bring(mart.provideBasket());
        // 식품을 담는다.
        jordan.pickFoods(mart.getFoodStand());      // BuyList 를 Basket에 담아야 됨.
        // 카운터에서 계산한다.
        jordan.payTox(mart.getCounter());
    }

    private static BuyList inputBuyListFromShell() {
        // Scanner에서 buyList 만들기
        //        BuyList buyList = new BuyList();
//        buyList.add(new BuyList.Item("양파", 2));
//        buyList.add(new BuyList.Item("계란", 3));
        // TODO

        // 예시: 양파 2 계란 3
        Scanner scanner = new Scanner(System.in);
        BuyList buyList = new BuyList();

        String input = scanner.nextLine();  // 여기는 예외 처리 안해도 됨.
        String[] splits = input.split(" ");
        // split[0] split[1]
        // split[2] split[3]    ...
        for (int i = 0; i <= splits.length / 2; i = i + 2) {
            buyList.add(new BuyList.Item(splits[i], Integer.parseInt(splits[i+1])));
        }

        return buyList;
    }
}

public class NhnMart {
    private final FoodStand foodStand = new FoodStand();

    public void prepareMart() {
        fillFoodStand();
    }

    private void fillFoodStand() {
        for (int i = 0; i < 2; i++) {
            foodStand.add(new Food("양파", 1_000));
        }
        for (int i = 0; i < 5; i++) {
            foodStand.add(new Food("계란(30개)", 5_000));
        }
        for (int i = 0; i < 10; i++) {
            foodStand.add(new Food("파", 500));
        }
        for (int i = 0; i < 20; i++) {
            foodStand.add(new Food("사과", 2_000));
        }
    }

    public Basket provideBasket() {
        return new Basket();
    }

    public FoodStand getFoodStand() {
        return foodStand;
    }

    public Counter getCounter() {
        return new Counter();
    }
}
