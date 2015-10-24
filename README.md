# README #

## aquire access token
curl -X POST --user 'clientName:clientPassword' -d 'grant_type=password&username=bob&password=test' http://localhost:9000/oauth/token

## access resource using token
curl -X GET  http://localhost:9000/users?access_token=
curl -X GET  http://localhost:9000/users?access_token=eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsicmVzb3VyY2UiXSwidXNlcl9pZCI6MSwidXNlcl9uYW1lIjoiYm9iIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTQ0NTcyMzY3MywianRpIjoiNmZlNWMxZGItMWJlNS00NzhlLWI0ZDItZWZmM2JkOWJlNDIwIiwiY2xpZW50X2lkIjoiY2xpZW50TmFtZSJ9._--kwlHEy2U9pbemn8fr36H1EDwsVIa9qHTgUbw4FjI

## refresh token
curl -X POST  --user 'clientName:clientPassword' -d 'grant_type=refresh_token&refresh_token=' http://localhost:9000/oauth/token
