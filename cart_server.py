import socket
 
host = '192.168.1.101' #host ip
port = 9999 #포트 번호 임의로 설정

data2="SUCCESS"

server_sock = socket.socket(socket.AF_INET)
server_sock.bind((host, port))
server_sock.listen(1)
 
print("장바구니 정보 기다리는 중")
client_sock, addr = server_sock.accept()
 
print('Connected by', addr)
data = client_sock.recv(1024) #자바 client에서 값 받아오기
data = data.decode("utf-8")
print(data)


data1=input("계산완료시 0을  눌러주세요 ")

if(data1==0):
 print(data2)

#자바 client로 값 보내기
client_sock.send(len(data2).to_bytes(4, byteorder="big"))
client_sock.send(str(data2).encode(encoding='utf-8'))

client_sock.close()
server_sock.close()
