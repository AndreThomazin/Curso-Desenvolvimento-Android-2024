package devandroid.thomazin.appgaseta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import devandroid.thomazin.appgaseta.model.Combustivel;

public class GasEtaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "gaseta.db";
    private static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;


    public GasEtaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // QUERY SQL para criar uma tabela

        String sqlTabelaCombustivel = "CREATE TABLE Combustivel (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nomeDoCombustivel TEXT, " +
                "precoDoCombustivel REAL, " +
                "recomendacao TEXT)";

        db.execSQL(sqlTabelaCombustivel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void salvarObjeto(String tabela,
                             ContentValues dados){

        db.insert(tabela, null, dados);

    }

    public List<Combustivel> listarDados(){

        List<Combustivel> lista = new ArrayList<>();

        // Representa um registro que está salvo na tabela
        // Combutivel do Banco de Dados da Aplicação

        Combustivel registro;

        String querySQL = "SELECT * FROM Combustivel";

        cursor = db.rawQuery(querySQL, null);

        if (cursor.moveToFirst()){
            // True
            // TODO: Contar registros SQL "SELECT COUNT(*) FROM Combustivel"
            do {

                registro = new Combustivel();

                registro.setId(cursor.getInt(0));
                registro.setNomeDoCombustivel(cursor.getString(1));
                registro.setPrecoDoCombustivel(cursor.getDouble(2));
                registro.setRecomendacao(cursor.getString(3));

                lista.add(registro);


            }while (cursor.moveToNext());

        }else{
            // False
        }

        return lista;

    }

    public void alterarObjeto(String tabela,
                              ContentValues dados){

        // ID do registro a ser alterado (PK)
        // update TABLE set campo=novoDado WHERE id = ?

        int id = dados.getAsInteger("id");

        db.update(tabela, dados,"id=?",
                new String[]{Integer.toString(id)});

    }

    public void deletarObjeto(String tabela,
                              int id){

        // ID do registro a ser alterado (PK)
        // delete from TABLE WHERE id = ?

        db.delete(tabela, "id=?",
                new String[]{Integer.toString(id)});

    }
    // Criar métodos para implementar um CRUD
    // C = Create criar banco de dados e as tabelas
    // Create database nome_do_banco_de_dados.db (SQL)
    // 1 - Nome do Banco de Dados
    // 2 - Versão do Banco de Dados



    // Create table (SQL)


    // R = Retrieve recuperar os dados salvos nas tabelas
    // Select * from table (SQL)

    // U = Update alterar dados que já existem em um registro na tabela
    // Update from table (SQL)

    // D = Delete deletar os dados/registros de uma tabela
    // Delete from (SQL)

}
