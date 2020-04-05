# Natural Language Division of the AIWolf Contest

This is a sample agent code and server runtime repository for the Natural Language Division of the AIWolf contest series.
We will hold the Natural Language Division of the 2020 AIWolf Contest as described in the following website.

https://sites.google.com/view/aiwolfdial2020

The previous task in 2019 was held as the AIWolfDial shared task, which is held as part of the AIWolfDial2019 workshop, collocated with the INLG conference.
See https://aiwolfdial.kanolab.net/ for details of the 2019 shared task and the workshop.

# RandomTalkAgent: a sample agent code in Java

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

Your log will be saved to https://kanolab.net/aiwolf/2020/main/. Please submit your self-game logs (more than 50 games) as your registration (see https://sites.google.com/view/aiwolfdial2020/important-dates for details).

# 人狼知能大会 自然言語部門 2020のサンプルエージェントと対戦サーバ

人狼知能大会 自然言語部門 2020の一般的な情報は、
https://sites.google.com/view/aiwolfdial2020
を参照してください。

サンプルエージェントの実行には、このリポジトリを Eclipse にインポートし, AutoStarter クラスをメインに指定して実行してください:
https://github.com/aiwolfdial/RandomTalkAgent/blob/master/src/org/aiwolf/ui/bin/AutoStarter.java
デフォルトの設定では、ローカルで対戦サーバを起動し、RandamTalkAgents を五体接続します。
設定の変更には、.ini ファイルを編集します：
https://github.com/aiwolfdial/RandomTalkAgent/blob/master/res/AutoStarter.ini

運営の提供する対戦サーバへリモート接続する場合は、
network=true
と変更します。サーバのIPアドレス、ポート番号は以下の通りです。10001番に接続できるよう、ファイアウォールの設定が必要です。
host: "160.16.83.206"
port: 10001

対戦ログは https://kanolab.net/aiwolf/2020/main/　に保存されます。50ゲーム以上の対戦ログを、参加登録時にお送りください。
詳細は https://sites.google.com/view/aiwolfdial2020/%E6%97%A5%E6%9C%AC%E8%AA%9E をご覧ください。

