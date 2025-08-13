package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="datadriven")
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		try {
		//HomePage
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
				
		//LoginPage
				
		LoginPage lp=new LoginPage(driver);
		lp.setEmailAddress(email);
		lp.setPassword(pwd);
		lp.clickLogin();
			
		//MyAccountPage
				
		MyAccountPage macc= new MyAccountPage(driver);
	    boolean targetpage=	macc.isMyAccountPageExist();
	    
	/*   Data is valid-- login success- test pass- logout
	                   login failed- test fail
	                   
	   Data is Invalid-- login success- test fail- logout
	                      login failed- test pass
	   */
	    
	    if(exp.equalsIgnoreCase("Valid"))
	    {
	    	if(targetpage==true)
	    	{
	    		macc.clickLogout();
	    		Assert.assertTrue(true);
	    	}
	    	
	    	else
	    	{
	    		Assert.fail();
	    	}
	    }
	        
	    if(exp.equalsIgnoreCase("Invalid"))
	    {
	    	if(targetpage==true)
	    	{
	    		macc.clickLogout();
	    		Assert.assertTrue(false);
	    	}
	    	
	    	else
	    	{
	    		Assert.assertTrue(true);
	    	}
	    }
	    
		}catch(Exception e)
		{
			Assert.fail();
		}
	}

}
