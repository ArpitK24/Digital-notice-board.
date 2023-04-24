package com.mvc;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class EventModel {
private int id;
private String eventname;
CommonsMultipartFile efile;
private String eimagename;

public String getEimagename() {
	return eimagename;
}
public void setEimagename(String eimagename) {
	this.eimagename = eimagename;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEventname() {
	return eventname;
}
public void setEventname(String eventname) {
	this.eventname = eventname;
}
public CommonsMultipartFile getEfile() {
	return efile;
}
public void setEfile(CommonsMultipartFile efile) {
	this.efile = efile;
}
}
