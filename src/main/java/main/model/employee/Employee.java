package main.model.employee;

import lombok.Data;

@Data
public class Employee {
    /**
     * Имя работника.
     */
    private String name;

    /**
     * Конструктор.
     * @param name имя работника
     */
    public Employee(String name) {
        this.name = name;
    }
}
