package com.mvc;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mvc.AcheivementModel;
import com.mvc.CalenderpanelModel;
import com.mvc.EventModel;

import com.mvc.NoticeModel;
import com.mvc.PlacementModel;

public class MainDao {

	 public JdbcTemplate jt ;

	public JdbcTemplate getjt() {
		return jt;
	}

	public void setjt(JdbcTemplate jt) {
		this.jt = jt;
	}

	public int save(RegistrationModel registerfrom) {
		// TODO Auto-generated method stub
		String sql= "insert into tbl_staff(Firstname,Lastname,Mobileno,Emailid,Password)value('"+registerfrom.getFirstname()+"','"+registerfrom.getLastname()+"','"+registerfrom.getMobileno()+"','"+registerfrom.getEmailid()+"','"+registerfrom.getPassword()+"')";
		return jt.update(sql);
	}

	

	public List<RegistrationModel> dologin(Login log) {
		// TODO Auto-generated method stub
		
		String sql ="select * from tbl_staff where Emailid='"+log.getEmailid()+"' and Password='"+log.getPassword()+"'";
		List<RegistrationModel> users = jt.query(sql, new RowMapper<RegistrationModel>() {

			@Override
			public RegistrationModel mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				
				RegistrationModel reg = new RegistrationModel();
				reg.setEmailid(rs.getString("Emailid"));
				reg.setPassword(rs.getString("Password"));
				return reg;
			}
			
		});
		
		List<RegistrationModel> list = users.size()>0? users:null;
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	public int saveacalender(CalenderpanelModel cm) {
		// TODO Auto-generated method stub
		String sql="insert into calendertable(date,eventdetail)values('"+cm.getDate()+"','"+cm.getEventdetail()+"')";
		return jt.update(sql);
	}

	public List<CalenderpanelModel> getcalenderlist() {
		// TODO Auto-generated method stub
		String sql="select * from calendertable";
		return jt.query(sql,new RowMapper<CalenderpanelModel>() {

			@Override
			public CalenderpanelModel mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				CalenderpanelModel cal=new CalenderpanelModel();
				cal.setId(rs.getInt(1));
				cal.setDate(rs.getString(2));
				cal.setEventdetail(rs.getString(3));
				return cal;
			}
		});
	}

	public CalenderpanelModel editcalender(int id) {
		// TODO Auto-generated method stub
		String sql="select * from calendertable where id='"+id+"'";
		return jt.queryForObject(sql, new Object[] {},new BeanPropertyRowMapper<>(CalenderpanelModel.class));
	}

	public int updatecalender(CalenderpanelModel cm) {
		// TODO Auto-generated method stub
		String sql="update calendertable set date='"+cm.getDate()+"',eventdetail='"+cm.getEventdetail()+"' where id='"+cm.getId()+"'";
		return jt.update(sql);
	}

	public int deletecalender(int id) {
		// TODO Auto-generated method stub
		String sql="delete from calendertable where id='"+id+"'";
		return jt.update(sql);
	}

	public int savenotice(NoticeModel nm) {
		// TODO Auto-generated method stub
		String sql="insert into noticetable(notice)values('"+nm.getNotice()+"')";
		return jt.update(sql);
	}

	public List<NoticeModel> viewnotice() {
		// TODO Auto-generated method stub
		String sql="select * from noticetable";
		return jt.query(sql, new RowMapper<NoticeModel>() {

			@Override
			public NoticeModel mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				NoticeModel nm=new NoticeModel();
				nm.setId(rs.getInt(1));
				nm.setNotice(rs.getString(2));
				return nm;
			}
			
		});
	}

	public int deletenotice(int id) {
		// TODO Auto-generated method stub
		String sql="delete from noticetable where id='"+id+"'";
		return jt.update(sql);
	}

	public NoticeModel getnotice(int id) {
		// TODO Auto-generated method stub
		String sql="select * from noticetable where id='"+id+"'";
		return jt.queryForObject(sql, new Object[] {},new BeanPropertyRowMapper<>(NoticeModel.class));
	}

	public int updatenotice(NoticeModel n) {
		// TODO Auto-generated method stub
		String sql="update noticetable set notice='"+n.getNotice()+"' where id='"+n.getId()+"'";
		return jt.update(sql);
	}

	public int uploadacheivement(AcheivementModel am) {
		// TODO Auto-generated method stub
		CommonsMultipartFile file=am.getAfile();
		String filename = file.getOriginalFilename();
		String filepath="C:\\Users\\arpit\\eclipse-workspace\\Fun\\src\\main\\webapp\\acheivementImage";
		try{  
	        byte barr[]=file.getBytes();  
	          
	        BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(filepath+"\\"+filename));  
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	         
	        }catch(Exception e){System.out.println(e);}
		String sql="insert into acheivementtable(imagename)values('"+filename+"')";
		return jt.update(sql);
	}

	public List<AcheivementModel> getacheivementlist() {
		// TODO Auto-generated method stub
		String sql="select * from acheivementtable";
		return jt.query(sql, new RowMapper<AcheivementModel>() {

			@Override
			public AcheivementModel mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				AcheivementModel am=new AcheivementModel();
				am.setId(rs.getInt(1));
				am.setImagename(rs.getString(2));
				return am;
			}
		});
	}

	public int deleteacheieve(int id) {
		// TODO Auto-generated method stub
		String sql="delete from acheivementtable where id='"+id+"'";
		return jt.update(sql);
	}

	public int uploadplacement(PlacementModel pm) {
		// TODO Auto-generated method stub
		CommonsMultipartFile file=pm.getPfile();
		String filename = file.getOriginalFilename();
		String filepath="C:\\Users\\arpit\\eclipse-workspace\\Fun\\src\\main\\webapp\\placementimages";
		try{  
	        byte barr[]=file.getBytes();  
	          
	        BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(filepath+"\\"+filename));  
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	         
	        }catch(Exception e){System.out.println(e);}
		String sql="insert into placementtable(imagename)values('"+filename+"')";
		return jt.update(sql);
	}

	public List<PlacementModel> getplacement() {
		// TODO Auto-generated method stub
		String sql="select * from placementtable";
		return jt.query(sql, new RowMapper<PlacementModel>() {

			@Override
			public PlacementModel mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				PlacementModel pm=new PlacementModel();
				pm.setId(rs.getInt(1));
				pm.setPimagename(rs.getString(2));
				return pm;
			}
		});
	}

	public int deleteplacemet(int id) {
		// TODO Auto-generated method stub
		String sql="delete from placementtable where id='"+id+"'";
		return jt.update(sql);
	}

	public int uploadevent(EventModel em) {
		// TODO Auto-generated method stub
		CommonsMultipartFile file=em.getEfile();
		String filename = file.getOriginalFilename();
		String filepath="C:\\Users\\arpit\\eclipse-workspace\\Fun\\src\\main\\webapp\\eventimages";
		try{  
	        byte barr[]=file.getBytes();  
	          
	        BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(filepath+"\\"+filename));  
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	         
	        }catch(Exception e){System.out.println(e);}
		String sql="insert into eventtable(eventname,imagename)values('"+em.getEventname()+"','"+filename+"')";
		return jt.update(sql);
	}

	public List<EventModel> getevent() {
		// TODO Auto-generated method stub
		String sql="select * from eventtable";
		return jt.query(sql, new RowMapper<EventModel>() {

			@Override
			public EventModel mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				EventModel em=new EventModel();
				em.setId(rs.getInt(1));
				em.setEventname(rs.getString(2));
				em.setEimagename(rs.getString(3));
				return em;
			}
		});
	}

	public EventModel editevent(int id) {
		// TODO Auto-generated method stub
		String sql="select * from eventtable where id='"+id+"'";
		return jt.queryForObject(sql, new Object[] {},new BeanPropertyRowMapper<>(EventModel.class));
	}

	public int updatevent(EventModel ee) {
		// TODO Auto-generated method stub
		
		CommonsMultipartFile file=ee.getEfile();
		String filename = file.getOriginalFilename();
		String filepath="C:\\Users\\arpit\\eclipse-workspace\\Fun\\src\\main\\webapp\\eventimages";
		try{  
	        byte barr[]=file.getBytes();  
	          
	        BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(filepath+"\\"+filename));  
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	         
	        }catch(Exception e){System.out.println(e);}
		String sql="update eventtable set eventname='"+ee.getEventname()+"',imagename='"+filename+"' where id='"+ee.getId()+"'";
		return jt.update(sql);
	}

	public int deleteevent(int id) {
		// TODO Auto-generated method stub
		String sql="delete from eventtable where id='"+id+"'";
		return jt.update(sql);
	}

	
	public List<String> getAllImages() {
		// TODO Auto-generated method stub
		String sql="select imagename from acheivementtable";
		return jt.queryForList(sql,String.class);
	}

	}

