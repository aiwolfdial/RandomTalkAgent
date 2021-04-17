# Natural Language Division of the AIWolf Contest

This is a sample agent code and server runtime repository for the Natural Language Division of the AIWolf contest series.
We will hold the Natural Language Division of the 2021 AIWolf Contest as described in the following website.

http://aiwolf.org/3rd-international-aiwolf-contest

## RandomTalkAgent: a sample agent code in Java

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

## Previous Contests

2020:
https://sites.google.com/view/aiwolfdial2020

2019:
The previous task in 2019 was held as the AIWolfDial shared task, which is held as part of the AIWolfDial2019 workshop, collocated with the INLG conference.
See https://aiwolfdial.kanolab.net/ for details of the 2019 shared task and the workshop.


# 人狼知能大会 2021 自然言語部門 のサンプルエージェントと対戦サーバ

大会全般の情報は公式サイト
http://aiwolf.org/3rd-international-aiwolf-contest
を参照してください。

大会の対戦は、我々主催者が提供する対戦サーバに5体のエージェントをリモート接続することで自動実行します。
予選、決勝とも予選、決勝ともに同じ形式ですが、予選は自己対戦（自分のエージェントを五体起動して接続）、決勝は相互対戦（異なる参加者のエージェントを一体ずつ、計五体接続）になります。

サンプルエージェントは、この対戦サーバに接続し、決め打ちの発話をランダムに発するだけのテスト用実装です。サーバ接続テストや、接続まわりの参考実装としてお使いください。
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

## 過去の大会情報

人狼知能大会 自然言語部門 2020の一般的な情報は、
https://sites.google.com/view/aiwolfdial2020
を参照してください。
対戦ログは https://kanolab.net/aiwolf/2021/main/　に保存されます。50ゲーム以上の対戦ログを、参加登録時にお送りください。


