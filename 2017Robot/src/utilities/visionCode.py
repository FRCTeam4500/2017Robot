import socket
import threading
import cv2
import numpy as np
import os

data = 0
path_to_streamer = "/media/mjpg-streamer/opencvImage"

class Socket:
    def __init__(self, ip, port):
        self.server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.server.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
        self.ip = ip
        self.port = port
        self.address = (ip, port)

    def send_data(self):
        self.server.sendto(str(data), self.address)


def server_thread(s_class):
    while 1:
        s_class.send_data()


def opencv_thread():
    global data
    cap = cv2.VideoCapture(0)
    cap.set(3, 320)
    cap.set(4, 240)
    cap.set(5, 30)
    while 1:
        _, frame = cap.read()

		'''
		Put vision code here
		'''

        cv2.imwrite(path_to_streamer + "/output.jpg", frame)
        k = cv2.waitKey(5) & 0xFF
        if k == 27:
            break
    cv2.destroyAllWindows()


def main():
    server = Socket("10.45.0.23", 1234)
    socketThread = threading.Thread(target=server_thread, args=(server,))
    opencvThread = threading.Thread(target=opencv_thread)
    socketThread.start()
    opencvThread.start()

if __name__ == '__main__':
    main()