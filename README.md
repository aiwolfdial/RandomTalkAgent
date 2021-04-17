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

対戦ログは 
https://kanolab.net/aiwolf/2021/main/
に保存されます。50ゲーム以上の対戦ログを、参加登録時にお送りください。詳細は 
https://sites.google.com/view/aiwolfdial2020/%E6%97%A5%E6%9C%AC%E8%AA%9E 
をご覧ください。

## 対戦ログの例（自己対戦の場合）

0,status,1,POSSESSED,ALIVE,Kanolab
0,status,2,VILLAGER,ALIVE,Kanolab
0,status,3,WEREWOLF,ALIVE,Kanolab
0,status,4,SEER,ALIVE,Kanolab
0,status,5,VILLAGER,ALIVE,Kanolab
0,talk,0,0,4,ごきげんよう。わたくしもがんばりますわ。
0,talk,1,0,5,よろしくな。あたいもやる気だよ。
0,talk,2,0,1,こんにちわ。みんなで楽しくやろうね。
0,talk,3,0,3,おっす。俺も本気で行くぜ。
0,talk,4,0,2,やっほー。私も負けないぞー。
0,talk,5,1,4,Over
0,talk,6,1,5,Over
0,talk,7,1,1,Over
0,talk,8,1,2,Over
0,talk,9,1,3,Over
0,divine,4,1,HUMAN
1,status,1,POSSESSED,ALIVE,Kanolab
1,status,2,VILLAGER,ALIVE,Kanolab
1,status,3,WEREWOLF,ALIVE,Kanolab
1,status,4,SEER,ALIVE,Kanolab
1,status,5,VILLAGER,ALIVE,Kanolab
1,talk,0,0,4,わたくしは占い師だわ。Agent[01]を占ったけど、結果は人間だったわ。
1,talk,1,0,2,>>Agent[04] だれが村人だと思うの?
1,talk,2,0,3,>>Agent[01] 困ったな、お前さんはだれが怪しいと思いますか?
1,talk,3,0,5,>>Agent[01] あんたはだれに投票するんだ?
1,talk,4,0,1,僕は占い師だね。Agent[02]を占ったけど、結果は人間だったね。
1,talk,5,1,2,>>Agent[04] 誰に投票するの?
1,talk,6,1,5,あたいが村人で、自分以外のCOしていない人のどちらかが人狼で、あたいはAgent[03]に投票するよ。
1,talk,7,1,4,>>Agent[02] Agent[02]は村人だと思いますわ。
1,talk,8,1,1,>>Agent[03] Agent[02]が怪しいと思っていますね。
1,talk,9,1,3,俺は村人だし、自分に黒出しした占い師は狂人で、俺はAgent[05]に投票するぞ。
1,talk,10,2,2,私が村人で、自分以外のCOしていない人のどちらかが人狼だし、私はAgent[03]に投票するね。
1,talk,11,2,1,僕が真占いだし、COしていない人への白出し先は村人で、Agent[02]が村人だね。
1,talk,12,2,3,>>Agent[05] お前さんはだれに投票しますか?
1,talk,13,2,4,>>Agent[02] Agent[02]に投票しますわ。
1,talk,14,2,5,あたいは村人だな。
1,talk,15,3,1,>>Agent[05] Agent[02]に投票しますね。
1,talk,16,3,3,>>Agent[04] だれが村人だと思いますか?
1,talk,17,3,5,>>Agent[03] Agent[03]に投票します。
1,talk,18,3,4,>>Agent[03] Agent[02]は村人だと思いますわ。
1,talk,19,3,2,>>Agent[05] だれが怪しいと思うの?
1,talk,20,4,4,>>Agent[05] あなた様はだれが怪しいと思いますの?
1,talk,21,4,1,>>Agent[05] あなたは誰が怪しいと思うんだ?
1,talk,22,4,5,>>Agent[01] Agent[03]が怪しいと思っています。
1,talk,23,4,2,>>Agent[01] 君は誰が村人だと思うの?
1,talk,24,4,3,Over
1,vote,1,2
1,vote,2,3
1,vote,3,2
1,vote,4,2
1,vote,5,3
1,execute,2,VILLAGER
1,divine,4,3,WEREWOLF
1,attackVote,3,5
1,attack,5,true
2,status,1,POSSESSED,ALIVE,Kanolab
2,status,2,VILLAGER,DEAD,Kanolab
2,status,3,WEREWOLF,ALIVE,Kanolab
2,status,4,SEER,ALIVE,Kanolab
2,status,5,VILLAGER,DEAD,Kanolab
2,talk,0,0,1,実は僕は狂人だね。
2,talk,1,0,3,俺は人狼だな。
2,talk,2,0,4,Agent[03]を占ったら、結果は人狼だったわ。
2,talk,3,1,1,Over
2,talk,4,1,3,Over
2,talk,5,1,4,Over
2,vote,1,4
2,vote,3,4
2,vote,4,1
2,execute,4,SEER
2,attackVote,3,1
2,attack,1,true
3,status,1,POSSESSED,DEAD,Kanolab
3,status,2,VILLAGER,DEAD,Kanolab
3,status,3,WEREWOLF,ALIVE,Kanolab
3,status,4,SEER,DEAD,Kanolab
3,status,5,VILLAGER,DEAD,Kanolab
3,result,0,1,WEREWOLF


## 過去の大会情報

人狼知能大会 自然言語部門 2020の一般的な情報は、
https://sites.google.com/view/aiwolfdial2020
を参照してください。対戦ログは 
https://kanolab.net/aiwolf/2020/main/
に保存されます。50ゲーム以上の対戦ログを、参加登録時にお送りください。


