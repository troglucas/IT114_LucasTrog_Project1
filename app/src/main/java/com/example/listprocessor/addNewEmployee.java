package com.example.listprocessor;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
public class addNewEmployee extends AppCompatActivity {

    @Inject
    ItemList the_list;   // reference to singleton string list object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    } // end onCreate

    public void addEmployee(View view)
            throws java.io.IOException {

        EditText et = findViewById(R.id.newEmployee);
        String AssetFile = et.getText().toString().trim();

            if (et == null) {
                // Defensive: avoid NPE if the TextView id is wrong in layout
                Snackbar.make(findViewById(android.R.id.content),
                        "edit text is empty", Snackbar.LENGTH_LONG).show();
                return;
            }

        try
        {
            AssetManager assetManager = getAssets();
            Scanner fsc = new Scanner(assetManager.open(AssetFile));

                //read the file and add the elements to the list
                while (fsc.hasNext()) {
                    String Name = fsc.nextLine();
                    String ID = fsc.nextLine();
                    double Salary = Double.parseDouble(fsc.nextLine());
                    String Location = fsc.nextLine();
                    String Extension = fsc.nextLine();
                    int Year = Integer.parseInt(fsc.nextLine());
                    double performance = Double.parseDouble(fsc.nextLine());

                    //create new employee and place new read data into the arguments
                    Employee e = new Employee(Name, ID, Salary, Location, Extension, performance, Year);

                    //add the data to the employee list
                    the_list.add(e);

                }
                fsc.close();

            // hide soft keyboard so snackbar is visible
            Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                    "Employee added to the list",
                    Snackbar.LENGTH_SHORT).show();
        }
        catch(IndexOutOfBoundsException e)
        {
            hideKeyboard();
            Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                    "Failed to add employee to the list",
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

} // end AddItemActivity