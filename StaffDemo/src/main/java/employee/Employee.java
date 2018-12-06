package employee;

import java.util.*;

public class Employee implements Comparable<Employee>{
    private int id;
    private String name;
    private double workTime;
    private double base;
    private double payment;
    private String position;
    private int subordinates;
    private List<Project> projects = new ArrayList<>();

    public Employee(){}

    @Override
    public String toString() {
        return "Id: "+id+" Должность: "+position+" ФИО: "+name+" Зарплата ="+payment;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWorkTime(double workTime){
        this.workTime = workTime;
    }

    public double getWorkTime(){
        return workTime;
    }

    public void setBase(double base){
        this.base = base;
    }

    public double getBase() {
        return base;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getPayment() {
        return payment;
    }

    public void payroll(){}

    public void setSubordinates(int subordinates){
        this.subordinates = subordinates;
    }

    public int getSubordinates() {
        return subordinates;
    }


    @Override
    public boolean equals (Object other){
        Employee e = (Employee) other;
        return id == e.getId();
    }

    public int compareTo(Employee p) {

        return ((Integer)id).compareTo(p.getId());
    }

    public static class Project {
        private String title;
        private double budget;
        private double personalInput;

        public Project(String title, double budget, double personalInput){
            this.title = title;
            this.budget = budget;
            this.personalInput = personalInput;
        }

        public double getBudget() {
            return budget;
        }

        public double getPersonalInput() {
            return personalInput;
        }
    }

    public void addProject(Project project){
        projects.add(project);
    }

    public List<Project> getProjects() {
        return projects;
    }
}
