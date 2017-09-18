
package br.univali.kob.poo1.aula05;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Odolir Junior <odolir@edu.univali.br>
 * @date   09/08/2017
 */
public class Aula05 {


    public static void main(String[] args) {
        // TODO code application logic here
        Employee e1 = new Employee("antonio jose","10/02/1900","01/09/2005",40,"3");
        System.out.println(e1.getId());
        System.out.println(e1.toString());
        
        
        Professor p1 = new Professor("Carlos Silva", "02/02/1960", "01/02/2017",40,"40",AcademicDegree.DOCTORATE);
        System.out.println(p1.getId());
        System.out.println(p1.toString());
        
        Student et1 = new Student("Maria das Dores","10/02/2000","01/09/2005","01/09/2005");
        System.out.println(et1.toString());
        
    

    }
    
}
