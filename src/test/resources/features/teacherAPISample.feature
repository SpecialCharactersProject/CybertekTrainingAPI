Feature: Teacher API module

  @teacherAPI
  Scenario: Creating teacher with Create teacher API
    Given teacher is created with values
      | firstName	 |  John		|
      | lastName     |  Smith	    |
      | batch		 |  11		    |
      | subject		 | Javacript    |
      | section		 | AP-12		|
      | birthDate    | 12/12/1999   |
      | joinDate	 | 12/12/2019   |
      | emailAddress | john@gmail.com |
      | phone		 | 7732321212	|
      | gender		 | Male			|
      | permenantAdd |	123. N State st |
      | salary		 |	10000000	|
      | department	 |	Maths		|
      | password	 |   123abc		|

    When user hits the web service "http://api.cybertektraining.com/teacher/create"
    Then verify status code "200"
    And verify response with Database
    And verify response with UI