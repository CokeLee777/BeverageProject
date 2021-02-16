import numpy as np
import cv2

body_classifier = cv2.CascadeClassifier('haarcascade_fullbody.xml')
cap = cv2.VideoCapture('people1.mp4')
cam = cv2.VideoWriter('harr.avi',cv2.VideoWriter_fourcc('D', 'I', 'V', 'X'),25,(800,600))
while cap.isOpened():
    ret,img = cap.read()
    frame = img
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    bodies = body_classifier.detectMultiScale(gray, 1.2, 3)
    #print(bodies)
    for (x,y,w,h) in bodies:
        cv2.rectangle(frame,(x,y),(x+w,y+h),(0,255,255),2)
    frame2 = cv2.resize(frame,(800,600))
    cv2.imshow('Humans',frame2)
    cam.write(frame2)
    ch = cv2.waitKey(30)& 0xff
    if ch == 27:
        print('끗')
        break
cam.release()
cv2.destroyAllWindows()