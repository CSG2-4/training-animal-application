# 課題1

## 概要

すでに実装されているAPIを実行して動作確認を行う。

## API0001(檻一覧取得API)

### 概要

登録されている檻の一覧を取得するAPI

### METHOD

POST

[Rest APIで使われるHTTPメソッドとURL設計](https://qiita.com/sfp_waterwalker/items/765abc2b53cc11d5e367)

> [!NOTE]
> GETリクエストではRequest Bodyに値を設定することができないため、クエリパラメータで情報をやりとりすることになる。
> RESTAPIでは取得系のAPIにはGETを使うことが一般的だが、業務系システムではセキュリティの問題からPOSTで情報の取得を行うこともある。

### URL

```
/api/cage/list
```

### Request

```json
{}
```

### Response

```json
{
  "cages": [
    {
      "cageId": "0001",
      "name": "普通の檻",
      "limitWeight": "500Kg",
      "limitSize": "5匹"
    },
    {
      "cageId": "0002",
      "name": "頑丈な檻",
      "limitWeight": "9999Kg",
      "limitSize": "5匹"
    },
    {
      "cageId": "0003",
      "name": "広い檻",
      "limitWeight": "500Kg",
      "limitSize": "99匹"
    }
  ]
}
```

[JSONとは？今更聞けない入門編！使い方やデータフォーマットについて解説](https://datamix.co.jp/media/datascience/introduction-to-json/)

## 課題の進め方

### 事前条件

アプリが起動されていること

### 実施内容

#### gitでブランチの作成

ブランチ名
```feature/kuga_assignment2```

[Git入門：初心者向けの基本操作と概念](https://zenn.dev/kthrlab_blog/articles/9a304808e4ad8b)

- ブランチ作成

#### curlの実施

curlコマンドを実行

```
curl -XPOST -H "Content-Type: application/json"  --data '{}' "http://localhost:8080/api/cage/list"
```

実行した結果を下記フォルダにテキストファイルとして配置

```
evidence
```

[よく使うcurlコマンドのオプション]()https://qiita.com/ryuichi1208/items/e4e1b27ff7d54a66dcd9

> [!NOTE]
> REST APIではRequestBody,ResponseBodyにはjson以外の形式で情報を含めることもできるため、APIに対してjson形式でやりとりすることを明言するために -H "Content-Type: application/json"を追加する必要がる。

#### git add,git commit,git pushを行う

[Git入門：初心者向けの基本操作と概念](https://zenn.dev/kthrlab_blog/articles/9a304808e4ad8b)

- ステージング
- コミット
- リモートレポジトリにPUSH

#### PRを作成してRv依頼
[【GitHub】Pull Requestの作成方法](https://qiita.com/hinakko/items/2be68f1164473d295325)
