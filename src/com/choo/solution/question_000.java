package com.choo.solution;
import com.choo.entity.Question;

import java.util.*;

public class question_000 {


    static public void main(String[] args) {
        System.out.println("--------------------------------------------------------------------------");
        System.out.println("No.   : 42889");
        System.out.println("title : 실패율");
        System.out.println("Link  : https://school.programmers.co.kr/learn/courses/30/lessons/42889");
        System.out.println("--------------------------------------------------------------------------");

        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};

        /*
        5	[2, 1, 2, 6, 2, 4, 3, 3]	[3,4,2,1,5]
        4	[4,4,4,4,4]	[4,1,2,3]
        */


        solution(N,stages);

    }

    static public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        // Map<Integer, Integer> failMap = new HashMap();
        int[] userFailCnt = new int[N+2]; //각 스테이지에 머물러 있는 플레이어 수
        int[] userTotalCnt = new int[N+1]; //각 스테이지에 머물러 있는 플레이어 수

        Map<Integer, Integer> sumFail = new HashMap();
        Map<Integer, Double> rate = new HashMap();
        int fail = 0;

        for (int stage : stages) {
            //실패 stage 세팅
            userFailCnt[stage]++;
            // failMap.put(stage, failMap.getOrDefault(stage, 0) + 1);
        }

        //스테이지별 도착 인원 = failcnt[i] + totalCnt[i+1]
        userTotalCnt[N] = userFailCnt[N]+userFailCnt[N+1];
        for(int i =N-1; i>=1; i--){
            userTotalCnt[i] = userFailCnt[i]+userTotalCnt[i+1];
        }

        System.out.println("도착 인원 : " + Arrays.toString(userTotalCnt));
        System.out.println("실패 인원 : " + Arrays.toString(userFailCnt));

        for (int i = 1; i < N + 1; i++) {

            if (userTotalCnt[i] != 0) {
//                System.out.println(i + "값  : " + (double)userFailCnt[i]/userTotalCnt[i]);
//                System.out.println(i + "userFailCnt  : " + userFailCnt[i]);
//                System.out.println(i + "userTotalCnt  : " + userTotalCnt[i]);
                // rate.put(i, Double.valueOf(userFailCnt[i] / userTotalCnt[i]));
                rate.put(i, (double)userFailCnt[i]/userTotalCnt[i]);
            }else
                rate.put(i, 0.0);
        }
        System.out.println("rate : "+ rate.toString());

        //실패율 v 순으로 스테이지key를 내림차순 정렬
        List<Map.Entry<Integer, Double>> list = new ArrayList<>(rate.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for(int i = 0; i < list.size(); i++){
            Map.Entry entry  = list.get(i);
            answer[i] = (int) entry.getKey();
        }
        System.out.println("answer : "+ Arrays.toString(answer));

        return answer;


   }
}
