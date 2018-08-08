package com.uiFramework;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.testbase.TestBase;

public class B extends TestBase{
	int i = 1;
	
	@Test
	public void testLogin() {
		if (i == 3) {
			Assert.assertTrue(true);
		}else {
			i++;
			Assert.assertTrue(false);
		}
		
	}

}
