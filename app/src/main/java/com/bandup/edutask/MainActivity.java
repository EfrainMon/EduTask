package com.bandup.edutask;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
}
