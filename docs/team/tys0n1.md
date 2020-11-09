# Tan Yu Shing – Project Portfolio Zoomaster

## About the project
My team of 4 software engineering students and I were tasked with creating a command line interface desktop application for our Software Engineering project.  
We designed our application to launch Zoom links using the command line interface called Zoomaster. <br></br>
This new application enables NUS students to easily store website links and launch them quickly; create a timetable with said website links attached and quickly launch Zoom lessons for the current lesson in the timetable; and a planner to help organize their timetables to suit that of their teammates.

## Summary of contributions
This section shows the summary of my coding, documentation, and other helpful contributions to the team project.

**Enhancement added:** I added the ability to switch between different modes of the App and the command to print out the list of timetable lessons in sequential order.
*	What it does: The **mode** command allows the user to switch between the three modes of the App. 
The **show** command allows the users to view list of timetable lessons in three ways; view entire timetable; view timetable on specific day; or view today’s timetable.
*	Justification: The **mode** command allows users to easily change between different modes of the App. 
It also allows unified inputs in each mode which reduces the number of commands the users have to remember when using the App. 
The **show** command allows users to view the changes they had made to their timetable to see if they made correct changes. They can also and view upcoming lessons and get prepared for them ahead of time.
*	Highlights: The **mode** enhancement works with existing as well as future commands. 
The logic of the App is modularized such that additional modes can be added easily. 
The **show** command to view the timetable is unified with the command to view bookmarks attached to lesson slots. 
The algorithm searches for keywords within the input command to decode if it is to view the timetable or the bookmarks.

**Code contributed:** Please click these links to see a sample of my code: [Functional code](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=w11&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=TYS0n1&tabRepo=AY2021S1-CS2113T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code) 
[Test code](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=w11&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=TYS0n1&tabRepo=AY2021S1-CS2113T-W11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=test-code)

**Other contributions:**
* Project management:
    * There was a total of 3 releases, from version 1.0 to 2.1. I managed release version 2.0 on GitHub.
* Enhancements to existing features:
    * Adjusted colour scheme of a few print statements to allow users to see text better against a dark background: 
    [#174](https://github.com/AY2021S1-CS2113T-W11-1/tp/pull/174), [#176](https://github.com/AY2021S1-CS2113T-W11-1/tp/pull/176)
* Documentation:
    * Created navigation links to different sections for table of content to allow quick navigation by the readers: 
    [#135](https://github.com/AY2021S1-CS2113T-W11-1/tp/pull/135), [#137](https://github.com/AY2021S1-CS2113T-W11-1/tp/pull/137/files)
    * Wrote descriptions of Zoomaster components. Explained the implementations of Zoomaster modes feature and Show timetable feature. Also created the sequence and activity diagrams for those features. 
    ([PRs](https://github.com/AY2021S1-CS2113T-W11-1/tp/pulls?q=is%3Apr+is%3Aclosed+assignee%3ATYS0n1))
* Community
    * Reviewed PE feedback from peers and assigned team mates to help resolve issues: 
    [#141 to #171](https://github.com/AY2021S1-CS2113T-W11-1/tp/issues?q=is%3Aopen+is%3Aissue)
    * Reported bugs and offered suggestions for other teams during [PE](https://github.com/TYS0n1/ped/issues)
* Tools:
    * Utilised Jansi library (Fusesource) to create coloured text in team project 
    ([#101](https://github.com/AY2021S1-CS2113T-W11-1/tp/pull/101), 
    [#174](https://github.com/AY2021S1-CS2113T-W11-1/tp/pull/174), 
    [#176](https://github.com/AY2021S1-CS2113T-W11-1/tp/pull/176))

