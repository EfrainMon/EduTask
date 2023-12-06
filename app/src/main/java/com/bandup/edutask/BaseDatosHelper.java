package com.bandup.edutask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseDatosHelper extends SQLiteOpenHelper {

    private static final String TAG = "BaseDatosHelper";
    private static final String DB_NAME = "EduTaskDB";
    private static final int DB_VERSION = 1;

    // Nombres de las tablas
    private static final String TABLE_MATERIA = "Materia";
    private static final String TABLE_ALUMNO = "Alumno";
    private static final String TABLE_ASIGNACION = "Asignacion";
    private static final String TABLE_ALUMNO_MATERIA = "Alumno_Materia";
    private static final String TABLE_ALUMNO_ASIGNACION = "Alumno_Asignacion";

    // Columnas de la tabla Alumno_Materia
    private static final String COL_ALUMNO_MATERIA_ID = "ID";
    private static final String COL_ALUMNO_MATERIA_NUM_CONTROL = "NumControl";
    private static final String COL_ALUMNO_MATERIA_CLAVE = "Clave";

    // Columnas de la tabla Alumno_Asignacion
    private static final String COL_ALUMNO_ASIGNACION_ID = "ID";
    private static final String COL_ALUMNO_ASIGNACION_NUM_CONTROL = "NumControl";
    private static final String COL_ALUMNO_ASIGNACION_ID_ASIGNACION = "IDAsignacion";

    // Columnas de la tabla Alumno
    private static final String COL_ALUMNO_ID = "ID";
    private static final String COL_ALUMNO_NUM_CONTROL = "NumControl";
    private static final String COL_ALUMNO_NOMBRE = "Nombre";
    private static final String COL_ALUMNO_A_PATERNO = "aPaterno";
    private static final String COL_ALUMNO_A_MATERNO = "aMaterno";
    private static final String COL_ALUMNO_OCULTO = "Oculto";

    // Columnas de la tabla Asignacion
    private static final String COL_ASIGNACION_ID = "ID";
    private static final String COL_ASIGNACION_FECHA = "Fecha";
    private static final String COL_ASIGNACION_NOMBRE = "Nombre";
    private static final String COL_ASIGNACION_REALIZADA = "Realizada";
    private static final String COL_ASIGNACION_OCULTO = "Oculto";

    // Columnas de la tabla Materia
    private static final String COL_MATERIA_ID = "ID";
    private static final String COL_MATERIA_CLAVE = "Clave";
    private static final String COL_MATERIA_NOMBRE = "Nombre";
    private static final String COL_MATERIA_OCULTO = "Oculto";

    public BaseDatosHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            // Crear la tabla Alumno
            String crearTablaAlumno = "CREATE TABLE IF NOT EXISTS " + TABLE_ALUMNO + " ("
                    + COL_ALUMNO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_ALUMNO_NUM_CONTROL + " TEXT UNIQUE, "
                    + COL_ALUMNO_NOMBRE + " TEXT, "
                    + COL_ALUMNO_A_PATERNO + " TEXT, "
                    + COL_ALUMNO_A_MATERNO + " TEXT, "
                    + COL_ALUMNO_OCULTO + " INTEGER)";
            db.execSQL(crearTablaAlumno);

            // Crear la tabla Materia
            String crearTablaMateria = "CREATE TABLE IF NOT EXISTS " + TABLE_MATERIA + " ("
                    + COL_MATERIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_MATERIA_CLAVE + " TEXT UNIQUE, "
                    + COL_MATERIA_NOMBRE + " TEXT, "
                    + COL_MATERIA_OCULTO + " INTEGER)";
            db.execSQL(crearTablaMateria);

            // Crear la tabla Asignacion
            String crearTablaAsignacion = "CREATE TABLE IF NOT EXISTS " + TABLE_ASIGNACION + " ("
                    + COL_ASIGNACION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_ASIGNACION_FECHA + " TEXT, "
                    + COL_ASIGNACION_NOMBRE + " TEXT, "
                    + COL_ASIGNACION_REALIZADA + " INTEGER, "
                    + COL_ASIGNACION_OCULTO + " INTEGER, "
                    + "Materia_ID INTEGER, "
                    + "FOREIGN KEY(Materia_ID) REFERENCES " + TABLE_MATERIA + "(" + COL_MATERIA_ID + "))";
            db.execSQL(crearTablaAsignacion);

            // Crear la tabla Alumno_Materia
            String crearTablaAlumnoMateria = "CREATE TABLE IF NOT EXISTS " + TABLE_ALUMNO_MATERIA + " ("
                    + COL_ALUMNO_MATERIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_ALUMNO_MATERIA_NUM_CONTROL + " TEXT, "
                    + COL_ALUMNO_MATERIA_CLAVE + " TEXT, "
                    + "Alumno_ID INTEGER, "
                    + "Materia_ID INTEGER, "
                    + "FOREIGN KEY(Alumno_ID) REFERENCES " + TABLE_ALUMNO + "(" + COL_ALUMNO_ID + "), "
                    + "FOREIGN KEY(Materia_ID) REFERENCES " + TABLE_MATERIA + "(" + COL_MATERIA_ID + "))";
            db.execSQL(crearTablaAlumnoMateria);

            // Crear la tabla Alumno_Asignacion
            String crearTablaAlumnoAsignacion = "CREATE TABLE IF NOT EXISTS " + TABLE_ALUMNO_ASIGNACION + " ("
                    + COL_ALUMNO_ASIGNACION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_ALUMNO_ASIGNACION_NUM_CONTROL + " TEXT, "
                    + COL_ALUMNO_ASIGNACION_ID_ASIGNACION + " INTEGER, "
                    + "Alumno_ID INTEGER, "
                    + "Asignacion_ID INTEGER, "
                    + "FOREIGN KEY(Alumno_ID) REFERENCES " + TABLE_ALUMNO + "(" + COL_ALUMNO_ID + "), "
                    + "FOREIGN KEY(Asignacion_ID) REFERENCES " + TABLE_ASIGNACION + "(" + COL_ASIGNACION_ID + "))";
            db.execSQL(crearTablaAlumnoAsignacion);
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Puedes implementar la lógica de actualización de la base de datos si es necesario
        // Por ahora, simplemente borramos todas las tablas y las volvemos a crear
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALUMNO_MATERIA);
        // ... (borrar las otras tablas)
        onCreate(db);
    }

    // Puedes agregar métodos para insertar, actualizar y consultar datos en cada tabla según tus necesidades

    // Ejemplo de método para agregar datos a la tabla Alumno
// Métodos para operaciones en la tabla Alumno
    public boolean addAlumno(String numControl, String nombre, String aPaterno, String aMaterno, int oculto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ALUMNO_NUM_CONTROL, numControl);
        contentValues.put(COL_ALUMNO_NOMBRE, nombre);
        contentValues.put(COL_ALUMNO_A_PATERNO, aPaterno);
        contentValues.put(COL_ALUMNO_A_MATERNO, aMaterno);
        contentValues.put(COL_ALUMNO_OCULTO, oculto);

        long resultado = db.insert(TABLE_ALUMNO, null, contentValues);

        return resultado != -1;
    }

    public Cursor getAlumnos() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ALUMNO + " WHERE " + COL_ALUMNO_OCULTO + " = 0";
        return db.rawQuery(query, null);
    }

    public void deleteAlumno(String numControl) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Actualiza el valor de Oculto a 1 en lugar de eliminar la fila
        ContentValues values = new ContentValues();
        values.put(COL_ALUMNO_OCULTO, 1);

        db.update(TABLE_ALUMNO, values, COL_ALUMNO_NUM_CONTROL + " = ?", new String[]{numControl});

        // Puedes añadir logs o mensajes aquí para indicar que el alumno fue "eliminado"
        Log.d(TAG, "deleteAlumno: Alumno con número de control " + numControl + " oculto (Oculto = 1)");
    }

    // Métodos para operaciones en la tabla Asignacion
    public boolean addAsignacion(String fecha, String nombre, int realizada, int oculto, int materiaID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ASIGNACION_FECHA, fecha);
        contentValues.put(COL_ASIGNACION_NOMBRE, nombre);
        contentValues.put(COL_ASIGNACION_REALIZADA, realizada);
        contentValues.put(COL_ASIGNACION_OCULTO, oculto);
        contentValues.put("Materia_ID", materiaID);

        long resultado = db.insert(TABLE_ASIGNACION, null, contentValues);

        return resultado != -1;
    }

    public Cursor getAsignaciones() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ASIGNACION + " WHERE " + COL_ASIGNACION_OCULTO + " = 0";
        return db.rawQuery(query, null);
    }

    public void deleteAsignacion(int asignacionID) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Actualiza el valor de Oculto a 1 en lugar de eliminar la fila
        ContentValues values = new ContentValues();
        values.put(COL_ASIGNACION_OCULTO, 1);

        db.update(TABLE_ASIGNACION, values, COL_ASIGNACION_ID + " = ?", new String[]{String.valueOf(asignacionID)});

        // Puedes añadir logs o mensajes aquí para indicar que la asignación fue "eliminada"
        Log.d(TAG, "deleteAsignacion: Asignación con ID " + asignacionID + " oculta (Oculto = 1)");
    }

    // Métodos para operaciones en la tabla Materia
    public boolean addMateria(String clave, String nombre, int oculto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MATERIA_CLAVE, clave);
        contentValues.put(COL_MATERIA_NOMBRE, nombre);
        contentValues.put(COL_MATERIA_OCULTO, oculto);

        long resultado = db.insert(TABLE_MATERIA, null, contentValues);

        return resultado != -1;
    }

    public void deleteMateria(String nombre, String clave) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Actualiza el valor de Oculto a 1 en lugar de eliminar la fila
        ContentValues values = new ContentValues();
        values.put(COL_MATERIA_OCULTO, 1);

        // Utiliza 'nombre' en lugar de 'id' en la siguiente línea
        db.update(TABLE_MATERIA, values, COL_MATERIA_NOMBRE + " = ? AND " + COL_MATERIA_CLAVE + " = ?", new String[]{nombre, clave});

        // Puedes añadir logs o mensajes aquí para indicar que la materia fue "eliminada"
        Log.d(TAG, "deleteMateria: Materia con nombre " + nombre + " y clave " + clave + " oculta (Oculto = 1)");
    }

    public Cursor getMaterias() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MATERIA + " WHERE " + COL_MATERIA_OCULTO + " = 0";
        return db.rawQuery(query, null);
    }

    // Dentro de la clase BaseDatosHelper

    public Cursor getAsignacionesParaDia(String fecha) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ASIGNACION +
                " WHERE " + COL_ASIGNACION_FECHA + " = ? AND " +
                COL_ASIGNACION_OCULTO + " = 0 AND " +
                COL_ASIGNACION_FECHA + " <= ?";
        return db.rawQuery(query, new String[]{fecha, fecha});
    }
}