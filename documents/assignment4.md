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

> [!IMPORTANT]
> 檻一覧取得API、動物一覧取得APIの初期データを変更した様々なパターンで想定通り処理が動くか動作確認してみること

> [!TIP]
余裕があれば重量、収容数は境界値分割、収容可否についてはC0+C2を満たすようパターンを考えて動作確認できると良い


[【ブラックボックステスト技法】同値分割法と境界値分析とは？](https://www.vn.japanquality.asia/post/%E3%80%90%E3%83%96%E3%83%A9%E3%83%83%E3%82%AF%E3%83%9C%E3%83%83%E3%82%AF%E3%82%B9%E3%83%86%E3%82%B9%E3%83%88%E6%8A%80%E6%B3%95%E3%80%91-%E5%90%8C%E5%80%A4%E5%88%86%E5%89%B2%E6%B3%95%E3%81%A8%E5%A2%83%E7%95%8C%E5%80%A4%E5%88%86%E6%9E%90%E3%81%A8%E3%81%AF%EF%BC%9F)

[カバレッジの種類～C0・C1・C2・MCC～](https://tech.nri-net.com/entry/coverage_c0_c1_c2_mcc)

#### git add,git commit,git pushを行う

#### PRを作成してRv依頼

Rv実施後に指摘があれば修正してgit add,git commit,git pushを行い再度Rv依頼
