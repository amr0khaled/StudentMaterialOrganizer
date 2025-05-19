/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amr0khaled.student_organizer.controller;

import com.amr0khaled.student_organizer.Subject;
import com.amr0khaled.student_organizer.Week;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author rami3
 */
public class SubjectController {
  private File repo;
  private ArrayList<Subject> subjects = new ArrayList();
  public File location;
  public ArrayList<File> subjectsDirectories = new ArrayList();
  
  private static SubjectController instance = new SubjectController();
  
  public static SubjectController getInstance() {
    return instance;
  }
  
  public SubjectController() {
  }
  private void fetch() {
    subjectsDirectories = new ArrayList();
    File[] files = repo.listFiles();
    for (File file: files) {
      if (file.isDirectory()) {
        subjectsDirectories.add(file);
      }
    }
  }
  public void refresh() {
    fetch();
    dirToSubject();
  }
  private void dirToSubject() {
    subjects = new ArrayList();
    for (File file: subjectsDirectories) {
      // Weeks
      ArrayList<Week> weeks = new ArrayList<Week>();
      for (File s: file.listFiles()){
        String weekName = s.getName();
        if (weekName.contains("Week")) {
          Week week = new Week(Integer.parseInt(weekName.substring("Week ".length())));
          week.setPath(s.getPath());
          weeks.add(week);
        }
      }
      Subject subject = new Subject(
        file.getName(),
        file.getPath(),
        Optional.ofNullable(null),
        Optional.ofNullable(weeks)
      );
      subjects.add(subject);
    }
  }
  public ArrayList<Subject> getSubjects() {
    return subjects; 
  }
  public void setRepo(String repo) {
    this.repo = new File(repo);
    if (!this.repo.exists()) {
      this.repo.mkdir();
    }
    setLocation(this.repo);
  }
  public void setLocation(File newFile) {
    this.location = newFile;
  }
}
