# Natural Language Division of the AIWolf Contest

<b>日本語の情報は後半にあります。</b>

New! Regsitration deadline (with submission of self-match game logs) for the 2021 contest has extended to **July 30**, 2021 (2021/6/24)

大会へのチーム登録の締め切り（予選＝自己対戦のログ提出も同時です）は**2021年7月30日**に延長されました!(2021/6/24)

## Background

We have been annually holding the AI werewolf contests under the AI Werewolf project. The AI werewolf contest has two divisions, the protocol division and the natural language division. The protocol division asks participants to implement an AI werewolf player agent that communicates in a middle language called the AI werewolf protocol. The natural language division asks participants to implement an AI werewolf agent that communicates in natural language. 

## The Werewolf Game in this Shared Task

Agents of the AIWolf NL Division will play the werewolf game of five players, including roles of a seer, a werewolf, a possessed, and two villagers. Players do not know other players' roles. All players, other than the werewolf, are humans. 

A game consists of a couple of days, continuing until the human team or the werewolf team survives. A werewolf can specify and attack another player in the end of the day; the attached player will be eliminated from the game. All of players are required to vote to another player, and a player voted most will be eliminated from the game. When humans survive, the villager team wins. When a werewolf survives, the werewolf team wins. A possessed is a human but belongs to the werewolf team. A seer can specify another player in the end of the day, then either human or werewolf is notified.       

## Agent Technical Specifications

### Language Requirement

A shared task participant of AIWolf NL Division is required to implement an AI werewolf agent that communicate either in English or Japanese. Agents of the Japanese language are required to make an English version, at least by using machine translation internally.  

### AIWolf Agent APIs

A shared task participant of AIWolf NL Division is required to implement an AI werewolf agent that connects to our AIWolf server via network. Therefore, there is no limitation on the client side hardware/software other than the network I/O specifications. 

### Agent Specification

* Day 0 has greetings only.
* The end of Day 0 has an inspection by a seer, and the game starts from Day 1. 
* After Day 1, the end of the days have votes by all players and an attack by a werewolf. Vote, attack, inspection are made via specific APIs (network communications).  
* A day consists of a couple turns, where all of agents can make a talk for each turn, receiving talks of previous turns. 
* (New from 2020) An agent is required to talk for each turn in the day, but the order within a talk is random. An agent could be asked to make a talk just after the previous talk, or after 8 talks (2 talks of 4 other agents) of other agents. 
* An agent should make a talk within a specified periods after a talk request is sent. 
* During days, Agents can communicate anything in natural language.  A talk should consists of normal letters and punctuations only. An agent returns "Skip" when nothing to talk, returns "Over" if nothing to talk anymore in that day.   
* Use Agent[0x] (e.g. Agent[05], x is 1-5)  to mention other agents．
* An anchor e.g. ">>Agent[0x]" could be inserted at the beginning of a talk to refer to another agent, to whom your agent with to talk with. That agent is assumed to respond something to your agent by using an anchor.

## From Registration to Formal Run

### Registration

A team should send a mail to aiwolf at kanolab.net (replace at by @) to register the shared task, describing your team name, a contact e-mail address, names and affiliations of your members (please mark a contact person when a team consists of multiple members), communication language (English and/or Japanese) of your agent. There is no fee required to register/participate the shared task. You will be notified our preliminary and formal run server location after registration.

### Testing Your Agent System Beforehand

A shared task participant of AIWolfDial 2020 is required to implement an AI werewolf agent that connects to our AIWolf server at the specified timing. We will provide an AIWolf server running, where participants can try connecting with dummy agents to check their system behavior. Participants are required to check their systems certainly work before the shared task run.

### Preliminary Run 

Participants should run your five agents connected to our server, running 50 games. Then submit your game logs to the organisers via e-mail. If there are too many participants to run the formal run, organisers might select formal run teams depending on these logs. In either case, these logs will be used in the final evaluations.

### Formal Run 

A participant team is required to connect an agent to our server at a specified timing to play games with other participants. If your team cannot remotely attend the formal run, you should prepare your system where the organisers can somehow trigger your remote system to connect our server and run, e.g. by via a web browser. 

### System Evaluation

Participants should submit a system design description document to the organizers. This document and logs of the games might be used for research purpose and included and published in our overview paper without any further permission. Participants are encouraged to submit a paper to the workshop.

Games will be between the same agents, different agents, and/or human players. Reviewers will perform subjective evaluations on the game logs, using following criteria:

* A Natural utterance expressions
* B Contextually natural conversation
* C Coherent (not contradictory) conversation 
* D Coherent game actions (vote, attack, divine) with conversation contents 
* E Diverse utterance expressions, including coherent characterization 

Please not that vague utterances, that can be used regardless of contexts, are not always natural in the werewolf game.

# Sample Agent Code

This is a sample agent code and server runtime repository for the Natural Language Division of the AIWolf contest series.

The contest will be held automatically by connecting five agents to our remote server. 
We provide the same game server which can run locally, and a sample agent for your development/test uses.
The same five agents are used in the preliminary contest, while five different agents from five participants are used in the final contest.
The same remote game server will be used both for the preliminary and final contests.

Our sample agent will connect to a specified local/remote game server by randomly selecting one of predefined talks.
This agent is a reference implementation for server testing and connections in your agent development.

## RandomTalkAgent: a sample agent code in Java

Please clone the repository to your Eclipse environment and run the org.aiwolf.ui.bin.AutoStarter class with specifying AutoStarter.ini as its program argumen* https://github.com/aiwolfdial/RandomTalkAgent/blob/master/src/org/aiwolf/ui/bin/AutoStarter.java

By default, this class will run a server and connect five RandamTalkAgents.

You can change the behaviour by editing the .ini file:
* https://github.com/aiwolfdial/RandomTalkAgent/blob/master/res/AutoStarter.ini

When you wish to connect your agent to a remote server, please change as
* network=true
in the .ini file above. The server location is currently
* host: "160.16.83.206"
* port: 10001

Your log will be saved to 
* https://kanolab.net/aiwolf/2021/pre/ (preliminary contest/trial runs) 
* https://kanolab.net/aiwolf/2021/pre/ (final contest)
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

## AIWolf Contest 2021

We will hold the Natural Language Division of the 2021 AIWolf Contest as described in the following website.
http://aiwolf.org/3rd-international-aiwolf-contest
All of the required information is describe in this README.

New! Regsitration deadline (with submission of self-match game logs) for the 2021 contest has extended to **July 30**, 2021 (2021/6/24)

## Previous Contests

2020:
https://sites.google.com/view/aiwolfdial2020

2019:
The previous task in 2019 was held as the AIWolfDial shared task, which is held as part of the AIWolfDial2019 workshop, collocated with the INLG conference.
See https://aiwolfdial.kanolab.net/ for details of the 2019 shared task and the workshop.

# 人狼知能大会 自然言語部門 について

## 目的

「汝は人狼なりや？」（以下、「人狼ゲーム」）は、話し合いを通じて行われるコミュニケーションゲームです。プレイヤーは勝つために自分自身の認知能力を十分に発揮しなければなりません。人狼ゲームはチェスやリバーシなどの完全情報ゲームとは異なり、隠された情報のある不完全情報ゲームです。各プレイヤーは目的を達成するために、他のプレイヤーの会話や行動から、隠された情報を推測し、役割に応じて自分の情報は隠しつつ行動します。プレイヤーは信頼を得るために説得し、嘘つきを見つけるために推測を行うことが必要です。

人狼ゲームは対話を通じて行われますので、対話システムの研究という側面もあります。対話システムの研究は盛んですが、一貫した文脈や複雑な文で自然な会話をするにはまだ不十分です。根本的な問題の1つとして、適切な評価の欠如があります。対話システムの新しい評価方法として、この人狼ゲームの利用を提案します。

人狼ゲームでは、プレイヤーは嘘をつき、説得し、嘘を発見しなければならないので、矛盾や曖昧な反応は「不自然な」ものとして評価され、ゲームで負けてしまいます。さらに、人狼ゲームではあらゆる会話が可能なので、タスク指向および非タスク指向の両方の会話を含んでいます。タスク志向と非タスク志向の中間であることから、完全に自由な対話よりも開発や評価をフォーカスさせやすく、人狼ゲームを評価基準にすることで、より相手の発話を踏まえた自然な対話システムが構築できると期待しています。

## 人狼知能大会とエージェント作成

人狼知能エージェントの仕様を満たした通信をするネットワーク接続の対戦エージェントを実装いただきます。人狼知能大会には別にプロトコル部門があり、基本的に仕様は同一ですがネットワーク対戦であること、プロトコル（人狼言語）ではなく自然言語（日本語または英語）を用いる点など（詳細は後述）が異なります。

## エージェントの要求事項

プログラム実行ファイルの提出ではなく、各自のサーバ・コンピュータからのネットワーク対戦で行います。したがって，予選・本選にプログラムが実行できるPC（リモートサーバも可）等が必要です。対戦サーバは運営側で用意しますが、エージェントの実行に必要な実行環境はご自身でご用意ください。

人狼知能同士だけではなく，人間のプレイヤーを交えた対戦も予定しています．
* ゲームは5人村で行われ、村人2、人狼1、占い師1、狂人1の構成です。
* 会話パート（talk）では自然言語(日本語または英語)での対話を行います。プロトコルなど自然言語以外の使用は禁止です。発話しない場合はSkip、当該のdayで発話をしない場合はOverを返します（プロトコル部門と同じ）。投票、襲撃、占い先の指定などの方法はプロトコル部門と同じです。
* talkでプレイヤーの名前を呼ぶ際はAgent[01]～Agent[05]の形式にしてください。(例：Agent[01]さんは人狼でした) Agent番号は二けたの任意の数字がありえます。
* 「>>Agent[01] 」というようなアンカーをtalkの発言冒頭につけることで、特定のエージェントに向けた発話ができます。発話を向けられたエージェントは、なにか応答することが期待されます。
* 発言はロボット等を使い，音声で再生される場合があります．したがって，音声で再生できない顔文字や絵文字，記号(句読点，！，？を除く)等の使用は控えてください。
* 応答の制限時間は5秒とします。
* 初日（Day 0）にもtalkが行われます。ここでは挨拶などを行うことを想定しています。
* 1日は複数のターンで構成されている．朝は前日の夜に人狼に襲われたプレイヤーの発表と勝敗の判定を行い，昼はプレイヤー間で誰が人狼なのかを議論する．そして，一人一票誰を処刑したいかの投票を行い，最も多く投票されたプレイヤーは即座に処刑される．夜は特殊な行動を行うことが出来る役職の処理を行う．
* (2020年度より) 昼の1ターンでは各エージェントが1度ずつ発話することが要求されるが，その順序はランダムです。そのため，発話直後に新たな発話を求められることや，前回の発話から8発話終わった後に新たな発話を求められることがあります。平均すると、1ターンに相当する5発話が渡されることになります。これにより、疑似的にランダムな発話に近い状況を再現するのが狙いです。

## 予選と本選

予選は自己対戦（自分の同じエージェント同士）の対戦ログを提出します。
本戦は、相互対戦（異なるチームのエージェント同士）で対戦したログを使用します。参加チーム数によっては、予選の結果に基づき本戦出場チームを選抜することがあります。
本戦は英語トラックと日本語トラックを用意する予定です。参加チーム数によっては、日本語のチームの入出力を機械翻訳により英語に変換して英語トラックに参加していただくことがあります。

## 参加方法

一名以上のチーム。参加資格は特にありません。どなたでも参加できます。参加者には大会後にシステムの詳細を記述したドキュメントをご提出いただきます。参加者にはANAC2021への論文提出を推奨します。

チームごとにaiwolf at kanolab.net (at を@にする)にメールにて送付をお願いいたします．必要事項は、チーム名、代表連絡先メールアドレス、代表者（および他のメンバー）氏名、所属名、氏名所属の公開可否（不可の場合はチーム名のみを用います）です。

* 参加チームの登録費は無料です。
* 提出されたドキュメント、大会のログは研究用途で公開・利用することがあります。
* 大会のログに基づき、審査委員による主観評価を行います。評価項目は以下を予定しています：

A 発話表現は自然か

B 文脈を踏まえた対話は自然か

C 発話内容は一貫しており矛盾がないか（一貫性に関与しない発話は一貫していないとカウント）

D ゲーム行動（投票、襲撃、占いなど）は対話内容を踏まえているか

E 発話表現は豊かか。エージェントごとに一貫して豊かなキャラクター性が出ているか

どんな文脈でも使えてしまうような曖昧な発話は、人狼においては必ずしも自然ではなく評価を下げる可能性があります。

# 人狼知能大会 自然言語部門 のサンプルエージェントと対戦サーバ

大会の対戦は、我々主催者が提供する対戦サーバに5体のエージェントをリモート接続することで自動実行します。
このサイトでは、開発時向けに、ローカルで実行できるサーバそのものと、接続テストに利用可能なサンプルエージェントを配布しています。
予選、決勝とも予選、決勝ともに同じ形式ですが、予選は自己対戦（自分のエージェントを五体起動して接続）、決勝は相互対戦（異なる参加者のエージェントを一体ずつ、計五体接続）になります。

サンプルエージェントは、対戦サーバに接続し、決め打ちの発話をランダムに発するだけのテスト用実装です。サーバ接続テストや、接続まわりの参考実装としてお使いください。
サンプルエージェントの実行には、このリポジトリを Eclipse にインポートし, 
org.aiwolf.ui.bin.AutoStarter をメインクラスに、AutoStarter.iniをプログラム引数に実行すれば5人人狼で対戦ができます。（同じ実行構成も用意してあります）。https://github.com/aiwolfdial/RandomTalkAgent/blob/master/src/org/aiwolf/ui/bin/AutoStarter.java
java.lang.ClassNotFoundException: com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel という例外が出ますが無視してください。
デフォルトの設定では、ローカルで対戦サーバを起動し、RandamTalkAgents を五体接続します。
設定の変更には、.ini ファイルを編集します：
https://github.com/aiwolfdial/RandomTalkAgent/blob/master/res/AutoStarter.ini

運営の提供する対戦サーバへリモート接続する場合は、
* network=true
と変更します。サーバのIPアドレス、ポート番号は以下の通りです。10001番に接続できるよう、ファイアウォールの設定が必要です。
* host: "160.16.83.206"
* port: 10001

対戦ログは 
* https://kanolab.net/aiwolf/2021/pre/ (予選およびテスト用)
* https://kanolab.net/aiwolf/2021/main/ (決勝)
に保存されます。50ゲーム以上の対戦ログを、参加登録時にお送りください。詳細は 
* https://sites.google.com/view/aiwolfdial2020/%E6%97%A5%E6%9C%AC%E8%AA%9E 
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

# 人狼知能大会 2021 自然言語部門 

大会全般の情報は公式サイト
http://aiwolf.org/3rd-international-aiwolf-contest
も参照してください。自然言語部門に関しては、このREADMEにすべての情報が記載されています。

大会へのチーム登録の締め切り（予選＝自己対戦のログ提出も同時です）は**2021年7月30日**に延長されました!(2021/6/24)

## 過去の大会情報

人狼知能大会 自然言語部門 2020の一般的な情報は、
https://sites.google.com/view/aiwolfdial2020
を参照してください。対戦ログは 
https://kanolab.net/aiwolf/2020/main/
に保存されます。50ゲーム以上の対戦ログを、参加登録時にお送りください。


