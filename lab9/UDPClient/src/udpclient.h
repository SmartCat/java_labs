#ifndef UDPCLIENT_H
#define UDPCLIENT_H

#include <QtGui/QMainWindow>
#include "ui_udpclient.h"
#include <winsock2.h>


class UDPClient : public QMainWindow
{
	Q_OBJECT

public:
	UDPClient(QWidget *parent = 0, Qt::WFlags flags = 0);
	~UDPClient();

private:
	Ui::UDPClientClass ui;
	SOCKET  sSocket;

	int  UDPClient::askServer(char *out, int out_cnt, char *in, int in_cnt);

protected slots:
	void onGetInfo();
	void onGetPrice();
};

#endif // UDPCLIENT_H
