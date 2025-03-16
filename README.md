# GitHub-Client 

# アプリの改善点

## 1. 検索と絞り込み

* **目的:** ユーザーが目的のユーザーやイベントを迅速かつ容易に特定し、快適な操作体験を提供する。
* **機能:**
    * **高度なユーザー検索（GitHub Search API を利用）:**
        * GitHub の `/search/users` エンドポイントを利用し、ユーザー名、フルネーム、場所など、複数の条件でユーザーを検索可能にする。
        * 検索結果は関連度順に表示し、ページネーション、ソート機能などを実装して、大量の検索結果にも柔軟に対応する。
    * **柔軟なイベント絞り込み（ViewModel で処理）:**
        * イベントの種類（例：`PushEvent`、`IssuesEvent`、`PullRequestEvent` など）、期間、リポジトリなどの条件で、表示するイベントを細かく絞り込めるようにする。
        * 絞り込み条件は、チェックボックス、ドロップダウンリスト、日付ピッカーなどを組み合わせ、直感的に操作できるようにする。
* **利点:**
    * 高度な検索機能により、目的のユーザーを素早く正確に見つけられる。
    * 柔軟な絞り込み機能により、ユーザーは関心のある情報に効率的にアクセスできる。

## 2. キャッシュとオフライン対応

* **目的:** インターネット接続がない状況でも情報にアクセスできるようにし、性能を向上させる。
* **機能:**
    * **ユーザー、プロフィール、イベントをRoomに保存:**
        * Roomデータベースを利用し、ユーザー検索結果、ユーザープロフィール、イベントデータをローカルに記憶します。
        * キャッシュするデータ量と有効期限を設定し、ストレージ効率とデータ鮮度を両立させます。
        * ネットワーク接続が無いとき、キャッシュからデータを表示します。
        * ネットワーク接続が復帰した際に、情報の再同期を行います。
* **利点:**
    * オフライン環境でも、最近閲覧した情報を利用できます。
    * キャッシュにより、APIリクエストを減らし、アプリの応答速度を向上させます。

## 3. イベントにクリック可能なリンクを追加

* **目的:** イベントから関連するGitHubページへの直接的なアクセスを可能にし、ユーザーの利便性を高めます。
* **機能:**
    * **リポジトリやWikiページを開くクリック機能:**
        * イベントのリポジトリ名や関連するURLをタップすると、外部ブラウザで該当するGitHubページを開きます。
* **利点:**
    * GitHub上の関連情報に素早くアクセスできます。
    * ユーザーはアプリから離れることなく、詳細な情報を確認できます。

## 4. 認証を追加してトークンを利用

* **目的:** 非公開リポジトリやフォロー/フォロワーリストなど、認証が必要な情報へのアクセスを可能にします。
* **機能:**
    * **プライベートデータへのアクセスとレート制限の増加:**
        * ユーザーがGitHubパーソナルアクセストークンを入力し、アプリに認証情報を保存できるようにします。
        * 認証されたユーザーは、プライベートリポジトリのイベントや詳細情報にアクセスできます。
        * 認証された場合、GitHub APIのレート制限が向上し、より多くのリクエストを処理できます。
    * **フォロー中/フォロワーを表示するタブの追加:**
        * `/users/{username}/followers`および`/users/{username}/following`エンドポイントを利用し、フォロー中およびフォロワーのリストを表示するタブを追加します。
        * フォロー/フォロワーのリストでは、ユーザー名、プロフィール画像、簡単なプロフィール情報などを表示します。
* **利点:**
    * 認証により、プライベートな情報にアクセスし、より包括的なGitHub体験を提供します。
    * レート制限の向上により、アプリの利用頻度を高めることができます。


# App Improvements

## 1. Search and Filtering

* **Purpose:** To enable users to quickly and easily locate desired users and events, providing a comfortable user experience.
* **Features:**
    * **Advanced User Search (Utilizing GitHub Search API):**
        * Enable user searches with multiple criteria, such as username, full name, and location, using GitHub's `/search/users` endpoint.
        * Display search results in order of relevance, implementing pagination and sorting functions to flexibly handle large volumes of search results.
    * **Flexible Event Filtering (Processed in ViewModel):**
        * Enable detailed filtering of displayed events by event type (e.g., `PushEvent`, `IssuesEvent`, `PullRequestEvent`), date range, repository, and other criteria.
        * Combine filtering conditions using checkboxes, dropdown lists, date pickers, etc., to provide intuitive operation.
* **Benefits:**
    * Advanced search functionality allows users to quickly and accurately find their desired users.
    * Flexible filtering functionality enables users to efficiently access information of interest.

## 2. Caching and Offline Support

* **Purpose:** To enable data access even without an internet connection, improving performance.
* **Features:**
    * **Save Users, Profiles, and Events in Room:**
        * Utilize the Room database to locally store user search results, user profiles, and event data.
        * Set the amount of data to be cached and its expiration date to balance storage efficiency and data freshness.
        * Display data from the cache when there is no network connection.
        * Perform data re-synchronization when the network connection is restored.
* **Benefits:**
    * Users can utilize recently viewed information even in an offline environment.
    * Caching reduces API requests and improves app response speed.

## 3. Add Clickable Links to Events

* **Purpose:** To enable direct access to related GitHub pages from events, enhancing user convenience.
* **Features:**
    * **Click to Open Repository/Wiki Pages:**
        * Tapping on an event's repository name or related URL opens the corresponding GitHub page in an external browser.
* **Benefits:**
    * Provides quick access to related information on GitHub.
    * Users can check detailed information without leaving the app.

## 4. Add Authentication and Utilize Tokens

* **Purpose:** To enable access to authenticated information, such as private repositories and follower/following lists.
* **Features:**
    * **Access Private Data and Increase Rate Limits:**
        * Allow users to input their GitHub personal access token and store authentication information within the app.
        * Authenticated users can access events and detailed information from private repositories.
        * When authenticated, GitHub API rate limits are increased, allowing for the processing of more requests.
    * **Add a Tab to Display Following/Followers:**
        * Utilize the `/users/{username}/followers` and `/users/{username}/following` endpoints to add a tab displaying lists of following and followers.
        * Display usernames, profile images, and brief profile information in the following/follower lists.
* **Benefits:**
    * Authentication enables access to private information, providing a more comprehensive GitHub experience.
    * Increased rate limits allow for more frequent use of the app.