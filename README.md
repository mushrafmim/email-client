# Email Client Project
University - University of Moratuwa  
Module - CS1040 Program Construction  
Semester - 02

## Description
In this assignment, you will be implementing a command-line based email client.

The email client has two types of recipients
* official
  * Some official recipients are close friends.
* personal

## Technologies
* Language - JAVA
* Concepts Covered
  * OOP concepts
  * File Handling
  * Object Serialization
  * Emailing
  
## Requirements

1. Details of the recipient list should be stored in a text file.  

    An official recipient’s record in the text file has the following format  
    `Official: <name>, <email>, <designation>`  
    Example  
    `Official: nimal,nimal@gmail.com,ceo`

    A sample record for official friends in the text file looks as follows (last value is the recipient's birthday):

    `Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12`

    A sample record for personal recipients in the text file looks as follows (last value is the recipient's birthday):

    `Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10`


2. The user should be able to add a new recipient through command-line, and these details should be added to the text file.

3. When the email client is running, an object for each email recipient should be maintained in the application. For this, you will have to load the recipient details from the text file into the application.   
4. For each recipient having a birthday, a birthday greeting should be sent on the correct day. Official friends and personal recipients should be sent different messages (e.g. Wish you a Happy Birthday. <your name> for an office friend, and hugs and love on your birthday. <your name> for personal recipients). But all personal recipients receive the same message, and all office friends should receive the same message.  A list of recipients to whom a birthday greeting should be sent is maintained in the application, when it is running. When the email client is started, it should traverse this list, and send a greeting email to anyone having their birthday on that day.

5. The system should be able to keep a count of the recipient objects. Use static members to keep this count.

6. All the emails sent out by the email client should be saved into the hard disk, in the form of objects – object serialization can be used for this. The user should be able to retrieve information of all the mails sent on a particular day by using a command-line option. 

You only have to send out messages. No need to implement the logic to receive messages.

## Interface
Command-line options should be available for:

  * Adding a new recipient  
  * Sending an email  
  * Printing out all the names of recipients who have their birthday set to current date  
  * Printing out details (subject and recipient) of all the emails sent on a date specified by user input  
  * Printing out the number of recipient objects in the application  
  * Save the recipient data into clientList.txt.

You may use the code given in this [link](/docs/starter.txt) to implement the basic functionality of the mail client.  


## References
Starter File  
https://github.com/mushrafmim/email-client/blob/main/docs/starter.txt  
JavaMail API - Sending email via Gmail SMTP Example (Tutorial)  
https://mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/

