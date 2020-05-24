package android.example.mymonney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MonneyActivity";

    private Calculator mCalculator;

    private EditText mSoldeInitialEditText;
    private EditText mTotalRecettesEditText;
    private EditText mTotalDepensesEditText;

    private TextView mResultat;
    private TextView mTotalInitial;
    private TextView mTotalRecettes;
    private TextView mTotalDepenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalculator = new Calculator();
        mSoldeInitialEditText = findViewById(R.id.solde_initial);
        mTotalInitial = findViewById(R.id.total_initial);
        mTotalRecettesEditText = findViewById(R.id.montant_recettes);
        mTotalRecettes = findViewById(R.id.total_recettes);
        mTotalDepensesEditText = findViewById(R.id.montant_depenses);
        mTotalDepenses = findViewById(R.id.total_depenses);
        mResultat = findViewById(R.id.total);


        Spinner spinnerRecettes = (Spinner) findViewById(R.id.spinner_recettes);
        // Créer un ArrayAdapter en utilisant le tableau de chaînes et une disposition de spinner par défaut
        ArrayAdapter<CharSequence> adapterRecettes = ArrayAdapter.createFromResource(this,
                R.array.recettes_array, android.R.layout.simple_spinner_item);
        // Spécifiez la mise en page à utiliser lorsque la liste de choix apparaît
        adapterRecettes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Appliquer l'adaptateur au spinner
        spinnerRecettes.setAdapter(adapterRecettes);

        Spinner spinnerDepenses = (Spinner) findViewById(R.id.spinner_depenses);
        // Créer un ArrayAdapter en utilisant le tableau de chaînes et une disposition de spinner par défaut
        ArrayAdapter<CharSequence> adapterDepenses = ArrayAdapter.createFromResource(this,
                R.array.depenses_array, android.R.layout.simple_spinner_item);
        // Spécifiez la mise en page à utiliser lorsque la liste de choix apparaît
        adapterDepenses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Appliquer l'adaptateur au spinner
        spinnerDepenses.setAdapter(adapterDepenses);

        final Button recettesButton = (Button)findViewById(R.id.recettes_btn);
        recettesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute(Calculator.Operator.RECETTES);
               // mTotalRecettes.setText(getTotalRecettesText(mTotalRecettesEditText) + " €");
            }
        });

        final Button depensesButton = (Button)findViewById(R.id.depenses_btn);
        depensesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute(Calculator.Operator.DEPENSES);
                //mTotalDepenses.setText(getTotalDepensesText(mTotalDepensesEditText) + " €");
            }
        });
    }

    public void addInitial(View view) {
            compute(Calculator.Operator.INIT);
            mTotalInitial.setText(getSoldeInitText(mSoldeInitialEditText) + " €");
    }
    /** je teste
    public void addRecettes(View view) {
            compute(Calculator.Operator.RECETTES);
            mTotalRecettes.setText(getTotalRecettesText(mTotalRecettesEditText) + " €");
    }*/
    /** je teste
    public void addDepenses(View view) {
            compute(Calculator.Operator.DEPENSES);
            mTotalDepenses.setText(getTotalDepensesText(mTotalDepensesEditText) + " €");
    }*/

    private void compute(Calculator.Operator operator) {
        double soldeInitial = 0;
        double recettes = 0;
        double depenses = 0;
        try {
            soldeInitial = getSoldeInit(mSoldeInitialEditText);
            recettes = getTotalRecettes(mTotalRecettesEditText);
            depenses = getTotalDepenses(mTotalDepensesEditText);
        } catch (NumberFormatException nfe) {
            Log.e(TAG, "NumberFormatException", nfe);
            mResultat.setText("il manque une donnée");
            return;
        }
        String result;
        Double mResult;
        switch (operator) {
            case INIT:
                result = String.valueOf(mCalculator.soldeInitial(soldeInitial));
                mResult = mCalculator.soldeInitial(soldeInitial);
                mTotalInitial.setText(mResult+" €");
                break;
            case RECETTES:
                mResult = mCalculator.soldeInitial(soldeInitial);
                result = String.valueOf(mCalculator.recettes(recettes)+ mResult);
                mTotalRecettes.setText(mCalculator.recettes(recettes)+" €");
                break;
            case DEPENSES:
                mResult = mCalculator.soldeInitial(soldeInitial)+ mCalculator.recettes(recettes);
                result = String.valueOf(mResult-mCalculator.depenses(depenses));
                mTotalDepenses.setText(mCalculator.depenses(depenses)+" €");
                break;

            default:
                result = "erreur";
                break;
        }
        mResultat.setText(result+" €");
    }


    /**
     * @return the operand value entered in an EditText as double.
     */
    private static Double getSoldeInit(EditText soldeInitialText) {
        String montantSoldeInitial = getSoldeInitText(soldeInitialText);
        return Double.valueOf(montantSoldeInitial);
    }

    /**
     * @return the operand text which was entered in an EditText.
     */
    private static String getSoldeInitText(EditText soldeInitialText) {
        return soldeInitialText.getText().toString();
    }

    private static Double getTotalRecettes(EditText totalRecettesText){
        String montantTotalRecettes = getTotalRecettesText(totalRecettesText);
        return Double.valueOf(montantTotalRecettes);
    }

    private static String getTotalRecettesText(EditText totalRecettesText){
        return totalRecettesText.getText().toString();
    }

    private static Double getTotalDepenses(EditText totalDepensesText){
        String montantTotalDepenses = getTotalDepensesText(totalDepensesText);
        return Double.valueOf(montantTotalDepenses);
    }

    private static String getTotalDepensesText(EditText totalDepensesText){
        return totalDepensesText.getText().toString();
    }

}
