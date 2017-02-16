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

	    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
	    lower = np.array([70, 90, 200])
	    upper = np.array([255, 255, 255])
	    mask = cv2.inRange(hsv, lower, upper)
	    res = cv2.bitwise_and(frame, frame, mask=mask)
	    cv2.imshow('mask', res)
	
	    ret, thresh = cv2.threshold(mask, 127, 255, 0)
	    contours, hierarchy = cv2.findContours(thresh.copy(), cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
	
	    XpwY = []
	    XpwYpw = []
	    XY = []
	
	    for c in contours:
	        epsilon = 0.05 * cv2.arcLength(c, True)  # Current optimal: 0.01
	        approx = cv2.approxPolyDP(c, epsilon, True)
	        sides = len(approx)
	
	        x, y, w, h = cv2.boundingRect(approx)
	
	        area = w * h
	        perimeter = (2 * w) + (2 * h)
	
	        if 3000 > area > 50 and 500 > perimeter > 40:  # Current optimal: 100, 50
	            if h > w:  # Checks to make sure the height is greater then the width. This isolates identifying the highgoal as the spoke
	                cv2.drawContours(frame, [approx], -1, (0, 0, 255), 3)
	                try:
	                    area = cv2.contourArea(approx)
	                    hull = cv2.convexHull(approx)
	                    hull_area = cv2.contourArea(hull)
	                    solidity = float(area) / hull_area
	                except ZeroDivisionError:
	                    print ""
	                # print "Solidity: {}".format(solidity)
	                if 0.75 < solidity < 1.2:  # Current optimal is 0.75 < solidity < 1.2
	                    cv2.drawContours(frame, [approx], -1, (255, 0, 0), 3)
	                    # print "Sides: {}".format(sides)
	                    if 4 <= sides <= 8:
	                        cv2.drawContours(frame, [approx], -1, (0, 255, 0), 3)
	                        XpwY.append([x+w, y])
	                        XY.append([x, y])
	                        XpwYpw.append([x+w, y+h])
	                        # cX = (x + (w / 2))
	                        # cY = (y + (h / 2))
	                        # cv2.circle(frame, (cX, cY), 1, (255, 255, 255), 5)
	    if len(XY) == 2:
	        centerX = (XpwY[0][0] + XY[1][0]) / 2
	        centerY = (XpwY[0][1] + XpwYpw[0][1]) / 2
	        center = [centerX, centerY]
	        cv2.circle(frame, (center[0], center[1]), 1, (255, 0, 0), 5)
	
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