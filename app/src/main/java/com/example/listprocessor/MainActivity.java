package com.example.listprocessor;

import android.content.res.AssetManager;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

/////////////////////////////////////////////////////
//
// This app manipulates a list of strings.  Options
// are chosen from the main app bar.
//
// Some options just display results in the main
// TextView.  Others invoke new activities to carry
// out their tasks.
//
// Author: M. Halper
//
/////////////////////////////////////////////////////

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    ItemList the_list;   // reference to singleton string list object

    private TextView tv;



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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // set the reference to the "main" TextView object so
        // we do not have to retrieve it in every method below

           tv = (TextView) findViewById(R.id.text_main);

        // put some strings on the list (if the list is empty).  Note that the
        // "new" list might not be empty due to a restart of the app

        if(the_list.isEmpty())
        {

            the_list.add(the_list.size(), "pizza");
            the_list.add(the_list.size(), "crackers");
            the_list.add(the_list.size(), "peanut butter");
            the_list.add(the_list.size(), "jelly");
            the_list.add(the_list.size(), "bread");
            the_list.add(the_list.size(), "spaghetti");

        }

    } // end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onOption1(MenuItem i) {
        // display employees
        StringBuilder sb = new StringBuilder();
        for (Employee e : the_list.getEmployees()) {
            sb.append(e.employeeDisplay()).append("\n");
        }
        tv.setText(sb.toString());


    }// end onOption1

    public void onOption2(MenuItem i)
    {
        // load list from web (remove current list)

        startActivity(new Intent(this, AddURL.class));

    } // end onOption2

    public void onOption3(MenuItem i)
    {
        //add a new employee through an asset file

        startActivity(new Intent(this, addNewEmployee.class));

    } // end onOption3

    public void onOption4(MenuItem i)
    {

        //Show a certain employees details

        startActivity(new Intent(this, displayDetails.class));

    } // end onOption4

    public void onOption5(MenuItem i)
    {

        //Remove an employee from the list
        startActivity(new Intent(this, RemoveItemActivity.class));


    } // end onOption5

    public void onOption6(MenuItem i)
    {
        double salaryProduct = 1.0;
        ArrayList<Double> Salaries = new ArrayList<>();
        //show geometric average of salaries of an employee
        for (Employee e : the_list.getEmployees()) {
            Salaries.add(e.getSalary());
    }
        for (double s : Salaries) {
            salaryProduct *= s;
        }
        double geoSalary = Math.cbrt(salaryProduct);

        String GeoAverage = String.format("%.2f", geoSalary);

        tv.setText("The geometric average of all the employees is " + geoSalary);

    } // end onOption6

    public void onOption7(MenuItem i)
    {
        double highSalary = 100000.00;
        int count = 0;
        //show number of high-paid employees
        for (Employee e : the_list.getEmployees()) {
            double currentSalary = e.getSalary();
            if (currentSalary >= highSalary){
                count++;
            }
        }
        tv.setText("The number of employees paid over $100,000.00 is " + count);

    } // end onOption7

} // end MainActivity
