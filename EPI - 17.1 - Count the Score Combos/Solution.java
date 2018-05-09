import java.util.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println(getScoreCombos(3, 2, 7, 16));
    }

    public static int getScoreCombos(int a, int b, int c, int score) {
        int matchesCount = get3Plays(a, b, c, score);
        matchesCount += get2Plays(a, b, score);
        matchesCount += get2Plays(b, c, score);
        matchesCount += get2Plays(a, c, score);
        if(get1Play(a, score))
            ++matchesCount;
        if(get1Play(b, score))
            ++matchesCount;
        if(get1Play(c, score))
            ++matchesCount;
        return matchesCount;
    }

    private static int get3Plays(int x, int y, int z, int score) {
        HashSet<Integer> cache = new HashSet<>();
        for(int ySum=y; ySum < score; ySum += y) {
            for(int zSum=z; zSum + ySum < score; zSum += z) {
                // stop at '<', since caching 'zSum + ySum = score' never
                // results in a match when matching xSum against zSum|ySum cache
                cache.add(zSum + ySum);
            }
        }
        int matchesCount = 0;
        for(int xSum=x; xSum < score; xSum += x) {
            int scoreDiff = score - xSum;
            if(cache.contains(scoreDiff))
                ++matchesCount;
        }
        return matchesCount;
    }

    private static int get2Plays(int y, int z, int score) {
        int matchesCount = 0;
        for(int ySum=y; ySum < score; ySum += y) {
            for(int zSum=z; zSum + ySum <= score; zSum += z) {
                if(zSum + ySum == score)
                    ++matchesCount;
            }
        }
        return matchesCount;
    }

    // could also just divide score by x, and see if integer returns..
    private static boolean get1Play(int x, int score) {
        for(int xSum=x; xSum <= score; xSum += x) {
            if(xSum == score)
                return true;
        }
        return false;
    }

}
