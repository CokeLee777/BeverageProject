import cv2
import numpy as np


cap = cv2.VideoCapture("video.mp4")

# 옵션 설명 http://layer0.authentise.com/segment-background-using-computer-vision.html
fgbg = cv2.createBackgroundSubtractorMOG2(history=500, varThreshold=500, detectShadows=0)

while(1):
    ret, frame = cap.read()

    width = frame.shape[1]
    height = frame.shape[0]
    #frame = cv2.resize(frame, (int(width*0.5), int(height*0.5)))

    fgmask = fgbg.apply(frame)

    #라벨링으로 다중 값 반환
    nlabels, labels, stats, centroids = cv2.connectedComponentsWithStats(fgmask)


    for index, centroid in enumerate(centroids):
        if stats[index][0] == 0 and stats[index][1] == 0:
            continue
        if np.any(np.isnan(centroid)):
            continue


        x, y, width, height, area = stats[index]
        centerX, centerY = int(centroid[0]), int(centroid[1])

        if 120 > area > 70:
            #cv2.circle(frame, (centerX, centerY), 1, (0, 255, 0), 2)
            cv2.rectangle(frame, (x, y), (x + width, y + height), (0, 0, 255))
            print("===== 이미지 detection =====\n x:", x, " y:", y)



    #cv2.imshow('mask',fgmask)
    cv2.imshow('frame',frame)

    k = cv2.waitKey(30) & 0xff
    if k == 27:
        break

cap.release()
cv2.destroyAllWindows()