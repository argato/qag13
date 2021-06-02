package tests;

import static io.qameta.allure.Allure.step;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentRegistrationFormTest extends TestBase {

  StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

  @Test
  @Owner("arina_ng")
  @Feature("Форма регистрации студента")
  @Story("Проверка регистрации студента без ошибки")
  @DisplayName("Тест без ошибки")
  void successfulFillTest() {
    UserDefaultData testDate = new UserDefaultData();
    step("Open students registration form", () -> studentRegistrationFormPage.openPage());
    step("Fill students registration form", () -> {
      studentRegistrationFormPage.setFirstName(testDate.getFirstName());
      studentRegistrationFormPage.setLastName(testDate.getLastName());
      studentRegistrationFormPage.setUserEmail(testDate.getUserEmail());
      studentRegistrationFormPage.setUserNumber(testDate.getUserNumber());
      studentRegistrationFormPage.setGenter();
    });
    step("Set subjects", () -> studentRegistrationFormPage.setSubject(testDate.getSubject()));
    step("Set date", () -> studentRegistrationFormPage.setBirthDate(testDate.getYearOfBirth(),
                                                                    testDate.getMonthOfBirth(),
                                                                    testDate.getDateOfBirth()));
    step("Set hobbies", () -> studentRegistrationFormPage.setHobby());
    step("Set address", () -> {
      studentRegistrationFormPage.setCurrentAddress(testDate.getCurrentAddress());
      studentRegistrationFormPage.setState(testDate.getState());
      studentRegistrationFormPage.setCity(testDate.getCity());
    });
    step("Upload image", () -> studentRegistrationFormPage.setFile(testDate.getFile()));

    step("Submit form", () -> studentRegistrationFormPage.submitForm());
    step("Verify successful form submit",
         () -> studentRegistrationFormPage.checkData(testDate.getExpectedDate()));
  }
}
