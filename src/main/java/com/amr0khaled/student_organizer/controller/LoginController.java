/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amr0khaled.student_organizer.controller;

/**
 *
 * @author rami3
 */
public class LoginController {
  private String salt = "1234";
  private int _password = ("1234".concat(salt)).hashCode();
  private String _username = "Amr";
  public LoginController() {
  }
  public boolean check(String username, String password) {
    password = password.concat(salt);
    System.out.print(_username);
    System.out.println(_password);
    
    System.out.print(username);
    System.out.println(password);
    if (_username == username
      && _password == password.hashCode()) {
      return true;
    }
    return false;
  }
}
