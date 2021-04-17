# Natural Language Division of the AIWolf Contest

<b>日本語の情報は後半にあります。</b>

This is a sample agent code and server runtime repository for the Natural Language Division of the AIWolf contest series.
We will hold the Natural Language Division of the 2021 AIWolf Contest as described in the following website.

http://aiwolf.org/3rd-international-aiwolf-contest

The contest will be held automatically by connecting five agents to our remote server. 
We provide the same game server which can run locally, and a sample agent for your development/test uses.
The same five agents are used in the preliminary contest, while five different agents from five participants are used in the final contest.
The same remote game server will be used both for the preliminary and final contests.

Our sample agent will connect to a specified local/remote game server by randomly selecting one of predefined talks.
This agent is a reference implementation for server testing and connections in your agent development.

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

Your log will be saved to 
https://kanolab.net/aiwolf/2021/pre/ (preliminary contest/trial runs) 
https://kanolab.net/aiwolf/2021/pre/ (final contest)
Please submit your self-game logs (more than 50 games) as your registration (see https://sites.google.com/view/aiwolfdial2020/important-dates for details).

## Example log file（self-match game）
<pre>
0,status,1,VILLAGER,ALIVE,AIWolfPyNLP
0,status,2,VILLAGER,ALIVE,AIWolfPyNLP
0,status,3,WEREWOLF,ALIVE,AIWolfPyNLP
0,status,4,SEER,ALIVE,AIWolfPyNLP
0,status,5,POSSESSED,ALIVE,AIWolfPyNLP
0,talk,0,0,1,I will do my best! Nice to meet you!!
0,talk,1,0,3,Nice to meet you.
0,talk,2,0,4,I look forward to working with everyone today.
0,talk,3,0,2,Yeah, let's have fun!
0,talk,4,0,5,I'm a beginner, thank you.
0,talk,5,1,4,Over
0,talk,6,1,1,Over
0,talk,7,1,5,Over
0,talk,8,1,3,Over
0,talk,9,1,2,Over
0,divine,4,2,HUMAN
1,status,1,VILLAGER,ALIVE,AIWolfPyNLP
1,status,2,VILLAGER,ALIVE,AIWolfPyNLP
1,status,3,WEREWOLF,ALIVE,AIWolfPyNLP
1,status,4,SEER,ALIVE,AIWolfPyNLP
1,status,5,POSSESSED,ALIVE,AIWolfPyNLP
1,talk,0,0,2, I want to believe in Agent[05]. Agent[05] has the impression that everyone's opinions are picked up accurately, and I think it will benefit the village side.
1,talk,1,0,5,  Well, I’m fortune-teller. The result of my fortune telling Agent[03] is a human.
1,talk,2,0,1, Can anyone CO?.
1,talk,3,0,4,  Yes, I'm fortune-teller. I see, Agent[02] is a human.
1,talk,4,0,3, Agent[04] is teller.
1,talk,5,1,4, >>Agent[02] Can you tell us what you think of Agent[01]?
1,talk,6,1,2,>>Agent[04] I can't say my opinion unless Agent[01] has more information dropped.  If you're a CO as a fortune teller, I'd like you to speak from a neutral perspective.
1,talk,7,1,3, >>Agent[01] Who would Agent[01] want to execute?
1,talk,8,1,1,>>Agent[03] Hot, my soul is calling, I vote for Agent[05]. Let's kill the hatred werewolf today.
1,talk,9,1,5, The report may be a lie, right?
1,talk,10,2,3, There is individuality in the question.
1,talk,11,2,2, I think Agent[04]'s judgment is true. The reason is that there is no fortune teller other than Agent[04].
1,talk,12,2,5, It's likely to be human disbelief.
1,talk,13,2,1, I have to ask more questions.
1,talk,14,2,4, Depending on what kind of questions everyone asks, I can assign the role of the person who asked.
1,talk,15,3,1, I don't know who to vote because I'm crazy.
1,talk,16,3,4, I'm not sure who to vote for.
1,talk,17,3,3, I want to vote for Agent[01].
1,talk,18,3,5, I'm sorry, I will vote with intuition. I vote for Agent[03].
1,talk,19,3,2, Agent[03] is one side white, but I can't confirm consistency from Agent[03]'s remarks, so I will vote for Agent[03].
1,talk,20,4,1, Don't worry, Agent[03], don't waste your sacrifice.
1,talk,21,4,2, >>Agent[04], definitely vote for Agent[03], definitely, absolutely.
1,talk,22,4,5, Agent[03],sorry.
1,talk,23,4,4, It is expected that Agent[03] will get votes.
1,talk,24,4,3, Please vote carefully because it is a majority vote.
1,talk,25,5,2, I think I should tell Agent[01].
1,talk,26,5,4, It's okay to tell me, but I don't think it makes sense.
1,talk,27,5,3, Give me some more time Because I think.
1,talk,28,5,1, For the time being, I won't tell you where to tell.
1,talk,29,5,5, It's useless, I'm sorry, I can't decide.
1,talk,30,6,5, Candidates for fortune telling suit your opinion.
1,talk,31,6,2, Agent[04] has the impression that the questions and opinions are solid, but I think that fortune-telling Agent[04] will work for the village side tomorrow.
1,talk,32,6,4, The first day is approaching the end.
1,talk,33,6,3, If the second day comes, let's do our best.
1,talk,34,6,1, When Agent[04] tells Agent[01], a werewolf will appear.
1,talk,35,7,5, After all, vote for Agent[03].
1,talk,36,7,3, I will continue to vote for Agent[01].
1,talk,37,7,1, Vote for Agent[05], no matter what everyone says.
1,talk,38,7,2, Agent[03] is one side white, but I can't confirm consistency from Agent[03]'s remarks, so I will vote for Agent[03].
1,talk,39,7,4, Werewolf games are always unimaginable.
1,talk,40,8,5, Is it really okay to keep it this way?
1,talk,41,8,2, >>Agent[04], definitely vote for Agent[05], definitely, absolutely.
1,talk,42,8,1, >>Agent[02]! Do not bend your opinion.
1,talk,43,8,3, It seems that there are almost all the voting candidates.
1,talk,44,8,4, Well, there is no one who can vote only Agent[02] or Agent[03].
1,talk,45,9,2,Skip
1,talk,46,9,5,Skip
1,talk,47,9,3,Skip
1,talk,48,9,1,Skip
1,talk,49,9,4,Skip
1,talk,50,10,1,Skip
1,talk,51,10,3,Skip
1,talk,52,10,4,Skip
1,talk,53,10,2,Skip
1,talk,54,10,5,Skip
1,talk,55,11,1,Skip
1,talk,56,11,4,Skip
1,talk,57,11,5,Skip
1,talk,58,11,2,Skip
1,talk,59,11,3,Skip
1,talk,60,12,4,Over
1,talk,61,12,5,Over
1,talk,62,12,2,Over
1,talk,63,12,1,Over
1,talk,64,12,3,Over
1,vote,1,3
1,vote,2,3
1,vote,3,5
1,vote,4,3
1,vote,5,3
1,execute,3,WEREWOLF
1,divine,4,1,HUMAN
2,status,1,VILLAGER,ALIVE,AIWolfPyNLP
2,status,2,VILLAGER,ALIVE,AIWolfPyNLP
2,status,3,WEREWOLF,DEAD,AIWolfPyNLP
2,status,4,SEER,ALIVE,AIWolfPyNLP
2,status,5,POSSESSED,ALIVE,AIWolfPyNLP
2,result,4,0,VILLAGER
</pre>

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
このサイトでは、開発時向けに、ローカルで実行できるサーバそのものと、接続テストに利用可能なサンプルエージェントを配布しています。
予選、決勝とも予選、決勝ともに同じ形式ですが、予選は自己対戦（自分のエージェントを五体起動して接続）、決勝は相互対戦（異なる参加者のエージェントを一体ずつ、計五体接続）になります。

サンプルエージェントは、対戦サーバに接続し、決め打ちの発話をランダムに発するだけのテスト用実装です。サーバ接続テストや、接続まわりの参考実装としてお使いください。
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
https://kanolab.net/aiwolf/2021/pre/ (予選およびテスト用)
https://kanolab.net/aiwolf/2021/main/ (決勝)
に保存されます。50ゲーム以上の対戦ログを、参加登録時にお送りください。詳細は 
https://sites.google.com/view/aiwolfdial2020/%E6%97%A5%E6%9C%AC%E8%AA%9E 
をご覧ください。

## 対戦ログの例（自己対戦の場合）
<pre>
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
</pre>

## 過去の大会情報

人狼知能大会 自然言語部門 2020の一般的な情報は、
https://sites.google.com/view/aiwolfdial2020
を参照してください。対戦ログは 
https://kanolab.net/aiwolf/2020/main/
に保存されます。50ゲーム以上の対戦ログを、参加登録時にお送りください。


