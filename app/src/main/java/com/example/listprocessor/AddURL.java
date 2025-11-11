package com.example.listprocessor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;

@AndroidEntryPoint
public class AddURL extends AppCompatActivity {

    @Inject
    ItemList the_list;   // reference to singleton string list object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_url_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    } // end onCreate

    public void loadWeb(View view)
    {
        the_list.clear();

        EditText et = findViewById(R.id.enter_link);
        String URL = et.getText().toString();
        TextView tv = findViewById(R.id.text_add_heading);

        try {

            URL url_file = new URL(URL);
            Scanner fsc = new Scanner(url_file.openStream());

            if (!fsc.hasNext()) {
                tv.append("\n Web file is empty");
            } else {

            //read each line of fsc (URL file) and place them into their respective variables
            while (fsc.hasNext()) {
                String Name = fsc.nextLine();
                String ID = fsc.nextLine();
                double Salary = Double.parseDouble(fsc.nextLine());
                String Location = fsc.nextLine();
                String Extension = fsc.nextLine();
                double performance = Double.parseDouble(fsc.nextLine());
                int Years = Integer.parseInt(fsc.nextLine());

                //create new employee and place new read data into the arguments
                Employee e = new Employee(Name, ID, Salary, Location, Extension, performance, Years);

                //add the data to the employee list
                the_list.add(e);
            }
            fsc.close();

            Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                    the_list + " added to the list",
                    Snackbar.LENGTH_SHORT).show();
        }
        } catch (MalformedURLException e) {
            hideKeyboard();
            Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                    "Failed to add employees to the list",
                    Snackbar.LENGTH_SHORT).show();

        } catch (IOException e) {
            hideKeyboard();
            Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                    "Failed to add employees to the list",
                    Snackbar.LENGTH_SHORT).show();
        }


    } // end

    private void hideKeyboard()
    {
        // This method dismisses the soft keyboard.
        // Code derived from developer.android.com and
        // StackOverflow

        Context context = getCurrentFocus().getContext();

        InputMethodManager inputMethodManager =
                (InputMethodManager)
                        context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (inputMethodManager != null)
            inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

    } // end hideKeyboard

} // end AddURL activity