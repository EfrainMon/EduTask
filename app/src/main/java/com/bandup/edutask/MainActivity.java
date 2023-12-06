package com.bandup.edutask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.bandup.edutask.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    BaseDatosHelper miBaseDatosHelper;

    private Button btnCrearMateria;
    private Button btnEliminarMateria;
    private EditText edtNombreMateria, edtClaveMateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        miBaseDatosHelper = new BaseDatosHelper(this);

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Infla la vista de tu activity_crear_datos.xml
                View dialogView = getLayoutInflater().inflate(R.layout.activity_anadir_nuevo, null);

                // Crea un AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView)
                        .setTitle("Añadir nuevo...")
                        .setNegativeButton("Volver", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss(); // Cierra el diálogo
                            }
                        });

                // Muestra el AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



                // Aquí es donde insertar el código para el nuevo AlertDialog
                TextView txtAnadeMateria = dialogView.findViewById(R.id.txtAnadeMateria);
                txtAnadeMateria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Infla la vista de tu activity_crear_materia.xml
                        View crearMateriaView = getLayoutInflater().inflate(R.layout.activity_crear_materia, null);

                        // Crea un AlertDialog
                        AlertDialog.Builder crearMateriaBuilder = new AlertDialog.Builder(MainActivity.this);
                        crearMateriaBuilder.setView(crearMateriaView)
                                .setTitle("Agregar Materia")
                                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Lógica para manejar la acción de agregar materia
                                        // Puedes obtener referencias a los elementos de tu formulario aquí
                                        EditText nombreMateria = crearMateriaView.findViewById(R.id.edtNombreMateria);
                                        EditText claveMateria = crearMateriaView.findViewById(R.id.edtClaveMateria);

                                        // Dentro del método onClick del botón "Agregar" en el diálogo de agregar materia
                                        String textoNombreMateria = nombreMateria.getText().toString();
                                        String textoClaveMateria = claveMateria.getText().toString();

                                        // Verifica si los campos no están vacíos
                                        if (!textoNombreMateria.isEmpty() && !textoClaveMateria.isEmpty()) {
                                            // Agrega la materia a la base de datos
                                            boolean exito = miBaseDatosHelper.addMateria(textoClaveMateria, textoNombreMateria, 0);

                                            // Verifica si se agregó correctamente
                                            if (exito) {
                                                // Puedes mostrar un mensaje de éxito o realizar otras acciones
                                                Toast.makeText(MainActivity.this, "Materia agregada correctamente", Toast.LENGTH_SHORT).show();
                                            } else {
                                                // Puedes mostrar un mensaje de error si la inserción falló
                                                Toast.makeText(MainActivity.this, "Error al agregar la materia", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            // Muestra un mensaje si algún campo está vacío
                                            Toast.makeText(MainActivity.this, "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                })

                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss(); // Cierra el diálogo
                                    }
                                });

                        // Muestra el AlertDialog para agregar materia
                        AlertDialog crearMateriaDialog = crearMateriaBuilder.create();
                        crearMateriaDialog.show();
                    }
                });

                // Aquí es donde insertar el código para el nuevo AlertDialog
                TextView txtAnadeAlumno = dialogView.findViewById(R.id.txtAnadeAlumno);
                txtAnadeAlumno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Infla la vista de tu activity_crear_alumno.xml
                        View crearAlumnoView = getLayoutInflater().inflate(R.layout.activity_crear_alumno, null);

                        // Crea un AlertDialog
                        AlertDialog.Builder crearAlumnoBuilder = new AlertDialog.Builder(MainActivity.this);
                        crearAlumnoBuilder.setView(crearAlumnoView)
                                .setTitle("Agregar Alumno")
                                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Lógica para manejar la acción de agregar alumno
                                        // Puedes obtener referencias a los elementos de tu formulario aquí
                                        EditText numControl = crearAlumnoView.findViewById(R.id.edtNumControlAlu);
                                        EditText nombreAlu = crearAlumnoView.findViewById(R.id.edtNombreAlu);
                                        EditText apPaternoAlu = crearAlumnoView.findViewById(R.id.edtApPatAlu);
                                        EditText apMaternoAlu = crearAlumnoView.findViewById(R.id.edtApMatAlu);

                                        // Dentro del método onClick del botón "Agregar" en el diálogo de agregar alumno
                                        String textoNumControl = numControl.getText().toString();
                                        String textoNombreAlu = nombreAlu.getText().toString();
                                        String textoApPaternoAlu = apPaternoAlu.getText().toString();
                                        String textoApMaternoAlu = apMaternoAlu.getText().toString();

                                        // Verifica si los campos no están vacíos
                                        if (!textoNumControl.isEmpty() && !textoNombreAlu.isEmpty() && !textoApPaternoAlu.isEmpty() && !textoApMaternoAlu.isEmpty()) {
                                            // Agrega el alumno a la base de datos
                                            boolean exito = miBaseDatosHelper.addAlumno(textoNumControl, textoNombreAlu, textoApPaternoAlu, textoApMaternoAlu, 0);

                                            // Verifica si se agregó correctamente
                                            if (exito) {
                                                // Puedes mostrar un mensaje de éxito o realizar otras acciones
                                                Toast.makeText(MainActivity.this, "Alumno agregado correctamente", Toast.LENGTH_SHORT).show();
                                            } else {
                                                // Puedes mostrar un mensaje de error si la inserción falló
                                                Toast.makeText(MainActivity.this, "Error al agregar al alumno", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            // Muestra un mensaje si algún campo está vacío
                                            Toast.makeText(MainActivity.this, "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                })

                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss(); // Cierra el diálogo
                                    }
                                });

                        // Muestra el AlertDialog para agregar alumno
                        AlertDialog crearAlumnoDialog = crearAlumnoBuilder.create();
                        crearAlumnoDialog.show();
                    }
                });

                // Aquí es donde insertar el código para el nuevo AlertDialog
                TextView txtAnadeAsignacion = dialogView.findViewById(R.id.txtAnadeAsignacion);
                txtAnadeAsignacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Infla la vista de tu activity_crear_asignacion.xml
                        View crearAsignacionView = getLayoutInflater().inflate(R.layout.activity_crear_asignacion, null);

                        // Crea un AlertDialog
                        AlertDialog.Builder crearAsignacionBuilder = new AlertDialog.Builder(MainActivity.this);
                        crearAsignacionBuilder.setView(crearAsignacionView)
                                .setTitle("Agregar Asignación")
                                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Lógica para manejar la acción de agregar asignación
                                        // Puedes obtener referencias a los elementos de tu formulario aquí
                                        EditText nombreAsignacion = crearAsignacionView.findViewById(R.id.edtNombreAsignacion);
                                        CalendarView calendarView = crearAsignacionView.findViewById(R.id.calendarViewAsignacion);

                                        // Dentro del método onClick del botón "Agregar" en el diálogo de agregar asignación
                                        String textoNombreAsignacion = nombreAsignacion.getText().toString();
                                        long fechaSeleccionada = calendarView.getDate(); // Obtiene la fecha seleccionada en milisegundos

                                        // Verifica si los campos no están vacíos
                                        if (!textoNombreAsignacion.isEmpty()) {
                                            // Agrega la asignación a la base de datos
                                            boolean exito = miBaseDatosHelper.addAsignacion(convertirFechaMilisegundosAFechaString(fechaSeleccionada), textoNombreAsignacion, 0, 0, 0);

                                            // Verifica si se agregó correctamente
                                            if (exito) {
                                                // Puedes mostrar un mensaje de éxito o realizar otras acciones
                                                Toast.makeText(MainActivity.this, "Asignación agregada correctamente", Toast.LENGTH_SHORT).show();
                                            } else {
                                                // Puedes mostrar un mensaje de error si la inserción falló
                                                Toast.makeText(MainActivity.this, "Error al agregar la asignación", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            // Muestra un mensaje si algún campo está vacío
                                            Toast.makeText(MainActivity.this, "El nombre de la asignación no puede estar vacío", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                })

                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss(); // Cierra el diálogo
                                    }
                                });

                        // Muestra el AlertDialog para agregar asignación
                        AlertDialog crearAsignacionDialog = crearAsignacionBuilder.create();
                        crearAsignacionDialog.show();
                    }
                });

            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // Encuentra referencias a los elementos de diseño
        btnCrearMateria = findViewById(R.id.btnCrearMateria);
        btnEliminarMateria = findViewById(R.id.btnEliminarMateria);
        edtNombreMateria = findViewById(R.id.edtNombreMateriaTest);
        edtClaveMateria = findViewById(R.id.edtClaveMateriaTest);

        // Configura el OnClickListener para el botón
        btnCrearMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene los valores de los EditText
                String nombreMateria = edtNombreMateria.getText().toString();
                String claveMateria = edtClaveMateria.getText().toString();

                // Verifica si los campos no están vacíos
                if (!nombreMateria.isEmpty() && !claveMateria.isEmpty()) {
                    // Agrega la materia a la base de datos
                    boolean exito = miBaseDatosHelper.addMateria(claveMateria, nombreMateria, 0);

                    // Verifica si se agregó correctamente
                    if (exito) {
                        // Puedes mostrar un mensaje de éxito o realizar otras acciones
                        Toast.makeText(MainActivity.this, "Materia agregada correctamente", Toast.LENGTH_SHORT).show();
                    } else {
                        // Puedes mostrar un mensaje de error si la inserción falló
                        Toast.makeText(MainActivity.this, "Error al agregar la materia", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Muestra un mensaje si algún campo está vacío
                    Toast.makeText(MainActivity.this, "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminarMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene los valores de los EditText
                String nombreMateria = edtNombreMateria.getText().toString();
                String claveMateria = edtClaveMateria.getText().toString();

                // Llamada al método deleteMateria en tu actividad o fragmento
                miBaseDatosHelper.deleteMateria(nombreMateria, claveMateria);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private String convertirFechaMilisegundosAFechaString(long fechaMilisegundos) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return sdf.format(new Date(fechaMilisegundos));
    }
}
