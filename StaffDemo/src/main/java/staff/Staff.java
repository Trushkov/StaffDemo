package staff;

import employee.Employee;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.*;

public class Staff {

    private List<Employee> employees = new ArrayList<>();

    public Staff() throws ParserConfigurationException, IOException, SAXException {}

    private NodeList createNodeList() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(getClass().getResourceAsStream("/Staff.xml"));
        Node rootNode = doc.getFirstChild();
        NodeList nodeList = rootNode.getChildNodes();
        return nodeList;
    }

    private void load() throws ParserConfigurationException, IOException, SAXException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        Employee employee;

        NodeList nodeList = createNodeList();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nextNode = nodeList.item(i);
            if (nextNode.getNodeType() == Node.ELEMENT_NODE){
            NodeList childes  = nextNode.getChildNodes();
            for (int j = 0; j<childes.getLength(); j++){
                Node nextChild = childes.item(j);
            if (nextChild.getNodeType() == Node.ELEMENT_NODE) {
                String pcg = nextNode.getParentNode().getNodeName().toLowerCase();
                String pcg2 = nextChild.getParentNode().getNodeName().toLowerCase();
                String clazz = nextChild.getNodeName();
                employee = (Employee) (Class.forName(pcg + "."+pcg2+"." + clazz)).newInstance();
                employees.add(employee);
                NodeList childNodes = nextChild.getChildNodes();
                parseNodeList(childNodes, employee);
                employee.payroll();
            }
            }
            }
        }
    }

    private void parseNodeList(NodeList childNodes, Employee employee) {
        try {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node nextChildNode = childNodes.item(i);
                if (nextChildNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (nextChildNode.getNodeName().equals("id"))
                        employee.setId(Integer.parseInt(nextChildNode.getTextContent()));
                    if (nextChildNode.getNodeName().equals("name"))
                        employee.setName(nextChildNode.getTextContent());
                    if (nextChildNode.getNodeName().equals("base"))
                        employee.setBase(Double.parseDouble(nextChildNode.getTextContent()));
                    if (nextChildNode.getNodeName().equals("workTime"))
                        employee.setWorkTime(Double.parseDouble(nextChildNode.getTextContent()));
                    if (nextChildNode.getNodeName().equals("position"))
                        employee.setPosition(nextChildNode.getTextContent());
                    if (nextChildNode.getNodeName().equals("subordinates"))
                        employee.setSubordinates(Integer.parseInt(nextChildNode.getTextContent()));
                    if (nextChildNode.getNodeName().equals("project")) {
                        Element element = (Element) nextChildNode;
                        double budget = Double.parseDouble(element.getAttribute("budget"));
                        String title = null;
                        double personalInput = 0;
                        NodeList projectEntries = nextChildNode.getChildNodes();
                        for (int j = 0; j < projectEntries.getLength(); j++) {
                            Node projectEntry = projectEntries.item(j);
                            if (projectEntry.getNodeType()== Node.ELEMENT_NODE) {
                                if (projectEntry.getNodeName().equals("title"))
                                    title = projectEntry.getTextContent();
                                if (projectEntry.getNodeName().equals("personalInput"))
                                    personalInput = Double.parseDouble(projectEntry.getTextContent());
                            }
                        }
                        employee.addProject(new Employee.Project(title, budget, personalInput));
                    }
                }
            }
        }catch (Exception e){
            System.err.println("Введены неверные символы!");
        }
    }

    static class Format{

        static String printHead(){
            return String.format("%-5s %15s %38s %12s %20s %9s\n","ID", "ФИО", "Должность", "Ставка", "Отработанное время","Зарплата")+
                    String.format("%-5s %15s %35s %14s %13s %15s","---","---","---","---","---","---");
        }

        static String printEmployee(Employee employee){
            return String.format("%-5d %-35s %-25s %5.1f %13.1f %16.1f", employee.getId(), employee.getName(), employee.getPosition(),
                    employee.getBase(), employee.getWorkTime(), employee.getPayment());
        }
    }

    public void loadAndPrint() throws ParserConfigurationException, IllegalAccessException, InstantiationException, SAXException, IOException, ClassNotFoundException {

        load();
        System.out.println(Format.printHead());
        Collections.sort(employees);
        for (Employee employee:employees){
            System.out.println(Format.printEmployee(employee));
        }
    }
}
