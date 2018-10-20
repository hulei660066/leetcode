package binarySearch;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Tags: Array, Binary Search
 */
class SearchRotatedSortedArr {
    public static void main(String[] args) {
        SearchRotatedSortedArr searchRotatedSortedArr = new SearchRotatedSortedArr();
        System.out.print(searchRotatedSortedArr.search(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 8));

    }

    /**
     * Binary Search.
     * Check which half is sorted.
     * If target is within that half, search in that half.
     * If not, search in the other half.
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[left]) { // left half sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else left = mid + 1;
            } else { // right half sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else right = mid - 1;
            }
        }
        return -1;
    }
}