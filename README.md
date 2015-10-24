# README #

## aquire access token
 curl -X POST --user 'clientName:clientPassword' -d 'grant_type=password&username=bob&password=test' http://localhost:9000/oauth/token

 ## access resource using token
 curl -X GET  http://localhost:9000/users?access_token=

 ## refresh token
 curl -X POST  --user 'clientName:clientPassword' -d 'grant_type=refresh_token&refresh_token=' http://localhost:9000/oauth/token
