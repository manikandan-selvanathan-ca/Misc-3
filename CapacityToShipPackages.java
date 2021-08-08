public class CapacityToShipPackages {

    // Logically, the ship should be able to carry alteast maximum of given weights.
    // Alteast one or all together. So min would be maximum of weithgs and max would
    // be sum of all weights.
    // Guessing a value in between and calculate the number of days.
    // If days are more than given days, then reduce the window length - For this we
    // can do binary search.
    
    //TC: O(N log(M)) Where N is number of weights and M is the maximum of given weights.
    //SC: O(1) Not using any extra space.
    public int shipWithinDays(int[] weights, int days) {
        if (weights == null || weights.length == 0)
            return 0;

            int min = Integer.MIN_VALUE;
            int max = 0;

            for(int weight: weights) {
                min = Math.max(min, weight);
                max += weight;
            }
        return calculateMinDays(weights, min, max, days);
    }

    private int calculateMinDays(int[] weights, int min, int max, int maxDays) {
        int low = min;
        int high = max;

        while(low <= high) {
            int currentWeight = 0;
            int days = 1;
            int mid = low + (high - low) /2;
            
            for(int weight: weights) {
                if(weight + currentWeight > mid) {
                    currentWeight = 0;
                    days++;
                }
                currentWeight += weight;
            }
            if(days > maxDays) { //Days are more so , we need to larger element.
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        CapacityToShipPackages capacityToShipPackages = new CapacityToShipPackages();
        int minCapacity = capacityToShipPackages.shipWithinDays(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 5);
        System.out.println("The result: " + minCapacity);
    }
}