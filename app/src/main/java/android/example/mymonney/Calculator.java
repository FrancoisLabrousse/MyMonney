package android.example.mymonney;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Calculator {

    public enum Operator {INIT, RECETTES, DEPENSES}

    public double soldeInitial(double soldeInitial){
        return soldeInitial;
    }

    public double recettes(double recettes){
        ArrayList<Double> recettesList = new ArrayList<>();
        recettesList.add(recettes);

        for (int i = 0 ; i < recettesList.size() ; i++){
            recettes += recettesList.get(i);
        }
        return recettes;
    }

    public double depenses(double depenses){
        ArrayList<Double> depensesList = new ArrayList<>();
        depensesList.add(depenses);

        for (int i = 0 ; i < depensesList.size() ; i++){
            depenses += depensesList.get(i);
        }
        return depenses;
    }


}
