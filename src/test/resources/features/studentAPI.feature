Feature: Creating a student
Background:
  Given the user is on the School Landing page
 Then the user should verify he is on the School main page

Scenario: Verification of the ability to create a student
  Given the user is on the Student's Module
  When user should click on the add student button
  And user should be able to see add students header
  And user should verify Student Information header
  When user store student's
| FIRST_NAME        | Lionel          |
| LAST_NAME         | Messi           |
| EMAIL             | messi@gmail.com |
| JOIN_DATE         | 01/01/2010      |
| PASSWORD          | 123             |
| SUBJECT           | Math            |
| MOBILE_NUMBER     | 3124756521      |
| GENDER            | Male            |
| ADMISSION_NO      | 10              |
| BIRTH_DATE        | 01/01/1990      |
| MAJOR             | Football        |
| BATCH             | 10              |
| SECTION           | LaLigA          |
| PREMANENT_ADDRESS | 921 W Huron St. |
| COMPANY_NAME      | Barcelona       |
| TITLE             | Goat            |
| START_DATE        | 01/02/2010      |
| CITY              | Barcelona       |
| STREET            | Huron           |
| ZIP_CODE          | 60742           |
| STATE             | IL              |

And user should enter student's information
And user should click on the summit button
And user should handle pop-up alert
When user is on the All students page
And user should search student by "name" on UI
And user should click on the student profile
And user should verify my profile header
And user should verify "student_id" on UI
And user should store added student information
Then user should compare added student information with database


 Scenario: Verification of the ability to update a student
    Given the user clicks and All Students and Edit button
    When the user updates below fields with new data

      |FIRST_NAME         |           Phyliss                                       |
      |LAST_NAME          |           Edelstein                                     |
      |EMAIL_ADDRESS      |           koteague0@shutterfly.com                      |
      |SUBJECT            |           Linear Algebra                                |
      |PHONE              |           2061166182                                    |
      |MAJOR              |           Calculus                                      |
      |PREMANENT_ADDRESS  |           965 Nancy Center, Long Beach, CA 90847        |
   Then the user should be able to see updated student info on website
   Then the user should be able to see the updated student info in database

  Scenario: Verification of the ability to delete a student
    Given the user clicks on Students dropdown menu
    And the user chooses All Students option
    And the user opens already created student
    And the user clicks on 3 dots sign in top right corner of student's name
    And the user clicks on delete option
    And the user clicks on red Delete button
    And the user sees that student removed from the All Students page (UI).
    Then the user should be able to see that student removed from the database.
