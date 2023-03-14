1. # 异或（Swap）

前提：两个数位于不同的内存。如果是同一个数组，`i`和`j`两个数相同，则直接`异或`成`0`。

```Java
int a = 0;
int b = 1;

a = a ^ b;
b = a ^ b;
a = a ^ b;
```

1. # 查找出现奇数次的数

1. ## 只有一个数出现奇数次，其他数均出现偶数次

```Java
int num = 0;

for (int i = 0; i < nums.length; i++) {
    num ^= nums[i];
}

System.out.println(num)
```

1. ## 只有两个数出现奇数次，其他数均出现偶数次

```Java
int num = 0;

for (int i = 0; i < nums.length; i++) {
    num ^= nums[i];
}

int rightone = num & (~num + 1);    // 提取出二进制中最右边的1
int oneonly = 0;

for (int i = 0; i < nums.length; i++) {
    if (nums[i] & rightone == 0) {
        oneonly ^= nums[i];
    }
}

System.out.println(oneonly + " " + (oneonly ^ num));
```

1. # 递归

1. ## 递归算法

1. ### 无序数组L到R范围中求最大值（初识递归）

```Java
public static int process(int[] arr , int L, int R) {
    if (L == R) {
        return arr[L];
    }
    
    int mid = L + (R - L) / 2;    // (L + R) 可能越界；
    int leftMax = process(arr, L, mid);
    int rightMax = process(arr, mid + 1, R);
    
    return Math.max(leftMax, rightMax);
}
```

1. ### 归并排序

```Java
public static void process(int[] arr, int l, int r) {
    if (l == r) {
        return;
    }

    int mid = l + (r - l) / 2;
    process(arr, l, mid);
    process(arr, mid + 1, r);

    merge(arr, l, mid, r);
}

public static void merge(int[] arr, int L, int mid, int R) {
    int[] help = new int[R - L + 1];
    int i = 0;
    int p1 = L;
    int p2 = mid + 1;

    while (p1 <= mid && p2 <= R) {
        help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    
    // 前面的合并并之后，剩余的再追加到help数组中
    while (p1 <= mid) {
        help[i++] = arr[p1++];
    }
    while (p2 <= R) {
        help[i++] = arr[p2++];
    }

    for (i = 0; i < help.length; i++) {
        arr[L + i] = help[i];
    }
}
```

1. ### 小和问题

![img](README.assets/-167878402526512.assets)

```Java
public static int smallSum(int[] arr, int l, int r) {
    if (l == r) {
        return 0;
    }

    int mid = l + (r - l) / 2;
    return smallSum(arr, l, mid) + smallSum(arr, mid + 1, r) + merge(arr, l, mid, r);
}

public static int merge(int[] arr, int l, int mid, int r) {
    int[] help = new int[r - l + 1];
    int i = 0;
    int p1 = l;
    int p2 = mid + 1;
    int res = 0;

    while (p1 <= mid && p2 <= r) {
        res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
        help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
    }

    while (p1 <= mid) {
        help[i++] = arr[p1++];
    }
    while (p2 <= r) {
        help[i++] = arr[p2++];
    }

    for (i = 0; i < help.length; i++) {
        arr[l + i] = help[i];
    }

    return res;
}
```

1. ### 快排3.0

```Java
/*
快排3.0： 
    最好情况：时间复杂度：O(N * logN)， 空间复杂度：O(logN)；
    最差情况：时间复杂度：O(N ^ 2)， 空间复杂度：O(N)；
*/
int l = 0;
int r = arr.length - 1;

public static void quickSort(int[] arr, int l, int r) {
    if (arr == nil || arr.length < 2) {
        return;
    }
    
    process(arr, l, r);
}

public static void process(int[] arr, int l, int r) {
    if (l < r) {
        swap(arr, l + (int)Math.random() * (r - l + 1), r);
        int[] p = partition(arr, l, r);    // 返回的长度一定为2，[左边界，右边界]
        process(arr, l, p[0] - 1);
        process(arr, p[1] + 1, r);
    }
}

public static int[] partition(int[] arr, int l, int r) {
    int less = l - 1;
    int more = r;
    
    while(l < more) {
        if (arr[l] < arr[r]) {
            swap(arr, ++less, l++);
        } else if (arr[l] > arr[r]) {
            swap(arr, --more, l);
        } else {
            l++;
        }
    }
    
    swap(arr, more, r);
    
    return new int[]{less + 1, more};
}

public static void swap(int[] arr, int l, int r){
    if (arr[l] != arr[r]) {
        arr[l] = arr[l] ^ arr[r];
        arr[r] = arr[l] ^ arr[r];
        arr[l] = arr[l] ^ arr[r];
    }
}
```

1. ### 堆排序（非递归）

```Java
public static void heapSort(int[] arr) {
    if (arr == null || arr.length  < 2) {
        return;
    }
    
    for (int i = 0; i < arr.length; i++) {
        heapInsert(arr, i);
    }
    
    int heapSize = arr.length;
    swap(arr, 0, --heapSize);
    while(heapSize > 0) {
        heapify(arr, 0, heapSize);
        swap(arr, 0, --heapSize);
    }
}

public static void heapInsert(int[] arr, int index) {
    while (arr[index] > arr[(index - 1) / 2]) {
        swap(arr, index, (index - 1) / 2);
        index = (index - 1) / 2;
    }
}

public static void heapify(int[] arr, int index, int heapSize) {
    int left = index * 2 + 1;
    while(left < heapSize) {
        int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
        largest = arr[largest] > arr[index] ? largest : index;
        
        if (largest = index) {
            break;
        }
        
        swap(arr, index, largest);
        index = largest;
        left = index * 2 + 1;
    }
}
```

1. ### 基数排序（桶排序）

```Java
public static void radixSort(int[] arr) {
    if (arr == null || arr.length < 2) {
        return
    }
    
    radixSort(arr, 0, arr.length - 1, maxbits(arr));
}

public static int maxbits(int[] arr) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
        max = Math.max(max, arr[i]);
    }
    
    int res = 0;
    while(max != 0) {
        res++;
        max /= 10;
    }
    
    return res;
}

public static void redixSort(int[] arr, int l, int r, int digit) {
    int radix = 10;
    int i = 0
    int j = 0;
    int[] bucket = new int[r - l + 1];
    
    for (int d = 1; d <= digit; i++) {
        int[] count = new int[radix];
        for (int i = l; i <= r; i++) {
            j = getDigit(arr[i], d);
            count[j]++;
        }
        
        for (i = 1; i < radix; i++) {
            count[i] += count[i - 1];
        }
        
        for (i = r; i >= l; i--) {
            j = getDigit(arr[i], d);
            bucket[count[j] - 1] = arr[i];
            count[j]--;
        }
        
        for (i = l, j = 0; i <= r; i++) {
            arr[i] = bucket[j];
        }
    }    
}

public static int getDigit(int x, int d) {
    return ((x / (int)Math.pow(10, (d - 1))) % 10);
}
```

1. ## master 公式求解时间复杂度

注意：

使用master公式，必须保证子问题规模相同！

1. ### 定义

```
T(N) = a * T(N ``/ b``) + O(N ^ d)
```

- `T(N)`：母问题的数据量是`N`的；
- `T(N ``/`` b)` ：子问题的规模是`N ``/ b`；
- a：子问题被调用的次数；
- O(N ^ d)：除子问题之外，剩下过程的时间复杂度。 

1. ### 求解时间复杂度：

- `log(b)a < d`：`O(N ^ d)`；
- `log(b)a = d`：`O((N ^ d) * logN)`；
- `log(b)a > d`：`O(N ^ log(b)a)`；

1. ### 解释

刚好符合master公式进行求解：

- 无序数组L到R范围中求最大值:
  - `T(N) = 2 * T(N ``/ 2``) + O(1)，(a = 2, b = 2, d = 0)`；
  - 时间复杂度：`O(N)`；
- 归并排序：
  - `T(N) = 2 * T(N ``/ 2``) + O(N)，(a = 2, b = 2, d = 1)`；
  - 时间复杂度：`O(N * logN)`；

1. # 堆

1. ## 数组`arr[i]`在完全二叉树中的位置：

- 左：`2 * i + 1`；
- 右：`2 * i + 2`；
- 根：`(i - 1) ``/ 2`；

1. ## 堆排序（具体代码见3.1.5）：

   1. ###  `heapInsert` 操作：

   1. 时间复杂度：O(N * logN)；
   2. 空间复杂度：O(1)；

    描述：数组中一个数一个数的遍历，一个数一个数的放入完全二叉树中，生成“大根堆”。

   ```Java
   public static void heapInsert(int[] arr, int index){
       while(arr[index] > arr[(index - 1) / 2]) {
           swap(arr, index, (index - 1) / 2);
           index = (index - 1) / 2;
       }
   }
   ```

   1. ###  `heapInsert`操作优化：

      1.   时间复杂度：O(N)；

      1.   描述：数组中的所有数直接构成完全二叉树（此时树肯定不是大根堆），之后将树从下到上，从左到右依次对每个结点进行`heapify`操作；

        for (int i = arr.length - 1; i >= 0; i--) {    heapify(arr, i, arr.length); }

   1. ###  `heapify`操作：

      1. 时间复杂度：O(N * logN)；
      2. 空间复杂度：O(1)；
      3. 描述：在已经生成的“大根堆”中，额外添加一个数字，保证添加新数字之后的完全二叉树仍旧是“大根堆”。

      ```Java
      public static void heapify(int[] arr, int index, int heapSize) {
          int left = index * 2 + 1;
          
          while (left < heapSize) {
              int largest = left + 1 < heapsize && arr[left + 1] > arr[left] ? left + 1 : left;
              largest = arr[largest] > arr[index] ? largest : index;
              
              if (largest = index) {
                  break;
              }
              
              swap(arr, largest, index);
              index = largest;
              left = index * 2 + 1;
          }
      }
      ```

1. ## Java语言提供的小根堆

1. ### 小根堆

```Java
PriorityQueue<Integer> heap = new PriorityQueue<>();

heap.add(8);
heap.add(4);
heap.add(4);
heap.add(9);
heap.add(10);
heap.add(3);

while(!heap.isEmpty()) {
    System.out.println(heap.poll());
}

/*
3 4 4 8 9 10
*/
```

Java提供的小根堆中，相当于一个黑盒，只能一个一个数的放，一个一个数的取。改变其中某个数之后，就可能不是小根堆了（不支持`heapInsert`，`heapify`操作）。建议自己手写小根堆。

1. ### 比较器（实质是重载比较运算符）（小根堆改成大根堆）

```Java
public static class AComp implements Comparator<Integer>{
    /**
     * 如果返回正数，b比a大，b排到a前面；
     * 如果返回负数，a比b大，不用交换；
     * 如果为0，两数一样大。
     *
     * @param a the first object to be compared.
     * @param b the second object to be compared.
     * @return
     */
    public int compare(Integer a, Integer b) {
        return b - a;
    }
}

public static void heapTest() {
    PriorityQueue<Integer> heap = new PriorityQueue<>(new AComp());

    heap.add(4);
    heap.add(7);
    heap.add(2);
    heap.add(5);
    heap.add(4);
    heap.add(3);

    while (!heap.isEmpty()) {
        System.out.println(heap.poll());
    }
}

/*
7 5 4 4 3 2
*/
```

1. ## 堆排序题目

![img](README.assets/-16787840252481.assets)

```Java
public static void sortArrayDistanceLessK(int[] arr, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    int index = 0;
    for (; index <= Math.min(arr.length - 1, k); index++) {
        heap.add(index);
    }

    int i = 0;
    for (; index < arr.length; index++) {
        heap.add(arr[index]);
        arr[i++] = heap.poll();
    }

    while (!heap.isEmpty()) {
        arr[i++] = heap.poll();
    }
}
```

1. ## 一个数据流中，随时可以取得中位数

1. 构建一个大根堆、一个小根堆；
2. 第一个数字进大根堆；
3. 第二个数字和大根堆的堆顶进行比较，小于等于堆顶、入大根堆，大于堆顶、入小根堆；
4. 比较两个堆的`size`，`size`大的比`size`小的多`2`，`size`大的堆的堆顶进入`size`小的堆的堆顶；
5. 执行第二步。

 结果：较小的`n/2`个数在大根堆，交大的`n/2`个数在小根堆；

1. # 排序

1. ## 时间复杂度为O(N ^ 2)：

   - 冒泡排序：最大值依次往后冒泡；稳定；
   - 选择排序：从`0-N`中选一个最小值放在`arr[0]`，从`1-N`中选一个最小值放在`arr[1]`；不稳定；
   - 插入排序：0-1有序，0-2有序，0-3有序， ...， 0-N有序。后面的元素依次插入到前面；稳定；

1. ## 时间复杂度为O(N * logN)：

   - 归并排序（递归）：两个有序的部分合并成一个（`merge`操作）；稳定；
   - 快排3.0：`partition`操作（荷兰国旗问题）；不稳定；
   - 堆排序：`heapInsert`操作形成大根堆，根节点和最后一个节点交换，`heapSize--`，交换之后的堆从上到下做`heapify`操作；不稳定；
   - 计数排序、基数排序：稳定；

|                  | 时间复杂度    | 空间复杂度 | 稳定性 |
| ---------------- | ------------- | ---------- | ------ |
| 选择排序         | `O(N ^ 2)`    | `O(1)`     | ×      |
| 冒泡排序         | `O(N ^ 2)`    | `O(1)`     | √      |
| 插入排序         | `O(N ^ 2)`    | `O(1)`     | √      |
| 归并排序（递归） | `O(N * logN)` | `O(N)`     | √      |
| 快排3.0          | `O(N * logN)` | `O(logN)`  | ×      |
| 堆排序           | `O(N * logN)` | `O(1)`     | ×      |

1. ## 注意：

- 归并排序的空间复杂度可以做到O(1)，但是非常难，同时又失去了稳定性，还不如使用堆排序；
- 原地归并排序会导致时间复杂度变成O(N ^ 2)，还不如使用插入排序；
- 快排3.0可以保证稳定性，但会导致空间复杂度变成O(N)；（解决方案：01StableSort论文）
- 

推荐使用快排3.0！

1. ## 题目：

基数放在数组左边，偶数放在数组右边，要求原始的次序不变，要求时间复杂度O(N)，空间复杂度O(1)；

可以，但是非常难，这和快排中的`partition`过程是一个道理，想要保证稳定性，经典的快排做不到。请面试官教教我！

1. ## 工程上对排序的改进：

1. 利用时间复杂度O(N * log N)和O(N ^ 2)的优势；
2. 稳定性考虑；
3. 综合排序：快排中使用插入排序（快排的子递归中，数据样本量小于60时，直接进行插入排序）；
4. Java提供的`Array.sort()`：系统自动判定排序的内容是否为基础类型，是基础类型，用归并，不是则用快排；为了保证稳定性。

1. # 哈希表、有序表

1. ## 区别

有序表的Key按照顺序组织，哈希表的Key完全不组织；

1. ## 哈希表（`Key`无序）

- 哈希表的任何增删改查操作，其时间复杂度都认为是常数级别；
- 对于基础类型（`int`、`String`），哈希表内部按照值传递，占用空间就等于变量所占用的空间；对于`Node`、`List`类型，哈希表按照引用传递，`Key`一律占8字节（直接记录内存地址）；
- 哈希表增删改查操作的时间复杂度默认为常数级别，但是这个级别比较大；
- 哈希表对基本数据类型，使用值传递；
- 哈希表的key为`node`、`student`等，必须提供比较器，`Key`使用引用传递，将内存地址拷贝到哈希表中作为`key`，此时的`key`一律8字节；

![img](README.assets/-16787840252482.assets)

1. ### HashSet

只包含`Key`

```Java
HashSet<Integer> hash = new HashSet<>();

hash.add(1);
hash.add(2);
hash.remove(2);

hash.contians(2);    // false
```

1. ### HashMap

包含`Key-Value`

```Java
HashMap<Integer, String> map= new HashMap<>();

map.put(1, "name");
map.put(2, "age");
map.put(3, "msg");
map.put(3, "newMsg")

map.remove(3);

map.get(1);    // name
map.get(4);    // null

map.containsKey(1);    // true
```

1. ## 有序表（`Key`有序）

- 只有`Key`，就是`Set`结构；
- 既有`Key`又有`Value`，就是`Map`结构；
- 以下所有操作时间复杂度为`O(logN)`；
- 红黑树、AVL数、size-balance-tree、跳表等都属于有序表，只是底层实现逻辑不同；
- key为`node`、`student`等，必须提供比较器，`Key`使用引用传递，将内存地址拷贝到哈希表中作为`key`，此时的`key`一律8字节；

![img](README.assets/-16787840252493.assets)

```Java
TreeMap<Integer, String> treeMap = new TreeMap<>();

treeMap.put(3, "msg");
treeMap.put(1, "name");
treeMap.put(2, "age");

treeMap.containsKey(2);    // true
treeMap.get(2);
treeMap.remove(2);
treeMap.firstKey();    // 最小
treeMap.lastKey();     // 最大
treeMap.floorKey(4);   // 所有<=4的key中，离4最近的Key；
treeMap.ceilingKey(4); // 所有>=4的key中，离4最近的key；
```

1. # 链表

1. ## 面试

1. 笔试：不需要过于重视空间复杂度，一切为了时间复杂度；
2. 面试：时间复杂度仍然放在第一位，但一定要找到节省空间复杂度的方法；

1. ## 链表结构

1. 单链表
   1. ```Java
      class Node<E> {
          E data;
          Node<E> next;
          
          Node(E data, Node<E> next) {
              this.data = data;
              this.next = next;
          }
      }
      ```

   2. ```Java
      public static class Node {
          public int value;
          public Node next;
          
          public Node(int data) {
              this.value = data;
          }
      }
      ```
2. 双链表
   1. ```Java
      class Node<V> {
          V value;
          Node next;
          Node last;
      }
      ```

1. ## 题目

1. ### 反转单链表

```Java
public static Node reverse(Node head) {
    Node prev = null;
    Node curr = head;
    while (curr != null) {
        Node temp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = temp;
    }
    
    return prev;
}
```

1. ### 单链表排序

将一个链表，小于某个数的放左边，等于某个数的放中间，大于某个数的放右边

- 方法一：

```Java
// 链表数组Partition
public static Node listPartition1(Node head, int pivot) {
    if (head == null) {
        return null;
    }

    Node cur = head;
    int i = 0;
    while (cur != null) {
        i++;
        cur = cur.next;
    }

    Node[] nodeArr = new Node[i];
    i = 0;
    cur = head;
    for (i = 0; i < nodeArr.length; i++) {
        nodeArr[i] = cur;
        cur = cur.next;
    }

    arrPartition(nodeArr, pivot);
    for (i = 1; i < nodeArr.length; i++) {
        nodeArr[i - 1].next = nodeArr[i];
    }

    nodeArr[i - 1].next = null;

    return nodeArr[0];
}

public static void arrPartition(Node[] nodeArr, int pivot) {
    int small = -1;
    int big = nodeArr.length;
    int index = 0;

    while (index != big) {
        if (nodeArr[index].value < pivot) {
            swap(nodeArr, ++small, index++);
        } else if (nodeArr[index].value == pivot) {
            index++;
        } else {
            swap(nodeArr, --big, index);
        }
    }
}

public static void swap(Node[] nodeArr, int a, int b) {
    Node temp = nodeArr[a];
    nodeArr[a] = nodeArr[b];
    nodeArr[b] = temp;
}
```

- 方法二：

```Java
// 六个指针
public static Node listPartition2(Node head, int pivot) {
    Node sH = null;
    Node sT = null;
    Node eH = null;
    Node eT = null;
    Node mH = null;
    Node mT = null;

    Node next = null;
    while (head != null) {
        next = head.next;
        head.next = null;

        if (head.value < pivot) {
            if (sH == null) {
                sH = head;
                sT = head;
            } else {
                sT.next = head;
                sT = head;
            }
        } else if (head.value == pivot) {
            if (eH == null) {
                eH = head;
                eT = head;
            } else {
                eT.next = head;
                eT = head;
            }
        } else {
            if (mH == null) {
                mH = head;
                mT = head;
            } else {
                mT.next = head;
                mT = head;
            }
        }

        head = next;
    }

    // 非常精妙的写法（只连尾巴，最后return再连接头部）
    if (sT != null) {
        sT.next = eH;
        eT = eT == null ? sT : eT;
    }

    if (eT != null) {
        eT.next = mH;
    }

    return sH != null ? sH : (eH != null ? eH : mH);
}
```

1. ### 复制含有随机指针节点的链表

![img](README.assets/-16787840252494.assets)

- 方法一：（哈希表）
  - ```Java
    package les04.algorithm;
    
    import java.util.HashMap;
    
    /**
     * @author ether
     */
    public class CopyListWithRandom {
        public static class Node{
            public int value;
            public Node rand;
            public Node next;
    
            public Node(int value) {
                this.value = value;
            }
        }
    
        public static Node copyListWithRandom1(Node head) {
            Node cur = head;
            HashMap<Node, Node> map = new HashMap<>();
    
            while (cur != null) {
                map.put(cur, new Node(cur.value));
                cur = cur.next;
            }
            
            cur = head;
    
            while (cur != null) {
                map.get(cur).next = map.get(cur.next);
                map.get(cur).rand = map.get(cur.rand);
                cur = cur.next;
            }
    
            return map.get(head);
        }
    }
    ```
- 方法二：（非哈希表）
  - ```Java
    public class {
        public static class Node{
            public int value;
            public Node rand;
            public Node next;
            
            public Node(int value) {
                this.value = value;
            }
        }
    
        public static Node copyListWithRandom2(Node head) {
            if (head == null) {
                return null;
            }
        
            Node cur = head;
            Node nextNode = null;
            
            while (cur != null) {
                nextNode = cur.next.next;
                cur.next = new Node(cur.value);
                cur.next.next = nextNode;
                cur = nextNode;
            }
            
            cur = head;
            Node curCopy = null;
            while (cur != null) {
                nextNode = cur.next.next;
                curCopy = cur.next;
                if (nextNode.rand != null) {
                    curCopy.rand = nextNode.rand
                } else {
                    curCopy.rand = null;
                }
                
                cur = nextNode;
            }
            
            Node res = head.next;
            cur = head;
            
            while (cur != null) {
                nextNode = cur.next.next;
                curCopy = cur.next;
                cur.next = nextNode;
                if (nextNode != null) {
                    curCopy.next = nextNode.next;
                } else {
                    curCopy.next = null
                }
                
                cur = nextNode;
            }
            
            return res;
        }
    }
    ```

1. ### 判断单链表是否回文

方法一：（使用栈）

```Java
public static boolean isPalindrome1(Node head) {
    Stack<Node> stack = new Stack<Node>();
    
    Node cur = head;
    while (cur != null) {
        stack.push(cur);    // 将元素压入栈中
        cur = cur.next;
    }
    
    while (head != null) {
        if (head.value != stack.pop().value) {    // stack.pop() 弹出栈顶元素
            return false;
        }
        
        head = head.next;
    }
    
    return true;
}
```

方法二：（快慢指针）

```Java
public static boolean isPalindrome2(Node head) {
    if (head == null || head.next == null) {
        return true;
    }
    
    Node right = head.next;
    Node cur = head;
    
    while (cur.next != null && cur.next.next != null) {
        right = right.next;
        cur = cur.next.next;
    }
    
    Stack<node> stack = new Stack<Node>();
    while (right != null) {
        stack.push(right);
        right = right.next;
    }
    
    while (!stack.isEmpty()) {
        if (head.value != stack.pop().value) {
            return false;
        }
        
        head = head.next;
    }
    
    return true;
}
```

方法三：

```Java
public static boolean isPalindrome3(Node head) {
    if (head == null || head.next == null) {
        return true;
    }
    
    Node n1 = head;
    Node n2 = head;
    
    while (n2.next != nill && n2.next.next != null) {
        n1 = n1.next;
        n2 = n2.next.next;
    }
    n2 = n1.next;
    n1.next = null;
    
    Node n3 = null;
    
    // 反转后半段，反转后的用n1记录
    while (n2 != null) {
        n3 = n2.next;
        n2.next = n1;
        n1 = n2;
        n2 = n3;
    }
    
    n3 = n1;
    n2 = head;
    
    boolean res = true;
    while (n1 != null && n2 != null) {
        if (n1.value != n2.value) {
            res = false;
            break;
        }
        n1 = n1.next;
        n2 = n2.next;
    }
    
    n1 = n3.next;
    n3.next = null;
    while (n1 != null) {
        n2 = n1.next;
        n1.next = n3;
        n3 = n1;
        n1 = n2;
    }
    
    return res;
}
```

1. ### 两个单链表相交问题

1. #### 前置问题：判断一个链表有没有环，有环则输出入环结点。

方法：使用快慢指针

1. 快慢指针开始走；
2. 两个指针相遇，快指针回到头结点；
3. 之后快慢指针同时以相同的速度一步一步的走；
4. 两个指针相遇，该点即为入环结点。

```Java
public static Node getloopNode(Node head) {
    if (head == null || head.next == null || head.next.next == null) {
        return null;
    }
    
    Node n1 = head.next;    // slow
    Node n2 = head.next.next;    // fast
    while (n1 != n2) {
        if (n2.next == null || n2.next.next == null) {
            return null;
        }
        
        n2 = n2.next.next;
        n1 = n1.next;
    }
    
    n2 = head;    // fast 回到头结点
    while(n1 != n2) {
        n1 = n1.next;
        n2 = n2.next;
    }
    
    return n1;
}
```

1. #### 两个单链表相交问题

判断两个单链表是否相交可能的情况；

1. 两个无环单链表是否相交；
2. 连个有环单链表是否相交；
3. 不存在一个有环单链表和一个无环单链表相交。

![img](README.assets/-16787840252495.assets)

```Java
// 两个无环单链表是否相交
public static Node noLoop(Node head1, Node head2) {
    if (head1 == null || head2 == null) {
        return null;
    }

    Node cur1 = head1;
    Node cur2 = head2;

    int n = 0;
    while(cur1.next != null){
        n++;
        cur1 = cur1.next;
    }

    while (cur2.next != null) {
        n--;
        cur2 = cur2.next;
    }
    
    // 两个链表的结尾内存地址不一样，则两个链表不相交！
    if (cur1 != cur2) {
        return null;
    }

    cur1 = n > 0 ? head1 : head2;
    cur2 = cur1 == head1 ? head2 : head1;

    n = Math.abs(n);
    while (n != 0) {
        n--;
        cur1 = cur1.next;
    }

    while (cur1 != cur2) {
        cur1 = cur1.next;
        cur2 = cur2.next;
    }


    return cur1;
}

// 连个有环单链表是否相交
public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
    Node cur1 = null;
    Node cur2 = null;

    if (loop1 == loop2) {
        cur1 = head1;
        cur2 = head2;
        int n = 0;
        while (cur1 != loop1) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != loop2) {
            n--;
            cur2 = cur2.next;
        }

        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    } else {
        cur1 = loop1.next;

        while (cur1 != loop1) {
            if (cur1 == loop2) {
                return loop1;   // 返回loop1 和 loop2 都可以
            }

            cur1 = cur1.next;
        }

        return null;
    }
}

// 主函数
public static Node getIntersectNode(Node head1, Node head2) {
    if (head1 == null || head2 == null) {
        return null;
    }

    Node loop1 = getloopNode(head1);
    Node loop2 = getloopNode(head2);

    // 两个单链表都无环
    if (loop1 == null && loop2 == null) {
        return noLoop(head1, head2);
    }

    // 两个单链表都有环
    if (loop1 != null && loop2 != null) {
        return bothLoop(head1, loop1, head2, loop2);
    }

    // 不可能一个有环一个无环
    return  null;
}
```

1. # 树

1. ## 树的遍历

![img](README.assets/-16787840252496.assets)

1. ### 递归序演示

```Java
public static void f(Node head) {
    if (head == null) {
        return
    }
    
    f(head.left);
    f(head.right);
}
```

1. ### 递归方式

1. 递归序：`1 ``2 ``4 4 4 ``2 ``5 5 5 ``2 ``1 ``3 ``6 6 6 ``3 ``7 7 7 ``3 ``1`；
2. 先序（头、左、右）（递归序中第一次遇到的就打印）：`1 2 4 5 3 6 7`；
3. 中序（左、头、右）（递归序中第二次遇到的才打印）：`4 2 5 1 6 3 7`；
4. 后序（左、右、头）（递归序中第三次遇到的才打印）：`4 5 2 6 7 3 1`；

```Java
public class PreinPosTraversal {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.value + " ");
        inOrderRecur(head.right);
    }

    public static void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.println(head.value + " ");
    }

}
```

1. ### 非递归方式（使用栈，面试中常考）

1. #### 先序遍历

步骤：

1. 头结点入栈
2. 从栈中弹出一个结点；
3. 打印弹出的结点；
4. 先右孩子再左孩子入栈；
5. 回到第2步。

细节：

1. `1`入栈；
2. 弹出`1`；
3. 打印`1`；
4. `3`入栈、`2`入栈；
5. `2`出栈、
6. 打印`2`；
7. `5`入栈、`4`入栈；
8. 打印`4`；
9. 左右孩子没有，什么也不做；
10. `5`出栈；
11. 打印`5`；
12. 左右孩子没有，什么也不做；
13. `3`出栈；
14. 打印`3`；
15. `7`进栈、`6`进栈；
16. `6`出栈；
17. 打印`6`；
18. 左右孩子没有，什么也不做；
19. `7`出栈；
20. 打印`7`。
21. 左右孩子没有，什么也不做；

```Java
// 先序遍历
public static void preOrderUnrecur(Node head) {
    System.out.println("Pre-Order: ");
    if(head != null) {
        Stack<Node> stack = new Stack<Node>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.println(head.value + " ");

            // 右结点不为空，先压右
            if (head.right != null) {
                stack.push(head.right);
            }

            // 左结点不为空，再压左
            if (head.left != null) {
                stack.push(head.left);
            }
        }
     }
    System.out.println();
}
```

1. #### 中序遍历

准备一个栈：

1. 整棵树的左边界从上到下依次进栈；
2. 弹出结点4；
3. 打印弹出的结点4；
4. 结点4没有右子树；
5. 弹出结点2；
6. 打印结点2；
7. 结点2有右子树5；
8. 右子树5进栈，结点5的左子树进栈，没有就算了；
9. 弹出结点5；
10. 打印结点5；
11. 弹出结点1；
12. 打印结点1；
13. 以此类推。

```Java
// 中序遍历
public static void inOrderUnRecur(Node head) {
    System.out.println("In-Order: ");
    if (head != null) {
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || head != null) {
            // 不停地把左边界依次进栈
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                // 弹出最左节点，打印最左节点，head走到右结点
                head = stack.pop();
                System.out.println(head.value + " ");
                head = head.right;
            }
        }
    }

    System.out.println();
}
```

1. #### 后续遍历

- 先序遍历是先将头节点入栈、之后先压右再压左，实现头左右的顺序；如果先将头入栈、之后先压左再压右，实现头右左的顺序；将头右左的顺序再入栈、出栈，最后打印的顺序为左右头，从而实现后续遍历。

准备两个栈，第二个栈作为收集栈：

1. 根结点进栈；
2. 弹出根节点；
3. 弹出的结点进收集栈；
4. 右结点进栈，左节点进栈；
5. 弹出右结点；
6. 右结点3进收集栈；
7. 右结点7进栈，左节点6进栈；
8. 右结点7弹出；
9. 右结点7进收集栈；
10. 左节点6弹出；
11. 左结点6进收集栈；
12. 以此类推；
13. 依次弹出收集栈并打印。

```Java
// 后续遍历
public static void posOrderUnRecur1(Node head) {
    System.out.println("Pos-Order: ");
    if (head != null) {
        Stack<Node> s1 = new Stack<Node>();
        Stack<Node> s2 = new Stack<Node>();
        s1.push(head);

        while (!s1.isEmpty()) {
            head = s1.pop();
            s2.push(head);

            if (head.left != null) {
                s1.push(head.left);
            }

            if (head.right != null) {
                s1.push(head.right);
            }
        }

        while (!s2.isEmpty()) {
            System.out.println(s2.pop().value + " ");
        }
    }

    System.out.println();
}
```

1. ### 树的直观打印

```Java
package les05.algorithm;

/**
 * @author ether
 */
public class PrintBinaryTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static void printTree(Node head) {
        System.out.println("Binary tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }
    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }

        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;

        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }

        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        printTree(head);
    }
}
```

![img](README.assets/-16787840252497.assets)

1. ### 宽度优先遍历

1. #### 二叉树的宽度优先遍历

使用队列：

1. 头进队列；
2. 头出队列并打印；
3. 先放左再放右；
4. 出队就打印；
5. 以此类推

```Java
public static void w(Node head) {
    if (head == null) {
        return;
    }
    
    Queue<Node> queue = new linkedList<>();
    queue.add(head);
    
    while (!queue.isEmpty()) {
        Node cur = queue.pop();
        System.out.println(cur.value);
        
        if (cur.left != null) {
            queue.add(cur.left);
        }
        
        if (cur.right != null) {
            queue.add(cur.right);
        }
    }
}
```

1. #### 求一棵二叉树的最大宽度

- 方法一：（哈希表记录当前节点所在的层）

```Java
public static void treeMaxWidth(Node head) {
    if (head == null) {
        return;
    }
    
    Queue<Node> queue = new LinkedList<>();
    queue.add(head);
    HashMap<Node, Integer> levelMap = new HashMap<>();
    levelMap.add(head, 1);
    int curLevel = 1;
    int curLevelNodes = 0;
    int max = 0;
    
    while(!queue.isEmpty()) {
        Node cur = queue.poll();
        int curNodeLevel = levelMap.get(cur);
        if (curNodeLevel == curLevel) {
            curLevelNodes++;
        } else {
            curLevel++;
            max = Math.max(max, curlevelNodes);
            curLevelNodes = 1;
        }
        
        if (cur.left != null) {
            queue.add(cur.left);
            levelMap.add(cur.left, curNodeLevel + 1);
        }
        
        if (cur.right != null) {
            queue.add(cur.right);
            levelMap.add(cur.rught, curNodeLevel + 1);
        }
    }
    
}
```

- 方法二：（队列）

1. ### 是否为搜索二叉树

搜索二叉树：每一棵子树，左树的节点都比它小，右树的结点都比它大。

- 递归1
  - ```Java
    long preValue = Long.MIN_VALUE;
        
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        
        boolean isLeftBST = isBST(head.left);
        if (!isLeftBST) {
            return false;
        }
        
        if (head.Value <= preValue) {
            return false;
        } else {
            preValue = head.Value;
        }
        
        return isBST(head.right);
    }
    ```
- 递归2
  - ```Java
    public static boolean isBST(Node head) {
        List<Node> list = new ArrayList<>();
        process(head, list);
        
        // for range...
    }
    
    public static void process(Node head, List<Node> list) {
        if (head == null) {
            return true;
        }
        
        process(head.left, list);
        list.add(head);
        process(head.right, list);
    }
    ```
- 递归3
  - ```Java
    public static class ReturnData {
        public boolean isBST;
        public int min;
        public int max;
        
        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    
    public static ReturnData isBST(Node head) {
        if (head == null) {
            return null;
        }
        
        RetrunData leftData = isBST(head.left);
        ReturnData rightData = isBST(head.right);
        
        int min = head.value;
        int max = head.value;
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max. rightData.max);
        }
        
        boolean isBST = true;
        if (leftData != null && (!leftData.isBST || leftData.max >= head.value)) {
            isBST = false;
        }
        
        if (rightData != null && (!rightData.isBST || rifhtData.min <= head.value)) {
            isBST = false;
        }
        
        return new RetrunData(isBST, min, max);
    }
    ```

- 非递归
  - ```Java
    long preValue = Long.MIN_VALUE;
    
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty()) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.value <= preValue) {
                    return false;
                } else {
                    preValue = head.value;
                }
                
                head = head.right;
            }
        }
        
        return true;
    }
    ```

1. ### 是否为完全二叉树（使用宽度优先遍历）

思路：

1. 任何一个结点，有右孩子，无左孩子，直接返回false；
2. 如果第一次遇到只有左孩子的结点，则接下来遍历的所有结点都为叶子结点，否则返回false；

```Java
public static boolean(Node head) {
    if (head == null) {
        return true;
    }
    
    Queue<Node> queue = new LinkedList<>();
    boolean leaf = false;    // 是否遇到过只有一个孩子的结点
    Node l = null;
    Node r = null;
    queue.add(head);
    
    while(!queue.isEmpty()) {
        head = queue.pop();
        l = head.left;
        r = head.right;
    
        if (leaf && (l != null || r != null) || l == null && r != null) {
            return false;
        }
        
        if (l != null) {
            queue.add(l);
        }
        
        if (r != null) {
            queue.add(r);
        }
        
        if (l == null || r == null) {
            leaf = true;
        }
    }
    
    return true;
}
```

1. ### 是否为满二叉树

最大深度为l，结点个数为n，则n = (2 ^ l) - 1；

- 递归
  - ```Java
    public static class Info{
        public int height;
        public int nodes;
        
        public Info(int h, int n) {
            this.height = h;
            this.nodes = n;
        }
    }
    
    psvm {
        public static boolean isF(Node head) {
            if (head == null) {
                return true;
            }
            
            Info data = f(head);
            
            return data.nodes == (1 << data.height - 1);
        }
    }
        
    public static Info f(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        
        Info leftData = f(head.left);
        Info rightData = f(head.right);
        
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        
        return new Info(height, nodes);
    }
    ```

1. ### 是否为平衡二叉树

每一棵子树，左树的高度和右树的高度差不超过1；

```Java
public static boolean isBalanced(Node head) {
    return  process(head).isBalanced;
}

public static class ReturnType {
    public boolean isBalanced;
    public int height;
    
    public ReturnType(boolean isB, int hei) {
        isBalanced = isB;
        height = hei;
    }
}

public static ReturnType process(Node x) {
    if (x == null) {
        return new ReturnType(true, 0);
    }
    
    ReturnType leftData = process(x.left);
    ReturnType rightData = process(x.right);
    
    int height = Math.max(leftData.height, rightData.height) + 1;
    boolean isBalanced = leftData.isBalanced && rightData.isBalanced && Math.abs(leftData.height - rightData.height) < 2;
    
    return new ReturnType(isBalanced, height);
}
```

1. ### 最低公共祖先

在一个二叉树中，给定两个结点，找出这两个结点的最低公共祖先。

- 方法一：
  - ```Java
    public static Node lca(Node head, Node O1, Node O2) {
        HashMap<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(head, head);
        process(head, fatherMap);
        HashSet<Node> setO1 = new HashSet<>();
        
        Node cur = O1;
        while (cur != fatherMap.get(cur)) {
            setO1.add(cur);
            cur = fatherMap.get(cur);
        }
        
        setO1.add(head);
    }
    
    public static void process(Node head, HashMap<Node, Node> fatherMap) {
        if (head == null) {
            return;
        }
        
        fatherMap.put(head.left, head);
        fatherMap.put(head.right, head);
        process(head.left, fatherMap);
        process(head.right, fatherMap);
    }
    ```

- 方法二：
  - ```Java
    public static Node lowestAncestor(Node head, Node O1, Node O2) {
        if (head == null || head == O1 || head == O2) {
            return head;
        }
        
        Node left = lowestAncestor(head.left, O1, O2);
        Node right = lowestAncestor(head.right, O1, O2);
        if (left != null && right != null) {
            return head;
        }
        
        return left != null ? left : right;
    }
    ```

1. # 图

1. ## 图的表示

- 点
  - ```Java
    public class Node {
        public int value;    // 点的编号
        public int in;       // 入度
        public int out;      // 出度
        public ArrayList<Node> nexts;    // 从自己出发直接相邻的点
        public ArrayList<Edge> edges;    // 自己拥有的边
        
        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
        
    }
    ```

- 边
  - ```Java
    public class Edge {
        public int weight;    // 权重
        public Node from;     // 从哪个点来
        public Node to;       // 到哪个点去
        
        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }
    ```

- 图
  - ```Java
    public class Graph {
        public HashMap<Integer, Node> nodes;    // 点集
        public HashSet<Edge> edges;    // 边集
        
        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }
    ```

1. ## 图的转化

例如：

1. 二维数组表示有向图，`matrix[i][2] : 边的权值；``matrix[i][0] = '3' -> matrix[i][1] = '2' : '3'点 -> '2'点；`
   1. ```Java
      public class Node {
          public int value;    // 点的编号
          public int in;       // 入度
          public int out;      // 出度
          public ArrayList<Node> nexts;    // 从自己出发直接相邻的点
          public ArrayList<Edge> edges;    // 自己拥有的边
          
          public Node(int value) {
              this.value = value;
              in = 0;
              out = 0;
              nexts = new ArrayList<>();
              edges = new ArrayList<>();
          }
      }
      
      public class Edge {
          public int weight;    // 权重
          public Node from;     // 从哪个点来
          public Node to;       // 到哪个点去
          
          public Edge(int weight, Node from, Node to) {
              this.weight = weight;
              this.from = from;
              this.to = to;
          }
      }
      
      public class Graph {
          public HashMap<Integer, Node> nodes;    // 点集
          public HashSet<Edge> edges;    // 边集
          
          public Graph() {
              nodes = new HashMap<>();
              edges = new HashSet<>();
          }
      }
      
      public static Graph createGraph(Integer[][] matrix) {
          Graph graph = new Graph();
          
          for (int i = 0; i < matrix.length(); i++) {
              Integer from = matrix[i][0];
              Integer to = matrix[i][1];
              Integer weight = matrix[i][2];
              
              if (!graph.nodes.containsKey(from)) {
                  graph.nodes.put(from, new Node(from));
              }
              
              if (!graph.nodes.containsKey(to)) {
                  graph.nodes.put(to, new Node(to));
              }
              
              Node fromNode = graph.nodes.get(from);
              Node toNode = graph.nodes.get(to);
              Edge newEdge = new Edge(weight, fromNode, toNode);
              
              formNode.nexts.add(toNode);
              fromNode.out++;
              toNode.in++;
              fromNode.edges.add(newEdge);
              graph.edges.add(newEdge);
          }
      
          return graph;
      }
      ```

1. ## 宽度优先遍历

```Java
public static void bsf(Node node) {
    if (node == null) {
        return;
    }
    
    Queue<Node> queue = new LinkedList<>();
    HashSet<Node> set = new HashSet<>;
    
    queue.add(node);
    set.add(node);
    
    while(!queue.isEmpty()) {
        Node cur = queue.poll();
        System.out.println(cur.value);
        
        for (Node next : cur.nexts) {
            if (!set.contains(next)) {
                queue.add(next);
                set.add(next);
            }
        }
    }
    
}
```

1. ## 深度优先遍历

```Java
public static void dfs(Node node) {
    if (node == null) {
        return;
    }
    
    Stack<Node> stack = new Stack<>();
    HashSet<Node> set = new HashSet<>();
    
    stack.add(node);
    set.add(node);
    
    System.out.println(node.value);
    
    while(!stack.isEmpty()) {
        Node cur = stack.poll();
        for (Node next : cur.nexts) {
            if (!set.contains(next)) {
                stack.push(cur);
                stack.push(next);
                set.add(next);
                System.out.println(next.value);
                break;
            }
        }
    }
}
```

1. ## 拓扑排序

```Java
public static List<Node> topologySort(Grapg grapg) {
    HashMap<Node, Integer> inMap = new HashMap<>();    // <某一个node, node的入度>
    Queue<Node> zeroInQueue = new LinkedList<>();      // 入度为"0"的点才能进这个队列
    
    for (Node node : graph.nodes.values()) {
        inMap.put(ndoe, node.in);
        if (node.in == 0) {
            zeroInQueue.add(node);
        }
    }
    
    List<Node> result = new ArrayList<>();
    while (!zeroInQUeue.isEmpty()) {
        Node cur = zeroInQueue.poll();
        result.add(cur);
        for (Node next : cur.nexts) {
            inMap.put(next, inMap.get(next) - 1);
            
            if (inMap.get(next) == 0) {
                zeroInQueue.add(next);
            }
        }
    }
    
    return result;
}
```

1. ## Kruskal算法

要求：无向图，生成最小生成树

思路：将边的权值从小到大排序，之后依次遍历添加边，如果形成环则抛弃。

```Java
package les06.graph.algorithm;

import java.util.*;

/**
 * @author 樊金亮
 * @date 2023/2/8
 */
public class Kruskal {
    public static class MySets {
        public HashMap<GraphGenerator.Node, List<GraphGenerator.Node>> setMap;

        public MySets(List<GraphGenerator.Node> nodes) {
            for (GraphGenerator.Node node : nodes) {
                List<GraphGenerator.Node> set = new ArrayList<>();
                set.add(node);
                setMap.put(node, set);
            }
        }

        public boolean isSameSet(GraphGenerator.Node from, GraphGenerator.Node to) {
            List<GraphGenerator.Node> fromSet = setMap.get(from);
            List<GraphGenerator.Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        public void union(GraphGenerator.Node from, GraphGenerator.Node to) {
            List<GraphGenerator.Node> fromSet = setMap.get(from);
            List<GraphGenerator.Node> toSet = setMap.get(to);

            for (GraphGenerator.Node toNode : toSet) {
                fromSet.add(toNode);
                setMap.put(toNode, fromSet);
            }
        }
    }

    public static class UnionFind {
        private HashMap<GraphGenerator.Node, GraphGenerator.Node> fatherMap;
        private HashMap<GraphGenerator.Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<GraphGenerator.Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (GraphGenerator.Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private GraphGenerator.Node findFather(GraphGenerator.Node n) {
            Stack<GraphGenerator.Node> path = new Stack<>();
            while (n != fatherMap.get(n)) {
                path.add(n);
                n = fatherMap.get(n);
            }

            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), n);
            }

            return n;
        }

        public boolean isSameSet(GraphGenerator.Node a, GraphGenerator.Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(GraphGenerator.Node a, GraphGenerator.Node b) {
            if (a == null || b == null) {
                return;
            }

            GraphGenerator.Node aDai = findFather(a);
            GraphGenerator.Node bDai = findFather(b);

            if (aDai != bDai) {
                int aSetSize = sizeMap.get(aDai);
                int bSetSize = sizeMap.get(bDai);

                if (aSetSize <= bSetSize) {
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }

    public static class EdgeComparator implements Comparator<GraphGenerator.Edge> {
        @Override
        public int compare(GraphGenerator.Edge o1, GraphGenerator.Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<GraphGenerator.Edge> kruskalMST(GraphGenerator.Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        PriorityQueue<GraphGenerator.Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        for (GraphGenerator.Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }

        Set<GraphGenerator.Edge> result = new HashSet<>();
        while(!priorityQueue.isEmpty()) {
            GraphGenerator.Edge edge = priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }

        return result;
    }
}
```

1. ## Prim算法

1. # 贪心

1. ## 笔试过程解题思路

1. 实现一个不依靠贪心策略的算法X，可以直接暴力求解；
2. 脑补出贪心策略A、贪心策略B...；
3. 用算法X和对数器来验证每一个贪心策略，用实验的方式得知那个贪心策略的正确性；
4. 不要去纠结贪心策略的证明。

1. ## 会议时间安排问题

会议结束时间早的先安排

![img](README.assets/-16787840252498.assets)

```Java
public static class Program {
    public int start;
    public int end;

    public Program(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public static class ProgramComparator implements Comparator<Program> {
    @Override
    public int compare(Program p1, Program p2) {
        return p1.end - p2.end;
    }
}

public static int bestArrange(Program[] programs, int timePoint) {
    Arrays.sort(programs, new ProgramComparator());
    int result = 0;

    // 依次遍历所有的会议
    for (int i = 0; i < programs.length; i++) {
        if (timePoint <= programs[i].start) {
            result++;
            timePoint = programs[i].end;
        }
    }

    return result;
}
```

1. ## 拼接字符串生成最小字典序问题

str1和`str2`两个字符串结合，比较`str1 + str2`和`str2 + str1`两个合并后的字符串字典序的大小，小的放在前面。

排序策略的传递性：

`a + b <= b + a` && `b + c <= c + b` ==> `a + c <= c + a`（`a`和`b`结合，`a`放在前面，`b`和`c`结合，`b`放在前面，所以`a`和`c`结合，`a`放在前面）；

```Java
public static class MyComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
        return (a + b).compareTo(b + a);    // compareTO(); 返回字典序；前一个数字典序低，返回负数，后一个数字典序低，返回正数。
    }
}

public static String lowestLexicography(String[] strs) {
    if (strs == null || strs.length == 0) {
        return "";
    }
    Arrays.sort(strs, new MyComparator());
    String res = "";
    for (int i = 0; i < strs.length; i++) {
        res += strs[i];
    }

    return res;
}
```

1. ## 金条切割问题（哈夫曼编码问题）

```Java
public static int lessMoney(int[] arr) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();

    for (int i = 0; i < arr.length; i++) {
        heap.add(arr[i]);
    }

    int sum = 0;    // 总代价
    int cur = 0;    // 结合小根堆中弹出的两个数字
    while (heap.size() > 1) {
        cur = heap.poll() + heap.poll();
        sum += cur;
        heap.add(cur);
    }

    return sum;
}
```

1. ## 项目分配问题（串行）

![img](README.assets/-16787840252499.assets)

```Java
public class IPO {
    public static class Node{
        // 利润
        public int p;

        // 花费
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] Profits, int [] Capital) {
        // 花费的小根堆（被锁池）
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());

        // 利润的大根堆
        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());

        // 将所有项目放入被锁池中，构成小根堆
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Node(Profits[i], Capital[i]));
        }


        for (int i = 0; i < k; i++) {
            // 被锁池中能被解锁的项目进行解锁，解锁后放入利润的大根堆中（heap.peek(); 检索或获取第一个或栈顶元素，被检索的元素不会被删除）
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= w) {
                maxProfitQ.add(minCostQ.poll());
            }

            // 小根堆的根就不满足，那么所有的项目都不满足
            if (maxProfitQ.isEmpty()) {
                return w;
            }

            // 一次只能做一个项目，所以每次只需要读取大根堆中的根元素；
            w += maxProfitQ.poll().p;
        }

        return w;
    }
}
```

1. ## N皇后问题

时间复杂度：O(N ^ N)

```Java
public class NQueens {
    public static void main(String[] args) {
        System.out.println(num1(4));
    }


    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }

        // record[i]: 第i行的皇后放在了第几列;
        int[] record = new int[n];
        return process1(0, record, n);
    }

    /**
     *
     * @param i 目前来到了第i行
     * @param record record[0 ... i-1]表示之前的行放置的皇后
     * @param n 整体共有多少行
     * @return 总共多少种合法的摆法
     */
    public static int process1(int i, int[] record, int n) {
        if (i == n) {   // 终止行：n-1行下面的一行
            return 1;
        }

        int res = 0;
        for (int j = 0; j < n; j++) {   // 当前在第i行，尝试第i行所有的列j（i从0开始）
            if (isValid(record, i, j)) {    // 判断当前第i个皇后能否放在第i行第j列（i从0开始）
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }

        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {   // 遍历0到i-1行所有的皇后;
            // Math.abs(a): a的绝对值;
            if (j == record[k] || Math.abs(j - record[k]) == Math.abs(i - k)) {
                return false;
            }
        }

        return true;
    }
}
```

1. # Dijkstra

```Java
// 改进后算法
public static HashMap<Node, Integer> dijkstra(Node head, int size) {
    NodeHeap nodeHeap = new NodeHeap(size);
    nodeHeap.addOrUpdateOrIgnore(head, 0);
    HashMap<Node, Integer> result = new HashMap<>();
    
    while (!nodeHeap.isEmpty()) {
        NodeRecord record = nodeHeap.pop();
        Node cur = record.node;
        int distance = record.distance;
        
        for (Edge edge : cur.edges) {
            nodeHeap.addOrUpdateOrIgnore(edfe.to, edge.weight + distance);
        }
        
        result.put(cur, distance);
    }
    
    return result;
}

public static class NodeHeap {
    private Node[] nodes;
    private HashMap<Node, Integer> heapIndexMap;
    private HashMap<Node, Integer> distanceMap;
    private int size;
    
    public NodeHeap(int size) {
        nodes = new Node[size];
        
    }
}
```

1. # 暴力递归

1. ## Hanoi

```Java
public static void hanoi(int n) {
    if (n > 0) {
        func (n, "左", "右", "中");
    }
}

public static void func(int n, String start, String end, String other) {
    if (i == 1) {
        System.out.println("Move 1 from" + start + "to" + end);
    } else {
        func (i - 1, "start", "other", "end");
        System.out.println("Move" + i + "from" + start + "to" + end);
        func (i - 1, "other", "end", "start");
    }
}
```

1. ## 打印一个字符串的全部子序列（子序列可以无序）

```Java
public static void printAllSubsquenceNew(String str) {
    char[] chs = str.toCharArray();
    
    return process(chs, 0);
}

public static void process(char[] str, int i) {
    if (i == str.length) {
        System.out.println(String.valueOf(str));
       return;
    }
    
    process(str, i + 1);
    
    char tmp = str[i];
    str[i] = 0;
    process(str, i + 1);
    str[i] = tmp;    // 还原到原来的样子
}
```

1. ## 打印一个字符串的全排列（考虑重复问题）

```Java
public static void process(char[] str, int i, ArrayList<String> res) {
    if (i == str.length) {
        res.add(Stirng.valueOf(str));
    }
    
    boolean[] visit = new boolean[26]
    for (int j = i; j < str.length; j++) {
        if (!visit[str - 'a']) {
            visit[str - 'a'] = true;
    
            swap(str, i, j);
            process(str, i + 1, res);
            swap(str, i, j);
        }
    }
}
```

1. ## 纸牌分数最大问题

```Java
public static int win(int[] arr) {
    if (arr == null || arr.length == 0) {
        return 0;
    }
    
    return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
}

/*
递归的拿，不要纠结一个一个的拿
*/

// 先手拿
public static int f(int[] arr, int l, int r) {
    if (l == r) {
        return arr[l];
    }
    
    return Math.max(arr[l] + s(arr, l + 1, r), arr[r] + s(arr, l, r + 1));
}

// 后手拿
public static int s(int[] arr, int l, int r) {
    if (l == r) {
        return 0;
    }
    
    // 最大的已经被先手拿了，所以只能拿最小的
    return Math.min(f(arr, l + 1, r), f(arr, l, r - 1));
}
```

1. ## 栈逆序问题

给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归。

```Java
public class ReverseStackUsingRecursive {
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverseStack(stack);
        stack.push(i);
    }


    // 栈底上方的元素相对顺序不变，移除栈底元素并返回，
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);

            return last;
        }
    }
}
```

1. ## 数字转化为字符问题

![img](README.assets/-167878402524910.assets)

```Java
public class ConverToLetterString {


    public static int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }

        if (str[i] == '0') {
            return 0;
        }

        if (str[i] == '1') {
            // i自己作为单独的部分，计算第i + 1开始有多少种方法
            int res = process(str, i + 1);

            if (i + 1 < str.length) {
                // i和i + 1两位作为单独的部分，计算第i + 2开始有多少种方法
                res += process(str, i + 2);
            }

            return res;
        }

        if (str[i] == '2') {
            int res = process(str, i + 1);

            if (i + 1 < str.length && (str[i + 1] >= '0' && str[i + 1] <= '6')) {
                res += process(str, i + 2);
            }
        }

        // 字符是3-9的时候：
        return process(str, i + 1);
    }
}
```

 

1. ## 装载价值最多问题

![img](README.assets/-167878402524911.assets)

```Java
public class Knapsack {

    public static int process(int[] weights, int[] values, int i, int alreadyweight, int bag) {
        if (alreadyweight > bag) {
            return 0;
        }

        if (i == weights.length) {
            return 0;
        }

        return Math.max(0 + process(weights, values, i + 1, alreadyweight, bag),
                weights[i] + process(weights, values, i + 1, weights[i] + alreadyweight, bag));
    }
}
```

1. ## 象棋马走日问题

1. 递归

```Java
public static int process(int x, int y, int step) {
    if (x < 0 || x > 8 || y < 0 || y > 9) {
        return 0;
    }

    if (step == 0) {
        return (x == 0 && y == 0) ? 1 : 0;
    }

    return process(x + 2, y + 1, step - 1)
            + process(x + 1, y + 2, step - 1)
            + process(x + 2, y - 1, step - 1)
            + process(x - 1, y + 2, step - 1)
            + process(x - 2, y + 1, step - 1)
            + process(x + 1, y - 2, step - 1)
            + process(x - 2, y - 1, step - 1)
            + process(x - 1, y - 2, step - 1);
}
```

1. 动态规划

```Java
public static int dpWays(int x, int y, int step) {
    if (x < 0 || x > 8 || y < 0 || y > 9 || step < 0) {
        return 0;
    }

    int[][][] dp = new int[9][10][step + 1];
    dp[0][0][0] = 1;

    for (int h = 1; h <= step; h++) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 10; c++) {
                dp[r][c][h] += getValue(dp, r - 1, c + 2, h - 1);
                dp[r][c][h] += getValue(dp, r + 1, c + 2, h - 1);
                dp[r][c][h] += getValue(dp, r + 2, c + 1, h - 1);
                dp[r][c][h] += getValue(dp, r + 2, c + 2, h - 1);
                dp[r][c][h] += getValue(dp, r + 1, c + 2, h - 1);
                dp[r][c][h] += getValue(dp, r - 1, c + 2, h - 1);
                dp[r][c][h] += getValue(dp, r - 2, c + 2, h - 1);
                dp[r][c][h] += getValue(dp, r - 2, c + 2, h - 1);
            }
        }
    }

    return dp[x][y][step];
}

public static int getValue(int[][][] dp, int row, int col, int step) {
    if (row < 0 || row > 8 || col < 0 || col > 9) {
        return 0;
    }

    return dp[row][col][step];
}
```

1. ## Bob活下来的概率问题

```Java
// n * m 的区域，Bob在(row, col)位置，走rest步之后获得的生存方法数
public static String bob1(int n, int m, int i, int j, int k) {
    long all = (long)Math.pow(4, k);    // 所有的方法数
    long live = procs(n, m, i, j, k);   // 能活下来的方法数
    long gcd(all, live);                // 求最大公约数

    return String.valueOf((live / gcd) + "/" + (all / gcd));
}

public static long process(int n, int m, int row, int rest) {
    if (row < 0 || row >= n || col < m || col >= m) {
        return 0;
    }
    
    if (rest == 0) {
        return 1;
    }
    
    long live = process(n, m, row + 1, col, rest - 1);
    live += process(n, m, row - 1, col, rest - 1);
    live += process(n, m, row, col + 1, rest - 1);
    live += process(n, m, row, col - 1, rest - 1);
    
    return live;
}
```

1. ## 货币方法数问题

给定一个数组，数组中所有的数为正数，每个位置的值代表货币的面值，每种面值的货币任意张。求组成一定钱数的方法数。

1. 递归

```Java
public static int coinWay(int[] arr, int aim) {
    return process(arr, 0, aim);
}

public static int process(int[] arr, int index, int rest) {
    // 没有钱可以选了
    if (index == arr.length) {
        return rest == 0 ? 1 : 0;
    }
    
    int ways = 0;
    for (int i = 0; arr[index] * i <= rest; i++) {
        ways += process(arr, index + 1, rest - arr[index] * i);
    }
    
    return  ways;
}
```

1. 动态规划（枚举行为，没有必要）

```Java
public static int way2(int[] arr, int aim) {
    if (arr == null || arr.length == 0) {
        return 0;
    }
    
    int N = arr.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 1;
    
    for (int index = N - 1; index >= 0; index--) {    // 行
        for (int rest = 0; rest <= aim; rest++) {     // 列
            int ways = 0;
            for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {    // 张数
                ways += dp[index + 1][rest - arr[index] * zhang];
            }
            
            dp[index][rest] = ways;
        }
    }
    
    return dp[0][aim];
}
```

1. 动态规划（改进版，斜率优化。观察能不能替代枚举行为）

```Java
public static int wayNew2(int[] arr, int aim) {
    if (arr == null || arr.length == 0) {
        return 0;
    }
    
    int N = arr.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 1;
    
    for (int index = N - 1; index >= 0; index--) {
        for (int rest = 0; rest <= aim; rest++) {
            dp[index][rest] = dp[index + 1][rest];
            if (rest - arr[index] >= 0) {
                dp[index][rest] += dp[index][rest - arr[index]];
            }
        }
    }
    
    return dp[0][aim];
}
```

1. ## 0-1背包

- 非递归

```Java
public static void bagProblem(int[] weight, int[] value, int bagSize) {
    int[][] dp = new int[weight.length][bagSize + 1];
    
    for (int i = weight[0]; i <= bagSize; i++) {
        dp[0][i] = value[0];
    }
    
    for (int i = 1; i < weight.length; i++) {
        for (int j = 1; j <= bagSize; j++) {
            if (weight[i] > j) {
                dp[i][j] = dp[i - 1][j];
            } else {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
            }
            
        }
    }
}
```

- 递归

```Java
public static void main(String[] args) {
    process(w, v, n, V);
}

public static int process(int[] w, int[] v, int n, int V) {    // n:物品数量，V:背包容量
    if（n == 0 || V == 0） {
        return 0;
    } else {
        if (w[n - 1] > V) {
            process(w, v, n - 1, V);
        } else {
            int tem1 = process(w, v, n - 1, V);
            int tem2 = process(w, v, n - 1, V - w[n - 1]);
            
            return tem1 > tem2 ? tem1 : tem2;
        }
    }
}
```

1. ## 总结

暴力递归改动态规划：

1. 第一步写出暴力递归；
2. 第二步根据暴力递归改成枚举版动态规划；
3. 改写枚举版动态规划是，可变参数尽可能的少，尽可能的简单；
4. 把枚举版动态规划进行优化，主要观察能不能替代或者优化枚举行为；

注意：

- 先保证：单个可变参数的维度最好是整数。如果是数组，就是坐牢！再保证：可变参数少；
- 一个可变参数，就是一维表；
- 两个可变参数就是二维表；
- 三个可变参数就是立方体；