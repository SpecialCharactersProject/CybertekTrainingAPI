@studentAPI
Feature: Student API module

  Agile Story: User should be able to create/update and delete a student

  @createStudent
  Scenario: Creating student with POST API
    Given student is created with given values below

      | firstName        | Lionelio          |
      | lastName         | Messi           |
      | emailAddress     | messi@gmail.com |
      | joinDate         | 01/01/2010      |
      | password         | 123jbr             |
      | subject          | Math            |
      | phone            | 3124756521      |
      | gender           | Male            |
      | admissionNo      | 10              |
      | birthDate        | 01/01/1990      |
      | major            | Football        |
      | batch            | 10              |
      | section          | LaLigA          |
      | premanentAddress | 921 W Huron St. |
      | companyName      | Barcelona       |
      | title            | Goatie            |
      | startDate        | 01/02/2010      |
      | city             | Barcelona       |
      | street           | 13 Hurnon           |
      | zipCode          | 60742           |
      | state            | IL              |

    Then verify created Student success status code "200"
    And verify created Student response with Database

  @updateStudent
  Scenario: Updating student with PUT API
    Given student is updated with given values below

      | firstName        | Henry                                  |
      | lastName         | Ford                                   |
      | emailAddress     | koteague0@shutterfly.com               |
      | subject          | Intro to Science                       |
      | phone            | 2061166182                             |
      | major            | Pyhsics                                |
      | premanentAddress | 965 Nancy Center, Long Beach, CA 90847 |

    Then verify updated Student response with Database

  @deleteStudent
  Scenario: Deleting student with DELETE API

    Given student is deleted with "studentID"
    Then  verify deleted Student success status code "200"
    And verify deleted Student response with Database
