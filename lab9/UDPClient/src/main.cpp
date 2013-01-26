#include "stdafx.h"
#include "udpclient.h"
#include <QtGui/QApplication>

int main(int argc, char *argv[])
{
  WSADATA wsaData;
  WORD wVersionRequested = MAKEWORD( 2, 2 );
  int err = WSAStartup( wVersionRequested, &wsaData );
	if (err){
		printf("Win Socket error!");
		return 1;
	};
  QTextCodec::setCodecForTr(QTextCodec::codecForName("Windows-1251"));

	QApplication a(argc, argv);
	UDPClient w;
	w.show();
	return a.exec();
}
