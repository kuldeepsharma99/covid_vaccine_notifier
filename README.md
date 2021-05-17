# covid_vaccine_notifier
FOR INDIA: checks the COVID vaccine availability in a PIN code and notifies through email

Before running the code below properties needs to be updated in the application.properties file. 

# Pincode(s) of your Area
vaccine.pincode={110024,110025}
# Mimum Age Limit. Currently, accepted values are 18 and 45
vaccine.age=18
# Email address where notification is needed. 
vaccine.email.to=placeholder@email.com
# Email address( Only Gmail is supported ) from where email will be triggered 
spring.mail.username=placeholder@gmail.com
# Email Password.
spring.mail.password=placeholder

To Enable login to your gmail acccount from code you will need to disable a security flag in your gmail account used for property "spring.mail.username".
Below is official google link for this:
https://support.google.com/accounts/answer/6010255#zippy=%2Cif-less-secure-app-access-is-on-for-your-account



Disclaimer: This code has been developed in 2 hours and for fun and testing purpose. You will not find the best practises followed because.... no reason. Have fun. 

 
