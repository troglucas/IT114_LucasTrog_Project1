package com.example.listprocessor;

import java.time.Year;

public class Employee {

    private String name;
    private String ID;
    private double salary;
    private String office;
    private String extension;
    private double performanceRating;
    private int startYear;
    private static int count = 0;
    public Employee(String name, String ID, double salary, String location, String extension, double performance, int Year) {
        this.name = name;
        this.ID = ID;
        this.salary = salary;
        this.office = location;
        this.extension = extension;
        this.performanceRating = performance;
        this.startYear = Year;

        count++;
    }

    public String employeeDisplay() {
        StringBuilder display = new StringBuilder();
        display.append("\n").append(name).append(", ").append(ID).append(", ").append(startYear);
        return display.toString();
    }
    public String employeeDetails() {
        StringBuilder EmployeeData = new StringBuilder();
        EmployeeData.append("\n").append(name);
        EmployeeData.append("\n").append(salary);
        EmployeeData.append("\n").append(office);
        EmployeeData.append("\n").append(extension);
        EmployeeData.append("\n").append(performanceRating);
        double bonus = getBonus();
        if (bonus != 0) {
            EmployeeData.append("\n").append(getBonus());
        }
            int yearsWorked = getYearsService();
        if (yearsWorked == 0) {
            EmployeeData.append("\n").append("New Employee");
        } else {
            EmployeeData.append("\n").append(getYearsService());
        }
        return EmployeeData.toString();
    }

    public double getBonus() {
        double bonus;
        if (performanceRating > 3.5) {
            bonus = salary * 0.05;
        }
        else if (performanceRating >= 2.0 && performanceRating <= 3.5) {
            bonus = salary * 0.02;
        }
        else {
            bonus = 0;
        }
        return bonus;
    }

    public int getYearsService() {
        Year currentYear = Year.now();
        int yearValue = currentYear.getValue();
        int yearsService = yearValue - startYear;
        return yearsService;
    }
    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    public String getID() { return ID; }
    public void setID(String ID) {
        this.ID = ID;
    }

    public double getSalary() { return salary; }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getOffice() { return office; }
    public void setOffice(String office) {
        this.office = office;
    }

    public String getExtension() { return extension; }
    public void setExtension(String extension) {
        this.extension = extension;
    }

    public double getPerformanceRating() { return performanceRating; }
    public void setPerformanceRating(double performanceRating) {
        this.performanceRating = performanceRating;
    }

    public int getStartYear() { return startYear; }
    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

}
