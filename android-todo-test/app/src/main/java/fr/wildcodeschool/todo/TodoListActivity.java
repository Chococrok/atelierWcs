package fr.wildcodeschool.todo;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TodoListActivity extends Activity {

    private ArrayList<String> listTodo;
    private ArrayAdapter<String> adaptTodo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // On recupère la listView de sa version XML
        ListView listViewTodo = (ListView) findViewById(R.id.listToDo);
        // On recupère l'editText de sa version XML
        final EditText textTodo = (EditText) findViewById(R.id.textToDo);

        // On crée une liste de chaine de caractère et on y ajoute un premier élément
        listTodo = new ArrayList<String>();
        listTodo.add("Elément 1");

        // On lie l'adaptateur avec la liste de chaine de caractere et un layout
        adaptTodo = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listTodo);

        // On associe l'adaptateur à la listeView
        listViewTodo.setAdapter(adaptTodo);
        listViewTodo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adaptTodo.remove(adaptTodo.getItem(i));
                adaptTodo.notifyDataSetChanged();
            }
        });

        // Ecouteur si on appuie soit sur le bouton OK ou la trackball en étant
        // sur l'editText
        textTodo.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // si un bouton ou la trackball a été appuyer
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    // si le bouton OK ou la trackball a été appuyer
                    if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                        // appel de la fonction d'ajout dans la liste
                        TodoListActivity.this.rempliListeTodo(listTodo, textTodo, adaptTodo);
                        return true;
                    }
                }
                return false;

            }
        });

        // récupération du bouton grâce à son ID
        Button button = (Button) findViewById(R.id.buttonAdd);

        // on applique un écouteur d'évenement au clique sur le bouton ajouter
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // appel de la fonction d'ajout dans la liste
                TodoListActivity.this.rempliListeTodo(listTodo, textTodo, adaptTodo);
            }
        });
    }

    // Fonction qui rempli une liste
    private void rempliListeTodo(ArrayList<String> arrayList, EditText editText,
                                 ArrayAdapter<String> arrayAdapter) {
        // si au moins un caractère a été rentré dans l'editText
        if (editText.getText().toString().length() > 0) {
            arrayList.add(0, editText.getText().toString());
            arrayAdapter.notifyDataSetChanged();
            editText.setText("");
        } else {
            // Sinon un message s'affiche
            Toast.makeText(this, "1 caractère minimum !!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

}