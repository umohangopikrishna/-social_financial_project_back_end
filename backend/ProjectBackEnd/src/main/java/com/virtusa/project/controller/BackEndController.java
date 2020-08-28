package com.virtusa.project.controller;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.project.been.Mail;
import com.virtusa.project.models.Feed_back;
import com.virtusa.project.models.Managerdefinedtb;
import com.virtusa.project.models.UserInfotb;
import com.virtusa.project.models.Usertb;
import com.virtusa.project.models.managertb;
import com.virtusa.project.repository.ManagerdefinedInterface;
import com.virtusa.project.repository.Repo_feedback;
import com.virtusa.project.repository.Repouserinfotb;
import com.virtusa.project.repository.Repousetb;
import com.virtusa.project.repository.managerinfointerface;
import com.virtusa.project.services.mailInterface;

@CrossOrigin(origins = "*")
@RestController
public class BackEndController extends TimerTask {
    @Autowired
	private Repousetb repotb_obj;
    @Autowired
    private Repouserinfotb repouserinfo_obj;
    
    @Autowired
    private ManagerdefinedInterface repo_managerdefinedocc;
    
    @Autowired
    private managerinfointerface repo_managerinfo;
    
    @Autowired
    private Repo_feedback repofeed_back_obj;
    
    /*---- video logic test ------------------
    
    
    public static List<String> img_base64 = new ArrayList<String>();
    
    @PostMapping("/set_video_stream")
    public List<String> set_video_stream(@RequestBody String img)
    {
    	img_base64.add("\""+img+"\"");
    	
    	return(img_base64);
    }
    @GetMapping("/get_video_stream")
    public List<String> get_video_stream()
    {
    	List<String> img_str_dul = new ArrayList<String>(img_base64);
    	img_base64.clear();
    	return(img_str_dul);
    }
    
    -------------end video logic test ------*/
    
    @PostMapping("/reguser")
    public Usertb adduser(@RequestBody Usertb userobj)
    {  System.out.println(userobj);
    	try
    	{
    	return repotb_obj.save(userobj);
    	
    	}
      catch(Exception e)
    {
    	  return userobj;
    }
    
    }
    
    @PostMapping("/verifyuser")
    public String verifyuser(@RequestBody Usertb userobj)
    {
    	List<Usertb> userlist = repotb_obj.findAll();
    	String flat_no="\"0,";
    	String email = "0,";
    	String phonenumber = "0\"";
    	System.out.println(userobj.getFlat_no()+" "+userobj.getEmailId()+" "+userobj.getPhoneNumber()+"===============================");
    	for(int i=0;i<userlist.size();i++)
    	{
    		if(userlist.get(i).getFlat_no().trim().equals(userobj.getFlat_no().trim()))
    		{
    			flat_no="\"1,";
    		}
    		if(userlist.get(i).getEmailId().trim().equals(userobj.getEmailId().trim())) 
    		{
    			email ="1,";
    		}
    		if(userlist.get(i).getPhoneNumber().trim().equals(userobj.getPhoneNumber().trim()))
    		{
    			phonenumber = "1\"";
    		}
    	}
    	return(flat_no+email+phonenumber);
    }
     
 
    
	
//-----------------------------------------------------------------------------------------------

 @GetMapping("/getting_manager_information/")
 public String getting_manager_information()
 {
	 String manager_data = new String("");
	 try {
	 managertb managertb_obj = repo_managerinfo.findAll().get(0);
	 
	 manager_data ="\""+ managertb_obj.getManager_name()+","+managertb_obj.getManager_gmail()+","+managertb_obj.getPhone_number()+"\"";
	 
	   }
	 catch(Exception e)
	 {
		 manager_data="\"\"";
	 }
	 
	 return(manager_data);
}
    

@GetMapping("/userauth/{flat_id}/{password}")
public boolean finduser( @PathVariable String flat_id ,@PathVariable String password)
    {
	
	List<Usertb> userlist = repotb_obj.findAll();
	System.out.println(flat_id+" "+password);
	for(int i=0;i<userlist.size();i++)
	{   
		if(userlist.get(i).getFlat_no().trim().equals(flat_id.trim()) && userlist.get(i).getPassword().trim().equals(password.trim()))
			{
			  return true;
			}
	}
	
	return false;
    	
    }

@DeleteMapping("/userdelet/{flat_id}")
public boolean deletuser(@PathVariable String flat_id)
{
    List<Usertb> userlist = repotb_obj.findAll();
    boolean fg1=false;
    boolean fg2=false;
    if(flat_id==null)
    	return(false);
    for(int i=0;i<userlist.size();i++)
    {   if(userlist.get(i).getFlat_no() == null)
    	continue;
    	if(userlist.get(i).getFlat_no().trim().equals(flat_id.trim()))
    	{  fg1 = true;
    		repotb_obj.delete(userlist.get(i));
    	}
    }
    
    List<UserInfotb> userinfolist = repouserinfo_obj.findAll();
    for(int i=0;i<userinfolist.size();i++)	
    { if(userinfolist.get(i).getFlat_id().trim().equals(flat_id.trim()))
    	{ fg2 = true;
    	repouserinfo_obj.delete(userinfolist.get(i));
    	}
    }

    return fg1;
}


@GetMapping("/annual_expenditure_manager_side")
public List<Integer> annual_expenditure_manager_side()
{
	String pattern = "MM-dd-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());
	int year = Integer.parseInt(date.substring(6, 10));
	
	int s1=0,s2=0,s3=0,s4=0,s5=0,s6=0;
	
	List<UserInfotb> userinfolist = repouserinfo_obj.findAll();
	
	for(int i=0;i<userinfolist.size();i++)
	{
		if(userinfolist.get(i).getDatatopay().trim().endsWith(year+""))
		{
			s6=s6+userinfolist.get(i).getPaymoney();
		}
		else if(userinfolist.get(i).getDatatopay().trim().endsWith((year-1)+""))
		{
			s5=s5+userinfolist.get(i).getPaymoney();
		}
		else if(userinfolist.get(i).getDatatopay().trim().endsWith((year-2)+""))
		{
			s4=s4+userinfolist.get(i).getPaymoney();
		}
		else if(userinfolist.get(i).getDatatopay().trim().endsWith((year-3)+""))
		{
			s3=s3+userinfolist.get(i).getPaymoney();
		}
		else if(userinfolist.get(i).getDatatopay().trim().endsWith((year-4)+""))
		{
			s2=s2+userinfolist.get(i).getPaymoney();
		}
		else if(userinfolist.get(i).getDatatopay().trim().endsWith((year-5)+""))
		{
			s1=s1+userinfolist.get(i).getPaymoney();
		}
	}
	
	List<Integer> annual_amount_list = new ArrayList<Integer>();
	annual_amount_list.add(s1);
	annual_amount_list.add(s2);
	annual_amount_list.add(s3);
	annual_amount_list.add(s4);
	annual_amount_list.add(s5);
	annual_amount_list.add(s6);
	
	return(annual_amount_list);

}


@GetMapping("/late_paid_users")
public List<String> late_paid_users()
{
   
	List<UserInfotb> user_monthly_data = repouserinfo_obj.findAll();
	
	HashMap<String, Integer> late_pay_find = new HashMap<String, Integer>();
	List<String> flat_id_late_day_list = new ArrayList<String>();
	
	String pattern = "MM-dd-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());
	boolean include_fg=false;
	int late_val=0;
	
	
	
	for(int i=0;i<user_monthly_data.size();i++)
	{
		int day =Integer.parseInt(date.substring(3,5));
		if(user_monthly_data.get(i).getDatatopay().trim().endsWith(date.substring(6,10)) && (user_monthly_data.get(i).getDatatopay().trim().startsWith(date.substring(0,2))||(Integer.parseInt(user_monthly_data.get(i).getDatatopay().trim().substring(0,2))==(Integer.parseInt(date.substring(0,2))-1)))) 
		{
			System.out.println(user_monthly_data.get(i).getDatatopay()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			if(user_monthly_data.get(i).getPaymode()==0 && (day>10) ) 
			{
				
				include_fg=true;
				late_val=day;
			}else if(Integer.parseInt(date.substring(3,5))>10)
			{
				late_val = Integer.parseInt(user_monthly_data.get(i).getPaydata().substring(3,5));
				if(late_val>10)
				{
					include_fg=true;

				}
			}
			
			if(include_fg) {
		try
		{
			int c=late_pay_find.get(user_monthly_data.get(i).getFlat_id());	
			late_pay_find.put(user_monthly_data.get(i).getFlat_id(), c+late_val);
			
		}
		catch(Exception e)
		{
			late_pay_find.put(user_monthly_data.get(i).getFlat_id(), late_val);

		}
		
			}
			
			include_fg=false;
			late_val=0;
		
		}
		
		}
	
	for (String i : late_pay_find.keySet()) 
	    {
		String flat_id_late_day = i+","+late_pay_find.get(i);
		flat_id_late_day_list.add(flat_id_late_day);
	    }
	
	
		return(flat_id_late_day_list);
	}


@GetMapping("/delet_manager")
public boolean delet_manager()
{
try {
    repo_managerinfo.deleteAll();
return(true);   
}
catch(Exception e)
{
return(false);
}


}


@GetMapping("/payverfication/{flat_id}")
public int payverify(@PathVariable String flat_id)
{   
	String pattern = "MM-dd-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());
	System.out.println(date);
  	List<UserInfotb> userinfolist = repouserinfo_obj.findAll();
  	try {
  	for(int i=0;i<userinfolist.size();i++)
  	{
  		if(userinfolist.get(i).getDatatopay().trim().startsWith(date.substring(0, 2))&&userinfolist.get(i).getDatatopay().trim().endsWith(date.substring(6, 10)) && userinfolist.get(i).getFlat_id().trim().equals(flat_id.trim()))
  		{
  			return(userinfolist.get(i).getPaymode());
  		}	
  		
  	}
  	}
  	catch(Exception e)
  	{
  		return(3);
  	}
  	return(3);
}

@GetMapping("/adduserinfo/{flat_id}/{fund}")
public boolean adduserinfo(@PathVariable String flat_id ,@PathVariable int fund)
{
	List<UserInfotb> userinfo_list = repouserinfo_obj.findAll();
	String pattern = "MM-dd-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());
	try
	{
       
		for(int i=0;i<userinfo_list.size();i++)
		{
			if(userinfo_list.get(i).getDatatopay().trim().startsWith(date.substring(0,2))&&userinfo_list.get(i).getDatatopay().trim().endsWith(date.substring(6,10))&&userinfo_list.get(i).getFlat_id().trim().equals(flat_id))
			{
				System.out.println(userinfo_list.get(i).getFlat_id());
				
				UserInfotb userinfotb_obj =  repouserinfo_obj.findById(userinfo_list.get(i).getId()).get();
				userinfotb_obj.setFlat_id(flat_id);
				userinfotb_obj.setPaydata(date);
				userinfotb_obj.setPaymode(1);
				userinfotb_obj.setPaymoney(fund);
				repouserinfo_obj.save(userinfotb_obj);
				
			}
		}
		return true ;
	}
  catch(Exception e)
{
	  return false ;
}
	
}
/*public Product updateProduct(Product product)
{
	Product exi=repo.findById(product.getId()).orElse(null);
	exi.setName(product.getName());
	exi.setPrice(product.getPrice());
	exi.setQuantity(product.getQuantity());
	return repo.save(exi);
	
}
*/

@GetMapping("/viewuserdata/{date}/{flat_id}")
public List<UserInfotb> viewuserdata(@PathVariable String date , @PathVariable String flat_id)
{
	List<UserInfotb> userrecordlist = repouserinfo_obj.findAll();
	List<UserInfotb>userrecord = new ArrayList<UserInfotb>();
	for(int i=0;i<userrecordlist.size();i++)
	{
		if(userrecordlist.get(i).getDatatopay().trim().startsWith(date.substring(0, 2)) && userrecordlist.get(i).getDatatopay().trim().endsWith(date.substring(6, 10)) && userrecordlist.get(i).getFlat_id().trim().equals(flat_id))
		{   userrecord.add(userrecordlist.get(i));
			return(userrecord);
		}
	}
	return(userrecord);
	
}

@GetMapping("/viewoccuation_and_fund")
public String getoccusionname_and_fund()
{
	String pattern = "MM-dd-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());
	
	System.out.println(date+"+++++++++++++++++++++++++++++++++++++++++++");
  	List<Managerdefinedtb> Managerdefinedtb_objs = repo_managerdefinedocc.findAll();
  	Managerdefinedtb manager_defined_tb_obj = new Managerdefinedtb();
  	for(int i=0;i<Managerdefinedtb_objs.size();i++)
  	{
  		if(Managerdefinedtb_objs.get(i).getFund_day().trim().startsWith(date.substring(0,2)) && Managerdefinedtb_objs.get(i).getFund_day().trim().endsWith(date.substring(6,10)))
  			{
  			manager_defined_tb_obj=Managerdefinedtb_objs.get(i);
  			break;
  			}
  	}
  	
  	String occuation_and_fund = manager_defined_tb_obj.getOccuation();
  	
  	occuation_and_fund = occuation_and_fund +"||"+manager_defined_tb_obj.getFund();
  		
	System.out.println(occuation_and_fund+"+++++++++++++++++++++++++++++++++++++++++++");

    return("\""+occuation_and_fund+"\"");
	
}

  
@PostMapping("/regmanager")
public managertb addmanager(@RequestBody managertb manager_reg_obj)
{  System.out.println(manager_reg_obj);
	try
	{
	return repo_managerinfo.save(manager_reg_obj);
	
	}
  catch(Exception e)
{
	  return manager_reg_obj;
}

}

@GetMapping("/verifymanager")
public boolean verifymanager()
{
	int manager_activate = repo_managerinfo.findAll().size();
	return(manager_activate==0);
	
}
	
@GetMapping("/verify_month_info")
public boolean verify_month_fund()
{
	String pattern = "MM-dd-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());
	List<Managerdefinedtb>month_list =  repo_managerdefinedocc.findAll();
	for(int i=0;i<month_list.size();i++)
	{
		if(month_list.get(i).getFund_day().trim().startsWith(date.substring(0,2))&&month_list.get(i).getFund_day().trim().endsWith(date.substring(6,10)))
			return true;
	}
	return false;
	
}

// if verify_month_fund is true then we not go to add_moth_info

@PostMapping("/add_month_info")
public Managerdefinedtb add_month_info(@RequestBody Managerdefinedtb managerdef )
{  System.out.println(managerdef);
        
       List<Usertb> userlist = repotb_obj.findAll();
       int record_len =userlist.size();

	//List<UserInfotb> userinfolist = repouserinfo_obj.findAll();

	for(int i=0;i<record_len;i++)
	{
	        	UserInfotb UserInfotb_obj = new UserInfotb();
				UserInfotb_obj.setDatatopay(managerdef.getFund_day());
		       UserInfotb_obj.setPaydata("");
		       UserInfotb_obj.setFlat_id(userlist.get(i).getFlat_no());
		       UserInfotb_obj.setOccuation(managerdef.getOccuation());
		       UserInfotb_obj.setPaymode(0);
		       UserInfotb_obj.setPaymoney(0);
		       repouserinfo_obj.save(UserInfotb_obj);
		       
	}

	try
	{
	return repo_managerdefinedocc.save(managerdef);
	
	}
  catch(Exception e)
{
	  return managerdef;
}

}

@GetMapping("/mode_use_pay/{date}")
public List<String> users_paymode(@PathVariable String date)
{
	
	System.out.println(date);
	List<String> user_paymode= new ArrayList<String>(); 
  	List<UserInfotb> userinfolist = repouserinfo_obj.findAll();
  	//int record_len = repotb_obj.findAll().size();
  	//int c=0;
  	date = date.trim();
  	for(int i=0;i<userinfolist.size();i++)
  	{
  		if(userinfolist.get(i).getDatatopay().trim().startsWith(date.substring(0, 2))&&userinfolist.get(i).getDatatopay().trim().endsWith(date.substring(6, 10)))
  		{ 
  			String st = userinfolist.get(i).getFlat_id()+","+userinfolist.get(i).getPaymode()+","+userinfolist.get(i).getId();
  			user_paymode.add(st);
  			//c++;
  		}	
  		
  	}
  	return(user_paymode);
	
}

@GetMapping("/manager_mode_change/{id}")
public boolean change_mode_pay(@PathVariable int id)
{ try {
    UserInfotb userinfotb_obj = repouserinfo_obj.findById(id).get();
    userinfotb_obj.setPaymode(2);
    repouserinfo_obj.save(userinfotb_obj);
    return(true);
    }
catch(Exception e)
    {
	return(false);
	}
    
}
@GetMapping("/update_password/{psd}/{flat_id}")
public boolean set_password(@PathVariable String psd , @PathVariable String flat_id )
{
	List<Usertb> user_list = repotb_obj.findAll();
	
	for(int i=0;i<user_list.size();i++)
	{
		if(user_list.get(i).getFlat_no().trim().equals(flat_id.trim()))
		{
			
			 Usertb	temp_user_obj = user_list.get(i);
			 temp_user_obj.setPassword(psd);
			 repotb_obj.save(temp_user_obj);
			return(true);
			
		}
	}
	return(false);
	
}
@GetMapping("/check_detail_send_otp/{flat_id}/{email}")
public boolean verfiy_to_send_otp(@PathVariable String flat_id , @PathVariable String email)
{
	List<Usertb> user_list = repotb_obj.findAll();
	
	for(int i=0;i<user_list.size();i++)
	{
		if(user_list.get(i).getFlat_no().trim().equals(flat_id.trim()))
		{
			return(user_list.get(i).getEmailId().equals(email));
		}
	}
	return(false);
	
	
}

@GetMapping("/user_info_visualization/{falt_id}/{year}")
public List<String> user_info_visualization(@PathVariable String falt_id , @PathVariable String year )
{
	List<UserInfotb> user_year_info = repouserinfo_obj.findAll();
	List<String> user_year_month_list = new ArrayList<String>();
	
	for(int i=0;i<user_year_info.size();i++)
	{
		if(user_year_info.get(i).getFlat_id().trim().equals(falt_id.trim()) && user_year_info.get(i).getDatatopay().trim().endsWith(year.trim()) && user_year_info.get(i).getPaymode() == 2)
		{
			String st = user_year_info.get(i).getOccuation() +"||"+user_year_info.get(i).getPaydata()+"||"+user_year_info.get(i).getPaymoney();
			user_year_month_list.add(st);
		}
		
	}
	
return(user_year_month_list);	
}

@GetMapping("/total_fund")
public List<String> total_fund()
{
	List<UserInfotb> userinfo_list = repouserinfo_obj.findAll();
	String pattern = "MM-dd-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());
	int total_count = 0;
	int payed_count = 0;
	int unconformed = 0;
	int total_amount =0;
	String payed_user_list=new String("");
	String unconformed_user_list=new String("");
	String not_payed_user_list=new String("");

	for(int i=0;i<userinfo_list.size();i++)
	{
		//System.out.println(userinfo_list.get(i).getDatatopay());
		if(userinfo_list.get(i).getDatatopay().trim().startsWith(date.substring(0,2)) && userinfo_list.get(i).getDatatopay().trim().endsWith(date.substring(6,10)))
		{
			if(userinfo_list.get(i).getPaymode()==2)
			{
				payed_count++;
				payed_user_list=payed_user_list+","+userinfo_list.get(i).getFlat_id();
				total_amount = total_amount+userinfo_list.get(i).getPaymoney();
			}else if(userinfo_list.get(i).getPaymode()==1)
			{
				unconformed_user_list=unconformed_user_list+","+userinfo_list.get(i).getFlat_id();
				unconformed++;
			}else
			{
				not_payed_user_list=not_payed_user_list+","+userinfo_list.get(i).getFlat_id();

			}
			
				total_count++;	
		}
	}
	String count_data = new String("");
	count_data = total_count+","+payed_count+","+unconformed+","+total_amount;
	
	List<String> payment_description = new ArrayList<String>();
	
	payment_description.add(payed_user_list);
	payment_description.add(unconformed_user_list);
	payment_description.add(not_payed_user_list);
	payment_description.add(count_data);
	
	return(payment_description);
}


@GetMapping("/year_sending money/{year}")
public  String[] year_spend_analysis(@PathVariable int year)
{
  String[] year_analysis = new String[12];
  int[] amount = new int[12];
  for(int i=0;i<12;i++)
  { 
	  year_analysis[i]="";
      amount[i]=0;
  }
  
  List<UserInfotb> user_payment_description = repouserinfo_obj.findAll();
  
  for(int i=0;i<user_payment_description.size();i++)
  {
	  if(user_payment_description.get(i).getPaydata().trim().endsWith(""+year+""))
	  {
		  int month = Integer.parseInt(user_payment_description.get(i).getPaydata().substring(0,2));
		  amount[month-1]=amount[month-1]+user_payment_description.get(i).getPaymoney();
	  }
  }
  
  List<Managerdefinedtb> manager_month_info =  repo_managerdefinedocc.findAll();
  
  for(int i=0;i<manager_month_info.size();i++)
  {
	  if(manager_month_info.get(i).getFund_day().trim().endsWith(""+year+""))
	  {
		  int month = Integer.parseInt(manager_month_info.get(i).getFund_day().substring(0,2));
		  year_analysis[month-1]=manager_month_info.get(i).getOccuation();
		  
	  }
  }
  
  
  for(int i=0;i<12;i++)
  {
	  year_analysis[i]=year_analysis[i]+"||"+amount[i];
  }
	  
  
  
  return(year_analysis);
}

@GetMapping("/manager_auth/{manager_id}/{manager_psd}")
public boolean manager_auth(@PathVariable String manager_id , @PathVariable String manager_psd)
{
  List<managertb> managertb_list = repo_managerinfo.findAll();
  
  for(int i=0;i<managertb_list.size();i++)
  {  
	  System.out.println(manager_id.trim().length()+"**"+managertb_list.get(i).getManager_id().trim().length()+"**"+managertb_list.get(i).getManager_password().trim().length()+"**"+manager_psd.trim().length());
	  if(managertb_list.get(i).getManager_id().trim().equals(manager_id.trim()) && managertb_list.get(i).getManager_password().trim().equals(manager_psd.trim()))
	  {
		  System.out.println("hello----------");
		  return(true);
	  }
  }
  return(false);
	
}

@GetMapping("forget_data_manage_verfication/{manager_id}/{email_id}")
public boolean manager_forget_date_verfiy(@PathVariable String manager_id , @PathVariable String email_id )
{
  List<managertb> managertb_list = repo_managerinfo.findAll();

  for(int i=0;i<managertb_list.size();i++)
  {
	  if(managertb_list.get(i).getManager_id().trim().equals(manager_id.trim()) && managertb_list.get(i).getManager_gmail().trim().equals(email_id.trim()))
      {
    	  return(true);
      }
  }
  
  return(false);

}

@GetMapping("update_manager_psd/{manager_id}/{reset_psd}")
public boolean manager_password_update(@PathVariable String manager_id , @PathVariable String reset_psd)
{
 
	List<managertb> managertb_list = repo_managerinfo.findAll();
	
	for(int i=0;i<managertb_list.size();i++)
	{
		if(managertb_list.get(i).getManager_id().trim().equals(manager_id.trim()))
		{
			managertb managertb_obj = managertb_list.get(i);
			managertb_obj.setManager_password(reset_psd);
			repo_managerinfo.save(managertb_obj);
			return(true);
		}
	}
	return(false);
	
}

// ---------- feed back ----------------

@PostMapping("feed_back_send/")
public boolean feed_back_send(@RequestBody Feed_back feed_back_user_send_obj)
{
    try {
	repofeed_back_obj.save(feed_back_user_send_obj);
    return(true);
    }
    catch(Exception e)
    {
    	return(false);
    }
}

@GetMapping("feed_back_get/{id_no}")
public List<Feed_back> feed_back_get(@PathVariable int id_no )
{
	List<Feed_back> feed_back_data = repofeed_back_obj.findAll();

	Collections.sort(feed_back_data, new Comparator<Feed_back>() {
		@Override
		public int compare(Feed_back p1, Feed_back p2) {
			return p1.getId() - p2.getId();
		}
	});
	
	Feed_back feed_back_obj = new Feed_back(feed_back_data.size(),"","","");

    
	while( feed_back_data.size()>0 )
	{
		if(feed_back_data.get(0).getId()<=id_no)
		{
			feed_back_data.remove(0);
		}else
		{
			break;
		}
	}
	feed_back_data.add(feed_back_obj);

	return(feed_back_data);
	
	
}

@GetMapping("online_get/{flat_id}")
public List<String> online_users_manager(@PathVariable String flat_id)
{
	
	
	
List<String> online_users_manager_list = new ArrayList<String>();

managertb manager = repo_managerinfo.findAll().get(0);
if(manager.getManager_id().trim().equals(flat_id.trim()))
{
manager.setOnline(true);
repo_managerinfo.save(manager);
}
online_users_manager_list.add(manager.isOnline()+","+manager.getManager_id()+","+"manager");// for indecateing he is a manager 	

List<Usertb> user_list = repotb_obj.findAll();
for(int i=0;i<user_list.size();i++)
{
	Usertb user_obj = repotb_obj.findById(user_list.get(i).getId()).get();	
	
	if(user_list.get(i).getFlat_no().trim().equals(flat_id.trim()))
	{
		user_obj.setOnline(true);
	    repotb_obj.save(user_obj);
		online_users_manager_list.add(user_list.get(i).isOnline()+","+user_list.get(i).getFlat_no());
	}
	else
	{
		online_users_manager_list.add(user_list.get(i).isOnline()+","+user_list.get(i).getFlat_no());

	}
}

return(online_users_manager_list);	
}

@GetMapping("reset_chat/")
public boolean reset_chat()
{

	try
	{
		repofeed_back_obj.deleteAll();
		return(true);
	}
	catch(Exception e)
	{
		return(false);
	}
}

@GetMapping("refresh_online_users/")
public boolean refresh_online()
{   
	List<Usertb> user_list = repotb_obj.findAll();
	try {
			for(int i=0;i<user_list.size();i++)
			{
	   
				Usertb user_obj = repotb_obj.findById(user_list.get(i).getId()).get();	
				user_obj.setOnline(false);
				repotb_obj.save(user_obj);
				

			}
			managertb manager = repo_managerinfo.findAll().get(0);
			manager.setOnline(false);
			repo_managerinfo.save(manager);
			
			return(true);
		}
	catch(Exception e)
	{
		return(false);
	}
}


//------- end feed back-------------------



@GetMapping("/verify_manager_month_info_updateion") // it will check wheather month info is add or not for the current month

public boolean verify_manager_month_info()
{

	String pattern = "MM-dd-yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String date = simpleDateFormat.format(new Date());
	
	List<Managerdefinedtb> manager_month_info_list = repo_managerdefinedocc.findAll();
	
	for(int i=0;i<manager_month_info_list.size();i++)
	{
		if(manager_month_info_list.get(i).getFund_day().trim().startsWith(date.substring(0,2)) && manager_month_info_list.get(i).getFund_day().trim().endsWith(date.substring(6,10)))
		{
			return(true);
		}
	}
	return(false);
	
}


@GetMapping("/manager_gmail")
public String get_manager_gmail()
{
  return("\""+repo_managerinfo.findAll().get(0).getManager_gmail()+"\"");
    
}

@GetMapping("/sendgmail/{otp}/{mode_of_user}/{emailid}")
public String sendgmaile(@PathVariable int otp , @PathVariable int mode_of_user , @PathVariable String emailid )
{   
	 Mail mail = new Mail();
     mail.setMailFrom("ramakrishna711999@gmail.com");
     mail.setMailTo(emailid);
     if(mode_of_user==0) // forget password
     {
    	 mail.setMailSubject("RESET YOUR SWEET HOME's PASSWORD");
    	 mail.setMailContent("<img src='https://www.brandcrowd.com/gallery/brands/pictures/picture13381230327004.jpg'>"
    	     		+ "<h3> OTP :-</h3><h4>"+otp+"</h4>");
     }else if(mode_of_user == 1)
     {
    	 mail.setMailSubject("CONFRIM OTP TO DELETE YOUR/'s ACCOUNT !!!!");
    	 mail.setMailContent("<img src='https://www.brandcrowd.com/gallery/brands/pictures/picture13381230327004.jpg'> <h1> WE MISS YOU ALOT :-(</h1>"
    	     		+ "<h3> OTP :-</h3><h4>"+otp+"</h4>");
     }else if(mode_of_user == 2)
     {
    	 mail.setMailSubject("RESET YOUR MANAGER SWEET HOME's PASSWORD");
    	 mail.setMailContent("<img src='https://www.brandcrowd.com/gallery/brands/pictures/picture13381230327004.jpg'>"
    	     		+ "<h3> OTP :-</h3><h4>"+otp+"</h4>");
     }else if(mode_of_user ==3)
     {
    	 mail.setMailSubject("Verfiy User Registration SWEET HOME's ");
    	 mail.setMailContent("<h1>My Dear Manager Show Otp To User, </h1><img src='https://www.brandcrowd.com/gallery/brands/pictures/picture13381230327004.jpg'>"
    	     		+ "<h2> OTP :-</h2><h2>"+otp+"</h2>");
     }
     
     ApplicationContext  context = mail.getContext(); 
     mailInterface mailService = (mailInterface) context.getBean("mailService");
     System.out.println(context);
     mailService.sendEmail(mail);
     
     return("\"mail is send\"");
     
}

@GetMapping("/user_mail_id/{flat_id}")
public String get_user_emailid(@PathVariable String flat_id)
{
List<Usertb> user_list = repotb_obj.findAll();

for(int i=0;i<user_list.size();i++)
{
if(user_list.get(i).getFlat_no().trim().equals(flat_id.trim()))
{
return("\""+user_list.get(i).getEmailId()+"\"");
}
}
return("");
}
	




/*--------time scheudular ---------------*/


Timer timer;
static ArrayList<String> user_mail_id; 
static TimerTask  backend_controller_obj;

public void fun( TimerTask  obj,int seconds) {
    timer = new Timer();
    timer.schedule( obj , seconds*1000);
    System.out.println(":-)");
}

@GetMapping("/mail_schedualer/{current_hour}")
public boolean main_caller(@PathVariable int current_hour) {
	TimerTask  obj= new BackEndController();
	backend_controller_obj = obj;
	
  System.out.println("Task scheduled. "+current_hour);
  List<Usertb> usertb_list = repotb_obj.findAll();
  user_mail_id = new ArrayList<String>();
  for(int i=0;i<usertb_list.size();i++)
  {
	  user_mail_id.add(usertb_list.get(i).getEmailId());
  }
  
  int hours = 24-(current_hour+1);  // in typescript the gethours method return hours number 0 to 23 ,so i add 1 to current hour
  
  System.out.println(hours+" "+current_hour);
     fun(obj,hours*60*60);
      return(true);

                                        }

    public void run() 
    {
        System.out.println("Time's up!");
        
        String pattern = "MM-dd-yyyy";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	String date = simpleDateFormat.format(new Date());
        
        int day = Integer.parseInt(date.substring(3,5));
        System.out.println("day -----------------"+day);
        if(day>=5 && day<=10)
        {
        reminder_user_list();
        System.out.println("send mail completed -----------------");

        }
        //Terminate the timer thread
        //fun(backend_controller_obj,24*60*60);
       //gmail_reminder("umgkrishna00@gmail.com");
    try {
        timer.cancel();
    }
    catch(Exception e)
    {
    	
    }
        System.out.println("==============================================================================");
    	TimerTask  obj= new BackEndController();
    	backend_controller_obj =obj;
        fun(backend_controller_obj,24*60*60);

    }
    
    public void reminder_user_list()
    {
     
     for(int i=0;i<user_mail_id.size();i++)
     {
    	// System.out.println(usertb_list.get(i).getEmailId());
    	 gmail_reminder(user_mail_id.get(i));
     }
          
    
    }
    
 
    public void gmail_reminder(String emailid )
    {   
    	try {
    	
    	 Mail mail = new Mail();
         mail.setMailFrom("ramakrishna711999@gmail.com");
         mail.setMailTo(emailid);
         
         
        	 mail.setMailSubject("PAY MONTHLY SERVICES MONEY");
        	 mail.setMailContent("<img src='https://www.brandcrowd.com/gallery/brands/pictures/picture13381230327004.jpg'>"
        	 		+ "<h1> Monthly Services Money Need To Be Payed</h1>"
        	 		+ "<h3 style='color:red'> 100 RS Fine To Be Pay For Every Day After The 10th day </h3>");
         
         
         ApplicationContext  context = mail.getContext(); 
         mailInterface mailService = (mailInterface) context.getBean("mailService");
         System.out.println(context);
         mailService.sendEmail(mail);
         
    	}
    	catch(Exception e)
    	{
    		//gmail_reminder(emailid);
    		TimerTask  obj= new BackEndController();
        	backend_controller_obj =obj;
            fun(backend_controller_obj,5*60);

    	}
    }

}

