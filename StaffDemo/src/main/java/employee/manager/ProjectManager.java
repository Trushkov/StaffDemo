package employee.manager;

import employee.Employee;
import interfaces.Heading;

public class ProjectManager extends Manager implements Heading {

    @Override
    public double getPaymentForHeading(Employee employee) {
        return employee.getSubordinates()*100000;
    }

    @Override
    public void payroll(){
        super.payroll();
        double payment = super.getPayment() + getPaymentForHeading(this);
        super.setPayment(payment);
    }
}