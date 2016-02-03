package com.gdf.managedBean;
import com.gdf.ejb.SearchBean;
import com.gdf.ejb.TenderService;
import com.gdf.persistence.Contractor;
import com.gdf.persistence.Review;
import com.gdf.persistence.Tenderer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.primefaces.event.FileUploadEvent;

/**
 * ManagedBean providing contractor informations
 * @author nicolas
 */

@Named(value = "T1")
@ViewScoped

public class TendererBean implements Serializable{
  private long id;
     
     private Tenderer tenderer;
     @EJB
    private SearchBean searchBean;
     
     
  @EJB
    TenderService userServiceLocal;
  
   
    private String log;
    private Tenderer user;
     private Tenderer user1;
    private String password;
    private String emailupd; 
    private Tenderer tendBymail;
    private Tenderer tendByphone;
    private String pwdupdate;
    private String pss=password;
    private String phone;
    byte tableau [] = new byte[1024];
     private String pwd ;

   
   String avatarr;

   
   
   String navigateTo =null;
  String Hidden1;
    /**
     * Creates a new instance of T1
     */
    
   

    public TenderService getUserServiceLocal() {
        return userServiceLocal;
    }

    public void setUserServiceLocal(TenderService userServiceLocal) {
        this.userServiceLocal = userServiceLocal;
    }

    public String getAvatarr() {
        return avatarr;
    }

    public void setAvatarr(String avatarr) {
        this.avatarr = avatarr;
    }
    public TendererBean() {
    }
     public void init() {
        this.tenderer = this.searchBean.searchTendererById(id);
       
    }

     
     
        public void updateUser() {
     //tendBymail=userServiceLocal.getTendbyEmail(user.getEmail());
     //tendByphone=userServiceLocal.gettendByphone(user.getPhone());
     tendBymail=userServiceLocal.getTendbyEmail(tenderer.getEmail());
     tendByphone=userServiceLocal.gettendByphone(tenderer.getPhone());
     //pss=userServiceLocal.getexistingTend(log,password).getPassword();
   //  pss=user.getPassword();
  // user.setPassword(encryptPassword(user.getPassword()));
  pss=encryptPassword(pwd);
     if (tenderer !=null)
         {
                if((tendBymail==null))
                {
                    if((tendByphone==null))
                    {
                        if (tenderer.getPassword().equals(pss))
                        {
                            FacesContext context = FacesContext.getCurrentInstance();
                                context.addMessage(null,
				new FacesMessage("User " + 
						 "Ancien  Mot de Passe    !!!!"));   
                        }
                        else
                        {
                      ok();  
                        }
                    }
                    else
                    {
                         if (tendByphone.getId() != tenderer.getId())
                       {
                           if (tenderer.getPassword().equals(pss))
                           {
                            FacesContext context1 = FacesContext.getCurrentInstance();
                                context1.addMessage(null,
				new FacesMessage("User " + 
						 "ancien mot de passe Phone est déja attribué !!!!"));
                           }
                           else
                           {
                               FacesContext context1 = FacesContext.getCurrentInstance();
                                context1.addMessage(null,
				new FacesMessage("User " + 
						 "Phone est déja attribué !!!!"));
                               
                           }
                       }
                         else
                         { 
                             if (tenderer.getPassword().equals(pss))
                             {
                                 FacesContext context1 = FacesContext.getCurrentInstance();
                                context1.addMessage(null,
				new FacesMessage("User " + 
						 "Ancien  Mot de Passe  !!!!"));
                             }else{
                            ok(); }
                         }
                    }
                    
                }
                else //   tendBymail != null
                {
                    if (tendBymail.getId() == tenderer.getId())
                    {
                         if ((tendByphone==null))
                         {
                             
                           if ( !(tenderer.getPassword().equals(pss)))
                           {
                               ok();
                           }
                           else
                           {
                               FacesContext context2 = FacesContext.getCurrentInstance();
                                context2.addMessage(null,
				new FacesMessage("User " + 
						 "Ancien  Mot de Passe   !!!!")); 
                           }
                             
                         }
                         else
                         {
                             if (tendByphone.getId()==tenderer.getId())
                             {
                                 if (!(tenderer.getPassword().equals(pss)))
                                         {
                                             ok();
                                         }
                                 else
                                 {
                                      FacesContext context3 = FacesContext.getCurrentInstance();
                                context3.addMessage(null,
				new FacesMessage(
						 "Ancien  Mot de Passe  !!!!")); 
                                 }
                             }
                             else
                             {
                                 if((tenderer.getPassword().equals(pss)))
                                 {
                                     
                                     FacesContext context4 = FacesContext.getCurrentInstance();
                                context4.addMessage(null,
				new FacesMessage(
						 "Phone déja attribué & mot de passe ancien!!!!")); 
                                 }
                                 else
                                 {
                                     FacesContext context5 = FacesContext.getCurrentInstance();
                                context5.addMessage(null,
				new FacesMessage("" + 
						 "Phone déja attribué !!!!")); 
                                 }
                             }
                         }
                    }
                    else //tendBymail.getId() != user.getId())
                    {   
                        if ((tenderer.getPassword().equals(pss)) && ((tenderer.getPhone().equals(tendByphone))))
                        {
                            FacesContext context6 = FacesContext.getCurrentInstance();
                                context6.addMessage(null,
				new FacesMessage("" + 
				"phone deja attribué & Mail déja attribué & Ancien Password !!!!"));
                        }
                        if ((!(tenderer.getPassword().equals(pss))) && (tendByphone==null))
                             {
                            FacesContext context6 = FacesContext.getCurrentInstance();
                                context6.addMessage(null,
				new FacesMessage("" + 
				" Mail déja attribué 2 !!!!"));
                        }
                        if (((tenderer.getPassword().equals(pss))) && (tendByphone==null))
                             {
                            FacesContext context6 = FacesContext.getCurrentInstance();
                                context6.addMessage(null,
				new FacesMessage("" + 
				" mail deja attribué ancien password 2 !!!!"));
                        }
 ////vefiiiii !!!!
//                        if ((user.getPassword().equals(pss)) && (!(user.getPhone().equals(tendByphone)) && (user.getId()!= tendByphone.getId()) ))
//                        {
//                            FacesContext context6 = FacesContext.getCurrentInstance();
//                                context6.addMessage(null,
//				new FacesMessage("" + 
//						 "phone deja attribué &  Mail déja attribué & Ancien Password !!!!"));
//                        }
//                          if ((user.getPassword().equals(pss)) && ((user.getPhone().equals(tendByphone))))
//                        {
//                            FacesContext context6 = FacesContext.getCurrentInstance();
//                                context6.addMessage(null,
//				new FacesMessage("" + 
//						 " Mail déja attribué & Ancien Password !!!!"));
//                       }
                        
                        else
                        {
                            if ((tenderer.getPassword().equals(pss)))
                            {
                                
                                if (tenderer.getId()!=tendByphone.getId())
                                {
                                                              
                                
                                 FacesContext context6 = FacesContext.getCurrentInstance();
                                context6.addMessage(null,
				new FacesMessage("" + 
                                 "telephone & Mail déja attribué & ancien password !!!!")); 
                                }
                                else
                                {
                                   
                                   FacesContext context6 = FacesContext.getCurrentInstance();
                                context6.addMessage(null,
				new FacesMessage("" + 
                                 "Mail déja attribué & ancien password !!!!"));  
                                }
                                
                            }
                            else
                            {
                                if ((tenderer.getId()!=tendByphone.getId()) )
                                {
                                     FacesContext context6 = FacesContext.getCurrentInstance();
                                context6.addMessage(null,
				new FacesMessage("" + 
                                        		 "phone & mail  déja attribué !!!!")); 
                                }
                               
                                FacesContext context6 = FacesContext.getCurrentInstance();
                                context6.addMessage(null,
				new FacesMessage("" + 
                                        		 "Mail déja attribué  !!!!"));
                            
                        } 
                     
                    }
                      
                   
//                   if (tendBymail.getId()==user.getId())
//                   {
//                      if (!(user.getPassword().equals(pss)))
//                      {
//                          ok(4);
//                      }
//                      else
//                      {
//                          FacesContext context7 = FacesContext.getCurrentInstance();
//                                context7.addMessage(null,
//				new FacesMessage("User " + 
//						 "ancien  password !!!!")); 
//                      }
//                   }
//                   else
//                   {
//                       if (!(user.getPassword().equals(pss)))
//                       {
//                           FacesContext context8 = FacesContext.getCurrentInstance();
//                                context8.addMessage(null,
//				new FacesMessage("User " + 
//						 "email déja attribué!!!!"));
//                       }
//                       else
//                       {
//                            FacesContext context9 = FacesContext.getCurrentInstance();
//                                context9.addMessage(null,
//				new FacesMessage("User " + 
//						 "email déja attribué  & le il s'agit de l'ancien mot de passe!!!!"));  
//                       }
//                       
//                   } 
                }

         }
     }
     
//      if ((!(user.getPassword().equals(pss)))&&(tendByphone==null)&& (tendBymail!=null)&&(tendBymail.getId()!=user.getId()))
//                            {
//                                FacesContext context6 = FacesContext.getCurrentInstance();
//                                context6.addMessage(null,
//				new FacesMessage("" + 
//                                        		 "Mail déja attribué !!!!"));
//                            }
     }
     
     public void ok(){

            tenderer.setEmail(tenderer.getEmail());
            
                tenderer.setPassword(pwd);
                tenderer.setPhone(tenderer.getPhone());
                tenderer.setFirstname(tenderer.getFirstname());
                tenderer.setLastname(tenderer.getLastname());
                tenderer.setAvatar(tenderer.getAvatar());
                tenderer.setLogin(tenderer.getLogin());
                tenderer.setId(tenderer.getId());
               userServiceLocal.updateTender(tenderer);
               FacesContext context1 = FacesContext.getCurrentInstance();
		context1.addMessage(null,
				new FacesMessage("User " +tenderer.getLogin()
						+ " est modifé avec succée"));
                
//	FacesContext context = FacesContext.getCurrentInstance();
//
//	String viewId = context.getViewRoot().getViewId();
//
//	ViewHandler handler = context.getApplication().getViewHandler();
//
//	UIViewRoot root = handler.createView(context, viewId);
//
//	root.setViewId(viewId);

//	context.setViewRoot(root);
//HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
//response.addHeader("Pragma", "no-cache");
//response.addHeader("Cache-Control", "no-cache");
//response.setHeader("Cache-Control", "no-store");
//response.addHeader("Cache-Control", "must-revalidate");


    }
       public Tenderer getUser() {
        return user;
    }

    public void setUser(Tenderer user) {
        this.user = user;
    }
      public Tenderer getUser1() {
        return user1;
    }

    public void setUser1(Tenderer user1) {
        this.user1 = user1;
    }
      public String getEmailupd() {
        return emailupd;
    }

    public void setEmailupd(String emailupd) {
        this.emailupd = emailupd;
    }
     public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tenderer getTendBymail() {
        return tendBymail;
    }

    public void setTendBymail(Tenderer tendBymail) {
        this.tendBymail = tendBymail;
    }
   
     public String getPwdupdate() {
        return pwdupdate;
    }

    public void setPwdupdate(String pwdupdate) {
        this.pwdupdate = pwdupdate;
    }
     public String getPss() {
        return pss;
    }

    public void setPss(String pss) {
        this.pss = pss;
    }

    public Tenderer getTendByphone() {
        return tendByphone;
    }

    public void setTendByphone(Tenderer tendByphone) {
        this.tendByphone = tendByphone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
      public String getHidden1() {
        return Hidden1;
    }

    public void setHidden1(String Hidden1) {
        this.Hidden1 = Hidden1;
    }
     public String getLog() {
        return log;
    }

    public void setLog(String login) {
        this.log = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
//     public void handleFileUpload(FileUploadEvent event) {
//      try {
//          FacesMessage messages = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
//          FacesContext.getCurrentInstance().addMessage(null, messages);
//          
//          DataOutputStream out = new DataOutputStream(
//                  new BufferedOutputStream(
//                          new FileOutputStream("F:\\Nouveau\\" + event.getFile().getFileName())));
//          out.write(tableau);
//          out.close();
//          //ajoute = true;
//      } catch (IOException ex) {
//          Logger.getLogger(TenderBean.class.getName()).log(Level.SEVERE, null, ex);
//      }
//     }
//     
//      public void handleFileUpload1(FileUploadEvent event) {  
//	        String fileName = event.getFile().getFileName();  
//	        //user.setAvatar(fileName);
//	        
//	 }  
  
   public void upload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        String fileName = event.getFile().getFileName(); 
           tenderer.setAvatar(fileName);
            userServiceLocal.updateTender(tenderer);

       
        System.out.println("a");
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }  
 
    public void copyFile(String fileName, InputStream in) {
           try {
              
              
                // write the inputStream to a FileOutputStream
             String realPath = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
			 
		 OutputStream out = new FileOutputStream(new File( "C:\\Users\\bettaieb\\Desktop\\oops-project\\Oops1\\Oops-web\\src\\main\\webapp\\resources\\images\\" + fileName));
                
//              String out1 = realPath.substring(0,1);
//               System.out.println("out1"+out1);
//               String destination=out1+"resources"+out1+"images"+out1;
//                   
//                    System.out.println("dest"+destination+fileName);
//                OutputStream out = new FileOutputStream((destination+ out1+fileName));
//                     
              
                int read = 0;
                byte[] bytes = new byte[1024];
              
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
              
                in.close();
                out.flush();
                out.close();
              
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public String performAuthetication() {

		String forward = null;
		tenderer = userServiceLocal.getexistingTend(log,password);
               // user = userServiceLocal.getTenderById(2);
               // tend = userServiceLocal.getexistingTend(user.getLogin(), user.getPassword());

//                add(user);
                //user = userServiceLocal.getexistingTend("ahmed", "ahmed");
		if (null != tenderer) {

			if (tenderer instanceof Tenderer) {
                        pss=password;
				forward = "/TenderManagment.xhtml";
                    addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Good login and password.",""));
       

			} 

		} else {

			tenderer = new Tenderer();
		    addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong login and/or password.",""));
                                forward = "/authentification.xhtml";
		}

		return forward;
	}
    public void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	} 
 public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
     private static final String ENCRYPTION_ALGORITHM = "SHA-256";
    public String encryptPassword(String password){
        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm( ENCRYPTION_ALGORITHM );
        passwordEncryptor.setPlainDigest( true );
        return passwordEncryptor.encryptPassword(password);
    }




    

    public Tenderer getTenderer() {
        return tenderer;
    }

    public void setTenderer(Tenderer tenderer) {
        this.tenderer = tenderer;
    }
    
}
