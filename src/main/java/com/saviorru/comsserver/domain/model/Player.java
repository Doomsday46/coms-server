package com.saviorru.comsserver.domain.model;

import java.time.LocalDate;
import java.time.Period;


public class Player {

    private String firstName;
    private String lastName;
    private String patronymicName;

    private LocalDate birthDate;

    public Player(String firstName, String lastName, LocalDate birthDate){
        if (firstName == null || lastName == null || birthDate == null) throw new NullPointerException();
        if (firstName.isEmpty() || lastName.isEmpty()) throw new IllegalArgumentException("Name of surname can't be empty");
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Player(String firstName, String lastName, String patronymicName, LocalDate birthDate){
        if (firstName == null || lastName == null || birthDate == null) throw new NullPointerException();
        if (firstName.isEmpty() || lastName.isEmpty()) throw new IllegalArgumentException("Name of surname can't be empty");
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymicName() {
        return (patronymicName == null) ? "" : patronymicName;
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public int getAge() {
        return calculateAge(this.birthDate);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        result += getFirstName();
        result += " ";
        result += getLastName();
        return result;
    }
}
