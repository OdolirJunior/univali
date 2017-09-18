package br.univali.kob.poo1.aula05;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.lang.String;
import java.util.Objects;

/**
 * @author Odolir Junior <odolir@edu.univali.br>
 * @date   09/08/2017
 */
public class Employee extends Person{
    
    private LocalDate hireDate;
    private LocalDate terminationDate;
    private int hoursPerWorkWeek;
    private BigDecimal hourlyRate; 
    
    /**
     * 
     * @param name String
     * @param dateOfBirth LocalDate
     * @param hireDate LocalDate
     * @param hoursPerWorkWeek 
     */
    public Employee( String name, LocalDate dateOfBirth, LocalDate hireDate,int hoursPerWorkWeek, BigDecimal hourlyRate){
        super(name, dateOfBirth);
        this.hoursPerWorkWeek = hoursPerWorkWeek;
        this.hourlyRate = hourlyRate;
        this.hireDate = hireDate;
        validateState();
    }
    
    public Employee (String name, String dateOfBirth, String hireDate,int hoursPerWorkWeek, String hourlyRate){
        this(name, 
                LocalDate.parse(dateOfBirth, AppConfig.DATE_FORMAT),
                LocalDate.parse(hireDate, AppConfig.DATE_FORMAT),
                hoursPerWorkWeek,
                new BigDecimal(hourlyRate));
    }


    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public int getHoursPerWorkWeek() {
        return hoursPerWorkWeek;
    }

    public void setHoursPerWorkWeek(int hoursPerWorkWeek) {
        this.hoursPerWorkWeek = hoursPerWorkWeek;
        validateHoursPerWorkWeek();
    }
    
    private void validateState(){
        validateHoursPerWorkWeek();
    }
    
    private void validateHoursPerWorkWeek(){    
        if(hoursPerWorkWeek < 1 ||hoursPerWorkWeek >40){
            throw new OutOfRangeException(hoursPerWorkWeek,"hours Per Work Week",1,40);
        }
    }
    
    
    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    
    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    
    
    public boolean isEmployed(){
        return terminationDate == null;
    }
    
    public int getYearsOfService(){
        if(isEmployed()){
            Period period =  Period.between(hireDate, LocalDate.now());
            return period.getYears();
        }else{
            Period period =  Period.between(hireDate, terminationDate);
            return period.getYears();
        }
    }
    
    /**
     * Calcula o salario base do empregado em R$, multiplicando a 
     * quantidade de horas pelo valor da hora contratada
     * @return salario base semanal
     */
    public BigDecimal getRegularWeekSalary(){
        BigDecimal value = new BigDecimal(hoursPerWorkWeek);
        value = value.multiply(getHourlyRate());
        return value.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    public BigDecimal getWeekPayment(int absentHours){
        BigDecimal value = new BigDecimal(absentHours);
        return getRegularWeekSalary().subtract(value).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
   
    @Override
    protected String appendToString(){
         StringBuilder output = new StringBuilder();
         output.append("//Employee "+AppConfig.NEW_LINE);
         output.append("HireDate= "+hireDate.format(AppConfig.DATE_FORMAT)+AppConfig.NEW_LINE);
         output.append("TerminationDate= ");
         output.append(((terminationDate == null)? null: terminationDate.format(AppConfig.DATE_FORMAT))+AppConfig.NEW_LINE);
         output.append("HoursPerWorkWeek= "+hoursPerWorkWeek+AppConfig.NEW_LINE);
         output.append("HourlyRate= "+hourlyRate.toPlainString()+AppConfig.NEW_LINE);
         return output.toString();
    }
    
    @Override
    public boolean equals(Object obj){
        if(!super.equals(obj)){
            return false;
        }
        Employee employee = (Employee) obj;
        return 
                (hireDate == employee.hireDate || hireDate.equals(employee.hireDate)) &&
                Objects.equals(terminationDate, employee.terminationDate) &&
                hoursPerWorkWeek == employee.hoursPerWorkWeek &&
                (hourlyRate == employee.hourlyRate || hourlyRate.equals(employee.hourlyRate));
    }
    
    @Override
    public int hashCode(){
        return 
            super.hashCode() ^
            hireDate.hashCode() ^
            (terminationDate == null ? 19 : terminationDate.hashCode())^
            hoursPerWorkWeek ^
            getHourlyRate().hashCode();      
    }

}
