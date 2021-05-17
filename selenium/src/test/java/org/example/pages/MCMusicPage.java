package org.example.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

@DefaultUrl("https://www.mcmusic.ro/")
public class MCMusicPage extends PageObject{
    @FindBy(className = "laberUserInfo")
    private WebElementFacade buttonYourAccount;

    @FindBy(css = "[title='Log in to your customer account']")
    private WebElementFacade buttonAuth;

    @FindBy(name = "email")
    private WebElementFacade email;

    @FindBy(name = "password")
    private WebElementFacade password;

    @FindBy(id = "submit-login")
    private WebElementFacade buttonLogin;

    @FindBy(className = "logout")
    private WebElementFacade buttonLogout;

    @FindBy(css = "p.nameuser :first-child")
    private WebElementFacade loginStatus;

    @FindBy(id = "search_query_top")
    private WebElementFacade searchBar;

    @FindBy(name = "submit_search")
    private WebElementFacade buttonSearch;

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/section/div[3]/div/div[1]/div/div[1]/div/article/div/div[2]/div[4]/div/div[1]/form/button")
    private WebElementFacade buttonAddToCart;

    @FindBy(css = "div.productName.prod_grid_div_like_h2 a")
    private WebElementFacade itemText;

    @FindBy(css = "a.btn.btn-primary")
    private WebElementFacade buttonToCart;

    @FindBy(css = ".cart-total .value")
    private WebElementFacade cartPrice;

    public void clickYourAccount() {
        buttonYourAccount.click();
    }

    public void clickAuth() {
        buttonAuth.click();
    }


    public void clickLogin() {
        buttonLogin.click();
    }

    public void clickLogout() {
        buttonLogout.click();
    }

    public void typeEmailAndPassword(String user, String pass) {
        email.type(user);
        password.type(pass);
    }

    public String getLoginStatus() {
        return loginStatus.getText();
    }

    public void typeSearch(String searchText) {
        searchBar.type(searchText);
    }

    public void clickSearchButton() {
        buttonSearch.click();
    }

    public void clickAddToCart() {
        buttonAddToCart.click();
    }

    public String getItemText() {
        return itemText.getText();
    }

    public void clickToCart() {
        buttonToCart.click();
    }

    public String getCartPrice() {
        return cartPrice.getText();
    }
}
