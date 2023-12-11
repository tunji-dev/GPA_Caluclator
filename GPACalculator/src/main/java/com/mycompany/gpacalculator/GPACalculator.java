/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.gpacalculator;
import java.util.Scanner;
import com.mycompany.gpacalculator.Models.InputModel;

/**
 *
 * @author hp
 */
public class GPACalculator {
    static GPACalculatorLogic student = new GPACalculatorLogic();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        
        System.out.println("Welcome To The University Of Lagos...");
        String studentName = getValidStudentName(input);
        if (studentName.equalsIgnoreCase("done")) {
            System.out.println("Thank you for your time.");
            System.exit(0);
        }else {
            System.out.println("|--------------------------------------------------------|");
            System.out.println("\nWelcome " + studentName.toUpperCase());
        }
        boolean doneEnteringCourses = false;
        while(!doneEnteringCourses){
            InputModel stuInputs = GetInputs();
            String success = student.StoreStudentRecords(stuInputs.CourseName, stuInputs.Score,stuInputs.CourseUnit);
            if(success.equalsIgnoreCase("ok")){
                System.out.printf("%d Record(s) Stored Successfully..........\n", student.GetRecordId());
            }
            System.out.printf(" \n(%s) Would you like to Continue? (type yes or no): ", studentName);
            String studentResponse = getYesOrNoInput(input);
            if(studentResponse.equalsIgnoreCase("no")){
                System.out.print("\nHERE IS A BREAKDOWN OF YOUR ACADEMIC RECORD\n");
                doneEnteringCourses = true;
            }
//            doneEnteringCourses = !input.nextBoolean();           
        }
        System.out.println("|--------------------------------------------------------|");
        System.out.println("\nStudent Name: " + studentName);
        student.displayResult();
        input.close();
    }
    
    
    
    
    
    
    private static InputModel GetInputs(){
        Scanner input = new Scanner(System.in);
       
        String name = student.getValidCourseName(input);
//        System.out.println("Enter Course Unit");
//        int courseUnit = input.nextInt();
        int courseUnit = student.getValidCourseUnits(input);
        System.out.println("Enter Score For " + name.toUpperCase());
        double score = student.getValidScore(input);
        
        InputModel inputs = new InputModel();
        inputs.CourseName = name;
        inputs.CourseUnit = courseUnit;
        inputs.Score = score;
        
        return inputs;
        
    }
    
    
    
    
    private static String getValidStudentName(Scanner input){
        String name = "";
        while(true){
            if (name.trim().isEmpty() || name.length() > 32){
                System.out.print("Enter student name (not blank, not more than 32 characters) or 'done' to exit): ");
                name = input.nextLine();
            }else{
                break; // Exit the loop if the name is valid 
            }     
        }
        return name;
    }
    
    private static String getYesOrNoInput(Scanner input){
        String response = ""; 
        while(true){
            response = input.nextLine();
            if(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("no")){
                break;
            }else {
                System.out.print("Invalid input. Please enter 'yes' or 'no': ");
            }
        }
        return response;
    }
}
