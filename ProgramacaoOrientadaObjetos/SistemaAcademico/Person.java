package br.univali.kob.poo1.aula05;

import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.Month;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Odolir Junior <odolir@edu.univali.br>
 * @date   09/08/2017
 */
public abstract class Person {

    private String name;
    private LocalDate dateOfBirth;    
    private int id; 
    private static int nextId = 1;
    
    public Person(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;        
        validateState();
        id = nextId++;
    }
    public Person(String name, String dateOfBirth){
        this(name, LocalDate.parse(dateOfBirth, AppConfig.DATE_FORMAT));
    }
    
    public int getId(){
        return id;
    }
    
    public static int getNextId(){
        return nextId;
    }
    
    private void validateState(){
        validateName();
        validateDateOfBirth();
    }
    
    public String getName() {
        return name;
    }
    private void validateName(){
        if(!name.contains(" ")){
            throw new IllegalArgumentException("Nome deve ser composto"
                    + "Ex: nome sobrenome"); 
        }
        if(!checkName(name)){
            throw new IllegalArgumentException("Nome invalido"); 
        }
    }
    
    private boolean checkName(String name){
		Pattern p = Pattern.compile("^[[ ]|\\p{L}*]+$");//pau nessa ER =\ nao pega acento
		Matcher m = p.matcher(name);
		if(m.find()){
			return true;
		}else{
			return false;
                }
    }

    public void setName(String name) {
        this.name = name;
        validateName();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

  
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        validateDateOfBirth();
    }
    private void validateDateOfBirth(){
        if(dateOfBirth.isAfter(LocalDate.now()) || dateOfBirth.isBefore(LocalDate.of(1900, Month.JANUARY, 1))){
            throw new IllegalArgumentException("A data de nascimento deve ser maior que 1900/1/1 ou menor que hoje"); 
        }
    }
    
    public int getAge(){
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return period.getYears();
    }
    
    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        
        output.append(this.getClass().getName() + "{" + AppConfig.NEW_LINE);
        output.append("//Person "+AppConfig.NEW_LINE);
        output.append("Id= "+id+AppConfig.NEW_LINE);
        output.append("Name= "+name+AppConfig.NEW_LINE);
        output.append("DateOfBirth= "+
                dateOfBirth.format(AppConfig.DATE_FORMAT)+ AppConfig.NEW_LINE);
        output.append(appendToString());
        output.append("}"+AppConfig.NEW_LINE);
        return output.toString();
    }
    
    protected abstract String appendToString();
    
    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        
        Person person = (Person)obj;
        
        return 
                id==person.id &&
                (name == person.name || name.equals(person.name)) &&
                (dateOfBirth == person.dateOfBirth || dateOfBirth.equals(person.dateOfBirth));
    }
    
    @Override
    public int hashCode(){
        return id ^(name.hashCode()) ^ (dateOfBirth.hashCode());
    }
    
}