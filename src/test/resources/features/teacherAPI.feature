Feature: Teacher API module

  @teacherAPI
  Scenario: Creating teacher with Create teacher API

Given the user is on the Teacher's Module
And the user clicks on Add Teacher
And the user enters
| FirstName        | Venkat           |
| Email            | venkat@gmail.com |
| Password         | 123456           |
| Subject          | Math             |
| Gender           | Male             |
| BirthDate        | 09/01/2020       |
| Batch            | 7                |
| LastName         | Patel            |
| JoiningDate      | 02/02/2020       |
| MobileNumber     | 7731112222       |
| Department       | English          |
| Salary           | 50000            |
| Section          | 9.0              |
| PermanentAddress | 123 Main St      |
And the user clicks on the Submit button
And the user should see a new created teacher on the All Teacher page(UI)
Then the user should see a new created teacher in the database

  Scenario: Verification of the ability to update a teacher
    Given the user clicks and All Teacher and Edit button
    When the user updates below fields with new data

      |FIRST_NAME         |           Henry                                       |
      |LAST_NAME          |           Ford                                        |
      |EMAIL_ADDRESS      |           koteague0@shutterfly.com                    |
      |SUBJECT            |           Intro to Science                            |
      |PHONE              |           2061166182                                  |
      |MAJOR              |           Pyhsics                                     |
      |PREMANENT_ADDRESS  |           965 Nancy Center, Long Beach, CA 90847      |

    Then the user should be able to see updated teacher info on website
    Then the user should be able to see the updated teacher info in database

  Scenario: Verification of the ability to delete a teacher
    Given user clicks on All Teachers submodule
    And user search teacher with "Jason"
    And user on the teacher profile
    And user verifies the MY PROFILE header
    And user stores the "teacher_id"
    Then user compares UI teacher_id with dataBase teacher_id
    When user clicks on All Teachers submodule
    And user search the teacher with id
    And   user clicks on threeDotsign in order to delete the teacher
    When  user  clicks on delete option
    And   user should handle the confirmation message is displayed
    When  user clicks on All Teachers submodule
    And user search for teacher with teacher_id on UI
    And user verifies threeDotsign not displayed on UI
    Then user verifies UI teacherid with dataBase teacherid
