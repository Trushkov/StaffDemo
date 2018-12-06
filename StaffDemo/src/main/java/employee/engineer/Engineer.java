package employee.engineer;

import employee.Employee;
import interfaces.WorkTime;
import interfaces.Project;

import java.util.Collection;
import java.util.List;

public class Engineer extends Employee implements WorkTime, Project{

    @Override
    public String toString() {
        return super.toString() + "Ставка: " + super.getBase() + " Зарплата: " + super.getPayment();
    }

    @Override
    public double getPaymentForProject(Employee employee) {

        double payment = 0;

        List<Project> projects = employee.getProjects();
        for(int i = 0; i< projects.size(); i++){
            Project project = projects.get(i);
            payment = project.getBudget()*project.getPersonalInput();
        }

        return payment;
    }

    @Override
    public double getHourlyPay(Employee employee) {
        return getWorkTime()*getBase();
    }

    @Override
    public void payroll(){
        double payment = getHourlyPay(this)+ getPaymentForProject(this);
        super.setPayment(payment);
    }
}
