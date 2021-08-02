package algorithms.curated170.medium.killprocess;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KillProcessBFS {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, HashSet<Integer>> parentChildMap = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            int par = ppid.get(i);
            if (par == 0) {
                if(pid.get(i) == kill)
                {
                    return pid;
                }
                continue;
            }
            int chi = pid.get(i);
            parentChildMap.putIfAbsent(par, new HashSet<>());
            parentChildMap.get(par).add(chi);
        }

        List<Integer> pTK = new LinkedList<>();
        Queue<Integer> toKill = new LinkedList<>();
        toKill.offer(kill);
        while (!toKill.isEmpty()) {
            int killed = toKill.poll();
            pTK.add(killed);
            if (parentChildMap.containsKey(killed)) {
                toKill.addAll(parentChildMap.get(killed));
            }
        }
        return pTK;
    }

    public static void main(String[] args) {
        var solution = new KillProcessBFS();
        var pid = List.of(1, 3, 10, 5);
        var ppid = List.of(3, 0, 5, 3);
        var k2 = solution.killProcess(pid, ppid, 3);
        System.out.println(k2);
    }
}
