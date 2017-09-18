package br.univali.kob.poo1.aula05;

/**
 * @author Odolir Junior <odolir@edu.univali.br>
 * @date   26/08/2017
 */
public enum AcademicDegree {
    BACHELOR("Bachelor","0.00"), 
    MASTER("Master","0.15"), 
    DOCTORATE("Doctorate","0.30");
    
    private String description;
    private String bonus;
    
    public String getDescription(){
        return description;
    }
    public String getBonus(){
        return bonus;
    }
    
    private AcademicDegree(String description, String bonus){
        this.description = description;
        this.bonus = bonus;
    }
}
