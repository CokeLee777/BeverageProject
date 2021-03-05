import cv2 as cv
import numpy as np
import imutils

hsv = 0
#  HSV pixel의 lower과 upper 경계값 정의/스킨 색 경계값 설정
# 'skin'의 범위 값 설정
lower = np.array([0, 48, 80], dtype="uint8")
upper = np.array([20, 255, 255], dtype="uint8")


cap = cv.VideoCapture(0)

def Rotate(src, degrees):
    if degrees == 90:
        dst = cv.transpose(src)
        dst = cv.flip(dst, 1)

    elif degrees == 180:
        dst = cv.flip(src, -1)

    elif degrees == 270:
        dst = cv.transpose(src)
        dst = cv.flip(dst, 0)
    else:
        dst = 0
    return dst


while (True):
    ret, img_color = cap.read()

    # 회전의 중심 축을 정의하면 해당 중심축으로 회전을 함.
    img_color = imutils.rotate(img_color, 45, (0,0))  # 회전 중심축 TOP LEFT
    #위에는 좌표를 줌 (좌표자체는 못돌리는것잉교ㅠ)

    # 회전의 중심축을 정의하지 않으면 그림의 중심이 됨
    #img_color = imutils.rotate(img_color, 45)
    #이건 좌표를 안준다 왜징


    height, width = img_color.shape[:2]
    img_color = cv.resize(img_color, (width, height), interpolation=cv.INTER_AREA)

    # 원본 영상을 HSV 영상으로 변환합니다.
    img_hsv = cv.cvtColor(img_color, cv.COLOR_BGR2HSV)

    # 범위 값으로 HSV 이미지에서 마스크를 생성합니다.
    img_mask = cv.inRange(img_hsv, lower, upper)

    kernel = np.ones((11, 11), np.uint8)
    img_mask = cv.morphologyEx(img_mask, cv.MORPH_OPEN, kernel)
    img_mask = cv.morphologyEx(img_mask, cv.MORPH_CLOSE, kernel)

    #kernel = cv.getStructuringElement(cv.MORPH_ELLIPSE, (11, 11))  # 타원 모양으로 매트리스 생성
    #img_mask = cv.dilate(img_mask, kernel, iterations=1)  # SkinMask의 iterations를 두번 반복(잡힌 범위 주변 margin이 뚱뚱해진다)

    img_mask = cv.GaussianBlur(img_mask, (3, 3), 0)

    # 마스크 이미지로 원본 이미지에서 범위값에 해당되는 영상 부분을 획득합니다.
    img_result = cv.bitwise_and(img_color, img_color, mask=img_mask)

    numOfLabels, img_label, stats, centroids = cv.connectedComponentsWithStats(img_mask)

    for idx, centroid in enumerate(centroids):
        if stats[idx][0] == 0 and stats[idx][1] == 0:
            continue

        if np.any(np.isnan(centroid)):
            continue

        x, y, width, height, area = stats[idx]

        if 160 > width > 80:
            centerX, centerY = int(centroid[0]), int(centroid[1])
            #print(height)
            cv.putText(img_color, 'X : ' + str(centerX), (10, 20), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                       lineType=cv.LINE_AA)
            cv.putText(img_color, 'Y : ' + str(centerY), (10, 50), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                       lineType=cv.LINE_AA)
            if 400 > centerX > 200:
                if 200 > centerY > 100:
                    cv.putText(img_color, 'BP : COKE', (10, 80), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                               lineType=cv.LINE_AA)
                elif 300 > centerY > 200:
                    cv.putText(img_color, 'BP : CIDER', (10, 80), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                               lineType=cv.LINE_AA)
                else:
                    cv.putText(img_color, 'BP : WATER', (10, 80), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                               lineType=cv.LINE_AA)

            elif centerX > 400:
                if 200 > centerY > 100:
                    cv.putText(img_color, 'BP : WJ', (10, 80), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                               lineType=cv.LINE_AA)
                elif 300 > centerY > 200:
                    cv.putText(img_color, 'BP : CM', (10, 80), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                               lineType=cv.LINE_AA)
                else:
                    cv.putText(img_color, 'BP : YJ', (10, 80), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                               lineType=cv.LINE_AA)

            else:
                if 200 > centerY > 100:
                    cv.putText(img_color, 'BP : SAMSUNG', (10, 80), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                               lineType=cv.LINE_AA)
                elif 300 > centerY > 200:
                    cv.putText(img_color, 'BP : APPLE', (10, 80), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                               lineType=cv.LINE_AA)
                else:
                    cv.putText(img_color, 'BP : LG', (10, 80), cv.FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 1,
                               lineType=cv.LINE_AA)

            cv.circle(img_color, (centerX, centerY), 10, (0, 0, 255), 10)
            cv.rectangle(img_color, (x, y), (x + width, y + height), (0, 0, 255))

    #cv.imshow('CAM_RotateWindow', img_color_test)
    cv.imshow('img_color', img_color)

    # ESC 키누르면 종료
    if cv.waitKey(1) & 0xFF == 27:
        break

cv.destroyAllWindows()