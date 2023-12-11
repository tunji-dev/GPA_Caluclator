/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gpacalculator;
import java.util.Scanner;
import java.util.ArrayList;
import com.mycompany.gpacalculator.Models.StudentRecordsModel;



/**
 *
 * @author hp
 */
public class GPACalculatorLogic {
    public ArrayList<StudentRecordsModel> StudentRecords;
    
    public GPACalculatorLogic(){
        StudentRecords = new ArrayList<StudentRecordsModel>();
        
    }
    public String StoreStudentRecords(String courseName, double score, int courseUnit){
        
        StudentRecordsModel recordToAdd = new StudentRecordsModel();
        recordToAdd.CourseName = courseName.toUpperCase();
        recordToAdd.CourseUnit = courseUnit;
        recordToAdd.Score = score;
        recordToAdd.Grade = GetStudentGrade(score);
        recordToAdd.GradePoint = CalculateGradePoint(recordToAdd.Grade);
        
        StudentRecords.add(recordToAdd);
        return "OK";
    }
    
    private double calculateGPA() {
        double totalQualityPoints = 0;
        double totalGradeUnits = 0;

        for (StudentRecordsModel course : StudentRecords) {
            double qualityPoints = course.CourseUnit * course.GradePoint;
            totalQualityPoints += qualityPoints;
            totalGradeUnits += course.GradePoint;
        }

        return totalQualityPoints / totalGradeUnits;
    }
    
     public void displayResult() {
        System.out.println("|---------------------|-------------|-------|------------|");
        System.out.println("|        COURSE       | COURSE UNIT | GRADE | GRADE-UNIT |");
        System.out.println("|---------------------|-------------|-------|------------|");

        for (StudentRecordsModel record : StudentRecords) {
            System.out.printf("| %-19s | %-11d | %-5s | %-10.1f |\n",
                    record.CourseName, record.CourseUnit, record.Grade, record.GradePoint);
        }
        System.out.println("|--------------------------------------------------------|");
        System.out.printf("\nYour GPA is = %.2f to 2 decimal places.", calculateGPA());
        
     }
    
    public int GetRecordId(){
        return StudentRecords.size();
    }
    
    
    private char GetStudentGrade(double score){
        if (score >= 70) {
            return 'A';
        } else if (score >= 60) {
            return 'B';
        } else if (score >= 50) {
            return 'C';
        } else if (score >= 45) {
            return 'D';
        } else if (score >= 40){
            return 'E';
        }else{
            return 'F';
        }
    }
    
    private double CalculateGradePoint(char grade){
            switch (grade) {
            case 'A':
                return 5.0;
            case 'B':
                return 4.0;
            case 'C':
                return 3.0;
            case 'D':
                return 2.0;
            case 'E':
                return 1.0;
            default:
                return 0.0;
        }
    }
    
   public  String getValidCourseName(Scanner scanner) {
        String name = "";
        while (true) {
            System.out.print("Enter course name (cannot be blank): ");
            name = scanner.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println("Invalid course name. Please try again.");
            } else {
                break; // Exit the loop if the name is valid
            }
        }
        return name;
    }

    // Helper method to validate and get the course units
    public int getValidCourseUnits(Scanner scanner) {
        int units = 0;
        while (true) {
            System.out.print("Enter course unit (must be at least 1): ");
            if (scanner.hasNextInt()) {
                units = scanner.nextInt();
                if (units >= 1) {
                    break; // Exit the loop if units is valid
                } else {
                    System.out.println("Invalid course unit. Please enter a value of at least 1.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume newline
        return units;
    }

    // Helper method to validate and get the score
    public double getValidScore(Scanner scanner) {
        double score = 0;
        while (true) {
            System.out.print("Score must be between 0 and 100 : ");
            if (scanner.hasNextDouble()) {
                score = scanner.nextDouble();
                if (score >= 0 && score <= 100) {
                    break; // Exit the loop if score is valid
                } else {
                    System.out.println("Invalid score. Please enter a value between 0 and 100.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume newline
        return score;
    }  
}
