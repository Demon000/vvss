package org.example.steps.serenity;

import net.thucydides.core.annotations.Step;
import org.example.pages.MCMusicPage;

public class MCMusicSteps {
    MCMusicPage mcMusicPage;
    String synthName = "Korg Minilogue-XD Module - Sintetizator";
    String cartPrice = "2.520,00 lei";

    @Step
    public void init() {
        mcMusicPage.open();
    }

    @Step
    public void step_login(String email, String password) {
        mcMusicPage.clickYourAccount();
        mcMusicPage.clickAuth();
        mcMusicPage.typeEmailAndPassword(email, password);
        mcMusicPage.clickLogin();
    }

    @Step
    public void step_logout() {
        mcMusicPage.clickYourAccount();
        mcMusicPage.clickLogout();
    }

    @Step
    public void assertLoggedIn(boolean loggedIn) {
        if(loggedIn) {
            assert mcMusicPage.getLoginStatus().equals("Ioan Dolanescu");
        }
        else {
           assert mcMusicPage.getLoginStatus().equals("Autentificare");
        }
    }

    @Step
    public void search() {
        mcMusicPage.typeSearch(synthName);
        mcMusicPage.clickSearchButton();
    }

    @Step
    public void addToCart() {
        mcMusicPage.clickAddToCart();
    }

    @Step
    public void assertSearchResult() {
        assert mcMusicPage.getItemText().equals(synthName);
    }

    @Step
    public void toCart() {
        mcMusicPage.clickToCart();
    }

    @Step
    public void assertPrice() {
        assert mcMusicPage.getCartPrice().equals(cartPrice);
    }
}
