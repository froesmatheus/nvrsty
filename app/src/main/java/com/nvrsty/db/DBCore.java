package com.nvrsty.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Matheus on 02/10/2015.
 */
public class DBCore extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "Nvrsty";
    private static final int VERSAO_BANCO = 1;

    public static final String TABELA_DISCIPLINAS = "Disciplinas";
    public static final String TABELA_HORARIOS = "Horarios";
    public static final String TABELA_EVENTOS = "Eventos";
    public static final String TABELA_HORARIOS_EVENTO = "Horarios_Eventos";

    // Colunas da tabela Disciplinas
    public static final String COLUNA_DISCIPLINAS_ID = "_id";
    public static final String COLUNA_DISCIPLINAS_NOME_DISCIPLINA = "nome_disciplina";
    public static final String COLUNA_DISCIPLINAS_NOME_PROFESSOR = "nome_professor";
    public static final String COLUNA_DISCIPLINAS_SIGLA = "sigla";
    public static final String COLUNA_DISCIPLINAS_COR = "cor";
    public static final String COLUNA_DISCIPLINAS_UNIDADES = "qt_unidades";
    public static final String COLUNA_DISCIPLINAS_FREQUENCIA = "frequencia";

    // Colunas da tabela Horarios
    public static final String COLUNA_HORARIOS_ID = COLUNA_DISCIPLINAS_ID;
    public static final String COLUNA_HORARIOS_DIA_SEMANA = "dia_semana";
    public static final String COLUNA_HORARIOS_HORARIO_INICIO = "horario_inicio";
    public static final String COLUNA_HORARIOS_HORARIO_FIM = "horario_fim";
    public static final String COLUNA_HORARIOS_BLOCO = "bloco";
    public static final String COLUNA_HORARIOS_SALA = "sala";
    public static final String COLUNA_HORARIOS_ID_DISCIPLINA = "id_disciplina";

    // Colunas da tabela Eventos
    public static final String COLUNA_EVENTOS_ID = COLUNA_DISCIPLINAS_ID;
    public static final String COLUNA_EVENTOS_OBSERVACOES = "observacoes";
    public static final String COLUNA_EVENTOS_ID_DISCIPLINA = "id_disciplina";
    public static final String COLUNA_EVENTOS_TIPO = "tipo_evento";
    public static final String COLUNA_EVENTOS_ID_HORARIO_EVENTO = "id_horario_evento";

    // Colunas da tabela Horarios_Eventos
    public static final String COLUNA_HORARIOS_EVENTOS_ID = COLUNA_EVENTOS_ID;
    public static final String COLUNA_HORARIOS_EVENTOS_DATA = "data";
    public static final String COLUNA_HORARIOS_EVENTOS_BLOCO = "bloco";
    public static final String COLUNA_HORARIOS_EVENTOS_SALA = "sala";


    private static final String CREATE_TABLE_HORARIOS_EVENTOS = "CREATE TABLE " + TABELA_HORARIOS_EVENTO + "(" +
            COLUNA_HORARIOS_EVENTOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUNA_HORARIOS_EVENTOS_BLOCO + " TEXT NOT NULL," +
            COLUNA_HORARIOS_EVENTOS_SALA + " TEXT NOT NULL," +
            COLUNA_HORARIOS_EVENTOS_DATA + " TEXT NOT NULL);";


    private static final String CREATE_TABLE_EVENTOS = "CREATE TABLE " + TABELA_EVENTOS + "(" +
            COLUNA_EVENTOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUNA_EVENTOS_OBSERVACOES + " TEXT NOT NULL," +
            COLUNA_EVENTOS_TIPO + " TEXT NOT NULL," +
            COLUNA_EVENTOS_ID_DISCIPLINA + " INTEGER NOT NULL," +
            COLUNA_EVENTOS_ID_HORARIO_EVENTO + " INTEGER NOT NULL," +
            " FOREIGN KEY(" + COLUNA_EVENTOS_ID_DISCIPLINA + ") REFERENCES Disciplinas(" + COLUNA_DISCIPLINAS_ID + ")," +
            " FOREIGN KEY(" + COLUNA_EVENTOS_ID_HORARIO_EVENTO + ") REFERENCES Horarios_Eventos(" + COLUNA_HORARIOS_EVENTOS_ID + ")" + ");";


    private static final String CREATE_TABLE_DISCIPLINAS = "CREATE TABLE " + TABELA_DISCIPLINAS + "(" +
            COLUNA_DISCIPLINAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUNA_DISCIPLINAS_NOME_DISCIPLINA + " TEXT NOT NULL," +
            COLUNA_DISCIPLINAS_SIGLA + " TEXT NOT NULL," +
            COLUNA_DISCIPLINAS_NOME_PROFESSOR + " TEXT NULL, " +
            COLUNA_DISCIPLINAS_COR + " INTEGER NOT NULL, " +
            COLUNA_DISCIPLINAS_UNIDADES + " INTEGER NOT NULL, " +
            COLUNA_DISCIPLINAS_FREQUENCIA + " INTEGER NOT NULL" +
            ");";


    private static final String CREATE_TABLE_HORARIOS = "CREATE TABLE " + TABELA_HORARIOS + "(" +
            COLUNA_HORARIOS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUNA_HORARIOS_DIA_SEMANA + " INTEGER NOT NULL," +
            COLUNA_HORARIOS_HORARIO_INICIO + " TEXT NOT NULL," +
            COLUNA_HORARIOS_HORARIO_FIM + " TEXT NOT NULL," +
            COLUNA_HORARIOS_BLOCO + " TEXT NULL," +
            COLUNA_HORARIOS_SALA + " TEXT NULL," +
            COLUNA_HORARIOS_ID_DISCIPLINA + " INTEGER NOT NULL," +
            " FOREIGN KEY(" + COLUNA_HORARIOS_ID_DISCIPLINA + ") REFERENCES Disciplinas(" + COLUNA_DISCIPLINAS_ID + ")" + ");";

    public DBCore(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DISCIPLINAS);
        db.execSQL(CREATE_TABLE_HORARIOS);
        db.execSQL(CREATE_TABLE_HORARIOS_EVENTOS);
        db.execSQL(CREATE_TABLE_EVENTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABELA_DISCIPLINAS + ";");
        db.execSQL("DROP TABLE " + TABELA_HORARIOS + ";");
        db.execSQL("DROP TABLE " + TABELA_EVENTOS + ";");
        db.execSQL("DROP TABLE " + TABELA_HORARIOS_EVENTO + ";");
        onCreate(db);
    }
}
