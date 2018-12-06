package employee.personal;

import employee.Employee;
import interfaces.*;

public class Personal extends Employee implements WorkTime{


    @Override
    public String toString() {
        return super.toString() + "Ставка: " + getBase()+" Отработанное время: "+ getWorkTime()+" Почасовая оплата: ";
    }

    @Override
    public double getHourlyPay(Employee employee) {
        return getWorkTime()*getBase();
    }

    @Override
    public void payroll(){
        double payment = getHourlyPay(this);
        setPayment(payment);
    }
}