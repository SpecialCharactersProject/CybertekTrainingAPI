@teacherAPI
Feature: Teacher API module

  Agile Story: User should be able to create/update and delete a teacher

  @Order(1)
  Scenario: Creating teacher with POST API
    Given teacher is created with given values below

      | firstName        | John            |
      | lastName         | Smith           |
      | batch            | 11              |
      | subject          | Javascript       |
      | section          | AP-12           |
      | birthDate        | 12/12/1999      |
      | joinDate         | 12/12/2019      |
      | emailAddress     | john@gmail.com  |
      | phone            | 7732321212      |
      | gender           | Male            |
      | premanentAddress | 123. N State st |
      | salary           | 10000000        |
      | department       | Maths           |
      | password         | 123abc          |

    Then verify created Teacher success status code "200"
    Then verify created Teacher response with Database

  @Order(2)
  Scenario: Updating teacher with PUT API
    Given teacher is updated with given values below

      | firstName        | Henry                                  |
      | lastName         | Ford                                   |
      | emailAddress     | koteague0@shutterfly.com               |
      | subject          | Intro to Science                       |
      | phone            | 2061166182                             |
      | premanentAddress | 965 Nancy Center, Long Beach, CA 90847 |

    Then verify updated Teacher response with Database

  @Order(3)
  Scenario: Scenario: Deleting teacher with DELETE API

    Given teacher is deleted with "teacherID"
    Then verify deleted Teacher success status code "200"
    And verify deleted Teacher response with Database
