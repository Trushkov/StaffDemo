package main;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import staff.Staff;

    public class Main {

        public static void main(String[] args) throws IOException, ParserConfigurationException, InstantiationException, SAXException, IllegalAccessException, ClassNotFoundException {
            Staff staff = new Staff();
            staff.loadAndPrint();
        }
    }






