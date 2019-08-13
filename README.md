# RandomTalkAgent
This is a repository for the AIWolfDial shared task, which is held as part of the AIWolfDial2019 workshop, collocated with the INLG conference.
See https://aiwolfdial.kanolab.net/ for details of the shared task and the workshop.

Clone the repository into your Eclipse workspace, then run the AutoStarter class as your main class:
https://github.com/aiwolfdial/RandomTalkAgent/blob/master/src/org/aiwolf/ui/bin/AutoStarter.java

By default, this class will run a server and connect five RandamTalkAgents.

You can change the behaviour by editing the .ini file:
https://github.com/aiwolfdial/RandomTalkAgent/blob/master/res/AutoStarter.ini

When you wish to connect your agent to a remote server, please change as
network=true
in the .ini file above. The server location is currently
host: "160.16.83.206"
port: 10001


