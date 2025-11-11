package com.example.listprocessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

//////////////////////////////////////////////////////////////////////////
//
//  Singleton class ItemList, for creating/managing a single
//  instance of a list of items (at the moment, strings).  Note
//  that the type "String" can easily be changed to accommodate a
//  list of whatever kind of objects you wish.
//
//  Author: M. Halper
//
//////////////////////////////////////////////////////////////////////////

@Singleton
public class ItemList extends LinkedList<String>
{
    private final List<Employee> employees;
    @Inject
    ItemList()
    {
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public void clear() {
        employees.clear();
    }

    public void add(Employee e) {
        employees.add(e);
    }

    public Employee findByID(String id) {
        for (Employee e : employees) {
            if (e.getID().equalsIgnoreCase(id.trim()))
                return e;
        }
        return null;
    }
    public boolean removeById(String id) {
        Employee e = findByID(id);
        if (e != null) {
            employees.remove(e);
            return true;
        }
        return false;
    }

} // end ItemList
