package com.shashank.example.automationdemo.uistore;

import org.openqa.selenium.By;

public class LoginPageLocators
{
    public static By usernameLocator = By.name("username");
    public static By passwordLocator = By.name("password");
    public static By loginButtonLocator = By.cssSelector("button[type='submit']");

    public static By errorMessage = By.cssSelector(".oxd-alert-content-text");
}
