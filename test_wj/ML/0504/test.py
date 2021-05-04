import Server_Connect
import time

start = time.time()
print(time.time())
time.sleep(5)
print(time.time() - start )
Server_Connect.server_connect()
while True:
    print(time.time() - start)
    if (time.time() - start) > 10:
        start = time.time()
        print("서버에서 데이터를 가져옵니다.")
        Server_Connect.server_connect()