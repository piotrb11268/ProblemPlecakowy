package com.company;

import java.io.*;
import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static int[] zbior(List<Integer> indeksy, int[] z) {
        int[] result = new int[z.length];
        for (int i = 0; i < z.length; i++) {
            result[i] = indeksy.get(z[i]);
        }
        return result;
    }

    public static List<int[]> kombinacje(List<Integer> indeksy, int k){

        List<int[]> zbiory = new ArrayList<>();

        int[] s = new int[k];

        if (k <= indeksy.size()) {
            for (int i = 0; (s[i] = i) < k - 1; i++);
            zbiory.add(zbior(indeksy, s));
            while(true) {
                int i;
                for (i = k - 1; i >= 0 && s[i] == indeksy.size() - k + i; i--);
                if (i < 0) {
                    break;
                }
                s[i]++;
                for (++i; i < k; i++) {
                    s[i] = s[i - 1] + 1;
                }
                zbiory.add(zbior(indeksy, s));
            }
        }

        return zbiory;
    }

    static List<Przedmiot> pakuj(List<Przedmiot> przedmioty, int maxw){
        ArrayList<Integer> indeksy = new ArrayList<Integer>();
        ArrayList<Integer> ilosci = new ArrayList<Integer>();

        for(int i =0; i< przedmioty.size(); i++){
            indeksy.add(i);
            if(i>0){
                ilosci.add(i);
            }
        }
        ilosci.add(przedmioty.size());

        ArrayList<ArrayList<Przedmiot>> warianty = new ArrayList<>();

        for (int il : ilosci) {
            List<int[]> c = kombinacje(indeksy,il);

            for(int[] ci : c){
                ArrayList<Przedmiot> w = new ArrayList<>();

                for(int i : ci){
                    w.add(przedmioty.get(i));
                }

                int waga = w.stream().mapToInt(m->m.getWaga()).sum();

                if(waga <= maxw) {
                    warianty.add(w);
                }
            }
        }

        int max = Integer.MIN_VALUE;

        for(ArrayList<Przedmiot> w : warianty){
            int waga = w.stream().mapToInt(m->m.getWaga()).sum();

            if(waga >= max){
                max = waga;
            }
        }

        ArrayList<ArrayList<Przedmiot>> optymalne = new ArrayList<>();

        for(ArrayList<Przedmiot> w : warianty){
            int waga = w.stream().mapToInt(m->m.getWaga()).sum();

            if(waga == max){
                optymalne.add(w);
            }
        }

        int min = Integer.MAX_VALUE;
        ArrayList<Przedmiot> optimum = null;

        for(ArrayList<Przedmiot> w : optymalne){
            int wartosc = w.stream().mapToInt(m->m.getWartosc()).sum();

            if(wartosc < min){
                min = wartosc;
                optimum = w;
            }
        }

        return optimum;
    }

    public static List<Przedmiot> readData() throws IOException {
        ArrayList<Przedmiot> data = new ArrayList<Przedmiot>();



        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                data.add(new Przedmiot(Integer.parseInt(parts[0]),Integer.parseInt(parts[1])));
            }
        }

        return data;
    }


    public static void main(String[] args) throws IOException {


        List<Przedmiot> przedmioty = readData();

        int w = 50;
        int n = przedmioty.size();

       List<Przedmiot> optimum = pakuj(przedmioty,w);

        System.out.println("Najlepszy wariant:");

       for(Przedmiot p : optimum){
           System.out.println(p);
       }

    }
}
