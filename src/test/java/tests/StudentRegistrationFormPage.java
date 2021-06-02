package tests;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

import java.io.File;
import java.util.Map;

public class StudentRegistrationFormPage {

  public void openPage() {
    open("https://demoqa.com/automation-practice-form");
  }

  public void setFirstName(String firstName) {
    $("#firstName").setValue(firstName);
  }

  public void setLastName(String lastName) {
    $("#lastName").setValue(lastName);
  }

  public void setUserEmail(String userEmail) {
    $("#userEmail").setValue(userEmail);
  }

  public void setGenter() {
    $("#genterWrapper [for=gender-radio-3]").click();
  }

  public void setUserNumber(String userNumber) {
    $("#userNumber").setValue(userNumber);
  }

  public void setSubject(String subject) {
    $("#subjectsInput").val(subject).pressEnter();
  }

  public void setBirthDate(String year, String month, String day) {
    $("#dateOfBirthInput").click();
    $(".react-datepicker__month-select").$(byText(month)).click();
    $(".react-datepicker__year-select").$(byText(year)).click();
    $(".react-datepicker__month").$(byText(day)).click();
  }

  public void setHobby() {
    $("#hobbiesWrapper [for=hobbies-checkbox-3]").click();
  }

  public void setFile(File file) {
    $("#uploadPicture").uploadFile(file);
  }

  public void setCurrentAddress(String currentAddress) {
    $("#currentAddress").setValue(currentAddress);
  }

  public void setState(String state) {
    $("#stateCity-wrapper #state").scrollTo().click();
    $("#stateCity-wrapper #state").$(byText(state)).click();
  }

  public void setCity(String city) {
    $("#stateCity-wrapper #city").click();
    $("#stateCity-wrapper #city").$(byText(city)).click();
  }

  public void submitForm() {
    $("#submit").click();
  }

  public void checkData(Map<String, String> enteredData) {
    $$(".modal-content tbody tr").snapshot().forEach(row -> {
      String rowLabel = row.$("td").text();
      String expectedText = enteredData.get(rowLabel);
      row.$("td", 1).shouldHave(exactText(expectedText));
    });
  }

}
