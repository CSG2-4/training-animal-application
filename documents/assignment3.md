# 課題2

## 概要

動物を取得するAPIを自分で作成してみる

## API1001(動物一覧取得API)

### 概要

登録されている動物の一覧を取得するAPI

### METHOD

POST

### URL

```
/api/animal/list
```

### Request

```json
{}
```

### Response

```json
{
  "animals": [
    {
      "animalId": "1001",
      "name": "うさぎ",
      "weight": "5Kg"
    },
    {
      "animalId": "1002",
      "name": "ライオン",
      "weight": "150Kg"
    },
    {
      "animalId": "1003",
      "name": "象",
      "weight": "1000Kg"
    }
  ]
}
```

## 課題の進め方

### 事前条件

1. 課題1が完了していること

### 実施内容

#### gitでブランチの作成

ブランチ名
```feature/kuga_assignment3```

#### APIの実装

課題1で動作確認を行った檻一覧取得APIのコードを参考にして動物一覧取得APIを実装する

#### curlの実施

curlコマンドを実行して動作確認を行う。

実行した結果を下記フォルダにテキストファイルとして配置

```
evidence
```

#### git add,git commit,git pushを行う

#### PRを作成してRv依頼

Rv実施後に指摘があれば修正してgit add,git commit,git pushを行い再度Rv依頼
