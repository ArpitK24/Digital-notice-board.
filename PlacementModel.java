package com.mvc;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class PlacementModel {
private int id;
private String pimagename;
CommonsMultipartFile pfile;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getPimagename() {
	return pimagename;
}
public void setPimagename(String pimagename) {
	this.pimagename = pimagename;
}
public CommonsMultipartFile getPfile() {
	return pfile;
}
public void setPfile(CommonsMultipartFile pfile) {
	this.pfile = pfile;
}

}
