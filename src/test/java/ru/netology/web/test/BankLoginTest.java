package ru.netology.web.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.data.SQLHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.SQLHelper.cleanAuthCodes;
import static ru.netology.web.data.SQLHelper.cleanDatabase;

public class BankLoginTest {
    LoginPage loginPage;
    DataHelper.AuthInfo authInfo = DataHelper.getAuthInfoWithTestData();

    @AfterAll
    static void tearDownAll() {
        cleanDatabase();
    }

    @AfterEach
    void tearDown() {
        cleanAuthCodes();
    }

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999/", LoginPage.class);
    }

    @Test
    void shouldSuccessfullyLogin() {
        var verificationPage = loginPage.validLogin(authInfo);
        var verifyCode = SQLHelper.getVerifyCode();
        verificationPage.validVerify(verifyCode.getCode());
    }

}


