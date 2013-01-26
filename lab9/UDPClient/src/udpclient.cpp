#include "stdafx.h"
#include "udpclient.h"

UDPClient::UDPClient(QWidget *parent, Qt::WFlags flags)
	: QMainWindow(parent, flags)
{
	ui.setupUi(this);
	sSocket = socket(AF_INET, SOCK_DGRAM, IPPROTO_UDP);
	if (sSocket == INVALID_SOCKET){
		QMessageBox::critical(this, "Ошибка", "Ошибка открытия сокета");
		return;
	}
	sockaddr_in   local;
  memset(&local, 0, sizeof(local));
  local.sin_family		= AF_INET;
  local.sin_port			= htons((WORD)2001);
  local.sin_addr.S_un.S_addr = htonl(INADDR_ANY);
	if (bind(sSocket, (sockaddr *)&local, sizeof(local))){
		QMessageBox::critical(this, "Ошибка", "Ошибка подключения сокета. Видимо порт 2001 занят.");
		return;
	}
}

UDPClient::~UDPClient()
{

}

void UDPClient::onGetInfo(){
	char out_buf[200], in_buf[200];
	strncpy(out_buf, "CONTAINQ", 8);
	*( (int *)(out_buf + 8) ) = htonl(1);
	int received = 0;
	received = askServer(out_buf, 12, in_buf, 200);
	if (received == 24){
		if (strncmp(in_buf, "CONTAINR", 8) == 0){
			if (ntohl( *( (int *)(in_buf + 8) ) ) == 1){
				ui.leItemCount->setText( QString::number( ntohl( *( (int *)(in_buf + 12) ) ) ) );
				char c;
				for (int i=0;i<4;i++){
					c = in_buf[16+i]; in_buf[16+i] = in_buf[23-i]; in_buf[23-i] = c;
				}
				ui.leAvgPrice->setText( QString::number(*( (double *)(in_buf + 16) )) );
			}else{
				QMessageBox::critical(this, "Ошибка", "Неверный номер команды");
			};
		}else{
			QMessageBox::critical(this, "Ошибка", "Неверная сигнатура пакета");
		}
	}else{
		QMessageBox::critical(this, "Ошибка", "Неверный размер пакета");
	}
};

void UDPClient::onGetPrice(){
	char out_buf[200], in_buf[200];
	strncpy(out_buf, "CONTAINQ", 8);
	*( (int *)(out_buf + 8) ) = htonl(2);
	*( (int *)(out_buf +12) ) = htonl(ui.sbItemNumber->value());
	int received = 0;
	received = askServer(out_buf, 16, in_buf, 200);
	if (received == 24){
		if (strncmp(in_buf, "CONTAINR", 8) == 0){
			if (ntohl( *( (int *)(in_buf + 8) ) ) == 2){

				char c;
				for (int i=0;i<4;i++){
					c = in_buf[16+i]; in_buf[16+i] = in_buf[23-i]; in_buf[23-i] = c;
				}
				if (ntohl( *( (int *)(in_buf + 12) ) ) == -1)
					ui.leItemPrice->setText(tr("неправильный индекс"));
				else
					ui.leItemPrice->setText(QString::number(*( (double *)(in_buf + 16) )) );
			}else{
				QMessageBox::critical(this, tr("Ошибка"), tr("Неверный номер команды"));
			};
		}else{
			QMessageBox::critical(this, tr("Ошибка"), tr("Неверная сигнатура пакета"));
		}
	}else{
		QMessageBox::critical(this, tr("Ошибка"), tr("Неверный размер пакета"));
	}
};

int  UDPClient::askServer(char *out, int out_cnt, char *in, int in_cnt){
	sockaddr_in to;
	memset(&to, 0, sizeof(to));
	to.sin_family						 = AF_INET;
	to.sin_addr.S_un.S_addr  = inet_addr(ui.leIP->text().toStdString().c_str());
	to.sin_port							 = htons(ui.sbPort->value());
	int z = ::sendto(sSocket, out, out_cnt, 0, (sockaddr *)&to, sizeof(to));
	if (out_cnt != z){
		QMessageBox::critical(this, "Ошибка", "Ошибка посылки пакета");
	} else {
		fd_set  fd_dat, fd_vals;
		TIMEVAL to_vals = {2, 0}, to_dat;
		FD_ZERO(&fd_vals);
		FD_SET(sSocket, &fd_vals);
			fd_dat = fd_vals; to_dat = to_vals;
			int i = select(FD_SETSIZE, &fd_dat, NULL, NULL, &to_dat);
			if (i==SOCKET_ERROR){
				i = WSAGetLastError();
			};
			if (1 == i){
				int iRdCnt = recv(sSocket, (char *)in, in_cnt, 0);     //TODO: Check, it may be buggy
				if (iRdCnt <= 0){
					QMessageBox::critical(this, "Ошибка", "Неверная сигнатура пакета");
					return 0;
				}
				else
					return iRdCnt;
			};
	};
};