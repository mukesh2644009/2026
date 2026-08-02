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

### 1. Two Sum
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

### 2. Contains Duplicate
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

### 3. Product of Array Except Self
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

<!--
Template for new entries — copy this below for each new program:

### N. Problem Name
**File:** [`path/to/File.java`](path/to/File.java)
**Problem:** short description.
**Approach:** step-by-step explanation.
**Complexity:** time / space.
-->
