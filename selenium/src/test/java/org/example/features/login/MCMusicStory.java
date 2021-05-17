package org.example.features.login;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.example.steps.serenity.MCMusicSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom("src/test/resources/user_data.csv")
public class MCMusicStory {
    @Managed(uniqueSession = true, driver = "chrome")
    public WebDriver webDriver;

    @Steps
    public MCMusicSteps mcMusicSteps;

    String email, password, valid;

    @Test
    public void auth() {
        mcMusicSteps.init();
        mcMusicSteps.assertLoggedIn(false);
        mcMusicSteps.step_login(email, password);
        if(valid .equals("1")) {
            mcMusicSteps.assertLoggedIn(true);
            mcMusicSteps.search();
            mcMusicSteps.assertSearchResult();
            mcMusicSteps.addToCart();
            mcMusicSteps.toCart();
            mcMusicSteps.assertPrice();
            mcMusicSteps.step_logout();
            mcMusicSteps.assertLoggedIn(false);
        }
    }

    @Test
    public void loginSuccess() {
        mcMusicSteps.init();
        mcMusicSteps.assertLoggedIn(false);
        mcMusicSteps.step_login(email, password);
        if(valid .equals("1")) {
            mcMusicSteps.assertLoggedIn(true);
        }
    }

    @Test
    public void loginFail() {
        mcMusicSteps.init();
        mcMusicSteps.assertLoggedIn(false);
        mcMusicSteps.step_login(email, password);
        if(valid .equals("0")) {
            mcMusicSteps.assertLoggedIn(false);
        }
    }

}
