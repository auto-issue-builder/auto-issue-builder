<h1 align="middle">Simple Q&A</h1>
<p align="middle">자동 이슈 등록 라이브러리</p>

## 소개
개발자들은 이슈로 버그나 수정해야 할 것들을 보는게 편하다 (관리도 편함)

근데 일반 사용자들은(또는 깃허브를 사용하지 않는 사람들) 이슈를 어떻게 쓰는지 모르는 경우가 많다.

그래서 일반 문의처럼 챗에 문의를 하면 자동으로 이슈가 등록되고, 개발자들은 그것을 보고 댓글을 달면 챗에 자동으로 응답이 가도록 하는 라이브러리를 개발할 것이다.

## 프로젝트에 적용시키기

build.grade에 추가

#### gradle 일때
```java
allprojects {
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
```java
implementation 'com.github.simpleqa:simpleqa-springboot:1.1.5'
```

<br/>

#### maven 일때
```java
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

```java
<dependency>
	    <groupId>com.github.simpleqa</groupId>
	    <artifactId>simpleqa-springboot</artifactId>
	    <version>1.1.5</version>
	</dependency>
```

## 라이브러리 호출

import
```java
import org.simpleqa.IssueMessage;
import org.simpleqa.SimpleSendIssue;
```

<br/>

method
```java
SimpleSendIssue.send(message);
```

<br/>

예시
```java
@PostMapping("/new")
    public void test(@RequestBody IssueInfo info) {
    /*
    IssueMessage로 변환해야 합니다. IssueMessage는 Builder 패턴으로 생성합니다. 
    총 4가지의 인자를 필요로 합니다.
    - 적용시킬 자신의 프로젝트 레파지토리 링크
    - 요청한 유저의 닉네임(이름)
    - 제목
    - 내용
    */
        IssueMessage message = IssueMessage.builder()
                .githubRepoPath(info.getGithubRepoPath())
                .nickname(info.getNickname())
                .title(info.getTitle())
                .content(info.getContent())
                .build();
        SimpleSendIssue.send(message);
    }
```
