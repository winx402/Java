package com.winx.arithmetic;

import com.google.common.collect.Sets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.Random;
import java.util.Set;

public class BloomFilterTest {

    private static int count = 100000000;

    private static BloomFilter bloomFilter = BloomFilter.create(Funnels.integerFunnel(), count, 0.01);

    private static int bloomFilterRepeatCount = 0;

    private static Set<Integer> set = Sets.newHashSetWithExpectedSize(count);

    private static int repeatCount = 0;

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int i1 = random.nextInt(count);
            bloomFilter(i1);
            setFilter(i1);
            if (i % 10000 == 0){
                System.out.println(i / 10000 + "万");
            }
        }
        System.out.println("bloom filter : " + bloomFilterRepeatCount);
        System.out.println("set filter : " + repeatCount);
    }

    private static void bloomFilter(int i) {
        if (bloomFilter.mightContain(i)) {
            bloomFilterRepeatCount++;
        } else {
            bloomFilter.put(i);
        }
    }

    private static void setFilter(int i) {
        if (!set.add(i)) repeatCount++;
    }
}
