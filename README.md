# ChatRoom. Программа Messenger.

Состоит из двух приложений вместо одного:
server отвечает за серверную часть;
client отвечает за клиентскую часть.

Т.е. одна часть программы будет на одном компьютере, а вторая на другом. 
И они смогут общаться друг с другом.
 
В этом варианте программа будет работать на одном компьютере:
1)Запускаем серверную часть...  Серверная часть ждёт клиента;
2)Запускаем клиентскую часть... Клиент подключается к серверу;
3)Теперь можно обмениваться сообщениями.

Для того, чтобы программа работала на двух разных компьютерах:
1)Нужно скомпилировать такой клиент, у которого будет указан IP address того компьютера,
на котором будет работать серверная часть;
2)Артефакт сохраняем на другом компьютере;
3)Запускаем серверную часть;
4)Запускаем клиентскую часть (собранный .jar файл на втором компьютере);
5)Теперь можно обмениваться сообщениями.

Спасибо за внимание!))
