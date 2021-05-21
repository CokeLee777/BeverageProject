from datetime import datetime



a = datetime.today()            # 현재 날짜 가져오기
print(a)


b = datetime.today().year        # 현재 연도 가져오기
print(b)

c = datetime.today().month      # 현재 월 가져오기
print(c)

datetime.today().day        # 현재 일 가져오기


videoname = 'MakingDataSet_' + str(datetime.today().month) + str(datetime.today().day) + '.avi'
print(videoname)