# DSA Notes

Daily log of concepts learned and programs solved. Each entry links to the
Java file under `src/` and explains the approach, complexity, and why it works.

---

## Concepts

_(Add short notes here as you learn new concepts — e.g. HashMap lookups,
two-pointer technique, sliding window, recursion, DP, etc.)_

### HashMap for O(1) lookups
A `HashMap<Integer, Integer>` lets you store a value and its index, then check
"have I seen the number I need?" in constant time instead of scanning the
array again (which would be O(n) per check, O(n²) overall).

---

## Programs

### 1. Two Sum (LeetCode #1)
**File:** [`src/test/java/com/automation/Leet_01_Two_Sum.java`](src/test/java/com/automation/Leet_01_Two_Sum.java)
**Problem:** Given an array of integers `nums` and an integer `target`,
return the indices of the two numbers that add up to `target`. Assume
exactly one solution exists, and you may not use the same element twice.

**Example:**
```
Input:  nums = [2, 7, 11, 15], target = 9
Output: [0, 1]   // nums[0] + nums[1] == 9
```

**Approach (one-pass HashMap):**
1. Walk through the array once, keeping a `HashMap<value, index>` of numbers
   already seen (`seen`).
2. For each number `num[i]`, compute the complement it needs:
   `comp = target - num[i]`.
3. If `comp` is already in `seen`, we've found our pair — return
   `[seen.get(comp), i]`.
4. Otherwise, record the current number and its index in `seen`, and move on.
5. If we finish the loop with no match, throw, since the problem guarantees
   a solution exists.

```java
public static int[] twoSum(int[] num, int target) {
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < num.length; i++) {
        int comp = target - num[i];
        if (seen.containsKey(comp)) {
            return new int[]{seen.get(comp), i};
        }
        seen.put(num[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
}
```

**Why it works:** instead of checking every pair of numbers (brute force,
O(n²)), we only need to ask "have I already seen the number that completes
this pair?" — and a HashMap answers that in O(1). By the time we reach index
`i`, every earlier number is already in the map, so if its complement was
seen earlier, we catch it immediately.

**Complexity:**
- Time: `O(n)` — single pass through the array.
- Space: `O(n)` — worst case, the map holds all but the last element.

---

### 2. Contains Duplicate (LeetCode #217)
**File:** [`src/test/java/com/automation/Leet_217_containsDuplicate.java`](src/test/java/com/automation/Leet_217_containsDuplicate.java)
**Problem:** Given an integer array `nums`, return `true` if any value
appears at least twice, and `false` if every element is distinct.

**Example:**
```
Input:  nums = [1, 2, 4]
Output: false   // no repeated value
```

**Approach (HashSet membership check):**
1. Keep a `HashSet<Integer>` called `seen` to track numbers encountered so far.
2. For each number, check if it's already in `seen`.
   - If yes, a duplicate exists — return `true` immediately.
   - If no, add it to `seen` and continue.
3. If the loop finishes without finding a repeat, return `false`.

```java
public static boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int i = 0; i < nums.length; i++) {
        if (seen.contains(nums[i])) {
            return true;
        }
        seen.add(nums[i]);
    }
    return false;
}
```

**Why it works:** a `HashSet` gives O(1) average-time membership checks, so
we avoid the brute-force O(n²) approach of comparing every pair of elements.
The first time we see a number that's already in the set, we know it's a
duplicate — same one-pass idea as Two Sum, just checking presence instead of
looking up a paired value.

**Complexity:**
- Time: `O(n)` — single pass through the array.
- Space: `O(n)` — worst case, the set holds every element.

---

### 3. Product of Array Except Self (LeetCode #238)
**File:** [`src/test/java/com/automation/Leet_238_productExceptSelf.java`](src/test/java/com/automation/Leet_238_productExceptSelf.java)
**Problem:** Given an integer array `nums`, return an array `answer` where
`answer[i]` is the product of all elements of `nums` except `nums[i]`,
without using division.

**Example:**
```
Input:  nums = [1, 2, 3, 5]
Output: [30, 15, 10, 6]
```

**Approach (brute force, one product per index):**
1. For each index `i`, compute the product of every other element by
   looping over the whole array and skipping `j == i`.
2. Store that product in `answer[i]`.
3. Repeat for every index.

```java
public static int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] answer = new int[n];

    for (int i = 0; i < n; i++) {
        int product = 1;
        for (int j = 0; j < n; j++) {
            if (j != i) {
                product = product * nums[j];
            }
        }
        answer[i] = product;
    }
    return answer;
}
```

**Bug fixed along the way:** the original code printed the *input* array
(`a`) instead of the *result* (`b`), and printed it with plain
`System.out.println(arr)` — which prints the array's object reference
(e.g. `[I@1b6d3586`), not its contents. Fixed by printing
`Arrays.toString(b)`.

**Why it works:** for each position, re-scanning the array and multiplying
everything except the current index directly matches the problem
definition — no shortcuts, just the definition applied literally.

**Complexity:**
- Time: `O(n²)` — for every index, a full pass over the array.
- Space: `O(n)` — for the output array (no extra space beyond that).

> Note: this can be optimized to `O(n)` time using prefix/suffix products
> (compute running product from the left, then multiply in a running
> product from the right), but the brute-force version above is what's
> currently in the repo.

---

### 4. Maximum Subarray (LeetCode #53)
**File:** [`src/test/java/com/automation/Leet_53_maxSubArray.java`](src/test/java/com/automation/Leet_53_maxSubArray.java)
**Problem:** Given an integer array `nums`, find the contiguous subarray
(containing at least one number) with the largest sum, and return that sum.

**Example:**
```
Input:  nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output: 6   // subarray [4, -1, 2, 1] has the largest sum
```

**Approach (Kadane's algorithm):**
1. Start `currentSum` and `maxSum` at `nums[0]`.
2. Walk the array from index `1` onward, adding each element to `currentSum`.
3. Update `maxSum` if `currentSum` is bigger.
4. If `currentSum` ever drops below `0`, reset it to `0` — a negative running
   sum can only drag down any subarray that extends it, so it's better to
   start fresh from the next element.

```java
public static int maxSubArray(int[] nums) {
    int currentSum = nums[0];
    int maxSum = nums[0];

    for (int i = 1; i < nums.length; i++) {
        currentSum = currentSum + nums[i];
        maxSum = Math.max(currentSum, maxSum);
        if (currentSum < 0) {
            currentSum = 0;
        }
    }
    return maxSum;
}
```

**Bugs fixed along the way:**
1. **Missing reset:** the original code never reset `currentSum` when it
   went negative, so it just accumulated the whole array's running total
   instead of restarting subarrays — e.g. it returned `2` instead of `6`
   for the example above.
2. **Wrong initial value:** `maxSum` (and later `currentSum`) started at
   `0` instead of `nums[0]`. This breaks all-negative inputs — e.g.
   `[-3, -1, -2]` should return `-1` (least-negative single element), but
   starting at `0` returned `0`, a value that never even appears in the
   array.
3. **Off-by-one after the fix:** once both variables were initialized to
   `nums[0]`, the loop still started at `i = 0`, which added `nums[0]` to
   itself before the first real comparison — e.g. a single-element array
   `[5]` returned `10` instead of `5`. Starting the loop at `i = 1` fixed it.

**Why it works:** at every index, `currentSum` represents the best sum of a
subarray ending at that index. Resetting it to `0` whenever it goes negative
means we never let a bad run drag down a future subarray — we just start a
new one. `maxSum` tracks the best value seen across all positions.

**Complexity:**
- Time: `O(n)` — single pass through the array.
- Space: `O(1)` — only two running variables, no extra data structures.

---

### 5. Valid Anagram (LeetCode #242)
**File:** [`src/test/java/com/automation/Leet_242_IsAnagram.java`](src/test/java/com/automation/Leet_242_IsAnagram.java)
**Problem:** Given two strings `s` and `t`, return `true` if `t` is an
anagram of `s` (same letters, same counts, possibly reordered).

**Example:**
```
Input:  s = "muk", t = "esh"
Output: false   // no shared letters at all
```

**Approach (character count array):**
1. If the lengths differ, they can't be anagrams — return `false` early.
2. Use a `count[26]` array (one slot per lowercase letter).
3. Walk `s`, incrementing the count for each character.
4. Walk `t`, decrementing the count for each character.
5. If every slot is back to `0`, every letter in `s` was matched by the same
   letter in `t` — return `true`. Otherwise, return `false`.

```java
public static boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }

    int[] count = new int[26];

    for (int i = 0; i < s.length(); i++) {
        count[s.charAt(i) - 'a']++;
    }

    for (int i = 0; i < t.length(); i++) {
        count[t.charAt(i) - 'a']--;
    }

    for (int c : count) {
        if (c != 0) {
            return false;
        }
    }

    return true;
}
```

**Bug fixed along the way:** the decrement loop read `s.charAt(j)` instead
of `t.charAt(j)`. That meant it decremented the same characters the first
loop had just incremented, so every count always landed back on `0` no
matter what `t` actually contained — the function returned `true` for any
two same-length strings, including `"muk"` vs `"esh"` which share zero
letters. Fixed by reading from `t` in the second loop.

**Why it works:** if `s` and `t` are anagrams, every letter in `s` has a
matching letter in `t`, so incrementing for `s` and decrementing for `t`
cancels out to all zeros. Any leftover non-zero count means a letter
appeared a different number of times in one string than the other.

**Complexity:**
- Time: `O(n)` — one pass over each string.
- Space: `O(1)` — fixed-size 26-element array, independent of input length.

---

### 6. Valid Palindrome (LeetCode #125)
**File:** [`src/test/java/com/automation/Leet_125_IsPalindrome.java`](src/test/java/com/automation/Leet_125_IsPalindrome.java)
**Problem:** Given a string `s`, return `true` if it reads the same forward
and backward after converting all uppercase letters to lowercase and
removing all non-alphanumeric characters.

**Example:**
```
Input:  s = "A man, a plan, a canal: Panama"
Output: true   // "amanaplanacanalpanama" reads the same both ways
```

**Approach (two pointers):**
1. Start `left` at index `0` and `right` at the last index.
2. Move `left` forward while it points at a non-alphanumeric character;
   move `right` backward the same way.
3. Compare the lowercase versions of `s.charAt(left)` and `s.charAt(right)`.
   If they differ, it's not a palindrome — return `false`.
4. If they match, move both pointers inward and repeat.
5. If the pointers cross without a mismatch, it's a palindrome.

```java
public static boolean isPalindrome(String s) {
    int left = 0;
    int right = s.length() - 1;

    while (left < right) {
        char leftChar = Character.toLowerCase(s.charAt(left));
        char rightChar = Character.toLowerCase(s.charAt(right));

        if (!Character.isLetterOrDigit(leftChar)) {
            left++;
            continue;
        }

        if (!Character.isLetterOrDigit(rightChar)) {
            right--;
            continue;
        }

        if (leftChar != rightChar) {
            return false;
        }

        left++;
        right--;
    }

    return true;
}
```

**Why it works:** a palindrome mirrors around its center, so comparing the
outermost characters and working inward checks every mirrored pair exactly
once. Skipping non-alphanumeric characters on each side before comparing
means punctuation and spacing never affect the result, and lowercasing both
characters makes the comparison case-insensitive.

**Complexity:**
- Time: `O(n)` — each pointer moves across the string once.
- Space: `O(1)` — only two index variables, no extra data structures.

---

<!--
Template for new entries — copy this below for each new program:

### N. Problem Name
**File:** [`path/to/File.java`](path/to/File.java)
**Problem:** short description.
**Approach:** step-by-step explanation.
**Complexity:** time / space.
-->
