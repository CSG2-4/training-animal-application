# 2025年度 根本課新規配属者課題
## 動物管理アプリを作ろう!
### 事前準備
以下をinstallする
- IntelliJ IDEA Community Edition
- java openjdk21

:::note info
intelliJ インストール　windows
intelliJ インストール　mac
java21 インストール　windows
java21 インストール　mac
:::


### アプリの起動方法
intelliJのターミナルから以下コマンドで実行
 `./gradlew bootRun`

:::note info
エラーが出て起動できない場合はログの中に
ERR,Error,ERROR
という文言があるので、そのあとに表示されるメッセージでgoogleで検索してみる。
:::

`Tomcat initialized with port 8080 (http)`
というmessageが表示されたら起動成功

### アプリの要件
#### 概要
用意してある檻に動物を入れて管理するアプリを作成する。
檻にはそれぞれ、最大重量、最大動物収容数が設定されており、それぞれの最大値を超えて動物を檻に入れることはできない。
#### 檻の仕様
檻には3種類の檻が用意されている。
- 普通の檻
  - 最大重量:500kg
  - 最大動物収容数:5匹
- 頑丈な檻
  - 最大重量:9999kg
  - 最大動物収容数:5匹
- 広い檻
  - 最大重量:500kg
  - 最大動物収容数:99匹
 
#### 動物の仕様
動物はそれぞれ名前、体重のステータスを持っている。

### 課題
#### 課題1 APIを実行してみる
すでにAPIを実装しているのでcurlを使ってAPIを実行してみる。

##### API仕様
*API名*
API0001(檻一覧取得API)
*概要*
登録されている檻の一覧を取得するAPI
*URL*
`/api/cage/list`
*Request*
```json
{}
```

*Response*
```
{
  "cages": [
    {
      "cageId": "0001",
      "name": "普通の檻",
      "limitWeight": 500,
      "limitSize": 5
    },
    {
      "cageId": "0002",
      "name": "頑丈な檻",
      "limitWeight": 9999,
      "limitSize": 5
    },
    {
      "cageId": "0003",
      "name": "広いな檻",
      "limitWeight": 500,
      "limitSize": 99
    }
  ]
}
```

コマンドを実行してメッセージが返ってきたら成功
```
curl -XPOST -H "Content-Type: application/json"  --data '{}' "http://localhost:8080/api/cage/list"

{"cages":[{"cageId":"0001","name":"普通の檻","limitWeight":500,"limitSize":5},{"cageId":"0002","name":"頑丈な檻","limitWeight":9999,"limitSize":5},{"cageId":"0003","name":"広いな檻","limitWeight":500,"limitSize":99}]}
```

#### 課題2 動物を取得するAPIを作成する
##### API仕様
*API名*
API1001(動物一覧取得API)
*概要*
登録されている動物の一覧を取得するAPI
*URL*
`/api/animal/list`
*Request*
```json
{}
```

*Response*
```
{
  "animals": [
    {
      "animalId": "1001",
      "name": "うさぎ",
      "weight": 5,
    },
    {
      "animalId": "1002",
      "name": "ライオン",
      "weight": 150,
    },
    {
      "animalId": "1003",
      "name": "象",
      "weight": 1000,
    },
  ]
}
```

##### 進め方
1. master-kugaブランチから新規にブランチを作成する。
   ```git branch作成```
2. 機能の実装を行う。
   デフォルトでResponseの3匹の動物を追加しておく。
3. commit,pushをして、PRを作成。
```
git commit
git push 
github PR作成
```

#### 課題3 動物を檻に追加するAPIを作成する
##### API仕様
*API名*
API3001(動物収容API)
*概要*
動物を檻に追加する。
*URL*
`/api/house/post`
*Request*
```json
{
 "cageId": "0001",
 "animalId": "1001"
}
```

*200 Response*
```
{
  "result": "OK",
  "message": null
}
```

*Error Response*
```
{
  "result": "NG",
  "message": "最大収容数を超えています"
}
```

##### 進め方
課題2と同じ

#### 課題4 動物一覧取得APIの修正
*API名*
API0001(檻一覧取得API)
*概要*
登録されている檻の一覧を取得するAPI
*URL*
`/api/cage/list`
*Request*
```json
{}
```

*Response*
```
{
  "cages": [
    {
      "cageId": "0001",
      "name": "普通の檻",
      "limitWeight": 500,
      "limitSize": 5,
      "totalWeight":10,
      "totalSize":2,
      "animals": [
       {
        "animalId": "1001",
        "name": "うさぎ",
       },
       {
        "animalId": "1001",
        "name": "うさぎ",
       }
      ]
    },
    {
      "cageId": "0002",
      "name": "頑丈な檻",
      "limitWeight": 9999,
      "limitSize": 5,
      "totalWeight":0,
      "totalSize":0,
      "animals": []
    },
    {
      "cageId": "0003",
      "name": "広いな檻",
      "limitWeight": 500,
      "limitSize": 99,
      "totalWeight":0,
      "totalSize":0,
      "animals": []
    }
  ]
}
```


##### 進め方
課題2と同じ