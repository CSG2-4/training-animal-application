# 課題3

## 概要

動物を檻に収容するAPIを作成する

## API3001(動物収容API)

### 概要

動物を檻に収容するAPI

動物の収容が成功した場合はSUCCESSのレスポンスを返却する

最大重量、最大収容数を超えて収容した場合はWARNレスポンスを返却する

動物の収容に失敗した場合、または処理継続不能な事象が発生した場合がERRORレスポンスを返却する

### METHOD

POST

### URL

```
/api/house/post
```

### Request

```json
{
  "cageId": "0001",
  "animalId": "1001"
}
```

### Response

#### SUCCESS

```json
{
  "result": "OK",
  "message": null
}
```

#### WARN

```json
{
  "result": "WARN",
  "message": "最大重量が10Kgオーバーしました。"
}
```

#### ERROR

```json
{
  "result": "ERROR",
  "message": "最大収容数を超えています"
}
```

## 課題の進め方

### 事前条件

1. 課題1が完了していること
1. 課題2が完了していること

### 実施内容

#### gitでブランチの作成

ブランチ名
```feature/kuga_assignment4```

#### APIの実装

APIを実装する

> [!IMPORTANT]
> 各レスポンス内のメッセージは一例なため、発生した自称に応じて適切なメッセージを設定して 返却すること

#### curlの実施

curlコマンドを実行して動作確認を行う。

実行した結果を下記フォルダにテキストファイルとして配置

```
evidence
```

#### git add,git commit,git pushを行う

#### PRを作成してRv依頼

Rv実施後に指摘があれば修正してgit add,git commit,git pushを行い再度Rv依頼
