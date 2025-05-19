/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amr0khaled.student_organizer;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author rami3
 */
public class Subject {
  private String path;
  private String imageSrc;
  private String title;
  private Open open;
  private ArrayList<Week> weeks = new ArrayList();
  public Subject(String title, String path, Optional<String> imageSrc, Optional<ArrayList<Week>> weeks) {
    this.title = title;
    this.path = path;
    if (imageSrc.isPresent()) {
      this.imageSrc = imageSrc.get();
    } else {
      this.imageSrc = path + "image.jpg";
    }
    if (weeks.isPresent()) {
      this.weeks = weeks.get();
    }
  }
  public boolean exists() {
    for (Week week: weeks) {
      if (week.exists(open)) {
        return true;
      }
    }
    return false;
  }
  public void setOpen(Open open) {
    this.open = open;
  }
  public void setPath(String path) {
    int total = this.path.length() -1;
    String slice = this.path.substring(total - title.length());
    File file = new File(this.path + '/');
    if (slice == title) {
      this.path = this.path.substring(0, (total + 1) - title.length());
    }
    this.path = path + "/" + title;
    if (!file.exists() && !file.isDirectory()) {
      file.mkdir();
    }
  }
  public String getTitle() {
    return title;
  }
  public String getImageSrc() {
    return imageSrc;
  }
  public void setImageSrc() {
    this.imageSrc = imageSrc;
  }
  public String getPath() {
    return path;
  }
  public String getLocation() {
    if (path.charAt(path.length() - 1) == '/') {
      return this.path + '/';
    }
    return this.path;
  }
  public ArrayList<Week> getWeeks(Open open) {
    ArrayList<Week> _weeks = new ArrayList();
    for (Week week: weeks) {
      if (week.exists(open)) {
        _weeks.add(week);
      }
    }
    return _weeks;
  }
}
