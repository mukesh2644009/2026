package Array.String;

public class BuySellStocks_121 
{
	public static void main(String[] args) {
		int a[] = {7,1,5,3,6,4};
		System.out.println(maxProfit(a));
	}
	
	public static int maxProfit(int[] prices) {
		int minPrice = Integer.MAX_VALUE;
		int max = 0;
		for(int i=0; i< prices.length;i++) {
			if(prices[i] < minPrice) {
				minPrice = prices[i];
			} 
			else {
				max = Math.max(max, prices[i]- minPrice);
			}
		}
		
		return max;
	}
}
