# Spring security with oauth2 and jwt
Testing how to make API secure with oauth2 combined with jwt

This app is running in port 7777

# generate token 
type : POST

endpoints : /oauth/token

Headers :

basic auth : {
    
    username : client-id
    
    password : client-secret
}


content type : application/x-www-form-urlencoded

body : 

params (role 'USER'): {
    
    username : acep
    
    password : acep
    
    grant_type : password       
}

params (role 'ADMIN'): {
    
    username : budi
    
    password : budi
    
    grant_type : password       
}

# show product list
type : GET

endpoints : /products

role : 'USER'

Headers :

Authorization : bearer 'jwt from access_token'

# insert product
type : POST

endpoints : /products

role : 'ADMIN'

Headers :

Authorization : bearer 'jwt from access_token'

content type : application/json

request : {

    "name": "Indomie",
	"price": "3000",
	"imageName": "indomie.jpg"
}

# refresh token when access token expire
type : POST

endpoints : /oauth/token

Headers :

basic auth : {
    
    username : client-id
    
    password : client-secret
}


content type : application/x-www-form-urlencoded

body : 

params : {
    
    refresh_token : 'jwt from refresh_token'
    
    grant_type : 'refresh_token'       
}