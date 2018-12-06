package employee.engineer;

import employee.Employee;
import interfaces.Heading;

public class TeamLeader extends Programmer implements Heading{

    @Override
    public double getPaymentForHeading(Employee employee) {
        return employee.getSubordinates()*100000;
    }

    @Override
    public void payroll(){
        super.payroll();
        double payment = super.getPayment() + 600000;
        super.setPayment(payment);
    }
}