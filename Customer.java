package exercise;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Customer {
    // 고객의 구매 목록
    private final BuyList buyList;
    // 고객의 장바구니
    private Basket basket;

    public Customer(BuyList buyList) {
        this.buyList = buyList;
    }

    /* 고객 갖고 있는 돈 2만원 */
    private static int MONEY = 20000;

    // 장바구니를 챙김
    public void bring(Basket basket) {
        this.basket = basket;
    }

    public void pickFoods(FoodStand foodStand) {
        ArrayList<BuyList.Item> items = this.buyList.getBuyList();

        ArrayList<Food> foods = foodStand.getFoods();

        // basket에 buyList에 있는 값들 전부 넣어주기
        
        // 근데 여기서 재고보다 많이 담을시 예외 처리 해줘야될꺼같은데

        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < foods.size(); j++) {
                if (foods.get(j).getName().contains(items.get(i).getName())) {
                    for (int k = 0; k < items.get(i).getAmount(); k++) {
                        this.basket.add(foods.get(j));
                    }
                    break;
                }
            }
        }

        ArrayList<Food> basketFoods = this.basket.getFoods();

        int basketCount = basketFoods.size();

        for (int i = 0; i < basketCount; i++) {
            for (int j = 0; j < foods.size(); j++) {
                if (foods.get(j).getName().contains(basketFoods.get(i).getName())) {
                    foods.remove(j);
                    basketCount--;
                }
            }
        }

        if (basketCount != 0) {
            throw new NoSuchElementException("식품매대에 재고가 부족할 때");
        }

    }

    public void payTox(Counter counter) {

        ArrayList<Food> foods = this.basket.getFoods();
        int totalPrice = 0;

        for (Food food : foods) {
            totalPrice += food.getPrice();
        }

        NumberFormat numberFormat = NumberFormat.getNumberInstance();

        MONEY = MONEY - totalPrice;

        if (MONEY < 0) {
            throw new ArithmeticException("고객이 가진 돈을 초과하는 상품을 구매하려고 할 때");
        }


        System.out.println("총 가격은 " + numberFormat.format(totalPrice) + "원 입니다.");
        System.out.println("고객님 결제 후 잔액: " + numberFormat.format(MONEY));
    }


}
