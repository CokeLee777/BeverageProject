import tensorflow as tf
import cv2 as cv
import numpy as np
import ConnectAndData
import TTS_gtts
import time
import Server_Connect

#bp = np.loadtxt('first_dataset_2.csv', delimiter=',', dtype=np.float32)

#x_test = bp[:, 0:-1]
#y_test = bp[:, [-1]]


def nothing(x):
    pass

color = [ 83 , 89 , 105]

one_pixel = np.uint8([[color]])
hsv = cv.cvtColor(one_pixel, cv.COLOR_BGR2HSV)
hsv = hsv[0][0]
threshold = 40
lower_blue1 = np.array([hsv[0], threshold, threshold])
upper_blue1 = np.array([180, 255, 255])
lower_blue2 = np.array([0, threshold, threshold])
upper_blue2 = np.array([hsv[0]+10-180, 255, 255])
lower_blue3 = np.array([hsv[0]-10, threshold, threshold])
upper_blue3 = np.array([hsv[0], 255, 255])

cv.namedWindow('img_color')
cv.namedWindow('img_result')

cam = cv.VideoWriter('0429_train.avi',cv.VideoWriter_fourcc('D', 'I', 'V', 'X'),25,(640,480))
cap = cv.VideoCapture(0)

with open("forconnect9.txt", "r") as f:
            x = list(f.readlines())
            xy_name = []
            for i in range(0,len(x)):
                x_name = list(x[i].split(','))
                xy_name.extend(x_name)
            print(xy_name)

b_number = 0
model = tf.keras.models.Sequential([
    tf.keras.layers.Input((5,)),
    tf.keras.layers.Dense(8),
    tf.keras.layers.BatchNormalization(),
    tf.keras.layers.Activation('swish'),
    tf.keras.layers.Dense(8),
    tf.keras.layers.BatchNormalization(),
    tf.keras.layers.Activation('swish'),
    tf.keras.layers.Dense(8),
    tf.keras.layers.BatchNormalization(),
    tf.keras.layers.Activation('swish'),
    tf.keras.layers.Dense(1)
])


model.load_weights('bp3_checkpoint')
model.summary()
#print(model.predict(x_test[:1]))
#print(x_test[:1])
b = 1000
d = 0

start_time = time.time()
    # 시작시간 체크

while(True):

    # 10분에 한번 서버에서 데이터를 가져옵니다. 
    if (time.time() - start_time) > 600:
        start_time = time.time()
        Server_Connect.server_connect()
        
    ret,img_color = cap.read()
    height, width = img_color.shape[:2]
    #print(img_color.shape[:2])
    img_color = cv.resize(img_color, (width, height), interpolation=cv.INTER_AREA)

    # 원본 영상을 HSV 영상으로 변환합니다.
    img_hsv = cv.cvtColor(img_color, cv.COLOR_BGR2HSV)

    # 범위 값으로 HSV 이미지에서 마스크를 생성합니다.
    img_mask1 = cv.inRange(img_hsv, lower_blue1, upper_blue1)
    img_mask2 = cv.inRange(img_hsv, lower_blue2, upper_blue2)
    img_mask3 = cv.inRange(img_hsv, lower_blue3, upper_blue3)
    img_mask = img_mask1 | img_mask2 | img_mask3

    kernel = np.ones((11,11), np.uint8)
    img_mask = cv.morphologyEx(img_mask, cv.MORPH_OPEN, kernel)
    img_mask = cv.morphologyEx(img_mask, cv.MORPH_CLOSE, kernel)

    # 마스크 이미지로 원본 이미지에서 범위값에 해당되는 영상 부분을 획득합니다.
    img_result = cv.bitwise_and(img_color, img_color, mask=img_mask)


    numOfLabels, img_label, stats, centroids = cv.connectedComponentsWithStats(img_mask)
    key = cv.waitKey(1)
    for idx, centroid in enumerate(centroids):
        if stats[idx][0] == 0 and stats[idx][1] == 0:
            continue

        if np.any(np.isnan(centroid)):
            continue

        x,y,width,height,area = stats[idx]
        
        centerX,centerY = int(centroid[0]), int(centroid[1])
    
        if  450 > height > 100:
            if 360 > width > 50:
                
                result = [[centerX,centerY,width,height,area]]

                if d == 2:
                    predictions = model.predict(result)
                else:
                    # predictions = str(7)
                    predictions = model.predict(result)
                

                with tf.compat.v1.Session() as sess:
                
                    print(" 실 예측값 : ")
                    print(predictions)
                    print(" 근사 예측값 ")
                    a = list(map(float, predictions))
                    print(round(a[0]))
                    c = round(a[0])

                    if b == c:
                        if c == 0:
                            b_name = xy_name[5][6:-1]
                            speakstory = ConnectAndData.connect(b_name)
                            TTS_gtts.speak(speakstory)
                            time.sleep(5)
                            continue
                        elif c == 2:
                            b_name = xy_name[6][6:-1]
                            speakstory = ConnectAndData.connect(b_name)
                            TTS_gtts.speak(speakstory)
                            time.sleep(5)
                            continue
                        elif c == 1:
                            b_name = xy_name[7][6:-1]
                            speakstory = ConnectAndData.connect(b_name)
                            TTS_gtts.speak(speakstory)
                            time.sleep(5)
                            continue
                    b = c
                    

                    cv.putText(img_color,'Real : ' + str(predictions), (10, 20), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1, lineType=cv.LINE_AA)
                    cv.putText(img_color,'About : ' + str(round(a[0])), (10, 50), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1, lineType=cv.LINE_AA)
                    
                    

                    

                    #print(round(a))

    cam.write(img_color)
    cv.imshow('img_color', img_color)
    #cv.imshow('img_mask', img_mask)
    cv.imshow('img_result', img_result)

    # ESC 키누르면 종료
    if cv.waitKey(1) & 0xFF == 27:
        cam.release()
        print('영상을 종료합니다. 녹화가 진행되었습니다.')
        break

cv.destroyAllWindows()

