This assignment is built using Spring boot framework .

Below basic functionality is working using below  four APIS


**1:)**

http://localhost:8080/roadsideassistance/updateAssistantLocation
Assistant location can be updated by this API


**2:)**

http://localhost:8080/roadsideassistance/findNearestAssistants

API returns nearest 5 Assistant based on customer's longitude and latitude



**3:)**

http://localhost:8080/roadsideassistance/reserveAssistant

First finds nearest assistant and reserves it .
since this Assistant is allocated to a customer so this assistant will not be reserve again until got released 
or will not be return when calling "findNearestAssistants" API


**4:)**

http://localhost:8080/roadsideassistance/releaseAssistant
Only when assigned assistant will be released by calling this api ,  it will be available and can be served again




**How to RUN** 

I am attaching a Postman collection export .

1. simply run the Spring boot application .
2. Import Post collection file  "RoadsideAssistance.postman_collection.json"
3. Hit all four APIS.




