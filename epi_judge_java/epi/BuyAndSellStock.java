package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    if (prices.size() < 1){
      return 0;
    }
    double currentMin = prices.get(0);
    double maxProfit = 0.0;
    for (int i = 1; i < prices.size(); ++i){
      maxProfit = Math.max(maxProfit, prices.get(i) - currentMin);
      currentMin = Math.min(currentMin, prices.get(i));
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}


