# Library management system

## Requirements :
*For this project, you have to implement the backend system of an application used
for the management of a library. The data will be stored in an SQL database.*

The endpoints that should be implemented are:
<br>
- add an author
  - only first name and last name is required
  - throw exception if an author having the same name already exists
    <br>

- add a book
  - the details that should be provided are: title, author id, category (e.g.:
    science-fiction, biography, adventure, …), year, total number of copies,
    available number of copies
  - if provided data is not valid (year / number of copies are negative numbers) or
    if there is already a book in the database having the same title and author or if
    the author doesn’t exist, an exception should be thrown
  <br>

- edit the details of a book
  - if provided data is not valid (year / number of copies are negative numbers,
    the requested book doesn’t exist), an exception should be thrown
    <br>

- get a list with books
  - the endpoint should contain an optional parameter for selecting books from a
    certain category
  - the books should be sorted alphabetically by title
    <br>

- borrow a book
  - provide a user_id so we can store the borrow history in another table (no
    need to add users table in database)
  - in the table containing borrowed books, set status to BORROWED
  - if the requested book doesn’t exist or if there is no available copy of it, an
    exception should be thrown
    <br>

- return a book
  - also provide the user_id who borrowed this
  - throw an exception if the user didn’t borrow the book or if the book doesn’t
    exist
  - in the table containing borrowed books, set status to RETURNED
    <br>

- delete a book
  - soft deletion
  - an exception should be thrown if the book doesn’t exist
<br>
  
The security part is not required. Its purpose is to familiarise you with the structure of
  a spring boot project. Extra points if PagingAndSortingRepository is used