package com.example.davidcano.altausuario;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.Toast;

        import java.util.Iterator;
        import java.util.TreeMap;
        import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout botonera;

    private EditText tnombre;
    private EditText tdni;
    private EditText tlogin;
    private EditText tpassword;
    private EditText tsaldo;

    private Button badd;
    private Button blistar;

    private TreeMap<String, Usuario> usuarios;

    private Vector<Usuario> ordenados;

    private int posicion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonera = (LinearLayout) findViewById(R.id.botonera);
        botonera.setVisibility(LinearLayout.INVISIBLE);

        tnombre = (EditText)findViewById(R.id.nombre);
        tdni = (EditText)findViewById(R.id.nif);
        tlogin = (EditText)findViewById(R.id.login);
        tpassword = (EditText)findViewById(R.id.password);
        tsaldo = (EditText)findViewById(R.id.saldo);

        badd = (Button)findViewById(R.id.add);
        blistar = (Button)findViewById(R.id.listar);

        badd.setOnClickListener(this);
        blistar.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.add:
                add();
                break;
            case R.id.listar:
                listar();
                break;
            case R.id.primero:
                primero();
                break;
            case R.id.anterior:
                anterior();
                break;
            case R.id.siguiente:
                siguiente();
                break;
            case R.id.ultimo:
                ultimo();

        }

    }

    public void ordenarUsuarios(){

        if(!usuarios.isEmpty()) {

            Iterator<Usuario> iterador = usuarios.values().iterator();

            ordenados.removeAllElements();
            while (iterador.hasNext()) {
                ordenados.add(iterador.next());
            }

            /*for (int i = 0; i < ordenados.size()-1; i++) {
                for (int j = 1; j < ordenados.size(); j++) {

                    if (ordenados.get(i).getNombre().compareToIgnoreCase(ordenados.get(j).getNombre()) == 1) {
                        Usuario aux = ordenados.get(i);
                        ordenados.get(i) = ordenados.get(j);
                        ordenados.get(j) = aux;

                    }
                }
            }*/
        }

    }

    public void setInvisible(){

        botonera.setVisibility(LinearLayout.INVISIBLE);

        tnombre.setEnabled(true);
        tnombre.setFocusable(true);

        tdni.setEnabled(true);
        tdni.setFocusable(true);

        tsaldo.setEnabled(true);
        tsaldo.setFocusable(true);

        tlogin.setEnabled(true);
        tlogin.setFocusable(true);

        tpassword.setEnabled(true);
        tpassword.setFocusable(true);
    }

    public void setVisible(){

        botonera.setVisibility(LinearLayout.VISIBLE);

        tnombre.setEnabled(false);
        tnombre.setFocusable(false);

        tdni.setEnabled(false);
        tdni.setFocusable(false);

        tsaldo.setEnabled(false);
        tsaldo.setFocusable(false);

        tlogin.setEnabled(false);
        tlogin.setFocusable(false);

        tpassword.setEnabled(false);
        tpassword.setFocusable(false);
    }

    public void add(){

        setInvisible();

        Toast toast;

        String nombre = tnombre.getText().toString();

        String nif = tdni.getText().toString();

        String login = tlogin.getText().toString();

        String password = tpassword.getText().toString();

        float saldo = Float.parseFloat(tsaldo.getText().toString());

        if(nif.equals("")){
            toast = Toast.makeText(getApplicationContext(),"El DNi es obligatorio",Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Usuario usuario = new Usuario(nif, nombre, saldo, login, password);
            if (!usuarios.containsKey(nif)){
                usuarios.put(nif, usuario);
                toast = Toast.makeText(getApplicationContext(),"El usuario registrado con éxito",Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                toast = Toast.makeText(getApplicationContext(),"El usuario ya está registrado",Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        tnombre.setText("");
        tdni.setText("");
        tsaldo.setText("");
        tlogin.setText("");
        tpassword.setText("");


    }

    public void listar(){

        setVisible();

        posicion = 0;

        ordenarUsuarios();

        mostrarUsuario();

    }

    public void mostrarUsuario(){

        if(!ordenados.isEmpty())
            tnombre.setText(ordenados.get(posicion).getNombre());
        tdni.setText(ordenados.get(posicion).getNif());
        tsaldo.setText(String.valueOf(ordenados.get(posicion).getSaldo()));
        tlogin.setText(ordenados.get(posicion).getLogin());
        tpassword.setText(ordenados.get(posicion).getPassword());
    }

    public void primero(){

        posicion = 0;
        mostrarUsuario();
    }

    public void ultimo(){

        posicion = ordenados.size()-1;
        mostrarUsuario();
    }

    public void anterior(){

        if(posicion > 0){
            posicion--;
        }
        mostrarUsuario();
    }

    public void siguiente(){

        if(posicion < ordenados.size()-1){
            posicion++;
        }
        mostrarUsuario();
    }

}
