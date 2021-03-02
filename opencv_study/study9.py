import cv2
import numpy as np

cap = cv2.VideoCapture(0)
while cv2.waitKey(1) < 0:
    hasFrame, frame = cap.read()
    im = frame

    imgray = cv2.cvtColor(im, cv2.COLOR_BGR2GRAY)
    ret, thresh = cv2.threshold(imgray, 127, 255, 0, cv2.THRESH_BINARY_INV | cv2.THRESH_OTSU)
    #cv2.imshow('thresh',thresh)
    contours, hierarchy = cv2.findContours(thresh, cv2.RETR_LIST, cv2.CHAIN_APPROX_NONE)

    cnts = cv2.drawContours(im, contours[0], -1, (0, 255, 0), 5)

    kpCnt = len(contours[0])

    x = 0
    y = 0

    for kp in contours[0]:
        x = x+kp[0][0]
        y = y+kp[0][1]

    cv2.circle(im, (np.uint8(np.ceil(x/kpCnt)), np.uint8(np.ceil(y/kpCnt))), 1, (0, 0, 255), 3)


    cv2.namedWindow("Result", cv2.WINDOW_NORMAL)
    cv2.imshow("Result", cnts)
    ch = cv2.waitKey(30)& 0xff
    if ch == 27:
        print('끗')
        break
cv2.destroyAllWindows()