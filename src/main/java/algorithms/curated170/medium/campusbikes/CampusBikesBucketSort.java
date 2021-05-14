package algorithms.curated170.medium;

import java.util.ArrayList;
import java.util.List;

public class CampusBikesBucketSort {

      int w, b;
      List<int[]>[] buckets = new List[2001];

      public int[] assignBikes(int[][] workers, int[][] bikes) {

            w = workers.length;
            b = workers.length;

            assignToBuckets(workers, bikes);

            return assignFromBuckets();
      }

      private void assignToBuckets(int[][] workers, int[][] bikes) {
            for (int i = 0; i < w; i++) {
                  for (int j = 0; j < b; j++) {
                        int dist = dist(workers[i], bikes[j]);
                        if (buckets[dist] == null) {
                              buckets[dist] = new ArrayList<>();
                        }
                        buckets[dist].add(new int[] { i, j });
                  }
            }
      }

      private int[] assignFromBuckets() {
            int[] wo = new int[w];
            boolean[] bikeAssigned = new boolean[w];
            boolean[] workerAssigned = new boolean[b];
            short assigned = 0;

            for (int i = 0; i < buckets.length && assigned <= w; i++) {
                  if (buckets[i] != null) {
                        for (int[] pair : buckets[i]) {

                              int w_ = pair[0];
                              int b_ = pair[1];

                              if (!workerAssigned[w_] && !bikeAssigned[b_]) {
                                    wo[w_] = pair[b_];

                                    bikeAssigned[b_] = true;
                                    workerAssigned[w_] = true;
                                    assigned++;
                              }
                        }
                  }
            }
            return wo;
      }

      private int dist(int[] w, int[] b) {
            return Math.abs(w[0] - b[0]) + Math.abs(w[1] - b[1]);
      }

      public static void main(String[] args) {

      }
}