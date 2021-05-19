import time
import pyfirmata

# 아두이노에 연결합니다. 
board = pyfirmata.ArduinoMEGA('/dev/ttyACM0')
board2 = pyfirmata.Arduino('/dev/ttyACM1')

# 디지털(digital) 핀(pin) 13번을 출력(output) 모드로 가져옵니다. 
# 아두이노 메가
led_builtin = board.get_pin('d:13:o')
touch1 = board.get_pin('d:0:i')
# 아두이노 우노
led_builtin = board2.get_pin('d:13:o')
touch13 = board2.get_pin('d:8:i')

it = pyfirmata.util.Iterator(board)
it.start()
it2 = pyfirmata.util.Iterator(board2)
it2.start()
touch1.enable_reporting()

# 각 센서의 상황을 입력받기 위해 라이브러리에서 필요로 하는 코드


class BeYerage:
    def __init__(self):
        with open("forconnect7.txt", "r") as f:
            x = list(f.readlines())
            xy_name = []
            for i in range(0,len(x)):
                x_name = list(x[i].split(','))
                xy_name.extend(x_name)
                self.xy_name = xy_name
    
    def start(self):
        
        start = time.time()
        b_number = 0
        while True:
            if time.time()- start > 10:
                # 시현용 시간이기에 600 정도가 적당함.
                print("time :", time.time() - start)
                connect7.server().server_connect()
                start = time.time()
                
            if touch1.read(): #1번
                b_name = self.xy_name[1][6:-1]
                # 핀에 출력값으로 1을 주면 led 불이 켜집니다. 
                led_builtin.write(1)
                # time.sleep(1)
                print('해당 제품은'+ b_name +'입니다.')
                speakstory = connect10.connect(b_name)
                test0330.Bname('The location is','1-1')
                # 한글을 영어로 표현하는 걸 어떻게 자동으로 설정하지??? => 우선은 공통 대체 언어이용
                testtts3.speak(speakstory)
                time.sleep(1)
                continue

            else:
                # 핀에 출력값으로 0을 주면 led 불이 꺼집니다.     
                led_builtin.write(0)
                #print('한 번 눌러봐')
                test0330.Brest()
            time.sleep(0.2)
while True:
    print(BeYerage().start())