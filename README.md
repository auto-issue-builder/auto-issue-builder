<div align="center">
	<img src="https://blog.kakaocdn.net/dn/cmRRBo/btr61S2p9kx/sXED6wM7xztK6exvLrFcT1/img.png" width="300">
	<h1  style="color:black" >Auto Issue Builder</h1>
	<p>
		<b>자동 이슈 등록 라이브러리</b>
	</p>
	<br>
	<br>
	<br>

</div>


자동으로 이슈를 등록해주는 라이브러리입니다. 개발자들은 버그나 수정해야할 사항들을 Github 이슈로 관리하기가 편합니다. <br>
그러나 웹사이트를 사용하는 사용자들은 버그나 이상한 점을 발견했을 때, 이메일이나 Github 이슈가 아닌 다른 방법으로 등록해야 합니다. <br>
이 과정에서 사용자한테 받은 요청사항을 직접 이슈로 옮기기에는 무리가 있습니다. <br>
이 라이브러리를 사용하면 사용자는 일반 문의처럼 문의를 등록만 하면 되고, 설정한 Github 레파지토리의 이슈탭에 이슈가 자동으로 생성이 되게 도와줍니다. 

<br>

## 적용

build.grade에 추가할 수 있습니다.

gradle
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

<br>

maven
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

## 호출
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

## 예시코드
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
