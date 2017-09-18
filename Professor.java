package br.univali.kob.poo1.aula05;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Odolir Junior <odolir@edu.univali.br>
 * @date   16/08/2017
 */
public class Professor extends Employee{
    
    private AcademicDegree academicDegree; 
    private BigDecimal hourlyRate; 
    
    public Professor(String name, LocalDate dateOfBirth, LocalDate hireDate,
        int hoursPerWorkWeek, BigDecimal hourlyRate, AcademicDegree academicDegree){
        super (name,dateOfBirth,hireDate,hoursPerWorkWeek,hourlyRate);
        this.hourlyRate = hourlyRate;
        this.academicDegree = academicDegree;
    }   
    
    
    public Professor(String name, String dateOfBirth, String hireDate,
        int hoursPerWorkWeek, String hourlyRate, AcademicDegree academicDegree){
        this(name,
                LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.parse(hireDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                hoursPerWorkWeek,
                new BigDecimal(hourlyRate),
                academicDegree);
    }
    
    public AcademicDegree getAcademicDegree(){
        return academicDegree;
    }
    
    public void setAcademicDegree(AcademicDegree academicDegree){
        this.academicDegree = academicDegree;
    }
    
    
    @Override
    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }
    
    public BigDecimal getAcademicBonus(){
        BigDecimal bonus = super.getHourlyRate().multiply(new BigDecimal(academicDegree.getBonus()));
        return bonus.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    @Override
    protected String appendToString(){
        StringBuilder output = new StringBuilder(super.appendToString());
        output.append("//Professor "+AppConfig.NEW_LINE);
        output.append("AcademicDegree= "+academicDegree+AppConfig.NEW_LINE);
        output.append("HourlyRate= " +hourlyRate+AppConfig.NEW_LINE );
        output.append("AcademicBonus= "+getAcademicBonus()+AppConfig.NEW_LINE);
        return output.toString();
    }
    
    @Override
    public boolean equals(Object obj){
        if(!super.equals(obj)){
            return false;
        }
        Professor professor = (Professor)obj;
        return 
            (hourlyRate == professor.hourlyRate || hourlyRate.equals(professor.hourlyRate))&&
            (academicDegree == professor.academicDegree || academicDegree.equals(professor.academicDegree));           
    }
    
    @Override
    public int hashCode(){
        return 
                super.hashCode()^
                hourlyRate.hashCode()^
                academicDegree.ordinal();
    }
}
