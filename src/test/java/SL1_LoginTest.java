import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.aventstack.extentreports.ExtentReports;

import extent.reports.T3_LoginTests;



public class SL1_LoginTest extends T3_LoginTests{
    //Valid Login Test parameters
    private String vtestName = "Valid Login Test";
    private String vtestInfo = "This test is performed to check login Functionality";
    private String vcategoryName = "Smoke";
    private String vdeviceName = "Chrome Browser";
    private String vpassInfo = "Test Passed user able to login Successfully";
    private String vfailInfo = "User unable to Login";
    private String vscreenShotName = "Login Failed";

    //Invalid Login Test parameters
    private String itestName = "Invalid Login Test";
    private String itestInfo = "This test is performed to check Invalid Login Functionality";
    private String icategoryName = "Smoke";
    private String ideviceName = "Chrome Browser";
    private String ipassInfo = "Test Passed user unable to login";
    private String ifailInfo = "Test Failed user able to login";
    private String iscreenShotName = "Login Success";

    @BeforeTest
    public void initializeReport(){
        extentReport("Login Tests", "SW Login Tests");
    }
    
    @Test (priority = 1)
    public void validLogins() {
        for (String username: validUsernames) {
            login(username, validPassword);
            syncWait(2);
            result(productsShown(), vtestName, vtestInfo, vcategoryName, vdeviceName, vpassInfo, vfailInfo, vscreenShotName);
            syncWait(2);
            logout(username);
        }
    }

    @Test (priority = 2)
    public void invalidLogins() {
        for (int i = 0; i < invalidUsernames.length; i++) {

            for (int j = 0; j < invalidPasswords.length; j++) {

                login(invalidUsernames[i], invalidPasswords[j]);
                syncWait(2);
                result(!productsShown(), itestName, itestInfo, icategoryName, ideviceName, ipassInfo, ifailInfo, iscreenShotName);
                syncWait(3);
                logout(invalidUsernames[i]);
            }
        }
    }


    @AfterTest
    public void showReport() throws Exception {
        super.showReport();
    }
}
