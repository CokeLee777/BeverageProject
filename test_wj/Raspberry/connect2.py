import requests

r =requests.post('http://15.165.63.211:3333/beverageLocationInfoByJson/raspberry-pi')
r1 =requests.post('http://15.165.63.211:3333/beverageInfoByJson/raspberry-pi')

print(r.status_code)
print(r.text)

print(r1.status_code)
print(r1.text)