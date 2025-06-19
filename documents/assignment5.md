# 課題4

## 概要

動物一覧取得APIの修正

## API0001(檻一覧取得API)

### 概要

登録されている檻の一覧を取得するAPI

### METHOD

POST

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
      "limitSize": "5匹",
      "totalWeight": "10Kg",
      "totalSize": "2匹",
      "animals": [
        {
          "name": "うさぎ"
        },
        {
          "name": "うさぎ"
        }
      ]
    },
    {
      "cageId": "0002",
      "name": "頑丈な檻",
      "limitWeight": "9999Kg",
      "limitSize": "5匹",
      "totalWeight": "0Kg",
      "totalSize": "0匹",
      "animals": []
    },
    {
      "cageId": "0003",
      "name": "広いな檻",
      "limitWeight": "500Kg",
      "limitSize": "99匹",
      "totalWeight": "0Kg",
      "totalSize": "0匹",
      "animals": []
    }
  ]
}
```

#### 変更差分
```diff
{
  "cages": [
    {
      "cageId": "0001",
      "name": "普通の檻",
      "limitWeight": "500Kg",
-      "limitSize": "5匹"
+      "limitSize": "5匹",
+      "totalWeight": "10Kg",
+      "totalSize": "2匹",
+      "animals": [
+        {
+          "name": "うさぎ"
+        },
+        {
+          "name": "うさぎ"
+        }
+      ]
    },
    {
      "cageId": "0002",
      "name": "頑丈な檻",
      "limitWeight": "9999Kg",
-      "limitSize": "5匹"
+      "limitSize": "5匹",
+      "totalWeight": "0Kg",
+      "totalSize": "0匹",
+      "animals": []
    },
    {
      "cageId": "0003",
      "name": "広いな檻",
      "limitWeight": "500Kg",
-      "limitSize": "99匹"
+      "limitSize": "99匹",
+      "totalWeight": "0Kg",
+      "totalSize": "0匹",
+      "animals": []
    }
  ]
}
```

## 課題の進め方

### 事前条件

1. 課題1が完了していること
1. 課題2が完了していること
1. 課題3が完了していること

### 実施内容

#### gitでブランチの作成

ブランチ名
```feature/kuga_assignment5```

#### APIの実装

APIを修正する

> [!IMPORTANT]
> RDBMSを想定してCageEntityに中に動物をListで持たせるのは禁止とする。
> 檻には複数の動物が収容され、動物も複数の檻に収容されるという、 檻と動物が多：多となるようなリレーションでのテーブルを想定すること。

[やさしい図解で学ぶ　中間テーブル　多対多　概念編](https://qiita.com/ramuneru/items/db43589551dd0c00fef9)

#### curlの実施

curlコマンドを実行して動作確認を行う。

実行した結果を下記フォルダにテキストファイルとして配置

```
evidence
```

#### git add,git commit,git pushを行う

#### PRを作成してRv依頼

Rv実施後に指摘があれば修正してgit add,git commit,git pushを行い再度Rv依頼
