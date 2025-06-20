# 2025年度 根本課新規配属者課題

## 動物管理アプリを作ろう!

### 事前準備

以下をinstallする

- IntelliJ IDEA Community Edition
- java openjdk21

### アプリの起動方法

intelliJのターミナルから以下コマンドで実行

```
./gradlew bootRun
```

```
Tomcat initialized with port 8080 (http)
```

というmessageが表示されたら起動成功

> [!TIP]
> エラーが出て起動できない場合はログの中に
> ERR,Error,ERROR
> という文言があるので、そのあとに表示されるメッセージでgoogleで検索してみる。

> [!TIP]
> エラーの原因が特定できない場合は```./gradlew bootRun --stacktrace```を付けて実行してみる。
> ログの中にStackTraceが表示されるので、StackTraceの中からエラーの原因になりそうなメッセージを探してみる。

[Javaのエラー解決の王道～スタックトレースの読み方～](https://ittoybox.com/archives/588)

### アプリの要件

[動物管理アプリ](https://github.com/CSG2-4/training-animal-application/blob/master/documents/assignment1.md)

#### 課題1 APIを実行してみる

[課題1](https://github.com/CSG2-4/training-animal-application/blob/master/documents/assignment2.md)

#### 課題2 動物を取得するAPIを作成する

[課題2](https://github.com/CSG2-4/training-animal-application/blob/master/documents/assignment3.md)

#### 課題3 動物を檻に収容するAPIを作成する

[課題3](https://github.com/CSG2-4/training-animal-application/blob/master/documents/assignment4.md)

#### 課題4 動物一覧取得APIの修正

[課題4](https://github.com/CSG2-4/training-animal-application/blob/master/documents/assignment5.md)
