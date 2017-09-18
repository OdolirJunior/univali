package br.univali.kob.poo1.aula05;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author Odolir Junior <odolir@edu.univali.br>
 * @date   09/08/2017
 */
public class Student extends Person{
    private LocalDate enrollmentDate;
    private LocalDate dropDate;
    
    public Student( String name, LocalDate dateOfBirth, LocalDate enrollmentDate, LocalDate dropDate){
        super(name,dateOfBirth);
        this.enrollmentDate = enrollmentDate;
        this.dropDate = null;
    }
    
    public Student(String name, String dateOfBirth, String enrollmentDate, String dropDate){
        this(name, 
                LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.parse(enrollmentDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.parse(dropDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
    

    public LocalDate getEnrollmentDate(){
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDate enrollmentDate){
        this.enrollmentDate = enrollmentDate;
    }
    
    public LocalDate getDropDate(){
        return dropDate;
    }
    
    public void setDropDate(LocalDate dropDate){
        this.dropDate = dropDate;
    }
    
    /**
     * isEnrolled
     * Verifica se o estudante esta matriculado
     * @return 
     */
    public boolean isEnrolled(){
        return dropDate == null;
    }
    
    @Override
    protected String appendToString(){
        StringBuilder output = new StringBuilder();
        output.append("//Student "+AppConfig.NEW_LINE);
        output.append("EnrollmentDate"+enrollmentDate.format(AppConfig.DATE_FORMAT) +AppConfig.NEW_LINE);
        output.append("DropDate= "+((dropDate == null)? null: dropDate.format(AppConfig.DATE_FORMAT))+AppConfig.NEW_LINE);
        return output.toString();
    }
    
    @Override
    public boolean equals(Object obj){
        if(!super.equals(obj)){
            return false;
        }
        Student student = (Student)obj;
        return 
            (enrollmentDate == student.enrollmentDate || enrollmentDate.equals(student.enrollmentDate)) &&
            Objects.equals(dropDate, student.dropDate);
    }
    
    @Override 
    public int hashCode(){
        return 
            super.hashCode()^
            enrollmentDate.hashCode()^
            (dropDate == null? 2 : dropDate.hashCode());
    }
            
    
}
