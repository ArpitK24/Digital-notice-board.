package com.mvc;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class AcheivementModel {
 private int id;
 private String imagename;
 CommonsMultipartFile afile;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getImagename() {
	return imagename;
}
public void setImagename(String imagename) {
	this.imagename = imagename;
}
public CommonsMultipartFile getAfile() {
	return afile;
}
public void setAfile(CommonsMultipartFile afile) {
	this.afile = afile;
}
 
}
