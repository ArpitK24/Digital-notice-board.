package com.mvc;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.mvc.AcheivementModel;
import com.mvc.CalenderpanelModel;
import com.mvc.EventModel;
import com.mvc.NoticeModel;
import com.mvc.PlacementModel;

@Controller
public class MainController {
	
	
	@Autowired
     private MainDao dao;	

		
	
	
	@RequestMapping("hm")
	public String gethome()
	{
		return "home";
	}
	
	
	
	@RequestMapping("display")
	public String index(Model model)
	{
		
		List<NoticeModel> m=dao.viewnotice();
		
		List<CalenderpanelModel> list=dao.getcalenderlist();
		List<EventModel> em=dao.getevent();
		List<AcheivementModel> am1=dao.getacheivementlist();
		List<String> names = dao.getAllImages();
		names.forEach(a->{
			System.out.println("Notice --"+a);
		});
		List<PlacementModel> pm=dao.getplacement();
		model.addAttribute("notice1", m);
		model.addAttribute("calender",list);
		model.addAttribute("acheive",names);
		model.addAttribute("place", pm);
		model.addAttribute("event",em);
	     return "whatever";
	}
	
	
	
	
	
	
	
	@RequestMapping("reg")
	public String getfrom()
	{
		return "registerfrom";
	}
    
	@RequestMapping( value="save",method=RequestMethod.POST)
	public String getregisterd (@ModelAttribute("registerfrom") RegistrationModel registerfrom )
	{
		
		int staus = dao.save(registerfrom);
		return "redirect:/dummy";
	}
	
	@RequestMapping("/dummy")
	public String getregisterd()
	{
		return"registerfrom";
	}
	
	@RequestMapping( value="log",method=RequestMethod.POST)
	public String dologin (@ModelAttribute("log") Login log )
	{
		List<RegistrationModel> list = dao.dologin(log);
		if(list==null)
		{
			return"homepage";
		}
		else {
			return"homepage";
		}
		
	}
	

	
	 
	
	
	
	
	  @RequestMapping("acadmicpanel") public String getapanel() { return
	  "redirect:/viewcalender"; }
	  
	  @RequestMapping("noticepanel") public String getnoticepanel() { return
	  "redirect:/viewnotice"; }
	  
	  @RequestMapping("eventpanel") public String geteventpanel() { return
	  "redirect:/viewacheivement"; }
	  
	  @RequestMapping("placementpanel") public String getplacementpanel() { return
	  "redirect:/viewevent"; }
	  
	  @RequestMapping("acheivepanel") public String
	  getacheivepanel() { return "redirect:/viewacheivement"; }
	  
	  
	  @RequestMapping("back") public String
	  gettohome() { return "homepage"; }
	  
	  
	@RequestMapping(value="saveacalender", method=RequestMethod.POST)
	public String saveaCalender(@ModelAttribute("cm") CalenderpanelModel cm)
	{
		int i=dao.saveacalender(cm);
		//return "";
		return "redirect:/viewcalender";
	}
	@RequestMapping("viewcalender")
	public String viewcalender(Model model)
	{
		List<CalenderpanelModel> list=dao.getcalenderlist();
		model.addAttribute("clist",list);
		return "acalenderpanel";
	}
	@RequestMapping("edit/{id}")
	public String geteditbyid(@PathVariable int id,Model model)
	{
		CalenderpanelModel c=dao.editcalender(id);
		model.addAttribute("editcal",c);
		return "acalenderupdate";
	}
	@RequestMapping(value="updatecalender", method=RequestMethod.POST)
	public String updatecalender(@ModelAttribute("cm") CalenderpanelModel cm)
	{
		int i=dao.updatecalender(cm);
		return "redirect:/viewcalender";
	}
	@RequestMapping("delete/{id}")
	public String deletebyid(@PathVariable int id)
	{
		int i=dao.deletecalender(id);
		return "redirect:/viewcalender";
	}
	@RequestMapping(value="savenotice",method=RequestMethod.POST)
	public String savenotices(@ModelAttribute("nm") NoticeModel nm)
	{
		int i=dao.savenotice(nm);
		return "redirect:/viewnotice";
	}
	@RequestMapping("viewnotice")
	public String viewnotice(Model model)
	{
		List<NoticeModel> m=dao.viewnotice();
		model.addAttribute("nlist", m);
		return "noticepanel";
	}
	@RequestMapping("ndelete/{id}")
	public String deletenotices(@PathVariable int id)
	{
		int i=dao.deletenotice(id);
		return "redirect:/viewnotice";
	}
	@RequestMapping("nedit/{id}")
	public String editnotice(@PathVariable int id,Model model)
	{
		NoticeModel nm=dao.getnotice(id);
		model.addAttribute("enotice", nm);
		return "noticepanelupdate";
	}
	@RequestMapping(value="updatenotice" ,method=RequestMethod.POST)
	public String updatenotices(@ModelAttribute("n") NoticeModel n)
	{
		int i=dao.updatenotice(n);
		return "redirect:/viewnotice";
	}
	@RequestMapping(value="uploadacheivement" ,method=RequestMethod.POST)
	public String uploadacheivement(@ModelAttribute("am") AcheivementModel am)
	{
		int i=dao.uploadacheivement(am);
		return "redirect:/viewacheivement";
	}
	@RequestMapping("viewacheivement")
	public String viewacheive(Model model) 
	{
		List<AcheivementModel> am=dao.getacheivementlist();
		model.addAttribute("alist", am);
		return "eventpanel";
	}
	@RequestMapping("adelete/{id}")
	public String deleteacheieve(@PathVariable int id)
	{
		int i=dao.deleteacheieve(id);
		return "redirect:/viewacheivement";
	}
	@RequestMapping(value="uploadplacement",method=RequestMethod.POST)
	public String uploadplacement(@ModelAttribute("pm") PlacementModel pm)
	{
		int i=dao.uploadplacement(pm);
		return "redirect:/viewplacement";
	}
	@RequestMapping("viewplacement")
	public String viewplacemet(Model model)
	{
		List<PlacementModel> pm=dao.getplacement();
		model.addAttribute("plist", pm);
		return "placementpanel";
	}
	@RequestMapping("pdelete/{id}")
	public String deleteplcement(@PathVariable int id)
	{
		int i=dao.deleteplacemet(id);
		return "redirect:/viewplacement";
	}
	@RequestMapping(value="uploadevent" ,method=RequestMethod.POST)
	public String uploadevent(@ModelAttribute("em") EventModel em)
	{
		int i=dao.uploadevent(em);
		return "redirect:/viewevent";
	}
	@RequestMapping("viewevent")
	public String viewevent(Model model)
	{
		List<EventModel> em=dao.getevent();
		model.addAttribute("evlist", em);
		return"placementpanel";
	}
	@RequestMapping("evedit/{id}")
	public String editevent(@PathVariable int id,Model model)
	{
		EventModel em=dao.editevent(id);
		model.addAttribute("editevent", em);
		return "placementupdate";
	}
	@RequestMapping(value="updateevent",method=RequestMethod.POST)
	public String updateevents(@ModelAttribute("ee") EventModel ee)
	{
		int i=dao.updatevent(ee);
		return "redirect:/viewevent";
	}
	@RequestMapping("evdelete/{id}")
	public String deleteevent(@PathVariable int id)
	{
		int i=dao.deleteevent(id);
		return "redirect:/viewevent";
	}
	}

