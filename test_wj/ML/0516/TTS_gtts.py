from gtts import gTTS
# import os
import time
# import playsound
import vlc

def speak(text):
    tts = gTTS(text=text,lang='ko')
    filename ='voice0429.mp3'
    tts.save(filename)
    # playsound.playsound('/home/pi/Desktop/arduino/' + filename)
    # playsound 는 리룩스에서는 작동하지않아요.

    p = vlc.MediaPlayer(filename)
    p.play()
    time.sleep(5)
    
    # print('잘 다음으로 넘어가나요?')
    # os.system('/home/pi/Desktop/arduino/' + filename)
# speak(" 마음이 답답할 땐 청량하게! , 해당 제품은 사이다 입니다. ")

speak(" 올해는 도시마다 다시 즐거움이 켜질거에요! 해당 제품은 코카콜라입니다. ")
