package com.way2automation.testsuite;

import com.way2automation.pages.*;
import com.way2automation.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BankTest extends BaseTest {
    HomePage homePage;
    BankManagerLoginPage bankManagerLoginPage;
    AddCustomerPage addCustomerPage;
    CustomersPage customersPage;
    OpenAccountPage openAccountPage;
    CustomerLoginPage customerLoginPage;
    AccountPage accountPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        bankManagerLoginPage = new BankManagerLoginPage();
        addCustomerPage = new AddCustomerPage();
        customersPage = new CustomersPage();
        openAccountPage = new OpenAccountPage();
        customerLoginPage = new CustomerLoginPage();
        accountPage = new AccountPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void bankManagerShouldAddCustomerSuccessfully() {
        //click On "Bank Manager Login" Tab
        homePage.clickOnBankManagerLoginTab();
        //click On "Add Customer" Tab
        bankManagerLoginPage.clickOnAddCustomerTab();
        //enter FirstName
        addCustomerPage.enterFirstName("Abc");
        //enter LastName
        addCustomerPage.enterLastName("Xyz");
        //enter PostCode
        addCustomerPage.enterPostcode("EC1B 2JL");
        //click On "Add Customer" Button
        addCustomerPage.clickOnAddCustomerButton();
        //popup display
        //verify message "Customer added successfully"
        Assert.assertTrue(bankManagerLoginPage.getPopupMsg().contains("Customer added successfully"),
                "Message not found");
        //click on "ok" button on popup
        bankManagerLoginPage.clickOKOnPopup();
    }

    @Test(groups = {"sanity", "regression"})
    public void bankManagerShouldOpenAccountSuccessfully() {
        //click On "Bank Manager Login" Tab
        homePage.clickOnBankManagerLoginTab();
        //click On "Open Account" Tab
        bankManagerLoginPage.clickOnOpenAccountTab();
        //Search customer that created in first test
        openAccountPage.searchAndSelectCreatedCustomer("Harry Potter");
        //Select currency "Pound"
        openAccountPage.selectPoundInCurrency("Pound");
        //click on "process" button
        openAccountPage.clickOnProcessButton();
        //popup displayed
        //verify message "Account created successfully"
        Assert.assertTrue(bankManagerLoginPage.getPopupMsg().contains("Account created successfully"), "Message not found");
        //click on "ok" button on popup
        bankManagerLoginPage.clickOKOnPopup();
    }

    @Test(groups = {"smoke", "regression"})
    public void customerShouldLoginAndLogoutSuccessfully() {
        //click on "Customer Login" Tab
        homePage.clickOnCustomerLoginTab();
        //search customer that you created.
        customersPage.searchAndSelectNameFormDropdown("Harry Potter");
        //click on "Login" Button
        customerLoginPage.clickOnLoginButton();
        //verify "Logout" Tab displayed
        //Assert.assertTrue(customerLoginPage.getLogoutText().contains("Logout"), "Text not found");
        //click on "Logout"
        customerLoginPage.clickOnLogoutButton();
        //verify "Your Name" text displayed
        Assert.assertTrue(customerLoginPage.getNameTextAfterLogout().contains("Your Name"), "Text not found");
    }

    @Test(groups = {"smoke", "regression"})
    public void customerShouldDepositMoneySuccessfully() {
        //click on "Customer Login" Tab
        homePage.clickOnCustomerLoginTab();
        //search customer that you created.
        customersPage.searchAndSelectNameFormDropdown("Harry Potter");
        //click on "Login" Button
        customerLoginPage.clickOnLoginButton();
        //click on "Deposit" Tab
        accountPage.clickOnDepositTab();
        //Enter amount 100
        accountPage.enterAmountToDeposit("100");
        //click on "Deposit" Button
        accountPage.clickOnDepositButton();
        //verify message "Deposit Successful"
        Assert.assertEquals(accountPage.getDepositMsg(), "Deposit Successful", "Message not found");
    }

    @Test(groups = {"smoke", "regression"})
    public void customerShouldWithdrawMoneySuccessfully() throws InterruptedException {
        //click on "Customer Login" Tab
        homePage.clickOnCustomerLoginTab();
        //search customer that you created
        customersPage.searchAndSelectNameFormDropdown("Harry Potter");
        //click on "Login" Button
        customerLoginPage.clickOnLoginButton();
        //click on "Deposit" Tab
        accountPage.clickOnDepositTab();
        //Enter amount 100
        accountPage.enterAmountToDeposit("100");
        //click on "Deposit" Button
        accountPage.clickOnDepositButton();
        //click on "Withdrawl" Tab
        accountPage.clickOnWithdrawTab();
        //Enter amount 50
        Thread.sleep(1000);
        accountPage.enterAmountToWithdraw("50");
        //click on "Withdraw" Button
        accountPage.clickOnWithdrawButton();
        //verify message "Transaction Successful"
        Thread.sleep(1000);
        Assert.assertTrue(accountPage.getWithdrawMsg().contains("Transaction successful"),
                "Message not found");
    }
}