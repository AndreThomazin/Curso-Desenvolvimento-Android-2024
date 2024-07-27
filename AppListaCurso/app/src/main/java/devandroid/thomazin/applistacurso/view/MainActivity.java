package devandroid.thomazin.applistacurso.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import devandroid.thomazin.applistacurso.R;
import devandroid.thomazin.applistacurso.controller.PessoaController;
import devandroid.thomazin.applistacurso.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;

    public static final String NOME_PREFERENCES = "pref_listavip";

    PessoaController controller;

    Pessoa pessoa;

    EditText editPrimeiroNome;
    EditText editSobreNome;
    EditText editNomeCurso;
    EditText editTelefoneContato;

    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        preferences = getSharedPreferences(NOME_PREFERENCES, 0);
        listaVip = preferences.edit();

        controller = new PessoaController();
        controller.toString();

        pessoa = new Pessoa();
        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", "NA"));
        pessoa.setSobreNome(preferences.getString("sobreNome", "NA"));
        pessoa.setCursoDesejado(preferences.getString("nomeCurso", "NA"));
        pessoa.setTelefoneContato(preferences.getString("telefoneContato", "NA"));

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobreNome = findViewById(R.id.editSobrenome);
        editNomeCurso = findViewById(R.id.editNomeCurso);
        editTelefoneContato = findViewById(R.id.editTelefoneContato);

        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSobreNome.setText(pessoa.getSobreNome());
        editNomeCurso.setText(pessoa.getCursoDesejado());
        editTelefoneContato.setText(pessoa.getTelefoneContato());

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPrimeiroNome.setText("");
                editSobreNome.setText("");
                editNomeCurso.setText("");
                editTelefoneContato.setText("");

                listaVip.clear();
                listaVip.apply();

                Log.i("Button Limpar", "Comando limpar funcionou!!");
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Volte sempre", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoa.setSobreNome(editSobreNome.getText().toString());
                pessoa.setCursoDesejado(editNomeCurso.getText().toString());
                pessoa.setTelefoneContato(editTelefoneContato.getText().toString());

                Toast.makeText(MainActivity.this, "Salvo " + pessoa.toString(), Toast.LENGTH_LONG).show();

                listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
                listaVip.putString("sobreNome", pessoa.getSobreNome());
                listaVip.putString("nomeCurso", pessoa.getCursoDesejado());
                listaVip.putString("telefoneContato", pessoa.getTelefoneContato());
                listaVip.apply();

                controller.salvar(pessoa);
            }
        });

        Log.i("POOAndroid", "Objeto pessoa: " + pessoa.toString());
    }
}